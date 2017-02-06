package com.cqliving.cloud.online.account.manager.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.UserOtherAccountDao;
import com.cqliving.cloud.online.account.domain.UserOtherAccount;
import com.cqliving.cloud.online.account.manager.UserOtherAccountManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("userOtherAccountManager")
public class UserOtherAccountManagerImpl extends EntityServiceImpl<UserOtherAccount, UserOtherAccountDao> implements UserOtherAccountManager {

	@Override
	public UserOtherAccount getByUserToken(String userToken) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_userToken", userToken);
		List<UserOtherAccount> list = query(map, null);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
