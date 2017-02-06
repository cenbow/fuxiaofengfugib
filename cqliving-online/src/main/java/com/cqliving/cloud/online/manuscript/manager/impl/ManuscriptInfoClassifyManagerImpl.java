package com.cqliving.cloud.online.manuscript.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.app.dao.AppResouseDao;
import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.info.dao.InfoClassifyDao;
import com.cqliving.cloud.online.info.dao.InfoClassifyListDao;
import com.cqliving.cloud.online.info.dao.InfoCorrelationDao;
import com.cqliving.cloud.online.info.dao.InformationContentDao;
import com.cqliving.cloud.online.info.dao.InformationDao;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoClassifyList;
import com.cqliving.cloud.online.info.domain.InfoCorrelation;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.manuscript.dao.ManuscriptInfoClassifyDao;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptColumns;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptInfoClassify;
import com.cqliving.cloud.online.manuscript.dto.ChengKouLikeNews;
import com.cqliving.cloud.online.manuscript.dto.ChengKouListData;
import com.cqliving.cloud.online.manuscript.dto.ChengKouNewsData;
import com.cqliving.cloud.online.manuscript.dto.ManuscriptInfo;
import com.cqliving.cloud.online.manuscript.manager.ManuscriptInfoClassifyManager;
import com.cqliving.cloud.online.manuscript.service.impl.ManuscriptInfoClassifyServiceImpl;
import com.cqliving.cloud.online.manuscript.utils.ManuscriptUtils;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.tool.common.util.HtmlRegexpUtil;
import com.cqliving.tool.common.util.MD5BigFileUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.utils.XmlUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月8日
 */
/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月9日
 */
@Service("manuscriptInfoClassifyManager")
public class ManuscriptInfoClassifyManagerImpl extends EntityServiceImpl<ManuscriptInfoClassify, ManuscriptInfoClassifyDao> implements ManuscriptInfoClassifyManager {
    private static final Logger logger = LoggerFactory.getLogger(ManuscriptInfoClassifyManagerImpl.class);

