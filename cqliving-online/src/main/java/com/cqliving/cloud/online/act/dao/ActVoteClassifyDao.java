package com.cqliving.cloud.online.act.dao;

import java.util.List;

import com.cqliving.cloud.online.act.domain.ActVoteClassify;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 活动投票分类表 JPA Dao
 * Date: 2016-06-07 09:23:15
 * @author Code Generator
 */
public interface ActVoteClassifyDao extends EntityJpaDao<ActVoteClassify, Long>,ActVoteClassifyDaoCustom {
	
	public List<ActVoteClassify> findByActVoteId(Long actVoteId);
	
}
