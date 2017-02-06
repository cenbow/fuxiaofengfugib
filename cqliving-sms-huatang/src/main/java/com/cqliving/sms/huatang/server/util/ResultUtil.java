/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.sms.huatang.server.util;

import com.cqliving.tool.common.Response;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月26日
 */
public class ResultUtil {
	
	public static <T> Response<T> getResponse(Class<T> clazz, String xml) {
		return new Response<T>();
	}

}