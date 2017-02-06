package com.cqliving.getui.push.test;

import java.util.List;

import org.junit.Test;

import com.cqliving.getui.push.test.support.BaseTest;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.google.common.collect.Lists;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月14日
 */
public class PushTest extends BaseTest {
	
	@Test
	public void demoPush() {
		IGtPush push = new IGtPush(appKey, masterSecret, true);
		LinkTemplate template = new LinkTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setText("这是测试推送消息");
		template.setTitle("这是个推推送标题");
		template.setUrl("http://www.baidu.com");
		
		List<String> appIdList = Lists.newArrayList();
		appIdList.add(appId);
		AppMessage message = new AppMessage();
		message.setAppIdList(appIdList);
		message.setData(template);
		message.setOffline(true);
		message.setOfflineExpireTime(10000 * 60);
		
		IPushResult result = push.pushMessageToApp(message);
		System.out.println(result.getResponse());
	}
	
}