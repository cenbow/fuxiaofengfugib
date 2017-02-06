package com.cqliving.cloud.online.act.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.act.domain.UserActTestClassifyHistory;
import com.cqliving.tool.common.Response;

/**
 * 用户答题分类历史表。一个用户对应一个分类测试题可以有多条记录。 Service
 * Date: 2016-06-22 18:02:08
 * @author Code Generator
 */
public interface UserActTestClassifyHistoryService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserActTestClassifyHistory>> queryForPage(PageInfo<UserActTestClassifyHistory> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserActTestClassifyHistory> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(UserActTestClassifyHistory domain);
	/** @author Code Generator *****end*****/
	
}
