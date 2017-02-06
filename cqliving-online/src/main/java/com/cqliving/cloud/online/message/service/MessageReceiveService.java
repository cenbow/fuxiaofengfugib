package com.cqliving.cloud.online.message.service;

import java.util.Map;

import com.cqliving.cloud.online.message.domain.MessageReceive;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 消息通知接收表 Service
 * Date: 2016-05-11 09:35:50
 * @author Code Generator
 */
public interface MessageReceiveService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<MessageReceive>> queryForPage(PageInfo<MessageReceive> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<MessageReceive> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(MessageReceive domain);
	/** @author Code Generator *****end*****/
	
}
