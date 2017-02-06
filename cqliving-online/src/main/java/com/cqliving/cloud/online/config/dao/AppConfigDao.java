package com.cqliving.cloud.online.config.dao;


import java.util.List;

import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * app配置表 JPA Dao
 * Date: 2016-07-16 11:09:01
 * @author Code Generator
 */
public interface AppConfigDao extends EntityJpaDao<AppConfig, Long> {
	
	public List<AppConfig> findByAppId(Long appId);
	
}
