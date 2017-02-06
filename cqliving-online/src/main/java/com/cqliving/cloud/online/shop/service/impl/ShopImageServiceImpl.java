package com.cqliving.cloud.online.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.shop.domain.ShopImage;
import com.cqliving.cloud.online.shop.manager.ShopImageManager;
import com.cqliving.cloud.online.shop.service.ShopImageService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("shopImageService")
@ServiceHandleMapping(managerClass = ShopImageManager.class)
public class ShopImageServiceImpl implements ShopImageService {

	private static final Logger logger = LoggerFactory.getLogger(ShopImageServiceImpl.class);
	
	@Autowired
	private ShopImageManager shopImageManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询商铺图片表失败")})
	public Response<PageInfo<ShopImage>> queryForPage(PageInfo<ShopImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询商铺图片表失败")})
	public Response<ShopImage> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商铺图片表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商铺图片表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存商铺图片表失败")})
	public Response<Void> save(ShopImage shopImage) {
		return null;
	}

	@Override
	public Response<List<ShopImage>> getByShop(Long id) {
		Response<List<ShopImage>> response = Response.newInstance();
		try {
			List<ShopImage> data = shopImageManager.getByShop(id);
			response.setData(data);
		} catch (Exception e) {
			logger.error("保存商铺失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("保存商铺失败");
		}
		return response;
	}
	
}
