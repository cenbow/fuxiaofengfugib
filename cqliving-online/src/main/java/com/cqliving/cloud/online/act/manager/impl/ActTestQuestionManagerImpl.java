package com.cqliving.cloud.online.act.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.dao.ActTestAnswerDao;
import com.cqliving.cloud.online.act.dao.ActTestClassifyDao;
import com.cqliving.cloud.online.act.dao.ActTestQuestionDao;
import com.cqliving.cloud.online.act.dao.UserActTestClassifyDao;
import com.cqliving.cloud.online.act.domain.ActTestAnswer;
import com.cqliving.cloud.online.act.domain.ActTestClassify;
import com.cqliving.cloud.online.act.domain.ActTestQuestion;
import com.cqliving.cloud.online.act.domain.ActTestQuestionDto;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.cloud.online.act.dto.ActTestAnswerResult;
import com.cqliving.cloud.online.act.dto.ActTestQuestionDtoResult;
import com.cqliving.cloud.online.act.dto.ActTestQuestionExcelOption;
import com.cqliving.cloud.online.act.dto.ActTestQuestionResult;
import com.cqliving.cloud.online.act.manager.ActTestQuestionManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("actTestQuestionManager")
public class ActTestQuestionManagerImpl extends EntityServiceImpl<ActTestQuestion, ActTestQuestionDao> implements ActTestQuestionManager {

	@Autowired
	private ActTestQuestionDao actTestQuestionDao;
	@Autowired
	private ActTestAnswerDao actTestAnswerDao;
	@Autowired
	private ActTestClassifyDao actTestClassifyDao;
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private UserActTestClassifyDao userActTestClassifyDao;
	
	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void deletes(Long... ids) {
		actTestAnswerDao.deleteByQuestionId(Arrays.asList(ids));
		this.removes(ids);
	}
	
	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void sort(String questionListStr) {
		if(StringUtils.isNotBlank(questionListStr)){
			JSONArray jsonArray = JSONArray.parseArray(questionListStr);
			JSONObject json = null;
			int size = jsonArray.size();
			for(int i = 0; i < size; i ++){
				json = jsonArray.getJSONObject(i);
				this.getEntityDao().sort(json.getLong("id"), json.getLong("classifyId"), json.getInteger("sortNo"));
			}
		}
	}

	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void excelImport(Long classifyId, String content, List<ActTestQuestionExcelOption> list) {
		Date date = new Date();
		
		ActTestQuestion question = null;
		ActTestAnswer answer = null;
		List<ActTestAnswer> answerList = null;
		int questionSortNo = 0,
			answerSortNo = 0;
		

		//修改分类内容
		ActTestClassify actTestClassify = actTestClassifyDao.get(classifyId);
		if(actTestClassify == null){
			throw new BusinessException(-1, "分类信息不存在");
		}
		actTestClassify.setSubject(content);
		actTestClassifyDao.update(actTestClassify);
		
		Map<String, ActTestQuestion> map = Maps.newHashMap();
		for(ActTestQuestionExcelOption data : list){
			if(!map.containsKey(data.getQuestion())){
				question = new ActTestQuestion();
				question.setActTestClassifyId(classifyId);
				question.setQuestion(data.getQuestion());
				question.setType(data.getQuestionType());
				question.setScore(data.getScore());
				question.setSortNo(questionSortNo ++);
				question.setCreateTime(date);
				map.put(data.getQuestion(), question);
			}
		}
		Set<String> keySet = map.keySet();
		for(String key : keySet){
			question = this.save(map.get(key));
			answerList = Lists.newArrayList();
			answerSortNo = 0;
			for(ActTestQuestionExcelOption data : list){
				if(key.equals(data.getQuestion())){
					answer = new ActTestAnswer();
					answer.setActTestQuestionId(question.getId());
					answer.setAnswer(data.getAnswer());
					answer.setAnswerDesc(data.getAnswerDesc());
					answer.setCreateTime(date);
					answer.setType(ActTestAnswer.TYPE0);
					answer.setIsRightAnswer((data.getIsRightAnswer() != null && data.getIsRightAnswer().equals(ActTestAnswer.ISRIGHTANSWER1)) ? ActTestAnswer.ISRIGHTANSWER1 : ActTestAnswer.ISRIGHTANSWER0);
					answer.setSortNo(answerSortNo ++);
					answerList.add(answer);
				}
			}
			actTestAnswerDao.saves(answerList);
		}
	}

