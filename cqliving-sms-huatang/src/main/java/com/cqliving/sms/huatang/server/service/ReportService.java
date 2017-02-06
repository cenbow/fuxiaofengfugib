/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.sms.huatang.server.service;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.sms.huatang.server.common.Constants;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.http.HttpClientUtil;
import com.google.common.collect.Maps;

/**
 * Title: 报告 Service
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月27日
 */
@Service
public class ReportService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);
	
	public Response<String> getSendReport(String userId, String sign, Date timestamp, Integer statusNum) {
		Response<String> response = Response.newInstance();
		try {
			Map<String, String> params = Maps.newHashMap();
			params.put("userid", userId);
			params.put("timestamp", DateUtil.format(timestamp, DateUtil.YYYYMMDDHHMMSS));
			params.put("sign", sign);
			params.put("statusNum", statusNum == null ? "" : statusNum.toString());
			params.put("action", "query");
			LOGGER.debug("调用获取发送报告接口：" + Constants.URL_STATUS_API);
			LOGGER.debug("调用参数：" + params);
			String data = HttpClientUtil.sendPostRequest(Constants.URL_STATUS_API, params, null, null);
			LOGGER.debug("调用结果：" + data);
//			handResult(data, response);
			response.setData(data);
		} catch (Exception e) {
			LOGGER.error("获取发送报告失败", e);
			response.setCode(ErrorCodes.FAILURE);
		}
		return response;
	}
	
	public Response<String> getCallReport(String userId, String sign, Date timestamp) {
		Response<String> response = Response.newInstance();
		try {
			Map<String, String> params = Maps.newHashMap();
			params.put("userid", userId);
			params.put("timestamp", DateUtil.format(timestamp, DateUtil.YYYYMMDDHHMMSS));
			params.put("sign", sign);
			params.put("action", "query");
			LOGGER.debug("调用获取上行短信接口：" + Constants.URL_CALL_API);
			LOGGER.debug("调用参数：" + params);
			String data = HttpClientUtil.sendPostRequest(Constants.URL_CALL_API, params, null, null);
			LOGGER.debug("调用结果：" + data);
//			handResult(data, response);
			response.setData(data);
		} catch (Exception e) {
			LOGGER.error("获取上行短信失败", e);
			response.setCode(ErrorCodes.FAILURE);
		}
		return response;
	}
	
}