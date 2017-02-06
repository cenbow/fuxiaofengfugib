package com.cqliving.cloud.online.survey.service;

import java.util.Map;

import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswerAttach;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 调研问题表答案附加属性 Service
 * Date: 2016-04-15 09:46:59
 * @author Code Generator
 */
public interface SurveyQuestionAnswerAttachService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<SurveyQuestionAnswerAttach>> queryForPage(PageInfo<SurveyQuestionAnswerAttach> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<SurveyQuestionAnswerAttach> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(SurveyQuestionAnswerAttach domain);
	/** @author Code Generator *****end*****/
	
}
