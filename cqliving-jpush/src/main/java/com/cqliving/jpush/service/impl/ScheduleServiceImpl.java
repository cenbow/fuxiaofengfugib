/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.jpush.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cqliving.framework.utils.StringUtil;
import com.cqliving.jpush.common.ErrorCodes;
import com.cqliving.jpush.service.ScheduleService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.schedule.ScheduleClient;
import cn.jpush.api.schedule.ScheduleResult;
import cn.jpush.api.schedule.model.SchedulePayload;
import cn.jpush.api.schedule.model.TriggerPayload;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年5月9日
 */
public class ScheduleServiceImpl implements ScheduleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);

	@Override
	public Response<ScheduleResult> createSchedule(String masterSecret, String appKey, Date sendTime, String message) {
		Response<ScheduleResult> response = Response.newInstance();
		try {
			ScheduleClient client = new ScheduleClient(masterSecret, appKey);
			PushPayload push = PushPayload.alertAll(message);
			TriggerPayload trigger = TriggerPayload.newBuilder()
					.setSingleTime(DateUtil.format(sendTime))
					.buildSingle();
			SchedulePayload schedulePayload = SchedulePayload.newBuilder()
					.setEnabled(true)
					.setName(StringUtil.getUUID())
					.setPush(push)
					.setTrigger(trigger)
					.build();
			ScheduleResult data = client.createSchedule(schedulePayload);
			response.setData(data);
		} catch (APIConnectionException e) {
			LOGGER.error("定时任务API连接失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage(e.getMessage());
		} catch (APIRequestException e) {
			LOGGER.error("定时任务API请求失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getErrorMessage());
		} catch (Exception e) {
			LOGGER.error("定时任务失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("定时任务失败");
		}
		return response;
	}

	@Override
	public Response<ScheduleResult> createSchedule(String masterSecret, String appKey, SchedulePayload payload) {
		Response<ScheduleResult> response = Response.newInstance();
		try {
			ScheduleClient client = new ScheduleClient(masterSecret, appKey);
			ScheduleResult data = client.createSchedule(payload);
			response.setData(data);
		} catch (APIConnectionException e) {
			LOGGER.error("定时任务API连接失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage(e.getMessage());
		} catch (APIRequestException e) {
			LOGGER.error("定时任务API请求失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getErrorMessage());
		} catch (Exception e) {
			LOGGER.error("定时任务失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("定时任务失败");
		}
		return response;
	}

}