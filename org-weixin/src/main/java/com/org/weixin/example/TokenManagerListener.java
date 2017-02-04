package com.org.weixin.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.org.weixin.support.TokenManager;

public class TokenManagerListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		//WEB容器 初始化时调用
		TokenManager.init("appid", "secret");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		//WEB容器  关闭时调用
		TokenManager.destroyed();
	}
}
