package com.org.weixin.module.dalingmusicale.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.framework.utils.Dates;
import com.org.weixin.module.dalingmusicale.dao.UserViewHisDao;
import com.org.weixin.module.dalingmusicale.domain.UserViewHis;
import com.org.weixin.module.dalingmusicale.manager.UserViewHisManager;

@Service("userViewHisManager")
public class UserViewHisManagerImpl extends EntityServiceImpl<UserViewHis, UserViewHisDao> implements UserViewHisManager {

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserViewHisManager#saveUserViewHis(java.lang.String, java.lang.String, java.lang.Long, java.lang.Byte)
	 */
	@Override
	@Transactional
	public UserViewHis saveUserViewHis(String url, String name, Long userId, Byte viewType) {
		UserViewHis userViewHis = new UserViewHis();
		userViewHis.setSourceName(name);
		userViewHis.setSourceUrl(url);
		userViewHis.setUserId(userId);
		userViewHis.setViewTime(Dates.now());
		userViewHis.setViewType(viewType);
		return this.getEntityDao().saveAndFlush(userViewHis);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserViewHisManager#findByViewType(java.lang.Byte)
	 */
	@Override
	public List<UserViewHis> findByViewType(Byte viewType) {
		
		return this.getEntityDao().findByViewType(viewType);
	}
}
