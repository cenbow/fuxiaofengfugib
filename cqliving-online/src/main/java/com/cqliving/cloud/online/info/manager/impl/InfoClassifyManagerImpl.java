package com.cqliving.cloud.online.info.manager.impl;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.InformationUtil;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.ClientControlType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.manager.UserFavoriteManager;
import com.cqliving.cloud.online.account.manager.UserPraiseManager;
import com.cqliving.cloud.online.act.dao.ActInfoDao;
import com.cqliving.cloud.online.act.dao.ActInfoListDao;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.building.dao.BuildingInfoDao;
import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.cloud.online.config.dao.RecommendInfoDao;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.config.manager.AppConfigManager;
import com.cqliving.cloud.online.info.dao.InfoClassifyCommentDao;
import com.cqliving.cloud.online.info.dao.InfoClassifyCommentHisDao;
import com.cqliving.cloud.online.info.dao.InfoClassifyDao;
import com.cqliving.cloud.online.info.dao.InfoClassifyListDao;
import com.cqliving.cloud.online.info.dao.InfoCorrelationDao;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoClassifyComment;
import com.cqliving.cloud.online.info.domain.InfoClassifyCommentHis;
import com.cqliving.cloud.online.info.domain.InfoClassifyList;
import com.cqliving.cloud.online.info.domain.InfoCorrelation;
import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.manager.InfoClassifyManager;
import com.cqliving.cloud.online.info.manager.InfoSliderConfigManager;
import com.cqliving.cloud.online.info.manager.InfoThemeManager;
import com.cqliving.cloud.online.info.manager.InformationManager;
import com.cqliving.cloud.online.interfacc.dto.BusinessDetailResult;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.InfoThemeData;
import com.cqliving.cloud.online.interfacc.dto.NewsData;
import com.cqliving.cloud.online.interfacc.dto.NewsResult;
import com.cqliving.cloud.online.interfacc.dto.NewsWxlData;
import com.cqliving.cloud.online.interfacc.dto.NewsWxlResult;
import com.cqliving.cloud.online.interfacc.dto.SpecialInfoDetailResult;
import com.cqliving.cloud.online.message.dao.MessagePushLogDao;
import com.cqliving.cloud.online.message.domain.MessagePushLog;
import com.cqliving.cloud.online.shop.dao.ShopInfoDao;
import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.shopping.dao.ShoppingGoodsDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.topic.dao.TopicInfoDao;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.cloud.online.tourism.dao.TourismInfoDao;
import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.wz.dao.WzQuestionDao;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.jpush.common.PushUtil;
import com.cqliving.jpush.service.PushService;
import com.cqliving.redis.redis.client.DefaultRedisClient;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

@Service("infoClassifyManager")
public class InfoClassifyManagerImpl extends EntityServiceImpl<InfoClassify, InfoClassifyDao> implements InfoClassifyManager {

	@Autowired
	private ActInfoDao actInfoDao;
	@Autowired
	private ActInfoListDao actInfoListDao;
	@Autowired
	private AppConfigManager appConfigManager;
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private BuildingInfoDao buildingInfoDao;
	@Autowired
	private InfoClassifyCommentDao infoClassifyCommentDao;
	@Autowired
	private InfoClassifyCommentHisDao infoClassifyCommentHisDao;
	@Autowired
	private InfoClassifyListDao infoClassifyListDao;
	@Autowired
	private InfoCorrelationDao infoCorrelationDao;
	@Autowired
	private InfoSliderConfigManager infoSliderConfigManager;
	@Autowired
	private InformationManager informationManager;
	@Autowired
	private InfoThemeManager infoThemeManager;
	@Autowired
	private MessagePushLogDao messagePushLogDao;
	@Autowired
	private PushService pushService;
	@Autowired
	private RecommendInfoDao recommendInfoDao;
	@Autowired
	private ShopInfoDao shopInfoDao;
	@Autowired
	private ShoppingGoodsDao shoppingGoodsDao;
	@Autowired
	private TopicInfoDao topicInfoDao;
	@Autowired
	private TourismInfoDao tourismInfoDao;
	@Autowired
	private UserFavoriteManager userFavoriteManager;
	@Autowired
	private UserPraiseManager userPraiseManager;
	@Autowired
	private WzQuestionDao wzQuestionDao;
	@Autowired
	DefaultRedisClient defaultRedisClient;
	@Value(value="${property_config}")
	private String propertyConfig;
	
