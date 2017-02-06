package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserLoginInfo;
import com.cqliving.cloud.online.account.manager.UserLoginInfoManager;
import com.cqliving.cloud.online.account.service.UserLoginInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userLoginInfoService")
@ServiceHandleMapping(managerClass = UserLoginInfoManager.class)
public class UserLoginInfoServiceImpl implements UserLoginInfoService {

	//private static final Logger logger = LoggerFactory.getLogger(UserLoginInfoServiceImpl.class);
	
	@Autowired
	private UserLoginInfoManager userLoginInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户APP登录信息表失败")})
	public Response<PageInfo<UserLoginInfo>> queryForPage(PageInfo<UserLoginInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户APP登录信息表失败")})
	public Response<UserLoginInfo> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户APP登录信息表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户APP登录信息表失败")})
	public Response<Void> save(UserLoginInfo userLoginInfo) {
		return null;
	}
}
