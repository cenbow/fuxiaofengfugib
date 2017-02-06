/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.log.aop;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.log.util.RemoteAdressTools;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>
 * Description:
 * </p>
 * Copyright (c) CQLIVING 2013-2016
 * 
 * @author tangqiang on 2015年5月19日
 */
public abstract class BaseLogControllerAdvice {
	private static final Logger logger = LoggerFactory
			.getLogger(BaseLogControllerAdvice.class);

	protected String ignoreAttribute;

	@SuppressWarnings("rawtypes")
	protected void log(ProceedingJoinPoint pjp, Object result,
			long executeMilliseconds) {
		Object target = pjp.getTarget();
		String methodName = pjp.getSignature().getName();
		// 日志打印
		if (logger.isDebugEnabled() && logger.isTraceEnabled() == false) {
			logger.debug("execTime:{}ms;({}.{})", executeMilliseconds, target
					.getClass().getName(), methodName);
		}
		if (logger.isTraceEnabled()) {
			HttpServletRequest request = getRequest(pjp.getArgs());
			if (request != null) {
				// 客户端信息
				String clientInformations = getClientInformations(request);
				// request参数
				Map<String, Object> parmMap = Servlets
						.getParametersStartingWith(request, null);
				// request属性
				Enumeration attrNames = request.getAttributeNames();
				Map<String, Object> attrMap = Maps.newTreeMap();
				while (attrNames != null && attrNames.hasMoreElements()) {

					String arrtName = (String) attrNames.nextElement();
					if (isIgnoreAttribute(arrtName) == false) {
						attrMap.put(arrtName, request.getAttribute(arrtName));
					}
				}
				Map springMap = getSpringMap(pjp.getArgs());
				logger.trace(
						"execTime:{}ms; url:{}; clientInfo:{}; requestParam:{}; requestAttribute:{}; springMap:{}; ({}.{})",
						executeMilliseconds, request.getRequestURI(),
						clientInformations, parmMap.toString(), attrMap
								.toString(), springMap.toString(), target
								.getClass().getName(), methodName);
			}

		}
		writeLog(pjp, target, result, methodName, executeMilliseconds);
	}

	protected abstract void writeLog(ProceedingJoinPoint pjp, Object target,
			Object result, String methodName, long executeMilliseconds);

	/**
	 * 获取Controller 的 HttpServletRequest
	 * 
	 * @param args
	 * @return
	 */
	protected HttpServletRequest getRequest(Object[] args) {
		HttpServletRequest request = null;
		for (Object arg : args) {
			if (arg instanceof HttpServletRequest) {
				request = (HttpServletRequest) arg;
				break;
			}
		}

		return request;
	}

	/**
	 * 获取请求客户端信息
	 * 
	 * @param request
	 * @return
	 */
	protected String getClientInformations(HttpServletRequest request) {
		if (request == null)
			return "";
		String clientIP = RemoteAdressTools.getRemoteAddrIp(request);
		String requestUserAgent = request.getHeader("User-Agent");
		if(requestUserAgent == null || requestUserAgent.equals("")){
			return "";
		}
		UserAgent userAgent = UserAgent.parseUserAgentString(requestUserAgent);
		OperatingSystem os = userAgent.getOperatingSystem();
		Browser browser = userAgent.getBrowser();
		String clientInfo = clientIP + " - " + os.getName() + " - "
				+ browser.getName() + "/" + browser.getBrowserType().getName();
		return clientInfo;
	}

