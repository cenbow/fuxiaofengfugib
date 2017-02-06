/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.cloud.online.account.domain.UserSurveyQuestionAnswer;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.account.dto.UserSurveyHistoryDto;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.account.service.UserPraiseService;
import com.cqliving.cloud.online.account.service.UserSurveyQuestionAnswerService;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.service.InfoClassifyService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.http.ClientRemoteRealAdress;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月25日
 */
@Controller
@RequestMapping(value="info")
public class InformationController extends CommonController{

	@Autowired
	InfoClassifyService infoClassifyService;
	@Autowired
	UserInfoReplyService userInfoReplyService; 
	@Autowired
	AppInfoService appInfoService;
	@Autowired
	UserSurveyQuestionAnswerService userSurveyQuestionAnswerService;
	@Autowired
	UserPraiseService userPraiseService;
	@Autowired
	AppConfigService appConfigService;
	
	private final static String INFO_DETAIL_JSP = "/info/info_detail";
	private final static int PAGE_SIZE = 15;
	
	//新闻详情
	@RequestMapping(value="detail/{id}")
	public String infoDetail(HttpServletRequest request,PageInfo<InfoClassifyDto> pageInfo,
			@PathVariable(value="id") Long infoClassifyId,String noimg,String share){
		
		InformationDto informationDto = informationService.findDetail(infoClassifyId).getData();
		
		if(null == informationDto || (!StringUtil.isEmpty(share) && InfoClassify.STATUS3.byteValue() !=informationDto.getStatus().byteValue() )){
			return CommonController.DELETE_JSP;
		}
		informationService.setViewAndReplyCount(informationDto);
		request.setAttribute("appInfo", appInfoService.get(informationDto.getAppId()).getData());
		//样式文件需要
		request.setAttribute("appId",informationDto.getAppId());
		//原创的话要请求投票的数据
		if(Information.CONTEXTTYPE2.byteValue() == informationDto.getContextType().byteValue()){
			request.setAttribute("answerNum",informationService.findAnswerNumByInfoId(informationDto.getId()).getData());
		}
		request.setAttribute("infoDetail",informationDto);
		//查询已相关的新闻
		Map<String,Boolean> orderMap = Maps.newHashMap();
		orderMap.put("sortNo",true);
		Map<String,Object> searchMap = Maps.newHashMap();
		searchMap.put("EQ_infoClassifyId",infoClassifyId);
		infoClassifyService.queryHadCorrInfoClassifyPage(pageInfo, searchMap, orderMap);
		request.setAttribute("infoClassifyCorreDto",pageInfo.getPageResults());
		request.setAttribute("appConfig",appConfigService.findByAppId(informationDto.getAppId()).getData());
		
		//增加浏览量
	    informationService.addViewCount(infoClassifyId,informationDto.getId());
		
		return INFO_DETAIL_JSP;
	}
	
	//新闻评论
	@RequestMapping(value="comment")
	public String getInfoComment(HttpServletRequest request,String sortValue,Long informationId){
		
		ScrollPage<UserInfoReplyDto> scrollPage = new ScrollPage<UserInfoReplyDto>();
		
		scrollPage.setPageSize(PAGE_SIZE);
		List<ScrollPageOrder> scrollPageOrder = scrollPage.getListOrderColumn();
		scrollPageOrder.add(new ScrollPageOrder("id",ScrollPage.DESC,sortValue));
		Map<String,Object> conditions = Maps.newHashMap();
		conditions.put("EQ_sourceId",informationId);
		conditions.put("EQ_status",UserInfoReply.STATUS3);
		conditions.put("EQ_sourceType",UserInfoReply.SOURCETYPE1);
		userInfoReplyService.queryScrollPage(scrollPage, conditions, UserInfoReply.SOURCETYPE1);
		request.setAttribute("infocomments",scrollPage.getPageResults());
		
		return "/info/info_comment_page";
	}
	
	@ResponseBody
	@RequestMapping(value="save_survey_answer")
	public Response<Long[]> saveUserSurveyQuestionAnswer(HttpServletRequest request,UserSurveyHistoryDto userSurveyHistoryDto,
			UserSurveyQuestionAnswer userSurveyQuestionAnswer,@RequestParam(value="answerIds[]")Long[] answerIds){
		
		userSurveyHistoryDto.setIp(ClientRemoteRealAdress.getRemoteAddrIp(request));
		return userSurveyQuestionAnswerService.saveUserSurveyQuestionAnswer(userSurveyHistoryDto, userSurveyQuestionAnswer,answerIds);
	}
	
	//点赞
	@ResponseBody
	@RequestMapping(value="save_user_praise")
	public Response<UserPraise> saveUserPraise(HttpServletRequest request,UserPraise userPraise){
		
		return userPraiseService.saveUserPraise(userPraise);
	}
	
	@RequestMapping(value="add_view_num")
	@ResponseBody
    protected void addViewNum(HttpServletRequest request,String sessionId,String token,@RequestParam Long appId,@RequestParam Long infoClassifyId,@RequestParam Long informationId){
	    //保存访问记录
	    this.saveUserViewRecode(request, appId, infoClassifyId, informationId);
    }
}
