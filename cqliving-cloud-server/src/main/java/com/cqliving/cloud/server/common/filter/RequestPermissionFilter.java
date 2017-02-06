/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.server.common.filter;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.RequestPermissionUtil;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.JsonMapper;

/**
 * Title: 请求权限过滤器
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月14日
 */
@Service("requestFilter")
public class RequestPermissionFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestPermissionFilter.class);
	
	@Autowired
	private RequestPermissionUtil requestPermissionUtil;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("===========================初始化请求权限过滤器 ===========================");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//获取 appId、请求方法路径
		Map<String, String[]> map = request.getParameterMap();
		String appIdStr = ArrayUtils.isNotEmpty(map.get("appId")) ? map.get("appId")[0] : null;
		if (StringUtils.isBlank(appIdStr)) {
			//参数不包含appId，暂不进行控制
			chain.doFilter(request, response);
			return;
		}
		
		Long appId = Long.valueOf(appIdStr);
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		logger.debug("appId = " + appId);
		logger.debug("url = " + uri);
		if (uri.matches("^.+/\\d+\\.html$")) {
			Pattern p = Pattern.compile("(\\d+)\\.html");
	        Matcher m = p.matcher(uri);
	        if (m.find()) {
	        	uri = uri.replace(m.group(1), "*");      
	        	logger.debug("replace_url = " + uri);
	        } 
		}
		
		//查询是否 app_permission 数据
	    Response<Void> res = requestPermissionUtil.handlePermisson(req, appId, uri);
	    if (res.getCode() == 0) {
	    	//允许访问
	    	chain.doFilter(request, response);	
		} else {
			//有错误信息，返回错误信息
			response.setContentType("text/html; charset=utf-8");  
			response.getWriter().append(new JsonMapper().toJson(res));
		}
	}

	@Override
	public void destroy() {
		logger.debug("=========================== 销毁请求权限过滤器 ===========================");
	}
	
}