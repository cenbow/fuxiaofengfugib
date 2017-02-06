package com.cqliving.cloud.online.order.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.order.dao.OrderCallbackLogDao;
import com.cqliving.cloud.online.order.domain.OrderCallbackLog;
import com.cqliving.cloud.online.order.manager.OrderCallbackLogManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("orderCallbackLogManager")
public class OrderCallbackLogManagerImpl extends EntityServiceImpl<OrderCallbackLog, OrderCallbackLogDao> implements OrderCallbackLogManager {
}
