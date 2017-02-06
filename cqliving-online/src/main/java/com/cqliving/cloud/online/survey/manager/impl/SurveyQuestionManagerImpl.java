package com.cqliving.cloud.online.survey.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.survey.dao.SurveyQuestionAnswerDao;
import com.cqliving.cloud.online.survey.dao.SurveyQuestionDao;
import com.cqliving.cloud.online.survey.domain.SurveyQuestion;
import com.cqliving.cloud.online.survey.manager.SurveyQuestionManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("surveyQuestionManager")
public class SurveyQuestionManagerImpl extends EntityServiceImpl<SurveyQuestion, SurveyQuestionDao> implements SurveyQuestionManager {

	@Autowired
	SurveyQuestionAnswerDao surveyQuestionAnswerDao;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.manager.SurveyQuestionManager#deleteSurveyQuestion(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void deleteSurveyQuestion(Long id) {
		
		this.removeById(id);
		surveyQuestionAnswerDao.deleteByQuesId(id);
	}

}
