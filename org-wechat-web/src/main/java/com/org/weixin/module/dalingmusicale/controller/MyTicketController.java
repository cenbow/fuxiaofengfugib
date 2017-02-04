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
import com.org.weixin.module.ahjy.qrcode.QRcodeProvider;
import com.org.weixin.module.dalingmusicale.constant.MusicaleConstant;
import com.org.weixin.module.dalingmusicale.domain.MusicaleTicket;
import com.org.weixin.module.dalingmusicale.domain.UserTicket;
import com.org.weixin.module.dalingmusicale.dto.ShareDto;
import com.org.weixin.module.dalingmusicale.dto.UserTicketDto;
import com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager;
import com.org.weixin.module.dalingmusicale.manager.UserTicketManager;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月18日
 */
@Controller
public class MyTicketController extends CommonController{

	@Autowired
	UserTicketManager userTicketManager;
	@Autowired
	MusicaleTicketManager musicaleTicketManager; 
	
	private final static String MY_TICKET="/module/daling/musicale/my_ticket";
	private final static String VERIFY_TICKET="/module/daling/musicale/verify_ticket";
	private final static String TICKET_INFO = "/module/daling/musicale/ticket_info";
	
	@RequestMapping(value="musicale/{accId}/my_ticket")
	public String findMyTicket(HttpServletRequest request,@PathVariable Long accId){
		
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		try {
			List<UserTicketDto> userTicket = userTicketManager.findMyTicket(sessionUser.getId());
			request.setAttribute("userTickets",userTicket);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("shareDto",ShareDto.getMusicaleShare());
		super.setUserAndShare(request, accId);
		return MY_TICKET;
	}
	
	//查询门票信息,生成核销二维码
	@RequestMapping(value="musicale/{accId}/ticket_info")
	public String ticketInfo(HttpServletRequest request,@PathVariable Long accId,String verifyCode,Long musicaleId){
		
		String path = getRequestUrl(request);
		String webRootPath = request.getSession().getServletContext().getRealPath("/");
		String qrcodePath = "/musicale"+musicaleId+".png";
		try {
			String redirectUrl = "http://"+path+"/verify_info.html?verifyCode="+verifyCode;
			webRootPath+= qrcodePath;
			QRcodeProvider.textQrcode(redirectUrl, webRootPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("qrcodePath",qrcodePath);
		MusicaleTicket musicaleTicket = musicaleTicketManager.get(musicaleId);
		request.setAttribute("musicaleTicket",musicaleTicket);
		
		super.setUserAndShare(request, accId);
		request.setAttribute("shareDto",ShareDto.getMusicaleShare());
		return TICKET_INFO;
	}
	
	//查找中奖信息
	@RequestMapping(value="verify_info")
	public String findByVerifyCode(HttpServletRequest request,String verifyCode){
		
		try {
			UserTicketDto userTicketDto = userTicketManager.findByVerifyCode(verifyCode);
			request.setAttribute("userTicketDto",userTicketDto);
			request.setAttribute("allTakeStatus",UserTicket.allTakeStatuss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return VERIFY_TICKET;
	}
	
	@RequestMapping(value="verify_ticket")
	@ResponseBody
	public JsonResult verify(HttpServletRequest request,String verifyCode,String securityCode){
		JsonResult jr = new JsonResult();
		try {
			
			String safeCode = memcachedClient.get(MusicaleConstant.VERIFY_SECURITY_CODE);
			if(!safeCode.equals(securityCode)){
				jr.setCode(-1);
				jr.setMessage("核销验证码错误，请输入正确的核销验证码");
				return jr;
			}
			UserTicket userTicket = userTicketManager.verifyTicket(verifyCode);
			jr.appendData("userTicket",userTicket);
			jr.setMessage("核销成功");
		} catch (BusinessException e) {
			jr.setCode(e.getErrorCode());
			jr.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jr;
	}
	
	//获取域名和上下文地址 
	private String getRequestUrl(HttpServletRequest request){
		String server = request.getServerName();
		int port = request.getServerPort();
		String contextPath = request.getContextPath();
		if(port != 80){
			server += ":"+port;
		}
		if(StringUtil.isEmpty(contextPath)){
			return server;
		}
		return server+"/"+contextPath;
	}
}
