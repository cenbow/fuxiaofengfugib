/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.jpush.common;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cqliving.tool.common.util.encrypt.Base64Util;
import com.google.common.collect.Maps;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月20日
 */
public class PushUtil {
	
	/**
	 * <p>Description: 构造推送对象</p>
	 * @author Tangtao on 2016年7月20日
	 * @param id 主键
	 * @param sourceId 内容 id
	 * @param sourceType 业务类型{1:新闻,2:问政,3:商情,4:随手拍,5:段子,6:活动,7:话题}
	 * @param detailViewType 客户端展现类型{1:图文新闻,2:普通新闻,3:专题新闻,4:随手拍,5:段子,7:话题}
	 * @param commentType 是否允许评论{0:允许,1:不允许}
	 * @param url 跳转的 Url，外链的情况直接返回外链
	 * @param shareUrl 分享的 Url
	 * @param shareTitle 分享的标题
	 * @param title 标题
	 * @param synopsis 简介
	 * @param shareImgUrl 分享图片地址
	 * @param registrationId 指定接收者的设备 id，逗号分隔
	 * @param iosApnProduction 指定IOS平台推送环境{true: 生成环境, false: 开发环境, 为<tt>null</tt>时为开发环境}
	 * @return
	 */
	public static PushPayload create(Long id, Long sourceId, Byte sourceType, Byte detailViewType, Byte commentType, String url, String shareUrl, String shareTitle, String title, String synopsis, String shareImgUrl, String registrationId, Boolean iosApnProduction) {
		Map<String, String> extras = Maps.newHashMap();
		extras.put("id", id + "");	
		extras.put("sourceId", sourceId + "");	
		extras.put("sourceType", sourceType + "");	
		extras.put("detailViewType", detailViewType + "");	
		extras.put("commentType", commentType + "");	
		extras.put("url", StringUtils.defaultString(url));	
		extras.put("shareUrl", StringUtils.defaultString(shareUrl));	
		extras.put("shareTitle", Base64Util.encodeBase64(StringUtils.defaultString(shareTitle)));	
		extras.put("title", Base64Util.encodeBase64(title));	
		extras.put("synopsis", Base64Util.encodeBase64(synopsis));	
		extras.put("shareImgUrl", StringUtils.defaultString(shareImgUrl));	
		IosNotification iosNotification = IosNotification.newBuilder().setAlert(title).setContentAvailable(true).addExtras(extras).build();
		AndroidNotification androidNotification = AndroidNotification.newBuilder().setAlert(title).addExtras(extras).build();
		Audience audience = StringUtils.isNotBlank(registrationId) ? Audience.registrationId(registrationId.split(",")) : Audience.all();
		PushPayload pushPayload = PushPayload.newBuilder()
				.setNotification(Notification.newBuilder().addPlatformNotification(iosNotification).addPlatformNotification(androidNotification).build())
				.setMessage(Message.newBuilder().setTitle(title).setMsgContent(synopsis).addExtras(extras).build())
				.setAudience(audience)
				.setPlatform(Platform.all())
				.setOptions(Options.newBuilder().setApnsProduction(iosApnProduction == null ? false : iosApnProduction).build())
				.build();
		return pushPayload;
	}

}