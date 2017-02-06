package com.cqliving.cloud.online.config.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.config.domain.Permission;
import com.cqliving.cloud.online.config.manager.PermissionManager;
import com.cqliving.cloud.online.config.service.PermissionService;
import com.cqliving.cloud.online.security.dto.SysResTypeDto;
import com.cqliving.cloud.online.security.dto.TypesDto;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("permissionService")
@ServiceHandleMapping(managerClass = PermissionManager.class)
public class PermissionServiceImpl implements PermissionService {

	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	@Autowired
	private PermissionManager permissionManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询前端资源权限，该表数据需要初始化好失败")})
	public Response<PageInfo<Permission>> queryForPage(PageInfo<Permission> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询前端资源权限，该表数据需要初始化好失败")})
	public Response<Permission> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除前端资源权限，该表数据需要初始化好失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存前端资源权限，该表数据需要初始化好失败")})
	public Response<Void> save(Permission permission) {
		return null;
	}
	
	/**
     * 查询所有的资源
     * @Description 
     * @Company 
     * @parameter appId，客户端Id(查询该客户端对应资源的值)
     * @return
     * @author huxiaoping 2016年12月20日下午5:32:44
     */
    @Override
    public Response<List<SysResTypeDto>> findAllPermission(Long appId) {
        Response<List<SysResTypeDto>> response = Response.newInstance();
        try {
            List<SysResTypeDto> data = permissionManager.findAllPermission(appId);
            response.setData(data);
        } catch (BusinessException e) {
            logger.error("查询所有的资源失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("查询所有的资源失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("查询所有的资源失败");
        }
        return response;
    }

    /**
     * 保存对App的授权
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月22日下午5:39:42
     */
    @Override
    public Response<Void> saveAppPermission(Long appId, TypesDto types,Long userId,String userName) {
        Response<Void> response = Response.newInstance();
        try {
            permissionManager.saveAppPermission(appId,types,userId,userName);
        } catch (BusinessException e) {
            logger.error("保存对App的授权失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("保存对App的授权失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("保存对App的授权失败");
        }
        return response;
    }
}
