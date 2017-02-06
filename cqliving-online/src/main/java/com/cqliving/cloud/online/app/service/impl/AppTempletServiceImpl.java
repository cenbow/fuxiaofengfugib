package com.cqliving.cloud.online.app.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AppTemplet;
import com.cqliving.cloud.online.app.manager.AppTempletManager;
import com.cqliving.cloud.online.app.service.AppTempletService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appTempletService")
@ServiceHandleMapping(managerClass = AppTempletManager.class)
public class AppTempletServiceImpl implements AppTempletService {
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端模板表失败")})
	public Response<PageInfo<AppTemplet>> queryForPage(PageInfo<AppTemplet> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询客户端模板表失败")})
	public Response<AppTemplet> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端模板表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端模板表失败")})
	public Response<Void> save(AppTemplet appTemplet) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="deleteTemplet",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端模板表失败")})
	public Response<Void> deleteTemplet(Long[] ids) {
	    return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="saveTemplet",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端模板表失败")})
	public Response<Void> saveTemplet(AppTemplet appTemplet) {
	    return null;
	}
}
