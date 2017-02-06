//package com.cqliving.jpush.test;
//
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.cqliving.jpush.service.PushService;
//import com.cqliving.jpush.test.support.BaseTest;
//import com.cqliving.tool.common.Response;
//import com.cqliving.tool.common.util.encrypt.Base64Util;
//import com.google.common.collect.Maps;
//
//import cn.jpush.api.push.PushResult;
//import cn.jpush.api.push.model.Message;
//import cn.jpush.api.push.model.Options;
//import cn.jpush.api.push.model.Platform;
//import cn.jpush.api.push.model.PushPayload;
//import cn.jpush.api.push.model.audience.Audience;
//import cn.jpush.api.push.model.notification.AndroidNotification;
//import cn.jpush.api.push.model.notification.IosNotification;
//import cn.jpush.api.push.model.notification.Notification;
//
//public class PushTest extends BaseTest {
//	
//	@Autowired
//	private PushService pushService;
//	
//	@Test
//	public void sendPush1() {
//		String message = "泥嚎，我是一条推送消息";
//		Response<PushResult> response = pushService.sendPush(masterSecret, appKey, message);
//		System.out.println(response);
//	}
//	
//	@Test
//	public void sendPush2() {
//		PushPayload pushPayload = PushPayload.alertAll("Hi, JPush!");
//		Response<PushResult> response = pushService.sendPush(masterSecret, appKey, pushPayload);
//		System.out.println(response);
//	}
//	
//	@Test
//	public void sendPushValidate2() {
//		PushPayload pushPayload = PushPayload.alertAll("Hi, JPush!");
//		Response<PushResult> response = pushService.sendPush(masterSecret, appKey, pushPayload);
//		System.out.println(response);
//	}
//	
//	@Test
//	public void sendPushAndroid() {
//		Map<String, String> extras = Maps.newHashMap();
//		extras.put("id", "-1");	
//		extras.put("sourceId", "-1");	
//		extras.put("sourceType", "-1");	
//		extras.put("detailViewType", "-1");	
//		extras.put("commentType", "0");	
//		extras.put("url", StringUtils.defaultString("测试url"));	
//		extras.put("shareUrl", StringUtils.defaultString("测试shareUrl"));	
//		extras.put("title", Base64Util.encodeBase64("测试title"));	
//		extras.put("synopsis", Base64Util.encodeBase64("测试synopsis"));	
//		extras.put("shareImgUrl", StringUtils.defaultString("测试shareImgUrl"));	
//		AndroidNotification androidNotification = AndroidNotification.newBuilder().setAlert("测试title").setTitle("测试title").addExtras(extras).build();
//		PushPayload pushPayload = PushPayload.newBuilder()
//				.setNotification(Notification.newBuilder().addPlatformNotification(androidNotification).build())
//				.setMessage(Message.newBuilder().setTitle("测试title").setMsgContent("测试synopsis").addExtras(extras).build())
////				.setAudience(Audience.registrationId("190e35f7e044f990e07"))	//控制接收对象（收不到）
////				.setAudience(Audience.registrationId("190e35f7e044f990e06"))	//控制接收对象（能收到【长寿】）
//				.setAudience(Audience.registrationId("170976fa8a88032f568"))	//控制接收对象（能收到【九龙坡】）
//				.setPlatform(Platform.all())
//				.build();
//		Response<PushResult> response = pushService.sendPush(masterSecret, appKey, pushPayload);
//		System.out.println(response);
//	}
//	
//	@Test
//	public void sendPushIos() {
//		Map<String, String> extras = Maps.newHashMap();
//		extras.put("id", "-1");	
//		extras.put("sourceId", "-1");	
//		extras.put("sourceType", "-1");	
//		extras.put("detailViewType", "-1");	
//		extras.put("commentType", "0");	
//		extras.put("url", StringUtils.defaultString("测试url"));	
//		extras.put("shareUrl", StringUtils.defaultString("测试shareUrl"));	
//		extras.put("title", Base64Util.encodeBase64("测试title"));	
//		extras.put("synopsis", Base64Util.encodeBase64("测试synopsis"));	
//		extras.put("shareImgUrl", StringUtils.defaultString("测试shareImgUrl"));	
////		IosNotification iosNotification = IosNotification.newBuilder().setAlert("测试title_ApnsProduction_false").addExtras(extras).build();
//		IosNotification iosNotification = IosNotification.newBuilder().setAlert("测试title_ApnsProduction_true").addExtras(extras).build();
//		PushPayload pushPayload = PushPayload.newBuilder()
//				.setNotification(Notification.newBuilder().addPlatformNotification(iosNotification).build())
//				.setMessage(Message.newBuilder().setTitle("测试title").setMsgContent("测试synopsis").addExtras(extras).build())
////				.setAudience(Audience.registrationId("141fe1da9ea38ad0eb6"))	//控制接收对象（能收到【九龙坡】）
//				.setAudience(Audience.all())	
//				.setPlatform(Platform.all())
//				.setOptions(Options.newBuilder().setApnsProduction(true).build())	//推送ios生产环境
//				.build();
//		Response<PushResult> response = pushService.sendPush(masterSecret, appKey, pushPayload);
//		System.out.println(response);
//	}
//	
//}