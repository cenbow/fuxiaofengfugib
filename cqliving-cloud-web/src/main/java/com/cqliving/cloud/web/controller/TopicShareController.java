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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.ConfigCommonType;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.cloud.online.config.service.ConfigCommonTypeService;
import com.cqliving.cloud.online.topic.domain.TopicImage;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.cloud.online.topic.service.TopicImageService;
import com.cqliving.cloud.online.topic.service.TopicInfoService;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年8月15日
 */
@Controller
public class TopicShareController extends CommonController{

	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private ConfigCommonTypeService configCommonTypeService;
	@Autowired
	private TopicImageService topicImageService;
	@Autowired
	private TopicInfoService topicInfoService;
	@Autowired
	UserInfoReplyService userInfoReplyService; 
	@Autowired
	AppInfoService appInfoService;
	
	private final static int PAGE_SIZE = 15;
	
	//话题分享
	@RequestMapping(value="topic_share")
	public String shareJsp(HttpServletRequest request,@RequestParam Long id){

		//话题
		Response<TopicInfo> topicInfoResponse = topicInfoService.get(id);
		TopicInfo topicInfo = topicInfoResponse.getData();
		if(null == topicInfo || topicInfo.getStatus().byteValue() != TopicInfo.STATUS3.byteValue())return CommonController.DELETE_JSP;
		request.setAttribute("topicInfo",topicInfo);
		//话题图片
		Response<List<TopicImage>> topicImageResponse = topicImageService.getByTopicInfoId(id);
        request.setAttribute("topicImages",topicImageResponse.getData());
		//话题分类
		String types = StringUtils.strip(topicInfo.getTypes(), ",");
		if (StringUtils.isNotBlank(types)) {
			List<Long> ids = Lists.newArrayList();
			for (String idStr : types.split(",")) {
				ids.add(Long.valueOf(idStr));
			}
			Response<List<ConfigCommonType>> typeResponse = configCommonTypeService.getByIds(ids);
			request.setAttribute("topicTypes",typeResponse.getData());
		}
		request.setAttribute("appId",topicInfo.getAppId());
		request.setAttribute("appConfig",appConfigService.findByAppId(topicInfo.getAppId()).getData());
		request.setAttribute("appInfo",appInfoService.get(topicInfo.getAppId()).getData());
		return "/topic/topic_share";
	}
	
	@RequestMapping(value="topic_comment/{topicId}")
	public String getInfoComment(HttpServletRequest request,@PathVariable Long topicId){
		
		ScrollPage<UserInfoReplyDto> scrollPage = new ScrollPage<UserInfoReplyDto>();
		
		scrollPage.setPageSize(PAGE_SIZE);
		ScrollPageOrder scrollPageOrder = scrollPage.getOrderUniqueColumn();
		scrollPageOrder.setColumnName("id");
		scrollPageOrder.setOrderByType(ScrollPage.DESC);
		Map<String,Object> conditions = Maps.newHashMap();
		conditions.put("EQ_sourceId",topicId);
		conditions.put("EQ_status",UserInfoReply.STATUS3);
		conditions.put("EQ_sourceType",UserInfoReply.SOURCETYPE7);
		userInfoReplyService.queryForTopicCommentsPage(scrollPage, conditions);
		request.setAttribute("infocomments",scrollPage.getPageResults());
		
		return "/info/info_comment_page";
	}
}
