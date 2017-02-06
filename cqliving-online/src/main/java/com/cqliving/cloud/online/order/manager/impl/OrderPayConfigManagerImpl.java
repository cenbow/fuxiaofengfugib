package com.cqliving.cloud.online.order.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.order.dao.OrderPayConfigDao;
import com.cqliving.cloud.online.order.domain.OrderPayConfig;
import com.cqliving.cloud.online.order.manager.OrderPayConfigManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("orderPayConfigManager")
public class OrderPayConfigManagerImpl extends EntityServiceImpl<OrderPayConfig, OrderPayConfigDao> implements OrderPayConfigManager {

	@Override
	public OrderPayConfig getByAppId(Long appId, Byte payModel) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_payModel", payModel);
		List<OrderPayConfig> list = query(map, null);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
