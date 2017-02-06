package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserOtherAccount;
import com.cqliving.cloud.online.account.manager.UserOtherAccountManager;
import com.cqliving.cloud.online.account.service.UserOtherAccountService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userOtherAccountService")
@ServiceHandleMapping(managerClass = UserOtherAccountManager.class)
public class UserOtherAccountServiceImpl implements UserOtherAccountService {

	//private static final Logger logger = LoggerFactory.getLogger(UserOtherAccountServiceImpl.class);
	
	@Autowired
	private UserOtherAccountManager userOtherAccountManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户其他平台账号表失败")})
	public Response<PageInfo<UserOtherAccount>> queryForPage(PageInfo<UserOtherAccount> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户其他平台账号表失败")})
	public Response<UserOtherAccount> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户其他平台账号表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户其他平台账号表失败")})
	public Response<Void> save(UserOtherAccount userOtherAccount) {
		return null;
	}
}
