/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.service;

import com.cqliving.baidumap.domain.ip.IPResult;
import com.cqliving.tool.common.Response;

/**
 * Title: IP定位Service
 * <p>Description: 根据IP返回对应位置信息，调用次数限制默认为10万次/天</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月19日
 * @see <a href="http://lbsyun.baidu.com/index.php?title=webapi/ip-api">参考文档</a>
 */
public interface IPService {
	
	/**
	 * <p>Description: 获取指定IP的位置信息</p>
	 * @author Tangtao on 2016年4月19日
	 * @param ak 密钥（必须）
	 * @param ip ip地址（必须）
	 * @return
	 */
	Response<IPResult> locate(String ak, String ip);
	
}