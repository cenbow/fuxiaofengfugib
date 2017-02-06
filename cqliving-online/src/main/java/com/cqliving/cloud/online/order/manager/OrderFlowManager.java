package com.cqliving.cloud.online.order.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.order.domain.OrderFlow;

/**
 * 订单操作流水记录表 Manager
 * Date: 2016-11-21 21:35:04
 * @author Code Generator
 */
public interface OrderFlowManager extends EntityService<OrderFlow> {
	
	/**
	 * Title:根据订单编号和状态获取记录
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月24日
	 * @param orderId
	 * @param status
	 * @return
	 */
	public OrderFlow getByOrderAndStatus(Long orderId, Byte status);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月8日
	 * @param orderId
	 * @param operateType
	 * @return
	 */
	public OrderFlow getOneRecoreByOrderAndStatus(Long orderId, Byte operateType);
}
