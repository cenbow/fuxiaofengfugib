/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.test;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.baidumap.domain.geocoding.AddressResult;
import com.cqliving.baidumap.domain.geocoding.LocationResult;
import com.cqliving.baidumap.service.GeocodingService;
import com.cqliving.baidumap.test.support.BaseTest;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月13日
 */
public class GeocodingTest extends BaseTest {
	
	@Autowired
	private GeocodingService geocodingService;
	
	@Test
	public void addressToLocation() throws UnsupportedEncodingException {
		Map<String, String> map = Maps.newHashMap();
		map.put("city", "重庆");
		Response<LocationResult> response = geocodingService.addressToLocation(ak, "朝天门", map);
		System.out.println(response);
	}
	
	@Test
	public void locationToAddress() throws UnsupportedEncodingException {
		Map<String, String> map = Maps.newHashMap();
		map.put("pois", "1");
		Response<AddressResult> response = geocodingService.locationToAddress(ak, 29.568783575049, 106.5921462137, map);
		System.out.println(response);
	}

}