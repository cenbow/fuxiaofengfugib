package com.cqliving.cloud.online.order.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.order.domain.OrderAlipayNotifyLog;
import com.cqliving.tool.common.Response;

/**
 * 订单支付宝支付异步通知日志记录表 Service
 * Date: 2016-12-12 11:56:49
 * @author Code Generator
 */
public interface OrderAlipayNotifyLogService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderAlipayNotifyLog>> queryForPage(PageInfo<OrderAlipayNotifyLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderAlipayNotifyLog> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(OrderAlipayNotifyLog domain);
	/** @author Code Generator *****end*****/
	
}
