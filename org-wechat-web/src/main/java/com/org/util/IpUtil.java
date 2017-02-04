package com.org.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * Title:获取客户端ip工具
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月27日
 */
public class IpUtil {

	/**
	 * <p>Description:获取真实的ip地址</p>
	 * @param request
	 * @return
	 * @author fengshi on 2015年7月27日
	 */
	public static String getIpAddr(HttpServletRequest request) {
		
		String IP = request.getHeader("x-forwarded-for");
		if (IP == null || IP.length() == 0 || "unknown".equalsIgnoreCase(IP)) {
			IP = request.getHeader("Proxy-Client-IP");
		}
		if (IP == null || IP.length() == 0 || "unknown".equalsIgnoreCase(IP)) {
			IP = request.getHeader("WL-Proxy-Client-IP");
		}
		if (IP == null || IP.length() == 0 || "unknown".equalsIgnoreCase(IP)) {
			IP = request.getRemoteAddr();
			if (IP.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				IP = inet.getHostAddress();
			}
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (IP != null && IP.length() > 15) { 
			if (IP.indexOf(",") > 0) {
				IP = IP.substring(0, IP.indexOf(","));
			}
		}
		return IP;
	}

}
