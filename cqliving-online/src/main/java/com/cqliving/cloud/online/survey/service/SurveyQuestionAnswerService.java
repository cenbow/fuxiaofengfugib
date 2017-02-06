package com.cqliving.cloud.online.survey.service;

import java.util.Map;

import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 问题表答案表 Service
 * Date: 2016-04-15 09:46:56
 * @author Code Generator
 */
public interface SurveyQuestionAnswerService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<SurveyQuestionAnswer>> queryForPage(PageInfo<SurveyQuestionAnswer> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<SurveyQuestionAnswer> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(SurveyQuestionAnswer domain);
	/** @author Code Generator *****end*****/
	public Response<Void> updateStatus(Long id,Byte status);
}
