package com.cqliving.cloud.online.survey.manager.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.dao.InfoClassifyDao;
import com.cqliving.cloud.online.info.dao.InformationContentDao;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.survey.dao.SurveyInfoDao;
import com.cqliving.cloud.online.survey.dao.SurveyQuestionAnswerDao;
import com.cqliving.cloud.online.survey.dao.SurveyQuestionDao;
import com.cqliving.cloud.online.survey.domain.SurveyInfo;
import com.cqliving.cloud.online.survey.domain.SurveyQuestion;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.cloud.online.survey.dto.SurveyInfoDto;
import com.cqliving.cloud.online.survey.manager.SurveyInfoManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.StringUtil;

@Service("surveyInfoManager")
public class SurveyInfoManagerImpl extends EntityServiceImpl<SurveyInfo, SurveyInfoDao> implements SurveyInfoManager {

	@Autowired
	InformationContentDao informationContentDao;
	@Autowired
	SurveyQuestionDao surveyQuestionDao;
	@Autowired
	SurveyQuestionAnswerDao surveyQuestionAnswerDao;
	@Autowired
	InfoClassifyDao infoClassifyDao;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.manager.SurveyInfoManager#saveVote(com.cqliving.cloud.online.survey.domain.SurveyInfo)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public SurveyInfo saveVote(SurveyInfo surveyInfo,SurveyQuestionAnswer[] surveyAnswers) {
		
