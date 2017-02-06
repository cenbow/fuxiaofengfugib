package com.cqliving.manager.security.shiro.filter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.cqliving.framework.utils.mapper.JsonMapper;
import com.cqliving.manager.security.exception.AuthenticationException;
import com.cqliving.manager.security.listener.LoginLogoutListener;
import com.cqliving.tool.common.Response;

/**
 * Created by Administrator on 2015/6/3.
 *
 * 单用户登陆
 */
public class SingleUserFilter extends AccessControlFilter implements LoginLogoutListener {

    private Map<String, String> sessionMap = new HashMap<String, String>();

    private int ajaxRepeatLoginCode=-9999992;

    private String ajaxRepeatLoginMsg="你的账号在其他地方，你已被迫下线";

    private String promptUrl;


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String userName = (String) super.getSubject(request, response).getPrincipal();
        String sessionId = sessionMap.get(userName);
        return sessionId==null || sessionId!=((HttpServletRequest)request).getSession().getId();
    }

	@Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //退出登陆
        super.getSubject(request, response).logout();
        //重复登陆，提出提示
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //HttpServletResponse httpResponse = (HttpServletResponse) response;
        if ((httpRequest.getHeader("x-requested-with") != null && httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))
                || (httpRequest.getRequestURI()!=null && httpRequest.getRequestURI().indexOf("ajax")!=-1)) {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JsonMapper.nonDefaultMapper().toJson(new Response<Void>(ajaxRepeatLoginCode, ajaxRepeatLoginMsg)));
            printWriter.flush();
            printWriter.close();
        }else{
            if (StringUtils.isNotBlank(promptUrl)) {
                WebUtils.issueRedirect(request, response, promptUrl);
            } else {
                WebUtils.issueRedirect(request, response, super.getLoginUrl());
            }
        }
        return false;
    }

    @Override
    public void afterRememberMe(String userName, HttpServletRequest request, HttpServletResponse response) {
        //登陆后设置sessionId
        sessionMap.put(userName, request.getSession().getId());
    }

    @Override
    public void onLoginSuccess(String userName, HttpServletRequest request, HttpServletResponse response) {
        afterRememberMe(userName, request, response);
    }


    @Override
    public void beforeLogin(String userName, HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    }

    @Override
    public void onLoginFailure(String userName, AuthenticationException e, HttpServletRequest request, HttpServletResponse response) {

    }


    @Override
    public void beforeLogout(String userName, HttpServletRequest request, HttpServletResponse response) {

    }

    public int getAjaxRepeatLoginCode() {
		return ajaxRepeatLoginCode;
	}

	public void setAjaxRepeatLoginCode(int ajaxRepeatLoginCode) {
		this.ajaxRepeatLoginCode = ajaxRepeatLoginCode;
	}

	public String getAjaxRepeatLoginMsg() {
        return ajaxRepeatLoginMsg;
    }

    public void setAjaxRepeatLoginMsg(String ajaxRepeatLoginMsg) {
        this.ajaxRepeatLoginMsg = ajaxRepeatLoginMsg;
    }

    public String getPromptUrl() {
        return promptUrl;
    }

    public void setPromptUrl(String promptUrl) {
        this.promptUrl = promptUrl;
    }
}
