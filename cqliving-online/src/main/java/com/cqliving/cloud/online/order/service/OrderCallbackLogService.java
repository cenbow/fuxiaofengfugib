package com.cqliving.cloud.online.order.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.order.domain.OrderCallbackLog;
import com.cqliving.tool.common.Response;

/**
 * 订单支付宝回调日志 Service
 * Date: 2016-11-21 21:34:46
 * @author Code Generator
 */
public interface OrderCallbackLogService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderCallbackLog>> queryForPage(PageInfo<OrderCallbackLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderCallbackLog> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(OrderCallbackLog domain);
	/** @author Code Generator *****end*****/
	
}
