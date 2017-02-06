package com.cqliving.cloud.online.message.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.message.domain.MessageUnreadCount;
import com.cqliving.tool.common.Response;

/**
 * 消息未读数量表 Service
 * Date: 2016-06-16 13:37:29
 * @author Code Generator
 */
public interface MessageUnreadCountService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<MessageUnreadCount>> queryForPage(PageInfo<MessageUnreadCount> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<MessageUnreadCount> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(MessageUnreadCount domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取未读消息数</p>
	 * @author Tangtao on 2016年6月16日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type
	 * @return
	 */
	Response<Integer> getCount(Long appId, String sessionId, String token, Byte type);
	
	/**
	 * <p>Description: 清空未读消息数</p>
	 * @author Tangtao on 2016年6月16日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type
	 * @return
	 */
	Response<Void> clear(Long appId, String sessionId, String token, Byte type);
	
}
