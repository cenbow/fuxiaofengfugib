package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserActCollecInfo;
import com.cqliving.cloud.online.account.manager.UserActCollecInfoManager;
import com.cqliving.cloud.online.account.service.UserActCollecInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userActCollecInfoService")
@ServiceHandleMapping(managerClass = UserActCollecInfoManager.class)
public class UserActCollecInfoServiceImpl implements UserActCollecInfoService {

	//private static final Logger logger = LoggerFactory.getLogger(UserActCollecInfoServiceImpl.class);
	
	@Autowired
	private UserActCollecInfoManager userActCollecInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户活动信息收集表失败")})
	public Response<PageInfo<UserActCollecInfo>> queryForPage(PageInfo<UserActCollecInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户活动信息收集表失败")})
	public Response<UserActCollecInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户活动信息收集表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户活动信息收集表失败")})
	public Response<Void> save(UserActCollecInfo userActCollecInfo) {
		return null;
	}
}
