package com.cqliving.cloud.online.act.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.act.dao.ActVoteClassifyDao;
import com.cqliving.cloud.online.act.domain.ActVoteClassify;
import com.cqliving.cloud.online.act.dto.ActVoteClassifyDto;
import com.cqliving.cloud.online.act.manager.ActVoteClassifyManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("actVoteClassifyManager")
public class ActVoteClassifyManagerImpl extends EntityServiceImpl<ActVoteClassify, ActVoteClassifyDao> implements ActVoteClassifyManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.manager.ActVoteClassifyManager#findByClassifyId(java.lang.Long)
	 */
	@Override
	public ActVoteClassifyDto findByClassifyId(Long classifyId) {
		
		return this.getEntityDao().findByClassifyId(classifyId);
	}
}
