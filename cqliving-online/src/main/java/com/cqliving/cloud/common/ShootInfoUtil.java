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
public class ShootInfoUtil {
	
	/**
	 * <p>Description: 获取随手拍分享访问链接</p>
	 * @author Tangtao on 2016年7月20日
	 * @param id
	 * @param downLoadUrl
	 * @return
	 */
	public static String getShareUrl(Long id) {
		String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
		return webPath + "shoot/detail/" + id + ".html";
	}

}