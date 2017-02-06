package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserSurveyQuestionAnswer;
import com.cqliving.cloud.online.account.dto.UserSurveyHistoryDto;
import com.cqliving.cloud.online.account.manager.UserSurveyQuestionAnswerManager;
import com.cqliving.cloud.online.account.service.UserSurveyQuestionAnswerService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userSurveyQuestionAnswerService")
@ServiceHandleMapping(managerClass = UserSurveyQuestionAnswerManager.class)
public class UserSurveyQuestionAnswerServiceImpl implements UserSurveyQuestionAnswerService {

	//private static final Logger logger = LoggerFactory.getLogger(UserSurveyQuestionAnswerServiceImpl.class);
	
	@Autowired
	private UserSurveyQuestionAnswerManager userSurveyQuestionAnswerManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户调研问卷答案表失败")})
	public Response<PageInfo<UserSurveyQuestionAnswer>> queryForPage(PageInfo<UserSurveyQuestionAnswer> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户调研问卷答案表失败")})
	public Response<UserSurveyQuestionAnswer> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户调研问卷答案表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户调研问卷答案表失败")})
	public Response<Void> save(UserSurveyQuestionAnswer userSurveyQuestionAnswer) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserSurveyQuestionAnswerService#saveUserSurveyQuestionAnswer(com.cqliving.cloud.online.account.domain.UserSurveyHistory, com.cqliving.cloud.online.account.domain.UserSurveyQuestionAnswer)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Long[]> saveUserSurveyQuestionAnswer(UserSurveyHistoryDto userSurveyHistoryDto,
			UserSurveyQuestionAnswer userSurveyQuestionAnswer,Long[] answerIds) {
		return null;
	}
}
