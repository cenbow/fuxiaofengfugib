package com.cqliving.cloud.online.order.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.order.dao.OrderRefundDao;
import com.cqliving.cloud.online.order.domain.OrderRefund;
import com.cqliving.cloud.online.order.manager.OrderRefundManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("orderRefundManager")
public class OrderRefundManagerImpl extends EntityServiceImpl<OrderRefund, OrderRefundDao> implements OrderRefundManager {

    @Override
    public List<OrderRefund> getRefundingByOrderId(Long orderId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_orderId", orderId);
        Map<String, Boolean> sortMap = Maps.newHashMap();
        return query(map, sortMap);
    }
}
