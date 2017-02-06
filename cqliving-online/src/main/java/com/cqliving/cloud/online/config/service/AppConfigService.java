package com.cqliving.cloud.online.config.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.tool.common.Response;

/**
 * app配置表 Service
 * Date: 2016-07-16 11:09:01
 * @author Code Generator
 */
public interface AppConfigService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppConfig>> queryForPage(PageInfo<AppConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppConfig> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(AppConfig domain);
	/** @author Code Generator *****end*****/
	
	public Response<AppConfig> findByAppId(Long appId);
}
