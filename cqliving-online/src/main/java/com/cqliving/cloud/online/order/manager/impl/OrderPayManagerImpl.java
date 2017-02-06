package com.cqliving.cloud.online.order.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.order.dao.OrderPayDao;
import com.cqliving.cloud.online.order.domain.OrderPay;
import com.cqliving.cloud.online.order.manager.OrderPayManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("orderPayManager")
public class OrderPayManagerImpl extends EntityServiceImpl<OrderPay, OrderPayDao> implements OrderPayManager {
}
