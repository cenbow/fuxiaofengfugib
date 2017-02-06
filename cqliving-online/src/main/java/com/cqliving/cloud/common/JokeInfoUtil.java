/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.constant.PropertyKey;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月20日
 */
public class JokeInfoUtil {
	
	/**
	 * <p>Description: 获取段子分享访问链接</p>
	 * @author Tangtao on 2016年7月20日
	 * @param id
	 * @return
	 */
	public static String getShareUrl(Long id) {
		String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
		return webPath + "joke/detail/" + id + ".html";
	}

}