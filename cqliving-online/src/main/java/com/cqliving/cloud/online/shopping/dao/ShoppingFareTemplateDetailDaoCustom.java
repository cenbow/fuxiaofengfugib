package com.cqliving.cloud.online.shopping.dao;

import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateDetail;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月28日
 */
public interface ShoppingFareTemplateDetailDaoCustom {

	
	/**
	 * Title:根据用户的收货地址id和商品选择的运费模板获得运费
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月28日
	 * @param shoppingUserAddressId
	 * @param shippingFareTemplateId
	 * @return
	 */
	public ShoppingFareTemplateDetail getFareByUserAddress(Long shoppingUserAddressId, Long shippingFareTemplateId);
	
	/**
	 * Title:根据商品的运费模板id获得基础模版数据
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月28日
	 * @param shoppingFareTempleteId
	 * @return
	 */
	public ShoppingFareTemplateDetail getBaseFareByShoppingFareTempleteId(Long shoppingFareTempleteId);
}
