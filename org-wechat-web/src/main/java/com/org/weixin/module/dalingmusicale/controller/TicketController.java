/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.web.support.JsonResult;

import com.cqliving.tool.common.util.StringUtil;
import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.CommonController;
import com.org.weixin.module.dalingmusicale.domain.MusicaleTicket;
import com.org.weixin.module.dalingmusicale.domain.UserTicket;
import com.org.weixin.module.dalingmusicale.domain.UserViewHis;
import com.org.weixin.module.dalingmusicale.dto.ShareDto;
import com.org.weixin.module.dalingmusicale.enums.MusicaleEnum;
import com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager;
import com.org.weixin.module.dalingmusicale.manager.UserTicketManager;
import com.org.weixin.module.dalingmusicale.manager.UserViewHisManager;
import com.org.weixin.system.service.SysWechatUserService;

/**
 * Title:
 * <p>Description:抢票</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月16日
 */
@Controller
@RequestMapping(value="musicale/{accId}")
public class TicketController extends CommonController{

	@Autowired
	SysWechatUserService sysWechatUserService;
	@Autowired
	UserTicketManager userTicketManager;
	@Autowired
	UserViewHisManager userViewHisManager;
	@Autowired
	MusicaleTicketManager musicaleTicketManager;
	private final static String CHECK_PHONE_JSP = "/module/daling/musicale/send_sms";
	private final static String TICKET_INDEX = "/module/daling/musicale/musicale_index";
	private final static String GRAB_INDEX = "/module/daling/musicale/grab_ticket_index";
	
	@RequestMapping(value="index")
	public String ticketIndex(HttpServletRequest request,@PathVariable Long accId){
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		userViewHisManager.saveUserViewHis(request.getRequestURI(), "音乐会抢票首页", sessionUser.getId(), UserViewHis.VIEWTYPE2);
		super.setUserAndShare(request, accId);
		request.setAttribute("shareDto",ShareDto.getMusicaleShare());
		return TICKET_INDEX;
	}
	
	@RequestMapping(value="ticket_jsp")
	public String ticketJsp(HttpServletRequest request,@PathVariable Long accId){
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		if(StringUtil.isEmpty(sessionUser.getPhone())){
			return CHECK_PHONE_JSP;
		}
		List<MusicaleTicket> tickets = musicaleTicketManager.findTicket();
		request.setAttribute("tickets",tickets);
		super.setUserAndShare(request, accId);
		request.setAttribute("shareDto",ShareDto.getMusicaleShare());
		return GRAB_INDEX;
	}
	
	@ResponseBody
	@RequestMapping(value="grab_ticket")
	public JsonResult getTicket(HttpServletRequest request,Long musicaleId){
		
		JsonResult jr = new JsonResult();
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		try {
			UserTicket userTicket = userTicketManager.grabTicket(sessionUser.getPhone(), sessionUser.getId(), musicaleId);
			jr.appendData("userTicket",userTicket);
			if(UserTicket.TAKESTATUS0.byteValue() == userTicket.getTakeStatus().byteValue()){
				jr.setCode(MusicaleEnum.MUSICALE_NO_GOT.code);
				jr.setMessage(MusicaleEnum.MUSICALE_NO_GOT.msg);
			}else{
				jr.setMessage("恭喜你，已抽中");
			}
		} catch (BusinessException e) {
			jr.setCode(e.getErrorCode());
			jr.setMessage(e.getMessage());
			e.printStackTrace();
		}catch(Exception e1){
			jr.setCode(-1);
			jr.setMessage(e1.getMessage());
			e1.printStackTrace();
		}
		return jr;
	}
}