    @Autowired
    private InfoClassifyListDao infoClassifyListDao;
    @Autowired
    private InformationDao informationDao;
    @Autowired
    private InfoClassifyDao infoClassifyDao;
    @Autowired
    private InfoCorrelationDao infoCorrelationDao;
    @Autowired
    private ManuscriptInfoClassifyDao manuscriptInfoClassifyDao;
    @Autowired
    private AbstractRedisClient abstractRedisClient;
    @Autowired
    private InformationContentDao informationContentDao;
    @Autowired
    private AppResouseDao appResouseDao;
    
	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public Integer importData(Long createId, String creator, ManuscriptColumns mc, ManuscriptInfo mi , Date now) {
		//记录插入数，也用于发布时间的秒
		Integer index = 0;
		Integer multipleImgCount;//多图时记录单个新闻下图片数量，要保存到information表中的multiple_img_count
		Information information;
		InfoClassify infoClassify;
		List<ManuscriptInfoClassify> manuscriptInfoClassifys;
		List<Map<String, String>> imgs;
		InformationContent informationContent;
		Map<String, Object> searchMap = Maps.newHashMap();
		searchMap.put("EQ_oldGuid", mi.getGuid());
		searchMap.put("EQ_appId", mc.getAppId());
		searchMap.put("EQ_isInsert", ManuscriptInfoClassify.ISINSERT1);//要查询入库了的数据 哦
		try {
		    logger.debug("guid=" + mi.getGuid());
			manuscriptInfoClassifys = manuscriptInfoClassifyDao.query(searchMap, null);
			//缓存验证了是否存在之后再查询一次数据库验证一次是否已经保存了
			if(manuscriptInfoClassifys != null && manuscriptInfoClassifys.size() > 0){
			    logger.error("sql查询到有重复数据");
				return 0;
			}
			//验证数据有效性:比如说文本新闻没有内容，多图新闻没有图片，视频新闻没有视频地址等都是无效的信息，无效新闻不入新闻表。 
			if(validateData(mi, mc)){
			    logger.error("数据无效：比如说文本新闻没有内容，多图新闻没有图片，视频新闻没有视频地址guid=" + mi.getGuid());
				//保存关联表
		        getManuscriptInfoClassify(mi, mc, null, now);
				return 0;
			}
			//处理来源(如果数据库配置了来源就取数据库的配置，如果未配置来源就去xml解析的source)
			if(StringUtils.isBlank(mc.getSourceConfig())){//数据库未配置了来源
				mi.setSource(HtmlRegexpUtil.filterHtml(mi.getSource()));
			}else{//数据库配置了来源
				mi.setSource(mc.getSourceConfig());
			}
			//保存主表的数据
			information = getInformation(mi, mc, now, createId, creator, mc.getViewCountRange());
	        infoClassify = getInfoClasify(mi, mc, now, createId, creator, index, information, InfoClassify.ISVIEWWECHAT1);
	        //重复标题处理
	        validateTitle(mc, mi.getTitle(), mi.getGuid(), infoClassify);
	        //处理列表图
	        if(StringUtils.isNotBlank(mi.getImg())){
	        	getInfoClassifyList(mi, mc, infoClassify, information);
	        }
	        if(Information.CONTEXTTYPE1.equals(mc.getContextType()) || Information.CONTEXTTYPE5.equals(mc.getContextType())){//多图、视频
	        	informationContent = getInformationContent(mc, information, infoClassify, null);
	        	if(Information.CONTEXTTYPE1.equals(mc.getContextType())){//多图
	        		//如果是多图的话，要去解析content中的img标签，并且返回img标签的alt作为描述，如果alt为空就用description
		        	imgs = XmlUtils.getImgAttribute(mi.getContent(), "src,alt");
		        	if(imgs != null && imgs.size() > 0){
		        		multipleImgCount = 0;
		        		for(Map<String, String> map : imgs){
		        			getAppResouse(mc.getAppId(), information.getId(), informationContent.getId(), map.get("src"), map.containsKey("alt") && StringUtils.isNotBlank(map.get("alt")) ? map.get("alt") : mi.getDescription(), AppResouse.TYPE6);
		        			multipleImgCount ++;
		        		}
		        		information.setMultipleImgCount(multipleImgCount);
		        		informationDao.update(information);
		        	}
	        	}else if(Information.CONTEXTTYPE5.equals(mc.getContextType())){//视频
	        		getAppResouse(mc.getAppId(), information.getId(), informationContent.getId(), mi.getMedia(), mi.getDescription(), AppResouse.TYPE1);
        			//更新content_url
        			//如果是视频要在information表的content_url字段插入视频地址，否则客户端无法播放
        			information.setContentUrl(mi.getMedia());
        			informationDao.update(information);
	        	}
	        }
	        //保存关联表
	        getManuscriptInfoClassify(mi, mc, infoClassify, now);
	        index ++;
		} catch (Exception e) {
		    ManuscriptUtils.delHsetCache(abstractRedisClient, mc, mi.getGuid());
			//如果是某条记录没有保存成功也要保存一条日志到数据库
			getManuscriptInfoClassify(mi, mc, null, now);
			logger.error("抓稿失败，GUID为“"+mi.getGuid()+"”抓取失败：" , e);
//			throw new BusinessException(ErrorCodes.FAILURE, "抓稿失败，GUID为“"+mi.getGuid()+"”抓取失败：" + e);
		}
		return index;
	}
	
	/**
	 * Title:验证解析的数据是否 有效，比如说文本新闻没有内容，多图新闻没有图片，视频新闻没有视频地址等都是无效的信息，无效新闻不入新闻表。
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月21日
	 * @param mi
	 * @param mc
	 */
	public boolean validateData(ManuscriptInfo mi, ManuscriptColumns mc){
		boolean rs = true;
		if(Information.CONTEXTTYPE0.equals(mc.getContextType())){//纯文本
			rs = StringUtils.isBlank(mi.getContent());
		}else if(Information.CONTEXTTYPE1.equals(mc.getContextType())){//多图
			if(StringUtils.isBlank(mi.getContent())){
				rs = true;
			}else{
				List<Map<String, String>> imgs = XmlUtils.getImgAttribute(mi.getContent(), "src,alt");
				rs = imgs == null || imgs.size() < 1;
			}
		}else if(Information.CONTEXTTYPE5.equals(mc.getContextType())){//视频
			rs = StringUtils.isBlank(mi.getMedia());
		}
		return rs;
	}
	
