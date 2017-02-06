package com.cqliving.cloud.online.survey.manager;

import java.util.List;

import com.cqliving.cloud.online.survey.domain.SurveyInfo;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.cloud.online.survey.dto.SurveyInfoDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 调研表 Manager
 * Date: 2016-04-15 09:46:50
 * @author Code Generator
 */
public interface SurveyInfoManager extends EntityService<SurveyInfo> {

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年5月9日
	 * @param surveyInfo
	 * @param surveyAnswers
	 * @return
	 */
	public SurveyInfo saveVote(SurveyInfo surveyInfo,SurveyQuestionAnswer[] surveyAnswers);
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年5月9日
	 * @param surveyInfo
	 * @param questionIds
	 * @return
	 */
	public SurveyInfo saveSurvey(SurveyInfo surveyInfo,Long[] questionIds);
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年5月9日
	 * @param request
	 * @param surveyInfo
	 * @param surveyAnswers
	 * @return
	 */
	public SurveyInfo saveArena(SurveyInfo surveyInfo,List<SurveyQuestionAnswer> surveyAnswers);
	
	public SurveyInfoDto findByInfoContentId(Long infoContentId);
}
