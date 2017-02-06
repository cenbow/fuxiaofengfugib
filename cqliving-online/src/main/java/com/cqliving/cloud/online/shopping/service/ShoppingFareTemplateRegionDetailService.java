package com.cqliving.cloud.online.shopping.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateRegionDetail;
import com.cqliving.tool.common.Response;

/**
 * 运费模板明细地区表 Service
 * Date: 2016-11-17 15:41:29
 * @author Code Generator
 */
public interface ShoppingFareTemplateRegionDetailService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShoppingFareTemplateRegionDetail>> queryForPage(PageInfo<ShoppingFareTemplateRegionDetail> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShoppingFareTemplateRegionDetail> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ShoppingFareTemplateRegionDetail domain);
	/** @author Code Generator *****end*****/
	
}
