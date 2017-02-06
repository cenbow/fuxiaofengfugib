package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.account.domain.UserViewRecode;
import com.cqliving.tool.common.Response;

/**
 * 用户浏览记录表 Service
 * Date: 2016-06-22 10:57:45
 * @author Code Generator
 */
public interface UserViewRecodeService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserViewRecode>> queryForPage(PageInfo<UserViewRecode> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserViewRecode> get(Long id);
	public Response<Void> save(UserViewRecode domain);
	/** @author Code Generator *****end*****/
	
}
