package com.cqliving.cloud.online.order.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.order.dao.OrderFlowDao;
import com.cqliving.cloud.online.order.domain.OrderFlow;
import com.cqliving.cloud.online.order.manager.OrderFlowManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("orderFlowManager")
public class OrderFlowManagerImpl extends EntityServiceImpl<OrderFlow, OrderFlowDao> implements OrderFlowManager {

	@Override
	public OrderFlow getByOrderAndStatus(Long orderId, Byte status) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_orderId", orderId);
		map.put("EQ_operateType", status);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		List<OrderFlow> list = query(map, sortMap);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public OrderFlow getOneRecoreByOrderAndStatus(Long orderId, Byte operateType) {
		return this.getEntityDao().getOneRecoreByOrderAndStatus(orderId, operateType);
	}
}
