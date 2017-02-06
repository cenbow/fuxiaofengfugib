package com.cqliving.cloud.online.act.manager.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqliving.cloud.online.account.dao.UserActCollecInfoDao;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.dto.UserActCollectInfoDto;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.dao.ActInfoDao;
import com.cqliving.cloud.online.act.dao.ActTestAnswerDao;
import com.cqliving.cloud.online.act.dao.ActTestDao;
import com.cqliving.cloud.online.act.dao.UserActTestClassifyDao;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.cloud.online.act.domain.ActTestAnswer;
import com.cqliving.cloud.online.act.domain.ActTestQuestion;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.cloud.online.act.dto.ActAnswerResult;
import com.cqliving.cloud.online.act.dto.ActCollectInfoResult;
import com.cqliving.cloud.online.act.dto.ActCollectOptionResult;
import com.cqliving.cloud.online.act.dto.ActInfoDto;
import com.cqliving.cloud.online.act.dto.UserActTestClassifyResult;
import com.cqliving.cloud.online.act.manager.ActTestAnswerManager;
import com.cqliving.cloud.online.act.manager.ActTestQuestionManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("actTestAnswerManager")
public class ActTestAnswerManagerImpl extends EntityServiceImpl<ActTestAnswer, ActTestAnswerDao> implements ActTestAnswerManager {
	
	@Autowired
	private ActTestDao actTestDao;
	@Autowired
	private ActInfoDao actInfoDao;
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private UserActCollecInfoDao userActCollecInfoDao;
	@Autowired
	private UserActTestClassifyDao userActTestClassifyDao;
	@Autowired
	private ActTestQuestionManager actTestQuestionManager;
	@Autowired
	private UserAccountManager userAccountManager;
	
	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void sort(String answerListStr) {
		if(StringUtils.isNotBlank(answerListStr)){
			JSONArray jsonArray = JSONArray.parseArray(answerListStr);
			JSONObject json = null;
			int size = jsonArray.size();
			for(int i = 0; i < size; i ++){
				json = jsonArray.getJSONObject(i);
				this.getEntityDao().sort(json.getLong("id"), json.getLong("actTestQuestionId"), json.getInteger("sortNo"));
			}
		}
	}
	
