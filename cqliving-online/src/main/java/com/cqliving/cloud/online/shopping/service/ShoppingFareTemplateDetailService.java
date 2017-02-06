package com.cqliving.cloud.online.shopping.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateDetail;
import com.cqliving.tool.common.Response;

/**
 * 运费模板明细表 Service
 * Date: 2016-11-17 15:41:25
 * @author Code Generator
 */
public interface ShoppingFareTemplateDetailService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShoppingFareTemplateDetail>> queryForPage(PageInfo<ShoppingFareTemplateDetail> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShoppingFareTemplateDetail> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShoppingFareTemplateDetail domain);
	/** @author Code Generator *****end*****/
	
}
