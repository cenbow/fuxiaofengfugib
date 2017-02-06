package com.cqliving.cloud.server.controller;

import java.util.List;
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
import com.cqliving.cloud.online.actcustom.dto.ActCustomQrcodeColumnDto;
import com.cqliving.cloud.online.actcustom.service.ActCustomQrcodeColumnService;
import com.cqliving.tool.common.Response;

/**
 * Title:单独活动、定制活动相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月20日
 */
@Controller
@RequestMapping(value="/actc")
public class ActCustomController {
	
    private static final Logger logger = LoggerFactory.getLogger(ActCustomController.class);
    
    @Autowired
    private ActCustomQrcodeColumnService actCustomQrcodeColumnService;
	
    /**
     * Title:获取活动报名所需的字段 
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月21日
     * @param request
     * @param sessionId
     * @param token
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"getColumns"}, method=RequestMethod.POST)
    public Response<List<ActCustomQrcodeColumnDto>> getFields(HttpServletRequest request, HttpServletResponse response, @RequestParam String sessionId, String token, @RequestParam String code){
    	response.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
    	logger.debug(String.format("获取活动报名所需的字段 ：\nsessionId=%s, token=%s, code=%s", sessionId, token, code));
    	//检查参数的有效性
    	if(StringUtils.isBlank(sessionId) || StringUtils.isBlank(code)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<List<ActCustomQrcodeColumnDto>> rs = actCustomQrcodeColumnService.getColumnsByQrcode(code);
    	logger.debug("获取活动报名所需的字段 ：" + rs);
    	return rs;
    }
    
    /**
     * Title:参赛者列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月21日
     * @param request
     * @param sessionId
     * @param token
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"signList"}, method=RequestMethod.POST)
    public Response<Map<String, Object>> signList(HttpServletRequest request, HttpServletResponse response, @RequestParam String sessionId, String token, @RequestParam String code){
    	response.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
    	logger.debug(String.format("参赛者列表：\nsessionId=%s, token=%s, code=%s", sessionId, token, code));
    	//检查参数的有效性
    	if(StringUtils.isBlank(sessionId) || StringUtils.isBlank(code)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<Map<String, Object>> rs = actCustomQrcodeColumnService.signList(sessionId, token, code);
    	logger.debug("参赛者列表：" + rs);
    	return rs;
    }
    
	/**
	 * Title:报名保存
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月21日
	 * @param request
	 * @param sessionId
	 * @param token
	 * @param code
	 * @param columnIds
	 * @param values
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value={"signSave"}, method=RequestMethod.POST)
    public Response<Void> signSave(HttpServletRequest request, HttpServletResponse response, @RequestParam String sessionId, @RequestParam String token, @RequestParam String code, @RequestParam String columnIds, @RequestParam String values){
    	response.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
    	logger.debug(String.format("报名保存：\nsessionId=%s, token=%s, code=%s, columnIds=%s, values=%s", sessionId, token, code, columnIds, values));
    	//检查参数的有效性
    	if(StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || StringUtils.isBlank(code) || StringUtils.isBlank(columnIds) || StringUtils.isBlank(values)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<Void> rs = actCustomQrcodeColumnService.signSave(sessionId, token, code, columnIds, values);
    	logger.debug("报名保存： " + rs);
    	return rs;
    }
	
	/**
	 * Title:验证用户是否报名
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月22日
	 * @param request
	 * @param response
	 * @param sessionId
	 * @param token
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"isSign"}, method=RequestMethod.POST)
	public Response<Map<String, String>> isSign(HttpServletRequest request, HttpServletResponse response, @RequestParam String sessionId, @RequestParam String token, @RequestParam String code){
		response.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
		logger.debug(String.format("是否报名接口：\nsessionId=%s, token=%s, code=%s", sessionId, token, code));
		//检查参数的有效性
		if(StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || StringUtils.isBlank(code)){
			return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
		}
		Response<Map<String, String>> rs = actCustomQrcodeColumnService.isSign(sessionId, token, code);
		logger.debug("是否报名接口： " + rs);
		return rs;
	}
}
