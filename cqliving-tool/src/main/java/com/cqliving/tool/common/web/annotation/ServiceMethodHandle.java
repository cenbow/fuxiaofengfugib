/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.tool.common.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2016年1月6日
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceMethodHandle {

	ExceptionHandle[] exceptionClass() default {};
	//manager的方法名
	String managerMethodName() default "";
}
