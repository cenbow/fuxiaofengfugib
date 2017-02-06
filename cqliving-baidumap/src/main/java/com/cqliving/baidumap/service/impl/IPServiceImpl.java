/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;
import com.cqliving.baidumap.common.Constants;
import com.cqliving.baidumap.common.ErrorCodes;
import com.cqliving.baidumap.domain.ip.IPResult;
import com.cqliving.baidumap.service.IPService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.http.HttpClientUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月19日
 */
@Service
public class IPServiceImpl implements IPService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IPServiceImpl.class);

	@Override
	public Response<IPResult> locate(String ak, String ip) {
		Response<IPResult> response = Response.newInstance();
		try {
			Assert.notNull(ak, "缺少密钥");
			Assert.notNull(ip, "缺少地址");
			StringBuilder builder = new StringBuilder("?ak=" + ak);
			builder.append("&ip=" + ip);
			String data = HttpClientUtil.sendGetRequest(Constants.URL_IP_LOCATION + builder.toString(), null);
			IPResult result = JSONObject.parseObject(data, IPResult.class);
			response.setData(result);
		} catch (IllegalArgumentException e) { 
			LOGGER.error("定位失败，非法参数", e);
			response.setCode(ErrorCodes.ILLEGAL_ARGUMENT);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("定位失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("定位失败");
		}
		return response;
	}

}