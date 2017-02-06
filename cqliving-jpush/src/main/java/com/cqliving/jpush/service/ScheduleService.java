package com.cqliving.jpush.service;

import java.util.Date;

import com.cqliving.tool.common.Response;

import cn.jpush.api.schedule.ScheduleResult;
import cn.jpush.api.schedule.model.SchedulePayload;

/**
 * Title: 定时任务 Service
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年5月9日
 */
public interface ScheduleService {
	
	/**
	 * <p>Description: 定时任务</p>
	 * @author Tangtao on 2016年5月9日
	 * @param masterSecret 应用 Master Secret
	 * @param appKey 应用 AppKey
	 * @param sendTime 推送时间
	 * @param message 推送内容
	 * @return
	 */
	Response<ScheduleResult> createSchedule(String masterSecret, String appKey, Date sendTime, String message);
	
	/**
	 * <p>Description: 自定义定时任务</p>
	 * @author Tangtao on 2016年5月9日
	 * @param masterSecret 应用 Master Secret
	 * @param appKey 应用 AppKey
	 * @param payload 定时任务对象
	 * @return
	 * @see <a href="http://docs.jpush.io/server/rest_api_push_schedule/#schedule">参考文档</a>
	 */
	Response<ScheduleResult> createSchedule(String masterSecret, String appKey, SchedulePayload payload);
	
}