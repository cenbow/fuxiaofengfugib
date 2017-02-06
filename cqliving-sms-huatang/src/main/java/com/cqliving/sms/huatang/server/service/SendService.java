/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.sms.huatang.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.sms.huatang.server.common.Constants;
import com.cqliving.sms.huatang.server.domain.SendResult;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.http.HttpClientUtil;
import com.google.common.collect.Maps;

/**
 * Title: 短信发送 Service
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月26日
 */
@Service
public class SendService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendService.class);
	
	/**
	 * <p>Description: 非法关键字查询</p>
	 * @author Tangtao on 2016年4月27日
	 * @param userId
	 * @param sign
	 * @param timestamp
	 * @param content
	 * @return
	 */
	public Response<String> checkKeyWord(String userId, String sign, Date timestamp, String content) {
		Response<String> response = Response.newInstance();
		try {
			Map<String, String> params = Maps.newHashMap();
			params.put("userid", userId);
			params.put("timestamp", DateUtil.format(timestamp, DateUtil.YYYYMMDDHHMMSS));
			params.put("sign", sign);
			params.put("content", content);
			params.put("action", "checkkeyword");
			LOGGER.debug("调用非法关键字查询接口：" + Constants.URL_SMS);
			LOGGER.debug("调用参数：" + params);
			String data = HttpClientUtil.sendPostRequest(Constants.URL_SMS, params, null, null);
			LOGGER.debug("调用结果：" + data);
			response.setData(data);
		} catch (Exception e) {
			LOGGER.error("非法关键字查询失败", e);
			response.setCode(ErrorCodes.FAILURE);
		}
		return response;
	}
	
	/**
	 * <p>Description: 发送短信</p>
	 * @author Tangtao on 2016年4月26日
	 * @param userId 企业 id
	 * @param sign 签名（使用 账号+密码+时间戳 生成MD5字符串作为签名。MD5生成32位，且需要小写）
	 * @param timestamp 时间戳
	 * @param mobileList 被叫号码
	 * @param content 短信内容，需在末尾包含签名，如【新华龙】
	 * @param sendTime 发送时间，为空时表示立即发送
	 * @param extno 扩展子号（可选）
	 * @return
	 */
	public Response<SendResult> send(String userId, String sign, Date timestamp, List<String> mobileList, String content, Date sendTime, Integer extno) {
		Response<SendResult> response = Response.newInstance();
		try {
			Map<String, String> params = Maps.newHashMap();
			params.put("userid", userId);
			params.put("timestamp", DateUtil.format(timestamp, DateUtil.YYYYMMDDHHMMSS));
			params.put("sign", sign);
			params.put("mobile", StringUtils.join(mobileList, ","));
			params.put("content", content);
			params.put("sendTime", sendTime == null ? "" : DateUtil.format(sendTime, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
			params.put("extno", extno == null ? "" : extno.toString());
			params.put("action", "send");
			LOGGER.debug("调用发送短信接口");
			LOGGER.debug("调用参数：" + params);
			String data = HttpClientUtil.sendPostRequest(Constants.URL_SMS, params, null, null);
			LOGGER.debug("调用结果：" + data);
			handResult(data, response);
		} catch (Exception e) {
			LOGGER.error("发送短信失败", e);
			response.setCode(ErrorCodes.FAILURE);
		}
		return response;
	}
	
	private void handResult(String xml, Response<SendResult> response) {
		try {
			Document document = DocumentHelper.parseText(xml);
			//调用结果
			String status = document.selectSingleNode("/returnsms/returnstatus").getText();
			if ("Success".equals(status)) {
				response.setCode(ErrorCodes.SUCCESS);
			} else {
				response.setCode(ErrorCodes.FAILURE);
			}
			//返回信息
			String msg = document.selectSingleNode("/returnsms/message").getText();
			response.setMessage(msg);
			//业务字段
			SendResult result = new SendResult();
			String remainPointStr = document.selectSingleNode("/returnsms/remainpoint").getText();
			String taskIdStr = document.selectSingleNode("/returnsms/taskID").getText();
			String successCountsStr = document.selectSingleNode("/returnsms/successCounts").getText();
			result.setRemainPoint(Integer.parseInt(remainPointStr));
			result.setSuccessCounts(Integer.parseInt(successCountsStr));
			result.setTaskId(Long.parseLong(taskIdStr));
			response.setData(result);
		} catch (DocumentException e) {
			response.setCode(ErrorCodes.FAILURE);
			LOGGER.error("解析返回结果失败", e);
			e.printStackTrace();
		}
	}
	
}