package com.org.weixin.util;

/**
 * Title:微信常量类
 * <p>Description:这里面是所有关于微信的常量</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月10日
 */
public class Constant {
	
	/**
	 * Title:消息请求类型常量
	 * <p>Description:微信推送消息请求类型常量</p>
	 * Copyright (c) feinno 2013-2016
	 * @author fengshi on 2015年7月10日
	 */
	public class ReqMsgType{
		/**
		 * 文本
		 */
		public static final String TEXT = "text";
		/**
		 * 图片
		 */
		public static final String IMAGE = "image";
		/**
		 * 语音
		 */
		public static final String VOICE = "voice";
		/**
		 * 视频
		 */
		public static final String VIDEO = "video";
		/**
		 * 小视频
		 */
		public static final String SHORTVIDEO = "shortvideo";
		/**
		 * 地理位置
		 */
		public static final String LOCATION = "location";
		/**
		 *  连接消息
		 */
		public static final String LINK = "link";
		/**
		 *  事件消息
		 */
		public static final String EVENT = "event";
	}
	
	/**
	 * Title:消息请求事件常量
	 * <p>Description:微信推送消息请求事件常量</p>
	 * Copyright (c) feinno 2013-2016
	 * @author fengshi on 2015年7月10日
	 */
	public class ReqMsgEvent{
		/**
		 *  订阅
		 */
		public static final String SUBSCRIBE = "subscribe";
		/**
		 *  取消订阅
		 */
		public static final String UNSUBSCRIBE = "unsubscribe";
		/**
		 *  带参数二维码
		 */
		public static final String SCAN = "SCAN";
		/**
		 *  上报地理位置
		 */
		public static final String LOCATION = "LOCATION";
		/**
		 *  自定义菜单
		 */
		public static final String CLICK = "CLICK";
		/**
		 *   菜单跳转链接
		 */
		public static final String VIEW = "VIEW";
	}

}