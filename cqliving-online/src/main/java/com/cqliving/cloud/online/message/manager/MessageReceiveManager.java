package com.cqliving.cloud.online.message.manager;

import com.cqliving.cloud.online.message.domain.MessageReceive;
import com.cqliving.framework.common.service.EntityService;

/**
 * 消息通知接收表 Manager
 * Date: 2016-05-11 09:35:50
 * @author Code Generator
 */
public interface MessageReceiveManager extends EntityService<MessageReceive> {
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
