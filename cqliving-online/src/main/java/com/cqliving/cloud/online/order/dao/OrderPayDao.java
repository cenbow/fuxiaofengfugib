package com.cqliving.cloud.online.order.dao;


import com.cqliving.cloud.online.order.domain.OrderPay;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 订单合并支付表（用于支付宝回调唯一标识），用于订单合并支付（订单合并支付时支付流水号与订单关系为一对多，否则一对一） JPA Dao
 * Date: 2016-11-21 21:35:20
 * @author Code Generator
 */
public interface OrderPayDao extends EntityJpaDao<OrderPay, Long> {
}
