package com.cqliving.cloud.online.account.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.dao.UserActListDao;
import com.cqliving.cloud.online.account.dao.UserActVoteDao;
import com.cqliving.cloud.online.account.dao.UserShareHistoryDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserActList;
import com.cqliving.cloud.online.account.domain.UserActVote;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserActVoteManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.dao.ActVoteDao;
import com.cqliving.cloud.online.act.dao.ActVoteItemDao;
import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.StringUtil;

@Service("userActVoteManager")
public class UserActVoteManagerImpl extends EntityServiceImpl<UserActVote, UserActVoteDao> implements UserActVoteManager {

	@Autowired
	UserSessionManager userSessionManager;
	@Autowired
	UserAccountManager userAccountManager;
	@Autowired
	UserShareHistoryDao userShareHistoryDao;
	@Autowired
	ActVoteDao actVoteDao;
	@Autowired
	UserActListDao userActListDao;
	@Autowired
	ActVoteItemDao actVoteItemDao;
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.manager.UserActVoteManager#saveUserVote(com.cqliving.cloud.online.account.domain.UserActVote, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public synchronized void saveUserVote(UserActVote userActVote,UserActList userActList,Long[] itemIds, String sessionCode, String token) {
		
		if(!this.canVote(userActVote, itemIds, sessionCode, token))
			throw new BusinessException(ErrorCodes.FAILURE,"不能投票，规则限制");
		
		UserSession userSession = this.getUserSession(userActVote.getAppId(), sessionCode, token);
		
		userActList = this.saveUserActList(userSession.getUserId(), userActList);
		
