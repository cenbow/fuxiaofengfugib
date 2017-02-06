package com.cqliving.cloud.online.order.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.List;

import com.cqliving.cloud.online.order.domain.OrderShopCart;

/**
 * 购物车 Manager
 * Date: 2016-11-21 21:35:52
 * @author Code Generator
 */
public interface OrderShopCartManager extends EntityService<OrderShopCart> {

	/**
	 * <p>Description: 加入购物车</p>
	 * @author Tangtao on 2016年11月29日
	 * @param appId 
	 * @param sessionId
	 * @param token
	 * @param goodsId
	 * @param quantity
	 * @return
	 */
	Boolean add(Long appId, String sessionId, String token, Long goodsId, Integer quantity);

	/**
	 * <p>Description: 移出购物车</p>
	 * @author Tangtao on 2016年11月29日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param idList
	 * @return
	 */
	Boolean remove(Long appId, String sessionId, String token, List<Long> idList);

	/**
	 * <p>Description: 修改购物车</p>
	 * @author Tangtao on 2016年11月29日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @param goodsId
	 * @param quantity
	 * @return
	 */
	Integer modify(Long appId, String sessionId, String token, Long id, Long goodsId, Integer quantity);
	
}