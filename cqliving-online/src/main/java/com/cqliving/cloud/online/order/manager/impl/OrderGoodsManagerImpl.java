package com.cqliving.cloud.online.order.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.order.dao.OrderGoodsDao;
import com.cqliving.cloud.online.order.domain.OrderGoods;
import com.cqliving.cloud.online.order.manager.OrderGoodsManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("orderGoodsManager")
public class OrderGoodsManagerImpl extends EntityServiceImpl<OrderGoods, OrderGoodsDao> implements OrderGoodsManager {

	@Override
	public List<OrderGoods> getByOrder(Long orderInfoId) {
		Map<String, Object> searchMap = Maps.newHashMap();
		searchMap.put("EQ_orderId", orderInfoId);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		return query(searchMap, sortMap);
	}
	
	@Override
	public List<OrderGoods> getByOrders(Long...ids) {
		List<Long> idList = Arrays.asList(ids);
		Map<String, Object> searchMap = Maps.newHashMap();
		searchMap.put("IN_orderId", idList);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		return query(searchMap, sortMap);
	}

	@Override
	public OrderGoods getByOrderAndGoods(Long orderId, Long goodsId) {
		Map<String, Object> searchMap = Maps.newHashMap();
		searchMap.put("EQ_orderId", orderId);
		searchMap.put("EQ_goodsId", goodsId);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		List<OrderGoods> list = query(searchMap, sortMap);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<OrderGoods> getRefundGoodsByOrder(Long orderId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_orderId", orderId);
		map.put("EQ_refundStatus", OrderGoods.REFUNDSTATUS2);
		List<OrderGoods> list = query(map, null);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<OrderGoods> getByExceptRefundAndGoodsId(Long orderId, Long exceptGoodsId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_orderId", orderId);
		map.put("EQ_refundStatus", OrderGoods.REFUNDSTATUS1);//未退货
		map.put("NOTEQ_goodsId", exceptGoodsId);//除开这个商品
		List<OrderGoods> list = query(map, null);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	
}
