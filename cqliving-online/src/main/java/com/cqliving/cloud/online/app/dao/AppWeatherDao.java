package com.cqliving.cloud.online.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 天气预报表 JPA Dao
 * Date: 2016-05-25 14:15:26
 * @author Code Generator
 */
public interface AppWeatherDao extends EntityJpaDao<AppWeather, Long> {
	/**
	 * Title:获取APP所在区域的天气预报
	 * @author yuwu on 2016年5月26日
	 * @param appId
	 * @return
	 */
	@Query("select t1 from AppWeather t1 where t1.appId = ?1")
    public List<AppWeather> getAppWeatherByAppId(Long appId);
	
	/**
	 * Title:获取默认地区的天气预报地址
	 * @author yuwu on 2016年5月26日
	 * @param appId
	 * @return
	 */
	@Query("select t1 from AppWeather t1 where t1.isDefault = " + AppWeather.ISDEFAULT1)
    public List<AppWeather> getDefaultAppWeather();
}
