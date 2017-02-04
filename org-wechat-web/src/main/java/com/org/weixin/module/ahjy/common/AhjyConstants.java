/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.ahjy.common;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年3月26日
 */
public interface AhjyConstants {
	
	/** 模块 id（sys_modules 表的 id） */
	Long MODULE_ID = 22L;
	/** 每次活动最大用户数 */
	int ACTIVITY_USER_LIMIT = 4;
	
	//sys_config 配置表中的 key
	//二维码扫描跳转地址
	public final static String QRCODE_SCAN_REDIRECT_URL = "ahjy.qrcode.scan.redirect.url";
    //图片本地地址
	public final static String FILE_LOCAL_URL = "ahjy.file.local.url";
	//图片访问地址
	public final static String FILE_VISIT_URL = "ahjy.file.visit.url";
    /** 抽奖URL */
    public static final String LUCKY_DRAW_URL = "http://127.0.0.1:8080/getLuckyDraw.html";
	/** 排行榜展示多少条记录 */
    String GAME_TIME_SECOND = "ahjy_game_time";
    //积分CODE
    public final static String AWARD_INTEGRAL_CODE = "ahjy.award.integral.code";
    
}