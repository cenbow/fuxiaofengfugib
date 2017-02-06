package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserLoginInfo;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户APP登录信息表 Service
 * Date: 2016-04-15 09:46:12
 * @author Code Generator
 */
public interface UserLoginInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserLoginInfo>> queryForPage(PageInfo<UserLoginInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserLoginInfo> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(UserLoginInfo domain);
	/** @author Code Generator *****end*****/
	
}
