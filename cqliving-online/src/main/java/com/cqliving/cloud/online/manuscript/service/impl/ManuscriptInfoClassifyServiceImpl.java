package com.cqliving.cloud.online.manuscript.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptColumns;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptLog;
import com.cqliving.cloud.online.manuscript.dto.ChengKouLikeNews;
import com.cqliving.cloud.online.manuscript.dto.ChengKouListData;
import com.cqliving.cloud.online.manuscript.dto.ChengKouNewsData;
import com.cqliving.cloud.online.manuscript.dto.ManuscriptInfo;
import com.cqliving.cloud.online.manuscript.dto.XiuShanManuscript;
import com.cqliving.cloud.online.manuscript.manager.ManuscriptColumnsManager;
import com.cqliving.cloud.online.manuscript.manager.ManuscriptInfoClassifyManager;
import com.cqliving.cloud.online.manuscript.manager.ManuscriptLogManager;
import com.cqliving.cloud.online.manuscript.service.ManuscriptInfoClassifyService;
import com.cqliving.cloud.online.manuscript.utils.ManuscriptUtils;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.MD5BigFileUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.http.HttpClientUtils;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.utils.XmlUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("manuscriptInfoClassifyService")
@ServiceHandleMapping(managerClass = ManuscriptInfoClassifyManager.class)
public class ManuscriptInfoClassifyServiceImpl implements ManuscriptInfoClassifyService {

	private static final Logger logger = LoggerFactory.getLogger(ManuscriptInfoClassifyServiceImpl.class);

    /** xml网络请求时间 */
    private Long loadXmlMillisecond;
    /** 单个栏目xml总记录数 */
    private Integer xmlLimitCnt = 0;
    
    /** 重庆 */
    public static final Long chongQingAppId =1L;
    /** 城口 */
    private static final Long chengKouAppId = 35L;
    /** 秀山 */
    private static final Long xiuShanAppId = 13L;
    
    /** 城口抓取页数限制 */
    private static final int chengKouMaxPage = 3;
    /** 秀山抓取页数限制 */
    private static final int xiuShanMaxPage = 3;
	
	@Autowired
	private ManuscriptInfoClassifyManager manuscriptInfoClassifyManager;
	@Autowired
	private ManuscriptColumnsManager manuscriptColumnsManager;
    @Autowired
    private AbstractRedisClient abstractRedisClient;
    @Autowired
    private ManuscriptLogManager manuscriptLogManager;
    
