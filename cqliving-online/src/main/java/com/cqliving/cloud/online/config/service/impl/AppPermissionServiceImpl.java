package com.cqliving.cloud.online.config.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.AppPermission;
import com.cqliving.cloud.online.config.dto.AppPermissionDto;
import com.cqliving.cloud.online.config.manager.AppPermissionManager;
import com.cqliving.cloud.online.config.service.AppPermissionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appPermissionService")
@ServiceHandleMapping(managerClass = AppPermissionManager.class)
public class AppPermissionServiceImpl implements AppPermissionService {

	private static final Logger logger = LoggerFactory.getLogger(AppPermissionServiceImpl.class);
	
	@Autowired
	private AppPermissionManager appPermissionManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端资源权限表失败")})
	public Response<PageInfo<AppPermission>> queryForPage(PageInfo<AppPermission> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询客户端资源权限表失败")})
	public Response<AppPermission> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端资源权限表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端资源权限表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		logger.info("删除操作");
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端资源权限表失败")})
	public Response<Void> save(AppPermission appPermission) {
		return null;
	}

	@Override
	public Response<List<AppPermissionDto>> getDtoOfAll() {
		Response<List<AppPermissionDto>> response = Response.newInstance();
		try {
			List<AppPermissionDto> data = appPermissionManager.getDtoOfAll();
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取客户端资源权限失败:" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取客户端资源权限失败:" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取客户端资源权限失败");
		}
		return response;
	}
	
}