/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.server.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Title:
 * <p>Description:用于导出获取该工程内所有的请求链接，方法，及controller</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年12月19日
 */
@Controller
public class UrlController {

	@Autowired
	RequestMappingHandlerMapping requestMapping;

	@RequestMapping(value="all_uri")
	public void createRequestMappingInfo(HttpServletRequest request,HttpServletResponse response) {
		StringBuilder sb = new StringBuilder();  
        sb.append("URL").append("--").append("Class").append("--").append("Function").append('\n');  
		Map<RequestMappingInfo, HandlerMethod> map = requestMapping.getHandlerMethods();
		 for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {  
	            RequestMappingInfo info = m.getKey();  
	            HandlerMethod method = m.getValue();  
	            sb.append(info.getPatternsCondition()).append("--");  
	            sb.append(method.getMethod().getDeclaringClass()).append("--");  
	            sb.append(method.getMethod().getName()).append('\n');  
	        }  
		 try {
			response.addHeader("Content-Type","text/plain");
			response.addHeader("Content-Disposition", "attachment; filename=\"cqliving_server_uri.txt\"");
			response.getWriter().print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
