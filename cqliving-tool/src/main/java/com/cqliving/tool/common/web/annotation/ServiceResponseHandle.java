/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.tool.common.web.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2016年1月6日
 */
public class ServiceResponseHandle extends WebApplicationObjectSupport{

	private Logger logger  = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	public Object serviceMethodHandle(ProceedingJoinPoint pjp) {
		//处理的类
		Object service = pjp.getTarget();
		
		ServiceHandleMapping serviceHandleMapping = service.getClass().getAnnotation(ServiceHandleMapping.class);
		
		if(null == serviceHandleMapping){
			return this.proceed(pjp);
		}
		Class<?> managerClass = serviceHandleMapping.managerClass();
		//manager对象
		Object manager = super.getApplicationContext().getBean(managerClass);
		//执行的方法
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method serivceMethod = methodSignature.getMethod();
		
		//自定义方法处理
		PersonalMethodHandle personalMethodHandle = serivceMethod.getAnnotation(PersonalMethodHandle.class);
		if(null != personalMethodHandle)return  this.proceed(pjp);
		
		ServiceMethodHandle serviceMethodHandle = serivceMethod.getAnnotation(ServiceMethodHandle.class);
		
		if(null == serviceMethodHandle){
			return  this.proceed(pjp);
		}
		
		String managerMethodName = serviceMethodHandle.managerMethodName();
		
		//如果managerMethodName为空，则直接调用service的方法；
		if(StringUtil.isEmpty(managerMethodName)){
			managerMethodName = serivceMethod.getName();
		}
		
		Response<Object> rp = Response.newInstance();
		try {
			Object result = MethodUtils.invokeMethod(manager, managerMethodName, pjp.getArgs(),methodSignature.getParameterTypes());//managerMethod.invoke(manager, pjp.getArgs());
			if(null != result && Response.class.isAssignableFrom(result.getClass())){
				rp = (Response<Object>) result;
			}else{
				rp = Response.newInstance();
				rp.setData(result);
			}
		} catch (InvocationTargetException e) {
			//manager的异常
			this.setErrorReponse(rp, serviceMethodHandle.exceptionClass(), e.getTargetException());
			logger.warn("",e);
		} catch (IllegalAccessException e) {
			rp.setCode(errorCode);
			rp.setMessage(e.getMessage());
			logger.warn("",e);
		} catch (IllegalArgumentException e) {
			rp.setCode(errorCode);
			rp.setMessage(e.getMessage());
			logger.error("",e);
		} catch (NoSuchMethodException e) {
			rp.setCode(errorCode);
			rp.setMessage(e.getMessage());
			logger.error("",e);
		} 
		return rp;
	}
	
	
	private Object proceed(ProceedingJoinPoint pjp){
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			logger.info("方法调用失败",e);
		}
		return null;
	}
	
	private void setErrorReponse(Response<Object> rp,ExceptionHandle[] exceptions,Throwable e){
		
		if(BusinessException.class.isAssignableFrom(e.getClass())){
			BusinessException busi = (BusinessException) e;
			rp.setCode(busi.getErrorCode());
			rp.setMessage(busi.getMessage());
			return;
		}
		if(!StringUtil.isEmpty(exceptions)){
			for(ExceptionHandle exceptionHandle : exceptions){
				if(e.getClass().isAssignableFrom(exceptionHandle.exception())){
					rp.setCode(exceptionHandle.errorCode());
					rp.setMessage(exceptionHandle.errorMsg());
					return;
				}
			}
		}
		rp.setCode(errorCode);
		rp.setMessage(e.getMessage());
	}
	
	private int errorCode;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
