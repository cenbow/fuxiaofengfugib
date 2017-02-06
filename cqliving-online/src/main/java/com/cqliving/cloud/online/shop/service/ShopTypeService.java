package com.cqliving.cloud.online.shop.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shop.domain.ShopType;
import com.cqliving.cloud.online.shop.dto.ShopTypeDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 商铺类型表 Service
 * Date: 2016-05-18 11:31:09
 * @author Code Generator
 */
public interface ShopTypeService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShopType>> queryForPage(PageInfo<ShopType> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShopType> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShopType domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取商铺类型</p>
	 * @author Tangtao on 2016年5月18日
	 * @param appId
	 * @return
	 */
	Response<List<ShopType>> getByApp(Long appId);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月7日
	 * @return
	 */
	Response<List<ShopType>> getAll();
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月18日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	Response<PageInfo<ShopTypeDto>> queryDtoForPage(PageInfo<ShopTypeDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description: 获取栏目对应的商情类型</p>
	 * @author Tangtao on 2016年7月18日
	 * @param appColumnsId
	 * @return
	 */
	Response<ShopType> getByColumn(Long appColumnsId);
	
}
