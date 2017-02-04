package com.org.weixin.system.service;

import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.system.domain.SysModules;

/**
 * sys_modules Service
 *
 * Date: 2015-07-23 20:46:52
 *
 * @author Acooly Code Generator
 *
 */
public interface SysModulesService extends EntityService<SysModules> {

	public List<SysModules> queryModules();
	
}
