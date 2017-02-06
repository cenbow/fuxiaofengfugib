/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common.constant;

import java.util.Map;

import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.file.FileUtils;
import com.google.common.collect.Maps;

/**
 * Title: 业务类型常量类
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月18日
 */
public class BusinessType {
	
	/** 新闻 */
	public static final Byte SOURCE_TYPE_1 = 1;
	/** 问政 */
	public static final Byte SOURCE_TYPE_2 = 2;
	/** 商情 */
	public static final Byte SOURCE_TYPE_3 = 3;
	/** 随手拍 */
	public static final Byte SOURCE_TYPE_4 = 4;
	/** 段子 */
	public static final Byte SOURCE_TYPE_5 = 5;
	/** 活动 */
	public static final Byte SOURCE_TYPE_6 = 6;
	/** 话题 */
	public static final Byte SOURCE_TYPE_7 = 7;
	/** 便民 */
	public static final Byte SOURCE_TYPE_8 = 8;
	/** 热线 */
	public static final Byte SOURCE_TYPE_9 = 9;
	/** 旅游 */
	public static final Byte SOURCE_TYPE_10 = 10;
	/** 手机置业 */
	public static final Byte SOURCE_TYPE_11 = 11;
	/** 招聘 */
	public static final Byte SOURCE_TYPE_12 = 12;
	/** 商城 */
	public static final Byte SOURCE_TYPE_13 = 13;
	/** 商情-推荐到首页 */
	public static final Byte SOURCE_TYPE_14 = 14;
	/** 商城-推荐到首页 */
	public static final Byte SOURCE_TYPE_15 = 15;
	/** 商城-推荐到轮播 */
	public static final Byte SOURCE_TYPE_16 = 16;
	/** 商城-商品图片 */
	public static final Byte SOURCE_TYPE_17 = 17;
	
	public static final Map<Byte, String> allSourceTypes = Maps.newTreeMap();
	//图片压缩尺寸规格
	public static final Map<Long, String> IMG_THUMB_SIZE = Maps.newTreeMap();
	static {
		allSourceTypes.put(SOURCE_TYPE_1, "新闻");
		allSourceTypes.put(SOURCE_TYPE_2, "问政");
		allSourceTypes.put(SOURCE_TYPE_3, "商情");
		allSourceTypes.put(SOURCE_TYPE_4, "随手拍");
		allSourceTypes.put(SOURCE_TYPE_5, "段子");
		allSourceTypes.put(SOURCE_TYPE_6, "活动");
		allSourceTypes.put(SOURCE_TYPE_7, "话题");
		allSourceTypes.put(SOURCE_TYPE_8, "便民");
		allSourceTypes.put(SOURCE_TYPE_9, "热线");
		allSourceTypes.put(SOURCE_TYPE_10, "旅游");
		allSourceTypes.put(SOURCE_TYPE_11, "置业");
		allSourceTypes.put(SOURCE_TYPE_12, "招聘");
		allSourceTypes.put(SOURCE_TYPE_13, "商城");
		allSourceTypes.put(SOURCE_TYPE_14, "商情-推荐到首页");
		allSourceTypes.put(SOURCE_TYPE_15, "商城-推荐到首页");
		allSourceTypes.put(SOURCE_TYPE_16, "商城-推荐到轮播");
		
		
		/**
		 * 等比压缩，传递thumb：图片规格 格式：12x23,11x11
		 * 宽高，多组用逗号分隔,每组宽高用英文小写字母x链接(图片强制修改为指定宽高) 
		 * 如果只穿一个数字，则为宽，按照宽为维度等比压缩
		 * 如果要按照高为维度等比压缩，则传h_300
		 * 参数实例：?thumb=150x100,200,h_300,200x150
		 * 
		 * imgsize(kb):限制图片最大大小，如果大于配置的尺寸，则会进行缩小分辨率，规格不变
		 * 
		 * */
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_1), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_2), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_3), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_4), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_5), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_6), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_7), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_8), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_9), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_10), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_11), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_12), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_13), "imgsize=30&thumb=300x300,200");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_14), "imgsize=30&thumb=355x229");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_15), "imgsize=30&thumb=324x324");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_16), "imgsize=30&thumb=750x254");
		IMG_THUMB_SIZE.put(Long.valueOf(SOURCE_TYPE_17), "imgsize=50&thumb=342x342,190x190");
	}

	/**
	 * Title:
	 * <p>Description:获取图片规格尺寸: {"thumb":["300x300","200"],"imgsize":["30"]}</p>
	 * @author fuxiaofeng on 2016年11月30日
	 * @param imgConfig
	 * @return
	 */
	public static Map<String,String[]> getImgConfig(String imgConfig){
		if(StringUtil.isEmpty(imgConfig))return null;
		Map<String,String[]> config = Maps.newHashMap();
		String[] arrConfig = imgConfig.split("&");
		for(String str : arrConfig){
			String[] arr = str.split("=");
			String key = arr[0];
			String value = arr[1];
			String[] arrValue = value.split(",");
			config.put(key,arrValue);
		}
		return config;
	}
	
	//获取图片规格数组
	public static String[] getImageConfig(Byte businessType,String key){
		if(null == businessType)return null;
		Map<String,String[]> config = BusinessType.getImgConfig(BusinessType.IMG_THUMB_SIZE.get(Long.valueOf(businessType)));
		return config.get(key);
	}
	
	/**
	 * Title:
	 * <p>Description:为el表达式准备的方法</p>
	 * {"1":{"thumb":["300x300","200"],"imgsize":["30"]},"2":{"thumb":["300x300","200"],"imgsize":["30"]}}}
	 * @author fuxiaofeng on 2016年11月30日
	 * @return
	 */
	public static Map<Long,Map<String,String[]>> getImgConfig(){
		Map<Long, String> map = BusinessType.IMG_THUMB_SIZE;
		Map<Long,Map<String,String[]>> imgConfig = Maps.newHashMap(); 
		for(Long key : map.keySet()){
			String imgSize = map.get(key);
			imgConfig.put(key, getImgConfig(imgSize));
		}
		return imgConfig;
	}
	
	public static String replaceSize(String filePath,String sourceSize,String targetSize){
		if(StringUtil.isEmpty(filePath))return null;
		if(StringUtil.isEmpty(targetSize) || StringUtil.isEmpty(sourceSize))return filePath;
        		
	    String ext = "."+FileUtils.getExtensions(filePath);
		String subPath = filePath.substring(0,filePath.lastIndexOf(sourceSize));
		subPath += targetSize;
		return subPath += ext;
	}
	
	public static void main(String[] args) {
		//System.out.println(JsonMapper.nonEmptyMapper().toJson(getImgConfig()));
		System.out.println(replaceSize("/asdfasfa/567_234_567_234.jpg","567_234","234_23"));
	}
}