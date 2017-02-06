package com.cqliving.cloud.online.config.manager.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.config.dao.ConfigLifeDao;
import com.cqliving.cloud.online.config.domain.ConfigLife;
import com.cqliving.cloud.online.config.manager.ConfigLifeManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("configLifeManager")
public class ConfigLifeManagerImpl extends EntityServiceImpl<ConfigLife, ConfigLifeDao> implements ConfigLifeManager {
	public ConfigLife getByType(Integer type) {
		List<ConfigLife> list = this.getEntityDao().getByType(type);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
}
