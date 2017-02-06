/**
 * Copyright (c) aimartt, Inc. All rights reserved.
 */
package com.cqliving.jpush.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cqliving.jpush.common.ErrorCodes;
import com.cqliving.jpush.service.PushService;
import com.cqliving.tool.common.Response;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

/**
 * <p></p>
 * Copyright (c) cqliving
 * @author Tangtao on 2016年4月12日
 */
@Service
public class PushServiceImpl implements PushService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PushServiceImpl.class);

	@Override
	public Response<PushResult> sendPush(String masterSecret, String appKey, String message) {
		Response<PushResult> response = Response.newInstance();
		try {
			PushClient client = new PushClient(masterSecret, appKey);
			PushPayload pushPayload = PushPayload.alertAll(message);
			PushResult data = client.sendPush(pushPayload);
			response.setData(data);
			LOGGER.debug("推送结果：msg_id=" + data.msg_id + ",sendno=" + data.sendno);
		} catch (APIConnectionException e) {
			LOGGER.error("推送API连接失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage(e.getMessage());
		} catch (APIRequestException e) {
			LOGGER.error("推送API请求失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getErrorMessage());
		} catch (Exception e) {
			LOGGER.error("推送失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("推送失败");
		}
		return response;
	}

	@Override
	public Response<PushResult> sendPush(String masterSecret, String appKey, PushPayload pushPayload) {
		Response<PushResult> response = Response.newInstance();
		try {
			PushClient client = new PushClient(masterSecret, appKey);
			PushResult data = client.sendPush(pushPayload);
			response.setData(data);
			LOGGER.debug("推送结果：msg_id=" + data.msg_id + ",sendno=" + data.sendno);
		} catch (APIConnectionException e) {
			LOGGER.error("推送API连接失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage(e.getMessage());
		} catch (APIRequestException e) {
			LOGGER.error("推送API请求失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getErrorMessage());
		} catch (Exception e) {
			LOGGER.error("推送失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("推送失败");
		}
		return response;
	}

	@Override
	public Response<PushResult> sendPushValidate(String masterSecret, String appKey, PushPayload pushPayload) {
		Response<PushResult> response = Response.newInstance();
		try {
			PushClient client = new PushClient(masterSecret, appKey);
			PushResult data = client.sendPushValidate(pushPayload);
			response.setData(data);
			LOGGER.debug("推送结果：msg_id=" + data.msg_id + ",sendno=" + data.sendno);
		} catch (APIConnectionException e) {
			LOGGER.error("推送校验API连接失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage(e.getMessage());
		} catch (APIRequestException e) {
			LOGGER.error("推送校验API请求失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getErrorMessage());
		} catch (Exception e) {
			LOGGER.error("推送校验失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("推送校验失败");
		}
		return response;
	}

}
