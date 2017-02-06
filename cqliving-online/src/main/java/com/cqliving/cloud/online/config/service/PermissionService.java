package com.cqliving.cloud.online.config.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.config.domain.Permission;
import com.cqliving.cloud.online.security.dto.SysResTypeDto;
import com.cqliving.cloud.online.security.dto.TypesDto;
import com.cqliving.tool.common.Response;

/**
 * 前端资源权限，该表数据需要初始化好 Service
 * Date: 2016-12-20 10:12:14
 * @author Code Generator
 */
public interface PermissionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<Permission>> queryForPage(PageInfo<Permission> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<Permission> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(Permission domain);
	/** @author Code Generator *****end*****/
	public Response<List<SysResTypeDto>> findAllPermission(Long appId);
	/**
     * 保存对App的授权
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月22日下午5:39:42
     */
    public Response<Void> saveAppPermission(Long appId,TypesDto types,Long userId,String userName);
}
