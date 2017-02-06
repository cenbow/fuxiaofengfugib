package com.cqliving.cloud.online.message.service;

import java.util.Map;

import com.cqliving.cloud.online.message.domain.MessagePushLog;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 推送日志表 Service
 * Date: 2016-06-03 16:23:00
 * @author Code Generator
 */
public interface MessagePushLogService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<MessagePushLog>> queryForPage(PageInfo<MessagePushLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<MessagePushLog> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(MessagePushLog domain);
	/** @author Code Generator *****end*****/
	
}
