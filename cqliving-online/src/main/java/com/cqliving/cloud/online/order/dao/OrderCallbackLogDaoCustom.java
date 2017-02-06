package com.cqliving.cloud.online.order.dao;

import com.cqliving.cloud.online.order.domain.OrderCallbackLog;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月29日
 */
public interface OrderCallbackLogDaoCustom {

	/**
	 * Title:根据订单获取支付信息
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月29日
	 * @param orderId
	 * @return
	 */
	public OrderCallbackLog getByOrder(Long orderId);
}
