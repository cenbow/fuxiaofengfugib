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

import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.account.service.UserInfoService;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.cloud.online.shoot.domain.ShootImages;
import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.cloud.online.shoot.dto.ShootInfoDto;
import com.cqliving.cloud.online.shoot.service.ShootImagesService;
import com.cqliving.cloud.online.shoot.service.ShootInfoService;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.file.BeanUtil;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月20日
 */
@Controller
@RequestMapping({"shoot"})
public class ShootController {
	
	/** 每页记录数 */
	private static final int PAGE_SIZE = 10;
	
	private static final String VIEW_DETAIL_SHARE = "/shoot/shoot_detail_share";
	private static final String VIEW_COMMENT_DATA = "/shoot/shoot_comment_data";
	
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private ShootImagesService shootImagesService;
	@Autowired
	private ShootInfoService shootInfoService;
	@Autowired
	private UserInfoReplyService userInfoReplyService;
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * <p>Description: 随手拍分享详情</p>
	 * @author Tangtao on 2016年6月23日
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping({"detail/{id}"})
	public String detail(HttpServletRequest request, Model model, @PathVariable Long id) {
		//查询随手拍
		Response<ShootInfo> shootInfoResponse = shootInfoService.get(id);
		if (shootInfoResponse.getCode() != 0 
				|| shootInfoResponse.getData() == null 
				|| ShootInfo.STATUS88.equals(shootInfoResponse.getData().getStatus()) 
				|| ShootInfo.STATUS99.equals(shootInfoResponse.getData().getStatus())) {
			return CommonController.DELETE_JSP;
		}
		if (ShootInfo.STATUS2.equals(shootInfoResponse.getData().getStatus())) {
			model.addAttribute("errorMsg", "对不起，该条随手拍正在审核中");
			return CommonController.DELETE_JSP;
		}
		ShootInfo shootInfo = shootInfoResponse.getData();
		ShootInfoDto shootInfoDto = new ShootInfoDto();
		BeanUtil.copyProperty(shootInfoDto, shootInfo);
		shootInfoDto.setCreateTimeStr(DateUtil.convertTimeToFormatHore1(shootInfoDto.getCreateTime().getTime()));
		model.addAttribute("shootInfo", shootInfoDto);
		
		//查询APP信息
		Response<AppInfo> appInfoResponse = appInfoService.get(shootInfo.getAppId());
		if (appInfoResponse.getCode() != 0 || appInfoResponse.getData() == null) {
			return CommonController.DELETE_JSP;
		}
		AppInfo appinfo = appInfoResponse.getData();
		model.addAttribute("appInfo", appinfo);
		model.addAttribute("openUrl", appConfigService.findByAppId(appinfo.getId()).getData().getDownLoadUrl());
		
		//查询用户信息
		Response<UserInfo> userInfoResponse = userInfoService.get(shootInfo.getUserId());
		if (userInfoResponse.getCode() != 0 || userInfoResponse.getData() == null) {
			return CommonController.DELETE_JSP;
		}
		UserInfo userInfo = userInfoResponse.getData();
		model.addAttribute("userInfo", userInfo);
		
		//查询随手拍图片列表
		Response<List<ShootImages>> shootImagesResponse = shootImagesService.getByShootId(shootInfo.getId());
		if (shootImagesResponse.getCode() == 0 && CollectionUtils.isNotEmpty(shootImagesResponse.getData())) {
			List<ShootImages> shootImages = shootImagesResponse.getData();
			model.addAttribute("firstImage", shootImages.get(0).getImageUrl());
			for (ShootImages img : shootImages) {
				//获取原图
				img.setImageUrl(img.getImageUrl().replaceAll("_\\d{3}x\\d{3}\\.", "."));
			}
			model.addAttribute("shootImages", shootImages);
		}
		return VIEW_DETAIL_SHARE;
	}
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年6月23日
	 * @param request
	 * @param model
	 * @param shootId
	 * @param lastReplyId
	 * @return
	 */
	@RequestMapping({"loadCommentData"})
	public String loadCommentData(HttpServletRequest request, Model model, 
			@RequestParam("i") Long shootId, 
			@RequestParam(required = false, value = "l") Long lastReplyId) {
		ScrollPage<UserInfoReplyDto> scrollPage = new ScrollPage<UserInfoReplyDto>();
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastReplyId));
		scrollPage.setPageSize(PAGE_SIZE);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_status", UserInfoReply.STATUS3);
		conditions.put("EQ_sourceType", UserInfoReply.SOURCETYPE4);
		conditions.put("EQ_sourceId", shootId);
		Response<ScrollPage<UserInfoReplyDto>> response = userInfoReplyService.queryScrollPage(scrollPage, conditions, UserInfoReply.SOURCETYPE4);
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