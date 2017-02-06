/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common.constant;

/**
 * Title:存放配置文件KEY值的常量类
 * <p>Description:</p>
 * Copyright (c) xhl
 * @author huxiaoping
 */
public class PropertyKey {
	//文件上传地址
	public static final String FILE_LOCAL_PATH = "file.local.path";
	//文件访问地址
	public static final String FILE_URL_PATH = "file.url.path";
	/** 短信余额地址 */
	public static final String SMS_INTERFACE_QUERY_URL = "sms.interface.query.url"; 
	/** 短信发送地址 */
	public static final String SMS_INTERFACE_URL = "sms.interface.url";
	/** 短信接口企业 id */
	public static final String SMS_USERID = "sms.huatang.userid";
	/** 短信接口密码 */
	public static final String SMS_PWD = "sms.huatang.pwd";
	/** 短信接口账户 */
	public static final String SMS_ACCOUNT= "sms.huatang.account";
	/** 问政H5列表每页记录数 **/
	public static final String WZ_LIST_PAGE_SIZE = "wz.list.page.size";

	/** 调用天气预报接口所需要的KEY，http://apistore.baidu.com/apiworks/servicedetail/478.html **/
	public static final String WEATHER_KEY = "weather_key";
	
	/** 从七牛下载音、视频到本地文件的地址 */
	public static final String DOWNLOAD_QINIU_FILE_PATH = "download.qiniu.file.path";
	//前台网站地址
	public static final String WEB_URL_PATH="web.url.path";
	/** 接口地址 */
	public static final String SERVER_URL_PATH="server.url.path";
	
	/** 垫江微发布地址 **/
	public static final String DJ_WEIFABU = "dj_weifabu";
	
	/** 极光推送接收限制类型{all:全部,registrationid:指定设备} */
	public static final String JPUSH_AUDIENCE_TYPE = "jpush.audience.type";
	/** 极光推送接收设备注册 id */
	public static final String JPUSH_AUDIENCE_REGISTRATIONID = "jpush.audience.registrationid";
	/** 极光推送IOS平台推送环境指定{true: 生成环境, false: 开发环境, 为<tt>null</tt>时为开发环境} */
	public static final String JPUSH_IOS_APN_PRODUCTION = "jpush.ios.apn.production";
	
	/** 华龙网抓稿入库状态{-1:审核不通过,0:草稿,1:保存,3:正常上线,88:下线,99:删除} */
	public static final String MANUSCRIPT_STATUS = "manuscript.status";
	
	/** 华龙网抓稿创建用户ID */
	public static final String MANUSCRIPT_CREATE_ID = "manuscript.create.id";
	/** 华龙网抓稿创建用户名称 */
	public static final String MANUSCRIPT_CREATE_NAME = "manuscript.create.name";
	/** 城口抓稿创建用户ID */
	public static final String MANUSCRIPT_CK_CREATE_ID = "manuscript.ck.create.id";
	/** 城口抓稿创建用户名称 */
	public static final String MANUSCRIPT_CK_CREATE_NAME = "manuscript.ck.create.name";
	/** 秀山抓稿创建用户ID */
	public static final String MANUSCRIPT_XS_CREATE_ID = "manuscript.xs.create.id";
	/** 秀山抓稿创建用户名称 */
	public static final String MANUSCRIPT_XS_CREATE_NAME = "manuscript.xs.create.name";
	
	/** 华龙网抓稿默认阅读量范围 */
	public static final String MANUSCRIPT_VIEW_COUNT_RANGE = "manuscript_view_count_range";
	/** 手机置业h5跳转地址 */
	public static final String BUILDING_H5URL = "building_h5url";
	/** amqp主机地址（主机名或ip地址） */
	public static final String AMQP_HOST = "amqp.service.host";
	/** amqp交换机名称 */
	public static final String AMQP_EXCHANGENAME = "amqp.service.exchangename";
	/** amqp路由键 */
	public static final String AMQP_ROUTINGKEY = "amqp.service.routingkey";
	/** amqp用户名 */
	public static final String AMQP_USERNAME = "amqp.service.username";
	/** amqp密码 */
	public static final String AMQP_PASSWORD = "amqp.service.password";
	/** amqp端口 */
	public static final String AMQP_PORT = "amqp.service.port";
	/** 来 源 */
	public static final String AMQP_SOURCE = "amqp.service.source";
	
	/** 行政审批认证 */
    public static final String GOV_AUTH_REGISTER = "user.gov.authentication.register";
    /** 行政审批登录 */
    public static final String GOV_AUTH_LOGIN = "user.gov.authentication.login";
    /** 行政审批加密秘钥 */
    public static final String GOV_AUTH_KEY = "user.gov.authentication.key";
    
    /** 获取区县接口 */
    public static final String COUNTY_REGISTER = "county.register";
    /** 获取区县楼盘接口 */
    public static final String COUNTY_HOUSES_REGISTER = "county.houses.register";
}
