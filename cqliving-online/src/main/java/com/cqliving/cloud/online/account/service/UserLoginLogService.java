package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserLoginLog;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户登录日志表 Service
 * Date: 2016-04-15 09:46:15
 * @author Code Generator
 */
public interface UserLoginLogService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserLoginLog>> queryForPage(PageInfo<UserLoginLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserLoginLog> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(UserLoginLog domain);
	/** @author Code Generator *****end*****/
	
}
