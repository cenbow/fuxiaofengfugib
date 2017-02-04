/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.tool.common.util.StringUtil;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.common.Constants;
import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.system.dto.AccInfoDto;
import com.org.weixin.util.JsUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月6日
 */
public abstract class CommonController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public SpyMemcachedClient memcachedClient;
	/**
	 * <p>Description: 获取微信分享的配置</p>
	 * @param request
	 * @return
	 * @author Tangtao on 2015年7月14日
	 */
	@SuppressWarnings("unchecked")
	public String getShareConfig(HttpServletRequest request, Long accId) {
		Map<Long,AccInfoDto> accInfoMap = (Map<Long, AccInfoDto>) this.memcachedClient.get(Constants.Weixin.WEIXIN_ACC_MAP);
		String appId = accInfoMap.get(accId).getAppid();
		String ticket = accInfoMap.get(accId).getTicket();
		String s = request.getRequestURL().toString();
		if (!StringUtil.isEmpty(request.getQueryString())) {
			s += "?" + request.getQueryString();
		}
		int index = s.indexOf("#");
		if (index != -1) {
			s = s.substring(0, index);
		}
		logger.debug("--------------------url = " + s);
		String config = JsUtil.generateConfigJson(ticket, false, appId, s,"onMenuShareTimeline", "onMenuShareAppMessage","onMenuShareQQ");
		return config;
	}
	
	/**
	 * <p>Description: 设置用户和分享信息</p>
	 * @param request
	 * @param model
	 * @param accId
	 * @author Tangtao on 2015年7月25日
	 */
	public void setUserAndShare(HttpServletRequest request, Long accId) {
		
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		Long userId = sessionUser.getId();
		request.setAttribute("userId", userId);
		//分享
		String config = getShareConfig(request, accId);
		
		logger.info("-----------------分享config配置：{}",config);
		request.setAttribute("config", config);
	}
}
