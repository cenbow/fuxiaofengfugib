package com.cqliving.cloud.online.order.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.order.domain.OrderShopCart;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 购物车 JPA Dao
 * Date: 2016-11-21 21:35:52
 * @author Code Generator
 */
public interface OrderShopCartDao extends EntityJpaDao<OrderShopCart, Long> {

	/**
	 * <p>Description: 增加/减少购物车数量</p>
	 * @author Tangtao on 2016年11月29日
	 * @param id
	 * @param quantity
	 * @param updateTime 
	 * @return
	 */
	@Modifying
	@Query("update OrderShopCart set quantity = quantity + ?2, updateTime = ?3 where id = ?1")
	int modifyQuantity(Long id, Integer quantity, Date updateTime);
	
	
	@Modifying
	@Query("delete from OrderShopCart where goodsId in ?1")
	int deleteByGoodsIds(List<Long> goodsIds);
	
}