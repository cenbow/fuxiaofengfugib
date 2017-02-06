package com.cqliving.cloud.online.order.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.order.domain.OrderGoods;
import com.cqliving.tool.common.Response;

/**
 * 订单与商品关联表 Service
 * Date: 2016-11-21 21:35:07
 * @author Code Generator
 */
public interface OrderGoodsService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderGoods>> queryForPage(PageInfo<OrderGoods> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderGoods> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(OrderGoods domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:根据订单好获取商品集合
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月5日
	 * @param orderId
	 * @return
	 */
	public Response<List<OrderGoods>> getByOrder(Long orderId);
	
}
