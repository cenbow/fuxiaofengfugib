package com.cqliving.cloud.online.config.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.ConfigPermission;
import com.cqliving.cloud.online.config.manager.ConfigPermissionManager;
import com.cqliving.cloud.online.config.service.ConfigPermissionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("configPermissionService")
@ServiceHandleMapping(managerClass = ConfigPermissionManager.class)
public class ConfigPermissionServiceImpl implements ConfigPermissionService {

	private static final Logger logger = LoggerFactory.getLogger(ConfigPermissionServiceImpl.class);
	
	@Autowired
	private ConfigPermissionManager configPermissionManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询前端资源权限，该表数据需要初始化好失败")})
	public Response<PageInfo<ConfigPermission>> queryForPage(PageInfo<ConfigPermission> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询前端资源权限，该表数据需要初始化好失败")})
	public Response<ConfigPermission> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除前端资源权限，该表数据需要初始化好失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存前端资源权限，该表数据需要初始化好失败")})
	public Response<Void> save(ConfigPermission configPermission) {
		return null;
	}
}
