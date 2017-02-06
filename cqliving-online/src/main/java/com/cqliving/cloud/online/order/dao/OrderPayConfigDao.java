package com.cqliving.cloud.online.order.dao;


import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.order.domain.OrderPayConfig;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 订单支付配置表 JPA Dao
 * Date: 2016-11-21 21:35:27
 * @author Code Generator
 */
public interface OrderPayConfigDao extends EntityJpaDao<OrderPayConfig, Long> {

	/**
	 * Title:根据appid获取支付配置
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月2日
	 * @param appId
	 * @param payModel
	 * @return
	 */
	@Query(" FROM OrderPayConfig WHERE appId=?1 and payModel=?2")
	public OrderPayConfig getByAppId(Long appId, Byte payModel);
}
