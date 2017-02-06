package com.cqliving.framework.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author yuwu
 * @date 2014-05-16
 */
public class HTMLRequestWrapper extends HttpServletRequestWrapper {
	HttpServletRequest orgRequest = null;
	private char[] characterParams = null;
	private char[] replaceParams = null;
	private String[] ignoreParams = null;
	
	public HTMLRequestWrapper(HttpServletRequest request,char[] characterParams ,char[] replaceParams, String [] ignoreParams) {
		super(request);
		orgRequest = request;
		this.characterParams = characterParams;
		this.replaceParams = replaceParams;
		this.ignoreParams = ignoreParams;
	}

	@Override
	public String[] getParameterValues(String name) {
		String values [] = super.getParameterValues(name);
		if(values != null){
			String value = null;
			for(int i = 0 ; i < values.length ; i++){
				value = values[i];
				boolean flag = isIgnoreParamName(name);
				if(!(StringUtils.isBlank(value) || flag)){
					value = xssEncode(value);
					values[i] = value;
				}
			}
		}
		return values;
	}

	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		boolean flag = isIgnoreParamName(name);
		if(StringUtils.isBlank(value) || flag){
			return value;
		}else{
			value = xssEncode(value);
		}
		return value;
	}

	/**
	 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> getHeaderNames 也可能需要覆盖
	 */
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	/**
	 * 将容易引起xss漏洞的半角字符直接替换成全角字符
	 * @param s
	 * @return
	 */
	private String xssEncode(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		//用于判断是否被替换标识，如果没有被替换，则使用原字符
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			boolean flag = true;
			for(int j = 0 ; j < characterParams.length ; j++){
				if(c == characterParams[j]){
					sb.append(replaceParams[j]);
					flag = false;
					break;
				}
			}
			if(flag){
				//append原字符
				sb.append(c);
			}
			/*switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			case '\'':
				sb.append('‘');// 全角单引号
				break;
			case '\"':
				sb.append('“');// 全角双引号
				break;
			default:
				sb.append(c);
				break;
			}*/
		}
		return sb.toString();
	}

	/**
	 * Title:判断该参数是否忽略的参数,返回true，表示该参数对应的值不需要被过滤
	 * @author yuwu on 2016年9月1日
	 * @param name
	 * @return {true:该参数被忽略，不要被过滤，false:需要被过滤}
	 */
	public boolean isIgnoreParamName(String name) {
		boolean flag = false;
		if(ignoreParams == null || ignoreParams.length == 0){
			return flag;
		}
		for(String ignoreParamName : ignoreParams){
			if(ignoreParamName.startsWith("startsWith_") && name.startsWith(ignoreParamName.substring("startsWith_".length()))){
				flag = true;
				break;
			}else if(ignoreParamName.startsWith("endsWith_") && name.endsWith(ignoreParamName.substring("endsWith_".length()))){
				flag = true;
				break;
			}else if(ignoreParamName.startsWith("like_") && name.contains(ignoreParamName.substring("like_".length()))){
				flag = true;
				break;
			}else if(ignoreParamName.equals(name)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 获取最原始的request
	 * 
	 * @return
	 */
	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}

	/**
	 * 获取最原始的request的静态方法
	 * 
	 * @return
	 */
	public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
		if (req instanceof HTMLRequestWrapper) {
			return ((HTMLRequestWrapper) req).getOrgRequest();
		}
		return req;
	}
}