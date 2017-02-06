package com.cqliving.cloud.online.shop.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.shop.dao.ShopTypeDao;
import com.cqliving.cloud.online.shop.domain.ShopType;
import com.cqliving.cloud.online.shop.dto.ShopTypeDto;
import com.cqliving.cloud.online.shop.manager.ShopTypeManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("shopTypeManager")
public class ShopTypeManagerImpl extends EntityServiceImpl<ShopType, ShopTypeDao> implements ShopTypeManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShopType.STATUS99, idList);
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
		return this.getEntityDao().updateStatus(status, idList);
	}

	@Override
	public List<ShopType> getByApp(Long appId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_status", ShopType.STATUS3);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		sortMap.put("id", false);
		return query(map, sortMap);
	}

	@Override
	public List<ShopType> getAllAvailable() {
		return getByApp(null);
	}

	@Override
	public PageInfo<ShopTypeDto> queryDtoForPage(PageInfo<ShopTypeDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		return getEntityDao().queryDtoForPage(pageInfo, searchMap, sortMap);
	}

	@Override
	public ShopType getByColumn(Long appColumnsId) {
		if (appColumnsId == null) {
			return null;
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appColumnsId", appColumnsId);
		map.put("EQ_status", ShopType.STATUS3);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("updateTime", false);
		List<ShopType> list = getEntityDao().query(map, sortMap);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
}