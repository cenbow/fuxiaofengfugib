package com.cqliving.cloud.online.account.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.account.domain.UserSession;

/**
 * 用户APP当前登录信息表 Manager
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserSessionManager extends EntityService<UserSession> {

	/**
	 * Title: 获取登录用户
	 * @author Tangtao on 2016年5月1日
	 * @param token
	 * @return
	 */
	UserSession getByToken(String token);

	/**
	 * <p>Description: 获取登录用户</p>
	 * @author Tangtao on 2016年5月11日
	 * @param userId
	 * @return
	 */
	UserSession getByUserId(Long userId);

	/**
	 * <p>Description: 获取游客</p>
	 * @author Tangtao on 2016年5月19日
	 * @param sessionId
	 * @return
	 */
	UserSession getTourist(String sessionId);
	
	/**
	 * <p>Description: 获取用户（登录用户或游客）</p>
	 * @author Tangtao on 2016年6月1日
	 * @param sessionId
	 * @param token
	 * @return
	 */
	UserSession get(String sessionId, String token);

}