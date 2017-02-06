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

import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.cloud.online.tourism.domain.TourismImage;
import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.tourism.domain.TourismSpecial;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.cloud.online.tourism.service.TourismImageService;
import com.cqliving.cloud.online.tourism.service.TourismInfoService;
import com.cqliving.cloud.online.tourism.service.TourismSpecialService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.util.StringUtil;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年8月26日
 */
@Controller
@RequestMapping(value="tourism")
public class TourismController extends CommonController{

	@Autowired
	TourismInfoService tourismInfoService;
	@Autowired
	TourismImageService tourismImageService;
	@Autowired
	TourismSpecialService tourismSpecialService;
	@Autowired
	AppConfigService appConfigService;
	@Autowired
	AppInfoService appInfoService;
	
	private final static String SPECIAL_DETAIL = "/tourism/special_detail";
	private final static String SPECIAL_SUB = "/tourism/special_sub";
	private final static String TOURISM_DETAIL = "/tourism/tourism_detail";
	private final static String TOURISM_WAY_DES = "/tourism/tourism_des_way";
	
	@RequestMapping(value="special_detail/{id}")
	public String specialDetail(HttpServletRequest request,@PathVariable Long id,String view){
		
		TourismInfo tourismInfo = tourismInfoService.get(id).getData();
		if(null == tourismInfo || (StringUtil.isEmpty(view) && tourismInfo.getStatus().byteValue() != TourismInfo.STATUS3.byteValue()))
			return CommonController.DELETE_JSP;
		
		List<TourismImage> imgList=tourismImageService.findByTourismId(id).getData();
		request.setAttribute("tourismInfo",tourismInfo);
		request.setAttribute("imgList",imgList);
		
		AppConfig appConfig = appConfigService.findByAppId(tourismInfo.getAppId()).getData();
        request.setAttribute("appConfig",appConfig);	
        AppInfo appInfo = appInfoService.get(tourismInfo.getAppId()).getData();
        request.setAttribute("appInfo",appInfo);
		return SPECIAL_DETAIL;
	}
	
	@RequestMapping(value="special_sub/{id}")
	public String specialSub(HttpServletRequest request,@PathVariable Long id,Double lng,Double lat){
		
		PageInfo<TourismInfoDto> pageInfo = new PageInfo<TourismInfoDto>();
		pageInfo.setCountOfCurrentPage(100);
		Map<String,Object> searchMap = Maps.newHashMap();
		Map<String,Boolean> orderMap = Maps.newLinkedHashMap();
		searchMap.put("EQ_tourismId",id);//专题ID
		searchMap.put("lng", lng);
		searchMap.put("lat", lat);
		searchMap.put("EQ_status",TourismSpecial.STATUS3);
		orderMap.put("tourism_special_sort_no",true);
		orderMap.put("id",true);
		tourismSpecialService.queryForSpecialSub(pageInfo,searchMap, orderMap);
		request.setAttribute("sumTourism",pageInfo.getPageResults());
		
		return SPECIAL_SUB;
	}
	
	@RequestMapping(value="tourism_detail/{id}")
	public String tourismDetail(HttpServletRequest request,@PathVariable Long id,String view){
		
		String str = this.specialDetail(request, id,view);
		if(str.equals(CommonController.DELETE_JSP))return str;
		
		return TOURISM_DETAIL;
	}
	
	@RequestMapping(value="tourism_des_way/{id}")
	public String tourismDesWay(HttpServletRequest request,@PathVariable Long id){
		
		TourismInfo tourismInfo = tourismInfoService.get(id).getData();
		request.setAttribute("tourismInfo",tourismInfo);
		AppInfo appInfo = appInfoService.get(tourismInfo.getAppId()).getData();
        request.setAttribute("appInfo",appInfo);
		return TOURISM_WAY_DES;
		
	}
}
