package com.cqliving.cloud.online.config.manager.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.config.dao.AppConfigDao;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.manager.AppConfigManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("appConfigManager")
public class AppConfigManagerImpl extends EntityServiceImpl<AppConfig, AppConfigDao> implements AppConfigManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.config.manager.AppConfigManager#findByAppId(java.lang.Long)
	 */
	@Override
	public AppConfig findByAppId(Long appId) {
	
		List<AppConfig> config = this.getEntityDao().findByAppId(appId);
		
		if(CollectionUtils.isNotEmpty(config))return config.get(0);
		
		return null;
	}
}
