package com.cqliving.cloud.online.order.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.List;

import com.cqliving.cloud.online.order.domain.OrderRefund;

/**
 * 订单商品退货表，针对订单里面的商品退货 Manager
 * Date: 2016-11-21 21:35:45
 * @author Code Generator
 */
public interface OrderRefundManager extends EntityService<OrderRefund> {

    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月18日
     * @param orderId
     * @return
     */
    List<OrderRefund> getRefundingByOrderId(Long orderId);
}
