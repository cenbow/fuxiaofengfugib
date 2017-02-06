/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.controller.module.info;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.info.service.InfoClassifyService;
import com.cqliving.cloud.online.info.service.InfoCorrelationService;
import com.cqliving.cloud.online.info.service.InfoThemeService;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.service.SysUserDataService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月16日
 */
@Controller
@RequestMapping(value="module/info")
public class InfoCorrelationController extends CommonController{

	@Autowired
	InfoClassifyService infoClassifyService;
	@Autowired
	InfoThemeService infoThemeService ;
	@Autowired
	InfoCorrelationService infoCorrelationService;
	@Autowired
	SysUserDataService sysUserDataService;
	
	private final static String INFO_CLASSIFY_MODAL = "/module/infoClassify/info_classify_modal";
	private final static String INFO_CLASSIFY_MODAL_PAGE = "/module/infoClassify/info_classify_modal_page";
	private final static String INFO_CORRELATION_MODAL = "/module/infoClassify/info_correlation_modal";
	private final static String INFO_CORRELATION_MODAL_PAGE = "/module/infoClassify/info_correlation_modal_page";
	private final static String INFO_THEME_JSP = "/module/infoClassify/info_theme";
	private final static String HAD_CORR_PAGE_JSP = "/module/infoClassify/info_had_corre_page";
	private final static String JOIN_INFO_MODAL = "/module/infoClassify/join_info_modal";
	private final static String JOIN_INFO_MODAL_PAGE = "/module/infoClassify/join_info_modal_page";
	
	private void constantsInit(HttpServletRequest request){
		
		//是否允许评论
		request.setAttribute("allCommentTypes",InfoClassify.allCommentTypes);
		//是否需要审核
		request.setAttribute("allValidateTypes",Information.allValidateTypes);
		request.setAttribute("allStatus",InfoClassify.allStatuss);
	}
	
	//加入专题弹出层的新闻列表
	@RequestMapping(value="common/info_classify_modal")
	public String queryInfoClassifyPage(HttpServletRequest request,PageInfo<InfoClassifyDto> pageInfo,
	    	@RequestParam(value = "p", required = false) String isAjaxPage,Long appId){
		
		constantsInit(request);
		
		Map<String,Boolean> orderMap = Maps.newHashMap();
		
		orderMap.put("updateTime",false);
		Map<String,Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		searchMap.put("EQ_type", Information.TYPE2);
		searchMap.put("IN_status",new Byte[]{InfoClassify.STATUS3,InfoClassify.STATUS1});
		if(null == appId){
			searchMap.put("IN_appId",sysUserDataService.findValueByUserId(sessionUser.getUserId(),SysUserData.TYPE1).getData());	
		}else{
			searchMap.put("EQ_appId",appId);
		}
		String title = (String) searchMap.get("LIKE_title");
		if(!StringUtil.isEmpty(title)){
			searchMap.put("or_LIKE_specialTheme",title);
		}
		Response<PageInfo<InfoClassifyDto>> rp = infoClassifyService.queryDtoForPage(pageInfo,searchMap, orderMap);
		request.setAttribute("pageInfo", rp.getData());
		if(!StringUtil.isEmpty(isAjaxPage)){
			return INFO_CLASSIFY_MODAL_PAGE;
		}
		return INFO_CLASSIFY_MODAL;
	}
	
	//相关新闻弹层的新闻列表
	@RequestMapping(value="common/info_correlation_modal")
	public String queryInfoClassifyCorrelationPage(HttpServletRequest request,PageInfo<InfoClassifyDto> pageInfo,
	    	@RequestParam(value = "p", required = false) String isAjaxPage,@RequestParam Long infoClassifyId,Long appId){
		
		constantsInit(request);
		
		Map<String,Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		if(null == appId){
			searchMap.put("IN_appId",sysUserDataService.findValueByUserId(sessionUser.getUserId(),SysUserData.TYPE1).getData());
		}else{
			searchMap.put("IN_appId", new Long[]{appId});
		}
		searchMap.put("EQ_infoClassifyId",infoClassifyId);
		//searchMap.put("EQ_status", InfoClassify.STATUS3);
		Map<String,Boolean> orderMap = Maps.newHashMap();
		orderMap.put("sortNo",true);
		orderMap.put("onlineTime",false);
		infoClassifyService.queryInfoClassifyCorrelationPage(pageInfo,searchMap, orderMap);
		request.setAttribute("pageInfo",pageInfo);
		
		if(!StringUtil.isEmpty(isAjaxPage)){
			return INFO_CORRELATION_MODAL_PAGE;
		}
		return INFO_CORRELATION_MODAL;
	}
	
	
	//已相关的新闻列表
	@RequestMapping(value="common/had_correlation_modal")
	public String hasRecorr(HttpServletRequest request,PageInfo<InfoClassifyDto> pageInfo,
	    	@RequestParam(value = "p", required = false) String isAjaxPage){
		
		constantsInit(request);
		Map<String,Boolean> orderMap = Maps.newHashMap();
		orderMap.put("sortNo",true);
		orderMap.put("onlineTime",false);
		//查询已相关的新闻
		Map<String,Object> searchMap = Maps.newHashMap();
		searchMap.put("EQ_infoClassifyId",request.getParameter("search_EQ_id"));
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		searchMap.put("IN_appId",sysUserDataService.findValueByUserId(sessionUser.getUserId(),SysUserData.TYPE1).getData());
		
		infoClassifyService.queryHadCorrInfoClassifyPage(pageInfo, searchMap, orderMap);
		request.setAttribute("corrpageInfo",pageInfo);
		
		return HAD_CORR_PAGE_JSP;
	}
	
