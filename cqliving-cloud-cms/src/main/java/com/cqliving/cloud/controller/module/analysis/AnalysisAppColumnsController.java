/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.controller.module.analysis;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.EchartsXaxisUtil;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.analysis.domain.AnalysisAppColumns;
import com.cqliving.cloud.online.analysis.domain.AnalysisNews;
import com.cqliving.cloud.online.analysis.dto.AnalysisAppColumnsDto;
import com.cqliving.cloud.online.analysis.service.AnalysisAppColumnsService;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppColumnsService;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.echarts.EchartsUtil;
import com.github.abel533.echarts.json.GsonOption;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月11日
 */
@Controller
@RequestMapping(value="statistics/app_column")
public class AnalysisAppColumnsController extends CommonController{

	@Autowired
	AnalysisAppColumnsService analysisAppColumnsService;
	@Autowired
	AppColumnsService appColumnsService; 
	@Autowired
	AppInfoService  appInfoService; 
	
	private void initData(HttpServletRequest request,Long appId){
		
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		List<AppInfoDto> apps = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
		request.setAttribute("apps",apps);
		request.setAttribute("allUserType",AnalysisNews.allUserTypes);
		request.setAttribute("dayMap",AnalysisAppColumns.getDureDay(Dates.now()));
		Long defaultAppId =  null != appId ? appId : apps.get(0).getId();
		request.setAttribute("defaultAppId",defaultAppId);
		List<AppColumns> appColumns = this.findByAppId(request,defaultAppId).getData();
		request.setAttribute("defaultAppColumnsId",CollectionUtils.isNotEmpty(appColumns) ? appColumns.get(0).getId() : null);
		request.setAttribute("defaultAppColumn",appColumns);
	}
	
	@RequestMapping(value="index")
	public String statisticsAppColumn(HttpServletRequest request,@RequestParam(value = "p", required = false) String isAjaxPage){
		
		initData(request,null);
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		if(null == searchMap || searchMap.isEmpty() || null == searchMap.get("EQ_appId") ){
			searchMap.put("EQ_appId",request.getAttribute("defaultAppId"));
		}
		PageInfo<AnalysisAppColumnsDto> pageInfo = getPageInfo(request);
		analysisAppColumnsService.findPageInfo(pageInfo, searchMap);
		request.setAttribute("pageInfo",pageInfo);
		if(!StringUtil.isEmpty(isAjaxPage)){
			return "/module/analysis/analysis_app_columns_list_page";
		}
		return "tiles.module.analysis.analysis_app_columns_list";
	}
	
	@RequestMapping(value="common/app_column_echarts")
	public String AppColumnEcharts(HttpServletRequest request,@RequestParam Long appColumnsId,Long appId){
		
		initData(request,appId);
		return "tiles.module.analysis.analysis_app_columns_echarts";
	}
	
	@RequestMapping(value="common/echarts_option")
	@ResponseBody
	public Response<String> getEchartsOption(HttpServletRequest request,String dateStr,Byte userType,Long appColumnsId){
		
		Date now = Dates.now();
		int diffDay = 7;
		try {
			diffDay = DateUtil.toDifferDay(DateUtil.parseDate(dateStr, DateUtil.FORMAT_YYYY_MM_DD), now);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Response<String> rp = Response.newInstance();
		Map<String, Object> conditions = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		conditions.put("diffDay", diffDay);
		conditions.put("GTE_statisticsDate", dateStr);
		conditions.put("EQ_userType",userType);
		conditions.put("EQ_appColumnsId", appColumnsId);
		Map<String,Boolean> sortMap = Maps.newHashMap();
		Map<String,List<Map<String,String>>> seriesData = analysisAppColumnsService.findByConditions(conditions, sortMap).getData();
		GsonOption option = EchartsUtil.getSimpleLineEcharts("", "次", EchartsXaxisUtil.getDayXaxis(now,diffDay), seriesData);
		rp.setData(option.toString());
		return rp;
	}
	
	@RequestMapping(value="common/col_appid")
	@ResponseBody
	public Response<List<AppColumns>> findByAppId(HttpServletRequest request,Long appId){
		
		Map<String,Object> conditions = Maps.newHashMap();
		Map<String,Boolean> orderMap = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_status", AppColumns.STATUS3);
		conditions.put("EQ_columnsType", AppColumns.COLUMNSTYPE0);
		orderMap.put("sortNo",true);
		return appColumnsService.queryForList(conditions, orderMap);
	}
}
