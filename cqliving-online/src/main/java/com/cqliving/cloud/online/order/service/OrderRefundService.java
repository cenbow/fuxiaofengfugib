package com.cqliving.cloud.online.order.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.order.domain.OrderRefund;
import com.cqliving.tool.common.Response;

/**
 * 订单商品退货表，针对订单里面的商品退货 Service
 * Date: 2016-11-21 21:35:45
 * @author Code Generator
 */
public interface OrderRefundService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderRefund>> queryForPage(PageInfo<OrderRefund> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderRefund> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(OrderRefund domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:根据订单id获取订单正在退货的记录
	 * <p>Description:</p>
	 * @author DeweiLi on 2017年1月18日
	 * @param orderId
	 * @return
	 */
	public Response<List<OrderRefund>> getRefundingByOrderId(Long orderId);
	
}
