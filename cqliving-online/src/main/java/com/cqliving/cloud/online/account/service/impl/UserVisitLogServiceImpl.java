package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserVisitLog;
import com.cqliving.cloud.online.account.manager.UserVisitLogManager;
import com.cqliving.cloud.online.account.service.UserVisitLogService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userVisitLogService")
@ServiceHandleMapping(managerClass = UserVisitLogManager.class)
public class UserVisitLogServiceImpl implements UserVisitLogService {

	//private static final Logger logger = LoggerFactory.getLogger(UserVisitLogServiceImpl.class);
	
	@Autowired
	private UserVisitLogManager userVisitLogManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户打开日志表失败")})
	public Response<PageInfo<UserVisitLog>> queryForPage(PageInfo<UserVisitLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户打开日志表失败")})
	public Response<UserVisitLog> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户打开日志表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户打开日志表失败")})
	public Response<Void> save(UserVisitLog userVisitLog) {
		return null;
	}
}
