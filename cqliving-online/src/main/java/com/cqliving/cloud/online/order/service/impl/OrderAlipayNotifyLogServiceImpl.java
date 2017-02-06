package com.cqliving.cloud.online.order.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.order.domain.OrderAlipayNotifyLog;
import com.cqliving.cloud.online.order.manager.OrderAlipayNotifyLogManager;
import com.cqliving.cloud.online.order.service.OrderAlipayNotifyLogService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("orderAlipayNodifyLogService")
@ServiceHandleMapping(managerClass = OrderAlipayNotifyLogManager.class)
public class OrderAlipayNotifyLogServiceImpl implements OrderAlipayNotifyLogService {

	private static final Logger logger = LoggerFactory.getLogger(OrderAlipayNotifyLogServiceImpl.class);
	
	@Autowired
	private OrderAlipayNotifyLogManager orderAlipayNodifyLogManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询订单支付宝支付异步通知日志记录表失败")})
	public Response<PageInfo<OrderAlipayNotifyLog>> queryForPage(PageInfo<OrderAlipayNotifyLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询订单支付宝支付异步通知日志记录表失败")})
	public Response<OrderAlipayNotifyLog> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单支付宝支付异步通知日志记录表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存订单支付宝支付异步通知日志记录表失败")})
	public Response<Void> save(OrderAlipayNotifyLog orderAlipayNodifyLog) {
		return null;
	}
}
