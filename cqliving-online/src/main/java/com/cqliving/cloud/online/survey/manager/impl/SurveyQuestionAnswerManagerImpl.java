package com.cqliving.cloud.online.survey.manager.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.survey.dao.SurveyQuestionAnswerDao;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.cloud.online.survey.manager.SurveyQuestionAnswerManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("surveyQuestionAnswerManager")
public class SurveyQuestionAnswerManagerImpl extends EntityServiceImpl<SurveyQuestionAnswer, SurveyQuestionAnswerDao> implements SurveyQuestionAnswerManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.manager.SurveyQuestionAnswerManager#updateStatus(java.lang.Long, java.lang.Byte)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void updateStatus(Long id, Byte status) {
		
		if(null == id)return;
		this.getEntityDao().updateStatus(id, status);
	}

}