	@Override
	public ActAnswerResult answer(String sessionId, String token, Long actInfoListId, String isLoad){
		/* 获取活动的基本信息 */
		ActInfoDto actInfoDto = actInfoDao.findByActInfoListId(actInfoListId);
		//数据为空 || 该活动不是发布状态 || 不是答题类型的活动 || 不是激活状态
		if(actInfoDto == null || !ActInfo.STATUS3.equals(actInfoDto.getStatus()) || !ActInfoList.TYPE4.equals(actInfoDto.getActType()) || !ActInfoList.STATUS3.equals(Byte.parseByte(actInfoDto.getActStatus()))){
			throw new BusinessException(-99, "对不起，此信息已被删除或不存在。");
		}
		//获取答题的基本信息
		ActTest actTest = actTestDao.getByActListInfo(actInfoListId);
		if(actTest == null){
			throw new BusinessException(-99, "对不起，此信息已被删除或不存在。");
		}
		
		ActAnswerResult result = new ActAnswerResult();
		result.setActInfoId(actInfoDto.getId());
		result.setTitle(actInfoDto.getTitle());
		result.setDigest(actInfoDto.getDigest());
		result.setContent(actInfoDto.getContent());
		if(StringUtils.isNotBlank(actInfoDto.getActImageUrl())){
			String imgs[] = actInfoDto.getActImageUrl().split(",");
			List<String> actImageUrls = Lists.newArrayList();
			for(String str : imgs){
				if(StringUtils.isNotBlank(str))
					actImageUrls.add(str);
			}
			if(actImageUrls.size() > 0)
				result.setActImageUrls(actImageUrls);
		}
		result.setActTestId(actTest.getId());
		result.setStartTime(actTest.getStartTime());
		result.setEndTime(actTest.getEndTime());
		result.setStartTimeStr(DateUtil.format(actTest.getStartTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
		result.setEndTimeStr(DateUtil.format(actTest.getEndTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
		result.setIsLogin(actTest.getLoggedStatus());
		
		if(!"true".equals(isLoad)){//如果不是异步加载收集信息就不用执行后面的了。
		    return result;
		}
		
		/* 获取收集信息和用户已经收集的信息和收集信息选项值 */
		UserSession userSession = userSessionManager.get(sessionId, token);
		if(userSession == null){
			userAccountManager.createTourist(actInfoDto.getAppId(), sessionId);
			userSession = userSessionManager.get(sessionId, token);
		}
		List<UserActCollectInfoDto> userActCollectInfoDtoList = userActCollecInfoDao.getCollectInfoList(userSession.getUserId(), actInfoListId);
		
		ActCollectInfoResult actCollect = null;
		List<ActCollectInfoResult> collectList = Lists.newArrayList();
		Map<Long, List<ActCollectOptionResult>> map = Maps.newLinkedHashMap();
		List<ActCollectOptionResult> tmpList = null;
		ActCollectOptionResult tmp = null;
		Map<Long, ActCollectInfoResult> tmpMap = Maps.newLinkedHashMap();
		for(UserActCollectInfoDto dto : userActCollectInfoDtoList){
			if(dto == null || dto.getActTestCollectId() == null){
				continue;
			}
			if(map.containsKey(dto.getActTestCollectId())){
				tmpList = map.get(dto.getActTestCollectId());
			}else{
				tmpList = Lists.newArrayList();
				actCollect = new ActCollectInfoResult();
				actCollect.setActCollectInfoId(dto.getActCollectInfoId());
				actCollect.setInputValue(dto.getInputValue());
				actCollect.setIsRequired(dto.getIsRequired());
				actCollect.setLength(dto.getLength());
				actCollect.setName(dto.getName());
				actCollect.setType(dto.getType());
//				actCollect.setSelectValueId(dto.getSelectValueId());
				tmpMap.put(dto.getActTestCollectId(), actCollect);
			}
			if(dto.getOptionId() != null){
				tmp = new ActCollectOptionResult();
				tmp.setId(dto.getOptionId());
				tmp.setValue(dto.getOptionValue());
				tmp.setIsDefault(dto.getSelectValueId() != null);
				tmpList.add(0, tmp);
			}
			map.put(dto.getActTestCollectId(), tmpList);
		}
		Set<Long> tmpSet = tmpMap.keySet();
		for(Long log : tmpSet){
			actCollect = tmpMap.get(log);
			actCollect.setActCollectOptionList(map.get(log));
			collectList.add(actCollect);
		}
		result.setActCollectInfoList(collectList);
		
		/* 获得用户最后一次答题信息 */
		UserActTestClassify userActTestClassify = userActTestClassifyDao.getLast(userSession.getUserId(), actInfoListId);
		UserActTestClassifyResult userActTestClassifyResult = null;
		if(userActTestClassify != null && userActTestClassify.getId() != null){
			userActTestClassifyResult = new UserActTestClassifyResult();
			userActTestClassifyResult.setActTestClassifyId(userActTestClassify.getActTestClassifyId());
			userActTestClassifyResult.setActTestId(userActTestClassify.getActTestId());
			userActTestClassifyResult.setIsFinish(userActTestClassify.getIsFinish());
			userActTestClassifyResult.setScore(userActTestClassify.getScore());
			userActTestClassifyResult.setTestClassifyHistoryId(userActTestClassify.getTestClassifyHistoryId());
			//计算两个时间相隔多少分钟
			Long diff = 0L;
			if(userActTestClassify.getCreateTime() != null && userActTestClassify.getStartTime() != null){
				long nd = 1000 * 24 * 60 * 60;
			    long nh = 1000 * 60 * 60;
			    long nm = 1000 * 60;
				diff = (userActTestClassify.getCreateTime().getTime() - userActTestClassify.getStartTime().getTime());
				diff = diff % nd % nh / nm;
			}
			userActTestClassifyResult.setUsedTime(diff);
		}
		result.setUserClassify(userActTestClassifyResult);
		return result;
	}

	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void saveAndModify(ActTestAnswer actTestAnswer) {
		ActTestQuestion actTestQuestion = actTestQuestionManager.get(actTestAnswer.getActTestQuestionId());
		if(actTestQuestion != null && ActTestQuestion.TYPE1.equals(actTestQuestion.getType()) && ActTestAnswer.ISRIGHTANSWER1.equals(actTestAnswer.getIsRightAnswer())){//是单选，且当前保存的答题是正确答案。
			Map<String, Object> map = Maps.newHashMap();
			map.put("EQ_actTestQuestionId", actTestAnswer.getActTestQuestionId());
			map.put("EQ_isRightAnswer", ActTestAnswer.ISRIGHTANSWER1);
			if(actTestAnswer.getId() != null){
				map.put("NOTEQ_id", actTestAnswer.getId());
			}
			List<ActTestAnswer> list = query(map, null);
			if(list != null && list.size() > 0){
				for(ActTestAnswer a : list){
					a.setIsRightAnswer(ActTestAnswer.ISRIGHTANSWER0);
					update(a);
				}
			}
		}
		if(actTestAnswer.getId() != null){
			update(actTestAnswer);
		}else{
			save(actTestAnswer);
		}
	}
}
