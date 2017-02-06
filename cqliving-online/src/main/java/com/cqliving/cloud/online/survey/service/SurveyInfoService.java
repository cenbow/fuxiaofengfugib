package com.cqliving.cloud.online.survey.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.survey.domain.SurveyInfo;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.cloud.online.survey.dto.SurveyInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 调研表 Service
 * Date: 2016-04-15 09:46:50
 * @author Code Generator
 */
public interface SurveyInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<SurveyInfo>> queryForPage(PageInfo<SurveyInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<SurveyInfo> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(SurveyInfo domain);
	/** @author Code Generator *****end*****/
	public Response<SurveyInfo> saveVote(SurveyInfo surveyInfo,SurveyQuestionAnswer[] surveyAnswers);
	public Response<SurveyInfo> saveSurvey(SurveyInfo surveyInfo,Long[] questionIds);
	//保存打雷
	public Response<SurveyInfo> saveArena(SurveyInfo surveyInfo,List<SurveyQuestionAnswer> surveyAnswers);
	
	//根据新闻内容ID查找调研内容
	public Response<SurveyInfoDto> findByInfoContentId(Long infoContentId);
}
