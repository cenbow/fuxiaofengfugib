/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月14日
 */
public class ParameterUtil {
	
	public static String serialize(Map<String, String> map) throws UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder();
		if (MapUtils.isNotEmpty(map)) {
			for (Entry<String, String> entry : map.entrySet()) {
				builder.append("&" + entry.getKey() + "=" + EncodeUtil.encode(entry.getValue()));
			}
		}
		return builder.toString();
	}

}