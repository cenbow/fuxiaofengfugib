package com.cqliving.cloud.online.app.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.dto.AppColumnsDto;
import com.cqliving.cloud.online.app.manager.AppColumnsManager;
import com.cqliving.cloud.online.app.service.AppColumnsService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.GetColumnsData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appColumnsService")
@ServiceHandleMapping(managerClass = AppColumnsManager.class)
public class AppColumnsServiceImpl implements AppColumnsService {
	
	@Autowired
	private AppColumnsManager appColumnsManager;
	
	private static final Logger logger = LoggerFactory.getLogger(AppColumnsServiceImpl.class);
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端栏目表失败")})
	public Response<PageInfo<AppColumns>> queryForPage(PageInfo<AppColumns> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询客户端栏目表失败")})
	public Response<AppColumns> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端栏目表失败")})
	public Response<Void> delete(AppColumns appColumns) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端栏目表失败")})
	public Response<Void> save(AppColumns appColumns) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="saveColumns",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端栏目表失败")})
	public Response<Void> saveColumns(AppColumns appColumns) {
	    return null;
	}
	
	/* (non-Javadoc)
     * @see com.cqliving.cloud.online.app.service.AppColumnsService#getByConditions(java.util.Map)
     */
    @Override
    @ServiceMethodHandle
    public Response<List<AppColumnsDto>> getByConditions(Map<String, Object> conditions) {
        return null;
    }

	@Override
	public Response<CommonListResult<GetColumnsData>> getChildren(Long appId, String parentCode) {
		Response<CommonListResult<GetColumnsData>> response = Response.newInstance();
		try {
			CommonListResult<GetColumnsData> data = appColumnsManager.getChildren(appId, parentCode);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取子栏目失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取子栏目失败");
		}
		return response;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="updateColumns",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="编辑客户端栏目表失败")})
	public Response<Void> updateColumns(AppColumns domain) {
		return null;
	}
	
	/**
	 * 修改排序
	 * 
	 */
    @Override
    @ServiceMethodHandle(managerMethodName="sort",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改栏目排序失败")})
    public Response<Void> sort(Long[] ids, Integer[] sortNums, Long[] parentIds) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="getList",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询栏目列表失败")})
    public Response<List<AppColumnsDto>> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
        return null;
    }
    /**
     * 发布
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月20日
     */
    @Override
    @ServiceMethodHandle(managerMethodName="send",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="发布栏目失败")})
    public Response<Void> send(Long AppId){
        return null;
    }

	@Override
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="获取栏目失败")})
	public Response<List<AppColumns>> queryForList(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.service.AppColumnsService#getCache(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<GetColumnsData>> getCache(Long appId) {
		return null;
	}
	
}
