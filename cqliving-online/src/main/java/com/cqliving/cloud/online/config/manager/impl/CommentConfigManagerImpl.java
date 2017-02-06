package com.cqliving.cloud.online.config.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.config.dao.CommentConfigDao;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.manager.CommentConfigManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("commentConfigManager")
public class CommentConfigManagerImpl extends EntityServiceImpl<CommentConfig, CommentConfigDao> implements CommentConfigManager {

	@Override
	public List<CommentConfig> getByType(Byte type) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_type", type);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("id", true);
		return query(map, sortMap);
	}
	
	@Override
	public CommentConfig getByTypeAndName(Byte type, String name) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_type", type);
		map.put("EQ_name", name);
		List<CommentConfig> list = query(map, null);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
}