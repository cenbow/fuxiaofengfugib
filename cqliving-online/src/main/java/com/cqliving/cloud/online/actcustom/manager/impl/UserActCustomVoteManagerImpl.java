package com.cqliving.cloud.online.actcustom.manager.impl;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.actcustom.manager.ActCustomVoteItemManager;
import com.cqliving.cloud.online.actcustom.manager.UserActCustomVoteManager;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.domain.ActQrcode;
import com.cqliving.cloud.online.act.manager.ActQrcodeManager;
import com.cqliving.cloud.online.actcustom.dao.UserActCustomVoteDao;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomVote;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;



@Service("userActCustomVoteManager")
public class UserActCustomVoteManagerImpl extends EntityServiceImpl<UserActCustomVote, UserActCustomVoteDao> implements UserActCustomVoteManager {
	@Autowired
    private UserSessionManager userSessionManager;
	@Autowired
	private ActQrcodeManager actQrcodeManager;
	@Autowired
	private ActCustomVoteItemManager actCustomVoteItemManager;
	
	@Transactional(value="transactionManager")
	@Override
	public Byte addActeVote(String actQrcodeCode,String token, String sessionId, UserActCustomVote userActCustomVote) {
		Date now = DateUtil.now();
		Date stateTime = DateUtil.getTodayFirstTime();
		Date endTime=DateUtil.getTodaylastTime();
		ActQrcode actQrcode  =actQrcodeManager.findByCode(actQrcodeCode);
		if(actQrcode == null){
			throw new BusinessException(ErrorCodes.FAILURE, "活动已失效");
		}
		if(actQrcode.getStartTime().after(now)){
			throw new BusinessException(ErrorCodes.FAILURE, "活动还未开始");
		}
		if(actQrcode.getEndTime().before(now)){
			throw new BusinessException(ErrorCodes.FAILURE, "活动已结束");
		}
		//查询用户
		Long userId;
		String userSetionCode;
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户未登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		userId =userSession.getUserId();
		
		if(StringUtils.isNotBlank(userSession.getSessionCode())){
			userSetionCode=userSession.getSessionCode();
		}else{
			userSetionCode=token;
		}
		userActCustomVote.setSessionId(userSetionCode);
		userActCustomVote.setCreateTime(now);
		userActCustomVote.setUserId(userId);
	    if(actQrcode.getVoteLimitType()==1){
	    	List<UserActCustomVote> VoteList=this.getEntityDao().getVoteListById(userActCustomVote.getActCustomVoteItemId(),userId,userSetionCode                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ,actQrcodeCode, stateTime, endTime);
	    	if(VoteList.size()>=actQrcode.getVoteLimitValue()){
	    		throw new BusinessException(ErrorCodes.FAILURE, "只能投"+actQrcode.getVoteLimitValue()+"票");
	    	}
	    }
	    save(userActCustomVote);
	    actCustomVoteItemManager.updateActValue(userActCustomVote.getActCustomVoteItemId());
		return 3; 
	}
}
