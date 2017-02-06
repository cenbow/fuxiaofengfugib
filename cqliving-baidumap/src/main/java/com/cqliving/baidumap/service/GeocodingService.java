/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.service;

import java.util.Map;

import com.cqliving.baidumap.domain.geocoding.AddressResult;
import com.cqliving.baidumap.domain.geocoding.LocationResult;
import com.cqliving.tool.common.Response;

/**
 * Title: 地址坐标转换 Service
 * <p>Description: 提供从地址到经纬度坐标或者从经纬度坐标到地址的转换服务，调用次数限制默认为6000次/天</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月13日
 * @see <a href="http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding">参考文档</a>
 */
public interface GeocodingService {
	
	/**
	 * <p>Description: 地址转换为坐标信息</p>
	 * @author Tangtao on 2016年4月14日
	 * @param ak 密钥（必须）
	 * @param address 请求参数集合（必须），最多支持100个字节
	 * @param map 请求参数集合
	 * @return
	 */
	Response<LocationResult> addressToLocation(String ak, String address, Map<String, String> map);
	
	/**
	 * <p>Description: 坐标转换为地址信息</p>
	 * @author Tangtao on 2016年4月14日
	 * @param ak 密钥（必须）
	 * @param lat 纬度（必须）
	 * @param lng 经度（必须）
	 * @param map 请求参数集合
	 * @return
	 */
	Response<AddressResult> locationToAddress(String ak, double lat, double lng, Map<String, String> map);
	
}