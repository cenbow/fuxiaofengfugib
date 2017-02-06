/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.online.act.domain.UserActQrcode;
import com.cqliving.cloud.online.act.service.UserActQrcodeService;
import com.cqliving.tool.common.Response;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年12月16日
 */

@Controller
@RequestMapping(value="actqrcode")
public class ActQrcodeController {

	@Autowired
	UserActQrcodeService userActQrcodeService;
	
	/**
	 * Title:
	 * <p>Description:获取优惠券</p>
	 * @author fuxiaofeng on 2016年12月16日
	 * @param request
	 * @param code 活动code
	 * @param token  登录用户的token
	 * @return
	 */
	@RequestMapping(value="coupon")
	@ResponseBody
	public Response<UserActQrcode> findByCode(HttpServletRequest request,HttpServletResponse response,String code,String token){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		Response<UserActQrcode> rp = userActQrcodeService.findByCode(code, token);
		return rp;
	}
	
	/**
	 * Title:
	 * <p>Description: 核销优惠券</p>
	 * @author fuxiaofeng on 2016年12月16日
	 * @param request
	 * @param code 优惠券二维码上的活动code
	 * @param token  优惠券二维码上的用户token
	 * @return
	 */
	@RequestMapping(value="verify")
	@ResponseBody
	public Response<UserActQrcode> verify(HttpServletRequest request,HttpServletResponse response,String code,String token){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		return userActQrcodeService.verify(code, token);
	}
}
