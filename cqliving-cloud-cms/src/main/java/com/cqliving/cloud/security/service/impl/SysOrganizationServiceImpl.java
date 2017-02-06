package com.cqliving.cloud.security.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.security.domain.SysOrganization;
import com.cqliving.cloud.online.security.manager.SysOrganizationManager;
import com.cqliving.cloud.security.service.SysOrganizationService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("sysOrganizationService")
@ServiceHandleMapping(managerClass = SysOrganizationManager.class)
public class SysOrganizationServiceImpl implements SysOrganizationService {

	//private static final Logger logger = LoggerFactory.getLogger(SysOrganizationServiceImpl.class);
	
	@Autowired
	private SysOrganizationManager sysOrganizationManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询系统组织机构表失败")})
	public Response<PageInfo<SysOrganization>> queryForPage(PageInfo<SysOrganization> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询系统组织机构表失败")})
	public Response<SysOrganization> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除系统组织机构表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存系统组织机构表失败")})
	public Response<Void> save(SysOrganization sysOrganization) {
		return null;
	}
}
