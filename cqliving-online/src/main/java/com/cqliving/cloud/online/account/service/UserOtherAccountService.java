package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserOtherAccount;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户其他平台账号表 Service
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
public interface UserOtherAccountService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserOtherAccount>> queryForPage(PageInfo<UserOtherAccount> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserOtherAccount> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(UserOtherAccount domain);
	/** @author Code Generator *****end*****/
	
}
