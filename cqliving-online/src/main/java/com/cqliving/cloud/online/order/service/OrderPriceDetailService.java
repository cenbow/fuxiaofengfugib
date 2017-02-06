package com.cqliving.cloud.online.order.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.order.domain.OrderPriceDetail;
import com.cqliving.tool.common.Response;

/**
 * 订单金额明细表 Service
 * Date: 2016-11-21 21:35:33
 * @author Code Generator
 */
public interface OrderPriceDetailService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderPriceDetail>> queryForPage(PageInfo<OrderPriceDetail> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderPriceDetail> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(OrderPriceDetail domain);
	/** @author Code Generator *****end*****/
	
}
