///**
// * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
// * This software is the confidential and proprietary information of 
// * CQLIVING, Inc. You shall not disclose such Confidential
// * Information and shall use it only in accordance with the terms of the 
// * license agreement you entered into with CQLIVING.
// */
//package com.cqliving.jpush.test;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.cqliving.jpush.service.ReportService;
//import com.cqliving.jpush.test.support.BaseTest;
//import com.cqliving.tool.common.Response;
//
//import cn.jpush.api.report.ReceivedsResult;
//
///**
// * Title:
// * <p>Description:</p>
// * Copyright (c) CQLIVING 2016
// * @author Tangtao on 2016年4月13日
// */
//public class ReportTest extends BaseTest {
//	
//	@Autowired
//	private ReportService reportService;
//	
//	@Test
//	public void getReceiveds() {
//		Response<ReceivedsResult> response = reportService.getReceiveds(masterSecret, appKey, "3759395134");
//		System.out.println(response);
//	}
//
//}