package com.cqliving.cloud.online.shop.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shop.domain.ShopType;
import com.cqliving.cloud.online.shop.dto.ShopTypeDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 商铺类型表 Manager
 * Date: 2016-05-18 11:31:09
 * @author Code Generator
 */
public interface ShopTypeManager extends EntityService<ShopType> {
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
	 * <p>Description: 获取商铺类型</p>
	 * @author Tangtao on 2016年5月18日
	 * @param appId
	 * @return
	 */
	List<ShopType> getByApp(Long appId);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月8日
	 * @return
	 */
	List<ShopType> getAllAvailable();

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月18日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<ShopTypeDto> queryDtoForPage(PageInfo<ShopTypeDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);

	/**
	 * <p>Description: 获取栏目对应的商情类型</p>
	 * @author Tangtao on 2016年7月18日
	 * @param appColumnsId
	 * @return
	 */
	ShopType getByColumn(Long appColumnsId);
	
}
