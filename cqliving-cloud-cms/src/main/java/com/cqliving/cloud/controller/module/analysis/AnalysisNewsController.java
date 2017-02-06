/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.controller.module.analysis;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.EchartsXaxisUtil;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.analysis.domain.AnalysisNews;
import com.cqliving.cloud.online.analysis.service.AnalysisNewsService;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.service.InformationService;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.echarts.EchartsUtil;
import com.github.abel533.echarts.json.GsonOption;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月8日
 */
@Controller
@RequestMapping(value = "/module/analysis/news")
public class AnalysisNewsController extends CommonController {

	@Autowired
	AnalysisNewsService analysisNewsService;
	@Autowired
	InformationService informationService;
	
	@RequestMapping(value="statistics")
	public String statisticsNews(HttpServletRequest request,@RequestParam Long infoClassifyId){
		
		InformationDto info = informationService.findDetail(infoClassifyId).getData();
		request.setAttribute("infoDto",info);
		informationService.setViewAndReplyCount(info,false);
		request.setAttribute("allUserType",AnalysisNews.allUserTypes);
		request.setAttribute("dayMap",AnalysisNews.getDay(Dates.now(), 3));
		return "tiles.module.analysis.analysis_news";
	}
	
	@RequestMapping(value="common/echarts_option")
	@ResponseBody
	public Response<String> getEchartsOption(HttpServletRequest request,String dateStr,Byte userType){
		
		Response<String> rp = Response.newInstance();
		Map<String, Object> conditions = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		conditions.put("EQ_statisticsTime", dateStr);
		conditions.put("EQ_userType",userType);
		Map<String,Boolean> sortMap = Maps.newHashMap();
		Map<String,List<Map<String,String>>> seriesData = analysisNewsService.findByConditions(conditions, sortMap).getData();
		GsonOption option = EchartsUtil.getSimpleLineEcharts("新闻使用统计", "次", EchartsXaxisUtil.getHoursXaxis(), seriesData);
		rp.setData(option.toString());
		return rp;
	}
}
