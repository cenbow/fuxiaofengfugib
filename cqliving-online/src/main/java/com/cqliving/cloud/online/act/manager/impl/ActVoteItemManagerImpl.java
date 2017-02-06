package com.cqliving.cloud.online.act.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.act.dao.ActVoteItemDao;
import com.cqliving.cloud.online.act.domain.ActVoteItem;
import com.cqliving.cloud.online.act.manager.ActVoteItemManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("actVoteItemManager")
public class ActVoteItemManagerImpl extends EntityServiceImpl<ActVoteItem, ActVoteItemDao> implements ActVoteItemManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ActVoteItem.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ActVoteItem.STATUS99, idList);
	}
}
