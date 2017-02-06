package com.cqliving.cloud.online.app.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AppVersion;
import com.cqliving.cloud.online.app.dto.AppVersionDto;
import com.cqliving.cloud.online.app.manager.AppVersionManager;
import com.cqliving.cloud.online.app.service.AppVersionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appVersionService")
@ServiceHandleMapping(managerClass = AppVersionManager.class)
public class AppVersionServiceImpl implements AppVersionService {

	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端版本表失败")})
	public Response<PageInfo<AppVersion>> queryForPage(PageInfo<AppVersion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询客户端版本表失败")})
	public Response<AppVersion> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="getById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询客户端版本信息失败")})
	public Response<AppVersionDto> getById(Long id) {
	    return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端版本表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端版本表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="saveVersion",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端版本表失败")})
	public Response<Void> save(AppVersion appVersion) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="queryByPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端版本表失败")})
	public Response<PageInfo<AppVersionDto>> queryByPage(PageInfo<AppVersionDto> pageInfo, Map<String, Object> conditions,Map<String, Boolean> orders) {
	    return null;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="updateVersion",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改版本信息失败")})
    public Response<Void> update(AppVersion domain) {
        return null;
    }
}