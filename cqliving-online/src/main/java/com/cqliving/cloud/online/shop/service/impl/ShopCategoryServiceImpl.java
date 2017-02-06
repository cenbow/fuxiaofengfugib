package com.cqliving.cloud.online.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.shop.domain.ShopCategory;
import com.cqliving.cloud.online.shop.dto.ShopCategoryDto;
import com.cqliving.cloud.online.shop.manager.ShopCategoryManager;
import com.cqliving.cloud.online.shop.service.ShopCategoryService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("shopCategoryService")
@ServiceHandleMapping(managerClass = ShopCategoryManager.class)
public class ShopCategoryServiceImpl implements ShopCategoryService {

	private static final Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);
	
	@Autowired
	private ShopCategoryManager shopCategoryManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询商铺分类表失败")})
	public Response<PageInfo<ShopCategory>> queryForPage(PageInfo<ShopCategory> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询商铺分类表失败")})
	public Response<ShopCategory> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商铺分类表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商铺分类表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存商铺分类表失败")})
	public Response<Void> save(ShopCategory shopCategory) {
		return null;
	}

	@Override
	public Response<List<ShopCategory>> getByApp(Long appId, Long userId) {
		Response<List<ShopCategory>> response = Response.newInstance();
		try {
			List<ShopCategory> data = shopCategoryManager.getByApp(appId, userId);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取商铺分类失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商铺分类失败");
		}
		return response;
	}

	@Override
	public Response<List<ShopCategory>> getByAppAndType(Long appId, Long shopTypeId) {
		Response<List<ShopCategory>> response = Response.newInstance();
		try {
			List<ShopCategory> data = shopCategoryManager.getByAppAndType(appId, shopTypeId);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取商铺分类失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商铺分类失败");
		}
		return response;
	}

	@Override
	public Response<PageInfo<ShopCategoryDto>> queryDtoForPage(PageInfo<ShopCategoryDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		Response<PageInfo<ShopCategoryDto>> response = Response.newInstance();
		try {
			PageInfo<ShopCategoryDto> data = shopCategoryManager.queryDtoForPage(pageInfo, searchMap, sortMap);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取商铺分类分页列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商铺分类分页列表失败");
		}
		return response;
	}

	@Override
	public Response<Void> modifySortNo(Long id, Integer sortNo) {
		Response<Void> response = Response.newInstance();
		try {
			shopCategoryManager.modifySortNo(id, sortNo);
		} catch (Exception e) {
			logger.error("修改排序失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改排序失败");
		}
		return response;
	}

	@Override
	public Response<Void> clearSortNo(List<Long> ids) {
		Response<Void> response = Response.newInstance();
		try {
			shopCategoryManager.clearSortNo(ids);
		} catch (Exception e) {
			logger.error("清空排序失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("清空排序失败");
		}
		return response;
	}

}
