package com.cqliving.jpush.service;

import com.cqliving.tool.common.Response;

import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

/**
 * Title: 推送 Service
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月12日
 */
public interface PushService {
	
	/**
	 * <p>Description: 发送全平台文本推送</p>
	 * @author Tangtao on 2016年4月12日
	 * @param masterSecret 应用 Master Secret
	 * @param appKey 应用 AppKey
	 * @param message 推送内容
	 * @return
	 */
	Response<PushResult> sendPush(String masterSecret, String appKey, String message);
	
	/**
	 * <p>Description: 发送自定义推送</p>
	 * @author Tangtao on 2016年4月12日
	 * @param masterSecret 应用 Master Secret
	 * @param appKey 应用 AppKey
	 * @param pushPayload 推送对象
	 * @return
	 * @see <a href="http://docs.jpush.io/server/rest_api_v3_push/#_7">参考文档</a>
	 */
	Response<PushResult> sendPush(String masterSecret, String appKey, PushPayload pushPayload);
	
	/**
	 * <p>Description: 验证推送调用是否能够成功，不会向用户发送任何消息</p>
	 * @author Tangtao on 2016年4月12日
	 * @param masterSecret 应用 Master Secret
	 * @param appKey 应用 AppKey
	 * @param payloadString 推送对象
	 * @return
	 * @see <a href="http://docs.jpush.io/server/rest_api_v3_push/#_7">参考文档</a>
	 */
	Response<PushResult> sendPushValidate(String masterSecret, String appKey, PushPayload pushPayload);

}