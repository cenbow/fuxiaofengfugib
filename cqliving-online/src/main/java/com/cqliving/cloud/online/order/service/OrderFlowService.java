package com.cqliving.cloud.online.order.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.order.domain.OrderFlow;
import com.cqliving.tool.common.Response;

/**
 * 订单操作流水记录表 Service
 * Date: 2016-11-21 21:35:04
 * @author Code Generator
 */
public interface OrderFlowService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderFlow>> queryForPage(PageInfo<OrderFlow> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderFlow> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(OrderFlow domain);
	/** @author Code Generator *****end*****/

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月8日
	 * @param orderId
	 * @param operateType
	 * @return
	 */
	public Response<OrderFlow> getOneRecoreByOrderAndStatus(Long orderId, Byte operateType);
}
