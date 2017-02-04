package com.org.weixin.module.ahjy.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.module.ahjy.dto.AhjyReceiveAwardDto;
import com.org.weixin.module.ahjy.service.AhjyReceiveAwardService;

/**
 * Title:领奖Controller
 * <p>Description:</p>
 * Copyright (c) rongyiwang 2013-2016
 * @author huxiaoping on 2016年3月27日
 */
@Controller
@RequestMapping(value = "ahjy/{accId}")
public class AhjyReceiveAwardController {
	
	private static final Logger logger = LoggerFactory.getLogger(AhjyReceiveAwardController.class);
	
	
	@Autowired
	private SpyMemcachedClient memcachedClient;
	@Autowired
	private AhjyReceiveAwardService ahjyReceiveAwardService;
	
	private final static Integer EXPIRED_TIME = 10;
	/**
	 * Title: 领奖
	 * @author huxiaoping on 2016年3月26日
	 * @param request
	 * @param phone : 手机号码
	 * @return
	 */
	@RequestMapping({"receive/{phone}"})
	public synchronized String receiveAward(HttpServletRequest request,@PathVariable String accId, @PathVariable String phone) {
		try {
			//得到用户
			SessionUser user = SessionFace.getSessionUser(request);
			AhjyReceiveAwardDto dto = ahjyReceiveAwardService.receiveAward(phone, user);
			
			request.setAttribute("accId", accId);
			
			if(null!=dto){
				return returnView(dto,request);
			}else{
				return "/module/ahjy/pages/luckyDraw/receive_success";
			}
		}  catch (Exception e) {
			logger.error("领奖失败", e);
			return "/error/400";
		}
	}
	
	public String returnView(AhjyReceiveAwardDto dto,HttpServletRequest request){
        if(dto.getResultFlag() == AhjyReceiveAwardDto.RESULT_FLAG1){//手机号码不正确
        	request.setAttribute("errorMessage", "手机号码不正确");
            return "/module/ahjy/pages/luckyDraw/receive";
        }else if(dto.getResultFlag() == AhjyReceiveAwardDto.RESULT_FLAG2){//机会用尽
        	 return "/module/ahjy/pages/luckyDraw/ld_has_award";
        }else if(dto.getResultFlag() == AhjyReceiveAwardDto.RESULT_FLAG3){//没有参加活动
        	request.setAttribute("errorMessage", "您没有参加活动");
        	return "/module/ahjy/pages/luckyDraw/receive";
        }
        return "/error/400";
    }
}