/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.sms.huatang.server.controller;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.log.aop.annotation.EduLog;
import com.cqliving.sms.huatang.server.domain.QueryResult;
import com.cqliving.sms.huatang.server.domain.SendResult;
import com.cqliving.sms.huatang.server.service.QueryService;
import com.cqliving.sms.huatang.server.service.SendService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月26日
 */
@Controller
@RequestMapping({"sms"})
public class SmsController {
	
	@Autowired
	private QueryService queryService;
	@Autowired
	private SendService sendService;

	/**
	 * <p>Description: 发送短信</p>
	 * @author Tangtao on 2016年4月26日
	 * @param request
	 * @param userId
	 * @param sign
	 * @param timestamp
	 * @param mobileList
	 * @param content
	 * @param sendTime
	 * @param extno
	 * @return
	 */
	@ResponseBody
	@RequestMapping({"send"})
	@EduLog(action = "send", module = "sms", moduleName = "短信", actionName = "发送短信")
	public Response<SendResult> send(HttpServletRequest request, 
			@RequestParam String userId, 
			@RequestParam String sign, 
			@RequestParam String timestamp, 
			@RequestParam String mobileList, 
			@RequestParam String content, Date sendTime, Integer extno) {
		Response<SendResult> response = sendService.send(userId, sign, DateUtil.parse(timestamp, DateUtil.YYYYMMDDHHMMSS), Arrays.asList(mobileList.split(",")), content, sendTime, extno);
		return response;
	}
	
	/**
	 * <p>Description: 余额及发送量查询</p>
	 * @author Tangtao on 2016年10月9日
	 * @param request
	 * @param userId
	 * @param sign
	 * @param timestamp
	 * @return
	 */
	@ResponseBody
	@RequestMapping({"query"})
	@EduLog(action = "query", module = "sms", moduleName = "短信", actionName = "短信余额查询")
	public Response<QueryResult> query(HttpServletRequest request, 
			@RequestParam String userId, 
			@RequestParam String sign, 
			@RequestParam String timestamp) {
		Response<QueryResult> response = queryService.getOverage(userId, sign, DateUtil.parse(timestamp, DateUtil.YYYYMMDDHHMMSS));
		return response;
	}
	
}