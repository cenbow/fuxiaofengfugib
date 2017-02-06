package com.cqliving.cloud.online.order.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.order.domain.OrderPayConfig;

/**
 * 订单支付配置表 Manager
 * Date: 2016-11-21 21:35:27
 * @author Code Generator
 */
public interface OrderPayConfigManager extends EntityService<OrderPayConfig> {

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月2日
	 * @param appId
	 * @param payModel
	 * @return
	 */
	OrderPayConfig getByAppId(Long appId, Byte payModel);
}
