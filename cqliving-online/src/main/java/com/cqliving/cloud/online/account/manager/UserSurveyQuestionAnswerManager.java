package com.cqliving.cloud.online.account.manager;

import com.cqliving.cloud.online.account.domain.UserSurveyQuestionAnswer;
import com.cqliving.cloud.online.account.dto.UserSurveyHistoryDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 用户调研问卷答案表 Manager
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserSurveyQuestionAnswerManager extends EntityService<UserSurveyQuestionAnswer> {

	public Long[] saveUserSurveyQuestionAnswer(UserSurveyHistoryDto userSurveyHistoryDto,
			UserSurveyQuestionAnswer userSurveyQuestionAnswer,Long[] answerIds);
}
