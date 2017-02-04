/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.jywth.common;



/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月3日
 */
public class RandomUtil {

	public static String random(int length){
		long mili = System.currentTimeMillis();
		String str = String.valueOf(mili);
		
		str = str.substring(str.length()-length);
		
		return str;
	}
}
