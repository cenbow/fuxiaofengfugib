/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;
import com.cqliving.baidumap.common.Constants;
import com.cqliving.baidumap.common.ErrorCodes;
import com.cqliving.baidumap.domain.geocoding.AddressResult;
import com.cqliving.baidumap.domain.geocoding.LocationResult;
import com.cqliving.baidumap.service.GeocodingService;
import com.cqliving.baidumap.util.EncodeUtil;
import com.cqliving.baidumap.util.ParameterUtil;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.http.HttpClientUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月14日
 */
@Service
public class GeocodingServiceImpl implements GeocodingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GeocodingServiceImpl.class);

	@Override
	public Response<LocationResult> addressToLocation(String ak, String address, Map<String, String> map) {
		Response<LocationResult> response = Response.newInstance();
		try {
			Assert.notNull(ak, "缺少密钥");
			Assert.notNull(address, "缺少地址");
			StringBuilder builder = new StringBuilder("?output=json");
			builder.append("&ak=" + ak);
			builder.append("&address=" + EncodeUtil.encode(address));
			builder.append(ParameterUtil.serialize(map));
			String data = HttpClientUtil.sendGetRequest(Constants.URL_GEOCODER + builder.toString(), null);
			LocationResult result = JSONObject.parseObject(data, LocationResult.class);
			response.setData(result);
		} catch (IllegalArgumentException e) { 
			LOGGER.error("转换失败，非法参数", e);
			response.setCode(ErrorCodes.ILLEGAL_ARGUMENT);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("转换失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("转换失败");
		}
		return response;
	}

	@Override
	public Response<AddressResult> locationToAddress(String ak, double lat, double lng, Map<String, String> map) {
		Response<AddressResult> response = Response.newInstance();
		try {
			Assert.notNull(ak, "缺少密钥");
			StringBuilder builder = new StringBuilder("?output=json");
			builder.append("&ak=" + ak);
			builder.append("&location=" + lat + "," + lng);
			builder.append(ParameterUtil.serialize(map));
			String data = HttpClientUtil.sendGetRequest(Constants.URL_GEOCODER + builder.toString(), null);
			AddressResult result = JSONObject.parseObject(data, AddressResult.class);
			response.setData(result);
		} catch (IllegalArgumentException e) { 
			LOGGER.error("转换失败，非法参数", e);
			response.setCode(ErrorCodes.ILLEGAL_ARGUMENT);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("转换失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("转换失败");
		}
		return response;
	}
	
}