package com.cqliving.cloud.online.act.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.act.dao.ActTestCollectDao;
import com.cqliving.cloud.online.act.domain.ActTestCollect;
import com.cqliving.cloud.online.act.manager.ActTestCollectManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("actTestCollectManager")
public class ActTestCollectManagerImpl extends EntityServiceImpl<ActTestCollect, ActTestCollectDao> implements ActTestCollectManager {

	@Override
	public List<ActTestCollect> getByActTestId(Long actTestId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_actTestId", actTestId);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		return query(map, sortMap);
	}
}
