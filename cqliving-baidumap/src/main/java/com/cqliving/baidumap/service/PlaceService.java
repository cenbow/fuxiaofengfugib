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
 * Title: 区域检索 Service
 * <p>Description: 查询某个区域的某类POI数据，或单个POI的详情，调用次数限制默认为2000次/天</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月13日
 * @see <a href="http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-placeapi">参考文档</a>
 */
public interface PlaceService {
	
	/**
	 * <p>Description: 查询POI点的详情信息，如好评，评价等</p>
	 * @author Tangtao on 2016年4月15日
	 * @param ak 密钥（必须）
	 * @param map 其他参数集合
	 * @param uids uid的集合，最多10个（必须）
	 * @return
	 */
	Response<PlaceResult> getDetail(String ak, Map<String, String> map, String... uids);
	
	/**
	 * <p>Description: 查询POI点的详情信息，如好评，评价等</p>
	 * @author Tangtao on 2016年4月15日
	 * @param ak 密钥（必须）
	 * @param scope 检索结果详细程度，默认1。{1: 则返回基本信息, 2: 返回检索POI详细信息}
	 * @param map 其他参数集合
	 * @param uids uid的集合，最多10个（必须）
	 * @return
	 */
	Response<PlaceResult> getDetail(String ak, int scope, Map<String, String> map, String... uids);
	
	/**
	 * <p>Description: 矩形区域检索</p>
	 * @author Tangtao on 2016年4月14日
	 * @param ak 密钥（必须）
	 * @param query 检索关键字（必须），关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”
	 * @param swLat 左下角坐标纬度（必须）
	 * @param swLng 左下角坐标经度（必须）
	 * @param neLat 右上角坐标纬度（必须）
	 * @param neLng 右上角坐标经度（必须）
	 * @param map 区域检索通用接口参数集合
	 * @return
	 */
	Response<PlaceResult> searchByBounds(String ak, String query, double swLat, double swLng, double neLat, double neLng, Map<String, String> map);

	/**
	 * <p>Description: 圆形区域检索</p>
	 * @author Tangtao on 2016年4月14日
	 * @param ak 密钥（必须）
	 * @param query 检索关键字（必须），关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”
	 * @param lat 圆心坐标纬度（必须）
	 * @param lng 圆心坐标经度（必须）
	 * @param map 区域检索通用接口参数集合
	 * @return
	 */
	Response<PlaceResult> searchByLocation(String ak, String query, double lat, double lng, Map<String, String> map);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年4月14日
	 * @param ak 密钥（必须）
	 * @param query 检索关键字（必须），关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”
	 * @param lat 检索中心点坐标纬度（必须）
	 * @param lng 检索中心点坐标经度（必须）
	 * @param radius 检索半径，单位为米
	 * @param map 区域检索通用接口参数集合
	 * @return
	 */
	Response<PlaceResult> searchByLocation(String ak, String query, double lat, double lng, int radius, Map<String, String> map);

	/**
	 * <p>Description: 城市内检索</p>
	 * @author Tangtao on 2016年4月14日
	 * @param ak 密钥（必须）
	 * @param query 检索关键字（必须）
	 * @param region 检索区域（市级以上行政区域，必须）
	 * @param map 区域检索通用接口参数集合
	 * @return
	 */
	Response<PlaceResult> searchByRegion(String ak, String query, String region, Map<String, String> map);
	
	/**
	 * <p>Description: 城市内检索</p>
	 * @author Tangtao on 2016年4月14日
	 * @param ak 密钥（必须）
	 * @param query 检索关键字（必须）
	 * @param region 检索区域（市级以上行政区域，必须）
	 * @param isCityLimit 是否只返回指定region（城市）内的POI，默认 false。{true: 是, false: 否}
	 * @param map 区域检索通用接口参数集合
	 * @return
	 */
	Response<PlaceResult> searchByRegion(String ak, String query, String region, boolean isCityLimit, Map<String, String> map);
	
}