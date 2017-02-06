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
import com.cqliving.cloud.online.actcustom.domain.ActCustomVoteItem;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomVote;
import com.cqliving.cloud.online.actcustom.dto.ActCustomQrcodeColumnDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteItemDto;
import com.cqliving.cloud.online.actcustom.service.ActCustomVoteItemService;
import com.cqliving.cloud.online.actcustom.service.UserActCustomVoteService;
import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;
/**
 * Title:投票活动相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2017
 * @author FangHuiLin on 2017年1月3日
 *
 */
@Controller
@RequestMapping(value="/actc")
public class ActCustomVotController {
private static final Logger logger = LoggerFactory.getLogger(ActCustomController.class);
    
    @Autowired
    private ActCustomVoteItemService actCustomVoteItemService;
    @Autowired
    private UserActCustomVoteService userActCustomVoteService;
    /**
     * <p>Description: 活动投票项列表接口</p>
	 * @author FangHuiLin on 2017年1月3日
     * @param request
     * @param response
     * @param actCode
     * @param number
     * @param itemTitle
     * @param token
     * @param sessionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"getItemList"}, method=RequestMethod.POST)
    public Response<CommonListResult<ActCustomVoteItemDto>> getFields(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam String actQrcodeCode,
    		String itemTitle,
    		String token,
    		Long lastId,
    		Long ranking,
    		String sessionId){
    	response.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
    	logger.debug(String.format("获取投票活动 ：\n code=%s",actQrcodeCode));
    	//检查参数的有效性
    	if( StringUtils.isBlank(actQrcodeCode)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<CommonListResult<ActCustomVoteItemDto>> rs = actCustomVoteItemService.getColumnsByAcode(actQrcodeCode,itemTitle,lastId,ranking);
    	logger.debug("获取活动报名所需的字段 ：" + rs);
    	return rs;
    }
    /**
     * <p>Description: 活动投票接口</p>
	 * @author FangHuiLin on 2017年1月3日
     * @param request
     * @param response
     * @param actQrcodeCode
     * @param actCustomVoteItemId
     * @param token
     * @param sessionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"acteVote"}, method=RequestMethod.POST)
    public Response<Void> acteVote(HttpServletRequest request, 
    		HttpServletResponse response, 
    		@RequestParam String actQrcodeCode,
    		@RequestParam Long actCustomVoteItemId,
    		@RequestParam String token,
    		String sessionId) {
    	response.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
		logger.debug("===================== 调用保存收货地址接口 =====================>" + 
				String.format("\n<请求参数：actQrcodeCode=%s,actCustomVoteItemId=%d, token=%s,sessionId=%s ", 
						actQrcodeCode,actCustomVoteItemId,token, sessionId));
		Response<Void> response1 = Response.newInstance();
		UserActCustomVote userActCustomVote=new UserActCustomVote();
		//检查参数的必要性
		if (actCustomVoteItemId==null||StringUtils.isBlank(actQrcodeCode)||StringUtils.isBlank(token)||StringUtils.isBlank(sessionId)) {
			response1.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response1.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用保存收货地址接口异常：" + response1);
			return response1;
		}
		userActCustomVote.setActQrcodeCode(actQrcodeCode);
		userActCustomVote.setActCustomVoteItemId(actCustomVoteItemId);
	    response1 = userActCustomVoteService.addActeVote(actQrcodeCode,token,sessionId,userActCustomVote);
		
		logger.debug("调用保存或者修改收货地址接口结果：" + response1);
		return response1;
	}
    /**
     * <p>Description: 获取投票项详情</p>
	 * @author FangHuiLin on 2017年1月3日
     * @param request
     * @param response
     * @param actCustomVoteItemId
     * @param token
     * @param sessionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"acteVoteDetail"}, method=RequestMethod.POST)
    public Response<ActCustomVoteItem> acteVoteDetail(HttpServletRequest request, 
    		HttpServletResponse response, 
    		@RequestParam Long actCustomVoteItemId,
    		String token,
    		String sessionId) {
    	response.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
		logger.debug("===================== 调用保存收货地址接口 =====================>" + 
				String.format("\n<请求参数：actCustomVoteItemId=%d, token=%s,sessionId=%s ", 
						actCustomVoteItemId,token, sessionId));
		Response<ActCustomVoteItem> response1 = Response.newInstance();
		//检查参数的必要性
		if (actCustomVoteItemId==null) {
			response1.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response1.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用保存收货地址接口异常：" + response1);
			return response1;
		}
	    response1 = actCustomVoteItemService.acteVoteDetail(token,sessionId,actCustomVoteItemId);
		
		logger.debug("调用保存或者修改收货地址接口结果：" + response1);
		return response1;
	}
    @ResponseBody
    @RequestMapping(value={"acteDetail"}, method=RequestMethod.POST)
    public Response<ActCustomVoteDto> acteDetail(HttpServletRequest request, 
    		HttpServletResponse response, 
    		@RequestParam String actQrcodeCode,
    		String token,
    		String sessionId) {
    	response.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
		logger.debug("===================== 调用获取活动详情接口 =====================>" + 
				String.format("\n<请求参数：actQrcodeCode=%s, token=%s,sessionId=%s ", 
						actQrcodeCode,token, sessionId));
		Response<ActCustomVoteDto> response1 = Response.newInstance();
		//检查参数的必要性
		if (StringUtils.isBlank(actQrcodeCode)) {
			response1.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response1.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取活动详情接口异常：" + response1);
			return response1;
		}
	    response1 = actCustomVoteItemService.acteDetail(token,sessionId,actQrcodeCode);
		
		logger.debug("调用获取活动详情口结果：" + response1);
		return response1;
	}
}
