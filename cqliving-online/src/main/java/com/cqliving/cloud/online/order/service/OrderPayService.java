package com.cqliving.cloud.online.order.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.order.domain.OrderPay;
import com.cqliving.tool.common.Response;

/**
 * 订单合并支付表（用于支付宝回调唯一标识），用于订单合并支付（订单合并支付时支付流水号与订单关系为一对多，否则一对一） Service
 * Date: 2016-11-21 21:35:20
 * @author Code Generator
 */
public interface OrderPayService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderPay>> queryForPage(PageInfo<OrderPay> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderPay> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(OrderPay domain);
	/** @author Code Generator *****end*****/
	
}
