package com.cqliving.cloud.online.survey.manager;

import com.cqliving.cloud.online.survey.domain.SurveyQuestion;
import com.cqliving.framework.common.service.EntityService;

/**
 * 调研问题表 Manager
 * Date: 2016-04-15 09:46:53
 * @author Code Generator
 */
public interface SurveyQuestionManager extends EntityService<SurveyQuestion> {

	public void deleteSurveyQuestion(Long id);
}
