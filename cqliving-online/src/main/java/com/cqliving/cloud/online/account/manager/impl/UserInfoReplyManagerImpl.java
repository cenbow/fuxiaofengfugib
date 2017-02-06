package com.cqliving.cloud.online.account.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.dao.UserInfoReplyDao;
import com.cqliving.cloud.online.account.dao.UserPraiseDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserInfoReplyManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.dao.ActInfoDao;
import com.cqliving.cloud.online.act.dao.ActInfoListDao;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.building.dao.BuildingInfoDao;
import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.cloud.online.config.dao.ConfigSensitiveWordsDao;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.domain.ConfigSensitiveWords;
import com.cqliving.cloud.online.config.manager.CommentAppConfigManager;
import com.cqliving.cloud.online.info.dao.InfoClassifyDao;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.manager.InformationManager;
import com.cqliving.cloud.online.interfacc.dto.CommentsData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.MyCommentsData;
import com.cqliving.cloud.online.joke.dao.JokeInfoDao;
import com.cqliving.cloud.online.joke.domain.JokeInfo;
import com.cqliving.cloud.online.shoot.dao.ShootInfoDao;
import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.cloud.online.shop.dao.ShopInfoDao;
import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.shopping.dao.ShoppingGoodsDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.topic.dao.TopicInfoDao;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.cloud.online.tourism.dao.TourismInfoDao;
import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.wz.dao.WzQuestionDao;
import com.cqliving.cloud.online.wz.domain.WzAppAuthority;
import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.manager.WzAppAuthorityManager;
import com.cqliving.cloud.online.wz.manager.WzAuthorityManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("userInfoReplyManager")
public class UserInfoReplyManagerImpl extends EntityServiceImpl<UserInfoReply, UserInfoReplyDao> implements UserInfoReplyManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoReplyManagerImpl.class);
    
	@Autowired
	private ActInfoDao actInfoDao;
	@Autowired
	private ActInfoListDao actInfoListDao;
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private BuildingInfoDao buildingInfoDao;
	@Autowired
	private CommentAppConfigManager commentAppConfigManager;
	@Autowired
	private ConfigSensitiveWordsDao configSensitiveWordsDao;
	@Autowired
	private InfoClassifyDao infoClassifyDao;
	@Autowired
	private InformationManager informationDao;
	@Autowired
	private JokeInfoDao jokeInfoDao;
	@Autowired
	private ShootInfoDao shootInfoDao;
	@Autowired
	private ShopInfoDao shopInfoDao;
	@Autowired
	private ShoppingGoodsDao shoppingGoodsDao;
	@Autowired
	private TopicInfoDao topicInfoDao;
	@Autowired
	private TourismInfoDao tourismInfoDao;
	@Autowired
	private UserAccountManager userAccountManager;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserPraiseDao userPraiseDao;
    @Autowired
    private UserSessionManager userSessionManager;
    @Autowired
    private WzAppAuthorityManager wzAppAuthorityManager;
    @Autowired
    private WzAuthorityManager wzAuthorityManager;
    @Autowired
    private WzQuestionDao wzQuestionDao;
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
	    UserInfoReply reply;
	    for (Long id : idList) {
	        reply = this.get(id);
	        //减少评论数
	        if(null!=reply && UserInfoReply.STATUS3.equals(reply.getStatus())){
		        if(UserInfoReply.SOURCETYPE1.equals(reply.getSourceType())){
		            InfoClassify info = infoClassifyDao.get(reply.getInfoClassifyId());
		            if(null!=info){
		                informationDao.minusReplyCount(reply.getInfoClassifyId(),info.getInformationId());
		            }
                }else if(UserInfoReply.SOURCETYPE2.equals(reply.getSourceType())){
                    wzQuestionDao.decreaseReplyCount(reply.getSourceId());
                }else if(UserInfoReply.SOURCETYPE3.equals(reply.getSourceType())){
                    shopInfoDao.decreaseReplyCount(reply.getSourceId());
                }else if(UserInfoReply.SOURCETYPE4.equals(reply.getSourceType())){
                    shootInfoDao.decreaseReplyCount(reply.getSourceId());
                }else if(UserInfoReply.SOURCETYPE5.equals(reply.getSourceType())){
                    jokeInfoDao.decreaseReplyCount(reply.getSourceId());
                }else if(UserInfoReply.SOURCETYPE6.equals(reply.getSourceType())){
                    ActInfoList actInfoList = actInfoListDao.get(reply.getSourceId());
                    if(null != actInfoList&& null != actInfoList.getActInfoId()){
                        actInfoDao.decreaseReplyCount(actInfoList.getActInfoId());
                    }
                    //actInfoDao.decreaseReplyCount(reply.getSourceId());
                }else if(UserInfoReply.SOURCETYPE7.equals(reply.getSourceType())){
                    topicInfoDao.decreaseReplyCount(reply.getSourceId());
                }else if(UserInfoReply.SOURCETYPE10.equals(reply.getSourceId())){
                    tourismInfoDao.decreaseReplyCount(reply.getSourceId());
                }
	        }
        }
		return this.getEntityDao().updateStatus(UserInfoReply.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}
	/**
     * 审核
     * @param status 状态
     * @param auditingContent 审核描述
     * @param ids ID
     * @return
     */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int auditing(Byte status,String auditingContent,Long auditingId,String auditingtor,Long[] sourceIds,Byte sourceType,Long... ids){
	    List<Long> idList = Arrays.asList(ids);
	    //审核通过需要添加评论数
	    if(UserInfoReply.STATUS3.equals(status)){
	        if(null!=sourceIds&&sourceIds.length>0){
	            if(UserInfoReply.SOURCETYPE1.equals(sourceType)){
	                for (Long id : sourceIds) {
	                    InfoClassify info = infoClassifyDao.get(id);
	                    if(null!=info){
	                        informationDao.addReplyCount(id,info.getInformationId());
	                    }
	                }
	            }else if(UserInfoReply.SOURCETYPE2.equals(sourceType)){
	                for (Long id : sourceIds) {
	                    wzQuestionDao.plusReplyCount(id);
	                }
	            }else if(UserInfoReply.SOURCETYPE3.equals(sourceType)){
	                for (Long id : sourceIds) {
	                    shopInfoDao.increaseReplyCount(id);
	                }
	            }else if(UserInfoReply.SOURCETYPE4.equals(sourceType)){
	                for (Long id : sourceIds) {
                        shootInfoDao.increaseReplyCount(id);
                    }
    	        }else if(UserInfoReply.SOURCETYPE5.equals(sourceType)){
    	            for (Long id : sourceIds) {
    	                jokeInfoDao.increaseReplyCount(id);
    	            }
    	        }else if(UserInfoReply.SOURCETYPE6.equals(sourceType)){
    	            ActInfoList actInfoList ;
                    for (Long id : sourceIds) {
                        actInfoList = actInfoListDao.get(id);
                        if(null != actInfoList&& null != actInfoList.getActInfoId()){
                            actInfoDao.increaseReplyCount(actInfoList.getActInfoId());
                        }
                    }
                }else if(UserInfoReply.SOURCETYPE7.equals(sourceType)){
                    for (Long id : sourceIds) {
                        topicInfoDao.increaseReplyCount(id);
                    }
                }else if(UserInfoReply.SOURCETYPE10.equals(sourceType)){
                    //增加评论数
                    for (Long id : sourceIds) {
                        tourismInfoDao.increaseReplyCount(id);
                    }
                }
	        }
	    }
	    return this.getEntityDao().auditing(status,auditingContent,auditingId,auditingtor,new Date(),idList);
	}

	@Override
	public CommonListResult<CommentsData> getPageBySourceId(Long appId, String sessionId, String token, Long sourceId, Byte sourceType, Long lastId) {
		//查询当前用户 id
		UserSession userSession = userSessionManager.get(sessionId, token);
		Long userId = userSession == null ? -1L : userSession.getUserId();
		
		//查询评论数据
		ScrollPage<UserInfoReplyDto> page = new ScrollPage<UserInfoReplyDto>();
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		page.setPageSize(10);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_sourceId", sourceId);
		conditions.put("EQ_status", UserInfoReply.STATUS3);
		conditions.put("EQ_sourceType", sourceType);
		page = getEntityDao().queryDtoForScrollPage(page, conditions, userId, sourceType);
		List<UserInfoReplyDto> dtos = page.getPageResults();
		//查询评论点赞记录
		List<UserPraise> userPraises = Lists.newArrayList();
		if (userId != -1) {
			conditions.clear();
			conditions.put("EQ_appId", appId);
			conditions.put("EQ_type", UserPraise.TYPE0);
			conditions.put("EQ_operateType", UserPraise.OPERATETYPE1);
			conditions.put("EQ_sourceType", sourceType);
			conditions.put("EQ_sourceUserId", userId);
			conditions.put("EQ_status", InfoClassify.STATUS3);
			userPraises = userPraiseDao.query(conditions, null);
		}
		
		//获取敏感词
		List<ConfigSensitiveWords> sensitiveWordsList = configSensitiveWordsDao.getByAppId(appId);
		List<String> sensitiveList = Lists.newArrayList();
		for (ConfigSensitiveWords obj : sensitiveWordsList) {
			if (ConfigSensitiveWords.STATUS3.equals(obj.getStatus())) {
				sensitiveList.add(obj.getName());
			}
		}
		//返回数据
		CommonListResult<CommentsData> result = new CommonListResult<CommentsData>();
		List<CommentsData> comments = Lists.newArrayList();
		CommentsData data;
		if (CollectionUtils.isNotEmpty(dtos)) {
			boolean isAnonymous = false;	//是否匿名评论
			for (UserInfoReplyDto dto : dtos) {
				isAnonymous = UserInfoReply.TYPE1.equals(dto.getType());
				data = new CommentsData();
				data.setCommentTime(DateUtil.convertTimeToFormatHore1(dto.getCreateTime().getTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
				data.setContent(StringUtil.replaceSensitiveWord(dto.getContent(), sensitiveList, "**"));
				data.setHeaderImg(isAnonymous ? "" : dto.getImgUrl());
				data.setIsPraised(0);
				for (UserPraise obj : userPraises) {	//判断是否点赞此评论
					if (obj.getSourceId().equals(dto.getId())) {
						data.setIsPraised(1);
						break;
					}
				}
				data.setPassiveReplyContent(StringUtil.replaceSensitiveWord(dto.getSourceTitle(), sensitiveList, "**"));
				data.setPassiveReplyName(dto.getPassiveReplyName());
				data.setPlace(dto.getPlace());
				data.setPraiseCount(dto.getPraise());
				data.setReplyCount(dto.getReplyCount());
				data.setReplyId(dto.getId());
				data.setUserId(dto.getReplyUserId());
				data.setUserName(isAnonymous ? dto.getAnonymousName() : dto.getNickname());
				comments.add(data);
			}
		}
		result.setDataList(comments);
		return result;
	}

	@Override
	public CommonListResult<MyCommentsData> getPageByUser(Long appId, String sessionId, String token, Integer type, Long lastId, Byte sourceType) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//获取用户信息
		UserSession userSession = userSessionManager.get(sessionId, token);
		if (userSession == null) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		
		//查询数据
		ScrollPage<UserInfoReplyDto> page = new ScrollPage<UserInfoReplyDto>();
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		page.setPageSize(10);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_status", UserInfoReply.STATUS3);
//		conditions.put("EQ_sourceType", UserInfoReply.SOURCETYPE1);	//注释此行代码，展示所有业务类型的评论
		if (sourceType != null) {//fixedBy：DeweiLi 目前问政用到这个参数
			conditions.put("EQ_sourceType", sourceType);
		}
		if (type.equals(1)) {	//我的评论
			conditions.put("EQ_replyUserId", userSession.getUserId());
		} else {	//收到的评论
			conditions.put("EQ_passiveReplyId", userSession.getUserId());
		}
		page = getEntityDao().queryDtoForScrollPage(page, conditions, -1L, (byte) -1);
		List<UserInfoReplyDto> dtos = page.getPageResults();
		
		//返回数据
		CommonListResult<MyCommentsData> result = new CommonListResult<MyCommentsData>();
		List<MyCommentsData> comments = Lists.newArrayList();
		MyCommentsData data;
		if (CollectionUtils.isNotEmpty(dtos)) {
			boolean isAnonymous = false;	//是否展示匿名信息
			for (UserInfoReplyDto dto : dtos) {
				isAnonymous = type.equals(2) && UserInfoReply.TYPE1.equals(dto.getType());
				data = new MyCommentsData();
				data.setCommentTime(DateUtil.convertTimeToFormatHore1(dto.getCreateTime().getTime()));
//				data.setCommentTime(DateUtil.toString(dto.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
				data.setContent(dto.getContent());
				data.setHeaderImg(isAnonymous ? "" : dto.getImgUrl());
				data.setSourceId(UserInfoReply.SOURCETYPE1.equals(dto.getSourceType()) ? dto.getInfoClassifyId() : dto.getSourceId());
				data.setSourceTitle(dto.getSourceTitle());	
				data.setSourceType(dto.getSourceType());
				data.setPassiveReplyContent(dto.getSourceTitle());
				data.setPassiveReplyName(dto.getPassiveReplyName());
				data.setPraiseCount(dto.getPraise());	
				data.setReplyCount(dto.getReplyCount());
				data.setReplyId(dto.getId());
				data.setUserId(dto.getReplyUserId());
				data.setUserName(isAnonymous ? dto.getAnonymousName() : dto.getNickname());
				comments.add(data);
			}
		}
		result.setDataList(comments);
		return result;
	}
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月12日
     */
    @Override
    public PageInfo<UserInfoReplyDto> queryByPage(PageInfo<UserInfoReplyDto> pageInfo, Map<String, Object> conditions,
            Map<String, Boolean> orders,Byte sourceType) {
        return this.getEntityDao().queryByPage(pageInfo, conditions, orders,sourceType);
    }
    
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.manager.UserInfoReplyManager#queryScrollPage(com.cqliving.framework.common.dao.support.ScrollPage, java.util.Map, java.util.Map)
	 */
	@Override
	public ScrollPage<UserInfoReplyDto> queryScrollPage(ScrollPage<UserInfoReplyDto> scrollPage,Map<String, Object> conditions, Byte sourceType) {
		
		scrollPage = this.getEntityDao().queryDtoForScrollPage(scrollPage, conditions, -1L, sourceType);
		this.filterSensitive(scrollPage.getPageResults());
		return scrollPage;
	}

	private List<UserInfoReplyDto> filterSensitive(List<UserInfoReplyDto> lists){
		if(CollectionUtils.isEmpty(lists)){
			return null;
		}
		List<String> sensitiveWords = this.getSensitive(lists.get(0).getAppId());
		if(CollectionUtils.isEmpty(sensitiveWords)){
			return lists;
		}
		Iterator<UserInfoReplyDto> its = lists.iterator();
		while(its.hasNext()){
			UserInfoReplyDto dto = its.next();
			dto.setContent(StringUtil.replaceSensitiveWord(dto.getContent(), sensitiveWords,"**"));
		}
		return lists;
	}
	
	private List<String> getSensitive(Long appId){
		//获取敏感词
		List<ConfigSensitiveWords> sensitiveWordsList = configSensitiveWordsDao.getByAppId(appId);
		if(CollectionUtils.isEmpty(sensitiveWordsList)){
			return null;
		}
		List<String> sensitiveList = Lists.newArrayList();
		for (ConfigSensitiveWords obj : sensitiveWordsList) {
			if (ConfigSensitiveWords.STATUS3.equals(obj.getStatus())) {
				sensitiveList.add(obj.getName());
			}
		}
		return sensitiveList;
	}
	
	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Byte add(Long appId, String sessionId, String token, Long sourceId, Byte sourceType, String place,
			String lng, String lat, Long replyId, String passiveReplyName, Long passiveReplyId, String content, Boolean isAnonymous) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		
		//查询用户
		Long userId;
		Byte type;	//回复账户类型
		if (StringUtils.isNotBlank(token)) {	//注册用户
			UserSession userSession = userSessionManager.getByToken(token);
			if (userSession == null) {	//用户未登录
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
			}
			userId = userSession.getUserId();
			type = isAnonymous ? UserInfoReply.TYPE1 : UserInfoReply.TYPE0;
		} else {	//游客用户
			//查询游客用户
			UserSession userSession = userSessionManager.getTourist(sessionId);
			if (userSession == null) {
				//创建游客用户
				UserAccount userAccount = userAccountManager.createTourist(appId, sessionId);
				userId = userAccount.getId();
			} else {
				userId = userSession.getUserId();
			}
			type = UserInfoReply.TYPE2;
		}
		UserInfo userInfo = userInfoDao.get(userId);
		UserAccount userAccount = userAccountManager.get(userId);
		
		//判断用户账户状态，禁用账户不能发表评论 By Tangtao 2017-01-11
		if (userAccount == null || !UserAccount.STATUS0.equals(userAccount.getStatus())) {
			throw new BusinessException(ErrorCodes.FAILURE, "您不能发表评论");
		}
		
		//保存数据
		//新闻评论时，保存 information 表的 id 到 source_id
		UserInfoReply obj = new UserInfoReply();
		Date now = DateUtil.now();
		String sourceTitle = "";
		Byte status = null;
		boolean isCommentReply = passiveReplyId != null;	//是否回复评论
		if (isCommentReply) {	//获取被评论的内容
			UserInfoReply uir = getEntityDao().get(replyId);
			sourceTitle = uir.getContent();
		}
		if (BusinessType.SOURCE_TYPE_1.equals(sourceType)) {	//新闻
			InfoClassify infoClassify = infoClassifyDao.get(sourceId);
			sourceTitle = isCommentReply ? sourceTitle : infoClassify.getTitle();
			obj.setInfoClassifyId(sourceId);
			obj.setSourceId(infoClassify == null ? 0 : infoClassify.getInformationId());
			if (InfoClassify.COMMENTTYPE1.equals(infoClassify.getCommentType())) {	//新闻不允许评论
				throw new BusinessException(
						ErrorCodes.InfomationErrorEnum.COMMENT_UNALLOWED.getCode(), 
						ErrorCodes.InfomationErrorEnum.COMMENT_UNALLOWED.getDesc());
			}
			if (InfoClassify.COMMENTVALIDATETYPE0.equals(infoClassify.getCommentValidateType())) {
				status = UserInfoReply.STATUS3;
				//递增新闻评论数
				informationDao.addReplyCount(sourceId,infoClassify.getInformationId());
				if (replyId != null) {	//回复评论
					//递增评论的回复数
					getEntityDao().increaseReplyCount(replyId);
				}
			} else {	//评论需审核
				status = UserInfoReply.STATUS2;
			}
		} else if (BusinessType.SOURCE_TYPE_2.equals(sourceType)) {	//问政
			WzQuestion wzQuestion = wzQuestionDao.get(sourceId);
			sourceTitle = isCommentReply ? sourceTitle : wzQuestion.getTitle();
			obj.setSourceId(sourceId);
			//查询问政权限配置，评论是否需要审核
			status = UserInfoReply.STATUS3;
			WzAuthority wzAuthority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_COMMENT_IS_CHECK);
			if(wzAuthority != null){
			    WzAppAuthority wzAppAuthority = wzAppAuthorityManager.getByAuthorityId(appId, wzAuthority.getId());
			    if(wzAppAuthority != null && wzAppAuthority.getValue().equals(WzAppAuthority.VALUE1)){
			    	status = UserInfoReply.STATUS2;
			    }
			}
			//如果不需要审核，评论量加1
			if (UserInfoReply.STATUS3.equals(status)) {
			    wzQuestion.setReplyCount(wzQuestion.getReplyCount() + 1);
			    wzQuestionDao.update(wzQuestion);
			}
		} else if (BusinessType.SOURCE_TYPE_3.equals(sourceType)) { //商情
			//查询是否允许评论
			Byte canComment = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_3);
			if (CommentConfig.VALUE0.equals(canComment)) {
				throw new BusinessException(ErrorCodes.FAILURE, "该店铺不允许评论");
			}
			//查询评论是否需要审核
			Byte needAudit = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.COMMENT_NEED_AUDIT, BusinessType.SOURCE_TYPE_3);
			obj.setSourceId(sourceId);
			if (CommentConfig.VALUE1.equals(needAudit)) {
				//需要审核
				status = UserInfoReply.STATUS2;
			} else {
				status = UserInfoReply.STATUS3;
				//递增评论数
				shopInfoDao.increaseReplyCount(sourceId);
				if (replyId != null) {	//回复评论
					//递增评论的回复数
					getEntityDao().increaseReplyCount(replyId);
				}
			}
			ShopInfo shopInfo = shopInfoDao.get(sourceId);
			sourceTitle = isCommentReply ? sourceTitle : shopInfo.getName();
		} else if (BusinessType.SOURCE_TYPE_4.equals(sourceType)) {	//随手拍
			//查询是否允许评论
			Byte canComment = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_4);
			if (CommentConfig.VALUE0.equals(canComment)) {
				throw new BusinessException(ErrorCodes.FAILURE, "该随手拍不允许评论");
			}
			//查询评论是否需要审核
			Byte needAudit = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.COMMENT_NEED_AUDIT, BusinessType.SOURCE_TYPE_4);
			obj.setSourceId(sourceId);
			if (CommentConfig.VALUE1.equals(needAudit)) {
				//需要审核
				status = UserInfoReply.STATUS2;
			} else {
				status = UserInfoReply.STATUS3;
				//递增评论数
				shootInfoDao.increaseReplyCount(sourceId);
				if (replyId != null) {	//回复评论
					//递增评论的回复数
					getEntityDao().increaseReplyCount(replyId);
				}
			}
			ShootInfo shootInfo = shootInfoDao.get(sourceId);
			sourceTitle = isCommentReply ? sourceTitle : shootInfo.getContent();
		} else if (BusinessType.SOURCE_TYPE_5.equals(sourceType)) {	//段子
			//查询是否允许评论
			Byte canComment = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_5);
			if (CommentConfig.VALUE0.equals(canComment)) {
				throw new BusinessException(ErrorCodes.FAILURE, "该段子不允许评论");
			}
			//查询评论是否需要审核
			Byte needAudit = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.COMMENT_NEED_AUDIT, BusinessType.SOURCE_TYPE_5);
			obj.setSourceId(sourceId);
			if (CommentConfig.VALUE1.equals(needAudit)) {
				//需要审核
				status = UserInfoReply.STATUS2;
			} else {
				status = UserInfoReply.STATUS3;
				//递增评论数
				jokeInfoDao.increaseReplyCount(sourceId);
				if (replyId != null) {	//回复评论
					//递增评论的回复数
					getEntityDao().increaseReplyCount(replyId);
				}
			}
			JokeInfo jokeInfo = jokeInfoDao.get(sourceId);
			sourceTitle = isCommentReply ? sourceTitle : jokeInfo.getContent();
		} else if (BusinessType.SOURCE_TYPE_6.equals(sourceType)) {		//活动
			//查询是否允许评论
			Byte canComment = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_6);
			if (CommentConfig.VALUE0.equals(canComment)) {
				throw new BusinessException(ErrorCodes.FAILURE, "该活动不允许评论");
			}
			//查询评论是否需要审核
			Byte needAudit = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.COMMENT_NEED_AUDIT, BusinessType.SOURCE_TYPE_6);
			obj.setSourceId(sourceId);
			ActInfoList actInfoList = actInfoListDao.get(sourceId);
			ActInfo actInfo = actInfoDao.get(actInfoList.getActInfoId());
			if (CommentConfig.VALUE1.equals(needAudit)) {
				//需要审核
				status = UserInfoReply.STATUS2;
			} else {
				status = UserInfoReply.STATUS3;
				//递增评论数
				actInfoDao.increaseReplyCount(actInfoList.getActInfoId());
				if (replyId != null) {	//回复评论
					//递增评论的回复数
					getEntityDao().increaseReplyCount(replyId);
				}
			}
			sourceTitle = isCommentReply ? sourceTitle : actInfo.getTitle();
		} else if (BusinessType.SOURCE_TYPE_7.equals(sourceType)) {	//话题
			//查询是否允许评论
			Byte canComment = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_7);
			if (CommentConfig.VALUE0.equals(canComment)) {
				throw new BusinessException(ErrorCodes.FAILURE, "该话题不允许评论");
			}
			//查询评论是否需要审核
			Byte needAudit = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.COMMENT_NEED_AUDIT, BusinessType.SOURCE_TYPE_7);
			obj.setSourceId(sourceId);
			if (CommentConfig.VALUE1.equals(needAudit)) {
				//需要审核
				status = UserInfoReply.STATUS2;
			} else {
				status = UserInfoReply.STATUS3;
				//递增评论数
				topicInfoDao.increaseReplyCount(sourceId);
				if (replyId != null) {	//回复评论
					//递增评论的回复数
					getEntityDao().increaseReplyCount(replyId);
				}
			}
			TopicInfo topicInfo = topicInfoDao.get(sourceId);
			sourceTitle = isCommentReply ? sourceTitle : topicInfo.getName();
		} else if (BusinessType.SOURCE_TYPE_10.equals(sourceType)) {		//旅游
			//查询是否允许评论
			Byte canComment = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_10);
			if (CommentConfig.VALUE0.equals(canComment)) {
				throw new BusinessException(ErrorCodes.FAILURE, "该旅游不允许评论");
			}
			//查询评论是否需要审核
			Byte needAudit = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.COMMENT_NEED_AUDIT, BusinessType.SOURCE_TYPE_10);
			obj.setSourceId(sourceId);
			if (CommentConfig.VALUE1.equals(needAudit)) {
				//需要审核
				status = UserInfoReply.STATUS2;
			} else {
				status = UserInfoReply.STATUS3;
				//递增评论数
				tourismInfoDao.increaseReplyCount(sourceId);
				if (replyId != null) {	//回复评论
					//递增评论的回复数
					getEntityDao().increaseReplyCount(replyId);
				}
			}
			TourismInfo tourismInfo = tourismInfoDao.get(sourceId);
			sourceTitle = isCommentReply ? sourceTitle : tourismInfo.getName();
		} else if (BusinessType.SOURCE_TYPE_11.equals(sourceType)) {		//手机置业
			//查询是否允许评论
			Byte canComment = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_11);
			if (CommentConfig.VALUE0.equals(canComment)) {
				throw new BusinessException(ErrorCodes.FAILURE, "该手机置业不允许评论");
			}
			//查询评论是否需要审核
			Byte needAudit = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.COMMENT_NEED_AUDIT, BusinessType.SOURCE_TYPE_11);
			obj.setSourceId(sourceId);
			if (CommentConfig.VALUE1.equals(needAudit)) {
				//需要审核
				status = UserInfoReply.STATUS2;
			} else {
				status = UserInfoReply.STATUS3;
				//递增评论数
				buildingInfoDao.increaseReplyCount(sourceId);
				if (replyId != null) {	//回复评论
					//递增评论的回复数
					getEntityDao().increaseReplyCount(replyId);
				}
			}
			BuildingInfo buildingInfo = buildingInfoDao.get(sourceId);
			sourceTitle = isCommentReply ? sourceTitle : buildingInfo.getName();
		}
		obj.setAppId(appId);
		obj.setAuditingType(UserInfoReply.AUDITINGTYPE0);
		obj.setContent(content);
		obj.setCreateTime(now);
		obj.setLat(lat);
		obj.setLng(lng);
