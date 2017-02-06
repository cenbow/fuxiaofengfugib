package com.cqliving.cloud.online.order.dao;


import com.cqliving.cloud.online.order.domain.OrderRefund;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 订单商品退货表，针对订单里面的商品退货 JPA Dao
 * Date: 2016-11-21 21:35:45
 * @author Code Generator
 */
public interface OrderRefundDao extends EntityJpaDao<OrderRefund, Long>, OrderRefundDaoCustom {
}
