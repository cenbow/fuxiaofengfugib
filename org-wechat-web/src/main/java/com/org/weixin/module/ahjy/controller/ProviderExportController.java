/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.ahjy.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.web.support.JsonResult;

import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.google.common.collect.Maps;
import com.org.weixin.module.ahjy.common.AhjyConstants;
import com.org.weixin.module.ahjy.domain.AhjyActivity;
import com.org.weixin.module.ahjy.domain.AhjyUserActivity;
import com.org.weixin.module.ahjy.dto.AhjyActivityCountDto;
import com.org.weixin.module.ahjy.dto.AhjyUserActivityDto;
import com.org.weixin.module.ahjy.qrcode.QRcodeProvider;
import com.org.weixin.module.ahjy.service.AhjyActivityService;
import com.org.weixin.module.ahjy.service.AhjyUserActivityService;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年3月29日
 */
@RequestMapping(value="ahjy_export")
@Controller
public class ProviderExportController {
	
	@Autowired
	private AhjyActivityService ahjyActivityService;
	@Autowired
	private AhjyUserActivityService ahjyUserActivityService;
	@Autowired
	private SpyMemcachedClient memcachedClient;
	
	/**
	 * Title: 获取活动状态
	 * @author Tangtao on 2016年3月26日
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping({"as"})
	public JsonResult getActivityStatus(HttpServletRequest request, @RequestParam("a") Long activityId) {
		JsonResult result = new JsonResult();
		if (activityId == null) {
			result.setCode(-1);
			result.setSuccess(false);
			result.setMessage("活动不存在");
			return result;
		}
		AhjyActivity activity = ahjyActivityService.get(activityId);
		if (activity == null) {
			result.setCode(-1);
			result.setSuccess(false);
			result.setMessage("活动不存在");
			return result;
		}
		List<AhjyUserActivity> userList = null;
		List<AhjyUserActivityDto> dtos = new ArrayList<AhjyUserActivityDto>();
		switch (activity.getStatus()) {
			case AhjyActivity.STATUS0:	
			case AhjyActivity.STATUS2:
				//活动未开始/已结束，从数据库中获取数据
				userList = ahjyUserActivityService.getByActivity(activityId);
				break;
			case AhjyActivity.STATUS1:
				//活动已开始，从缓存中获取数据
				userList = memcachedClient.get("ahjyActivity_" + activityId);
				Date beginTime = activity.getBeginTime();
//				String time = memcachedClient.get(AhjyConstants.GAME_TIME_SECOND);
				Integer time = memcachedClient.get("ahjyActivity_" + activityId + "_atime");
				if (DateUtils.addSeconds(beginTime, time).before(DateUtil.now())) {	//活动时间到
					ahjyUserActivityService.finish(activityId, userList);
				}
				break;
		}
		AhjyUserActivityDto dto;
		for (AhjyUserActivity obj : userList) {
			dto = new AhjyUserActivityDto();
			BeanUtils.copyProperties(obj, dto);
			dtos.add(dto);
		}
		result.appendData("status", activity.getStatus());
		result.appendData("userList", dtos);
		return result;
	}
	
	private final static String LOGO_PATH = "ahjy_logo.png"; 
	
	@RequestMapping(value="create_acti")
	@ResponseBody
	public JsonResult getQRcode(HttpServletRequest request){
		
		JsonResult jr = new JsonResult();
		String filePath = memcachedClient.get(AhjyConstants.FILE_LOCAL_URL);
		String fileUrl = memcachedClient.get(AhjyConstants.FILE_VISIT_URL);
		String qrcodeRedirectUrl = memcachedClient.get(AhjyConstants.QRCODE_SCAN_REDIRECT_URL);
		AhjyActivity ahjyActivity = ahjyActivityService.createActivity();
		String fileName = getActivityFileName(ahjyActivity.getId());
		//放到缓存中
		String memKey = fileName.split("\\.")[0];
		setActivityInMemcached(memKey,ahjyActivity);
		File file = FileUtils.getFile(filePath, fileName);
		if(file.exists()){
			jr.appendData("activity_qrcode", fileUrl+fileName);
			jr.appendData("a",ahjyActivity.getId());
			return jr;
		}
		qrcodeRedirectUrl = qrcodeRedirectUrl.replace("{activityId}",ahjyActivity.getId().toString());
		try {
			String filestr = filePath + fileName;
			QRcodeProvider.textQrcode(qrcodeRedirectUrl, filestr);
			//QRcodeProvider.logoQrcode(qrcodeRedirectUrl, filestr, LOGO_PATH);
			jr.appendData("activity_qrcode", fileUrl+fileName);
			jr.appendData("a",ahjyActivity.getId());
			return jr;
		} catch (Exception e) {
			e.printStackTrace();
			jr.setCode(-1111);
			jr.setSuccess(false);
			jr.setMessage("生成二维码失败！");
			return jr;
		}
	}
	
	
	private String getActivityFileName(Long actiId){
		StringBuilder buil = new StringBuilder("ahjyActivity_");
		buil.append(actiId).append(".png");
		
		return buil.toString();
	}
	
	private AhjyActivity setActivityInMemcached(String key,AhjyActivity newAhjyActivity){
		
		AhjyActivity ahjyActivity = memcachedClient.get(key);
		
        String activityTimeKey = key+"_atime";
		Integer secon = memcachedClient.get(activityTimeKey);
		if(null == secon){
			memcachedClient.set(activityTimeKey,this.getRandomActivityTime());
		}
		if(null == ahjyActivity){
			memcachedClient.set(key, newAhjyActivity);
		}
		
		return memcachedClient.get(key);
	}
	
	private Integer getRandomActivityTime(){
		
		String duration = memcachedClient.get(AhjyConstants.GAME_TIME_SECOND);
		String[] arr = duration.split("-");
		Integer bottom = StringUtil.stringToInteger(arr[0]);
		Integer top = StringUtil.stringToInteger(arr[1]);
		
		Random rd = new Random();
		Integer in = rd.nextInt(top);
		
		while(in<bottom){
			in = rd.nextInt(top);
		}
		return in;
	}
	
	@RequestMapping(value="statistics")
	public String statistics(HttpServletRequest request,String beginTime,String endTime){
		
		Map<String,Object> conditions = Maps.newHashMap();
		
		conditions.put("LTE_endTime", endTime);
		conditions.put("GTE_beginTime", beginTime);
		
		Map<String,List<AhjyActivityCountDto>> map = ahjyActivityService.statistics(conditions);
		
		request.setAttribute("isGetStatus", AhjyActivity.allIsGetAwards);
		request.setAttribute("isAwardStatus", AhjyUserActivity.allIsAwards);
		request.setAttribute("statisticsData",map);
		
		return "/module/ahjy/pages/report";
	}
}
