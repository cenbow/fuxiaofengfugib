/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.sms.huatang.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.sms.huatang.server.service.QueryService;
import com.cqliving.sms.huatang.server.service.ReportService;
import com.cqliving.sms.huatang.server.service.SendService;
import com.cqliving.sms.huatang.server.util.EncryptUtil;
import com.cqliving.sms.huatang.test.support.BaseTest;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月26日
 */
public class SmsTest extends BaseTest {
	
	@Autowired
	private SendService sendService;
	@Autowired
	private QueryService queryService;
	@Autowired
	private ReportService reportService;
	
	@Test
	public void checkKeyWord() {
		Date timestamp = DateUtil.now();
		String sign = EncryptUtil.md5(account, pwd, timestamp);
		Response<String> response = sendService.checkKeyWord(userId, sign, timestamp, "你好，这是一条正常的测试短信");
		System.out.println(response);
	}
	
	@Test
	public void send() {
		Date timestamp = DateUtil.now();
		String sign = EncryptUtil.md5(account, pwd, timestamp);
		List<String> mobileList = Lists.newArrayList();
		mobileList.add("15826261984");
//		mobileList.add("15178857788");
//		mobileList.add("17782369541");
//		mobileList.add("13272579898");
		int extno = 12;
		sendService.send(userId, sign, timestamp, mobileList, "亲爱的用户，现在是北京时间：" + DateUtil.formatDateNowDefault() + "，扩展号段：" + extno, null, extno);
	}
	
	@Test
	public void overage() {
		Date timestamp = DateUtil.now();
		String sign = EncryptUtil.md5(account, pwd, timestamp);
		queryService.getOverage(userId, sign, timestamp);
	}
	
	@Test
	public void getSendReport() {
		Date timestamp = DateUtil.now();
		String sign = EncryptUtil.md5(account, pwd, timestamp);
		Response<String> response = reportService.getSendReport(userId, sign, timestamp, null);
		System.out.println(response);
	}
	
	@Test
	public void getCallReport() {
		Date timestamp = DateUtil.now();
		String sign = EncryptUtil.md5(account, pwd, timestamp);
		Response<String> response = reportService.getCallReport(userId, sign, timestamp);
		System.out.println(response);
	}

}