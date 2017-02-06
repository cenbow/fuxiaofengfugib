package com.cqliving.cloud.online.order.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.order.domain.OrderShopCart;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 购物车 Service
 * Date: 2016-11-21 21:35:52
 * @author Code Generator
 */
public interface OrderShopCartService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderShopCart>> queryForPage(PageInfo<OrderShopCart> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderShopCart> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(OrderShopCart domain);
	/** @author Code Generator *****end*****/
	
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
	Response<Boolean> add(Long appId, String sessionId, String token, Long goodsId, Integer quantity);
	
	/**
	 * <p>Description: 移出购物车</p>
	 * @author Tangtao on 2016年11月29日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param idList
	 * @return
	 */
	Response<Boolean> remove(Long appId, String sessionId, String token, List<Long> idList);
	
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
	Response<Integer> modify(Long appId, String sessionId, String token, Long id, Long goodsId, Integer quantity);
	
}