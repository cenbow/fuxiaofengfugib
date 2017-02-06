/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common;

import org.apache.commons.lang3.StringUtils;

import com.cqliving.tool.utils.RandomUtils;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月25日
 */
public class AppInfoUtil {
	
	/**
	 * <p>Description: 获取随机用户昵称，默认8位随机字符</p>
	 * @author Tangtao on 2016年7月5日
	 * @param prefix
	 * @return
	 */
	public static String getRandomNickname(String prefix) {
		return StringUtils.defaultString(prefix, "用户") + RandomUtils.generateString(8);
	}
	
	/**
	 * <p>Description: 获取随机用户昵称</p>
	 * @author Tangtao on 2016年7月5日
	 * @param prefix
	 * @param length
	 * @return
	 */
	public static String getRandomNickname(String prefix, int length) {
		return StringUtils.defaultString(prefix, "用户") + RandomUtils.generateString(length);
	}
	
}