	/**
	 * 过滤掉request attribute 的参数
	 * 
	 * @param attribureName
	 * @return
	 */
	protected boolean isIgnoreAttribute(String attribureName) {
		if (StringUtils.isBlank(ignoreAttribute)
				|| StringUtils.isBlank(attribureName)) {
			return true;
		}
		String[] ignores = StringUtils.split(ignoreAttribute, ",");
		for (String str : ignores) {
			if (attribureName.indexOf(str) != -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取Controller 的 map参数
	 * 
	 * @param args
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected Map getSpringMap(Object[] args) {
		Map map = new HashMap();
		for (Object arg : args) {
			if (arg instanceof Map) {
				map = (Map) arg;
				break;
			}
		}

		return map;
	}

	protected boolean isIgnoreParameter(String parameterName,
			String ignoreParameters) {
		String[] ignores = StringUtils.split(ignoreParameters, ",");
		for (String ignoreStr : ignores) {
			if (isLike(ignoreStr, parameterName)) {
				return true;
			}
		}
		return false;
	}

	protected boolean isLike(String srcIncludeStar, String dest) {
		if ("*".equals(srcIncludeStar)) {
			return true;
		} else if (srcIncludeStar.indexOf("*") == 0) {
			if (dest.indexOf(srcIncludeStar.substring(1,
					srcIncludeStar.length())) == dest.length()
					- srcIncludeStar.length() + 1) {
				return true;
			} else {
				return false;
			}
		} else if (srcIncludeStar.indexOf("*") == srcIncludeStar.length() - 1) {
			if (dest.indexOf(srcIncludeStar.substring(0,
					srcIncludeStar.length() - 1)) == 0) {
				return true;
			} else {
				return false;
			}
		} else if (srcIncludeStar.equalsIgnoreCase(dest)) {
			return true;
		}
		return false;
	}

	protected Map<String, String> getParameterMapping(Object target,
			String methodName, String[] paramMapping) {
		Map<String, String> param = Maps.newHashMap();
		if (paramMapping != null && paramMapping.length > 0) {
			for (String e : paramMapping) {
				String[] entry = StringUtils.split(e, ":");
				if (entry != null && entry.length == 2) {
					param.put(entry[0], entry[1]);
				}
			}
		}
		return param;
	}

	protected String getRequestParameters(HttpServletRequest request,
			Object target, String methodName, 
			String[] paramMapping, String[] ignorParam,boolean needRecordParameter,String ignoreParameters,
			Object[] args) {
		String parameters = "";
		if (needRecordParameter) {
			Map<String, Object> requestParameters = Servlets
					.getParametersStartingWith(request, null);

			// 解析@PathVariable注解的参数
			/*
			 * try{ Annotation[][] annotations =
			 * Reflections.getAccessibleMethodByName(target,
			 * methodName).getParameterAnnotations(); for(int j=0;
			 * j<annotations.length; j++){ Annotation[] annots = annotations[j];
			 * for(Annotation annot : annots){ if(annot instanceof
			 * PathVariable){ PathVariable var = (PathVariable) annot;
			 * requestParameters.put(var.value(), args[j]); } } } } catch
			 * (Exception ex){ logger.error("annotations param error:", ex); }
			 */

			Map<String, String> paramNameMapping = getParameterMapping(target,
					methodName, paramMapping);

			String ignorParams = "";

			if (ignorParam != null) {
				String ignors[] = ignorParam;
				if (ignors != null && ignors.length > 0) {
					ignorParams = "," + StringUtils.join(ignors, ",") + ",";
				}
			}

			Map<String, Object> confirmParameters = new TreeMap<String, Object>();
			for (Map.Entry<String, Object> entry : requestParameters.entrySet()) {
				if (!isIgnoreParameter(entry.getKey(), ignoreParameters)
						&& !StringUtils.contains(ignorParams,
								"," + entry.getKey() + ",")
						&& entry.getValue() != null
						&& StringUtils.isNotBlank(entry.getValue().toString())) {
					String key = entry.getKey();
					if (StringUtils.isNotBlank(paramNameMapping.get(key))) {
						key = paramNameMapping.get(key);
					}
					confirmParameters.put(key, entry.getValue());
				}
			}
			if (confirmParameters.size() > 0) {
				parameters = confirmParameters.toString();
			}
		}
		return parameters;
	}
	
	protected String getDescn(HttpServletRequest request){
		if(request == null) return "";
		String requestURI = request.getRequestURI();
		String ref = request.getParameter("ref");
		String referer = request.getHeader("referer");
		if ((ref == null || "".equals(ref.trim())) && (referer != null && referer.indexOf(request.getServerName()) != -1 )) {
			ref = referer;
		}
		ref=ref==null?"":ref;
		return "request:"+requestURI+" && ref:"+ref;
	}
}
