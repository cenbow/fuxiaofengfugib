package com.cqliving.cloud.online.account.manager;

import com.cqliving.cloud.online.account.domain.UserFavorite;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.FavoritesData;
import com.cqliving.cloud.online.interfacc.dto.FavoritesShoppingData;
import com.cqliving.framework.common.service.EntityService;

/**
 * 用户收藏表 Manager
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
public interface UserFavoriteManager extends EntityService<UserFavorite> {

	/**
	 * <p>Description: 收藏/取消收藏</p>
	 * @author Tangtao on 2016年5月31日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type 
	 * @param title
	 * @param sourceId
	 * @param sourceType
	 * @return 
	 */
	boolean collect(Long appId, String sessionId, String token, Byte type, String title, Long sourceId, Byte sourceType);

	/**
	 * <p>Description: 获取我的资讯/段子收藏</p>
	 * @author Tangtao on 2016年5月2日
	 * @param appId
	 * @param sessionId
	 * @param token 
	 * @param lastId
	 * @return
	 */
	CommonListResult<FavoritesData> getMyFavoritesPage(Long appId, String sessionId, String token, Long lastId);

	/**
	 * <p>Description: 获取我的商城商品收藏</p>
	 * @author Tangtao on 2016年11月21日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastFavoriteId
	 * @return
	 */
	CommonListResult<FavoritesShoppingData> getMyFavoritesShoppingPage(Long appId, String sessionId, String token, Long lastFavoriteId);

	/**
	 * <p>Description: 是否已收藏</p>
	 * @author Tangtao on 2016年5月31日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	boolean isCollected(Long appId, String sessionId, String token, Long sourceId, Byte sourceType);

}
