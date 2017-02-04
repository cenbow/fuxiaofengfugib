package com.org.weixin.module.zjchj.manager.impl;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.module.zjchj.dao.ZjchjUserVisitLogDao;
import com.org.weixin.module.zjchj.domain.ZjchjUserVisitLog;
import com.org.weixin.module.zjchj.manager.ZjchjUserVisitLogManager;

@Service("zjchjUserVisitLogManager")
public class ZjchjUserVisitLogManagerImpl extends EntityServiceImpl<ZjchjUserVisitLog, ZjchjUserVisitLogDao> implements ZjchjUserVisitLogManager {

	@Override
	public Long getTotalCount() {
		Long count = getEntityDao().getTotalCount();
		return count == null ? 0L : count;
	}

	@Override
	public Long getTotalPeople() {
		Long count = getEntityDao().getTotalPeople();
		return count == null ? 0L : count;
	}
	
}