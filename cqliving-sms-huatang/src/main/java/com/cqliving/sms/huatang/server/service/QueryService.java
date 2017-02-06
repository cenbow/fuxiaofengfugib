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

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.sms.huatang.server.common.Constants;
import com.cqliving.sms.huatang.server.domain.QueryResult;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.http.HttpClientUtil;
import com.google.common.collect.Maps;

/**
 * Title: 查询 Service
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月27日
 */
@Service
public class QueryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QueryService.class);
	
	/**
	 * <p>Description: 余额及已发送量查询</p>
	 * @author Tangtao on 2016年4月27日
	 * @param userId
	 * @param sign
	 * @param timestamp
	 * @return
	 */
	public Response<QueryResult> getOverage(String userId, String sign, Date timestamp) {
		Response<QueryResult> response = Response.newInstance();
		try {
			Map<String, String> params = Maps.newHashMap();
			params.put("userid", userId);
			params.put("timestamp", DateUtil.format(timestamp, DateUtil.YYYYMMDDHHMMSS));
			params.put("sign", sign);
			params.put("action", "overage");
			LOGGER.debug("调用余额及已发送量查询接口：" + Constants.URL_SMS);
			LOGGER.debug("调用参数：" + params);
			String data = HttpClientUtil.sendPostRequest(Constants.URL_SMS, params, null, null);
			LOGGER.debug("调用结果：" + data);
			handResult(data, response);
		} catch (Exception e) {
			LOGGER.error("余额及已发送量查询失败", e);
			response.setCode(ErrorCodes.FAILURE);
		}
		return response;
	}

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年10月9日
	 * @param xml
	 * @param response
	 */
	private void handResult(String xml, Response<QueryResult> response) {
		try {
			Document document = DocumentHelper.parseText(xml);
			//调用结果
			String status = document.selectSingleNode("/returnsms/returnstatus").getText();
			if ("Sucess".equals(status)) {	//此接口 Sucess 表示成功，而不是 Success
				response.setCode(ErrorCodes.SUCCESS);
			} else {
				response.setCode(ErrorCodes.FAILURE);
			}
			//返回信息
			String msg = document.selectSingleNode("/returnsms/message").getText();
			response.setMessage(msg);
			//业务字段
			QueryResult result = new QueryResult();
			String overage = document.selectSingleNode("/returnsms/overage").getText();
			String sendTotal = document.selectSingleNode("/returnsms/sendTotal").getText();
			result.setOverage(Long.valueOf(overage));
			result.setSendTotal(Long.valueOf(sendTotal));
			response.setData(result);
		} catch (DocumentException e) {
			response.setCode(ErrorCodes.FAILURE);
			LOGGER.error("解析返回结果失败", e);
			e.printStackTrace();
		}
	}
	
}