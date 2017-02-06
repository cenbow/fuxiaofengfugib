/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.jpush.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cqliving.jpush.common.ErrorCodes;
import com.cqliving.jpush.service.ReportService;
import com.cqliving.tool.common.Response;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.report.ReceivedsResult;
import cn.jpush.api.report.ReportClient;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月13日
 */
@Service
public class ReportServiceImpl implements ReportService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Override
	public Response<ReceivedsResult> getReceiveds(String masterSecret, String appKey, String... msgIdArray) {
		Response<ReceivedsResult> response = Response.newInstance();
		try {
			ReportClient client = new ReportClient(masterSecret, appKey);
			ReceivedsResult data = client.getReceiveds(msgIdArray);
			response.setData(data);
		} catch (APIConnectionException e) {
			LOGGER.error("送达统计API连接失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage(e.getMessage());
		} catch (APIRequestException e) {
			LOGGER.error("送达统计API请求失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("送达统计失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("送达统计失败");
		}
		return response;
	}

}