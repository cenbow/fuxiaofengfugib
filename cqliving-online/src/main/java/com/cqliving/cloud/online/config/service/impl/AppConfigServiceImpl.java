package com.cqliving.cloud.online.config.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.manager.AppConfigManager;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appConfigService")
@ServiceHandleMapping(managerClass = AppConfigManager.class)
public class AppConfigServiceImpl implements AppConfigService {

	private static final Logger logger = LoggerFactory.getLogger(AppConfigServiceImpl.class);
	
	@Autowired
	private AppConfigManager appConfigManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询app配置表失败")})
	public Response<PageInfo<AppConfig>> queryForPage(PageInfo<AppConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询app配置表失败")})
	public Response<AppConfig> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除app配置表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存app配置表失败")})
	public Response<Void> save(AppConfig appConfig) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.config.service.AppConfigService#findByAppId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<AppConfig> findByAppId(Long appId) {
		return null;
	}
}
