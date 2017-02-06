/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月14日
 */
public class EncodeUtil {
	
	/**
	 * <p>Description: 对字符串进行 UTF-8 编码</p>
	 * @author Tangtao on 2016年4月14日
	 * @param str 要编码的字符串
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str, "utf-8");
	}
	
}