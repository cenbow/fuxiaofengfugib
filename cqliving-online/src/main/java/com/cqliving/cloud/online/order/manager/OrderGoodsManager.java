package com.cqliving.cloud.online.order.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.List;

import com.cqliving.cloud.online.order.domain.OrderGoods;

/**
 * 订单与商品关联表 Manager
 * Date: 2016-11-21 21:35:07
 * @author Code Generator
 */
public interface OrderGoodsManager extends EntityService<OrderGoods> {
	
	/**
	 * Title:根据订单查询商品
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月23日
	 * @param orderInfoId
	 * @return
	 */
	List<OrderGoods> getByOrder(Long orderInfoId);
	
	/**
	 * Title:根据多个订单获取集合
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月23日
	 * @param ids
	 * @return
	 */
	List<OrderGoods> getByOrders(Long...ids);
	
	/**
	 * Title:根据订单和商品获取
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月29日
	 * @param orderId
	 * @param goodsId
	 * @return
	 */
	OrderGoods getByOrderAndGoods(Long orderId, Long goodsId);
	
	/**
	 * Title:获得订单下退货的商品
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月6日
	 * @param orderId
	 * @return
	 */
	List<OrderGoods> getRefundGoodsByOrder(Long orderId);
	
	/**
	 * Title:获得订单下未退货的商品和除开当前商品
	 * <p>Description:在验证订单是否所有都退货时使用</p>
	 * @author DeweiLi on 2016年12月14日
	 * @param orderId
	 * @return
	 */
	List<OrderGoods> getByExceptRefundAndGoodsId(Long orderId, Long exceptGoodsId);
}
