package com.cqliving.cloud.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.cqliving.cloud.online.account.domain.UserSession;

/**
 * Title:问政过滤器
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月30日
 */
public class WzFilter implements Filter {
    
    private final String SESSION_USER_OBJECT_KEY = "session_wz_user_obj";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        if(uri.indexOf("/wz/home/") != -1){
            String[] excludeUrl = new String[]{"wz_login", "wz_question_share"}; //不需要过滤的URL
            boolean doFilter = true;
            for(String str : excludeUrl){
                if(uri.indexOf(str) != -1){
                    doFilter = false;
                }
            }
            if(doFilter){
                HttpSession session = req.getSession();
                UserSession userSession = (UserSession)session.getAttribute(SESSION_USER_OBJECT_KEY);
                if(userSession == null){
                    String sessionId = request.getParameter("sessionId");
                    String token = request.getParameter("token");
                    String appId = request.getParameter("appId");
                    userSession = new UserSession();
                    userSession.setAppId(appId == null ? 0L : Long.parseLong(appId));
                    userSession.setSessionCode(sessionId);
                    userSession.setToken(token);
                }
                String appId = request.getParameter("appId");
                if(StringUtils.isNotBlank(appId)){
                	try {
                		userSession.setAppId(Long.parseLong(appId));
					} catch (Exception e) {
					}
                }
                session.setAttribute(SESSION_USER_OBJECT_KEY, userSession);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
