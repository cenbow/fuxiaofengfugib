/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common.constant;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Title: 客户端展现形式常量类
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月19日
 */
public class ClientControlType {
	
	/** 图文新闻 */
	public static final Byte DETAIL_VIEW_TYPE_1 = 1;
	/** 普通新闻 */
	public static final Byte DETAIL_VIEW_TYPE_2 = 2;
	/** 专题新闻 */
	public static final Byte DETAIL_VIEW_TYPE_3 = 3;
	/** 随手拍 */
	public static final Byte DETAIL_VIEW_TYPE_4 = 4;
	/** 段子 */
	public static final Byte DETAIL_VIEW_TYPE_5 = 5;
	/** 预留，暂不使用 */
//	public static final Byte DETAIL_VIEW_TYPE_6 = 6;
	/** 话题 */
	public static final Byte DETAIL_VIEW_TYPE_7 = 7;
	/** 纯webView页面 */
	public static final Byte DETAIL_VIEW_TYPE_8 = 8;
	/** 有导航栏的Webview */
	public static final Byte DETAIL_VIEW_TYPE_9 = 9;
	/** 旅游详情 */
	public static final Byte DETAIL_VIEW_TYPE_10 = 10;
	/** 旅游专题详情 */
	public static final Byte DETAIL_VIEW_TYPE_11 = 11;
	
	public static final Map<Byte, String> allDetailViewTypes = Maps.newTreeMap();
	static {
		allDetailViewTypes.put(DETAIL_VIEW_TYPE_1, "图文新闻");
		allDetailViewTypes.put(DETAIL_VIEW_TYPE_2, "普通新闻");
		allDetailViewTypes.put(DETAIL_VIEW_TYPE_3, "专题新闻");
		allDetailViewTypes.put(DETAIL_VIEW_TYPE_4, "随手拍");
		allDetailViewTypes.put(DETAIL_VIEW_TYPE_5, "段子");
//		allDetailViewTypes.put(DETAIL_VIEW_TYPE_6, "");
		allDetailViewTypes.put(DETAIL_VIEW_TYPE_7, "话题");
		allDetailViewTypes.put(DETAIL_VIEW_TYPE_8, "纯WebView");
		allDetailViewTypes.put(DETAIL_VIEW_TYPE_9, "有导航栏的Webview");
		allDetailViewTypes.put(DETAIL_VIEW_TYPE_10, "旅游详情");
		allDetailViewTypes.put(DETAIL_VIEW_TYPE_11, "旅游专题详情");
	}

}