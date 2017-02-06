package com.cqliving.cloud.online.account.manager.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.SessionDao;
import com.cqliving.cloud.online.account.domain.Session;
import com.cqliving.cloud.online.account.manager.SessionManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("sessionManagerImpl")
public class SessionManagerImpl extends EntityServiceImpl<Session, SessionDao> implements SessionManager {

	@Override
	public Session getBySession(Long appId, String sessionId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_sourceAppId", appId);
		map.put("EQ_sessionCode", sessionId);
		List<Session> list = query(map, null);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}