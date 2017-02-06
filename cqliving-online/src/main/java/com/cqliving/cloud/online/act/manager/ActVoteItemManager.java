package com.cqliving.cloud.online.act.manager;

import com.cqliving.cloud.online.act.domain.ActVoteItem;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动投票分类选项表 Manager
 * Date: 2016-06-07 09:23:54
 * @author Code Generator
 */
public interface ActVoteItemManager extends EntityService<ActVoteItem> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
}
