package com.cqliving.cloud.online.config.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.config.dao.ConfigPermissionDao;
import com.cqliving.cloud.online.config.domain.ConfigPermission;
import com.cqliving.cloud.online.config.manager.ConfigPermissionManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("configPermissionManager")
public class ConfigPermissionManagerImpl extends EntityServiceImpl<ConfigPermission, ConfigPermissionDao> implements ConfigPermissionManager {
}
