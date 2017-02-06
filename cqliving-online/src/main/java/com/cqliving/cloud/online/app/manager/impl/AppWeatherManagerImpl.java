package com.cqliving.cloud.online.app.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.app.dao.AppWeatherDao;
import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.cloud.online.app.manager.AppWeatherManager;
import com.cqliving.cloud.online.config.manager.ConfigWeatherConditionManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.http.HttpClientUtil;

@Service("appWeatherManager")
public class AppWeatherManagerImpl extends EntityServiceImpl<AppWeather, AppWeatherDao> implements AppWeatherManager {

	private static final Logger logger = LoggerFactory.getLogger(AppWeatherManagerImpl.class);

    @Autowired
    private ConfigWeatherConditionManager configWeatherConditionManager;
    
	/**
	 * Title:获取APP所在区域的天气预报
	 * @author yuwu on 2016年5月26日
	 * @param appId
	 * @return
	 */
    public AppWeather getAppWeatherByAppId(Long appId){
    	List<AppWeather> list = this.getEntityDao().getAppWeatherByAppId(appId);
    	if(CollectionUtils.isNotEmpty(list)){
    		AppWeather appWeather = list.get(0);
    		/** 定制文件夹。这个地址是共用的：http://nimage.cqliving.com/images/weathericon/common/101.png，
    		 * 当custom_dir不为空（例如等于cq）时，会将公共地址里面的common替换成cq，
    		 * 则重庆的天气预报图片地址则为http://nimage.cqliving.com/images/weathericon/cq/101.png */
    		if(appWeather != null && StringUtils.isNotBlank(appWeather.getCustomDir()) && StringUtils.isNotBlank(appWeather.getConditionIconUrl())){
    			String url = appWeather.getConditionIconUrl();
    			String str = url.substring(0, url.lastIndexOf("/"));
    			str = str.substring(0, str.lastIndexOf("/")) + "/" + appWeather.getCustomDir() + url.substring(url.lastIndexOf("/"));
    			appWeather.setConditionIconUrl(str);
    		}
    		return appWeather;
    	}else{
    		//取默认的
    		list = this.getEntityDao().getDefaultAppWeather();
    		if(CollectionUtils.isNotEmpty(list)){
        		return list.get(0);
        	}
    	}
    	return null;
    }
    
    /**
	 * Title:同步天气
	 * @author yuwu on 2016年5月25日
	 * @param appId
	 * @return
	 */
	public void syncWeather(){
		List<AppWeather> weatherList = this.getAll();
		Map<String,String> map = configWeatherConditionManager.geConfigWeatherMap();
		String url = "http://apis.baidu.com/heweather/weather/free?city=";
		for(AppWeather appWeather : weatherList){
			try{
				Header[] headers = new Header[]{new BasicHeader("apikey",PropertiesConfig.getString(PropertyKey.WEATHER_KEY))};
				String response = HttpClientUtil.sendGetRequest(url + appWeather.getCityPhoneticize(),null,headers);
				
				JSONObject json = JSONObject.parseObject(response);
		    	JSONArray list = json.getJSONArray("HeWeather data service 3.0");
		    	if(CollectionUtils.isNotEmpty(list)){
		    		JSONObject weather = list.getJSONObject(0);
		    		JSONObject now = weather.getJSONObject("now");
		    		JSONObject cond = now.getJSONObject("cond");
		    		
		    		appWeather.setConditionCode(cond.getString("code"));
		    		appWeather.setConditionName(cond.getString("txt"));
		    		appWeather.setConditionIconUrl(map.get(cond.getString("code")));
		    		appWeather.setTmpNow(Integer.parseInt(now.getString("tmp")));
		    		
		    		JSONArray forecasts = weather.getJSONArray("daily_forecast");
		    		JSONObject forecastToday = forecasts.getJSONObject(0);
		    		JSONObject tmp = forecastToday.getJSONObject("tmp");
		    		appWeather.setTmpMax(Integer.parseInt(tmp.getString("max")));
		    		appWeather.setTmpMin(Integer.parseInt(tmp.getString("min")));
		    		appWeather.setWeatherTime(DateUtil.parse(forecastToday.getString("date"), DateUtil.FORMAT_YYYY_MM_DD));
		    		appWeather.setUpdateTime(new Date());
		    	}
		    	this.save(appWeather);
		    	logger.info(">>>>>>>>>>>>>>>同步“{}”天气预报成功<<<<<<<<<<<<<<<",appWeather.getCityName());
			}catch(Exception e){
		    	logger.info("同步“{}”天气预报失败",appWeather.getCityName(),e);
			}
		}
	}
}
