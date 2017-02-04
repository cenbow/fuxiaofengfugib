package com.org.weixin.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.org.weixin.support.TicketManager;

public class TicketManagerListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		//WEB容器 初始化时调用
		//TicketManager 依赖 TokenManager，确保TokenManager.init 先被调用
		TicketManager.init("appid");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		//WEB容器  关闭时调用
		TicketManager.destroyed();
	}
	
	
}
