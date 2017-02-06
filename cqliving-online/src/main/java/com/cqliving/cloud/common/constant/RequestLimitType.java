/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common.constant;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Title: 请求限制操作类型
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月9日
 */
public class RequestLimitType {
	
	/** 注册 */
	public static final Byte TYPE0 = 0;
	/** 登录 */
	public static final Byte TYPE1 = 1;
	/** 找回密码 */
	public static final Byte TYPE2 = 2;
	
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "注册");
		allTypes.put(TYPE1, "登录");
		allTypes.put(TYPE2, "找回密码");
	}

}