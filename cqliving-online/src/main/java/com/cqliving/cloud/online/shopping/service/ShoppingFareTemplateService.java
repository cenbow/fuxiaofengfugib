package com.cqliving.cloud.online.shopping.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplate;
import com.cqliving.cloud.online.shopping.dto.ShoppingFareTemplateDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 运费模板表 Service
 * Date: 2016-11-17 15:41:20
 * @author Code Generator
 */
public interface ShoppingFareTemplateService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShoppingFareTemplate>> queryForPage(PageInfo<ShoppingFareTemplate> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShoppingFareTemplate> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShoppingFareTemplate domain);
	/** @author Code Generator *****end*****/
	public Response<Void> saveOrUpdate(ShoppingFareTemplate fareTemplate);
	
	public Response<List<ShoppingFareTemplateDto>> findConditions(Map<String,Object> conditions);
}
