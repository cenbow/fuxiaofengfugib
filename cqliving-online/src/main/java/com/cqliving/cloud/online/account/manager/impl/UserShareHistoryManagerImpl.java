package com.cqliving.cloud.online.account.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.dao.UserShareHistoryDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.domain.UserShareHistory;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.account.manager.UserShareHistoryManager;
import com.cqliving.cloud.online.act.dao.ActVoteDao;
import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.date.DateUtil;

@Service("userShareHistoryManager")
public class UserShareHistoryManagerImpl extends EntityServiceImpl<UserShareHistory, UserShareHistoryDao> implements UserShareHistoryManager {
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private UserAccountManager userAccountManager;
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	ActVoteDao actVoteDao;
	
	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public boolean add(Long appId, String sessionId, String token, Byte platform, Long sourceId, Byte sourceType, String place, String lat, String lng) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		
		Long userId = null;
		//查询用户
		UserSession userSession = userSessionManager.get(sessionId, token);
		if (userSession == null) {
			UserAccount userAccount = userAccountManager.createTourist(appId, sessionId);
			userId = userAccount.getId();
		} else {
			userId = userSession.getUserId();
		}
		
		//保存数据
		UserShareHistory obj = new UserShareHistory();
		obj.setAppId(appId);
		obj.setCreateTime(DateUtil.now());
		obj.setLat(lat);
		obj.setLng(lng);
		obj.setPlace(place);
		obj.setSessionCode(sessionId);
		obj.setSharePlateform(platform);
		obj.setSourceId(sourceId);
		obj.setSourceType(sourceType);
		obj.setUserId(userId);
		save(obj);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.manager.UserShareHistoryManager#canShareVote(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void canShareVote(Long voteId, Long appId, String sessionCode, String token) {

		UserSession userSession = this.getUserSession(appId, sessionCode, token);
		Long userId = userSession.getUserId();
		
		ActVote actVote = actVoteDao.get(voteId);
		
		byte limitShareType = actVote.getLimitShareType();
		int limitSharTimes = actVote.getLimitRateTimes();
		if(ActVote.LIMITRATETYPE1.byteValue() == limitShareType){//总数限制
			long count = this.getEntityDao().findTotalByUserIdSourceId(userId, appId,voteId);
			if(limitSharTimes >=1 && limitSharTimes <= count)
			   throw new BusinessException(ErrorCodes.FAILURE,"不能分享，分享总次数不能超过"+limitSharTimes+"次");
		}else if(ActVote.LIMITRATETYPE2.byteValue() == limitShareType){//单日限制
			long count = this.getEntityDao().findTotalByUserIdToday(userId, appId,voteId,Dates.todayStart(), Dates.todayEnd());
			if(limitSharTimes >=1 && limitSharTimes <= count)
			   throw new BusinessException(ErrorCodes.FAILURE,"不能分享，每日分享次数不能超过"+limitSharTimes+"次");
		}
	}

	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	private UserSession getUserSession(Long appId, String sessionCode, String token){
        UserSession userSession = userSessionManager.get(sessionCode, token);
		if(null == userSession){
			UserAccount userAccount = userAccountManager.createTourist(appId, sessionCode);
			userSession = new UserSession();
			userSession.setAppId(appId);
			userSession.setUserId(userAccount.getId());
			userSession.setSessionCode(sessionCode);
		}
		return userSession;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.manager.UserShareHistoryManager#share(com.cqliving.cloud.online.account.domain.UserShareHistory)
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void share(UserShareHistory userShareHistory,String token) {
		
		if(null == userShareHistory) return;
		userShareHistory.setCreateTime(Dates.now());
		
		String sessionCode = userShareHistory.getSessionCode();
		UserSession userSession = userSessionManager.get(sessionCode, token);
		if(null != userSession){
			userShareHistory.setUserId(userSession.getUserId());
			this.getEntityDao().saveAndFlush(userShareHistory);
			return;
		}
		
		Byte sourceType = userShareHistory.getSourceType();
		if(null == sourceType)sourceType = UserShareHistory.SHAREPLATEFORM4;
		if(sourceType.byteValue() == UserShareHistory.SHAREPLATEFORM4.byteValue()){
			Long voteId = userShareHistory.getSourceId();
			//检查投票是否需要登录操作，如果不需要登录，则创建游客账户，否则不创建
			ActVote actVote = actVoteDao.get(voteId);
			Byte logStatus = actVote.getLoggedStatus();
			if(logStatus.byteValue() == ActVote.LOGGEDSTATUS0.byteValue()){//可以不登录操作的话，要创建游客账户
				userSession = this.getUserSession(userShareHistory.getAppId(), sessionCode, token);
				userShareHistory.setUserId(userSession.getUserId());
			}
		}
		this.getEntityDao().saveAndFlush(userShareHistory);
	}
}