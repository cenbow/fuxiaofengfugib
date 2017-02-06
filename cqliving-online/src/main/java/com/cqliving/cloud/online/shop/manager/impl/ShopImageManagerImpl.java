package com.cqliving.cloud.online.shop.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.shop.dao.ShopImageDao;
import com.cqliving.cloud.online.shop.domain.ShopImage;
import com.cqliving.cloud.online.shop.manager.ShopImageManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("shopImageManager")
public class ShopImageManagerImpl extends EntityServiceImpl<ShopImage, ShopImageDao> implements ShopImageManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShopImage.STATUS99, idList);
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
		return this.getEntityDao().updateStatus(ShopImage.STATUS99, idList);
	}

	@Override
	public List<ShopImage> getByShop(Long id) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_shopId", id);
		map.put("EQ_status", ShopImage.STATUS3);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo", true);
		return query(map, sortMap);
	}
	
}
