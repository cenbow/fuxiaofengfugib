package com.cqliving.cloud.online.app.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.cloud.online.app.manager.AppMarketplaceResourceManager;
import com.cqliving.cloud.online.app.service.AppMarketplaceResourceService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appMarketplaceResourceService")
@ServiceHandleMapping(managerClass = AppMarketplaceResourceManager.class)
public class AppMarketplaceResourceServiceImpl implements AppMarketplaceResourceService {

	private static final Logger logger = LoggerFactory.getLogger(AppMarketplaceResourceServiceImpl.class);
	
	@Autowired
	private AppMarketplaceResourceManager appMarketplaceResourceManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端发布市场资源表失败")})
	public Response<PageInfo<AppMarketplaceResource>> queryForPage(PageInfo<AppMarketplaceResource> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询客户端发布市场资源表失败")})
	public Response<AppMarketplaceResource> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端发布市场资源表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端发布市场资源表失败")})
	public Response<Void> save(AppMarketplaceResource appMarketplaceResource) {
		return null;
	}

	@Override
	public Response<List<AppMarketplaceResource>> queryForList(Map<String, Object> map, Map<String, Boolean> sortMap) {
		Response<List<AppMarketplaceResource>> response = Response.newInstance();
		try {
			List<AppMarketplaceResource> data = appMarketplaceResourceManager.query(map, sortMap);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取客户端资源失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取客户端资源失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取客户端资源失败");
		}
		return response;
	}
	
	@Override
    @ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端发布市场资源表失败")})
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
    public Response<Void> updateSortNo(Integer sortNo, Long id) {
        Response<Void> response = Response.newInstance();
        try {
            appMarketplaceResourceManager.updateSortNo(sortNo,id);
        } catch (BusinessException e) {
            logger.error("修改排序号失败失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("修改排序号失败失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("修改排序号失败失败");
        }
        return response;
    }
}