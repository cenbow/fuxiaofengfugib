package com.org.weixin.module.dalingmusicale.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.framework.utils.Dates;
import com.org.weixin.module.dalingmusicale.dao.UserShareHisDao;
import com.org.weixin.module.dalingmusicale.domain.UserShareHis;
import com.org.weixin.module.dalingmusicale.manager.UserShareHisManager;

@Service("userShareHisManager")
public class UserShareHisManagerImpl extends EntityServiceImpl<UserShareHis, UserShareHisDao> implements UserShareHisManager {

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserShareHisManager#findDaily()
	 */
	@Override
	public List<UserShareHis> findDaily(Long userId,Byte shareType) {
		return this.getEntityDao().findDaily(Dates.todayStart(), Dates.todayEnd(), userId, shareType);
	}
}
