package com.cqliving.cloud.online.config.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.config.domain.ConfigPermission;
import com.cqliving.tool.common.Response;

/**
 * 前端资源权限，该表数据需要初始化好 Service
 * Date: 2016-12-14 16:50:57
 * @author Code Generator
 */
public interface ConfigPermissionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ConfigPermission>> queryForPage(PageInfo<ConfigPermission> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ConfigPermission> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ConfigPermission domain);
	/** @author Code Generator *****end*****/
	
}