		for(Long itemId : itemIds){
			UserActVote newUserActVote = new UserActVote();
			newUserActVote.setActVoteClassifyId(userActVote.getActVoteClassifyId());
			newUserActVote.setActVoteItemId(itemId);
			newUserActVote.setCreateTime(Dates.now());
			newUserActVote.setUserActListId(userActList.getId());
			newUserActVote.setUserId(userSession.getUserId());
			newUserActVote.setVoteId(userActVote.getVoteId());
			this.getEntityDao().saveAndFlush(newUserActVote);
			
			//增加投票数
			actVoteItemDao.addActValue(itemId,1);
		}
	}
	
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private UserActList saveUserActList(Long userId,UserActList userActList){
		
		List<UserActList> userActLists = userActListDao.findByActInfoListAndUserId(userActList.getActInfoListId(),userId);
		Date now = Dates.now();
		if(CollectionUtils.isEmpty(userActLists)){
			userActList.setCreateTime(now);
			userActList.setUpdateTime(now);
			userActList.setJoinCount(1);
			userActList.setUserId(userId);
			userActList = userActListDao.saveAndFlush(userActList);
		}else{
			 userActListDao.addJoinCount(userActLists.get(0).getId(),now);
			 userActList = userActLists.get(0);
		}
		return userActList;
	}
	
	//检查是否能投票
	private boolean canVote(UserActVote userActVote,Long[] itemIds,String sessionCode,String token){
		
		Long voteId = userActVote.getVoteId();
		Long voteClassifyId = userActVote.getActVoteClassifyId();
		
		if(null == voteId || 0 == voteId.longValue())
			throw new BusinessException(ErrorCodes.FAILURE,"投票不存在");
		
		ActVote actVote = actVoteDao.get(voteId);
		if(null == actVote)
			throw new BusinessException(ErrorCodes.FAILURE,"投票不存在");
		
		if(StringUtil.isEmpty(itemIds))
			throw new BusinessException(ErrorCodes.FAILURE,"请至少选择一个投票项");
		
		//多选限制
		byte limitRuleType = actVote.getLimitRuleType();
		int limitRuleTimes = actVote.getLimitRuleTimes();
		//多选不能超过限制数
		if(ActVote.LIMITRULETYPE1.byteValue() == limitRuleType && limitRuleTimes >=1 && limitRuleTimes < itemIds.length)
			throw new BusinessException(ErrorCodes.FAILURE,"最多只能选择"+limitRuleTimes+"项");
		
		byte loggedStatus = actVote.getLoggedStatus();
		UserSession userSession = userSessionManager.getByToken(token);
		if(ActVote.LOGGEDSTATUS1.byteValue() == loggedStatus){
			if(StringUtil.isEmpty(token) || null == userSession){
				throw new BusinessException(-11111,"请登录后再操作");
			}
		}
		if(null == userSession){
			userSession = userSessionManager.getTourist(sessionCode);
		}
		if(null == userSession)return true;
		
		Long userId = userSession.getUserId();
		Integer shareAddTimes = 0;//this.getShareAddTimes(actVote, userId, userSession.getAppId());
		//总数限制
		byte limitRateType = actVote.getLimitRateType();
		int limitRateTimes = actVote.getLimitRateTimes();
		if(ActVote.LIMITRATETYPE1.byteValue() == limitRateType){//总数限制
			long count = this.getEntityDao().countByUserIdVoteClassifyId(userId, voteClassifyId);
			count -= shareAddTimes;
			if(count >= limitRateTimes)
				throw new BusinessException(ErrorCodes.FAILURE,"最多只能投票"+limitRateTimes+"次");
		}
        if(ActVote.LIMITRATETYPE2.byteValue() == limitRateType){//每日限制
        	long count = this.getEntityDao().countTodayByUserIdVoteClassifyId(userId, voteClassifyId, Dates.todayStart(), Dates.todayEnd());
        	count -= shareAddTimes;
			if(count >= limitRateTimes)
				throw new BusinessException(ErrorCodes.FAILURE,"当日最多只能投票"+limitRateTimes+"次");
		}
		//单项限制
		byte limitSingleType = actVote.getLimitSingleType();
		int limitSingleTimes = actVote.getLimitSingleTimes();
        if(ActVote.LIMITSINGLETYPE1.byteValue() == limitSingleType){//总数限制
        	for(Long itemId : itemIds){
        		long count = this.getEntityDao().countByUserIdItemId(userId, itemId);
    			if(count >=  limitSingleTimes)
    				throw new BusinessException(ErrorCodes.FAILURE,"单项最多只能投票"+limitSingleTimes+"次");
    		}
		}
        if(ActVote.LIMITSINGLETYPE2.byteValue() == limitSingleType){//每日限制
        	for(Long itemId : itemIds){
        		long count = this.getEntityDao().countTodayByUserIdItemId(userId, itemId,Dates.todayStart(), Dates.todayEnd());
    			if(count >=  limitSingleTimes)
    				throw new BusinessException(ErrorCodes.FAILURE,"单项单日最多只能投票"+limitSingleTimes+"次");
    		}
		}
		
		return true;
	}
	
	/*private Integer getShareAddTimes(ActVote actVote,Long userId,Long appId){
		
		if(null == actVote)return 0;
		
		byte isShare = actVote.getIsShare();
		int shareAddTimes = 0;
		if(ActVote.ISSHARE1.byteValue() == isShare){
			shareAddTimes = actVote.getShareAddTimes();
		}
		byte shareLimitType = actVote.getLimitShareType();
		
		if(ActVote.LIMITSHARETYPE1.byteValue() == shareLimitType || ActVote.LIMITSHARETYPE0.byteValue() == shareLimitType){//总数限制
			long total = userShareHistoryDao.findTotalByUserIdSourceId(userId, appId,actVote.getId());
			shareAddTimes *=total;
		}else if(ActVote.LIMITSHARETYPE2.byteValue() == shareLimitType){//每日限制
			long total = userShareHistoryDao.findTotalByUserIdToday(userId, appId,actVote.getId(),Dates.todayStart(), Dates.todayEnd());
			shareAddTimes *=total;
		}
		return shareAddTimes;
	}*/
	
	
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
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
	 * @see com.cqliving.cloud.online.account.manager.UserActVoteManager#findJoinTotalByVoteId(java.lang.Long)
	 */
	@Override
	public Long findJoinTotalByVoteId(Long voteId) {
		
		return this.getEntityDao().findJoinTotalByVoteId(voteId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.manager.UserActVoteManager#surplusVote(java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public Integer surplusVote(String sessionCode, String token, Long voteClassifyId) {
		
		if(StringUtil.isEmpty(sessionCode) || null == voteClassifyId)
			return 0;
		
		ActVote actVote= actVoteDao.findByVoteClassifyId(voteClassifyId);
		if(null == actVote)
			return 0;
		
		byte limitRateType = actVote.getLimitRateType();
		if(ActVote.LIMITRATETYPE0.byteValue() == limitRateType){//无限制说个屁啊
			return -1;
		}
		UserSession userSession = this.getUserSession(actVote.getAppId(), sessionCode, token);
		Long userId = userSession.getUserId();
		
		//已经投票次数
		long hasVoteNum = 0;
		if(ActVote.LIMITRATETYPE1.byteValue() == limitRateType){//总数限制
			hasVoteNum = this.getEntityDao().countByUserIdVoteClassifyId(userId, voteClassifyId);
		}else if(ActVote.LIMITRATETYPE2.byteValue() == limitRateType){
			hasVoteNum = this.getEntityDao().countTodayByUserIdVoteClassifyId(userId, voteClassifyId, Dates.todayStart(), Dates.todayEnd());
		}
		//投票次数
		int voteTimes = actVote.getLimitRateTimes();
		int shareAddTimes = 0;//this.getShareAddTimes(actVote, userId, userSession.getAppId());
		voteTimes +=shareAddTimes;
		
		return voteTimes-(int)hasVoteNum;
	}
}
