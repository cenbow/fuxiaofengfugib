package com.cqliving.cloud.online.building.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.building.dao.BuildingImageDao;
import com.cqliving.cloud.online.building.domain.BuildingImage;
import com.cqliving.cloud.online.building.manager.BuildingImageManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("buildingImageManager")
public class BuildingImageManagerImpl extends EntityServiceImpl<BuildingImage, BuildingImageDao> implements BuildingImageManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(BuildingImage.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}

	@Override
	public List<BuildingImage> getByBuilding(Long buildingId, Byte type) {
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Boolean> sortMap = Maps.newHashMap();
		map.put("EQ_buildingInfoId", buildingId);
		if(type != null && BuildingImage.allTypes.containsKey(type))
			map.put("EQ_type", type);
		sortMap.put("sortNo", true);
		List<BuildingImage> list = this.getEntityDao().query(map, sortMap);
		return list;
	}
}
