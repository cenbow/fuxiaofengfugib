package com.org.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
  
/**
 * Title:自定义日志
 * <p>Description:实现拦截controller并记录日志</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月26日
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface SystemLog {  
  
	String module() default "";  //模块名称 系统管理-用户管理－列表页面
	String methods() default "";  //新增用户
    String description() default "";  //
  
}
