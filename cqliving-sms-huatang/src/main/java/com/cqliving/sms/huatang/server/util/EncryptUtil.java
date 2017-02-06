/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.sms.huatang.server.util;

import java.util.Date;

import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.Crypt;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月26日
 */
public class EncryptUtil {
	
	/**
	 * <p>Description: 根据帐号、密码和时间戳生成签名</p>
	 * @author Tangtao on 2016年4月26日
	 * @param account 短信接口帐号
	 * @param pwd 短信接口密码
	 * @param timestamp 调用接口的时间戳
	 * @return
	 */
	public static String md5(String account, String pwd, Date timestamp) {
		String time = DateUtil.format(timestamp, DateUtil.YYYYMMDDHHMMSS);
		return Crypt.MD5(account + pwd + time).toLowerCase();
	}

}