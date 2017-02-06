package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.ErrorLogin;
import com.cqliving.cloud.online.account.manager.ErrorLoginManager;
import com.cqliving.cloud.online.account.service.ErrorLoginService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("errorLoginService")
@ServiceHandleMapping(managerClass = ErrorLoginManager.class)
public class ErrorLoginServiceImpl implements ErrorLoginService {

	//private static final Logger logger = LoggerFactory.getLogger(ErrorLoginServiceImpl.class);
	
	@Autowired
	private ErrorLoginManager errorLoginManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询登录错误日志表失败")})
	public Response<PageInfo<ErrorLogin>> queryForPage(PageInfo<ErrorLogin> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询登录错误日志表失败")})
	public Response<ErrorLogin> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除登录错误日志表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存登录错误日志表失败")})
	public Response<Void> save(ErrorLogin errorLogin) {
		return null;
	}
}