		if(null == surveyInfo || StringUtil.isEmpty(surveyAnswers))
			throw new BusinessException(ErrorCodes.FAILURE,"投票信息及投票项不能为空");
		InformationContent informationContent =  this.saveBySurveyInfo(surveyInfo,InformationContent.TYPE3);
		surveyInfo.setInformationContentId(informationContent.getId());
		String title = surveyInfo.getTitle();
		surveyInfo = coverLimitTimes(surveyInfo);
    	Byte quesType = surveyInfo.getQuesType();
    	surveyInfo.setStatus(SurveyInfo.STATUS3);
		surveyInfo = this.getEntityDao().saveAndFlush(surveyInfo);
		//保存问题
		surveyInfo.setQuesType(quesType);
		SurveyQuestion surveyQuestion = this.saveSurveyQuestion(surveyInfo,title);
		//保存答案
		for(SurveyQuestionAnswer surveyQuestionAnswer : surveyAnswers){
			surveyQuestionAnswer.setQuestionId(surveyQuestion.getId());
			surveyQuestionAnswer.setStatus(SurveyQuestionAnswer.STATUS3);
			surveyQuestionAnswerDao.saveAndFlush(surveyQuestionAnswer);
		}
		//修改为不能推荐到微信小程序
		infoClassifyDao.updateIsViewWechat(informationContent.getId(), InfoClassify.ISVIEWWECHAT0);
		return surveyInfo;
	}
	
	private SurveyInfo coverLimitTimes(SurveyInfo surveyInfo){
		
		String title = surveyInfo.getTitle();
		if(!StringUtil.isEmpty(title) && title.length() >=33){
			surveyInfo.setTitle(title.substring(0, 32));
		}
		Integer limitRateTimes = surveyInfo.getLimitRateTimes();
    	if(null == limitRateTimes) surveyInfo.setLimitRateTimes(-1);
    	Integer limitRuleTimes = surveyInfo.getLimitRuleTimes();
    	if(null == limitRuleTimes)surveyInfo.setLimitRuleTimes(-1);
    	Integer limitSingleTimes = surveyInfo.getLimitSingleTimes();
    	if(null == limitSingleTimes)surveyInfo.setLimitSingleTimes(-1);
    	if(null == surveyInfo.getLimitRateType())surveyInfo.setLimitRateType((byte)-1);
    	if(null == surveyInfo.getLimitRuleType())surveyInfo.setLimitRuleType((byte)-1);
    	if(null == surveyInfo.getLimitSingleType())surveyInfo.setLimitSingleType((byte)-1);
    	return surveyInfo;
	}
	
	
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private SurveyQuestion saveSurveyQuestion(SurveyInfo surveyInfo,String title){
		
		SurveyQuestion surveyQuestion = null;
		//先看看有没有问题，有问题就是修改
		List<SurveyQuestion> list = surveyQuestionDao.findBySurveyId(surveyInfo.getId());
		if(!CollectionUtils.isEmpty(list)){
			surveyQuestion= list.get(0);	
		}else{
			surveyQuestion = new SurveyQuestion();
			Date now = Dates.now();
			surveyQuestion.setStatus(SurveyQuestion.STATUS3);
			surveyQuestion.setAppId(surveyInfo.getAppId());
			surveyQuestion.setCreateTime(now);
			surveyQuestion.setLimitCount(-1);
			surveyQuestion.setSortNo(1);
			surveyQuestion.setSurveyId(surveyInfo.getId());
		}
		if(!StringUtil.isEmpty(title) && title.length() >= 330)title = title.substring(0,329);
		surveyQuestion.setQuestion(title);
		surveyQuestion.setType(null == surveyInfo.getQuesType() ? 0 : surveyInfo.getQuesType());
		surveyQuestion = surveyQuestionDao.saveAndFlush(surveyQuestion);
		
		return surveyQuestion;
		
	}
	
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private InformationContent saveBySurveyInfo(SurveyInfo surveyInfo,Byte type){
		InformationContent informationContent = new InformationContent();
		informationContent.setStatus(InformationContent.STATUS3);
		informationContent.setId(surveyInfo.getInformationContentId());
		informationContent.setAppId(surveyInfo.getAppId());
		informationContent.setInformationId(surveyInfo.getInformationId());
		String title = surveyInfo.getTitle();
		if(!StringUtil.isEmpty(title) && title.length() >=20)title = title.substring(0,19);
		informationContent.setTitle(title);
		informationContent.setType(type);
		List<InformationContent> ics = informationContentDao.findByInformationId(surveyInfo.getInformationId());
		int sortNo = 0;
		if(!CollectionUtils.isEmpty(ics))sortNo = ics.size();
		sortNo += 1 ;
		informationContent.setSortNo(sortNo);
		return informationContentDao.saveAndFlush(informationContent);
	}
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.manager.SurveyInfoManager#saveSurvey(com.cqliving.cloud.online.survey.domain.SurveyInfo, java.lang.Long[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public SurveyInfo saveSurvey(SurveyInfo surveyInfo, Long[] questionIds) {
		
		surveyInfo.setType(SurveyInfo.TYPE0);
		surveyInfo = coverLimitTimes(surveyInfo);
		
		//保存内容
        InformationContent informationContent =  this.saveBySurveyInfo(surveyInfo,InformationContent.TYPE5);
		surveyInfo.setInformationContentId(informationContent.getId());
		surveyInfo = this.getEntityDao().saveAndFlush(surveyInfo);
		
		//修改问题的surveyId
		surveyQuestionDao.updateSurveyIdAppId(CollectionUtils.arrayToList(questionIds), surveyInfo.getId(),surveyInfo.getAppId());
		return surveyInfo;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.manager.SurveyInfoManager#saveArena(javax.servlet.http.HttpServletRequest, com.cqliving.cloud.online.survey.domain.SurveyInfo, java.util.List)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public SurveyInfo saveArena(SurveyInfo surveyInfo,List<SurveyQuestionAnswer> surveyAnswers) {
		
		surveyInfo.setType(SurveyInfo.TYPE2);
		String title = surveyInfo.getTitle();
		surveyInfo = coverLimitTimes(surveyInfo);
		//保存内容
        InformationContent informationContent =  this.saveBySurveyInfo(surveyInfo,InformationContent.TYPE4);
		surveyInfo.setInformationContentId(informationContent.getId());
		surveyInfo.setStatus(SurveyInfo.STATUS3);
		surveyInfo = this.getEntityDao().saveAndFlush(surveyInfo);
		SurveyQuestion surveyQuestion = this.saveSurveyQuestion(surveyInfo,title);
		
		//保存答案
		for(SurveyQuestionAnswer surveyQuestionAnswer : surveyAnswers){
			surveyQuestionAnswer.setQuestionId(surveyQuestion.getId());
			surveyQuestionAnswer.setStatus(SurveyQuestionAnswer.STATUS3);
			surveyQuestionAnswer.setActValue(null == surveyQuestionAnswer.getActValue() ? 0 : surveyQuestionAnswer.getActValue());
			surveyQuestionAnswerDao.saveAndFlush(surveyQuestionAnswer);
		}
		//修改为不能推荐到微信小程序
		infoClassifyDao.updateIsViewWechat(informationContent.getId(), InfoClassify.ISVIEWWECHAT0);
		return surveyInfo;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.manager.SurveyInfoManager#findByInfoId(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public SurveyInfoDto findByInfoContentId(Long infoContentId) {
		if(null == infoContentId || 0 == infoContentId)return null;
		SurveyInfoDto dto = this.getEntityDao().findByInfoContentId(infoContentId);
		/*if(null == dto){
			informationContentDao.updateStatus(infoContentId,InformationContent.STATUS99);
		}*/
		return dto;
	}

}
