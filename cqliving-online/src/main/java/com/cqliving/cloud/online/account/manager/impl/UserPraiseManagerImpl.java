package com.cqliving.cloud.online.account.manager.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserPraiseManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.dao.ActInfoDao;
import com.cqliving.cloud.online.act.dao.ActInfoListDao;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.building.dao.BuildingInfoDao;
import com.cqliving.cloud.online.info.dao.InformationDao;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.PraisesData;
import com.cqliving.cloud.online.joke.dao.JokeInfoDao;
import com.cqliving.cloud.online.shoot.dao.ShootInfoDao;
import com.cqliving.cloud.online.tourism.dao.TourismInfoDao;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("userPraiseManager")
public class UserPraiseManagerImpl extends EntityServiceImpl<UserPraise, UserPraiseDao> implements UserPraiseManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UserPraiseManagerImpl.class);
	
	@Autowired
	private ActInfoDao actInfoDao;
	@Autowired
	private ActInfoListDao actInfoListDao;
	@Autowired
	private BuildingInfoDao buildingInfoDao;
	@Autowired
	private InformationDao informationDao;
	@Autowired
	private JokeInfoDao jokeInfoDao;
	@Autowired
	private ShootInfoDao shootInfoDao;
	@Autowired
	private TourismInfoDao tourismInfoDao;
	@Autowired
	private UserAccountManager userAccountManager;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserInfoReplyDao userInfoReplyDao;
	@Autowired
	private UserSessionManager userSessionManager;
	
	@Override
	public CommonListResult<PraisesData> getMyPraisePage(Long appId, String token, Long lastId) {
		//获取用户信息
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		//查询数据
		ScrollPage<UserPraise> page = new ScrollPage<UserPraise>();
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		page.setPageSize(10);
		page = getEntityDao().getMyPraisePage(page, appId, userSession.getUserId());
		List<UserPraise> list = page.getPageResults();
		
		//返回数据
		CommonListResult<PraisesData> result = new CommonListResult<PraisesData>();
		List<PraisesData> praises = Lists.newArrayList();
		PraisesData data;
		if (CollectionUtils.isNotEmpty(list)) {
			for (UserPraise obj : list) {
				data = new PraisesData();
				data.setContent(UserPraise.OPERATETYPE0.equals(obj.getOperateType()) ? obj.getTitle() : obj.getContent());
				data.setId(obj.getId());
				data.setNickName(obj.getNickName());
				data.setPraiseTime(DateUtil.convertTimeToFormatHore1(obj.getCreateTime().getTime()));
				data.setSourceId(obj.getSourceId());
				data.setSourceType(obj.getSourceType());
				data.setSourceUserImage(obj.getSourceUserImage());
				data.setType(obj.getOperateType());
				data.setUserId(obj.getSourceUserId());
				praises.add(data);
			}
		}
		result.setDataList(praises);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.manager.UserPraiseManager#saveUserPraise(com.cqliving.cloud.online.account.domain.UserPraise)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public UserPraise saveUserPraise(UserPraise userPraise) {
		
		UserSession userSession = userSessionManager.get(userPraise.getSessionCode(),userPraise.getToken());
		if(null == userSession){
			UserAccount userAccount = userAccountManager.createTourist(userPraise.getAppId(),userPraise.getSessionCode());
			userPraise.setNickName(userAccount.getUserName());
			userPraise.setSourceUserId(userAccount.getId());
		}else{
			UserInfo userInfo = userInfoDao.get(userSession.getUserId());
			userPraise.setNickName(userInfo.getName());
			userPraise.setSourceUserId(userSession.getUserId());
			userPraise.setSourceUserImage(userInfo.getImgUrl());
		}
		
		Byte userPraiseType = userPraise.getSourceType();
		if(userPraiseType.byteValue() == UserPraise.SOURCETYPE2.byteValue()){//新闻
			
			List<UserPraise> list = this.getEntityDao().findByUserId(userPraise.getSourceUserId(),userPraiseType, userPraise.getSourceId());
			if(CollectionUtils.isNotEmpty(list)){
				throw new BusinessException(ErrorCodes.FAILURE,"你已赞");
			}
			informationDao.addPraiseCount(userPraise.getSourceId());
		}else if(userPraiseType.byteValue() == UserPraise.OPERATETYPE1.byteValue()){//评论
			
			List<UserPraise> list = this.getEntityDao().findByUserId(userPraise.getUserId(), userPraiseType, userPraise.getSourceId(),userPraise.getSourceUserId());
			if(CollectionUtils.isNotEmpty(list)){
				throw new BusinessException(ErrorCodes.FAILURE,"你已赞");
			}
			userInfoReplyDao.addPraise(userPraise.getSourceId());
		}
		
		userPraise.setCreateTime(Dates.now());
		userPraise.setStatus(UserPraise.STATUS3);
		
		return this.getEntityDao().saveAndFlush(userPraise);
	}

	@Override
	public boolean isPraised(Long appId, String sessionId, String token, Long sourceId, Byte sourceType) {
		UserSession userSession;
		//获取用户信息
		if (StringUtils.isNotBlank(token)) {	//注册用户
			userSession = userSessionManager.getByToken(token);
			if (userSession == null) {	//用户未登录
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
			}
		} else {
			userSession = userSessionManager.getTourist(sessionId);
			if (userSession == null) {	//游客不存在
				return false;
			}
		}
		
		//查询
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_sourceUserId", userSession.getUserId());
		map.put("EQ_sourceId", sourceId);
		map.put("EQ_sourceType", sourceType);
		map.put("EQ_status", UserPraise.STATUS3);
		map.put("EQ_type", UserPraise.TYPE0);
		List<UserPraise> list = query(map, null);
		return CollectionUtils.isNotEmpty(list);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public boolean praise(Long appId, String sessionId, String token, Long userId, String title, Byte operateType, Long sourceId, Byte sourceType, Byte type) {
		UserSession userSession;
		UserAccount userAccount;
		//获取用户信息
		if (StringUtils.isNotBlank(token)) {	//注册用户
			userSession = userSessionManager.getByToken(token);
			if (userSession == null) {	//用户不存在
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
						ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
			}
			userAccount = userAccountManager.get(userSession.getUserId());
		} else {
			userSession = userSessionManager.getTourist(sessionId);
			if (userSession == null) {	//游客不存在
				//创建游客账户
				userAccount = userAccountManager.createTourist(appId, sessionId);
			} else {
				userAccount = userAccountManager.get(userSession.getUserId());
			}
		}
		//查询用户信息
		userAccount = userAccount == null ? userAccountManager.get(userSession.getUserId()) : userAccount;
		UserInfo userInfo = userInfoDao.get(userAccount.getId());
		
		//判断是否已点赞
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_sourceUserId", userAccount.getId());
		map.put("EQ_sourceId", sourceId);
		map.put("EQ_sourceType", sourceType);
		map.put("EQ_status", UserPraise.STATUS3);
		List<UserPraise> list = query(map, null);
		if (CollectionUtils.isNotEmpty(list)) {
			Byte oldType = list.get(0).getType();
			logger.error("操作失败：已" + (UserPraise.TYPE0.equals(oldType) ? "点赞" : "点踩"));
			throw new BusinessException(ErrorCodes.FAILURE, "已" + (UserPraise.TYPE0.equals(oldType) ? "点赞" : "点踩"));
		}
		
		//保存点赞记录
		UserPraise obj = new UserPraise();
		obj.setAppId(appId);
		obj.setCreateTime(DateUtil.now());
		obj.setNickName(userInfo == null ? "" : userInfo.getName());
		obj.setOperateType(operateType);
		obj.setSessionCode(sessionId);
		obj.setSourceId(sourceId);
		obj.setSourceName(BusinessType.allSourceTypes.get(sourceType));
		obj.setSourceType(sourceType);
		obj.setSourceUserId(userAccount.getId());
		obj.setSourceUserImage(userInfo.getImgUrl());
		obj.setStatus(UserPraise.STATUS3);
		obj.setType(type);
		obj.setUserId(userId);
		if (UserPraise.OPERATETYPE0.equals(operateType)) {	//业务类型
			obj.setTitle(title);
		} else {	//评论
			obj.setContent(title);
			obj.setTitle("");
		}
		save(obj);
		
		int count = 0;
		if (UserPraise.TYPE0.equals(type)) {	//点赞
			//递增点赞数
			if (UserPraise.OPERATETYPE1.equals(operateType)) {	//评论
				count = userInfoReplyDao.addPraise(sourceId);
			} else if (UserPraise.SOURCETYPE1.equals(sourceType)) {	//资讯
				count = informationDao.addPraiseCount(sourceId);
			} else if (UserPraise.SOURCETYPE2.equals(sourceType)) {	//问政
				//不做处理
				count = 1;
			} else if (UserPraise.SOURCETYPE3.equals(sourceType)) {	//商情
				//不做处理
				count = 1;
			} else if (UserPraise.SOURCETYPE4.equals(sourceType)) {	//随手拍
				count = shootInfoDao.increasePraiseCount(sourceId);
			} else if (UserPraise.SOURCETYPE5.equals(sourceType)) {	//段子
				count = jokeInfoDao.increasePraiseCount(sourceId);
			} else if (UserPraise.SOURCETYPE6.equals(sourceType)) {	//活动
				ActInfoList actInfoList = actInfoListDao.get(sourceId);
				ActInfo actInfo = actInfoDao.get(actInfoList.getActInfoId());
				count = actInfoDao.increasePraiseCount(actInfo.getId());
			} else if (UserPraise.SOURCETYPE7.equals(sourceType)) {	//话题
				//不做处理
				count = 1;
			} else if (UserPraise.SOURCETYPE10.equals(sourceType)) {	//旅游
				count = tourismInfoDao.increasePraiseCount(sourceId);
			} else if (UserPraise.SOURCETYPE11.equals(sourceType)) {	//手机置业
				count = buildingInfoDao.increasePraiseCount(sourceId);
			}
		} else {	//点踩
			//递增点踩数
			if (UserPraise.SOURCETYPE5.equals(sourceType)) {	//段子
				count = jokeInfoDao.increaseDespiseCount(sourceId);
			}
		}
		if (count <= 0) {
			logger.error("操作失败：递增" + (UserPraise.TYPE0.equals(type) ? "点赞" : "点踩") + "数失败");
			throw new BusinessException(ErrorCodes.FAILURE, (UserPraise.TYPE0.equals(type) ? "点赞" : "点踩") + "失败");
		}
		return true;
	}

}