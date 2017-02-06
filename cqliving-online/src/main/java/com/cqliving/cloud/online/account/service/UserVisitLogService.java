package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserVisitLog;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户打开日志表 Service
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserVisitLogService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserVisitLog>> queryForPage(PageInfo<UserVisitLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserVisitLog> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(UserVisitLog domain);
	/** @author Code Generator *****end*****/
	
}
