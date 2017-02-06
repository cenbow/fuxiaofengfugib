package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户APP当前登录信息表 Service
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserSessionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserSession>> queryForPage(PageInfo<UserSession> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserSession> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(UserSession domain);
	/** @author Code Generator *****end*****/
	/**
     * Title: 获取登录用户
     * @author Tangtao on 2016年5月1日
     * @param token
     * @return
     */
    Response<UserSession> getByToken(String token);
    
    /**
     * Title:获取游客用户
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月27日
     * @param sessionId
     * @return
     */
    Response<UserSession> getTourist(String sessionId);
	
    /**
	 * <p>Description: 获取用户（登录用户或游客）</p>
	 * @author Tangtao on 2016年6月1日
	 * @param sessionId
	 * @param token
	 * @return
	 */
    Response<UserSession> get(String sessionId, String token);
}
