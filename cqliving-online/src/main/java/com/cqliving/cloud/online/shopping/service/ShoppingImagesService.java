package com.cqliving.cloud.online.shopping.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.shopping.domain.ShoppingImages;
import com.cqliving.tool.common.Response;

/**
 * 商品图片表 Service
 * Date: 2016-11-17 15:41:36
 * @author Code Generator
 */
public interface ShoppingImagesService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShoppingImages>> queryForPage(PageInfo<ShoppingImages> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShoppingImages> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShoppingImages domain);
	/** @author Code Generator *****end*****/
	
}
