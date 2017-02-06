package com.cqliving.cloud.online.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoTempleteImageConfig;
import com.cqliving.cloud.online.info.dto.InfoTempleteImageConfigDto;
import com.cqliving.cloud.online.info.manager.InfoTempleteImageConfigManager;
import com.cqliving.cloud.online.info.service.InfoTempleteImageConfigService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoTempleteImageConfigService")
@ServiceHandleMapping(managerClass = InfoTempleteImageConfigManager.class)
public class InfoTempleteImageConfigServiceImpl implements InfoTempleteImageConfigService {
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询模板图片配置表失败")})
	public Response<PageInfo<InfoTempleteImageConfig>> queryForPage(PageInfo<InfoTempleteImageConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询模板图片配置表失败")})
	public Response<InfoTempleteImageConfig> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除模板图片配置表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存模板图片配置表失败")})
	public Response<Void> save(InfoTempleteImageConfig infoTempleteImageConfig) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoTempleteImageConfigService#getByAppColumnsId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<InfoTempleteImageConfig>> getByAppColumnsId(Long appColumnsId,Long appId) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="getById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询模板图片配置表失败")})
	public Response<InfoTempleteImageConfigDto> getById(Long id) {
	    return null;
	}
}
