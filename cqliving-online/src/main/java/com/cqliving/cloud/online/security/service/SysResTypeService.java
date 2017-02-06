package com.cqliving.cloud.online.security.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.security.domain.SysResType;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 系统资源分类表 Service
 * Date: 2016-06-29 17:07:53
 * @author Code Generator
 */
public interface SysResTypeService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<SysResType>> queryForPage(PageInfo<SysResType> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<SysResType> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(SysResType domain);
	/** @author Code Generator *****end*****/
	public Response<List<SysResType>> getAll();
	//查找存在存在资源的资源分类
	public Response<List<SysResType>> findExistsRes();
	
	public Response<List<SysResType>> findExistsResByUserId(Long userId);
}
