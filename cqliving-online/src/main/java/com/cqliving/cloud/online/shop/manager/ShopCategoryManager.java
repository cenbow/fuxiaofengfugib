package com.cqliving.cloud.online.shop.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shop.domain.ShopCategory;
import com.cqliving.cloud.online.shop.dto.ShopCategoryDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 商铺分类表 Manager
 * Date: 2016-05-16 20:41:22
 * @author Code Generator
 */
public interface ShopCategoryManager extends EntityService<ShopCategory> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);

	/**
	 * <p>Description: 获取商铺分类</p>
	 * @author Tangtao on 2016年5月18日
	 * @param appId
	 * @param userId 用于控制数据权限
	 * @return
	 */
	List<ShopCategory> getByApp(Long appId, Long userId);

	/**
	 * <p>Description: 获取商铺分类</p>
	 * @author Tangtao on 2016年7月14日
	 * @param appId
	 * @param shopTypeId
	 * @return
	 */
	List<ShopCategory> getByAppAndType(Long appId, Long shopTypeId);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年6月17日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<ShopCategoryDto> queryDtoForPage(PageInfo<ShopCategoryDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);

	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年6月17日
	 * @param id
	 * @param sortNo
	 */
	void modifySortNo(Long id, Integer sortNo);

	/**
	 * <p>Description: 清空排序</p>
	 * @author Tangtao on 2016年6月17日
	 * @param ids
	 */
	void clearSortNo(List<Long> ids);
	
}
