package com.cqliving.cloud.online.config.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.config.dao.ConfigCommonTypeDao;
import com.cqliving.cloud.online.config.domain.ConfigCommonType;
import com.cqliving.cloud.online.config.manager.ConfigCommonTypeManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("configCommonTypeManager")
public class ConfigCommonTypeManagerImpl extends EntityServiceImpl<ConfigCommonType, ConfigCommonTypeDao> implements ConfigCommonTypeManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ConfigCommonType.STATUS99, idList);
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
		return this.getEntityDao().updateStatus(ConfigCommonType.STATUS99, idList);
	}
	
	@Override
	@Transactional(value="transactionManager")
	public int updateSort(Long id, Integer sortNo) {
		return this.getEntityDao().updateSort(id, sortNo);
	}

	@Override
	public List<ConfigCommonType> getBySourceType(Long appId, Byte sourceType) {
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Boolean> sortMap = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_status", ConfigCommonType.STATUS3);
		map.put("EQ_sourceType", sourceType);
		sortMap.put("sortNo", true);
		return query(map, sortMap);
	}

	@Override
	public List<ConfigCommonType> getByIds(List<Long> ids) {
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		map.put("IN_id", ids);
		sortMap.put("sortNo", true);
		return query(map, sortMap);
	}
	
}
