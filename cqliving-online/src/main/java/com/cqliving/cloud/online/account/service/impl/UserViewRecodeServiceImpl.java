package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserViewRecode;
import com.cqliving.cloud.online.account.manager.UserViewRecodeManager;
import com.cqliving.cloud.online.account.service.UserViewRecodeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userViewRecodeService")
@ServiceHandleMapping(managerClass = UserViewRecodeManager.class)
public class UserViewRecodeServiceImpl implements UserViewRecodeService {

	//private static final Logger logger = LoggerFactory.getLogger(UserViewRecodeServiceImpl.class);
	
	@Autowired
	private UserViewRecodeManager userViewRecodeManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户浏览记录表失败")})
	public Response<PageInfo<UserViewRecode>> queryForPage(PageInfo<UserViewRecode> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户浏览记录表失败")})
	public Response<UserViewRecode> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户浏览记录表失败")})
	public Response<Void> save(UserViewRecode userViewRecode) {
		return null;
	}
}
