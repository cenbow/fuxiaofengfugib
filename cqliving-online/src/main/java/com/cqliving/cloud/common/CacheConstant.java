package com.cqliving.cloud.common;

import java.util.MissingFormatArgumentException;

public final class CacheConstant {
	//资源缓存,以Hash数据类型存储
	public static final String SYS_RESOURCE_URL = "sys_resource_url_hash";
	public static final String SYS_RESOURCE_ID = "sys_resource_id_hash";
	
	/***
	 * 轮播图配置,操作该缓存使用redis的Hash数据结构，请参考使用AbstractRedisClient的setHSet方法和getHSet方法操作该值
	 * key_name:info_slider_config_hash
	 * field:appId_columnsId(示列值为：6_12)
	 * value:即显示值
	 */
	public static final String INFO_SLIDER_CONFIG = "info_slider_config_hash";
	
	/***
     * 栏目缓存key前缀,操作栏目缓存使用redis的对象，请参考使用AbstractRedisClient的set(String key, Object value)方法和getList(String key, Class<T> clazz)方法操作该值
     * key_name:"columnsCache"+appId(示列值为：columnsCache25)
     * value:即该app下的所有已发布未删除的栏目数据
     */
	public static final String APPCACHE = "columnsCache";

	/** 
	 * 缓存app_config表的对象
	 * key_name: app_config_app_ + appId(如：app_config_app_1)
	 * value: 该 appId 对应的 AppConfig 对象
	 */
	public static final String APP_CONFIG_APPID_PREFIX = "app_config_app_";
	
	/***
	 * APP后台域名,操作该缓存使用redis的Hash数据结构，请参考使用AbstractRedisClient的setHSet方法和getHSet方法操作该值
	 * key_name:app_info_cms_domain
	 * field:cms_domain(示列值为：cq.cqliving.com)
	 * value:AppInfo对象的json字符串
	 */
	public static final String APP_INFO_CMS_DOMAIN = "app_info_cms_domain";
	/***
	 * APP自定义域名,操作该缓存使用redis的Hash数据结构，请参考使用AbstractRedisClient的setHSet方法和getHSet方法操作该值
	 * key_name:app_info_cms_domain
	 * field:cms_domain(示列值为：cq.cqliving.com)
	 * value:AppInfo对象的json字符串
	 */
	public static final String APP_INFO_APP_DOMAIN = "app_info_app_domain";
	
	/***
	 * APP表所有数据,操作该缓存使用redis的string数据结构，
	 * 请参考使用AbstractRedisClient的set(String key, Object value)方法和getList(String key, Class<T> clazz)方法操作该值
	 * key_name:app_info_list
	 * value:List<AppInfo>对象的json字符串
	 */
	public static final String APP_INFO_LIST = "app_info_list";
	
	//新闻的浏览量
	public static final String INFO_VIEW_INFOMATION_COUNT_HASH = "info_view_infomation_count_hash";
	public static final String INFO_VIEW_INFOCLASSIFY_COUNT_HASH = "info_view_infoclassify_count_hash";
	//新闻的评论量
	public static final String INFO_REPLY_INFOMATION_COUNT_HASH = "info_reply_infomation_count_hash";
	public static final String INFO_REPLY_INFOCLASSIFY_COUNT_HASH = "info_reply_infoclassify_count_hash";
	
	/**
	 * 基础表区域json格式字符串
	 */
	public static final String BASIC_REGION_STR = "basic_region_str";
	
	//请求操作限制，防止服务器被刷崩
	/** 找回密码 */
	public static final String REQUEST_LIMIT_FIND_PWD_PREFIX = "request_limit_find_pwd_prefix_";
	/** 登录 */
	public static final String REQUEST_LIMIT_LOGIN_PREFIX = "request_limit_login_prefix_";
	/** 注册 */
	public static final String REQUEST_LIMIT_REGISTER_PREFIX = "request_limit_register_prefix_";
	
	/** 权限过滤器缓存的 domain 值前缀 */
	public static final String REQUEST_PERMISSION_APPID_HASH_PREFIX = "request_permission_appid_";
	
	/** -----------------------------------下面是最新redis缓存可以的格式命名--------------------------------------------------------- */
	
	/** 抓稿 */
	public static final String B_PREFIX_1 = "1";
	
	/** 
	 * 抓稿ID，目前一个app只有一个抓稿任务，所以就不区分是哪个app了，就直接根据key中的第二个参数(appId)来区分就可以了。
	 * key：业务类型:appid:manuscript_columns表id(例如：1:35:23)
	 * value:
	 **/
	public static final String MANUSCRIPT_ID_HASH = B_PREFIX_1 + ":i:%s" + ":%s";
	
	/** 
     * 抓稿TITLE，目前一个app只有一个抓稿任务，所以就不区分是哪个app了，就直接根据key中的第二个参数(appId)来区分就可以了。
     * key：业务类型:appid:manuscript_columns表id(例如：1:35:23)
     * value:
     **/
	public static final String MANUSCRIPT_TITLE_HASH = B_PREFIX_1 + ":t:%s" + ":%s";
	
	/** 微信小程序 */
	public static final String B_PREFIX_2 = "2";
    /** 
     * 微信小程序用户登录信息缓存
     * domain: 
     * key：业务类型：appId：生成的3rd_session
     * value: 根据接口获取的数据，格式为：“{"session_key":"Au6DMoGwI1KxIgLCVscXRg==","expires_in":2592000,"openid":"oluoJ0clRHCr-lpkh0NzYf914aQM"}”
     **/
    public static final String WECHATLITE_USER_SESSION_HASH = B_PREFIX_2 + ":%s";
	
	
	
	/**
	 * Title:根据业务类型key的格式和值获取完整的key字符串
	 * <p>Description:</p>
	 * @author DeweiLi on 2017年1月9日
	 * @param name
	 * @param keys
	 * @return
	 */
	public static String getKey(String name, Object...keys){
	    String key = null;
	    if(name != null && keys != null && keys.length > 0){
	        try {
	            key = String.format(name, keys);
            } catch (MissingFormatArgumentException e) {
                e.printStackTrace();
            }
	    }
	    return key;
	}
}