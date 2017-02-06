package com.cqliving.cloud.online.config.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.config.domain.AppPermission;
import com.cqliving.cloud.online.config.dto.AppPermissionDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 客户端资源权限表 Service
 * Date: 2016-12-14 16:50:46
 * @author Code Generator
 */
public interface AppPermissionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppPermission>> queryForPage(PageInfo<AppPermission> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppPermission> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(AppPermission domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年12月15日
	 * @return
	 */
	Response<List<AppPermissionDto>> getDtoOfAll();
	
}