package com.cqliving.cloud.online.shop.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shop.domain.ShopImage;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 商铺图片表 Service
 * Date: 2016-05-16 20:41:26
 * @author Code Generator
 */
public interface ShopImageService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShopImage>> queryForPage(PageInfo<ShopImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShopImage> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShopImage domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取商铺图片</p>
	 * @author Tangtao on 2016年5月21日
	 * @param id
	 * @return
	 */
	Response<List<ShopImage>> getByShop(Long id);
	
}