	/**
	 * Title:处理重复title
	 * <p>Description:如果有重复的，就把数据库中以前相同栏目相同标题的那些记录status=99</p>
	 * @author DeweiLi on 2016年11月16日
	 * @param mc
	 * @param title
	 * @param guid
	 * @param infoClassifyId
	 */
	private void validateTitle(ManuscriptColumns mc, String title, String guid, InfoClassify infoClassify){
		String key = MD5BigFileUtil.md5(title);//同一个栏目下是否存在相同的标题
		//是否有编辑自己创建的新闻, 默认没有
		if(!ManuscriptUtils.addCache(abstractRedisClient, "title", mc, key)){//缓存中不存在相同的title
			Map<String, Object> map = Maps.newHashMap();
			map.put("EQ_title", title);//相同标题
			map.put("EQ_columnsId", mc.getColumnsId());//同一个栏目
			map.put("NOTEQ_id", infoClassify.getId());//除开当前这条记录
			List<InfoClassify> list = infoClassifyDao.query(map, null);
			if(list != null && list.size() > 0){
				for(InfoClassify info : list){
					//如果不是抓稿的数据，就不入库
					if(StringUtils.isBlank(info.getHlwOldGuid())){
						//这种情况说明这条重复的标题新闻是编辑自己新增的，我们抓稿的时候不能给编辑自己新增的新闻删了。
						infoClassify.setStatus(InfoClassify.STATUS99);//把我们抓稿的这条数据状态改成删除状态，编辑新增的那条数据不变
						infoClassifyDao.update(infoClassify);
					}else{
						//如果是抓稿的数据就删除以前的
						info.setStatus(InfoClassify.STATUS99);
						infoClassifyDao.update(info);
					}
				}
			}
		}else{
			//缓存中已经存在了相同的title，
			//缓存中存在了，有可能上一个任务的数据还没插进去，在根据guid判断哈，根据标题和guid去查询数据库是否存在
			Map<String, Object> map = Maps.newHashMap();
//			map.put("EQ_hlwOldGuid", guid);
//			map.put("NOTEQ_id", infoClassify.getId());//除开当前这条记录
			
			map.put("EQ_title", title);//相同标题
            map.put("EQ_columnsId", mc.getColumnsId());//同一个栏目
            map.put("NOTEQ_id", infoClassify.getId());//除开当前这条记录
			List<InfoClassify> list = infoClassifyDao.query(map, null);
			if(list != null && list.size() > 0){
				InfoClassify info = list.get(0);
				if(info != null){
					info.setStatus(InfoClassify.STATUS99);
					infoClassifyDao.update(info);
				}
			}
		}
	}
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月15日
	 * @param appId
	 * @param informationId
	 * @param informationContentId
	 * @param src
	 * @param description
	 * @param type
	 * @return
	 */
	private AppResouse getAppResouse(Long appId, Long informationId, Long informationContentId, String src, String description, Byte type){
//		if(StringUtils.isBlank(src)){
//			return null;
//		}
		AppResouse appResouse = new AppResouse();
		appResouse.setAppId(appId);
		appResouse.setDescription(description);
		appResouse.setFileUrl(src);
		appResouse.setInformationId(informationId);
		appResouse.setInformationContentId(informationContentId);
		appResouse.setType(type);
		appResouse.setSortNo(Integer.MAX_VALUE);
		appResouse.setStatus(AppResouse.STATUS3);
		appResouse = appResouseDao.save(appResouse);
		return appResouse;
	}
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月15日
	 * @param mc
	 * @param information
	 * @param infoClassify
	 * @return
	 */
	private InformationContent getInformationContent(ManuscriptColumns mc, Information information, InfoClassify infoClassify, Byte type){
	    if(type == null || !InformationContent.allTypes.containsKey(type)){
	        type = Information.CONTEXTTYPE1.equals(mc.getContextType()) ? InformationContent.TYPE6 : InformationContent.TYPE1;
	    }
		InformationContent informationContent = new InformationContent();
    	informationContent.setAppId(mc.getAppId());
    	informationContent.setInformationId(information.getId());
    	informationContent.setType(type);
    	informationContent.setTitle(infoClassify.getTitle());
    	informationContent.setSortNo(Integer.MAX_VALUE);
    	informationContent.setStatus(InformationContent.STATUS3);
    	informationContent = informationContentDao.save(informationContent);
    	return informationContent;
	}
	
