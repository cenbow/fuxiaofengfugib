package com.cqliving.cloud.online.order.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.order.domain.OrderGoods;
import com.cqliving.cloud.online.order.manager.OrderGoodsManager;
import com.cqliving.cloud.online.order.service.OrderGoodsService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("orderGoodsService")
@ServiceHandleMapping(managerClass = OrderGoodsManager.class)
public class OrderGoodsServiceImpl implements OrderGoodsService {

	private static final Logger logger = LoggerFactory.getLogger(OrderGoodsServiceImpl.class);
	
	@Autowired
	private OrderGoodsManager orderGoodsManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询订单与商品关联表失败")})
	public Response<PageInfo<OrderGoods>> queryForPage(PageInfo<OrderGoods> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询订单与商品关联表失败")})
	public Response<OrderGoods> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单与商品关联表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存订单与商品关联表失败")})
	public Response<Void> save(OrderGoods orderGoods) {
		return null;
	}

	@Override
	public Response<List<OrderGoods>> getByOrder(Long orderId) {
		Response<List<OrderGoods>> rs = Response.newInstance();
		try {
			rs.setData(orderGoodsManager.getByOrder(orderId));
		}catch(BusinessException e){
			logger.error("根据订单获取商品集合失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("根据订单获取商品集合失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获取订单商品失败");
		}
		return rs;
	}
}
