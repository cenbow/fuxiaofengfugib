package com.cqliving.cloud.online.order.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.order.dao.OrderPriceDetailDao;
import com.cqliving.cloud.online.order.domain.OrderPriceDetail;
import com.cqliving.cloud.online.order.manager.OrderPriceDetailManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("orderPriceDetailManager")
public class OrderPriceDetailManagerImpl extends EntityServiceImpl<OrderPriceDetail, OrderPriceDetailDao> implements OrderPriceDetailManager {
}
