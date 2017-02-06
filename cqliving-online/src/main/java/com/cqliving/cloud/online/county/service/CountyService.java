package com.cqliving.cloud.online.county.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.county.domain.County;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 区县表 Service
 * Date: 2017-01-05 10:11:02
 * @author Code Generator
 */
public interface CountyService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<County>> queryForPage(PageInfo<County> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<County> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(County domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * 获取区县信息
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2017年1月6日下午1:33:23
	 */
	public Response<List<County>> queryList(String token,String sessionId);
	
}
