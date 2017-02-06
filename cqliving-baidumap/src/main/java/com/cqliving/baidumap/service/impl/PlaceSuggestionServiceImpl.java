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
import com.cqliving.baidumap.domain.place.PlaceResult;
import com.cqliving.baidumap.service.PlaceSuggestionService;
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
public class PlaceSuggestionServiceImpl implements PlaceSuggestionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PlaceSuggestionServiceImpl.class);

	@Override
	public Response<PlaceResult> suggestion(String ak, String query, String region, Map<String, String> map) {
		Response<PlaceResult> response = Response.newInstance();
		try {
			Assert.notNull(ak, "缺少密钥");
			Assert.notNull(query, "缺少检索关键字");
			StringBuilder builder = new StringBuilder("?output=json");
			builder.append("&ak=" + ak);
			builder.append("&q=" + EncodeUtil.encode(query));
			builder.append("&region=" + EncodeUtil.encode(region));
			builder.append(ParameterUtil.serialize(map));
			String data = HttpClientUtil.sendGetRequest(Constants.URL_PLACE_SUGGESTION + builder.toString(), null);
			PlaceResult result = JSONObject.parseObject(data, PlaceResult.class);
			response.setData(result);
		} catch (IllegalArgumentException e) { 
			LOGGER.error("操作失败，非法参数", e);
			response.setCode(ErrorCodes.ILLEGAL_ARGUMENT);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("操作失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("操作失败");
		}
		return response;
	}

}