	private static final int EXPIRE_TIME = 60*60*24*3;
	/** 小程序轮播新闻标题截字长度 */
	private static final int CUT_TITLE_LEN = 17;
	
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void publish(Long id, Date updateTime, Long updatorId, String updator) {
		getEntityDao().changeStatus(id, InfoClassify.STATUS3, updateTime, updatorId, updator);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void publishCorrelation(Long id, Date updateTime, Long updatorId, String updator) {
		infoCorrelationDao.changeStatus(id, InfoCorrelation.STATUS3, updateTime, updatorId, updator);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void publishBatch(List<Long> idList, Date updateTime, Long updatorId, String updator) {
		//getEntityDao().changeStatusBatch(idList, InfoClassify.STATUS1, InfoClassify.STATUS3, updateTime, updatorId, updator);
		getEntityDao().changeStatusBatch(idList,InfoClassify.STATUS3, updateTime, updatorId, updator);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void offline(Long id, Date updateTime, Long updatorId, String updator) {
		getEntityDao().changeStatus(id, InfoClassify.STATUS88, updateTime, updatorId, updator);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void offlineBatch(List<Long> idList, Date updateTime, Long updatorId, String updator) {
		//getEntityDao().changeStatusBatch(idList, InfoClassify.STATUS1, InfoClassify.STATUS88, updateTime, updatorId, updator);
		//getEntityDao().changeStatusBatch(idList, InfoClassify.STATUS3, InfoClassify.STATUS88, updateTime, updatorId, updator);
		getEntityDao().changeStatusBatch(idList,InfoClassify.STATUS88, updateTime, updatorId, updator);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void offlineCorrelation(Long id, Date updateTime, Long updatorId, String updator) {
		infoCorrelationDao.changeStatus(id, InfoCorrelation.STATUS88, updateTime, updatorId, updator);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void offlineCorrelationBatch(List<Long> idList, Date updateTime, Long updatorId, String updator) {
		infoCorrelationDao.changeStatusBatch(idList, InfoCorrelation.STATUS88, updateTime, updatorId, updator);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void delete(Long id, Date updateTime, Long updatorId, String updator) {
		getEntityDao().changeStatus(id, InfoClassify.STATUS99, updateTime, updatorId, updator);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void delBatch(List<Long> idList, Date updateTime, Long updatorId, String updator) {
		getEntityDao().changeStatusBatch(idList, InfoClassify.STATUS99, updateTime, updatorId, updator);
	}

	@Override
	public PageInfo<InfoClassifyDto> queryDtoForPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		return getEntityDao().queryDtoForPage(pageInfo, searchMap, sortMap);
	}

	@Override
	public PageInfo<InfoClassifyDto> queryDtoForCopyPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		return getEntityDao().queryDtoForCopyPage(pageInfo, searchMap, sortMap);
	}

	@Override
	public PageInfo<InfoClassifyDto> queryDtoForRecommendPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		return getEntityDao().queryDtoForRecommendPage(pageInfo, searchMap, sortMap);
	}

	@Override
	public PageInfo<InfoClassifyDto> queryDtoForSpecialSubPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		return getEntityDao().queryDtoForSpecialSubPage(pageInfo, searchMap, sortMap);
	}

	@Override
	public NewsResult getNewsByPage(Long appId, Long columnId, Boolean isCarousel, Long lastId, Integer lastSortNo, String lastOnlineTime) {
		//查询数据
		ScrollPage<InfoClassifyDto> page = new ScrollPage<InfoClassifyDto>();
		page.addScrollPageOrder(new ScrollPageOrder("sort_no", ScrollPage.ASC, lastSortNo == null ? 0 : lastSortNo));
		page.addScrollPageOrder(new ScrollPageOrder("online_time", ScrollPage.DESC, StringUtils.isBlank(lastOnlineTime) ? null : lastOnlineTime));
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		page.setPageSize(10);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_columnsId", columnId);
		conditions.put("EQ_status", InfoClassify.STATUS3);
		page = getEntityDao().queryDtoForScrollPage(page, conditions, false);
		List<InfoClassifyDto> dtos = page.getPageResults();
		
		//返回数据
		NewsResult result = new NewsResult();
		List<NewsData> newList = Lists.newArrayList();
		transfer(dtos, newList);
		result.setNews(newList);
		
		if (isCarousel) {	//获取轮播图
			page = new ScrollPage<InfoClassifyDto>();
			page.addScrollPageOrder(new ScrollPageOrder("sort_no", ScrollPage.ASC, 0));
			page.addScrollPageOrder(new ScrollPageOrder("online_time", ScrollPage.DESC, null));
			page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, null));
			Integer pageSize = infoSliderConfigManager.getCache(appId, columnId);	//获取配置
			page.setPageSize(pageSize);	
			page = getEntityDao().queryDtoForScrollPage(page, conditions, true);
			dtos = page.getPageResults();
			List<NewsData> carousels = Lists.newArrayList();
			transfer(dtos, carousels);
			result.setCarousels(carousels);
		}
		return result;
	}

	@Override
	public NewsWxlResult getWxlNewsByPage(Long appId, Long columnId, Boolean isCarousel, Long lastId, Integer lastSortNo, String lastOnlineTime, boolean onlyWechat) {
		//查询数据
		ScrollPage<InfoClassifyDto> page = new ScrollPage<InfoClassifyDto>();
		page.addScrollPageOrder(new ScrollPageOrder("sort_no", ScrollPage.ASC, lastSortNo == null ? 0 : lastSortNo));
		page.addScrollPageOrder(new ScrollPageOrder("online_time", ScrollPage.DESC, StringUtils.isBlank(lastOnlineTime) ? null : lastOnlineTime));
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		page.setPageSize(10);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_columnsId", columnId);
		conditions.put("EQ_status", InfoClassify.STATUS3);
		page = getEntityDao().queryWxlDtoForScrollPage(page, conditions, false, onlyWechat);
		List<InfoClassifyDto> dtos = page.getPageResults();
		
		//返回数据
		NewsWxlResult result = new NewsWxlResult();
		List<NewsWxlData> newList = Lists.newArrayList();
		transferWxl(dtos, newList);
		result.setNews(newList);
		
		if (isCarousel) {	//获取轮播图
			page = new ScrollPage<InfoClassifyDto>();
			page.addScrollPageOrder(new ScrollPageOrder("sort_no", ScrollPage.ASC, 0));
			page.addScrollPageOrder(new ScrollPageOrder("online_time", ScrollPage.DESC, null));
			page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, null));
			Integer pageSize = infoSliderConfigManager.getCache(appId, columnId);	//获取配置
			page.setPageSize(pageSize);	
			page = getEntityDao().queryWxlDtoForScrollPage(page, conditions, true, onlyWechat);
			dtos = page.getPageResults();
			List<NewsData> carousels = Lists.newArrayList();
			transfer(dtos, carousels);
			for (NewsData obj : carousels) {
				String title = obj.getTitle();
				int strLen = StringUtil.countLength(obj.getTitle());
				if (strLen > CUT_TITLE_LEN) {
					obj.setTitle(StringUtil.cutString(title, CUT_TITLE_LEN) + "...");
				}
			}
			result.setCarousels(carousels);
		}
		return result;
	}

