package com.cqliving.cloud.online.order.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.order.domain.OrderShopCart;
import com.cqliving.cloud.online.order.manager.OrderShopCartManager;
import com.cqliving.cloud.online.order.service.OrderShopCartService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("orderShopCartService")
@ServiceHandleMapping(managerClass = OrderShopCartManager.class)
public class OrderShopCartServiceImpl implements OrderShopCartService {

	private static final Logger logger = LoggerFactory.getLogger(OrderShopCartServiceImpl.class);
	
	@Autowired
	private OrderShopCartManager orderShopCartManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询购物车失败")})
	public Response<PageInfo<OrderShopCart>> queryForPage(PageInfo<OrderShopCart> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询购物车失败")})
	public Response<OrderShopCart> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除购物车失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存购物车失败")})
	public Response<Void> save(OrderShopCart orderShopCart) {
		return null;
	}

	@Override
	public Response<Boolean> add(Long appId, String sessionId, String token, Long goodsId, Integer quantity) {
		Response<Boolean> response = Response.newInstance();
		try {
			Boolean data = orderShopCartManager.add(appId, sessionId, token, goodsId, quantity);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("加入购物车失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error("加入购物车失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("加入购物车失败");
			response.setData(false);
		}
		return response;
	}

	@Override
	public Response<Boolean> remove(Long appId, String sessionId, String token, List<Long> idList) {
		Response<Boolean> response = Response.newInstance();
		try {
			Boolean data = orderShopCartManager.remove(appId, sessionId, token, idList);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("移出购物车失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error("移出购物车失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("移出购物车失败");
			response.setData(false);
		}
		return response;
	}

	@Override
	public Response<Integer> modify(Long appId, String sessionId, String token, Long id, Long goodsId, Integer quantity) {
		Response<Integer> response = Response.newInstance();
		try {
			Integer data = orderShopCartManager.modify(appId, sessionId, token, id, goodsId, quantity);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("修改购物车失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改购物车失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改购物车失败");
		}
		return response;
	}
	
}