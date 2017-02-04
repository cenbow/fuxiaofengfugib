package com.org.weixin.module.ahjy.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.web.support.JsonResult;

import com.cqliving.tool.common.util.date.DateUtil;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.module.ahjy.domain.AhjyActivity;
import com.org.weixin.module.ahjy.domain.AhjyUserActivity;
import com.org.weixin.module.ahjy.dto.AhjyUserActivityDto;
import com.org.weixin.module.ahjy.service.AhjyActivityService;
import com.org.weixin.module.ahjy.service.AhjyUserActivityService;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年3月26日
 */
@Controller
@RequestMapping(value = "ahjy/{accId}")
public class AhjyGameController {
	
	private static final Logger logger = LoggerFactory.getLogger(AhjyGameController.class);
	
	private static final String VIEW_PREPARE_FIRST = "/module/ahjy/pages/prepareFirst";
	private static final String VIEW_PREPARE_UN_FIRST = "/module/ahjy/pages/prepareUnFirst";
	private static final String VIEW_PLAY = "/module/ahjy/pages/play";
	private static final String VIEW_PLAY_LOSE = "/module/ahjy/pages/playLose";
	private static final String VIEW_PLAY_WIN = "/module/ahjy/pages/playWin";
	
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
	@RequestMapping({"addScore"})
	public JsonResult addScore(HttpServletRequest request, @RequestParam("a") Long activityId, @RequestParam("s") Integer score) {
		JsonResult result = new JsonResult();
		try {
			List<AhjyUserActivity> userList = memcachedClient.get("ahjyActivity_" + activityId);
			SessionUser user = SessionFace.getSessionUser(request);
//			SessionUser user = new SessionUser();
//			user.setId(1L);
			AhjyActivity activity = ahjyActivityService.get(activityId);
			if (AhjyActivity.STATUS1 == activity.getStatus()) {
				//加分
				for (AhjyUserActivity obj : userList) {
					if (obj.getUserId().equals(user.getId())) {
						obj.setScore(obj.getScore() + score);
					}
				}
				memcachedClient.set("ahjyActivity_" + activityId, userList);
			}
			result.appendData("status", activity.getStatus());
			result.appendData("userMap", userList);
		} catch (Exception e) {
			logger.error("加分失败", e);
			result.setCode(-1);
			result.setSuccess(false);
			result.setMessage("加分失败");
		}
		return result;
	}
	
	/**
	 * Title: 开始活动
	 * @author Tangtao on 2016年3月26日
	 * @param request
	 * @param activityId 活动 id
	 * @return
	 */
	@ResponseBody
	@RequestMapping({"begin"})
	public JsonResult begin(HttpServletRequest request, @RequestParam("a") Long activityId) {
		JsonResult result = new JsonResult();
		try {
			ahjyActivityService.begin(activityId);
			List<AhjyUserActivity> userList = ahjyUserActivityService.getByActivity(activityId);
			memcachedClient.set("ahjyActivity_" + activityId, userList);
		} catch (Exception e) {
			logger.error("活动开始失败", e);
			result.setCode(-1);
			result.setSuccess(false);
			result.setMessage("活动开始失败");
		}
		return result;
	}
	
	/**
	 * Title: 结束活动
	 * @author Tangtao on 2016年3月26日
	 * @param request
	 * @param activityId 活动 id
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping({"finish"})
//	public JsonResult finish(HttpServletRequest request, @RequestParam("a") Long activityId) {
//		JsonResult result = new JsonResult();
//		try {
//			List<AhjyUserActivity> userList = memcachedClient.get("ahjyActivity_" + activityId);
//			ahjyUserActivityService.finish(activityId, userList);
//		} catch (Exception e) {
//			logger.error("活动结束失败", e);
//			result.setCode(-1);
//			result.setSuccess(false);
//			result.setMessage("活动结束失败");
//		}
//		return result;
//	}
	
	/**
	 * Title: 游戏结果
	 * @author Tangtao on 2016年3月28日
	 * @param request
	 * @param activityId 活动 id
	 * @return
	 */
	@RequestMapping({"gr/{activityId}"})
	public String gameResult(HttpServletRequest request, Model model, @PathVariable Long activityId) {
		AhjyActivity activity = ahjyActivityService.get(activityId);
		if (activity != null && activity.getStatus().equals(AhjyActivity.STATUS2)) {
			Long userId = activity.getAwardUserId();
			SessionUser user = SessionFace.getSessionUser(request);
			Long sessionUserId = user.getId();
			List<AhjyUserActivity> list = ahjyUserActivityService.getByActivity(activityId);
			
			if (sessionUserId.equals(userId) && !CollectionUtils.isEmpty(list)) {//获胜者
				for(AhjyUserActivity ahjyUserActivity:list){
					if(sessionUserId.longValue() == ahjyUserActivity.getUserId().longValue() &&
							null !=ahjyUserActivity.getScore() && ahjyUserActivity.getScore().intValue() >=200){
						return VIEW_PLAY_WIN;
					}
				}
				
			} 
		}
		return VIEW_PLAY_LOSE;
	}
	
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
	
	/**
	 * Title: 加入活动
	 * @author Tangtao on 2016年3月26日
	 * @param request
	 * @param activityId 加入活动
	 * @return
	 */
	@RequestMapping({"join/{activityId}"})
	public String join(HttpServletRequest request, Model model, @PathVariable Long activityId) {
		model.addAttribute("activityId", activityId);
		SessionUser user = SessionFace.getSessionUser(request);
//		SessionUser user = new SessionUser();
//		user.setId(1L);
//		user.setHeadimgurl("http://xxox.jpg");
		try {
			Integer result = ahjyUserActivityService.join(activityId, user.getId(), user.getHeadimgurl());
//			Integer result = 0;
			if (result.equals(0)) {	//首位加入
				model.addAttribute("time", 18);
				return VIEW_PREPARE_FIRST;
			} else if (result.equals(1) || result.equals(-2)) {	//非首位加入、已加入
				AhjyUserActivity first = ahjyUserActivityService.getFirstJoinUser(activityId);
				Integer time = getLeftSecond(DateUtils.addSeconds(first.getJoinTime(), 18));
				model.addAttribute("time", time);
				return VIEW_PREPARE_UN_FIRST;
			}
			return VIEW_PLAY_LOSE;
		} catch (Exception e) {
			logger.error("加入活动失败", e);
			return VIEW_PLAY_LOSE;
		}
	}
	
	private Integer getLeftSecond(Date beginTime) {
		Calendar cal = Calendar.getInstance();
		Calendar endca = Calendar.getInstance();
		endca.setTime(beginTime);
		long daterange = endca.getTimeInMillis() - cal.getTimeInMillis();
		long time = 1000; // A day in milliseconds
		int resutl = (int) (daterange / time);
		return resutl;
	}

	/**
	 * Title: 开始游戏
	 * @author Tangtao on 2016年3月28日
	 * @param request
	 * @param activityId 活动 id
	 * @return
	 */
	@RequestMapping({"play/{activityId}"})
	public String play(HttpServletRequest request, Model model, @PathVariable Long activityId) {
		AhjyActivity activity = ahjyActivityService.get(activityId);
		if (activity != null && activity.getStatus().equals(AhjyActivity.STATUS1)) {
			model.addAttribute("activityId", activityId);
			return VIEW_PLAY;
		}
		return VIEW_PLAY_LOSE;
	}
	
}