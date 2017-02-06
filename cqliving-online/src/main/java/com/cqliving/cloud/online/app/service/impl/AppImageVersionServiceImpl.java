package com.cqliving.cloud.online.app.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AppImageVersion;
import com.cqliving.cloud.online.app.dto.AppImageVersionDto;
import com.cqliving.cloud.online.app.manager.AppImageVersionManager;
import com.cqliving.cloud.online.app.service.AppImageVersionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appImageVersionService")
@ServiceHandleMapping(managerClass = AppImageVersionManager.class)
public class AppImageVersionServiceImpl implements AppImageVersionService {
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端发布广告表失败")})
	public Response<PageInfo<AppImageVersion>> queryForPage(PageInfo<AppImageVersion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询客户端发布广告表失败")})
	public Response<AppImageVersion> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端发布广告表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端发布广告表失败")})
	public Response<Void> save(AppImageVersion appImageVersion) {
		return null;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="queryPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端发布广告表失败")})
    public Response<PageInfo<AppImageVersionDto>> queryPage(PageInfo<AppImageVersionDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap) {
        return null;
    }
    
    @Override
    @ServiceMethodHandle(managerMethodName="saveImageVersion",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端发布广告表失败")})
    public Response<Void> saveImageVersion(AppImageVersion appImageVersion, String loadingUrl, String linkUrl,Integer showTime) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="queryById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询客户端发布广告表失败")})
    public Response<AppImageVersionDto> getById(Long id) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="startUsing",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="启用失败")})
    public Response<Void> startUsing(Long id) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="nonUse",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="停用失败")})
    public Response<Void> nonUse(Long id) {
        return null;
    }
}
