package com.cqliving.cloud.online.account.dao;

import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author Tangtao on 2016年5月2日
 */
public interface UserFavoriteDaoCustom {
	
	/**
	 * <p>Description: 获取我的资讯/段子收藏</p>
	 * @author Tangtao on 2016年5月2日
	 * @param lastId
	 * @param appId
	 * @param userId
	 * @return
	 */
	ScrollPage<InfoClassifyDto> getMyFavoritesInfoPage(Long lastId, Long appId, Long userId);
	
	/**
	 * <p>Description: 获取我的商城商品收藏</p>
	 * @author Tangtao on 2016年11月21日
	 * @param lastFavoriteId
	 * @param appId
	 * @param userId
	 * @return
	 */
	ScrollPage<ShoppingGoodsDto> getMyFavoritesShoppingPage(Long lastFavoriteId, Long appId, Long userId);

}