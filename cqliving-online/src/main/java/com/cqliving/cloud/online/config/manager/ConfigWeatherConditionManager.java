package com.cqliving.cloud.online.config.manager;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigWeatherCondition;
import com.cqliving.framework.common.service.EntityService;

/**
 * 天气预报状态表 Manager
 * Date: 2016-05-25 20:24:32
 * @author Code Generator
 */
public interface ConfigWeatherConditionManager extends EntityService<ConfigWeatherCondition> {
	/**
	 * Title:返回天气状况CODE与图片关系表
	 * @author yuwu on 2016年5月25日
	 * @return
	 */
	public Map<String,String> geConfigWeatherMap();
}