	@Override
	public CommonListResult<NewsData> getSpecialSubList(Long appId, Long infoClassifyId, Long themeId, Long lastId, Integer lastSortNo, String lastOnlineTime) {
		//查询数据
		ScrollPage<InfoClassifyDto> page = new ScrollPage<InfoClassifyDto>();
		page.addScrollPageOrder(new ScrollPageOrder("corre_sort_no", ScrollPage.ASC, lastSortNo == null ? 0 : lastSortNo));
		page.addScrollPageOrder(new ScrollPageOrder("online_time", ScrollPage.DESC, StringUtils.isBlank(lastOnlineTime) ? null : lastOnlineTime));
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		page.setPageSize(10);
		Map<String, Object> conditions = Maps.newHashMap();
		//conditions.put("EQ_appId", appId);
		conditions.put("EQ_infoClassifyId", infoClassifyId);
		conditions.put("EQ_themeId", themeId);
		conditions.put("EQ_status", InfoClassify.STATUS3);
		page = getEntityDao().querySpecialSubDtoForScrollPage(page, conditions);
		List<InfoClassifyDto> dtos = page.getPageResults();
		
		//返回数据
		CommonListResult<NewsData> result = new CommonListResult<NewsData>();
		List<NewsData> newList = Lists.newArrayList();
		transfer(dtos, newList);
		result.setDataList(newList);
		return result;
	}

