package com.cqliving.cloud.online.order.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.order.domain.OrderCallbackLog;
import com.cqliving.cloud.online.order.manager.OrderCallbackLogManager;
import com.cqliving.cloud.online.order.service.OrderCallbackLogService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("orderCallbackLogService")
@ServiceHandleMapping(managerClass = OrderCallbackLogManager.class)
public class OrderCallbackLogServiceImpl implements OrderCallbackLogService {

	private static final Logger logger = LoggerFactory.getLogger(OrderCallbackLogServiceImpl.class);
	
	@Autowired
	private OrderCallbackLogManager orderCallbackLogManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询订单支付宝回调日志失败")})
	public Response<PageInfo<OrderCallbackLog>> queryForPage(PageInfo<OrderCallbackLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询订单支付宝回调日志失败")})
	public Response<OrderCallbackLog> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单支付宝回调日志失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存订单支付宝回调日志失败")})
	public Response<Void> save(OrderCallbackLog orderCallbackLog) {
		return null;
	}
}
