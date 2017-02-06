package com.cqliving.cloud.online.shop.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.shop.dao.ShopCategoryDao;
import com.cqliving.cloud.online.shop.domain.ShopCategory;
import com.cqliving.cloud.online.shop.domain.ShopType;
import com.cqliving.cloud.online.shop.dto.ShopCategoryDto;
import com.cqliving.cloud.online.shop.manager.ShopCategoryManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("shopCategoryManager")
public class ShopCategoryManagerImpl extends EntityServiceImpl<ShopCategory, ShopCategoryDao> implements ShopCategoryManager {
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShopCategory.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShopCategory.STATUS99, idList);
	}

	@Override
	public List<ShopCategory> getByApp(Long appId, Long userId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_status", ShopType.STATUS3);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo", true);
		sortMap.put("id", false);
		return query(map, sortMap);
	}

	@Override
	public List<ShopCategory> getByAppAndType(Long appId, Long shopTypeId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_typeId", shopTypeId);
		map.put("EQ_status", ShopType.STATUS3);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo", true);
		sortMap.put("id", false);
		return query(map, sortMap);
	}

	@Override
	public PageInfo<ShopCategoryDto> queryDtoForPage(PageInfo<ShopCategoryDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		return getEntityDao().queryDtoForPage(pageInfo, searchMap, sortMap);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void modifySortNo(Long id, Integer sortNo) {
		if (sortNo == null) {
			sortNo = Integer.MAX_VALUE;
		}
		getEntityDao().modifySortNo(id, sortNo);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void clearSortNo(List<Long> ids) {
		getEntityDao().clearSortNo(ids);
	}
	
}