	/**
	 * Title:保存关联表
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月15日
	 * @param mi
	 * @param mc
	 * @param infoClassify
	 * @param now
	 * @return
	 */
	private ManuscriptInfoClassify getManuscriptInfoClassify(ManuscriptInfo mi, ManuscriptColumns mc, InfoClassify infoClassify, Date now){
		ManuscriptInfoClassify manuscriptInfoClassify = new ManuscriptInfoClassify();
        manuscriptInfoClassify.setAppId(mc.getAppId());
        manuscriptInfoClassify.setCreateTime(now);
        manuscriptInfoClassify.setInfoClassifyId(infoClassify == null ? 0L : infoClassify.getId());
        manuscriptInfoClassify.setOldGuid(mi.getGuid());
        manuscriptInfoClassify.setManuscriptColumnsId(mc.getId());//后来新增的字段
    	manuscriptInfoClassify.setDescription(mi.getXMLCONTENT());
    	manuscriptInfoClassify.setIsInsert(infoClassify == null ? ManuscriptInfoClassify.ISINSERT0 : ManuscriptInfoClassify.ISINSERT1);
    	manuscriptInfoClassify.setEditor(mi.getEditor());
        manuscriptInfoClassify = manuscriptInfoClassifyDao.save(manuscriptInfoClassify);
        return manuscriptInfoClassify;
	}
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月15日
	 * @param mi
	 * @param mc
	 * @param infoClassify
	 * @param information
	 * @return
	 */
	private InfoClassifyList getInfoClassifyList(ManuscriptInfo mi, ManuscriptColumns mc, InfoClassify infoClassify, Information information){
		InfoClassifyList infoClassifyList = new InfoClassifyList();
    	infoClassifyList.setAppId(mc.getAppId());
    	infoClassifyList.setClassifyId(infoClassify.getId());
    	infoClassifyList.setColumnsId(mc.getColumnsId());
    	infoClassifyList.setImageUrl(mi.getImg());
    	infoClassifyList.setInformationId(information.getId());
    	if(StringUtils.isBlank(mi.getImg())){
    		infoClassifyList.setListViewType(InfoClassifyList.LISTVIEWTYPE0);
    	}else{
    		if(ManuscriptColumns.LISTVIEWTYPE2.equals(mc.getListViewType())){
    			infoClassifyList.setListViewType(ManuscriptColumns.LISTVIEWTYPE2);
    		}else{
    			infoClassifyList.setListViewType(ManuscriptColumns.LISTVIEWTYPE1);
    		}
    	}
    	infoClassifyList.setSortNo(Integer.MAX_VALUE);
    	infoClassifyList = infoClassifyListDao.save(infoClassifyList);
    	return infoClassifyList;
	}
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月15日
	 * @param mi
	 * @param mc
	 * @param now
	 * @param createId
	 * @param creator
	 * @param index
	 * @param information
	 * @return
	 */
	private InfoClassify getInfoClasify(ManuscriptInfo mi, ManuscriptColumns mc, Date now, Long createId, String creator, int index, Information information, Byte isViewWechat){
		InfoClassify infoClassify = new InfoClassify();
		infoClassify.setIsViewWechat(isViewWechat == null ? InfoClassify.ISVIEWWECHAT0 : isViewWechat);
		infoClassify.setInformationId(information.getId());
		String title = StringUtils.isNotBlank(mi.getTitle()) ? HtmlRegexpUtil.filterHtml(mi.getTitle()) : "";
		if(StringUtils.isNotBlank(title))
			title = title.replace("&nbsp;", "");
		title = StringUtils.isNotBlank(title) && title.length() > 50 ? title.substring(0, 50) : title;
		infoClassify.setTitle(title);
		infoClassify.setListTitle(title);
		infoClassify.setStatus(mc.getInfoStatus());
		infoClassify.setColumnsId(mc.getColumnsId());
		
		if(mi.getPubDate() != null && mi.getPubDate().length() ==19){
			infoClassify.setOnlineTime(DateUtil.parse(mi.getPubDate(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
		}else if (StringUtils.isBlank(mi.getPubDate())){
		    mi.setPubDate(DateUtil.format(now, DateUtil.YYYY_MM_DD_HH_MM_SS));
		    infoClassify.setOnlineTime(now);
		}else{
			//抓取的发布时间没有秒，这里我们脑补了一个秒
			String second = (index > 59 ? 0 : index) + "";
			infoClassify.setOnlineTime(DateUtil.parse(mi.getPubDate() + ":" + (second.length() == 1 ? "0" + second : second), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
		}
		infoClassify.setOnlineTimeDate(DateUtil.parse(mi.getPubDate(), DateUtil.FORMAT_YYYY_MM_DD));
		infoClassify.setHlwOldGuid(mi.getGuid());
		
		infoClassify.setListViewType(StringUtils.isNotBlank(mi.getImg()) ? InfoClassify.LISTVIEWTYPE1 : InfoClassify.LISTVIEWTYPE0);
		//modify add by yuwu 2017-01-12如果是重庆APP评论需要审核
		if(mc.getAppId() != null && mc.getAppId().longValue() == ManuscriptInfoClassifyServiceImpl.chongQingAppId.longValue()){
			infoClassify.setCommentValidateType(InfoClassify.COMMENTVALIDATETYPE1);
		}else{
			infoClassify.setCommentValidateType(InfoClassify.COMMENTVALIDATETYPE0);
		}
		infoClassify.setCommentType(InfoClassify.COMMENTTYPE0);
        infoClassify.setAppId(mc.getAppId());
        infoClassify.setCreateTime(now);
        infoClassify.setCreator(creator);
        infoClassify.setCreatorId(createId);
        infoClassify.setReplyCount(0);
        infoClassify.setSortNo(Integer.MAX_VALUE);
        infoClassify.setViewCount(0);
        infoClassify.setAddSpecialStatus(InfoClassify.ADDSPECIALSTATUS0);
        infoClassify.setSendStatus(InfoClassify.SENDSTATUS0);
        infoClassify.setIsRecommend(InfoClassify.ISRECOMMEND0);
    	infoClassify.setClassfieViewStatus(InfoClassify.CLASSFIEVIEWSTATUS0);
        infoClassify.setUpdateTime(now);
        infoClassify.setUpdatorId(createId);
        infoClassify.setUpdator(creator);
        infoClassify = infoClassifyDao.save(infoClassify);
        return infoClassify;
	}
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月15日
	 * @param mi
	 * @param mc
	 * @param now
	 * @param createId
	 * @param creator
	 * @param randStr
	 * @return
	 */
	private Information getInformation(ManuscriptInfo mi, ManuscriptColumns mc, Date now, Long createId, String creator, String randStr){
		Information information = new Information();
		information.setContent(mi.getContent());
		if(StringUtils.isNotBlank(mi.getContent())){//处理资讯内容的全文本，不带HTML标签的
			information.setContentText(HtmlRegexpUtil.filterHtml(mi.getContent()));
		}
		information.setValidateType(Information.VALIDATETYPE0);
		information.setTopTime(0);
		information.setPlViewType(Information.PLVIEWTYPE0);
		information.setInfoSource(mi.getSource());
		information.setAppId(mc.getAppId());
		information.setType(Information.TYPE0);//类型{0:普通新闻,2:主题新闻}
		information.setInfoSource(mi.getSource());//来源
        information.setViewCount(0);
        information.setReplyCount(0);
        information.setPraiseCount(0);
        information.setContextType(mc.getContextType());//新闻内容类型{0:纯文本,1:多图,2:原创,3:外链,4:音频,5:视频}
        information.setVideoStatus(Information.VIDEOSTATUS3);
		information.setCreateTime(now);
        information.setCreatorId(createId);
        information.setCreator(creator);
    	information.setUpdateTime(now);
        information.setUpdatorId(createId);
        information.setUpdator(creator);
//        information.setClassifyId(mc.getColumnsId());
        
        //下面读取配置，处理阅读量是一次添加还是逐条添加
        Byte addType = mc.getAddType() != null && Information.allAddTypes.containsKey(mc.getAddType()) ? mc.getAddType() : Information.ADDTYPE0;
        String configStr = mc.getCountAndTime();
        Integer topTime = 0;
        if(Information.ADDTYPE1.equals(addType) && StringUtils.isNotBlank(configStr)){
        	try {
    			topTime = Integer.parseInt(configStr);//达到峰值时间
    		} catch (NumberFormatException e) {
    		}
        }
        information.setInitCount(Long.parseLong(getRand(randStr) + ""));//初始阅读量
        information.setTopTime(topTime);//达到峰值时间
        information.setAddType(addType);
        information = informationDao.save(information);
        return information;
	}
	
	/**
	 * Title:生成一定范围的随机数,根据字符串参数范围（3000-6000）
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月21日
	 * @param randStr
	 * @return
	 */
	private int getRand(String randStr){
		if(StringUtils.isBlank(randStr) || randStr.indexOf("-") < 0){
			return 0;
		}
		int rs = 0;
		try {
			String[] strs = randStr.split("-");
			int a = Integer.parseInt(strs[0]),
				b = Integer.parseInt(strs[1]);
			if(a > b){//参数不合法
				return rs;
			}
			rs = (int)(a + Math.random() * (b-a+1)); 
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
		}
		return rs;
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public Integer importDataChengKou(Long createId, String creator, ManuscriptColumns mc, ChengKouListData data, Date now) {
		ChengKouNewsData newsData = data.getNewsData();
//		新闻内容类型{0:纯文本,1:多图,2:原创,3:外链,4:音频,5:视频}
		mc.setContextType(ChengKouListData.TYPE_PICS.equals(newsData.getType()) ? Information.CONTEXTTYPE2 : Information.CONTEXTTYPE0);
		ManuscriptInfo mi = new ManuscriptInfo();
		mi.setContent(newsData.getContents());
		mi.setGuid(newsData.getId().toString());
		mi.setXMLCONTENT(newsData.getJsonContent());
//		mi.setEditor();
		mi.setSource(StringUtils.isBlank(newsData.getSource()) ? mc.getSourceConfig() : newsData.getSource());
		mi.setTitle(newsData.getTitle());
		String pubTime = newsData.getPubTime();
		if(StringUtils.isBlank(pubTime)){
			pubTime = DateUtil.format(now, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
		}
		try {
		    pubTime = pubTime.replace("/", "-");
	        if(pubTime.length() <= 10){
	            pubTime = DateUtil.format(DateUtil.parse(pubTime), DateUtil.FORMAT_YYYY_MM_DD) + " " + DateUtil.format(new Date(), DateUtil.HH_MM_SS);
	        }
	        pubTime = DateUtil.format(DateUtil.parseDate(pubTime),DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
        } catch (Exception e) {
            pubTime = DateUtil.format(now, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
        }
		mi.setPubDate(pubTime);
		mi.setImg(data.getImg());
		mi.setDescription(data.getIntro());
		
		List<ManuscriptInfoClassify> manuscriptInfoClassifys;
		Information information;
		InfoClassify infoClassify;
		InformationContent informationContent;
		
		Map<String, Object> searchMap = Maps.newHashMap();
		searchMap.put("EQ_appId", mc.getAppId());
		searchMap.put("EQ_oldGuid", mi.getGuid());
		searchMap.put("NOTEQ_manuscriptColumnsId", mc.getId());//相同栏目
		searchMap.put("EQ_isInsert", ManuscriptInfoClassify.ISINSERT1);//要查询入库了的数据 哦
		try {
			//验证同一栏目是否存在相同的数据
			manuscriptInfoClassifys = manuscriptInfoClassifyDao.query(searchMap, null);
			//验证是否已经保存
			if(manuscriptInfoClassifys != null && manuscriptInfoClassifys.size() > 0){
				//保存关联表
		        getManuscriptInfoClassify(mi, mc, null, now);
				return 0;
			}
			//保存主表的数据
			information = getInformation(mi, mc, now, createId, creator, mc.getViewCountRange());
			infoClassify = getInfoClasify(mi, mc, now, createId, creator, 0, information, InfoClassify.ISVIEWWECHAT0);
			//为了更新classifyId
            information.setClassifyId(infoClassify.getId());
            informationDao.update(information);
			//处理列表图
			if(StringUtils.isNotBlank(mi.getImg())){
				getInfoClassifyList(mi, mc, infoClassify, information);
			}
			if(ChengKouListData.TYPE_PICS.equals(data.getType())){//原创新闻
				String picsStr = newsData.getPics();
				informationContent = getInformationContent(mc, information, infoClassify, InformationContent.TYPE0);
				getAppResouse(mc.getAppId(), information.getId(), informationContent.getId(), "", mi.getContent(), AppResouse.TYPE0);
				if(StringUtils.isNotBlank(picsStr)){
					String[] pics = picsStr.split(",");
					informationContent = getInformationContent(mc, information, infoClassify, InformationContent.TYPE6);
					//原创新闻要保存appresource记录
					for(String str: pics){
						getAppResouse(mc.getAppId(), information.getId(), informationContent.getId(), str, "", AppResouse.TYPE6);
					}
				}
			}else{//图片或文本
				
			}
			//保存关联表
			getManuscriptInfoClassify(mi, mc, infoClassify, now);
			//处理相关新闻
			List<ChengKouLikeNews> list = newsData.getLikeNews();
			if(list != null && list.size() > 0){
				try {
					List<InfoCorrelation> infoCorrelations = Lists.newArrayList();
					InfoCorrelation infoCorrelation;
					InfoClassify tmp;
					for(ChengKouLikeNews likeNews : list){
						infoCorrelation = new InfoCorrelation();
						tmp = this.queryInfoClassify(mc.getAppId(), likeNews.getId().toString());
						if(tmp != null){
							infoCorrelation.setRefInfoClassifyId(tmp.getId());
							infoCorrelation.setStatus(InfoCorrelation.STATUS3);
							infoCorrelation.setUpdatorId(createId);
							infoCorrelation.setCreatorId(createId);
						}else{
							infoCorrelation.setRefInfoClassifyId(-1L);
							infoCorrelation.setStatus((byte)99);
							infoCorrelation.setUpdatorId(-1L);
							infoCorrelation.setCreatorId(likeNews.getId());
						}
						infoCorrelation.setAppId(mc.getAppId());
						infoCorrelation.setInfoClassifyId(infoClassify.getId());
						infoCorrelation.setSortNo(Integer.MAX_VALUE);
						infoCorrelation.setUpdateTime(now);
						infoCorrelation.setCreateTime(now);
						infoCorrelation.setUpdator(creator);
						infoCorrelation.setCreator(creator);
						infoCorrelations.add(infoCorrelation);
					}
					infoCorrelationDao.saves(infoCorrelations);
				} catch (Exception e) {
					logger.error("处理相关新闻[id="+data.getId()+"]出错:", e);
				}
			}
			//处理以前没关联上的新闻
			updateInfoCorrelations(mc.getAppId(), newsData.getId(), infoClassify.getId(), createId);
			return 1;
		} catch (Exception e) {
		    ManuscriptUtils.delHsetCache(abstractRedisClient, mc, mi.getGuid());
		    //如果是某条记录没有保存成功也要保存一条日志到数据库
			getManuscriptInfoClassify(mi, mc, null, now);
			logger.error("城口抓稿保存失败："  , e);
		}
		return 0;
	}
	
	/**
	 * Title:根据第三方ID获得对应导入的新闻
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月28日
	 * @param appId
	 * @param hlwOldGuid
	 * @return
	 */
	private InfoClassify queryInfoClassify(Long appId, String hlwOldGuid){
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_hlwOldGuid", hlwOldGuid);
		List<InfoClassify> list = infoClassifyDao.query(map, null);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * Title:城口，处理之前抓取的关联新闻在数据库中还未保存的新闻
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月28日
	 * @param appId
	 * @param guid
	 * @param infoClassifyId
	 * @param creatorId
	 */
	private void updateInfoCorrelations(Long appId, Long guid, Long infoClassifyId, Long creatorId){
		try {
			Map<String, Object> map = Maps.newHashMap();
			map.put("EQ_appId", appId);
			map.put("EQ_creatorId", guid);
			map.put("EQ_updatorId", -1L);
			map.put("EQ_status", (byte)99);
			List<InfoCorrelation> list = infoCorrelationDao.query(map, null);
			if(list != null && list.size() > 0){
				for(InfoCorrelation ic : list){
					ic.setRefInfoClassifyId(infoClassifyId);
					ic.setUpdatorId(creatorId);
					ic.setCreatorId(creatorId);
					ic.setStatus(InfoCorrelation.STATUS3);
					infoCorrelationDao.update(ic);
				}
			}
		} catch (Exception e) {
			logger.error("城口抓取处理以前未关联上的关联新闻，出错：" , e);
		}
	}

    @Override
    public ManuscriptInfoClassify queryIsImport(String guid, Long manuscriptColumnsId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_oldGuid", guid);
        map.put("EQ_manuscriptColumnsId", manuscriptColumnsId);
        List<ManuscriptInfoClassify> list = query(map, null);
        if(list != null && list.size() > 0)
            return list.get(0);
        return null;
    }
    
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Integer importData(Long createId, String creator, ManuscriptColumns mc, ManuscriptInfo mi , Date now, String redisKey) {
        //记录插入数，也用于发布时间的秒
        Integer index = 0;
        Integer multipleImgCount;//多图时记录单个新闻下图片数量，要保存到information表中的multiple_img_count
        Information information;
        InfoClassify infoClassify;
        List<ManuscriptInfoClassify> manuscriptInfoClassifys;
        List<Map<String, String>> imgs;
        InformationContent informationContent;
        Map<String, Object> searchMap = Maps.newHashMap();
        searchMap.put("EQ_oldGuid", mi.getGuid());
        searchMap.put("EQ_appId", mc.getAppId());
        searchMap.put("EQ_isInsert", ManuscriptInfoClassify.ISINSERT1);//要查询入库了的数据 哦
        try {
            manuscriptInfoClassifys = manuscriptInfoClassifyDao.query(searchMap, null);
            //缓存验证了是否存在之后再查询一次数据库验证一次是否已经保存了
            if(manuscriptInfoClassifys != null && manuscriptInfoClassifys.size() > 0){
                return 0;
            }
            //验证数据有效性:比如说文本新闻没有内容，多图新闻没有图片，视频新闻没有视频地址等都是无效的信息，无效新闻不入新闻表。 
            if(validateData(mi, mc)){
                //保存关联表
                getManuscriptInfoClassify(mi, mc, null, now);
                return 0;
            }
            //处理来源(如果数据库配置了来源就取数据库的配置，如果未配置来源就去xml解析的source)
            if(StringUtils.isBlank(mc.getSourceConfig())){//数据库未配置了来源
                mi.setSource(mi.getSource() != null ? HtmlRegexpUtil.filterHtml(mi.getSource()) : "");
            }else{//数据库配置了来源
                mi.setSource(mc.getSourceConfig());
            }
            //保存主表的数据
            information = getInformation(mi, mc, now, createId, creator, mc.getViewCountRange());
            infoClassify = getInfoClasify(mi, mc, now, createId, creator, index, information, InfoClassify.ISVIEWWECHAT0);
            //为了更新classifyId
            information.setClassifyId(infoClassify.getId());
            informationDao.update(information);
            //重复标题处理
            validateTitle(mc, mi.getTitle(), mi.getGuid(), infoClassify);
            //处理列表图
            if(StringUtils.isNotBlank(mi.getImg())){
                getInfoClassifyList(mi, mc, infoClassify, information);
            }
            if(Information.CONTEXTTYPE1.equals(mc.getContextType()) || Information.CONTEXTTYPE5.equals(mc.getContextType())){//多图、视频
                informationContent = getInformationContent(mc, information, infoClassify, null);
                if(Information.CONTEXTTYPE1.equals(mc.getContextType())){//多图
                    //如果是多图的话，要去解析content中的img标签，并且返回img标签的alt作为描述，如果alt为空就用description
                    imgs = XmlUtils.getImgAttribute(mi.getContent(), "src,alt");
                    if(imgs != null && imgs.size() > 0){
                        multipleImgCount = 0;
                        for(Map<String, String> map : imgs){
                            getAppResouse(mc.getAppId(), information.getId(), informationContent.getId(), map.get("src"), map.containsKey("alt") && StringUtils.isNotBlank(map.get("alt")) ? map.get("alt") : mi.getDescription(), AppResouse.TYPE6);
                            multipleImgCount ++;
                        }
                        information.setMultipleImgCount(multipleImgCount);
                        informationDao.update(information);
                    }
                }else if(Information.CONTEXTTYPE5.equals(mc.getContextType())){//视频
                    getAppResouse(mc.getAppId(), information.getId(), informationContent.getId(), mi.getMedia(), mi.getDescription(), AppResouse.TYPE1);
                    //更新content_url
                    //如果是视频要在information表的content_url字段插入视频地址，否则客户端无法播放
                    information.setContentUrl(mi.getMedia());
                    informationDao.update(information);
                }
            }
            //保存关联表
            getManuscriptInfoClassify(mi, mc, infoClassify, now);
            index ++;
        } catch (Exception e) {
            ManuscriptUtils.delHsetCache(abstractRedisClient, mc, mi.getGuid());
            //如果是某条记录没有保存成功也要保存一条日志到数据库
            getManuscriptInfoClassify(mi, mc, null, now);
            logger.error("抓稿失败，GUID为“"+mi.getGuid()+"”抓取失败：" , e);
        }
        return index;
    }
}
