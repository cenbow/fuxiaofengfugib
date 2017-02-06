package com.cqliving.cloud.online.app.manager;

import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.framework.common.service.EntityService;

/**
 * 天气预报表 Manager
 * Date: 2016-05-25 14:15:26
 * @author Code Generator
 */
public interface AppWeatherManager extends EntityService<AppWeather> {
	/**
	 * Title:获取APP所在区域的天气预报
	 * @author yuwu on 2016年5月26日
	 * @param appId
	 * @return
	 */
    public AppWeather getAppWeatherByAppId(Long appId);
    /**
	 * Title:同步天气
	 * @author yuwu on 2016年5月25日
	 * @param appId
	 * @return
	 */
	public void syncWeather();
	
}
