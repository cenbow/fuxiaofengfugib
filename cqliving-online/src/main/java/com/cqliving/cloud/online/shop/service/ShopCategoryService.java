package com.cqliving.cloud.online.shop.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shop.domain.ShopCategory;
import com.cqliving.cloud.online.shop.dto.ShopCategoryDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 商铺分类表 Service
 * Date: 2016-05-16 20:41:22
 * @author Code Generator
 */
public interface ShopCategoryService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShopCategory>> queryForPage(PageInfo<ShopCategory> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShopCategory> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShopCategory domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取商铺分类</p>
	 * @author Tangtao on 2016年5月18日
	 * @param appId
	 * @param userId 用于控制数据权限
	 * @return
	 */
	Response<List<ShopCategory>> getByApp(Long appId, Long userId);
	
	/**
	 * <p>Description: 获取商铺分类</p>
	 * @author Tangtao on 2016年7月14日
	 * @param appId
	 * @param shopTypeId
	 * @return
	 */
	Response<List<ShopCategory>> getByAppAndType(Long appId, Long shopTypeId);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年6月17日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	Response<PageInfo<ShopCategoryDto>> queryDtoForPage(PageInfo<ShopCategoryDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年6月17日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	Response<Void> modifySortNo(Long id, Integer sortNo);
	
	/**
	 * <p>Description: 清空排序</p>
	 * @author Tangtao on 2016年6月17日
	 * @param asList
	 * @return
	 */
	Response<Void> clearSortNo(List<Long> asList);
	
}
