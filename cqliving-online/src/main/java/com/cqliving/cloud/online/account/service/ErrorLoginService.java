package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.ErrorLogin;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 登录错误日志表 Service
 * Date: 2016-04-15 09:45:41
 * @author Code Generator
 */
public interface ErrorLoginService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ErrorLogin>> queryForPage(PageInfo<ErrorLogin> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ErrorLogin> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(ErrorLogin domain);
	/** @author Code Generator *****end*****/
	
}
