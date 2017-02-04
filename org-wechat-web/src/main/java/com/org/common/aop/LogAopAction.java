package com.org.common.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.org.common.SessionFace;
import com.org.common.annotation.SystemLog;
import com.org.util.IpUtil;
import com.org.weixin.system.domain.SysLog;
import com.org.weixin.system.service.SysLogService;

/**
 * Title:日志切点类
 * <p>Description:实现日志拦截，记录日志</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月26日
 */
@Aspect
@Component
public class LogAopAction {
	
    //本地异常日志记录对象
	private  static  final Logger logger = LoggerFactory.getLogger(LogAopAction. class);
	
	@Resource
 	private SysLogService logService;
	
     //Controller层切点
    @Pointcut("@annotation(com.feinno.common.annotation.SystemLog)")
    public void controllerAspect() {}
    
    /**
     * <p>Description:操作异常记录</p>
     * @param point
     * @param e
     * @author fengshi on 2015年7月27日
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")  
    public void doAfterThrowing(JoinPoint point, Throwable e) {  
    	
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	SysLog log = new SysLog();
		String ip = IpUtil.getIpAddr(request);
		Map<String, Object> map = null;
		Long uid = null;
		if(null != SessionFace.getSessionUser(request)){
			uid = SessionFace.getSessionUser(request).getId();
		}
		try {
			map=getControllerMethodDescription(point);
		} catch (Exception ee) {
			logger.error("出错啦!!!{}",ee);
		}
		log.setDescription("执行方法异常:"+e);
	   	log.setExecEllapse(0L);
	   	log.setInvokeTime(new Date());
	   	log.setModule(map.get("module").toString());
	   	log.setMethods("执行方法异常:"+map.get("methods"));
	   	log.setIp(ip);
	   	log.setUserId(uid);
		try {
			logService.save(log);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
    
    /**
     * <p>Description:前置通知:用于拦截Controller层记录用户的操作</p>
     * @param point
     * @return
     * @author fengshi on 2015年7月27日
     */
    @Around("controllerAspect()")
     public Object doController(ProceedingJoinPoint point) {
    	
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	Object result = null;
		// 执行方法名
		String methodName = point.getSignature().getName();
		String className = point.getTarget().getClass().getSimpleName();
		SysLog log = new SysLog();
		Map<String, Object> map = null;
		Long uid = null;
		Long start = 0L;
		Long end = 0L;
		Long time = 0L;
		String ip = IpUtil.getIpAddr(request);
		if(null != SessionFace.getSessionUser(request)){
			uid = SessionFace.getSessionUser(request).getId();
		}
		// 当前用户
		try {
			map=getControllerMethodDescription(point);
			// 执行方法所消耗的时间
			start = System.currentTimeMillis();
			result = point.proceed();
			end = System.currentTimeMillis();
		    time = end - start;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
         try {
        	 log.setExecEllapse(time);
        	 log.setInvokeTime(new Date());
        	 log.setDescription(map.get("description").toString());
        	 log.setModule(map.get("module").toString());
        	 log.setMethods(map.get("methods").toString());
        	 log.setIp(ip);
        	 log.setUserId(uid);
        	 logService.save(log);
        	 logger.debug("==========================================通知开始==========================================");
        	 logger.debug("请求方法:{}.{}()",className,methodName);
        	 logger.debug("方法描述:{}",map);
        	 logger.debug("请求IP:{}",ip);
        	 logger.debug("==========================================通知结束==========================================");
        }  catch (Exception e) {
            logger.error("==========================================通知异常==========================================");
            logger.error("异常信息:{}", e.getMessage());
            logger.error("==========================================通知异常==========================================");
        }
         return result;
    }
    
    /**
     * <p>Description:获取注解中对方法的描述信息 用于Controller层注解</p>
     * @param joinPoint
     * @return
     * @throws Exception
     * @author fengshi on 2015年7月27日
     */
    @SuppressWarnings("rawtypes")
	public Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String targetName = joinPoint.getTarget().getClass().getName();
	    String methodName = joinPoint.getSignature().getName();
	    Object[] arguments = joinPoint.getArgs();
	    Class targetClass = Class.forName(targetName);
	    Method[] methods = targetClass.getMethods();
	     for (Method method : methods) {
	         if (method.getName().equals(methodName)) {
	            Class[] clazzs = method.getParameterTypes();
	             if (clazzs.length == arguments.length) {
	            	 String mo = method.getAnnotation(SystemLog.class).module();
	            	 String me = method.getAnnotation(SystemLog.class).methods();
	            	 String de = method.getAnnotation(SystemLog.class).description();
	            	 if(StringUtils.isBlank(mo)){
	            		 mo=targetName;
	            	 }
	            	 if(StringUtils.isBlank(me)){
	            		 me=methodName;
	            	 }
                	 if(StringUtils.isBlank(de)){
                		 de="执行成功!";
                	 }
                	 map.put("module", mo);
                	 map.put("methods", me);
                	 map.put("description", de);
	                 break;
	            }
	        }
	    }
	   return map;
    }
    
}
