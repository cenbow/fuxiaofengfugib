package com.cqliving.cloud.online.message.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.message.domain.MessageUnreadCount;

/**
 * 消息未读数量表 Manager
 * Date: 2016-06-16 13:37:29
 * @author Code Generator
 */
public interface MessageUnreadCountManager extends EntityService<MessageUnreadCount> {

	/**
	 * <p>Description: 清空未读消息数</p>
	 * @author Tangtao on 2016年6月16日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type
	 */
	void clear(Long appId, String sessionId, String token, Byte type);

	/**
	 * <p>Description: 获取未读消息数</p>
	 * @author Tangtao on 2016年6月16日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type
	 * @return
	 */
	Integer getCount(Long appId, String sessionId, String token, Byte type);
	
	/**
	 * <p>Description: 增加消息未读数</p>
	 * @author Tangtao on 2016年6月16日
	 * @param appId
	 * @param userId
	 * @param type
	 */
	void increaceUnreadCount(Long appId, Long userId, Byte type);
	
}