/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;
import com.cqliving.baidumap.common.Constants;
import com.cqliving.baidumap.common.ErrorCodes;
import com.cqliving.baidumap.domain.place.PlaceResult;
import com.cqliving.baidumap.service.PlaceService;
import com.cqliving.baidumap.util.EncodeUtil;
import com.cqliving.baidumap.util.ParameterUtil;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.http.HttpClientUtil;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月13日
 */
@Service
public class PlaceServiceImpl implements PlaceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PlaceServiceImpl.class);

	@Override
	public Response<PlaceResult> getDetail(String ak, Map<String, String> map, String... uids) {
		Response<PlaceResult> response = Response.newInstance();
		try {
			Assert.notNull(ak, "缺少密钥");
			Assert.notEmpty(uids, "缺少参数：uids");
			StringBuilder builder = new StringBuilder("?output=json");
			builder.append("&ak=" + ak);
			if (uids.length == 1) {
				builder.append("&uid=" + uids[0]);
			} else {
				builder.append("&uids=" + StringUtils.join(uids, ","));
			}
			builder.append(ParameterUtil.serialize(map));
			String data = HttpClientUtil.sendGetRequest(Constants.URL_PLACE_DETAIL + builder.toString(), null);
			data = handleSingleResult(data);
			PlaceResult result = JSONObject.parseObject(data, PlaceResult.class);
			response.setData(result);
		} catch (IllegalArgumentException e) { 
			LOGGER.error("检索失败，非法参数", e);
			response.setCode(ErrorCodes.ILLEGAL_ARGUMENT);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("检索失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("检索失败");
		}
		return response;
	}

	@Override
	public Response<PlaceResult> getDetail(String ak, int scope, Map<String, String> map, String... uids) {
		if (map == null) {
			map = Maps.newHashMap();
		}
		map.put("scope", String.valueOf(scope));
		return getDetail(ak, map, uids);
	}

	@Override
	public Response<PlaceResult> searchByBounds(String ak, String query, double swLat, double swLng, double neLat, double neLng, Map<String, String> map) {
		Response<PlaceResult> response = Response.newInstance();
		try {
			Assert.notNull(ak, "缺少密钥");
			Assert.notNull(query, "缺少检索关键字");
			StringBuilder builder = new StringBuilder("?output=json");
			builder.append("&ak=" + ak);
			builder.append("&q=" + EncodeUtil.encode(query));
			builder.append("&bds=" + swLat + "," + swLng + "," + neLat + "," + neLng);
			builder.append(ParameterUtil.serialize(map));
			String data = HttpClientUtil.sendGetRequest(Constants.URL_PLACE_SEARCH + builder.toString(), null);
			data = handleSingleResult(data);
			PlaceResult result = JSONObject.parseObject(data, PlaceResult.class);
			response.setData(result);
		} catch (IllegalArgumentException e) { 
			LOGGER.error("检索失败，非法参数", e);
			response.setCode(ErrorCodes.ILLEGAL_ARGUMENT);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("检索失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("检索失败");
		}
		return response;
	}

	@Override
	public Response<PlaceResult> searchByLocation(String ak, String query, double lat, double lng, Map<String, String> map) {
		Response<PlaceResult> response = Response.newInstance();
		try {
			Assert.notNull(ak, "缺少密钥");
			Assert.notNull(query, "缺少检索关键字");
			StringBuilder builder = new StringBuilder("?output=json");
			builder.append("&ak=" + ak);
			builder.append("&q=" + EncodeUtil.encode(query));
			builder.append("&location=" + lat + "," + lng);
			builder.append(ParameterUtil.serialize(map));
			String data = HttpClientUtil.sendGetRequest(Constants.URL_PLACE_SEARCH + builder.toString(), null);
			data = handleSingleResult(data);
			PlaceResult result = JSONObject.parseObject(data, PlaceResult.class);
			response.setData(result);
		} catch (IllegalArgumentException e) { 
			LOGGER.error("检索失败，非法参数", e);
			response.setCode(ErrorCodes.ILLEGAL_ARGUMENT);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("检索失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("检索失败");
		}
		return response;
	}

	@Override
	public Response<PlaceResult> searchByLocation(String ak, String query, double lat, double lng, int radius, Map<String, String> map) {
		if (map == null) {
			map = Maps.newHashMap();
		}
		map.put("r", String.valueOf(radius));
		return searchByLocation(ak, query, lat, lng, map);
	}

	@Override
	public Response<PlaceResult> searchByRegion(String ak, String query, String region, Map<String, String> map) {
		Response<PlaceResult> response = Response.newInstance();
		try {
			Assert.notNull(ak, "缺少密钥");
			Assert.notNull(query, "缺少检索关键字");
			StringBuilder builder = new StringBuilder("?output=json");
			builder.append("&ak=" + ak);
			builder.append("&q=" + EncodeUtil.encode(query));
			builder.append("&region=" + region);
			builder.append(ParameterUtil.serialize(map));
			String data = HttpClientUtil.sendGetRequest(Constants.URL_PLACE_SEARCH + builder.toString(), null);
			data = handleSingleResult(data);
			PlaceResult result = JSONObject.parseObject(data, PlaceResult.class);
			response.setData(result);
		} catch (IllegalArgumentException e) { 
			LOGGER.error("检索失败，非法参数", e);
			response.setCode(ErrorCodes.ILLEGAL_ARGUMENT);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("检索失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("检索失败");
		}
		return response;
	}

	@Override
	public Response<PlaceResult> searchByRegion(String ak, String query, String region, boolean isCityLimit, Map<String, String> map) {
		if (map == null) {
			map = Maps.newHashMap();
		}
		map.put("city_limit", String.valueOf(isCityLimit));
		return searchByRegion(ak, query, region, map);
	}

	/**
	 * <p>Description: 当返回数据为单条记录时，拼上一对中括号，保证转换正确</p>
	 * @author Tangtao on 2016年4月15日
	 * @param data 结果 json 字符串
	 * @return
	 */
	private String handleSingleResult(String data) {
		if (!data.contains("result\":[{") && !data.contains("results\":[{")) {
			data = data.replace("result\":{", "result\":[{").substring(0, data.length() - 1) + "]}";
		}
		return data;
	}

}