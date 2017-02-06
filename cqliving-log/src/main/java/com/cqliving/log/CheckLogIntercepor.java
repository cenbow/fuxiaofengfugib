/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.log;

import java.lang.reflect.Field;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cqliving.framework.utils.StringUtil;
import com.cqliving.log.aop.annotation.EduLog;
import com.cqliving.log.domain.BaseLog;
import com.cqliving.log.domain.LogOperate;
import com.cqliving.log.util.CacheConstant;
import com.cqliving.log.util.LoginUser;
import com.cqliving.log.util.SysResourceDto;
import com.cqliving.redis.base.AbstractRedisClient;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2015年11月26日
 */
public class CheckLogIntercepor implements HandlerInterceptor {

	private static ThreadLocal<StopWatch> stopWatchThread = new ThreadLocal<StopWatch>();
	
	@Autowired
	private CloudLogService cloudLogService;
	
	@Autowired
    private AbstractRedisClient abstractRedisClient;
	
	//登录用户的用户名属性字段名
	private String fieldLoginName;
	//登录用户的用户ID属性字段名
	private String fieldUserId;
	//当前的工程标签名
    private String projectTag;
	/**
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 * @author fuxiaofeng on 2015年11月26日
	 */
	@Override
	public boolean preHandle(final HttpServletRequest request, HttpServletResponse response, final Object handler) throws Exception {
		
		StopWatch stopWatch = new StopWatch();
		stopWatchThread.set(stopWatch);
		stopWatch.start(handler.toString());
		request.setAttribute("preTime",System.currentTimeMillis());
		
		return true;
	}
	
	/**
	 * 获取登录用户信息
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年7月6日上午10:09:51
	 */
	private void setUser(HttpServletRequest request,LoginUser user) throws Exception{
	    //获取登录用户
        Object sessionUser = request.getSession().getAttribute("session_user_obj");
        if(null != sessionUser){
            if(StringUtil.isEmpty(fieldLoginName)){
                fieldLoginName = "nickname";
            }
            if(StringUtil.isEmpty(fieldUserId)){
                fieldUserId = "userId";
            }
            Field loginNameField = sessionUser.getClass().getDeclaredField(fieldLoginName);
            loginNameField.setAccessible(true);
            user.setNickname((String) loginNameField.get(sessionUser));
            
            Field userIdField = sessionUser.getClass().getDeclaredField(fieldUserId);
            userIdField.setAccessible(true);
            user.setUserId((Long) userIdField.get(sessionUser));
            
            Field userNameField = sessionUser.getClass().getDeclaredField("username");
            userNameField.setAccessible(true);
            user.setUsername((String) userNameField.get(sessionUser));
            
            Field appIdField = sessionUser.getClass().getDeclaredField("appId");
            appIdField.setAccessible(true);
            user.setAppId((Long) appIdField.get(sessionUser));
        }
	}
	
	/**
	 * 设置日志实体
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年7月6日上午11:12:36
	 */
	private void setLog(HttpServletRequest request,LoginUser user,BaseLog log,HandlerMethod handerMethod) throws Exception{
	    log.setAppId(user.getAppId());
	    String methodName = handerMethod.getMethod().getName();
        if(StringUtil.isEmpty(projectTag)){
            projectTag = "cloud-portal";
        }
        log.setTag(projectTag);
        String requestURI = request.getRequestURI();
        //从资源中获取请求的方法和模块名称
        String value = abstractRedisClient.getHSet(CacheConstant.SYS_RESOURCE_URL, requestURI);
        //获取到资源 设置显示状态，模块名称，方法名称
        if(StringUtils.isNotBlank(value)){
            SysResourceDto temp = JSON.parseObject(value, SysResourceDto.class);
            //当是按钮，并且是post请求，就要显示
            if(SysResourceDto.BUTTON.equals(StringUtils.isBlank(temp.getRestype())?"":temp.getRestype().toUpperCase())&&request.getMethod().toLowerCase().equals("post")){
                log.setType(LogOperate.TYPE0);
            }else{
                log.setType(LogOperate.TYPE1);
            }
            //log.setAction(temp.getTitle());
            log.setActionName(temp.getTitle());
            log.setSysResTypeId(temp.getSysResTypeId());
            if(null!=temp.getParentId()&&!(temp.getParentId().longValue()==0l)){
                value = abstractRedisClient.getHSet(CacheConstant.SYS_RESOURCE_ID, temp.getParentId().toString());
                if(StringUtils.isNotBlank(value)){
                    temp = JSON.parseObject(value, SysResourceDto.class);
                    //log.setModule(temp.getTitle());
                    log.setModuleName(temp.getTitle());
                }else{
                    //log.setModule(handerMethod.getBeanType().getName());
                    log.setModuleName(handerMethod.getBeanType().getName());
                }
            }else{
                //log.setModule(temp.getTitle());
                log.setModuleName(temp.getTitle());
            }
            log.setModule(handerMethod.getBeanType().getName());
        }else{
            //未获取到资源 设置显示状态，模块名称，方法名称
            log.setSysResTypeId(CacheConstant.OTHERSYSRESTYPEID);
            //log.setAction(methodName);
            log.setActionName(methodName);
            log.setModule(handerMethod.getBeanType().getName());
            log.setModuleName(handerMethod.getBeanType().getName());
            //不显示
            log.setType(LogOperate.TYPE1);
        }
        log.setAction(methodName);
        log.setOperateTime(Calendar.getInstance().getTime());
        log.setOperateResult(1);
        //用户session信息
        log.setSessionId(request.getSession().getId());
        log.setOperateUser(user.getNickname()+"-"+user.getUsername());
        log.setOperateUserId(user.getUserId());
        StopWatch stopWatch = stopWatchThread.get();
        stopWatch.stop();
        Long excuteTime = stopWatch.getTotalTimeMillis();
        stopWatchThread.set(null);
        
        log.setExecuteMilliseconds(excuteTime);
        cloudLogService.log(request, log);
	}

	/**
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 * @author fuxiaofeng on 2015年11月26日
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		try {
			//如果HandlerMethod是handler.getClass()的父类或者是两个相同的类则调用日志拦截
			if(HandlerMethod.class.isAssignableFrom(handler.getClass())){
				HandlerMethod handerMethod = (HandlerMethod) handler;
				String methodName = handerMethod.getMethod().getName();
				//设置用户登录信息
				LoginUser user = new LoginUser();
				setUser(request, user);
				
				if(handerMethod.getMethod().isAnnotationPresent(EduLog.class)){
					EduLog eduLog = handerMethod.getMethodAnnotation(EduLog.class);
					cloudLogService.log(request, eduLog, handerMethod.getBeanType(), methodName,user.getUserId(),user.getNickname()+"-"+user.getUsername());
				}else{
				    //设置日志实体
				    BaseLog log = LogOperate.class.newInstance() ;
				    setLog(request, user, log, handerMethod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 * @author fuxiaofeng on 2015年11月26日
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	public String getFieldLoginName() {
		return fieldLoginName;
	}

	public void setFieldLoginName(String fieldLoginName) {
		this.fieldLoginName = fieldLoginName;
	}

	public String getFieldUserId() {
		return fieldUserId;
	}

	public void setFieldUserId(String fieldUserId) {
		this.fieldUserId = fieldUserId;
	}

	public String getProjectTag() {
		return projectTag;
	}

	public void setProjectTag(String projectTag) {
		this.projectTag = projectTag;
	}
   
	
}
