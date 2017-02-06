package com.cqliving.cloud.online.app.service;

import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppTemplet;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 客户端模板表 Service
 * Date: 2016-05-03 20:01:37
 * @author Code Generator
 */
public interface AppTempletService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppTemplet>> queryForPage(PageInfo<AppTemplet> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppTemplet> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(AppTemplet domain);
	/** @author Code Generator *****end*****/
	
	public Response<Void> deleteTemplet(Long[] ids);
	public Response<Void> saveTemplet(AppTemplet domain);
	
}