	@RequestMapping(value="common/info_theme_by_infoid")
	public String findInfoThemeByInfoClassifyId(HttpServletRequest request,Long infoClassifyId){
		 
		 request.setAttribute("infoThemes",infoThemeService.findByInfoClassifyId(infoClassifyId).getData());
		 
		 return INFO_THEME_JSP;
	}
	
	@RequestMapping(value="join_special_info")
	@ResponseBody
	public Response<Void> joinSpecialInfo(HttpServletRequest request,Long refInfoClassId,
			@RequestParam(value="infoThemeIds[]") Long[] infoThemeIds,
			@RequestParam(value="infoClassifyIds[]") Long[] infoClassifyIds,
			@RequestParam(value="appIds[]") Long[] appIds){
		
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		
		return infoCorrelationService.joinSpecialInfo(infoClassifyIds, refInfoClassId, infoThemeIds, appIds,sessionUser.getNickname(),sessionUser.getUserId());
	}
	
	//设置相关
	@ResponseBody
	@RequestMapping(value="info_corretlation")
	public Response<Void> infoCorrelation(HttpServletRequest request,Long infoClassifyId,Long refInfoClassifyId,Long appId){
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		return infoCorrelationService.infoCorrelation(infoClassifyId, refInfoClassifyId, appId,sessionUser.getNickname(),sessionUser.getUserId());
	}
	
	//取消相关
	@ResponseBody
	@RequestMapping(value="common/info_cancel_corre")
	public Response<Void> cancelCorrelation(HttpServletRequest request,Long infoClassifyId,Long refInfoClassifyId){
		
		return infoCorrelationService.cancelCorrelation(infoClassifyId, refInfoClassifyId);
	}
	
	//专题新闻加入新闻弹出层
	@RequestMapping(value="join_info_modal")
	public String joinInfoPage(HttpServletRequest request,PageInfo<InfoClassifyDto> pageInfo,
	    	@RequestParam(value = "p", required = false) String isAjaxPage,@RequestParam Long icid,Long appId){
		
		Map<String,Boolean> orderMap = Maps.newHashMap();
		
		orderMap.put("onlineTime",false);
		Map<String,Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		searchMap.put("IN_status", new Byte[]{InfoClassify.STATUS3,InfoClassify.STATUS1});
		searchMap.put("EQ_type",Information.TYPE0);
		if(null == appId){
			searchMap.put("IN_appId",sysUserDataService.findValueByUserId(sessionUser.getUserId(),SysUserData.TYPE1).getData());
		}else{
			searchMap.put("EQ_appId",appId);
		}
		Response<PageInfo<InfoClassifyDto>> rp = infoClassifyService.queryDtoForPage(pageInfo,searchMap, orderMap);
		request.setAttribute("pageInfo", rp.getData());
		request.setAttribute("allStatuses",InfoClassify.allStatuss);
		request.setAttribute("infoThemes",infoThemeService.findByInfoClassifyId(icid).getData());
		if(!StringUtil.isEmpty(isAjaxPage)){
			return JOIN_INFO_MODAL_PAGE;
		}
		return JOIN_INFO_MODAL;
	}
	
	//专题加入新闻
	@ResponseBody
	@RequestMapping(value="join_info")
	public Response<Void> joinInfo(HttpServletRequest request,
			@RequestParam(value="infoClassifyIds[]") Long[] infoClassifyIds,
			@RequestParam(value="refInfoClassifyId") Long refInfoClassifyId,
			@RequestParam(value="appId") Long appId,
			@RequestParam(value="infoThemeId") Long infoThemeId
			){
		
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		return infoCorrelationService.joinInfo(infoClassifyIds, refInfoClassifyId, appId,infoThemeId,sessionUser.getNickname(),sessionUser.getUserId());
	}
}
