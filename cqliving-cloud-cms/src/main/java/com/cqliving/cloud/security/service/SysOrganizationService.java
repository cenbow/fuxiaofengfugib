package com.cqliving.cloud.security.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.security.domain.SysOrganization;
import com.cqliving.tool.common.Response;

/**
 * 系统组织机构表 Service
 * Date: 2016-04-20 11:27:25
 * @author Code Generator
 */
public interface SysOrganizationService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<SysOrganization>> queryForPage(PageInfo<SysOrganization> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<SysOrganization> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(SysOrganization domain);
	/** @author Code Generator *****end*****/
	
}
