package com.cqliving.cloud.online.account.manager.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.UserSessionDao;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("userSessionManager")
public class UserSessionManagerImpl extends EntityServiceImpl<UserSession, UserSessionDao> implements UserSessionManager {

	@Override
	public UserSession getByToken(String token) {
		if (StringUtils.isBlank(token)) {
			return null;
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_token", token);
		List<UserSession> list = query(map, null);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public UserSession getByUserId(Long userId) {
		if (userId == null) {
			return null;
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_userId", userId);
		List<UserSession> list = query(map, null);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public UserSession getTourist(String sessionId) {
		List<UserSession> list = getEntityDao().getTourist(sessionId);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public UserSession get(String sessionId, String token) {
		if (StringUtils.isNotBlank(token) && !"null".equals(token)) {	//注册用户
			Map<String, Object> map = Maps.newHashMap();
			map.put("EQ_token", token);
			List<UserSession> list = query(map, null);
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			} 
		} else {	//游客
			List<UserSession> list = getEntityDao().getTourist(sessionId);
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}

}