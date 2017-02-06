package com.cqliving.cloud.online.config.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.config.dao.ConfigWeatherConditionDao;
import com.cqliving.cloud.online.config.domain.ConfigWeatherCondition;
import com.cqliving.cloud.online.config.manager.ConfigWeatherConditionManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("configWeatherConditionManager")
public class ConfigWeatherConditionManagerImpl extends EntityServiceImpl<ConfigWeatherCondition, ConfigWeatherConditionDao> implements ConfigWeatherConditionManager {
	/**
	 * Title:返回天气状况CODE与图片关系表
	 * @author yuwu on 2016年5月25日
	 * @return
	 */
	public Map<String,String> geConfigWeatherMap(){
		Map<String,String> map = Maps.newHashMap();
		List<ConfigWeatherCondition> list = this.getAll();
		for(ConfigWeatherCondition cwc : list){
			map.put(cwc.getCode(), cwc.getIconUrl());
		}
		return map;
	}
}
