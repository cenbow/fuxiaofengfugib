package com.cqliving.cloud.online.config.manager;

import com.cqliving.cloud.online.config.domain.ConfigLife;
import com.cqliving.framework.common.service.EntityService;

/**
 * 中国建行银行悦生活服务接口 Manager
 * Date: 2016-06-16 17:48:17
 * @author Code Generator
 */
public interface ConfigLifeManager extends EntityService<ConfigLife> {
	public ConfigLife getByType(Integer id);
}
