package com.cqliving.cloud.online.account.manager.impl;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.dao.UserSurveyHistoryDao;
import com.cqliving.cloud.online.account.dao.UserSurveyQuestionAnswerDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.domain.UserSurveyHistory;
import com.cqliving.cloud.online.account.domain.UserSurveyQuestionAnswer;
import com.cqliving.cloud.online.account.dto.UserSurveyHistoryDto;
import com.cqliving.cloud.online.account.dto.UserSurveyQuestionAnswerDto;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.account.manager.UserSurveyQuestionAnswerManager;
import com.cqliving.cloud.online.survey.dao.SurveyInfoDao;
import com.cqliving.cloud.online.survey.dao.SurveyQuestionAnswerDao;
import com.cqliving.cloud.online.survey.domain.SurveyInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.StringUtil;

@Service("userSurveyQuestionAnswerManager")
public class UserSurveyQuestionAnswerManagerImpl extends EntityServiceImpl<UserSurveyQuestionAnswer, UserSurveyQuestionAnswerDao> implements UserSurveyQuestionAnswerManager {

	@Autowired
	UserSessionManager userSessionManager;
	@Autowired
	UserAccountManager userAccountManager;
	@Autowired
	UserSurveyHistoryDao userSurveyHistoryDao;
	@Autowired
	SurveyInfoDao surveyInfoDao;
	@Autowired
	SurveyQuestionAnswerDao surveyQuestionAnswerDao;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.manager.UserSurveyQuestionAnswerManager#saveUserSurveyQuestionAnswer(com.cqliving.cloud.online.account.domain.UserSurveyHistory, com.cqliving.cloud.online.account.domain.UserSurveyQuestionAnswer)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public synchronized Long[] saveUserSurveyQuestionAnswer(UserSurveyHistoryDto userSurveyHistoryDto,
			UserSurveyQuestionAnswer userSurveyQuestionAnswer,Long[] answerIds) {
		
		Assert.notNull(userSurveyHistoryDto, "用户调研不能为空");
		Assert.notNull(userSurveyQuestionAnswer, "用户提交的答案不能为空");
		//Assert.notEmpty(answerIds, "请选择答案");
		
		if(!this.canSubmitAnswer(userSurveyHistoryDto, answerIds, userSurveyQuestionAnswer.getQuestionId()))
			throw new BusinessException(ErrorCodes.FAILURE,"超过限制，不能继续提交");
		
		//保存回答历史
		UserSurveyHistory userSurveyHistory = this.cover(userSurveyHistoryDto);
		userSurveyHistory = userSurveyHistoryDao.saveAndFlush(userSurveyHistory);
		//保存答案
		Date now = Dates.now();
		if(!StringUtil.isEmpty(answerIds)){
			for(Long ansId : answerIds){
				UserSurveyQuestionAnswer newUserSurveyQuestionAnswer =  new UserSurveyQuestionAnswer();
				userSurveyQuestionAnswer.setHistoryId(userSurveyHistory.getId());
				userSurveyQuestionAnswer.setAnswerId(ansId);
				BeanUtils.copyProperties(userSurveyQuestionAnswer, newUserSurveyQuestionAnswer);
				newUserSurveyQuestionAnswer.setCreateTime(now);
				this.getEntityDao().saveAndFlush(newUserSurveyQuestionAnswer);
			}
		}else{
			userSurveyQuestionAnswer.setHistoryId(userSurveyHistory.getId());
			userSurveyQuestionAnswer.setCreateTime(now);
			userSurveyQuestionAnswer = this.getEntityDao().saveAndFlush(userSurveyQuestionAnswer);
			answerIds = new Long[]{userSurveyQuestionAnswer.getId()};
		}
		
		//增加答案数
		surveyQuestionAnswerDao.addActValue(Arrays.asList(answerIds));
		return answerIds;
	}

	
	private boolean canSubmitAnswer(UserSurveyHistoryDto userSurveyHistoryDto,Long[] answerIds,Long questionId){
		
		SurveyInfo surveyInfo = surveyInfoDao.get(userSurveyHistoryDto.getSurveyId());

		Byte loggedStatus = surveyInfo.getLoggedStatus();
		String token = userSurveyHistoryDto.getToken();
		UserSession userSession = userSessionManager.getByToken(token);
		if(loggedStatus.byteValue() == SurveyInfo.LOGGEDSTATUS1.byteValue()){
			if(StringUtil.isEmpty(token) || null == userSession)
			  throw new BusinessException(-1111111,"请登录后再操作");
		}	
		if(null == userSession){
			userSession = userSessionManager.getTourist(userSurveyHistoryDto.getSessionCode());
		}
		//类型{0:单选,1:多选,2:限制选多少项}
		Byte limitRuleType = surveyInfo.getLimitRuleType();
		Integer limitRuleTimes = surveyInfo.getLimitRuleTimes();
		if(SurveyInfo.LIMITRULETYPE1.byteValue() == limitRuleType.byteValue() && null != limitRuleTimes 
			 && limitRuleTimes.byteValue() !=-1 && answerIds.length > limitRuleTimes.intValue()){
			throw new BusinessException(ErrorCodes.FAILURE,"最多只能选择"+limitRuleTimes+"项");
		}
		
		if(null == userSession)return true;

		Long userId = userSession.getUserId();
		//类型{0:无限制,1:总数限制,2:每日限制}
		Byte limitRateType = surveyInfo.getLimitRateType();
		Integer limitRateTimes = surveyInfo.getLimitRateTimes();
		if(SurveyInfo.LIMITRATETYPE1.byteValue() == limitRateType.byteValue()){
			long total = userSurveyHistoryDao.findTotalByUserId(userId,surveyInfo.getId());
			if(total >= limitRateTimes.longValue()){
				throw new BusinessException(ErrorCodes.FAILURE,"总项数不能超过"+limitRateTimes+"项");
			}
		}else if(SurveyInfo.LIMITRATETYPE2.byteValue() == limitRateType.byteValue()){
			long todayTotal = userSurveyHistoryDao.findCurrentDayTotalByUserId(userId, Dates.now(),surveyInfo.getId());
			if(todayTotal >= limitRateTimes.longValue()){
				throw new BusinessException(ErrorCodes.FAILURE,"当日项数不能超过"+limitRateTimes+"项");
			}
		}
		
		//单项类型{0:无限制,1:总数限制,2:每日限制}
		Byte limitSingleType = surveyInfo.getLimitSingleType();
		Integer limitSingleTimes = surveyInfo.getLimitSingleTimes();
		if(SurveyInfo.LIMITSINGLETYPE1.byteValue() == limitSingleType.byteValue()){
			List<UserSurveyQuestionAnswerDto> total = this.getEntityDao().findTotalByUserIdAndSurveyId(questionId, userSurveyHistoryDto.getSurveyId(), userId, false);
			boolean checkSingleType =  this.checkLimitSingleType(answerIds, total, limitSingleTimes);
			if(!checkSingleType)
				throw new BusinessException(ErrorCodes.FAILURE,"单项总数不能超过"+limitSingleTimes+"项");
		}else if(SurveyInfo.LIMITSINGLETYPE2.byteValue() == limitSingleType.byteValue()){
			List<UserSurveyQuestionAnswerDto> todayTotal = this.getEntityDao().findTotalByUserIdAndSurveyId(questionId, userSurveyHistoryDto.getSurveyId(), userId, true);
			boolean checkSingleType =  this.checkLimitSingleType(answerIds, todayTotal, limitSingleTimes);
			if(!checkSingleType)
				throw new BusinessException(ErrorCodes.FAILURE,"单项当日最大不能超过"+limitSingleTimes+"项");
		}
		return true;
	}
	
	
	private boolean checkLimitSingleType(Long[] answerIds,List<UserSurveyQuestionAnswerDto> total,int limitSingleTimes){
		
		if(StringUtil.isEmpty(answerIds)){
			return false;
		}
		for(Long answerId : answerIds){
			if(CollectionUtils.isNotEmpty(total)){
				for(UserSurveyQuestionAnswerDto dto : total){
					if(dto.getAnswerId().longValue() == answerId.longValue() && dto.getTotal().intValue() >= limitSingleTimes){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	private UserSurveyHistory cover(UserSurveyHistoryDto userSurveyHistoryDto){
		
		String sessionId = userSurveyHistoryDto.getSessionCode();
		String token = userSurveyHistoryDto.getToken();
		
		UserSession userSession = null;
		
		if(!StringUtil.isEmpty(token)){
			userSession = userSessionManager.getByToken(token);
		}else if(!StringUtil.isEmpty(sessionId)){
			userSession = userSessionManager.getTourist(sessionId);
		}
		
		if(null == userSession){
			UserAccount userAccount = userAccountManager.createTourist(userSurveyHistoryDto.getAppId(), sessionId);
			userSurveyHistoryDto.setPhone(userAccount.getTelephone());
			userSurveyHistoryDto.setUserId(userAccount.getId());
		}else{
			userSurveyHistoryDto.setPhone(userSession.getPhoneCode());
			userSurveyHistoryDto.setToken(userSession.getToken());
			userSurveyHistoryDto.setUserId(userSession.getUserId());
		}
		userSurveyHistoryDto.setCreateTime(Dates.now());
		userSurveyHistoryDto.setSource("APP");
		UserSurveyHistory userSurveyHistory = new UserSurveyHistory();
		BeanUtils.copyProperties(userSurveyHistoryDto, userSurveyHistory);
		
		return userSurveyHistory;
	}
}
