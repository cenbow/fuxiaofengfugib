package com.cqliving.cloud.online.temp.service;

import java.util.Map;

import com.cqliving.cloud.online.temp.domain.Templet;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 模板表 Service
 * Date: 2016-04-15 09:47:40
 * @author Code Generator
 */
public interface TempletService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<Templet>> queryForPage(PageInfo<Templet> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<Templet> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(Templet domain);
	/** @author Code Generator *****end*****/
	
}
