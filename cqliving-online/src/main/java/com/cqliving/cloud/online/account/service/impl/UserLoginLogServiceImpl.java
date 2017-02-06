package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserLoginLog;
import com.cqliving.cloud.online.account.manager.UserLoginLogManager;
import com.cqliving.cloud.online.account.service.UserLoginLogService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userLoginLogService")
@ServiceHandleMapping(managerClass = UserLoginLogManager.class)
public class UserLoginLogServiceImpl implements UserLoginLogService {

	//private static final Logger logger = LoggerFactory.getLogger(UserLoginLogServiceImpl.class);
	
	@Autowired
	private UserLoginLogManager userLoginLogManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户登录日志表失败")})
	public Response<PageInfo<UserLoginLog>> queryForPage(PageInfo<UserLoginLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户登录日志表失败")})
	public Response<UserLoginLog> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户登录日志表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户登录日志表失败")})
	public Response<Void> save(UserLoginLog userLoginLog) {
		return null;
	}
}
