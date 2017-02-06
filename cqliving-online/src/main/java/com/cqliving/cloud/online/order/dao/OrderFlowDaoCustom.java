package com.cqliving.cloud.online.order.dao;

import com.cqliving.cloud.online.order.domain.OrderFlow;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月8日
 */
public interface OrderFlowDaoCustom {

	
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
