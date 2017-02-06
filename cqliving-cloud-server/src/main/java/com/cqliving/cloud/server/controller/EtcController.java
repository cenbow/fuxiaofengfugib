package com.cqliving.cloud.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.service.UserInfoService;
import com.cqliving.cloud.online.account.service.UserSessionService;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

/**
 * Title:ETC接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月13日
 */
@Controller
@RequestMapping(value="/etc")
public class EtcController {
    private static final Logger logger = LoggerFactory.getLogger(EtcController.class);
    
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserSessionService userSessionService;
    
    /**
     * Title:查询
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月13日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"query"}, method=RequestMethod.POST)
    public Response<Map<String, String>> query(HttpServletRequest request, HttpServletResponse response, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token){
    	response.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
    	logger.debug(String.format("ETC查询接口：appId=%d, sessionId=%s, token=%s", appId, sessionId, token));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	UserSession userSession = userSessionService.getByToken(token).getData();
    	if(userSession == null){
    		return new Response<>(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
    	}
    	UserInfo userInfo = userInfoService.get(userSession.getUserId()).getData();
    	String trafficCard = "", idCard = "";
    	if(userInfo != null){
    		trafficCard = userInfo.getTrafficCard();
    		idCard = userInfo.getIdCard();
    	}
    	Map<String, String> map = Maps.newHashMap();
    	map.put("trafficCard", trafficCard);
    	map.put("idCard", idCard);
    	Response<Map<String, String>> rs = Response.newInstance();
    	rs.setData(map);
    	logger.debug("ETC查询接口结果：" + rs);
    	return rs;
    }
    
    /**
     * Title:注册、绑定
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月13日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param trafficCard 交通信息卡卡号
     * @param idCard 身份证号码
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"binding"}, method=RequestMethod.POST)
    public Response<Void> binding(HttpServletRequest request, HttpServletResponse response, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam String trafficCard, @RequestParam String idCard){
    	response.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
    	logger.debug(String.format("ETC绑定接口：appId=%d, sessionId=%s, token=%s, trafficCard=%s, idCard=%s", appId, sessionId, token, trafficCard, idCard));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || StringUtils.isBlank(trafficCard) || StringUtils.isBlank(idCard)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	if(!(idCard.length() == 15 || idCard.length() == 18)){
    		return new Response<>(-2, "请输入正确的身份证号");
    	}
    	UserSession userSession = userSessionService.getByToken(token).getData();
    	if(userSession == null){
    		return new Response<>(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
    	}
    	UserInfo userInfo = userInfoService.get(userSession.getUserId()).getData();
    	if(userInfo == null){
    		return new Response<>(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
    	}
    	userInfo.setTrafficCard(trafficCard);
		userInfo.setIdCard(idCard);
		Response<Void> rs = userInfoService.save(userInfo);
    	logger.debug("ETC绑定接口结果：" + rs);
    	return rs;
    }
}
