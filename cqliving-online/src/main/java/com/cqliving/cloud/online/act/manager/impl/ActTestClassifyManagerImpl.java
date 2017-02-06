package com.cqliving.cloud.online.act.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.act.dao.ActTestClassifyDao;
import com.cqliving.cloud.online.act.domain.ActTestClassify;
import com.cqliving.cloud.online.act.manager.ActTestClassifyManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("actTestClassifyManager")
public class ActTestClassifyManagerImpl extends EntityServiceImpl<ActTestClassify, ActTestClassifyDao> implements ActTestClassifyManager {

	@Override
	public List<ActTestClassify> getByActTest(Long actInfoListId, Long actTestId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_actInfoListId", actInfoListId);
		map.put("EQ_actTestId", actTestId);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		sortMap.put("sortNo", true);
		return this.getEntityDao().query(map, sortMap);
	}

	@Override
	public List<ActTestClassify> getByIds(List<Long> ids) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("IN_id", ids);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		sortMap.put("sortNo", true);
		return this.getEntityDao().query(map, sortMap);
	}
}
