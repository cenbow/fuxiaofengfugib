package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserActCollecInfo;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户活动信息收集表 Service
 * Date: 2016-06-07 09:29:11
 * @author Code Generator
 */
public interface UserActCollecInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserActCollecInfo>> queryForPage(PageInfo<UserActCollecInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserActCollecInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(UserActCollecInfo domain);
	/** @author Code Generator *****end*****/
	
}
