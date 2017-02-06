package com.cqliving.cloud.online.config.manager;

import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.framework.common.service.EntityService;

/**
 * app配置表 Manager
 * Date: 2016-07-16 11:09:01
 * @author Code Generator
 */
public interface AppConfigManager extends EntityService<AppConfig> {
	
	
	public AppConfig findByAppId(Long appId);
	
}
