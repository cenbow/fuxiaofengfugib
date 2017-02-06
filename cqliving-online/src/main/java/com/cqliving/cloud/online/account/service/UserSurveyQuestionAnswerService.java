package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserSurveyQuestionAnswer;
import com.cqliving.cloud.online.account.dto.UserSurveyHistoryDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户调研问卷答案表 Service
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserSurveyQuestionAnswerService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserSurveyQuestionAnswer>> queryForPage(PageInfo<UserSurveyQuestionAnswer> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserSurveyQuestionAnswer> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(UserSurveyQuestionAnswer domain);
	/** @author Code Generator *****end*****/
	//保存用户的投票打擂调研信息
	public Response<Long[]> saveUserSurveyQuestionAnswer(UserSurveyHistoryDto userSurveyHistoryDto,UserSurveyQuestionAnswer userSurveyQuestionAnswer,Long[] answerIds);
}
