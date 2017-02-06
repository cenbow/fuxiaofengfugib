package com.cqliving.cloud.online.survey.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.survey.dao.SurveyQuestionAnswerAttachDao;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswerAttach;
import com.cqliving.cloud.online.survey.manager.SurveyQuestionAnswerAttachManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("surveyQuestionAnswerAttachManager")
public class SurveyQuestionAnswerAttachManagerImpl extends EntityServiceImpl<SurveyQuestionAnswerAttach, SurveyQuestionAnswerAttachDao> implements SurveyQuestionAnswerAttachManager {

}
