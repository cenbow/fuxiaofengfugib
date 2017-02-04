/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.web.support.JsonResult;

import com.cqliving.tool.common.util.StringUtil;
import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.module.dalingmusicale.domain.SmsCode;
import com.org.weixin.module.dalingmusicale.manager.SmsCodeManager;
import com.org.weixin.system.domain.SysWechatUser;
import com.org.weixin.system.service.SysWechatUserService;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月16日
 */
@Controller
@RequestMapping(value="musicale/{accId}")
public class SMSController {

	@Autowired
	SysWechatUserService sysWechatUserService;
	@Autowired
	SmsCodeManager smsCodeManager;
	
	@RequestMapping(value="sms_code")
	@ResponseBody
	public JsonResult getSmsCode(HttpServletRequest request,String phone){
		JsonResult jr = new JsonResult();
		try {
			SmsCode smsCode = smsCodeManager.getSmsCode(phone);
			jr.appendData("smsCode", smsCode);
			jr.setMessage("验证码已发送，请注意查收");
		} catch (BusinessException e) {
			jr.setCode(e.getErrorCode());
			jr.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jr;
	}
	
	@RequestMapping(value="verify_sms_code")
	@ResponseBody
	public JsonResult verifySmsCode(HttpServletRequest request,String name,String phone,String smsCode){
		
        JsonResult jr = new JsonResult();
        try {
			smsCodeManager.verifySmsCode(name, phone, smsCode);
			this.updateSessionUser(request, phone);
		} catch (BusinessException e) {
			jr.setCode(e.getErrorCode());
			jr.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jr;
	}
	
	private void updateSessionUser(HttpServletRequest request,String phone){
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		SysWechatUser sysWechatUser= sysWechatUserService.get(sessionUser.getId());
		if(StringUtil.isEmpty(sysWechatUser.getPhone()) && !StringUtil.isEmpty(phone)){
			sysWechatUserService.updatePhone(phone, sessionUser.getId());
			sessionUser.setPhone(phone);
			SessionFace.setSessionUser(request, sessionUser);
		}
	}
}
