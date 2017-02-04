/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.szc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.web.support.JsonResult;

import com.cqliving.tool.common.util.StringUtil;
import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.CommonController;
import com.org.weixin.module.szc.constant.SzcConstant;
import com.org.weixin.module.szc.domain.SzcUserAward;
import com.org.weixin.module.szc.manager.SzcUserAwardManager;
import com.org.weixin.system.domain.SysWechatUser;
import com.org.weixin.system.service.SysWechatUserService;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月4日
 */
@Controller
@RequestMapping(value="/szc/{accId}")
public class SzcServerController extends CommonController{

	@Autowired
	SzcUserAwardManager szcUserAwardManager;
	@Autowired
	SysWechatUserService sysWechatUserService;
	
	private Logger logger = LoggerFactory.getLogger(SzcServerController.class);
	
	private final static String INDEX = "/module/szc/index";
	private final static String LUCK_DRAW = "/module/szc/award";
	
	@RequestMapping(value="index")
	public String index(HttpServletRequest request,@PathVariable Long accId,Integer district){
		
		if(null == district){
			district = SzcConstant.HEFEI;
		}
		request.setAttribute("district",district);
		request.setAttribute("accId",accId);
		request.setAttribute("allDistrict",SzcConstant.DISTRICT_MAP);
		request.setAttribute("allDistrictTitle",SzcConstant.DISTRICT_TITLE);
		//设置分享，获取config
	    super.setUserAndShare(request,accId);
		return INDEX;
	}
	
	@RequestMapping(value="luck_draw_jsp")
	public String luckDraw(HttpServletRequest request,@PathVariable Long accId,Integer district){
		request.setAttribute("accId",accId);
		if(null == district){
			district = SzcConstant.HEFEI;
		}
		request.setAttribute("district",district);
		request.setAttribute("allDistrictTitle",SzcConstant.DISTRICT_TITLE);
		//设置分享，获取config
		super.setUserAndShare(request,accId);
		return LUCK_DRAW;
	}
	
	@RequestMapping(value="luck_draw")
	@ResponseBody
	public JsonResult luckDraw(HttpServletRequest request,@RequestParam String phone,Integer district){
		
		JsonResult jr = new JsonResult();
		try {
			SessionUser sessionUser = SessionFace.getSessionUser(request);
			logger.info("-------->>>>>>>当前登录用户信息：{}",sessionUser);
			logger.info("-------->>>>>>>当前抽奖的区域：{}",district);
			if(null != district)sessionUser.setDistrict(district);
			SysWechatUser sysWechatUser= sysWechatUserService.get(sessionUser.getId());
			if(StringUtil.isEmpty(sysWechatUser.getPhone()) && !StringUtil.isEmpty(phone)){
				sysWechatUserService.updatePhone(phone, sessionUser.getId());
				sessionUser.setPhone(phone);
				SessionFace.setSessionUser(request, sessionUser);
			}
			logger.info("-------->>>>>>>修改后的当前登录用户信息：{}",sessionUser);
			SzcUserAward szcUserAward = szcUserAwardManager.luckDraw(phone,sessionUser);
			jr.appendData("szcUserAward", szcUserAward);
		} catch (BusinessException e) {
			jr.setCode(e.getErrorCode());
			jr.setMessage(e.getMessage());
		}
		return jr;
	}
}