//		obj.setName(userInfo.getName());		//不缓存评论人姓名，去关联查询 By Tangtao 2016-06-15
		obj.setPassiveRelpsyStatus(UserInfoReply.PASSIVERELPSYSTATUS3);
		obj.setPassiveReplyId(passiveReplyId);
		obj.setPassiveReplyName(passiveReplyName);
		obj.setPlace(place);
		obj.setPraise(0);
		obj.setRefInfoReplyId(replyId);
		obj.setReplyCount(0);
		obj.setReplyUserId(userId);
		obj.setSessionCode(sessionId);
		obj.setSourceType(sourceType);
		obj.setStatus(status);
		obj.setType(type);
		obj.setUpdateTime(now);
		obj.setUpdator(userInfo.getName());
		obj.setUpdatorId(userId);
		obj.setSourceTitle(sourceTitle);
		save(obj);
		return status;
	}

    @Override
    public CommonListResult<MyCommentsData> queryWzScrollPage(Long appId, String sessionId, String token, Integer type, Byte sourceType, Long lastId) {
		//获取用户信息
		UserSession userSession = userSessionManager.get(sessionId, token);
		//查询数据
		ScrollPage<UserInfoReplyDto> page = new ScrollPage<UserInfoReplyDto>();
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		page.setPageSize(10);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_status", InfoClassify.STATUS3);
		conditions.put("EQ_sourceType", sourceType);
		if (type.equals(1)) {	//我的评论
			conditions.put("EQ_replyUserId", userSession.getUserId());
		} else {	//收到的评论
			conditions.put("EQ_passiveReplyId", userSession.getUserId());
		}
		page = getEntityDao().queryDtoForScrollPage(page, conditions, -1L, sourceType);
		List<UserInfoReplyDto> dtos = page.getPageResults();
		
		//返回数据
		CommonListResult<MyCommentsData> result = new CommonListResult<MyCommentsData>();
		List<MyCommentsData> comments = Lists.newArrayList();
		MyCommentsData data;
		if (CollectionUtils.isNotEmpty(dtos)) {
			boolean isAnonymous = false;	//是否展示匿名信息
			for (UserInfoReplyDto dto : dtos) {
				isAnonymous = type.equals(2) && UserInfoReply.TYPE1.equals(dto.getType());
				data = new MyCommentsData();
				data.setCommentTime(DateUtil.convertTimeToFormatHore1(dto.getCreateTime().getTime()));
				data.setContent(dto.getContent());
				data.setHeaderImg(isAnonymous ? "" : dto.getImgUrl());
				data.setSourceId(UserInfoReply.SOURCETYPE1.equals(dto.getSourceType()) ? dto.getInfoClassifyId() : dto.getSourceId());
				data.setSourceTitle(dto.getSourceTitle());	
				data.setPassiveReplyContent(dto.getPassiveReplyContent());
				data.setPassiveReplyName(dto.getPassiveReplyName());
				data.setPraiseCount(dto.getPraise());	
				data.setReplyCount(dto.getReplyCount());
				data.setReplyId(dto.getId());
				data.setUserId(dto.getReplyUserId());
				data.setUserName(isAnonymous ? dto.getAnonymousName() : dto.getNickname());
				comments.add(data);
			}
		}
		result.setDataList(comments);
		return result;
	}

	@Override
	public Integer getReplyCount(Long appId, Long sourceId, Byte sourceType) {
		int count = 0;
		if (BusinessType.SOURCE_TYPE_1.equals(sourceType)) {	//资讯
			Information information = informationDao.get(sourceId);
			if (information == null || information.getReplyCount() == null) {
				count = 0;
			} else {
				InformationDto dto = new InformationDto();
				BeanUtils.copyProperties(information, dto);
				informationDao.setViewAndReplyCount(dto);
				count = dto.getReplyCount();
			}
		} else if (BusinessType.SOURCE_TYPE_2.equals(sourceType)) {	//问政
			WzQuestion wzQuestion = wzQuestionDao.get(sourceId);
			count = (wzQuestion == null || wzQuestion.getReplyCount() == null) ? 0 : wzQuestion.getReplyCount();
		} else if (BusinessType.SOURCE_TYPE_3.equals(sourceType)) {	//商情
			ShopInfo shopInfo = shopInfoDao.get(sourceId);
			count = (shopInfo == null || shopInfo.getReplyCount() == null) ? 0 : shopInfo.getReplyCount();
		} else if (BusinessType.SOURCE_TYPE_4.equals(sourceType)) {	//随手拍
			ShootInfo shootInfo = shootInfoDao.get(sourceId);
			count = shootInfo == null || shootInfo.getReplyCount() == null ? 0 : shootInfo.getReplyCount();
		} else if (BusinessType.SOURCE_TYPE_5.equals(sourceType)) {	//段子
			JokeInfo jokeInfo = jokeInfoDao.get(sourceId);
			count = jokeInfo == null || jokeInfo.getReplyCount() == null ? 0 : jokeInfo.getReplyCount();
		} else if (BusinessType.SOURCE_TYPE_6.equals(sourceType)) {	//活动
			ActInfoList actInfoList = actInfoListDao.get(sourceId);
			ActInfo actInfo = actInfoDao.get(actInfoList.getActInfoId());
			count = actInfo == null || actInfo.getReplyCount() == null ? 0 : actInfo.getReplyCount();
		} else if (BusinessType.SOURCE_TYPE_7.equals(sourceType)) {	//话题
			TopicInfo topicInfo = topicInfoDao.get(sourceId);
			count = topicInfo == null || topicInfo.getReplyCount() == null ? 0 : topicInfo.getReplyCount();
		} else if (BusinessType.SOURCE_TYPE_10.equals(sourceType)) {	//旅游
			TourismInfo tourismInfo = tourismInfoDao.get(sourceId);
			count = tourismInfo == null || tourismInfo.getReplyCount() == null ? 0 : tourismInfo.getReplyCount();
		} else if (BusinessType.SOURCE_TYPE_11.equals(sourceType)) {	//手机置业
			BuildingInfo buildingInfo = buildingInfoDao.get(sourceId);
			count = buildingInfo == null || buildingInfo.getReplyCount() == null ? 0 : buildingInfo.getReplyCount();
		} else if (BusinessType.SOURCE_TYPE_13.equals(sourceType)) {	//商城
			ShoppingGoods goods = shoppingGoodsDao.get(sourceId);
			count = goods == null || goods.getReplyCount() == null ? 0 : goods.getReplyCount();
		}
		return count;
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void remove(Long appId, String sessionId, String token, Long userInfoReplyId) {
		//获取用户
		UserSession userSession = userSessionManager.get(sessionId, token);
		if (userSession == null) {
			logger.error("删除我的评论失败：操作用户不存在或未登录");
			throw new BusinessException(ErrorCodes.FAILURE, "删除失败");
		}
		Long userId = userSession.getUserId();
		
		//验证记录是否存在
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_replyUserId", userId);
		map.put("EQ_id", userInfoReplyId);
		List<UserInfoReply> list = query(map, null);
		if (CollectionUtils.isEmpty(list)) {
			logger.error("删除我的评论失败：评论记录不存在");
			throw new BusinessException(ErrorCodes.FAILURE, "删除失败");
		}
		UserInfoReply obj = list.get(0);
		
		//减少源记录评论数
		int count = 0;
		boolean flag = true;
		if (UserInfoReply.STATUS3.equals(obj.getStatus())) {
			if (BusinessType.SOURCE_TYPE_1.equals(obj.getSourceType())) {	//资讯
				//count += informationDao.decreaseReplyCount(obj.getSourceId());
				//count += infoClassifyDao.decreaseReplyCount(obj.getInfoClassifyId());
				count = informationDao.minusReplyCount(obj.getInfoClassifyId(),obj.getSourceId());
				count = 2;
				flag = count == 2;
			} else if (BusinessType.SOURCE_TYPE_2.equals(obj.getSourceType())) {	//问政
				count = wzQuestionDao.decreaseReplyCount(obj.getSourceId());
				flag = count == 1;
			} else if (BusinessType.SOURCE_TYPE_3.equals(obj.getSourceType())) {	//商情
				count = shopInfoDao.decreaseReplyCount(obj.getSourceId());
				flag = count == 1;
			} else if (BusinessType.SOURCE_TYPE_4.equals(obj.getSourceType())) {	//随手拍
				count = shootInfoDao.decreaseReplyCount(obj.getSourceId());
				flag = count == 1;
			} else if (BusinessType.SOURCE_TYPE_5.equals(obj.getSourceType())) {	//段子
				count = jokeInfoDao.decreaseReplyCount(obj.getSourceId());
				flag = count == 1;
			} else if (BusinessType.SOURCE_TYPE_6.equals(obj.getSourceType())) {	//活动
				ActInfoList actInfoList = actInfoListDao.get(obj.getSourceId());
				count = actInfoDao.decreaseReplyCount(actInfoList.getActInfoId());
				flag = count == 1;
			} else if (BusinessType.SOURCE_TYPE_7.equals(obj.getSourceType())) {	//话题
				count = topicInfoDao.decreaseReplyCount(obj.getSourceId());
				flag = count == 1;
			}
		}
		
		//如果是评论的回复，递减被回复的评论的回复数
		boolean flag1 = true;
		if (obj.getRefInfoReplyId() != null) {
			int c = getEntityDao().decreaseReplyCount(obj.getRefInfoReplyId());
			flag1 = c == 1;
		}
		
		if (!flag || !flag1) {
			logger.error("删除我的评论失败：递减评论数失败");
			throw new BusinessException(ErrorCodes.FAILURE, "删除失败");
		}
		
		//删除评论
		List<Long> ids = Lists.newArrayList();
		ids.add(userInfoReplyId);
		getEntityDao().updateStatus(UserInfoReply.STATUS99, ids);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.manager.UserInfoReplyManager#queryForTopicCommentsPage(com.cqliving.framework.common.dao.support.ScrollPage, java.util.Map)
	 */
	@Override
	public ScrollPage<UserInfoReplyDto> queryForTopicCommentsPage(ScrollPage<UserInfoReplyDto> scrollPage,
			Map<String, Object> conditions) {
		
		return this.getEntityDao().queryForTopicCommentsPage(scrollPage, conditions);
	}
	
}