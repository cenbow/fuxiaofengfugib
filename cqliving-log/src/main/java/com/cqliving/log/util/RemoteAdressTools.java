package com.cqliving.log.util;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import org.apache.commons.lang.StringUtils;


public class RemoteAdressTools {
    public static String getRemoteAddrIp(HttpServletRequest request) {
        String ipFromNginx = getHeader(request, "X-Real-IP");      
        return ipFromNginx==null||ipFromNginx.length()==0? request.getRemoteAddr() : ipFromNginx;
    }


    private static String getHeader(HttpServletRequest request, String headName) {
        String value = request.getHeader(headName);
        return !StringUtils.isBlank(value) && !"unknown".equalsIgnoreCase(value) ? value : "";
    }
    
    /**
	 * 获取请求客户端信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientInformations(HttpServletRequest request) {
        if(request==null) return "";
		String clientIP = request.getRemoteAddr();
		String requestUserAgent = request.getHeader("User-Agent");
		UserAgent userAgent = UserAgent.parseUserAgentString(requestUserAgent);
		OperatingSystem os = userAgent.getOperatingSystem();
		Browser browser = userAgent.getBrowser();
		String clientInfo = clientIP + " - " + os.getName() + " - "
				+ browser.getName() + "/" + browser.getBrowserType().getName();
		return clientInfo;

	}
}
