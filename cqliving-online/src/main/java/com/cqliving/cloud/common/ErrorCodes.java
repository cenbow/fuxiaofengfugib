/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年4月15日
 */
public class ErrorCodes {

	public final static int FAILURE = -1;
	
	public final static int SUCCESS = 0;
	
	/**
	 * Title: 公共错误信息
	 * <p>Description:</p>
	 * Copyright (c) CQLIVING 2016
	 * @author Tangtao on 2016年4月27日
	 */
	public static enum CommonErrorEnum {
		REQUEST_NO_PERMISSION(-10100098, "没有请求权限"),
		TOKEN_IS_BLANK(-101000099, "Token为空"),
		SESSION_ID_IS_BLANK(-101000100, "SessionId为空"),
		
		APP_NOT_EXIST(-101000101, "App不存在"),
		INVALID_PARAM(-101000102, "无效参数"),
		CAPTCHA_EXPIRIED(-101000103, "验证码已失效"),
		CAPTCHA_ERROR(-101000104, "验证码错误"),
		USER_NOT_EXIST(-101000105, "用户不存在"),
		PWD_WRONG(-101000106, "密码错误"),
		JPUSH_NO_CONFIG(-101000107, "推送配置信息不存在"),
		JPUSH_AUDIENCE_CONFIG_ERROR(-101000108, "推送接收配置错误"),
		USER_WAS_FORBIDDEN(-101000109, "用户已被禁用"),
		NICKNAME_EXIST(-101000110, "昵称已被使用"),
		NOT_AUTHENTICATION(-200000000, "没有实名认证"),
		NOT_LOGIN(999992, "未登录");
		
		private int code;
		private String desc;
		CommonErrorEnum(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		public int getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}
	}
	
	/**
	 * Title: 注册错误信息
	 * <p>Description:</p>
	 * Copyright (c) CQLIVING 2016
	 * @author Tangtao on 2016年4月27日
	 */
	public static enum RegisterErrorEnum {
		USERNAME_IS_REGISTED(-102000101, "用户名已注册"),
		TELEPHONE_IS_REGISTED(-102000102, "手机号已注册"),
		TELEPHONE_IS_REGISTED_WITH_APP(-102000103, "手机号已在【#】APP注册，可直接登录");
		
		private int code;
		private String desc;
		RegisterErrorEnum(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		public int getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}
	}
	
	/**
	 * Title: 修改手机号错误信息
	 * <p>Description:</p>
	 * Copyright (c) CQLIVING 2016
	 * @author Tangtao on 2016年5月3日
	 */
	public static enum ChangePhoneErrorEnum {
		ORIGINAL_PHONE_WRONG(-103000101, "原手机号错误"),
		TIME_OUT(-103000102, "操作超时");
		
		private int code;
		private String desc;
		ChangePhoneErrorEnum(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		public int getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}
	}
	
	/**
	 * Title: 登录错误信息
	 * <p>Description:</p>
	 * Copyright (c) CQLIVING 2016
	 * @author Tangtao on 2016年5月4日
	 */
	public static enum LoginErrorEnum {
		USERNAME_OR_PWD_WRONG(-104000101, "用户名或密码错误");
		
		private int code;
		private String desc;
		LoginErrorEnum(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		public int getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}
	}
	
	/**
	 * Title: 短信错误信息
	 * <p>Description:</p>
	 * Copyright (c) CQLIVING 2016
	 * @author Tangtao on 2016年6月4日
	 */
	public static enum SmsErrorEnum {
		TYPE_NOT_EXIST(-105000101, "短信类型不存在"),
		SMS_URL_NOT_EXIST(-105000102, "短信地址未配置"),
		TELEPHONE_ERROR(-105000103, "不是接收短信的手机号");
		
		private int code;
		private String desc;
		SmsErrorEnum(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		public int getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}
	}
	
	/**
	 * Title: 新闻相关错误信息
	 * <p>Description:</p>
	 * Copyright (c) CQLIVING 2016
	 * @author Tangtao on 2016年6月4日
	 */
	public static enum InfomationErrorEnum {
		ALREADY_PUSHED(-106000101, "资讯已推送"),
		COMMENT_UNALLOWED(-106000102, "新闻不允许评论"),
		INFO_CLASSIFY_NOT_EXIST(-106000103, "资讯不存在"),
		NOT_SPECIAL(-106000104, "不是专题资讯");
		
		private int code;
		private String desc;
		InfomationErrorEnum(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		public int getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}
	}
	
	/**
	 * Title:
	 * <p>Description:二维码扫码活动错误信息</p>
	 * Copyright (c) CQLIVING 2016
	 * @author fuxiaofeng on 2016年12月16日
	 */
	public static enum QrcodeActErrorEnum {
		ACCOUNT_NOT_EXISTS(-1060101, "用户不存在"),
		USER_NOT_LOGIN(-1060102, "未登陆,请登录后参加活动"),
		ACT_NOT_EXISTS(-1060103, "活动不存在"),
		ACT_NOT_START(-1060104, "活动未开始"),
		ACT_EXPIRED(-1060105, "活动已过期"),
		HAD_OBTAINED(-1060106, "已领取,不可重复领取二维码"),
		HAD_VERIFIED(-1060107, "优惠券已核销"),
		ACT_ERR_PARM(-1060108, "活动请求参数不能为空"),
		USER_NOT_COUPON(-1060109, "用户未取得优惠券"),
		USER_NOT_RIGHT(-1060110, "不是此用户的优惠券,不能核销"),
		NOT_PHONE_USER(-1060111, "只能是手机号注册用户才能获取优惠券"),
		USER_HAD_JOINED(-1060112, "用户已经领取过优惠券,不能再领取");
		
		private int code;
		private String desc;
		QrcodeActErrorEnum(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		public int getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}
	}
}