	@Override
	public Response<Void> importData() {
		Response<Void> rs = Response.newInstance();
        try {
        	Date now = new Date(), endTime,startTime;
        	//创建人id
			Long createId = PropertiesConfig.getLong(PropertyKey.MANUSCRIPT_CREATE_ID);
			//创建人名称
			String creator = PropertiesConfig.getString(PropertyKey.MANUSCRIPT_CREATE_NAME);
			//查询配置
			Map<String, Object> searchMap = Maps.newHashMap();
			searchMap.put("EQ_appId", chongQingAppId);//
			searchMap.put("EQ_status", ManuscriptColumns.STATUS3);//只有有效的配置数据
			List<ManuscriptColumns> columnsList = manuscriptColumnsManager.query(searchMap, null);
			Long a,b;
			List<ManuscriptInfo> list;
			
			Integer index = 0;
			for(ManuscriptColumns mc : columnsList){
				startTime = new Date();
				logger.debug(String.format("开始解析-->栏目：%s, 地址：%s", mc.getColumnsName(), mc.getRssUrl()));
				loadXmlMillisecond = 0L;
				index = 0;
				try {
					a = new Date().getTime();
					//解析xml
					list = getEffective(mc);
					b = new Date().getTime();
					if(b - a > 2000)
						logger.info(String.format("超时长解析xml地址=%s，用时=%d", mc.getRssUrl(), b-a));
					for(ManuscriptInfo mi : list){
						index += manuscriptInfoClassifyManager.importData(createId, creator, mc, mi, now);
					}
					//结束
					endTime = new Date();
					//保存日志
			        getManuscriptLog(mc, endTime, startTime, index);
				} catch (Exception e) {
					//如果异常后数据回滚了，那么redis缓存也应该删除，否则执行任务的时候就不会保存
					ManuscriptUtils.delCache(abstractRedisClient, mc);//删除id的缓存
					ManuscriptUtils.delCache(abstractRedisClient, "title", mc);//删除标题的缓存
					xmlLimitCnt = 0;
					endTime = new Date();
					//保存异常日志
			        getManuscriptLog(mc, endTime, startTime, index);
			        logger.error("抓稿失败，整个“"+mc.getRssUrl()+"”都没有抓取成功：", e);
//					throw new BusinessException(ErrorCodes.FAILURE, "抓稿失败，整个“"+mc.getRssUrl()+"”都没有抓取成功：" + e);
				}
			}
        } catch (BusinessException e) {
            logger.error("重庆APP新闻抓稿失败："  , e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("重庆APP新闻抓稿失败："  , e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("重庆APP新闻抓稿失败");
        }
        return rs;
	}
	
	/**
	 * Title:保存日志
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月15日
	 * @param mc
	 * @param endTime
	 * @param startTime
	 * @param index
	 * @return
	 */
	private ManuscriptLog getManuscriptLog(ManuscriptColumns mc, Date endTime, Date startTime, int index){
		//用时
		Long useTime = endTime.getTime() - startTime.getTime();
		ManuscriptLog manuscriptLog = new ManuscriptLog();
        manuscriptLog.setEndTime(endTime);
        manuscriptLog.setStartTime(startTime);
        manuscriptLog.setManuscriptColumnsId(mc.getId());
        manuscriptLog.setInsertNum(index);
        manuscriptLog.setOperateMillisecond(useTime.intValue());
        manuscriptLog.setCountNum(xmlLimitCnt);
        manuscriptLog.setLoadXmlMillisecond(loadXmlMillisecond);
        manuscriptLog = manuscriptLogManager.save(manuscriptLog);
        return manuscriptLog;
	}
	
	/**
	 * Title:根据地址返回有效数据
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月8日
	 * @param xmlUrl
	 * @return
	 */
	private List<ManuscriptInfo> getEffective(ManuscriptColumns mc){
		Long s = new Date().getTime();
		String xmlUrl = mc.getRssUrl();
		String xml = HttpClientUtils.get(xmlUrl);
		Long end = new Date().getTime();
		loadXmlMillisecond = end - s;
		if(end - s > 1500)
			logger.info(String.format("超过1500ms访问xml=%s, 用时=%d", xmlUrl, (end - s)));
		List<ManuscriptInfo> list = null;
		try {
//			if(!XmlUtils.isXml(xml)){
//				logger.error("地址：" + xmlUrl + "解析的数据不是xml格式，请检查配置");
//				return null;
//			}
			list = XmlUtils.readXmlToObjects(xml, "/rss/channel/item", ManuscriptInfo.class);
		} catch (DocumentException e) {
			logger.error("地址： “"+xmlUrl+"”解析xml格式有误, : " , e);
			return null;
		}
		List<ManuscriptInfo> manuscriptInfoList = Lists.newArrayList();
		if(list != null && list.size() > 0){
			xmlLimitCnt = list.size();
			for(ManuscriptInfo gi : list){
				if(gi !=null){
					//如果缓存里面已经存在了就说明这条数据已经被抓取过了，就不再做后面的操作。
				    if(!ManuscriptUtils.addCache(abstractRedisClient, mc, gi.getGuid())){
				        gi.setImg(getImageStr(gi.getXMLCONTENT()));//处理图片
                        gi.setMedia(XmlUtils.getMedia(gi.getMedia()));//处理多媒体
                        manuscriptInfoList.add(gi);
				    }
				}
			}
		}
		return manuscriptInfoList;
	}
	
	/**
	 * Title:提取item中的img标签
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月8日
	 * @param xml
	 * @return
	 */
	private String getImageStr(String xml){
		String str = null;
		if(StringUtils.isBlank(xml)){
			return null;
		}
		try {
			Document doc = DocumentHelper.parseText(xml);
			Node node = doc.selectSingleNode("item");
			Object obj = node.selectObject("img");
			if(obj != null && (obj.toString().contains("img") || obj.toString().contains("IMG"))){
				Element el = (Element)obj;
				str = el.asXML();
				if(StringUtils.isBlank(str)){
					return null;
				}
				//解析img标签中的 src
				List<String> ls = XmlUtils.getImgSrc(str);
				if(ls != null && ls.size() > 0){
					return ls.get(0);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	@Override
	public Response<Void> importDataChengKou() {
		Date now = new Date(), endTime,startTime;
    	//创建人id
		Long createId = PropertiesConfig.getLong(PropertyKey.MANUSCRIPT_CK_CREATE_ID);
		//创建人名称
		String creator = PropertiesConfig.getString(PropertyKey.MANUSCRIPT_CK_CREATE_NAME);
		//查询配置
		Map<String, Object> searchMap = Maps.newHashMap();
		searchMap.put("EQ_appId", chengKouAppId);//
		searchMap.put("EQ_status", ManuscriptColumns.STATUS3);//只有有效的配置数据
		List<ManuscriptColumns> columnsList = manuscriptColumnsManager.query(searchMap, null);
		List<ChengKouListData> list;
		Long lastId = null;
		int index;//记录插入了多少条
		int pageSize;//每个栏目第一次只抓取chengKouMaxPage页数，后面就只取一页
		for(ManuscriptColumns mc : columnsList){
			if(StringUtils.isBlank(mc.getDepartment())){
				continue ;
			}
			pageSize = chengKouMaxPage;//控制抓取多少页
			try {
                if(Integer.parseInt(mc.getDepartment()) > 0){//如果不是第一页，那么只抓取1页数据，如果是第一页就要抓取三页数据
                    pageSize = 0; 
                }
            } catch (NumberFormatException e) {}
			lastId = null;
			do {
				index = 0;
				startTime = new Date();
				loadXmlMillisecond = 0L;
				list = getChengKouListObject(mc.getRssUrl(), lastId, mc);//本来这里的第二个参数是上次抓取的最后一条记录的id，但后来改了需求，除了第一次，后面只获取最新的一页数据。
				for(ChengKouListData data : list){
					index += manuscriptInfoClassifyManager.importDataChengKou(createId, creator, mc, data, now);
					lastId = data.getId();
				}
				if(lastId != null && lastId > 0){
				    pageSize --;
					//保存最后一次插入的最新ID,方便下次接着插入
					logger.debug(String.format("执行完一次-------->>>>栏目id=%s， 栏目名称=%s，最后一次id=%s", mc.getColumnsId(), mc.getColumnsName(), lastId.toString()));
					mc.setDepartment(lastId.toString());
					manuscriptColumnsManager.update(mc);
				}
				//结束
				endTime = new Date();
				//保存日志
				getManuscriptLog(mc, endTime, startTime, index);
			} while (list != null && list.size() > 0 && pageSize > 0);
		}
		return null;
	}
	
	/**
	 * Title:城口解析新闻内容 根据栏目id
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月27日
	 * @param xmlUrl
	 * @param newsId最后一条新闻id，第一次为0
	 * @param mc
	 * @return
	 */
	private List<ChengKouListData> getChengKouListObject(String url, Long newsId, ManuscriptColumns mc){
		if(StringUtils.isBlank(url)){
			return null;
		}
		if(newsId == null)
		    newsId = 0L;
		String xmlUrl = String.format(url, newsId.toString())/* + "?order=asc&pageSize=10"*/;
		logger.info("开始访问并解析：" + xmlUrl);
		Long s = new Date().getTime();
		StringBuilder xml = new StringBuilder(HttpClientUtils.get(xmlUrl));
		Long end = new Date().getTime();
		loadXmlMillisecond = end - s;
		List<ChengKouListData> list = Lists.newArrayList();
		try {
			JSONArray array = JSONArray.parseArray(xml.toString());
			if(array != null){
				int len = array.size();
				ChengKouListData data = null;
				for(int i = 0; i < len; i++){
					try {
						data = array.getObject(i, ChengKouListData.class);
						//这里增加一个缓存验证是否已经重复抓取的功能
						if(ManuscriptUtils.addCache(abstractRedisClient, mc, data.getId().toString())){
						    break;
						}
						data.setNewsData(getChengKouNewsList(data));
						list.add(data);
					} catch (Exception e) {
						logger.error("城口获取详细新闻内容出错 : "  , e);
					}
				}
			}
		} catch (Exception e) {
			logger.error("地址： “"+xmlUrl+"”解析格式有误, : "  , e);
		}finally{
			if(xml != null)
				xml.delete(0, xml.length());
		}
		return list;
	}
	
	/**
	 * Title:城口验证
	 * <p>Description:
	 * 超过一个月的数据就不读取了。
	 * 只获取最近一个月以内的数据
	 * </p>
	 * @author DeweiLi on 2017年1月3日
	 * @return true:是一个月以内的数据，false:不是一个月以内的数据，不需要存
	 */
	/*private boolean chengkouLimit(String dateStr){
	    try {
	        if(StringUtils.isNotBlank(dateStr))
	            dateStr = dateStr.replace("/", "-").trim();
	        Date date = DateUtil.parseDate(dateStr);
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, -30);
	        return cal.getTime().before(date);
        } catch (Exception e) {
            logger.error("城口判断是否为一个月以内的数据出错，可能原因是日期格式不正确，新闻日期为：" + dateStr);
        }
        return false;
	}*/
	
	/**
	 * Title:城口--获取详细新闻内容
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月27日
	 * @param data
	 * @return
	 */
	private ChengKouNewsData getChengKouNewsList(ChengKouListData data){
		if(data == null){
			return null;
		}
		ChengKouNewsData newsData = new ChengKouNewsData();
		StringBuilder xml = null;
		try {
			//获取新闻内容
			String url = String.format(ChengKouNewsData.URL, data.getId().toString());
			String xmlStr = HttpClientUtils.get(url);
			if(StringUtils.isBlank(xmlStr)){
			    return null;
			}
			xml = new StringBuilder(xmlStr);
			newsData.setJsonContent(xml.toString());
			JSONObject json = JSONObject.parseObject(xml.toString());
			if(!"0".equals(json.getString("errorcode"))){
				return newsData;
			}
			newsData = JSONObject.parseObject(json.getString("content"), ChengKouNewsData.class);
			newsData.setJsonContent(xml.toString());
			//设置相关新闻
			JSONArray array = JSONArray.parseArray(json.getString("like_news"));
			if(array != null){
				List<ChengKouLikeNews> likeNewsList = Lists.newArrayList();
				for(int i = 0; i < array.size(); i ++){
					likeNewsList.add(array.getObject(i, ChengKouLikeNews.class));
				}
				newsData.setLikeNews(likeNewsList);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			if(xml != null)
				xml.delete(0, xml.length());
		}
		return newsData;
	}

    @Override
    public void impotXiuShan() {
        Date now = new Date(), endTime,startTime;
        //创建人id
        Long createId = PropertiesConfig.getLong(PropertyKey.MANUSCRIPT_XS_CREATE_ID);
        //创建人名称
        String creator = PropertiesConfig.getString(PropertyKey.MANUSCRIPT_XS_CREATE_NAME);
        //查询配置
        Map<String, Object> searchMap = Maps.newHashMap();
        searchMap.put("EQ_appId", xiuShanAppId);//
        searchMap.put("EQ_status", ManuscriptColumns.STATUS3);//只有有效的配置数据
        List<ManuscriptColumns> columnsList = manuscriptColumnsManager.query(searchMap, null);
        List<ManuscriptInfo> list;
        int index = 0;//记录插入了多少条
        XiuShanManuscript xsm = new XiuShanManuscript(abstractRedisClient, manuscriptInfoClassifyManager);
        for(ManuscriptColumns mc : columnsList){
            startTime = DateUtil.now();
            index = 0;
            //获得数据
            xsm.setMc(mc);
            Long s = new Date().getTime();
            list = xsm.getNewsList(mc.getRssUrl(), xiuShanMaxPage);
            Long end = new Date().getTime();
            loadXmlMillisecond = end - s;
            if(list != null && list.size() > 0){
                String redisKey = "";
                ManuscriptColumns mcTmp = new ManuscriptColumns();
                BeanUtils.copyProperties(mc, mcTmp);
                for(ManuscriptInfo mi : list){
                    if(mi == null || StringUtils.isBlank(mi.getGuid()))
                        continue ;
                    redisKey = MD5BigFileUtil.md5(mc.getColumnsId() + "" + mi.getGuid());
                    //下面这个if，处理栏目中出现其他类型的新闻
                    if(StringUtils.isNotBlank(mi.getMedia())){
                        mcTmp.setContextType(Information.CONTEXTTYPE5);
                    }else if(StringUtils.isNotBlank(mi.getImages())){
                        mcTmp.setContextType(Information.CONTEXTTYPE1);
                    }else{
                        mcTmp.setContextType(mc.getContextType());
                    }
                    index += manuscriptInfoClassifyManager.importData(createId, creator, mcTmp, mi, now, redisKey);
                }
            }
            //结束
            endTime = new Date();
            //保存日志
            getManuscriptLog(mc, endTime, startTime, index);
        }
    }
}
