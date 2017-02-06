package com.cqliving.cloud.online.act.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.act.dao.UserActTestClassifyDao;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.cloud.online.act.manager.UserActTestClassifyManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("userActTestClassifyManager")
public class UserActTestClassifyManagerImpl extends EntityServiceImpl<UserActTestClassify, UserActTestClassifyDao> implements UserActTestClassifyManager {

	@Override
	public UserActTestClassify getByWhere(Long actTestId, Long actTestClassifyId, Long userId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_actTestId", actTestId);
		map.put("EQ_actTestClassifyId", actTestClassifyId);
		map.put("EQ_userId", userId);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		List<UserActTestClassify> list = query(map, sortMap);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
