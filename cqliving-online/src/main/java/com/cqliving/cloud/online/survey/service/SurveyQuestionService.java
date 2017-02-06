package com.cqliving.cloud.online.survey.service;

import java.util.Map;

import com.cqliving.cloud.online.survey.domain.SurveyQuestion;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 调研问题表 Service
 * Date: 2016-04-15 09:46:53
 * @author Code Generator
 */
public interface SurveyQuestionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<SurveyQuestion>> queryForPage(PageInfo<SurveyQuestion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<SurveyQuestion> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(SurveyQuestion domain);
	/** @author Code Generator *****end*****/
	
	public Response<Void> deleteSurveyQuestion(Long id);
}
