package com.cqliving.framework.common.dao.support;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionName {
	/**
	 * 存储过程调用名称
	 * 
	 * @return String
	 */
	String name();

	/**
	 * pojo中代表总行数的名称
	 * 
	 * @return String
	 */
	String countName();
}
