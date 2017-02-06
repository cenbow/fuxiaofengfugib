package com.cqliving.cloud.online.account.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.dao.UserActTestDao;
import com.cqliving.cloud.online.account.domain.UserActTest;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserActTestManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.dao.ActTestClassifyDao;
import com.cqliving.cloud.online.act.dao.ActTestDao;
import com.cqliving.cloud.online.act.dao.ActTestQuestionDao;
import com.cqliving.cloud.online.act.dao.UserActTestClassifyHistoryDao;
import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.cloud.online.act.domain.ActTestClassify;
import com.cqliving.cloud.online.act.domain.ActTestQuestionDto;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.cloud.online.act.domain.UserActTestClassifyHistory;
import com.cqliving.cloud.online.act.manager.UserActTestClassifyManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("userActTestManager")
public class UserActTestManagerImpl extends EntityServiceImpl<UserActTest, UserActTestDao> implements UserActTestManager {

	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private ActTestDao actTestDao;
	@Autowired
	private ActTestQuestionDao actTestQuestionDao;
	@Autowired
	private ActTestClassifyDao actTestClassifyDao;
	@Autowired
	private UserActTestClassifyManager userActTestClassifyManager;
	@Autowired
	private UserActTestClassifyHistoryDao userActTestClassifyHistoryDao;
	
	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public UserActTestClassify save(Long appId, String sessionId, String token, Long actTestClassifyId, Long startTime, Integer isFinish, Long[] questionIds, String[] answerIds, Long[] inputQuestionIds, String[] inputAnswerValues) {
		UserSession userSession = userSessionManager.get(sessionId, token);
		ActTest actTest = actTestDao.getByActTestClassify(actTestClassifyId);
		if(actTest == null){
			throw new BusinessException(-99, "活动不存在或已经下线。");
		}
		//验证是否许需要登录才能答题
		if(StringUtils.isBlank(userSession.getToken()) && ActTest.LOGGEDSTATUS1.equals(actTest.getLoggedStatus())){
			throw new BusinessException(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(startTime);
		
		UserActTestClassifyHistory userActTestClassifyHistory = new UserActTestClassifyHistory();
		
		userActTestClassifyHistory.setActTestClassifyId(actTestClassifyId);
		userActTestClassifyHistory.setActTestId(actTest.getId());
		userActTestClassifyHistory.setStartTime(c.getTime());
		userActTestClassifyHistory.setCreateTime(date);
		userActTestClassifyHistory.setUserId(userSession.getUserId());
		userActTestClassifyHistory.setIsFinish(UserActTestClassifyHistory.ISFINISH1.equals(Byte.parseByte(isFinish+"")) ? UserActTestClassifyHistory.ISFINISH1 : UserActTestClassifyHistory.ISFINISH0);
		userActTestClassifyHistory.setScore(0);
		userActTestClassifyHistoryDao.save(userActTestClassifyHistory);
		
		//是否设置了分值
		ActTestClassify actTestClassify = actTestClassifyDao.get(actTestClassifyId);
		
		//获得正确答案的集合
		List<ActTestQuestionDto> rightAnswerList = actTestQuestionDao.getQuestionRightAnswer(actTestClassifyId);
		//组合一个正确答案的map，主要是要处理多项选择的答题
		Map<Long, List<Long>> rightAnswerMap = Maps.newHashMap();
		Map<Long, Integer> scoreMap = Maps.newHashMap();
		List<Long> tmpList = null;
		for(ActTestQuestionDto dto : rightAnswerList){
			if(rightAnswerMap.containsKey(dto.getId())){
				tmpList = rightAnswerMap.get(dto.getId());
			}else{
				tmpList = Lists.newArrayList();
				scoreMap.put(dto.getId(), dto.getScore());
			}
			tmpList.add(dto.getAnswerId());
			rightAnswerMap.put(dto.getId(), tmpList);
		}
		
		UserActTest userActTest = null;
		List<UserActTest> userActTestList = Lists.newArrayList();
		int len = 0; 
		String[] answers = null;
		boolean isRight = false;//问题是否回答正确
		Integer errorCount = 0,//错误题目数
				rightScore = 0,//如果是问题按分值算就是正确的分数，如果按正确率算就是正确的题目数
				totalCount = 0;//总题目数
		if(questionIds != null && answerIds != null && questionIds.length == answerIds.length){//选择类型的题
			len = questionIds.length;
			totalCount = len;
			for(int i = 0; i < len; i ++){
				answers = null;
				if(StringUtils.isNotBlank(answerIds[i]) && answerIds[i].indexOf("a") != -1){//是多项选择
					answers = answerIds[i].split("a");
				}else if(StringUtils.isNotBlank(answerIds[i])){//是单项选择
					answers = new String[]{answerIds[i]};
				}
				
				isRight = false;
				if(answers != null){//说明这道题用户选择了的，如果为null则表示用户没做这道题
					//验证用户是否回答正确
					isRight = validateAnswerIsRight(rightAnswerMap.get(questionIds[i]), answers);
				}
				if(isRight){//正确统计分数
					if(ActTestClassify.ISSETSCORE1.equals(actTestClassify.getIsSetScore())){//设置分值
						rightScore += scoreMap.get(questionIds[i]);
					}else{//正确率
						rightScore ++;
					}
				}else{
					errorCount ++;
				}
				
				//保存答题记录
				if(answers != null){
					for(String str: answers){//保存多选题
						userActTest = new UserActTest();
						userActTest.setActTestQuestionId(questionIds[i]);
						try {
							userActTest.setActTestAnswerId(Long.parseLong(str));
						} catch (NumberFormatException e) {
							userActTest.setActTestAnswerId(null);
						}
						userActTest.setCreateTime(date);
						userActTest.setTestClassifyHistoryId(userActTestClassifyHistory.getId());
						userActTest.setUserId(userSession.getUserId());
						userActTestList.add(userActTest);
					}
				}else{
					userActTest = new UserActTest();
					userActTest.setActTestQuestionId(questionIds[i]);
					userActTest.setActTestAnswerId(null);
					userActTest.setCreateTime(date);
					userActTest.setTestClassifyHistoryId(userActTestClassifyHistory.getId());
					userActTest.setUserId(userSession.getUserId());
					userActTestList.add(userActTest);
				}
			}
		}
		if(inputQuestionIds != null && inputAnswerValues != null && inputQuestionIds.length == inputAnswerValues.length){//文本类型的题
			len = inputQuestionIds.length;
			totalCount += len;
			for(int i = 0; i < len; i ++){
				userActTest = new UserActTest();
				userActTest.setActTestQuestionId(inputQuestionIds[i]);
				userActTest.setAnswerContent(inputAnswerValues[i]);
				userActTest.setCreateTime(date);
				userActTest.setTestClassifyHistoryId(userActTestClassifyHistory.getId());
				userActTest.setUserId(userSession.getUserId());
				userActTestList.add(userActTest);
			}
		}
		if(ActTestClassify.ISSETSCORE0.equals(actTestClassify.getIsSetScore())){//按正确率统计
			if(rightScore > 0 && totalCount > 0 && rightScore <= totalCount){
				rightScore = (int)(rightScore/(totalCount*0.01));
			}else{
				rightScore = 0;
			}
		}
		userActTestClassifyHistory.setScore(rightScore);
		userActTestClassifyHistoryDao.update(userActTestClassifyHistory);
		
		UserActTestClassify userActTestClassify = userActTestClassifyManager.getByWhere(userActTestClassifyHistory.getActTestId(), userActTestClassifyHistory.getActTestClassifyId(), userActTestClassifyHistory.getUserId());
		if(userActTestClassify == null){
			userActTestClassify = new UserActTestClassify();
		}
		userActTestClassify.setActTestId(userActTestClassifyHistory.getActTestId());
		userActTestClassify.setActTestClassifyId(userActTestClassifyHistory.getActTestClassifyId());
		userActTestClassify.setCreateTime(date);
		userActTestClassify.setIsFinish(userActTestClassifyHistory.getIsFinish());
		userActTestClassify.setScore(userActTestClassifyHistory.getScore());
		userActTestClassify.setStartTime(userActTestClassifyHistory.getStartTime());
		userActTestClassify.setTestClassifyHistoryId(userActTestClassifyHistory.getId());
		userActTestClassify.setUserId(userSession.getUserId());
		if(userActTestClassify.getId() != null){// 重新答题
			userActTestClassifyManager.update(userActTestClassify);
		}else{
			userActTestClassifyManager.save(userActTestClassify);
		}
		
		if(userActTestList != null && userActTestList.size() > 0){
			this.getEntityDao().saves(userActTestList);
		}
		return userActTestClassify;
	}
	
	/**
	 * Title:验证用户是否回答正确
	 * <p>Description:只要有一个答案对不上就表示这道题回答错误</p>
	 * @author DeweiLi on 2016年6月28日
	 * @param rightList 正确答案集合
	 * @param answers 用户回答的答案集合
	 * @return 回答正确返回true，错误返回false
	 */
	private boolean validateAnswerIsRight(List<Long> rightList, String[] answers){
		boolean isRight = true,
				xo = false;
		if(rightList == null){
			return false;
		}
		for(Long tmp : rightList){
			xo = false;
			for(String str : answers){
				if(str.equals(tmp + "")){
					xo = true;
					break;
				}
			}
			if(!xo){//只要有一个答案对不上就表示这道题回答错误
				isRight = false;
				break;
			}
		}
		boolean validateError = true;
		for(String str : answers){
			validateError = true;
			for(Long tmp : rightList){
				if(str.equals(tmp + "")){
					validateError = false;
					break ;
				}
			}
			if(validateError){
				isRight = false;
				break;
			}
		}
		return isRight;
	}
}
