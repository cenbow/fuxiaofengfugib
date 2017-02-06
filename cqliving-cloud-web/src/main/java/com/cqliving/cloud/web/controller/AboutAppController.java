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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqliving.cloud.online.config.domain.ConfigText;
import com.cqliving.cloud.online.config.service.ConfigTextService;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年8月11日
 */
@Controller
public class AboutAppController extends CommonController{

	@Autowired
    private ConfigTextService configTextService;
	
	@RequestMapping(value="quqing_intro")
	public String getQuqingIntro(HttpServletRequest request,@RequestParam Long appId){
		
		ConfigText confitText = configTextService.getByAppId(appId, ConfigText.TYPE1).getData();
		if(null == confitText)return CommonController.DELETE_JSP;
		
		request.setAttribute("confitText", confitText);
		return "/other/quqing_intro";
	}
}
