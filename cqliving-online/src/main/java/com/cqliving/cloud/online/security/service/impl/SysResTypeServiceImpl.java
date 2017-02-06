package com.cqliving.cloud.online.security.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.security.domain.SysResType;
import com.cqliving.cloud.online.security.manager.SysResTypeManager;
import com.cqliving.cloud.online.security.service.SysResTypeService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("sysResTypeService")
@ServiceHandleMapping(managerClass = SysResTypeManager.class)
public class SysResTypeServiceImpl implements SysResTypeService {

	private static final Logger logger = LoggerFactory.getLogger(SysResTypeServiceImpl.class);
	
	@Autowired
	private SysResTypeManager sysResTypeManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询系统资源分类表失败")})
	public Response<PageInfo<SysResType>> queryForPage(PageInfo<SysResType> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询系统资源分类表失败")})
	public Response<SysResType> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除系统资源分类表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除系统资源分类表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存系统资源分类表失败")})
	public Response<Void> save(SysResType sysResType) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.service.SysResTypeService#getAll()
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<SysResType>> getAll() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.service.SysResTypeService#findExistsRes()
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<SysResType>> findExistsRes() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.service.SysResTypeService#findExistsResByUserId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<SysResType>> findExistsResByUserId(Long userId) {
		return null;
	}
}
