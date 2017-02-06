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

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.cloud.online.joke.domain.JokeInfo;
import com.cqliving.cloud.online.joke.service.JokeInfoService;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月20日
 */
@Controller
@RequestMapping({"joke"})
public class JokeController {
	
	/** 每页记录数 */
	private static final int PAGE_SIZE = 10;
	
	private static final String VIEW_DETAIL_SHARE = "/joke/joke_detail_share";
	private static final String VIEW_COMMENT_DATA = "/joke/joke_comment_data";
	
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private JokeInfoService jokeInfoService;
	@Autowired
	private UserInfoReplyService userInfoReplyService;
	
	/**
	 * <p>Description: 段子分享详情</p>
	 * @author Tangtao on 2016年7月20日
	 * @param request
	 * @param model
	 * @param id
	 * @param openUrl
	 * @return
	 */
	@RequestMapping({"detail/{id}"})
    public String detail(HttpServletRequest request, Model model, @PathVariable Long id) {
		//查询段子
		Response<JokeInfo> jokeInfoResponse = jokeInfoService.get(id);
		if (jokeInfoResponse.getCode() != 0 || jokeInfoResponse.getData() == null || !JokeInfo.STATUS3.equals(jokeInfoResponse.getData().getStatus())) {
			return CommonController.DELETE_JSP;
		}
		JokeInfo jokeInfo = jokeInfoResponse.getData();
		model.addAttribute("jokeInfo", jokeInfo);
		
		//查询APP信息
		Response<AppInfo> appInfoResponse = appInfoService.get(jokeInfo.getAppId());
		if (appInfoResponse.getCode() != 0 || appInfoResponse.getData() == null) {
			return CommonController.DELETE_JSP;
		}
		AppInfo appinfo = appInfoResponse.getData();
		model.addAttribute("appInfo", appinfo);
		model.addAttribute("openUrl", appConfigService.findByAppId(appinfo.getId()).getData().getDownLoadUrl());
		return VIEW_DETAIL_SHARE;
	}
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月20日
	 * @param request
	 * @param model
	 * @param jokeInfoId
	 * @param lastReplyId
	 * @return
	 */
	@RequestMapping({"loadCommentData"})
	public String loadCommentData(HttpServletRequest request, Model model, 
			@RequestParam("i") Long jokeInfoId, 
			@RequestParam(required = false, value = "l") Long lastReplyId) {
		ScrollPage<UserInfoReplyDto> scrollPage = new ScrollPage<UserInfoReplyDto>();
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastReplyId));
		scrollPage.setPageSize(PAGE_SIZE);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_status", UserInfoReply.STATUS3);
		conditions.put("EQ_sourceType", UserInfoReply.SOURCETYPE5);
		conditions.put("EQ_sourceId", jokeInfoId);
		Response<ScrollPage<UserInfoReplyDto>> response = userInfoReplyService.queryScrollPage(scrollPage, conditions, UserInfoReply.SOURCETYPE5);
		if (response.getCode() == 0 && CollectionUtils.isNotEmpty(response.getData().getPageResults())) {
			List<UserInfoReplyDto> list = response.getData().getPageResults();
			for (UserInfoReplyDto dto : list) {
				boolean isAnonymous = UserInfoReply.TYPE1.equals(dto.getType());
				dto.setCreateTimeStr(DateUtil.convertTimeToFormatHore1(dto.getCreateTime().getTime()));
				dto.setImgUrl(isAnonymous ? "" : dto.getImgUrl());
			}
			model.addAttribute("dataList", response.getData().getPageResults());
		}
		return VIEW_COMMENT_DATA;
	}
	
}