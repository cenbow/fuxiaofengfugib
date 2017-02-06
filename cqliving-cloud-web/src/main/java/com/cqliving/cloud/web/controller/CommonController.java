/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.domain.UserViewRecode;
import com.cqliving.cloud.online.account.service.UserSessionService;
import com.cqliving.cloud.online.account.service.UserViewRecodeService;
import com.cqliving.cloud.online.info.service.InformationService;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.utils.RequestUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月14日
 */
public class CommonController {

	@Autowired
	UserViewRecodeService userViewRecodeService;
	@Autowired
	UserSessionService userSessionService;
	@Autowired
	InformationService informationService;
	
	protected final static String DELETE_JSP = "/error/detelte_status";
	
    protected void saveUserViewRecode(HttpServletRequest request,Long appId,Long infoClassifyId,Long informationId){
		
		UserViewRecode userViewRecode = new UserViewRecode();
		String sessionId = request.getParameter("sessionId");
		String token = request.getParameter("token");
		
		userViewRecode.setAppId(appId);
		userViewRecode.setCreateTime(Dates.now());
		userViewRecode.setIpAddr(RequestUtil.getRequestIpAddr(request));
		userViewRecode.setSessionCode(sessionId);
		userViewRecode.setSourceId(infoClassifyId);
		userViewRecode.setInformationId(informationId);
		userViewRecode.setSourceType(UserViewRecode.SOURCETYPE0);
		
		UserSession userSession = userSessionService.get(sessionId, token).getData();
		userViewRecode.setUserId(null != userSession ? userSession.getUserId() : null);
		
		userViewRecodeService.save(userViewRecode);
		
	}
    
}
