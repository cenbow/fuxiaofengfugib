package com.cqliving.cloud.online.config.service;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigLife;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 中国建行银行悦生活服务接口 Service
 * Date: 2016-06-16 17:48:17
 * @author Code Generator
 */
public interface ConfigLifeService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ConfigLife>> queryForPage(PageInfo<ConfigLife> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ConfigLife> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ConfigLife domain);
	/** @author Code Generator *****end*****/
	public Response<ConfigLife> getByType(Integer id);
	
}
