/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.InformationUtil;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.service.InfoClassifyService;
import com.cqliving.cloud.online.info.service.InfoThemeService;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.util.StringUtil;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:专题新闻详情</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月12日
 */
@RequestMapping(value="topic")
@Controller
public class SpecialInfoController extends CommonController{

	@Autowired
	InfoThemeService infoThemeService;
	@Autowired
	InfoClassifyService infoClassifyService;
	@Autowired
	AppInfoService appInfoService;
	@Autowired
	AppConfigService appConfigService;
	
	private final static int PAGE_SIZE = 10;
	
	private final static String SPECIAL_LIST = "/info/topic_list";
	private final static String THEME_PAGE = "/info/topic_list_page";
	
	@RequestMapping(value="detail/{topicId}")
	public String list(HttpServletRequest request,@PathVariable Long topicId,String share){
		
		InformationDto dto = informationService.findDetail(topicId).getData();
		
		if(null == dto || (!StringUtil.isEmpty(share) && InfoClassify.STATUS3.byteValue() !=dto.getStatus().byteValue() )){
			return CommonController.DELETE_JSP;
		}
		
		request.setAttribute("infoThemes",infoThemeService.findByInfoClassifyId(topicId).getData());
		request.setAttribute("infoDetail",dto);
        request.setAttribute("appInfo",appInfoService.get(dto.getAppId()).getData());
        //样式文件需要
        request.setAttribute("appId",dto.getAppId());
        AppConfig appConfig = appConfigService.findByAppId(dto.getAppId()).getData();
		request.setAttribute("appConfig",appConfig);
		//增加浏览量
		informationService.addViewCount(topicId,dto.getId());
		return SPECIAL_LIST;
	}
	
	@RequestMapping(value="sub")
	public String topicSubInfo(HttpServletRequest request,Long infoThemeId,String sortNoValue,
			Long appId,Long infoClassifyId,String idValue,String onlineTimeValue){
		
		request.setAttribute("appInfo",appInfoService.get(appId).getData());
		ScrollPage<InfoClassifyDto> page = new ScrollPage<InfoClassifyDto>();
		page.setPageSize(PAGE_SIZE);
		page.addScrollPageOrder(new ScrollPageOrder("corre_sort_no",ScrollPage.ASC,sortNoValue));
		page.addScrollPageOrder(new ScrollPageOrder("online_time",ScrollPage.DESC,onlineTimeValue));
		page.addScrollPageOrder(new ScrollPageOrder("id",ScrollPage.DESC,idValue));
		Map<String,Object> conditions = Maps.newHashMap();
		conditions.put("EQ_themeId", infoThemeId);
		conditions.put("EQ_infoClassifyId", infoClassifyId);
		infoClassifyService.querySpecialSubDtoForScrollPage(page, conditions);
		
		request.setAttribute("subinfo",this.setViewAndReplyCount(page.getPageResults(),appId));
		
		if(CollectionUtils.isEmpty(page.getPageResults()) || page.getPageResults().size()<PAGE_SIZE){
			request.setAttribute("noSubInfo","noSubInfo");
		}
		AppConfig appConfig = appConfigService.findByAppId(appId).getData();
		
		request.setAttribute("appConfig",appConfig);
		
		if(appId == 18){
			return THEME_PAGE+"_18";
		}
		return THEME_PAGE;
	}
	
	private List<InfoClassifyDto> setViewAndReplyCount(List<InfoClassifyDto> list,Long appId){
		
		if(CollectionUtils.isEmpty(list))return null;
		
		for(InfoClassifyDto dto : list){
			InformationDto infodto = new InformationDto();
			infodto.setId(dto.getInformationId());
			infodto.setInfoClassifyId(dto.getId());
			infodto.setViewCount(dto.getDetailViewCount());
			infodto.setReplyCount(dto.getTotalReplyCount());
			infodto.setInitCount(dto.getInitCount());
			infodto.setTopTime(dto.getTopTime());
			infodto.setOnlineTime(dto.getOnlineTime());
			infodto.setAddType(dto.getAddType());
			
			informationService.setViewAndReplyCount(infodto);
			dto.setTotalReplyCount(infodto.getReplyCount());
			dto.setDetailViewCount(infodto.getViewCount());
			if(null != appId && 1 == appId.intValue()){
				dto.setShareTitle(InformationUtil.getShareTitle(appId, dto.getTitle()));
			}
		}
		return list;
	}
	
	@RequestMapping(value="info_view_num")
	public void getViewCount(HttpServletRequest request,HttpServletResponse response,Long infoClassifyId){
		InfoClassify infoClassify = infoClassifyService.get(infoClassifyId).getData();
		Information information = informationService.get(infoClassify.getInformationId()).getData();
		InformationDto dto = new InformationDto();
		BeanUtils.copyProperties(information, dto);
		dto.setOnlineTime(infoClassify.getOnlineTime());
		informationService.setViewAndReplyCount(dto);
		
		int viewCount = dto.getViewCount();
		String viewStr = InformationUtil.handleViewCount(viewCount);
		int replyCount = dto.getReplyCount();
		try {
			String jsonR = "{\"viewCount\":"+viewStr+",\"replyCount\":"+replyCount+"}";
			response.getWriter().print(jsonR);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				response.getWriter().print(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="add_view_num")
	@ResponseBody
    protected void addViewNum(HttpServletRequest request,String sessionId,String token,Long appId,Long infoClassifyId,Long informationId){
	    //保存访问记录
	    this.saveUserViewRecode(request, appId, infoClassifyId, informationId);
    }
}
