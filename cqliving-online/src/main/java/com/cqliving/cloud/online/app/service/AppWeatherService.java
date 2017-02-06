package com.cqliving.cloud.online.app.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 天气预报表 Service
 * Date: 2016-05-25 14:15:26
 * @author Code Generator
 */
public interface AppWeatherService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppWeather>> queryForPage(PageInfo<AppWeather> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppWeather> get(Long id);
	public Response<List<AppWeather>> getAll();
	public Response<Void> delete(Long... id);
	public Response<Void> save(AppWeather domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:获取APP所在区域的天气预报,如果没有则获取默认地区的天气预报
	 * @author yuwu on 2016年5月26日
	 * @param appId
	 * @return
	 */
    public Response<AppWeather> getAppWeatherByAppId(Long appId);
    
    /**
	 * Title:同步天气
	 * @author yuwu on 2016年5月25日
	 * @param appId
	 * @return
	 */
    public Response<Void> syncWeather();
}
