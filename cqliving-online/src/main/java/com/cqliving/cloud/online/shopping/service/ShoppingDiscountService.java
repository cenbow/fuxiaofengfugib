package com.cqliving.cloud.online.shopping.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.shopping.domain.ShoppingDiscount;
import com.cqliving.tool.common.Response;

/**
 * 商品折扣表 Service
 * Date: 2016-11-17 15:41:14
 * @author Code Generator
 */
public interface ShoppingDiscountService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShoppingDiscount>> queryForPage(PageInfo<ShoppingDiscount> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShoppingDiscount> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShoppingDiscount domain);
	/** @author Code Generator *****end*****/
	
}
