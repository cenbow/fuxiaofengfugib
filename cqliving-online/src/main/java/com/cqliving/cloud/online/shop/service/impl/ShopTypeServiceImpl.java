package com.cqliving.cloud.online.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.shop.domain.ShopType;
import com.cqliving.cloud.online.shop.dto.ShopTypeDto;
import com.cqliving.cloud.online.shop.manager.ShopTypeManager;
import com.cqliving.cloud.online.shop.service.ShopTypeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("shopTypeService")
@ServiceHandleMapping(managerClass = ShopTypeManager.class)
public class ShopTypeServiceImpl implements ShopTypeService {

	private static final Logger logger = LoggerFactory.getLogger(ShopTypeServiceImpl.class);
	
	@Autowired
	private ShopTypeManager shopTypeManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询商铺类型表失败")})
	public Response<PageInfo<ShopType>> queryForPage(PageInfo<ShopType> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询商铺类型表失败")})
	public Response<ShopType> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商铺类型表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商铺类型表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存商铺类型表失败")})
	public Response<Void> save(ShopType shopType) {
		return null;
	}

	@Override
	public Response<List<ShopType>> getByApp(Long appId) {
		Response<List<ShopType>> response = Response.newInstance();
		try {
			List<ShopType> data = shopTypeManager.getByApp(appId);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取商铺类型失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商铺类型失败");
		}
		return response;
	}

	@Override
	public Response<List<ShopType>> getAll() {
		Response<List<ShopType>> response = Response.newInstance();
		try {
			List<ShopType> data = shopTypeManager.getAllAvailable();
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取商铺类型失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商铺类型失败");
		}
		return response;
	}

	@Override
	public Response<PageInfo<ShopTypeDto>> queryDtoForPage(PageInfo<ShopTypeDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		Response<PageInfo<ShopTypeDto>> response = Response.newInstance();
		try {
			PageInfo<ShopTypeDto> data = shopTypeManager.queryDtoForPage(pageInfo, searchMap, sortMap);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取商铺类型失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商铺类型失败");
		}
		return response;
	}

	@Override
	public Response<ShopType> getByColumn(Long appColumnsId) {
		Response<ShopType> response = Response.newInstance();
		try {
			ShopType data = shopTypeManager.getByColumn(appColumnsId);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取商铺类型失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商铺类型失败");
		}
		return response;
	}
	
}
