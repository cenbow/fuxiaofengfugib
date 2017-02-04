package com.org.weixin.weixin.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.common.Constants;
import com.org.weixin.system.domain.SysConfig;
import com.org.weixin.system.domain.SysModules;
import com.org.weixin.system.dto.ModuleInfoDto;
import com.org.weixin.system.service.SysConfigService;
import com.org.weixin.system.service.SysModulesService;

/**
 * Title:配置文件定时刷新
 * <p>Description:定时刷新配置项任务</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月11日
 */
@Component
public class ConfigTask{

	@Resource
	SpyMemcachedClient spyMemcachedClient;//memcache service
	@Resource
	SysConfigService configService;//配置表 service
	@Resource
	SysModulesService modulesService ;//模块配置表 service
	
	private Logger logger = LoggerFactory.getLogger(ConfigTask.class);
	
	/**
	 * 定时刷新数据库配置项
	 * <p>Description:定时刷新数据库配置项（每隔10分钟刷新一次）</p>
	 * @author fengshi on 2015年7月11日
	 */
	@Scheduled(fixedDelay = 1000 * 60 * 10)
	public void RefreshConfig() {
		List<SysConfig> list = configService.queryConfigList();
		for (SysConfig conf : list) {
			spyMemcachedClient.set(conf.getConfigKey(), Constants.Weixin.WEIXIN_CONFIG_TIME, conf.getConfigValue());
		}
		logger.info("已更新完所有配置项！");
	}
	
	/**
	 * 定时更新拦截器拦截地址
	 * <p>Description:定时更新拦截器拦截地址(启动应用自动刷新Filter，每5分钟刷新一次)</p>
	 * @author fengshi on 2015年7月23日
	 */
	@Scheduled(fixedDelay = 1000 * 60 * 5)
	public void RefreshFilter(){
		List<SysModules> list = modulesService.queryModules();
		Map<Long,ModuleInfoDto> filterMap = new HashMap<Long,ModuleInfoDto>();
		for (SysModules mo : list) {
			ModuleInfoDto dto = new ModuleInfoDto();
			BeanUtils.copyProperties(mo, dto);
			filterMap.put(mo.getId(), dto);
		}
		spyMemcachedClient.set(Constants.Weixin.WEIXIN_FILTER_MAP, filterMap);
		logger.info("定时拦截器更新完毕！");
	}

}

