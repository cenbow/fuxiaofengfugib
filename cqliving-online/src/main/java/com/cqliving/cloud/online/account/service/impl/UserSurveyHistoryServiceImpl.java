package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserSurveyHistory;
import com.cqliving.cloud.online.account.manager.UserSurveyHistoryManager;
import com.cqliving.cloud.online.account.service.UserSurveyHistoryService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userSurveyHistoryService")
@ServiceHandleMapping(managerClass = UserSurveyHistoryManager.class)
public class UserSurveyHistoryServiceImpl implements UserSurveyHistoryService {

	//private static final Logger logger = LoggerFactory.getLogger(UserSurveyHistoryServiceImpl.class);
	
	@Autowired
	private UserSurveyHistoryManager userSurveyHistoryManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户参与调研历史表失败")})
	public Response<PageInfo<UserSurveyHistory>> queryForPage(PageInfo<UserSurveyHistory> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户参与调研历史表失败")})
	public Response<UserSurveyHistory> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户参与调研历史表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户参与调研历史表失败")})
	public Response<Void> save(UserSurveyHistory userSurveyHistory) {
		return null;
	}
}
