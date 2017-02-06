/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.service;

import java.util.Map;

import com.cqliving.baidumap.domain.place.PlaceResult;
import com.cqliving.tool.common.Response;

/**
 * Title: 检索建议 Service
 * <p>Description: 根据用户输入关键字，返回建议词条，调用次数限制默认为2000次/天</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月13日
 * @see <a href="http://lbsyun.baidu.com/index.php?title=webapi/place-suggestion-api">参考文档</a>
 */
public interface PlaceSuggestionService {
	
	/**
	 * <p>Description: 城市内检索</p>
	 * @author Tangtao on 2016年4月14日
	 * @param ak 密钥（必须）
	 * @param query 建议关键字（必须，支持拼音）
	 * @param region 检索区域（市级以上行政区域，必须）
	 * @param map 其他参数集合
	 * @return
	 */
	Response<PlaceResult> suggestion(String ak, String query, String region, Map<String, String> map);
	
}