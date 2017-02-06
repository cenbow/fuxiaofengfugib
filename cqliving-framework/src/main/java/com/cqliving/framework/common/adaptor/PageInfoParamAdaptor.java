/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.framework.common.adaptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.utils.StringUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2015年12月4日
 */
public class PageInfoParamAdaptor implements HandlerMethodArgumentResolver{

	private Integer pageSize;
	
	private String currentPageNo;
	/**
	 * @param parameter
	 * @return
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
	 * @author fuxiaofeng on 2015年12月4日
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if(PageInfo.class.isAssignableFrom(parameter.getParameterType())){
			return true;
		}
		return false;
	}

	/**
	 * @param parameter
	 * @param mavContainer
	 * @param webRequest
	 * @param binderFactory
	 * @return
	 * @throws Exception
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
	 * @author fuxiaofeng on 2015年12月4日
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		//Class<?> pageInfo = parameter.getParameterType();
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if(pageSize == null){
			pageSize = 15;
		}
		
		return getPageInfo(request,pageSize);
	}

	/**
	 * <p>Description:取分页对象</p>
	 * @param HttpServletRequest,int
	 * @return PageInfo
	 */
	protected <T>PageInfo<T> getPageInfo(HttpServletRequest request, int PageSize) {
		
		String countCurrentPage = request.getParameter("countOfCurrentPage");
		
		if(!StringUtil.isEmpty(countCurrentPage)){
			PageSize = StringUtil.stringToInteger(countCurrentPage);
		}
		
		Context context = new HttpServletRequestContext(request);
		LimitFactory limitFactory = new TableLimitFactory(context);
		Limit limit = new TableLimit(limitFactory);
		PageInfo<T> pageinfo = new PageInfo<T>();

		pageinfo.setCountOfCurrentPage(PageSize);
		
		if(StringUtil.isEmpty(currentPageNo)){
			currentPageNo = "ec_p";
		}
		String ecp = request.getParameter(currentPageNo);
		String ecrd = request.getParameter("ec_rd");
		if (StringUtils.isNotBlank(ecp)) {
			pageinfo.setCurrentPage(Integer.parseInt(ecp));
		} else {
			pageinfo.setCurrentPage(limit.getPage());
		}
		if (StringUtils.isNotBlank(ecrd)) {
			pageinfo.setCountOfCurrentPage(Integer.parseInt(ecrd));
		}

		// 获取Path
		//String path = request.getRequestURI();
		//pageinfo.setPath(path);
		// 设置查询条件
		String query = request.getQueryString();
		if (!StringUtils.isEmpty(query)) {
			StringBuffer sb = new StringBuffer();
			for (String s : query.split("&")) {
				if (s.startsWith(currentPageNo) || s.startsWith("ec_rd=")) {
					continue;
				} else {
					if (sb.length() > 0) {
						sb.append("&");
					}
					sb.append(s);
				}
			}
			//pageinfo.setQuery(sb.toString());
		}

		return pageinfo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	
}
