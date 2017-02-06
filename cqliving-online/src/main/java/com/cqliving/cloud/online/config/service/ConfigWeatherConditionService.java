package com.cqliving.cloud.online.config.service;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigWeatherCondition;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 天气预报状态表 Service
 * Date: 2016-05-25 20:24:32
 * @author Code Generator
 */
public interface ConfigWeatherConditionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ConfigWeatherCondition>> queryForPage(PageInfo<ConfigWeatherCondition> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ConfigWeatherCondition> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ConfigWeatherCondition domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:返回天气状况CODE与图片关系表
	 * @author yuwu on 2016年5月25日
	 * @return
	 */
	public Response<Map<String,String>> geConfigWeatherMap();
	
}
