package com.cqliving.framework.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HTMLFilter implements Filter {

	private char[] characterParams = null;
	private char[] replaceParams = null;
	private String[] ignoreParams = null;

	  
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse responseNew = (HttpServletResponse) response;
		//解决如下漏洞：“点击劫持：X-Frame-Options未配置”
		//responseNew.setHeader("X-Frame-Options", "SAMEORIGIN");//使用预览有问题
		HTMLRequestWrapper xssRequest = new HTMLRequestWrapper((HttpServletRequest) request,characterParams,replaceParams,ignoreParams);
		chain.doFilter(xssRequest, responseNew);
	}

	@Override
	public void destroy() {
	}

	public void init(FilterConfig config) throws ServletException {
		String characterParamsTemp [] = config.getInitParameter("characterParams").split(",");
		this.characterParams = new char[characterParamsTemp.length];
		for(int i = 0 ; i < characterParamsTemp.length ; i++){
			characterParams[i] = characterParamsTemp[i].charAt(0);
		}
		String replaceParamsTemp [] = config.getInitParameter("replaceParams").split(",");
		this.replaceParams = new char[replaceParamsTemp.length];
		for(int i = 0 ; i < replaceParamsTemp.length ; i++){
			replaceParams[i] = replaceParamsTemp[i].charAt(0);
		}
		this.ignoreParams = config.getInitParameter("ignoreParams").split(",");
	}
}