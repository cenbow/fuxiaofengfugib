package com.cqliving.cloud.online.config.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.ConfigCommonType;
import com.cqliving.cloud.online.config.manager.ConfigCommonTypeManager;
import com.cqliving.cloud.online.config.service.ConfigCommonTypeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("configCommonTypeService")
@ServiceHandleMapping(managerClass = ConfigCommonTypeManager.class)
public class ConfigCommonTypeServiceImpl implements ConfigCommonTypeService {

	private static final Logger logger = LoggerFactory.getLogger(ConfigCommonTypeServiceImpl.class);
	
	@Autowired
	private ConfigCommonTypeManager configCommonTypeManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询config_common_type失败")})
	public Response<PageInfo<ConfigCommonType>> queryForPage(PageInfo<ConfigCommonType> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询config_common_type失败")})
	public Response<ConfigCommonType> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除config_common_type失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除config_common_type失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存config_common_type失败")})
	public Response<Void> save(ConfigCommonType configCommonType) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateSort",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存排序失败")})
	public Response<Void> updateSort(Long id, Integer sortNo) {
		return null;
	}

	@Override
	public Response<List<ConfigCommonType>> getBySourceType(Long appId, Byte sourceType) {
		Response<List<ConfigCommonType>> res = Response.newInstance();
		try {
			res.setData(configCommonTypeManager.getBySourceType(appId, sourceType));
		} catch (Exception e) {
			logger.error("根据sourceType获得类型失败", e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("获取类型失败");
		}
		return res;
	}

	@Override
	public Response<List<ConfigCommonType>> getByIds(List<Long> ids) {
		Response<List<ConfigCommonType>> response = Response.newInstance();
		try {
			List<ConfigCommonType> data = configCommonTypeManager.getByIds(ids);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取话题分类失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取话题分类失败");
		}
		return response;
	}
}
