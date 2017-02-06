package com.cqliving.cloud.online.account.manager;

import com.cqliving.cloud.online.account.domain.Session;
import com.cqliving.framework.common.service.EntityService;

/**
 * 登录错误日志表 Manager
 * Date: 2016-04-15 09:45:41
 * @author Code Generator
 */
public interface SessionManager extends EntityService<Session> {

	/**
	 * <p>Description: 获取会话</p>
	 * @author Tangtao on 2016年5月5日
	 * @param appId
	 * @param sessionId
	 * @return
	 */
	Session getBySession(Long appId, String sessionId);

}