	@Override
	public CommonListResult<NewsData> searchNewsByPage(Long appId, String title, Long lastId, String lastOnlineTime) {
		//查询数据
		ScrollPage<InfoClassifyDto> page = new ScrollPage<InfoClassifyDto>();
		page.addScrollPageOrder(new ScrollPageOrder("online_time", ScrollPage.DESC, StringUtils.isBlank(lastOnlineTime) ? null : lastOnlineTime));
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		page.setPageSize(10);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("LIKE_title", title);
		conditions.put("EQ_status", InfoClassify.STATUS3);
		page = getEntityDao().queryDtoForScrollPage(page, conditions, null);
		List<InfoClassifyDto> dtos = page.getPageResults();
		
		//返回数据
		CommonListResult<NewsData> result = new CommonListResult<NewsData>();
		List<NewsData> newList = Lists.newArrayList();
		transfer(dtos, newList);
		result.setDataList(newList);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoClassifyManager#updateStatus(java.lang.Long, java.lang.Byte)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void updateStatus(Long classifyId, Byte status, Date updateTime, Long updatorId, String updator) {
		this.getEntityDao().changeStatus(classifyId, status, updateTime, updatorId, updator);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void clearSortNo(List<Long> ids) {
		getEntityDao().clearSortNo(ids);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void modifySortNo(Long id, Integer sortNo) {
		if (sortNo == null) {
			sortNo = Integer.MAX_VALUE;
		}
		getEntityDao().modifySortNo(id, sortNo);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void modifySortNos(Long[] ids, Integer[] sortNos, Long appId, Long columnsId, Byte listViewType) {
		if (ids.length != sortNos.length) {
			throw new BusinessException("保存失败：数据错误");
		}
		//判断是否已存在要保存的排序号
		Map<String, Object> map = Maps.newHashMap();
		map.put("NOTIN_id", Arrays.asList(ids));
		map.put("IN_sortNo", Arrays.asList(sortNos));
		map.put("EQ_appId", appId);
		map.put("EQ_columnsId", columnsId);
		map.put("EQ_addSpecialStatus", InfoClassify.ADDSPECIALSTATUS0);
		map.put("LT_status", InfoClassify.STATUS99);
		if (InfoClassify.LISTVIEWTYPE4.equals(listViewType)) {	//轮播
			map.put("EQ_listViewType", InfoClassify.LISTVIEWTYPE4);
		} else {
			map.put("NOTEQ_listViewType", InfoClassify.LISTVIEWTYPE4);
		}
		List<InfoClassify> list = query(map, null);
		if (CollectionUtils.isNotEmpty(list)) {	//排序号已存在
			StringBuilder sb = new StringBuilder();
			for (InfoClassify obj : list) {
				if (StringUtils.isBlank(sb)) {
					sb.append(obj.getSortNo());
				} else {
					sb.append(",").append(obj.getSortNo());
				}
			}
			throw new BusinessException("排序号" + sb.toString() + "已存在");
		}
		
		//保存排序号
		for (int i = 0; i < ids.length; i++) {
			getEntityDao().modifySortNo(ids[i], sortNos[i]);
		}
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void recommend(Long id, Long[] appIds) {
		Date now = DateUtil.now();
		//查询资讯
		InfoClassify obj = get(id);
		//保存推荐历史表
		InfoClassifyCommentHis icch = new InfoClassifyCommentHis();
		icch.setAppId(obj.getAppId());
		icch.setCommentTime(now);
		icch.setInfoClassifyId(id);
		icch.setTargetAppId(StringUtils.join(appIds, ','));
		infoClassifyCommentHisDao.save(icch);
		//保存推荐表
		InfoClassifyComment icc;
		List<InfoClassifyComment> list = Lists.newArrayList();
		for (Long appId: appIds){
			AppInfo appInfo = appInfoDao.get(appId);
			if (appInfo != null && !appInfo.getId().equals(obj.getAppId())) {
				icc = new InfoClassifyComment();
				icc.setCommentTime(now);
				icc.setHisId(icch.getId());
				icc.setSourceInfoClassifyId(id);
				icc.setSourceAppId(obj.getAppId());
				icc.setStatus(InfoClassifyComment.STATUS0);
				icc.setTargetAppId(appId);
				list.add(icc);
			}
		}
		infoClassifyCommentDao.save(list);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void push(Long id, String title, String summary, Long userId, String nickName) {
		//查询新闻
		InfoClassify infoClassify = getEntityDao().get(id);
		if (infoClassify == null || !InfoClassify.STATUS3.equals(infoClassify.getStatus())) {
			//资讯不存在或不是上线状态
			throw new BusinessException(
					ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getCode(), 
					ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getDesc());
		}
		if (InfoClassify.SENDSTATUS1.equals(infoClassify.getSendStatus())) {
			//资讯已推送
			throw new BusinessException(
					ErrorCodes.InfomationErrorEnum.ALREADY_PUSHED.getCode(), 
					ErrorCodes.InfomationErrorEnum.ALREADY_PUSHED.getDesc());
		}
		//查询 App 信息
		AppInfo appInfo = appInfoDao.get(infoClassify.getAppId());
		if (appInfo == null) {
			//App 不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc());
		}
		if (StringUtils.isBlank(appInfo.getJpushAppKey()) || StringUtils.isBlank(appInfo.getJpushAppSecret())) {
			//推送相关配置未配置
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.JPUSH_NO_CONFIG.getCode(), 
					ErrorCodes.CommonErrorEnum.JPUSH_NO_CONFIG.getDesc());
		}
		//查询新闻详情
		Information information = informationManager.get(infoClassify.getInformationId());
		if (information == null) {
			//资讯详情不存在
			throw new BusinessException(
					ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getCode(), 
					ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getDesc());
		}
		//查询图片资源
		StringBuilder images = new StringBuilder("");
		List<InfoClassifyList> infoClassifyList = infoClassifyListDao.getByInfoClassify(infoClassify.getId());
		for (InfoClassifyList obj : infoClassifyList) {
			if (StringUtils.isNotBlank(images)) {
				images.append(",");
			}
			images.append(obj.getImageUrl());
		}
		
		//修改推送状态
		Date now = DateUtil.now();
		infoClassify.setSendStatus(InfoClassify.SENDSTATUS1);
		infoClassify.setUpdateTime(now);
		infoClassify.setUpdator(nickName);
		infoClassify.setUpdatorId(userId);
		update(infoClassify);
		
		//调用极光接口推送
		title = StringUtils.isBlank(title) ? infoClassify.getTitle() : title;
//		summary = StringUtils.isBlank(summary) ? "点击查看详情" : summary;
		AppConfig appConfig = appConfigManager.findByAppId(infoClassify.getAppId());
		Byte detailViewType;
		if (Information.CONTEXTTYPE1.equals(information.getContextType())) {
			detailViewType = ClientControlType.DETAIL_VIEW_TYPE_1;
		} else if (Information.TYPE0.equals(information.getType())) {
			detailViewType = ClientControlType.DETAIL_VIEW_TYPE_2;
		} else if (Information.TYPE2.equals(information.getType())) {
			detailViewType = ClientControlType.DETAIL_VIEW_TYPE_3;
		} else {
			detailViewType = ClientControlType.DETAIL_VIEW_TYPE_2;
		}
		String url = InformationUtil.getRedirectUrl(information.getContextType(), information.getType(), information.getContentUrl(), infoClassify.getId());
		String shareUrl = InformationUtil.getShareUrl(information.getContextType(), information.getType(), information.getContentUrl(), infoClassify.getId(), appConfig.getDownLoadUrl());
		
		//获取推送配置
		String jpushAudienceType = PropertiesConfig.getString(PropertyKey.JPUSH_AUDIENCE_TYPE);
		String jpushAudienceRegistrationId = PropertiesConfig.getString(PropertyKey.JPUSH_AUDIENCE_REGISTRATIONID);
		String iosApnProductionStr = PropertiesConfig.getString(PropertyKey.JPUSH_IOS_APN_PRODUCTION);
		Boolean iosApnProduction = BooleanUtils.toBooleanObject(iosApnProductionStr);
		if (("all".equals(jpushAudienceType) && StringUtils.isNotBlank(jpushAudienceRegistrationId)) || "registrationid".equals(jpushAudienceType) && StringUtils.isBlank(jpushAudienceRegistrationId)) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.JPUSH_AUDIENCE_CONFIG_ERROR.getCode(), 
					ErrorCodes.CommonErrorEnum.JPUSH_AUDIENCE_CONFIG_ERROR.getDesc());
		}
		boolean useRegistrationId = "registrationid".equals(jpushAudienceType) && StringUtils.isNotBlank(jpushAudienceRegistrationId);
		
		//构建推送对象
		PushPayload pushPayload = PushUtil.create(
				infoClassify.getId(), 
				information.getId(), 
				BusinessType.SOURCE_TYPE_1, 
				detailViewType, 
				infoClassify.getCommentType(), 
				url, 
				shareUrl, 
				InformationUtil.getShareTitle(infoClassify.getAppId(), infoClassify.getTitle()),
				title, 
				summary, 
				images.toString(), 
				useRegistrationId ? jpushAudienceRegistrationId : null, 
				iosApnProduction);
		Response<PushResult> response = pushService.sendPush(appInfo.getJpushAppSecret(), appInfo.getJpushAppKey(), pushPayload);
		if (response.getCode() != 0) {
			//推送失败
			throw new BusinessException(response.getCode(), response.getMessage());
		}
		
		//保存推送任务结果
		MessagePushLog pushLog = new MessagePushLog();
		pushLog.setAppId(appInfo.getId());
		pushLog.setColumnsId(infoClassify.getColumnsId());
		pushLog.setCreateTime(now);
		pushLog.setCreateUserId(userId);
		pushLog.setCreator(nickName);
		pushLog.setMsgId(response.getData().msg_id);
		pushLog.setSourceId(infoClassify.getId());
		pushLog.setSourceType(MessagePushLog.SOURCE_TYPE1);
		pushLog.setSummary(summary);
		pushLog.setTitle(title);
		pushLog.setUpdateTime(now);
		pushLog.setUpdateUserId(userId);
		pushLog.setUpdator(nickName);
		messagePushLogDao.save(pushLog);
	}

	@Override
	public BusinessDetailResult getBusinessDetail(Long appId, String sessionId, String token, Long sourceId, Byte sourceType) {
		BusinessDetailResult result = new BusinessDetailResult();
		if (BusinessType.SOURCE_TYPE_1.equals(sourceType)) {	//新闻
			//查询相关信息
			InfoClassify infoClassify = get(sourceId);
			if (infoClassify == null || !InfoClassify.STATUS3.equals(infoClassify.getStatus())) {
				//资讯不存在或不是上线状态
				throw new BusinessException(
						ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getCode(), 
						ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getDesc());
			}
			Information information = informationManager.get(infoClassify.getInformationId());
			if (information == null) {
				//资讯详情不存在
				throw new BusinessException(
						ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getCode(), 
						ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getDesc());
			}
			
			boolean isPraised = userPraiseManager.isPraised(appId, sessionId, token, information.getId(), sourceType);
			result.setIsPraised(isPraised);
			result.setPraiseCount(information.getPraiseCount());
//			result.setReplyCount(information.getReplyCount());
			//计算浏览量和评论量
			InformationDto informationDto = new InformationDto();
			informationDto.setId(information.getId());
			informationDto.setInfoClassifyId(infoClassify.getId());
			informationDto.setViewCount(information.getViewCount());
			informationDto.setReplyCount(information.getReplyCount());
			informationDto.setInitCount(information.getInitCount());
			informationDto.setTopTime(information.getTopTime());
			informationDto.setOnlineTime(infoClassify.getOnlineTime());
			informationDto.setAddType(information.getAddType());
			informationManager.setViewAndReplyCount(informationDto);
			result.setViewCount(InformationUtil.handleViewCount(informationDto.getViewCount()));
			result.setReplyCount(informationDto.getReplyCount());
//			if (Information.ADDTYPE0.equals(information.getAddType())) {	//一次添加
//				result.setViewCount(information.getInitCount().intValue() + information.getViewCount());
//			} else if (Information.ADDTYPE1.equals(information.getAddType())) {	//逐步添加
//				Long secondDiff = (DateUtil.now().getTime() - infoClassify.getOnlineTime().getTime()) / 1000;
//				if (secondDiff.intValue() >= information.getTopTime()) {
//					result.setViewCount(information.getInitCount().intValue() + information.getViewCount());
//				} else {
//					Integer viewCount = information.getInitCount().intValue() * secondDiff.intValue() / information.getTopTime() + information.getViewCount();
//					result.setViewCount(viewCount);
//				}
//			} else {
//				result.setViewCount(0);
//			}
		} else if (BusinessType.SOURCE_TYPE_2.equals(sourceType)) {	//问政
			WzQuestion wzQuestion = wzQuestionDao.get(sourceId);
			if (wzQuestion == null || WzQuestion.STATUS99.equals(wzQuestion.getStatus())) {
				//问政不存在或已删除
				throw new BusinessException(ErrorCodes.FAILURE, "记录不存在");
			}
			boolean isPraised = userPraiseManager.isPraised(appId, sessionId, token, sourceId, sourceType);
			result.setIsPraised(isPraised);
			result.setPraiseCount(0);
			result.setReplyCount(wzQuestion.getReplyCount());
			result.setViewCount(InformationUtil.handleViewCount(wzQuestion.getViewCount()));
		} else if (BusinessType.SOURCE_TYPE_3.equals(sourceType)) {	//商情
			ShopInfo shopInfo = shopInfoDao.get(sourceId);
			if (shopInfo == null || !ShopInfo.STATUS3.equals(shopInfo.getStatus())) {
				//店铺不存在或不是上线状态
				throw new BusinessException(ErrorCodes.FAILURE, "记录不存在");
			}
			result.setIsPraised(false);
			result.setPraiseCount(0);
			result.setReplyCount(shopInfo.getReplyCount());
			result.setViewCount("0");
		} else if (BusinessType.SOURCE_TYPE_6.equals(sourceType)) {	//活动
			ActInfoList actInfoList = actInfoListDao.get(sourceId);
			if (actInfoList == null || !ActInfoList.STATUS3.equals(actInfoList.getStatus())) {
				//活动不存在或已过期
				throw new BusinessException(ErrorCodes.FAILURE, "记录不存在");
			}
			ActInfo actInfo = actInfoDao.get(actInfoList.getActInfoId());
			if (actInfo == null || !ActInfo.STATUS3.equals(actInfo.getStatus())) {
				//活动不存在或已过期
				throw new BusinessException(ErrorCodes.FAILURE, "记录不存在");
			}
			boolean isPraised = userPraiseManager.isPraised(appId, sessionId, token, sourceId, sourceType);
			result.setIsPraised(isPraised);
			result.setPraiseCount(actInfo.getPriseCount());
			result.setReplyCount(actInfo.getReplyCount());
			result.setViewCount("0");
		} else if (BusinessType.SOURCE_TYPE_7.equals(sourceType)) {	//话题
			TopicInfo topicInfo = topicInfoDao.get(sourceId);
			if (topicInfo == null || !TopicInfo.STATUS3.equals(topicInfo.getStatus())) {
				//话题不存在或已过期
				throw new BusinessException(ErrorCodes.FAILURE, "记录不存在");
			}
			boolean isPraised = userPraiseManager.isPraised(appId, sessionId, token, sourceId, sourceType);
			result.setIsPraised(isPraised);
			result.setPraiseCount(0);
			result.setReplyCount(topicInfo.getReplyCount());
			result.setViewCount("0"); 
		} else if (BusinessType.SOURCE_TYPE_10.equals(sourceType)) {	//旅游
			TourismInfo tourismInfo = tourismInfoDao.get(sourceId);
			if (tourismInfo == null || !ActInfoList.STATUS3.equals(tourismInfo.getStatus())) {
				//旅游不存在或已过期
				throw new BusinessException(ErrorCodes.FAILURE, "记录不存在");
			}
			boolean isPraised = userPraiseManager.isPraised(appId, sessionId, token, sourceId, sourceType);
			result.setIsPraised(isPraised);
			result.setPraiseCount(tourismInfo.getPraiseCount() == null ? 0 : tourismInfo.getPraiseCount());
			result.setReplyCount(tourismInfo.getReplyCount() == null ? 0 : tourismInfo.getReplyCount());
			result.setViewCount(InformationUtil.handleViewCount(tourismInfo.getViewCount()));
		} else if (BusinessType.SOURCE_TYPE_11.equals(sourceType)) {		//手机置业
			BuildingInfo buildingInfo = buildingInfoDao.get(sourceId);
			if (buildingInfo == null || !BuildingInfo.STATUS3.equals(buildingInfo.getStatus())) {
				//手机置业不存在
				throw new BusinessException(ErrorCodes.FAILURE, "记录不存在");
			}
			boolean isPraised = userPraiseManager.isPraised(appId, sessionId, token, sourceId, sourceType);
			result.setIsPraised(isPraised);
			result.setPraiseCount(buildingInfo.getPraiseCount() == null ? 0 : buildingInfo.getPraiseCount());
			result.setReplyCount(buildingInfo.getReplyCount() == null ? 0 : buildingInfo.getReplyCount());
			result.setViewCount("0");
		} else if (BusinessType.SOURCE_TYPE_13.equals(sourceType)) {		//商城
			ShoppingGoods shoppingGoods = shoppingGoodsDao.get(sourceId);
			if (shoppingGoods == null || !ShoppingGoods.STATUS3.equals(shoppingGoods.getStatus())) {
				//手机置业不存在
				throw new BusinessException(ErrorCodes.FAILURE, "记录不存在");
			}
			boolean isPraised = userPraiseManager.isPraised(appId, sessionId, token, sourceId, sourceType);
			result.setIsPraised(isPraised);
			result.setPraiseCount(shoppingGoods.getPraiseCount() == null ? 0 : shoppingGoods.getPraiseCount());
			result.setReplyCount(shoppingGoods.getReplyCount() == null ? 0 : shoppingGoods.getReplyCount());
			result.setViewCount(InformationUtil.handleViewCount(shoppingGoods.getViewCount()));
		}
		
		boolean isCollected = userFavoriteManager.isCollected(appId, sessionId, token, sourceId, sourceType);
		result.setIsCollected(isCollected);
		return result;
	}

	@Override
	public SpecialInfoDetailResult getSpecialDetail(Long appId, Long infoClassifyId) {
		
		//先取缓存，缓存取不到再取数据库
		String key = InformationUtil.getRedisCacheKey(propertyConfig, BusinessType.SOURCE_TYPE_1, infoClassifyId);
		SpecialInfoDetailResult dto = defaultRedisClient.get(key,SpecialInfoDetailResult.class);
		List<InfoThemeData> themes = this.findByInfoClassifyId(infoClassifyId);
		if(null != dto){
			dto.setThemes(themes);
			if(CollectionUtils.isNotEmpty(themes)){
				CommonListResult<NewsData> firstPageSub = this.getSpecialSubList(appId, infoClassifyId, themes.get(0).getId(), null, null, null);
				dto.setFirstPageData(firstPageSub);
			}
			return dto;
		}
		//查询资讯及资讯详情信息
		InfoClassify infoClassify = getEntityDao().get(infoClassifyId);
		if (infoClassify == null || !InfoClassify.STATUS3.equals(infoClassify.getStatus())) {
			//资讯不存在或不是上线状态
			throw new BusinessException(
					ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getCode(), 
					ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getDesc());
		}
		Information information = informationManager.get(infoClassify.getInformationId());
		if (information == null) {
			//资讯详情不存在
			throw new BusinessException(
					ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getCode(), 
					ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getDesc());
		}
		if (!Information.TYPE2.equals(information.getType())) {
			//不是专题资讯
			throw new BusinessException(
					ErrorCodes.InfomationErrorEnum.NOT_SPECIAL.getCode(), 
					ErrorCodes.InfomationErrorEnum.NOT_SPECIAL.getDesc());
		}
		
		//返回数据
		SpecialInfoDetailResult result = new SpecialInfoDetailResult();
		result.setInfoClassifyId(infoClassify.getId());
		result.setContentUrl(information.getContentUrl());
		result.setSynopsis(information.getSynopsis());
		result.setShareUrl(InformationUtil.getShareUrl(information.getContextType(), information.getType(),
				information.getContentUrl(),infoClassifyId,null));
		String listTitle = infoClassify.getListTitle();
		result.setShareTitle(InformationUtil.getShareTitle(appId,StringUtil.isEmpty(listTitle) ? infoClassify.getTitle() : listTitle));
		result.setId(infoClassifyId);
		result.setAppId(appId);
		result.setStatus(infoClassify.getStatus());
		result.setTitle(infoClassify.getTitle());
		
		List<InfoClassifyList> infoLists = infoClassifyListDao.getByInfoClassify(infoClassifyId);
		if(CollectionUtils.isNotEmpty(infoLists)){
			result.setListViewImg(infoLists.get(0).getImageUrl());
		}
		//放到缓存里边
		defaultRedisClient.set(key,result,EXPIRE_TIME);
		result.setThemes(themes);
		if(CollectionUtils.isNotEmpty(themes)){
			CommonListResult<NewsData> firstPageSub = this.getSpecialSubList(appId, infoClassifyId, themes.get(0).getId(), null, null, null);
			result.setFirstPageData(firstPageSub);
		}
		return result;
	}

	//根据专题ID获取专题分类数据
	private List<InfoThemeData> findByInfoClassifyId(Long infoClassifyId){
		if(null == infoClassifyId )return null;
		//查询专题分类
		List<InfoTheme> infoThemes = infoThemeManager.findByInfoClassifyId(infoClassifyId);
		if (CollectionUtils.isNotEmpty(infoThemes)) {
			List<InfoThemeData> themes = Lists.newArrayList();
			for (InfoTheme infoTheme : infoThemes) {
				InfoThemeData themeData = new InfoThemeData();
				themeData.setColor(infoTheme.getColor());
				themeData.setId(infoTheme.getId());
				themeData.setName(infoTheme.getName());
				themes.add(themeData);
			}
			return themes;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoClassifyManager#queryInfoClassifyCorrelationPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<InfoClassifyDto> queryInfoClassifyCorrelationPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> orderMap) {
		
		return this.getEntityDao().queryInfoClassifyCorrelationPage(pageInfo, searchMap, orderMap);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoClassifyManager#queryHadCorrInfoClassifyPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<InfoClassifyDto> queryHadCorrInfoClassifyPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> orderMap) {
		
		return this.getEntityDao().queryHadCorrInfoClassifyPage(pageInfo, searchMap, orderMap);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoClassifyManager#infoClassifyRecommedEdit(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public InfoClassify infoClassifyRecommedEdit(Long commentId) {
		
		InfoClassifyComment infoClassifyComment = infoClassifyCommentDao.get(commentId);
		
		if(infoClassifyComment.getStatus().byteValue() == InfoClassifyComment.STATUS2.byteValue() &&
				null != infoClassifyComment.getNewInfoClassifyId() && 0 != infoClassifyComment.getNewInfoClassifyId()){
			return this.get(infoClassifyComment.getNewInfoClassifyId());
		}
		Long infoClassifyId = infoClassifyComment.getSourceInfoClassifyId();
		InfoClassify infoClassify = this.get(infoClassifyId);
		InfoClassify newInfoClassify = new InfoClassify();
		BeanUtils.copyProperties(infoClassify, newInfoClassify);
		newInfoClassify.setAppId(infoClassifyComment.getTargetAppId());
		newInfoClassify.setId(null);
		newInfoClassify = this.save(newInfoClassify);
		infoClassifyComment.setNewInfoClassifyId(newInfoClassify.getId());
		infoClassifyComment.setStatus(InfoClassifyComment.STATUS2);
		//列表图片
		List<InfoClassifyList> infoClassifyList = infoClassifyListDao.getByInfoClassify(infoClassifyId);
		if(!CollectionUtils.isEmpty(infoClassifyList)){
			InfoClassifyList listImg = infoClassifyList.get(0);
			InfoClassifyList newlistImg = new InfoClassifyList();
			BeanUtils.copyProperties(listImg, newlistImg);
			newlistImg.setId(null);
			newlistImg.setAppId(infoClassifyComment.getTargetAppId());
			newlistImg.setClassifyId(newInfoClassify.getId());
			infoClassifyListDao.saveAndFlush(newlistImg);
		}
		return newInfoClassify;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoClassifyManager#querySpecialSubDtoForScrollPage(com.cqliving.framework.common.dao.support.ScrollPage, java.util.Map)
	 */
	@Override
	public ScrollPage<InfoClassifyDto> querySpecialSubDtoForScrollPage(ScrollPage<InfoClassifyDto> page,
			Map<String, Object> conditions) {
		
		return this.getEntityDao().querySpecialSubDtoForScrollPage(page, conditions);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public boolean increaseViewCount(Long appId, Long infoClassifyId) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		InfoClassify infoClassify = this.get(infoClassifyId);
		if(null == infoClassify){
			return false;
		}
		int count = informationManager.addViewCount(infoClassifyId,infoClassify.getInformationId());//TODO
		return count > 0;
	}

	@Override
	public CommonListResult<NewsData> getRecommended(Long appId) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		
		//查询、返回数据
		List<InfoClassifyDto> dtos = getEntityDao().getRecommended(appId);
		List<NewsData> dataList = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(dtos)) {
			transfer(dtos, dataList);
		}
		CommonListResult<NewsData> result = new CommonListResult<NewsData>();
		result.setDataList(dataList);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void recommendToHome(Long id, Long userId, String userName){
		InfoClassify infoClassify = get(id);
		if(infoClassify == null){
			throw new BusinessException(ErrorCodes.FAILURE, "新闻不存在");
		}
		infoClassify.setIsRecommend(InfoClassify.ISRECOMMEND1);
		this.update(infoClassify);
		
		Date date = new Date();
		RecommendInfo recommendInfo = new RecommendInfo();
		recommendInfo.setAppId(infoClassify.getAppId());
		recommendInfo.setSourceId(infoClassify.getId());
		recommendInfo.setSourceType(RecommendInfo.SOURCETYPE1);
		recommendInfo.setName(infoClassify.getTitle());
//		recommendInfo.setImageUrl();
		recommendInfo.setSortNo(0);
		recommendInfo.setStatus(RecommendInfo.STATUS1);
		recommendInfo.setCreator(userName);
		recommendInfo.setCreatorId(userId);
		recommendInfo.setCreateTime(date);
		recommendInfo.setUpdateTime(date);
		recommendInfo.setUpdator(userName);
		recommendInfo.setUpdatorId(userId);
		recommendInfoDao.save(recommendInfo);
	}

	/**
	 * <p>Description: 将 Dto 转换为接口返回所需的结构</p>
	 * @author Tangtao on 2016年6月1日
	 * @param dtos
	 * @param newList
	 */
	private void transfer(List<InfoClassifyDto> dtos, List<NewsData> newList) {
		NewsData news;
		if (CollectionUtils.isNotEmpty(dtos)) {
			AppConfig appConfig = appConfigManager.findByAppId(dtos.get(0).getAppId());
			for (InfoClassifyDto dto : dtos) {
				news = new NewsData();
				//计算浏览量和评论量
				InformationDto informationDto = new InformationDto();
				informationDto.setId(dto.getInformationId());
				informationDto.setInfoClassifyId(dto.getId());
				informationDto.setViewCount(dto.getDetailViewCount());
				informationDto.setReplyCount(dto.getTotalReplyCount());
				informationDto.setInitCount(dto.getInitCount());
				informationDto.setTopTime(dto.getTopTime());
				informationDto.setOnlineTime(dto.getOnlineTime());
				informationDto.setAddType(dto.getAddType());
				informationManager.setViewAndReplyCount(informationDto);
				news.setViewCount(InformationUtil.handleViewCount(informationDto.getViewCount()));
				news.setReplyCount(informationDto.getReplyCount());
//				if (Information.ADDTYPE0.equals(dto.getAddType())) {	//一次添加
//					news.setViewCount(dto.getInitCount().intValue() + dto.getDetailViewCount());
//				} else if (Information.ADDTYPE1.equals(dto.getAddType())) {	//逐步添加
//					Long secondDiff = (DateUtil.now().getTime() - dto.getOnlineTime().getTime()) / 1000;
//					if (secondDiff.intValue() >= dto.getTopTime()) {
//						news.setViewCount(dto.getInitCount().intValue() + dto.getDetailViewCount());
//					} else {
//						Integer viewCount = dto.getInitCount().intValue() * secondDiff.intValue() / dto.getTopTime() + dto.getDetailViewCount();
//						news.setViewCount(viewCount);
//					}
//				} else {
//					news.setViewCount(0);
//				}
				//详情展示类型
				Byte detailViewType;
				if (Information.CONTEXTTYPE1.equals(dto.getContextType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_1;
				} else if (Information.TYPE0.equals(dto.getType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_2;
				} else if (Information.TYPE2.equals(dto.getType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_3;
				} else {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_2;
				}
				news.setCommentType(dto.getCommentType());
				news.setContentUrl(dto.getContentUrl());
				news.setContextType(dto.getContextType());
				news.setDetailViewType(detailViewType);
				news.setId(dto.getId());
				news.setImages(dto.getImgUrls());
				news.setInformationId(dto.getInformationId());
				news.setInfoSource(dto.getInfoSource());
				news.setListViewType(dto.getListViewType());
				news.setMultipleImgCount(dto.getMultipleImgCount());
				news.setOnlineDate(DateUtil.convertTimeToFormatHore1(dto.getOnlineTime().getTime(), "MM-dd HH:mm"));
				news.setOnlineTime(DateUtil.toString(dto.getOnlineTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
//				news.setReplyCount(dto.getTotalReplyCount());
				news.setSortNo(dto.getSortNo());
				news.setSourceType(BusinessType.SOURCE_TYPE_1);
				news.setSynopsis(dto.getSynopsis());
				news.setInfoLabel(dto.getInfoLabel());	
				news.setTitle(StringUtils.isNotBlank(dto.getListTitle()) ? dto.getListTitle() : dto.getTitle());
				news.setDetailTitle(dto.getTitle());	
				news.setType(dto.getType());
				news.setUrl(InformationUtil.getRedirectUrl(dto.getContextType(), dto.getType(), dto.getContentUrl(), dto.getId()));
				news.setShareUrl(InformationUtil.getShareUrl(dto.getContextType(), dto.getType(), dto.getContentUrl(), dto.getId(), appConfig.getDownLoadUrl()));
				news.setShareTitle(InformationUtil.getShareTitle(dto.getAppId(), dto.getTitle()));
				newList.add(news);
			}
		}
	}
	
	/**
	 * <p>Description: 微信小程序新闻列表使用</p>
	 * @author Tangtao on 2017年1月16日
	 * @param dtos
	 * @param newList
	 */
	private void transferWxl(List<InfoClassifyDto> dtos, List<NewsWxlData> newList) {
		NewsWxlData news;
		if (CollectionUtils.isNotEmpty(dtos)) {
			AppConfig appConfig = appConfigManager.findByAppId(dtos.get(0).getAppId());
			for (InfoClassifyDto dto : dtos) {
				news = new NewsWxlData();
				//计算浏览量和评论量
				InformationDto informationDto = new InformationDto();
				informationDto.setId(dto.getInformationId());
				informationDto.setInfoClassifyId(dto.getId());
				informationDto.setViewCount(dto.getDetailViewCount());
				informationDto.setReplyCount(dto.getTotalReplyCount());
				informationDto.setInitCount(dto.getInitCount());
				informationDto.setTopTime(dto.getTopTime());
				informationDto.setOnlineTime(dto.getOnlineTime());
				informationDto.setAddType(dto.getAddType());
				informationManager.setViewAndReplyCount(informationDto);
				news.setViewCount(InformationUtil.handleViewCount(informationDto.getViewCount()));
				news.setReplyCount(informationDto.getReplyCount());
				//详情展示类型
				Byte detailViewType;
				if (Information.CONTEXTTYPE1.equals(dto.getContextType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_1;
				} else if (Information.TYPE0.equals(dto.getType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_2;
				} else if (Information.TYPE2.equals(dto.getType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_3;
				} else {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_2;
				}
				news.setCommentType(dto.getCommentType());
				news.setContentUrl(dto.getContentUrl());
				news.setContextType(dto.getContextType());
				news.setDetailViewType(detailViewType);
				news.setId(dto.getId());
				//组装图片列表
				String[] images = StringUtils.split(dto.getImgUrls(), ',');
				news.setImages(ArrayUtils.isNotEmpty(images) ? images : new String[]{});
				news.setInformationId(dto.getInformationId());
				news.setInfoSource(dto.getInfoSource());
				news.setListViewType(dto.getListViewType());
				news.setMultipleImgCount(dto.getMultipleImgCount());
				news.setOnlineDate(DateUtil.convertTimeToFormatHore1(dto.getOnlineTime().getTime(), "MM-dd HH:mm"));
				news.setOnlineTime(DateUtil.toString(dto.getOnlineTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
				news.setSortNo(dto.getSortNo());
				news.setSourceType(BusinessType.SOURCE_TYPE_1);
				news.setSynopsis(dto.getSynopsis());
				//只显示第一个标签
				String[] labels = StringUtils.split(dto.getInfoLabel(), ',');
				news.setInfoLabel(ArrayUtils.isNotEmpty(labels) ? labels[0] : null);	
				news.setTitle(StringUtils.isNotBlank(dto.getListTitle()) ? dto.getListTitle() : dto.getTitle());
				news.setDetailTitle(dto.getTitle());	
				news.setType(dto.getType());
				news.setUrl(InformationUtil.getRedirectUrl(dto.getContextType(), dto.getType(), dto.getContentUrl(), dto.getId()));
				news.setShareUrl(InformationUtil.getShareUrl(dto.getContextType(), dto.getType(), dto.getContentUrl(), dto.getId(), appConfig.getDownLoadUrl()));
				news.setShareTitle(InformationUtil.getShareTitle(dto.getAppId(), dto.getTitle()));
				newList.add(news);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void moveInfoClassify(Long[] infoClassifyIds, Long appColumnsId,String updator,Long updateId,Date updateTime) {
		if(StringUtil.isEmpty(infoClassifyIds) || null == appColumnsId){
			throw new BusinessException(ErrorCodes.FAILURE,"新闻或者栏目未选择，不能移动");
		}
		List<Long> classifyIds = Arrays.asList(infoClassifyIds);
		this.getEntityDao().updateAppColumn(classifyIds, appColumnsId, updator, updateId, updateTime);
	}

}