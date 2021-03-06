package com.cqliving.tool.common.util.http;

import javax.servlet.http.HttpServletRequest;

import com.cqliving.tool.common.util.StringUtil;

/**
 * Title:获取http请求实际地址的基础类
 * <p>Description:根据HTTP请求，获取实际访问地址</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author o.zhibin on 2015年2月10日
 */
public class ClientRemoteRealAdress {
	/**
	 * <p>Description:根据HTTP请求获取实际访问的客户端IP地址</p>
	 * @param request http请求;
	 * @return 实际IP地址
	 * @author o.zhibin on 2015年2月10日
	 */
	public static String getRemoteAddrIp(HttpServletRequest request) {
        String ipFromNginx = getHeader(request, "X-Real-IP");      
		return StringUtil.isEmpty(ipFromNginx) ? request.getRemoteAddr() : ipFromNginx;
    }

	/**
	 * <p>Description:根据HTTP请求获取实际访问头</p>
	 * @param request http请求;
	 * @return 返回头部内容。
	 * @author o.zhibin on 2015年2月10日
	 */
	private static String getHeader(HttpServletRequest request, String headName) {
        String value = request.getHeader(headName);
        
        return !StringUtil.isEmpty(value) && !"unknown".equalsIgnoreCase(value) ? value : "";
    }
}
