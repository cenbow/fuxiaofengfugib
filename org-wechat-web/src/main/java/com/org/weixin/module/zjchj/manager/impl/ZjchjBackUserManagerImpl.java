package com.org.weixin.module.zjchj.manager.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.module.zjchj.dao.ZjchjBackUserDao;
import com.org.weixin.module.zjchj.domain.ZjchjBackUser;
import com.org.weixin.module.zjchj.manager.ZjchjBackUserManager;

@Service("zjchjBackUserManager")
public class ZjchjBackUserManagerImpl extends EntityServiceImpl<ZjchjBackUser, ZjchjBackUserDao> implements ZjchjBackUserManager {

	@Override
	public ZjchjBackUser login(String userName, String pwd) {
		List<ZjchjBackUser> list = getEntityDao().login(userName, pwd);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
}