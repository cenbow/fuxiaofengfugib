package com.org.weixin.system.service;

import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.system.domain.SysConfig;

/**
 * sys_config Service
 *
 * Date: 2015-07-23 20:46:52
 *
 * @author Acooly Code Generator
 *
 */
public interface SysConfigService extends EntityService<SysConfig> {

	public List<SysConfig> queryConfigList();

}
