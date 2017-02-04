/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.szc.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.web.support.JsonResult;

import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.google.common.collect.Maps;
import com.org.common.SessionFace;
import com.org.weixin.module.szc.constant.SzcConstant;
import com.org.weixin.module.szc.domain.SzcUserAward;
import com.org.weixin.module.szc.dto.SzcUserDto;
import com.org.weixin.module.szc.manager.SzcUserAwardManager;
import com.org.weixin.module.szc.sms.provider.SmsSendUtil;
import com.org.weixin.module.szc.sms.vo.RequestData;
import com.org.weixin.module.szc.sms.vo.SmsRequest;
import com.org.weixin.module.szc.util.RandomUtil;
import com.org.weixin.util.JsonUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月2日
 */
@Controller
public class SzcAwardController {

	private Logger logger = LoggerFactory.getLogger(SzcAwardController.class);
	@Autowired
	private SpyMemcachedClient memcachedClient;
	@Autowired
	SzcUserAwardManager szcUserAwardManager;
	
	private final static String STATISTICS_PAGE = "/module/szc/report";
	private final static String SECURITY_PAGE = "/module/szc/security";
	private final static String SECURITY_CODE_SESSION_KEY = "security_code_session_key";
	
	@ResponseBody
	@RequestMapping(value="send_sms")
	public JsonResult sendSms(HttpServletRequest request,String phone){
		
		JsonResult jr = new JsonResult();
		
		logger.info("<<<<<<<<<<---------接收手机号:{}",phone);
		String smsMsg = memcachedClient.get(SzcConstant.DISTRICT_MSG_MAP.get(1));
		smsMsg = smsMsg.replace("convert_code", RandomUtil.randomUUid(8));
		String channel = memcachedClient.get(SzcConstant.SZC_SMS_CHANNEL);
		String publicKey = memcachedClient.get(SzcConstant.SZC_SMS_PUBLIC_KEY);
		String url = memcachedClient.get(SzcConstant.SZC_SMS_URL);
		String token = memcachedClient.get(SzcConstant.SZC_SMS_TOKEN);
		RequestData data = new RequestData(phone,smsMsg);
		logger.info("------短信data:{}",JsonUtil.toJSONString(data));
		String strData = data.getRequestData(publicKey);
		String timeStamp = String.valueOf(System.currentTimeMillis());
		SmsRequest sms = new SmsRequest(channel,strData,token,timeStamp);
		String s = SmsSendUtil.sendSms(sms, url);
		logger.info("--------短信发送回调结果:{}",s);
		jr.setMessage(s);
		return jr;
	}
	
	@RequestMapping(value="check_verify")
	public String checkVerify(HttpServletRequest request,String verify,Integer district){
		
		//if(null == district) district = SzcConstant.HEFEI;
		String verifyCode = memcachedClient.get(SzcConstant.SZC_VERIFY_SECURIRY_CODE);
		if(!StringUtil.isEmpty(verify) && verify.equals(verifyCode)){
			return "redirect:/statistics.html";//?district="+district
		}
		if(!StringUtil.isEmpty(verify)){
			request.setAttribute("error_msg", "验证码不对");
		}
		SessionFace.setAttribute(request,SECURITY_CODE_SESSION_KEY, verifyCode);
		return SECURITY_PAGE;
	}
	
	@RequestMapping(value="statistics")
	public String verifyConvertCode(HttpServletRequest request,String convertCode,String dateStr,Integer district){
		
		//if(null == district)district = SzcConstant.HEFEI;
		Object securityCode = SessionFace.getAttribute(request,SECURITY_CODE_SESSION_KEY);
		if(null == securityCode){
			return "redirect:/check_verify.html";//?district=+district;
		}
		Map<String,Object> conditions = Maps.newHashMap();
		Map<String,Boolean> sortMap = Maps.newHashMap();
		Date dayStart = null;
		Date dayEnd = null;
		if(!StringUtil.isEmpty(dateStr)){
			Date date = DateUtil.parse(dateStr);
			dayStart = Dates.dayStart(date);
			dayEnd = Dates.dayEnd(date);
			dateStr = DateUtil.format(date, DateUtil.FORMAT_YYYY_MM_DD);
			request.setAttribute("dateStr", dateStr);
			conditions.put("GTE_awardTime",dayStart);
			conditions.put("LTE_awardTime",dayEnd);
		}
		//request.setAttribute("awardNumDaily",szcUserAwardManager.findAwardNumDaily(dayStart, dayEnd,district));
		//request.setAttribute("verifyAwardNumDaily",szcUserAwardManager.findVerifyNumDaily(dayStart, dayEnd,district));
		conditions.put("EQ_convertCode", convertCode);
		//conditions.put("EQ_district",district);
		sortMap.put("award_time",false);
		if(!StringUtil.isEmpty(convertCode)){
			request.setAttribute("szcUserAwards",szcUserAwardManager.queryByConditions(conditions, sortMap));
		}
		request.setAttribute("allstatus",SzcUserAward.allTakeStatuss);
		request.setAttribute("allDistrict",SzcConstant.DISTRICT_MAP);
		return STATISTICS_PAGE;
	}
	
	@RequestMapping(value="verify_convert_code")
	@ResponseBody
	public JsonResult verify(HttpServletRequest request,@RequestParam String convertCode){
		
		JsonResult jr = new JsonResult();
		try {
			szcUserAwardManager.verifyAward(convertCode);
		} catch (BusinessException e) {
			e.printStackTrace();
			jr.setCode(e.getErrorCode());
			jr.setMessage(e.getMessage());
			jr.setSuccess(false);
		}
	    return jr;	
	}
	
	@RequestMapping(value="find_by_convert")
	@ResponseBody
	public JsonResult getUser(HttpServletRequest request,String convertCode){
		
		JsonResult jr = new JsonResult();
		try {
			List<SzcUserAward> userAwards = szcUserAwardManager.findByConvertCode(convertCode);
			SzcUserDto userDto = new SzcUserDto();
			if(CollectionUtils.isNotEmpty(userAwards)){
				SzcUserAward szcUserAward = userAwards.get(0);
				userDto.setAwardCode(szcUserAward.getAwardCode());
				userDto.setAwardName(szcUserAward.getAwardName());
				userDto.setAwardTime(szcUserAward.getAwardTime());
				userDto.setPhone(szcUserAward.getPhone());
				userDto.setTakeStatus(szcUserAward.getTakeStatus());
			}
			jr.appendData("user",userDto);
		} catch (Exception e) {
			jr.setCode(-1);
			jr.setMessage(e.getMessage());
			jr.setSuccess(false);
			e.printStackTrace();
		}
		return jr;
	}
	
	@RequestMapping(value="count_award")
	public String countAward(HttpServletRequest request){
		
		request.setAttribute("items",szcUserAwardManager.statistics(null, null));
		request.setAttribute("userItems", szcUserAwardManager.statisticsUser());
		request.setAttribute("userNum", szcUserAwardManager.statisticsJoinNum());
		request.setAttribute("allDistrict",SzcConstant.DISTRICT_MAP);
		request.setAttribute("allTakeStatus",SzcUserAward.allTakeStatuss);
		return "/module/szc/statistics";
	}
}
