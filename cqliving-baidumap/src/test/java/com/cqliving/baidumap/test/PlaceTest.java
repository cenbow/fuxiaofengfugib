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

import com.cqliving.baidumap.domain.place.PlaceResult;
import com.cqliving.baidumap.service.PlaceService;
import com.cqliving.baidumap.service.PlaceSuggestionService;
import com.cqliving.baidumap.test.support.BaseTest;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月13日
 */
public class PlaceTest extends BaseTest {
	
	@Autowired
	private PlaceService placeService;
	@Autowired
	private PlaceSuggestionService placeSuggestionService;
	
	@Test
	public void detail1() {
		Response<PlaceResult> response = placeService.getDetail(ak, null, "bba87388f67d56b9bca82278");
		System.out.println(response);
	}
	
	@Test
	public void detail2() {
		Response<PlaceResult> response = placeService.getDetail(ak, 2, null, "bba87388f67d56b9bca82278", "5ffb1816b849ab466f47607e");
		System.out.println(response);
	}
	
	@Test
	public void searchByBounds() throws UnsupportedEncodingException {
		Map<String, String> map = Maps.newHashMap();
		map.put("scope", "2");	//检索结果详细程度。取值为1 或空，则返回基本信息；取值为2，返回检索POI详细信息
		Response<PlaceResult> response = placeService.searchByBounds(ak, "学校", 38.76623, 116.43213, 39.54321, 116.46773 , map);
		System.out.println(response);
	}
	
	@Test
	public void searchByLocation1() throws UnsupportedEncodingException {
		Map<String, String> map = Maps.newHashMap();
		map.put("tag", "文化传媒");	//标签
		map.put("scope", "2");	//检索结果详细程度。取值为1 或空，则返回基本信息；取值为2，返回检索POI详细信息
		Response<PlaceResult> response = placeService.searchByLocation(ak, "新闻", 29.594034, 106.527612, map);
		System.out.println(response);
	}
	
	@Test
	public void searchByLocation2() throws UnsupportedEncodingException {
		Map<String, String> map = Maps.newHashMap();
		map.put("tag", "文化传媒");	//标签
		map.put("scope", "2");	//检索结果详细程度。取值为1 或空，则返回基本信息；取值为2，返回检索POI详细信息
		Response<PlaceResult> response = placeService.searchByLocation(ak, "新", 29.594034, 106.527612, 10000, map);
		System.out.println(response);
	}
	
	@Test
	public void searchByRegion1() throws UnsupportedEncodingException {
		Map<String, String> map = Maps.newHashMap();
		map.put("tag", "文化传媒");	//标签
		map.put("scope", "2");	//检索结果详细程度。取值为1 或空，则返回基本信息；取值为2，返回检索POI详细信息
		Response<PlaceResult> response = placeService.searchByRegion(ak, "新", "重庆", map);
		System.out.println(response);
	}
	
	@Test
	public void searchByRegion2() throws UnsupportedEncodingException {
		Map<String, String> map = Maps.newHashMap();
		map.put("tag", "文化传媒");	//标签
		map.put("scope", "2");	//检索结果详细程度。取值为1 或空，则返回基本信息；取值为2，返回检索POI详细信息
		Response<PlaceResult> response = placeService.searchByRegion(ak, "新", "重庆", true, map);
		System.out.println(response);
	}
	
	@Test
	public void suggestion() throws UnsupportedEncodingException {
		Map<String, String> map = Maps.newHashMap();
		Response<PlaceResult> response = placeSuggestionService.suggestion(ak, "jiefangbei", "重庆", map);
		System.out.println(response);
	}

}