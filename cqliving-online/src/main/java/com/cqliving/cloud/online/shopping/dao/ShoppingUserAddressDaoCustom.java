package com.cqliving.cloud.online.shopping.dao;

import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.framework.common.dao.support.ScrollPage;

public interface ShoppingUserAddressDaoCustom {
	
	ScrollPage<ShoppingUserAddress> queryAddressPage(ScrollPage<ShoppingUserAddress> page, Long appId, Long userId);
	
	/**
	 * Title:获取用户正常状态下的默认收货地址
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月5日
	 * @param appId
	 * @param userId
	 * @return
	 */
	public ShoppingUserAddress getUserDefault(Long appId, Long userId);
}
