package com.org.common;

import javax.servlet.http.HttpServletRequest;

import com.cqliving.tool.common.util.SpringUtil;
import com.feinno.module.memcached.SpyMemcachedClient;

/**
 * Title:用户session工具类
 * <p>Description:处理用户session的相关工具</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月11日
 */
public class SessionFace {

	public static final String SESSION_USER_KEY="weixin_session_user";
    
	public static final String JYWTH_TOKEN_KEY = "jywth_token_key";
    /**
     * 获取 session Id
     * @param request
     * @return String
     */
    public static String getSessionId(HttpServletRequest request){
        return request.getSession().getId();
    }

    /**
     * 获取session属性
     * @param request
     * @param key
     * @return Object
     */
    public static Object getAttribute(HttpServletRequest request, String key){
        return request.getSession().getAttribute(key);
    }

    /**
     * 设置session 属性
     * @param request
     * @param key
     * @param value
     */
    public static void setAttribute(HttpServletRequest request, String key, String value){
        request.getSession().setAttribute(key, value);
    }
    
    /**
     * 设置session 属性
     * @param request
     * @param key
     * @param obj
     */
    public static void setAttribute(HttpServletRequest request, String key, Object obj){
    	request.getSession().setAttribute(key, obj);
    }

    /**
     * 删除session属性
     * @param request
     * @param key
     */
    public static void removeAttribute(HttpServletRequest request, String key){
        request.getSession().removeAttribute(key);
    }

    /**
     * 缓存用户信息
     * @param request
     * @param sessionUser
     */
    public static void setSessionUser(HttpServletRequest request, SessionUser sessionUser){
        sessionUser.setSessionId(getSessionId(request));
        request.getSession().setAttribute(SESSION_USER_KEY, sessionUser);
        SpyMemcachedClient spyMemcachedClient = (SpyMemcachedClient) SpringUtil.getBean("spyMemcachedClient");
        spyMemcachedClient.set(SESSION_USER_KEY+"_"+sessionUser.getOpenid(), request.getSession().getMaxInactiveInterval()*60, sessionUser);
    }
    
    /**
     * 获取当前会话用户
     * @param request
     * @return
     */
    public static SessionUser getSessionUser(HttpServletRequest request){
        return (SessionUser) request.getSession().getAttribute(SESSION_USER_KEY);
        
    }
    
    /**
     * 删除当前会话用户
     * @param request
     */
    public static void removeSessionUser(HttpServletRequest request){
        SessionUser sessionUser = getSessionUser(request);
        request.getSession().removeAttribute(SESSION_USER_KEY);
        if(sessionUser!=null){
            removeOnlineUser(sessionUser.getOpenid());
        }
    }

	/**
	 * 通过openId删除在线用户
	 * @param openId
	 */
    public static void removeOnlineUser(String openId){
        SpyMemcachedClient spyMemcachedClient = (SpyMemcachedClient) SpringUtil.getBean("spyMemcachedClient");
        spyMemcachedClient.delete(SESSION_USER_KEY+"_"+openId);
    }
}