package com.cqliving.cloud.online.config.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.config.domain.ConfigCommonType;
import com.cqliving.tool.common.Response;

/**
 * config_common_type Service
 * Date: 2016-07-25 13:54:08
 * @author Code Generator
 */
public interface ConfigCommonTypeService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ConfigCommonType>> queryForPage(PageInfo<ConfigCommonType> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ConfigCommonType> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ConfigCommonType domain);
	/** @author Code Generator *****end*****/
	public Response<Void> updateSort(Long id, Integer sortNo);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月26日
	 * @param appId
	 * @param sourceType
	 * @return
	 */
	public Response<List<ConfigCommonType>> getBySourceType(Long appId, Byte sourceType);
	
	/**
	 * <p>Description: 获取分类列表</p>
	 * @author Tangtao on 2016年7月27日
	 * @param ids
	 * @return
	 */
	Response<List<ConfigCommonType>> getByIds(List<Long> ids);
	
}
