/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.baidumap.domain.ip.IPResult;
import com.cqliving.baidumap.service.IPService;
import com.cqliving.baidumap.test.support.BaseTest;
import com.cqliving.tool.common.Response;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月19日
 */
public class IPTest extends BaseTest {
	
	@Autowired
	private IPService iPService;
	
	@Test
	public void locate() throws UnsupportedEncodingException {
		Response<IPResult> response = iPService.locate(ak, "");
		System.out.println(response);
	}
	
}