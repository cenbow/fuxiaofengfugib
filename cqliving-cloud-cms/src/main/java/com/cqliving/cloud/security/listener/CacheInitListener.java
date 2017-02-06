package com.cqliving.cloud.security.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.security.service.SysResourceService;
import com.cqliving.tool.common.util.SpringUtil;

public class CacheInitListener implements ServletContextListener {
	
	public static Logger logger = LoggerFactory.getLogger(CacheInitListener.class);

	@Autowired
	private SysResourceService sysResourceService;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sysResourceService = SpringUtil.getBeanByClass(SysResourceService.class);
		sysResourceService.initAllResourceCache();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
