package com.cqliving.cloud.online.act.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.act.dao.ActTemplateDao;
import com.cqliving.cloud.online.act.domain.ActTemplate;
import com.cqliving.cloud.online.act.manager.ActTemplateManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("actTemplateManager")
public class ActTemplateManagerImpl extends EntityServiceImpl<ActTemplate, ActTemplateDao> implements ActTemplateManager {

	@Override
	public List<ActTemplate> getByApp(Long appId, Byte type) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_type", type);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		return this.query(map, sortMap);
	}
}