	@Override
	public ActTestQuestionDtoResult getQuestionAndAnswer(String sessionId, String token, Long classifyId, Integer type){
		UserSession userSession = userSessionManager.get(sessionId, token);
		//获得用户答题的信息
		UserActTestClassify userActTestClassify = null;
		if(type!=null && type.equals(2)){//查看结果的时候才需要
			userActTestClassify = userActTestClassifyDao.getByClassify(userSession.getUserId(), classifyId);
			if(userActTestClassify == null){
				throw new BusinessException(-99, "无数据");
			}
		}
		List<ActTestQuestionDto> listDto = actTestQuestionDao.getListAndAnswer(classifyId, (type != null && type.equals(1)) ? null : userSession.getUserId());
		if(listDto == null){
			throw new BusinessException(-99, "无数据");
		}
		List<ActTestQuestionResult> resultList = Lists.newArrayList();
		ActTestQuestionResult result = null;
		
		List<ActTestAnswerResult> answerList = null;
		ActTestAnswerResult answerResult = null;
		
		//问题id是key，问题答案信息列表为value
		Map<Long, List<ActTestAnswerResult>> map = Maps.newHashMap();
		
		for(ActTestQuestionDto dto : listDto){
			if(map.containsKey(dto.getId())){
				answerList = map.get(dto.getId());
			}else{
				result = new ActTestQuestionResult();
				result.setQuestionId(dto.getId());
				result.setQuestion(dto.getQuestion());
				result.setImageUrl(dto.getImageUrl());
				result.setType(dto.getType());
				result.setScore(dto.getScore());
				result.setIsRight(true);//先默认用户回答正确，后面处理用户是否回答正确
				resultList.add(result);
				answerList = Lists.newArrayList();
			}
			answerResult = new ActTestAnswerResult();
			answerResult.setId(dto.getAnswerId());
			answerResult.setAnswer(dto.getAnswer());
			answerResult.setAnswerDesc(dto.getAnswerDesc());
			answerResult.setImageUrl(dto.getAnswerImageUrl());
			answerResult.setType(dto.getAnswerType());
			
			answerResult.setIsRightAnswer(dto.getIsRightAnswer());
			answerResult.setUserAnswer(dto.getUserAnswerId() != null);
			answerResult.setAnswerContent(dto.getAnswerContent());
			answerList.add(answerResult);
			map.put(dto.getId(), answerList);
		}
		int errorCount = 0;
		for(ActTestQuestionResult tmp : resultList){
			answerList = map.get(tmp.getQuestionId());
			for(ActTestAnswerResult aList : answerList){
				if(ActTestAnswer.ISRIGHTANSWER1.equals(aList.getIsRightAnswer()) && !aList.getUserAnswer()){//是正确答案，且用户没回答正确就说明这个问题用户回答错误
					tmp.setIsRight(false);//用户回答错误
					errorCount ++;
					break;
				}
				if(!ActTestAnswer.ISRIGHTANSWER1.equals(aList.getIsRightAnswer()) && aList.getUserAnswer()){//是正确答案，且用户没回答正确就说明这个问题用户回答错误
					tmp.setIsRight(false);//用户回答错误
					errorCount ++;
					break;
				}
			}
			tmp.setAnswerList(answerList);
		}
		ActTestQuestionDtoResult rs = new ActTestQuestionDtoResult();
		rs.setQuestionList(resultList);
		rs.setErrorCount(errorCount);
		rs.setStartTime(new Date().getTime());
		
		if(type != null && type.equals(2)){//查看结果的时候才用
			long diff = userActTestClassify.getCreateTime().getTime() - userActTestClassify.getStartTime().getTime();
			long nd = 1000*24*60*60;//一天的毫秒数
			long nh = 1000*60*60;//一小时的毫秒数
			long nm = 1000*60;//一分钟的毫秒数
			diff = diff%nd%nh/nm;//计算差多少分钟
			rs.setUseTime(diff);
			rs.setScore(userActTestClassify.getScore());
		}
		return rs;
	}

	@Override
	public List<ActTestQuestionDto> validateNullAnswer(Long actInfoListId) {
		return this.getEntityDao().validateNullAnswer(actInfoListId);
	}
}
