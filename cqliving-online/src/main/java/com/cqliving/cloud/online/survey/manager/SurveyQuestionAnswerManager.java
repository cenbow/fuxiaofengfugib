package com.cqliving.cloud.online.survey.manager;

import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.framework.common.service.EntityService;

/**
 * 问题表答案表 Manager
 * Date: 2016-04-15 09:46:56
 * @author Code Generator
 */
public interface SurveyQuestionAnswerManager extends EntityService<SurveyQuestionAnswer> {

	public void updateStatus(Long id, Byte status);
}
