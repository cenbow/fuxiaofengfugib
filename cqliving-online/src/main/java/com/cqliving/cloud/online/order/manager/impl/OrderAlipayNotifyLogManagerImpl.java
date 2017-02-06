package com.cqliving.cloud.online.order.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.order.manager.OrderAlipayNotifyLogManager;
import com.cqliving.cloud.online.order.dao.OrderAlipayNotifyLogDao;
import com.cqliving.cloud.online.order.domain.OrderAlipayNotifyLog;
import com.cqliving.framework.common.service.EntityServiceImpl;

import org.springframework.stereotype.Service;

@Service("orderAlipayNodifyLogManager")
public class OrderAlipayNotifyLogManagerImpl extends EntityServiceImpl<OrderAlipayNotifyLog, OrderAlipayNotifyLogDao> implements OrderAlipayNotifyLogManager {
}
