package com.cqliving.cloud.online.act.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.tool.common.Response;

/**
 * 用户答题分类表，一个用户对应一个分类测试题只有一条记录。 Service
 * Date: 2016-06-22 18:02:02
 * @author Code Generator
 */
public interface UserActTestClassifyService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserActTestClassify>> queryForPage(PageInfo<UserActTestClassify> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserActTestClassify> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(UserActTestClassify domain);
	/** @author Code Generator *****end*****/
	
}
