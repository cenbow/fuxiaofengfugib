package com.cqliving.cloud.online.order.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.order.domain.OrderPay;
import com.cqliving.cloud.online.order.manager.OrderPayManager;
import com.cqliving.cloud.online.order.service.OrderPayService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("orderPayService")
@ServiceHandleMapping(managerClass = OrderPayManager.class)
public class OrderPayServiceImpl implements OrderPayService {

	private static final Logger logger = LoggerFactory.getLogger(OrderPayServiceImpl.class);
	
	@Autowired
	private OrderPayManager orderPayManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询订单合并支付表（用于支付宝回调唯一标识），用于订单合并支付（订单合并支付时支付流水号与订单关系为一对多，否则一对一）失败")})
	public Response<PageInfo<OrderPay>> queryForPage(PageInfo<OrderPay> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询订单合并支付表（用于支付宝回调唯一标识），用于订单合并支付（订单合并支付时支付流水号与订单关系为一对多，否则一对一）失败")})
	public Response<OrderPay> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单合并支付表（用于支付宝回调唯一标识），用于订单合并支付（订单合并支付时支付流水号与订单关系为一对多，否则一对一）失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存订单合并支付表（用于支付宝回调唯一标识），用于订单合并支付（订单合并支付时支付流水号与订单关系为一对多，否则一对一）失败")})
	public Response<Void> save(OrderPay orderPay) {
		return null;
	}
}
