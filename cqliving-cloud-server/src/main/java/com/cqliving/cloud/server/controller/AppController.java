/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
/**
 * 
 */
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
import com.cqliving.cloud.online.app.domain.AllMedia;
import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.cloud.online.app.service.AllMediaService;
import com.cqliving.cloud.online.app.service.AppColumnsService;
import com.cqliving.cloud.online.app.service.AppDetailVersionService;
import com.cqliving.cloud.online.app.service.AppMarketplaceResourceService;
import com.cqliving.cloud.online.app.service.AppWeatherService;
import com.cqliving.cloud.online.config.domain.ConfigHotline;
import com.cqliving.cloud.online.config.domain.HousingPublic;
import com.cqliving.cloud.online.config.domain.RecommendApp;
import com.cqliving.cloud.online.config.dto.RecommendAppDto;
import com.cqliving.cloud.online.config.service.ConfigConvenienceService;
import com.cqliving.cloud.online.config.service.ConfigHotlineService;
import com.cqliving.cloud.online.config.service.HousingPublicService;
import com.cqliving.cloud.online.config.service.RecommendAppService;
import com.cqliving.cloud.online.interfacc.dto.AllMediaData;
import com.cqliving.cloud.online.interfacc.dto.AppWeatherResult;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ConvenienceData;
import com.cqliving.cloud.online.interfacc.dto.GetColumnsData;
import com.cqliving.cloud.online.interfacc.dto.HotlineData;
import com.cqliving.cloud.online.interfacc.dto.HousingPublicData;
import com.cqliving.cloud.online.interfacc.dto.InitStartLoadingImg;
import com.cqliving.cloud.online.interfacc.dto.InitStartResult;
import com.cqliving.cloud.online.interfacc.dto.RecommendAppData;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title: App 相关接口
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年4月30日
 */
@Controller
public class AppController {
	
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);
	
	@Autowired
	private AllMediaService allMediaService;
	@Autowired
	private AppColumnsService appColumnsService;
	@Autowired
	private AppDetailVersionService appDetailVersionService;
	@Autowired
	private AppMarketplaceResourceService appMarketplaceResourceService;
	@Autowired
	private AppWeatherService appWeatherService;
	@Autowired
	private ConfigConvenienceService configConvenienceService;
	@Autowired
	private ConfigHotlineService configHotlineService;
	@Autowired
	private HousingPublicService housingPublicService;
	@Autowired
	private RecommendAppService recommendAppService;
	
	/**
	 * <p>Description: 获取便民信息</p>
	 * @author Tangtao on 2016年7月13日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"convenience"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ConvenienceData>> convenience(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token) {
		logger.debug("===================== 调用便民信息接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s", appId, sessionId, token));
		Response<CommonListResult<ConvenienceData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用便民信息接口异常：" + response);
			return response;
		}
		response = configConvenienceService.getByApp(appId);	
		logger.debug("调用便民信息接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取首页广告图</p>
	 * @author Tangtao on 2016年12月5日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getAdImg"}, method = {RequestMethod.POST})
	public Response<CommonListResult<InitStartLoadingImg>> getAdImg(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token) {
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s", appId, sessionId, token);
		logger.debug("===================== 调用获取首页广告图接口 =====================>" + parameters);
		Response<CommonListResult<InitStartLoadingImg>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取首页广告图接口异常：" + response + parameters);
			return response;
		}
		
		//查询数据
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_imageType", AppMarketplaceResource.IMAGETYPE2);
		map.put("EQ_status", AppMarketplaceResource.STATUS3);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo", true);
		sortMap.put("id", false);
		Response<List<AppMarketplaceResource>> dataResponse = appMarketplaceResourceService.queryForList(map, sortMap);
		List<AppMarketplaceResource> dtos = dataResponse.getData();
		//组装数据
		CommonListResult<InitStartLoadingImg> data = new CommonListResult<InitStartLoadingImg>();
		List<InitStartLoadingImg> dataList = Lists.newArrayList();
		InitStartLoadingImg obj;
		for (AppMarketplaceResource dto : dtos) {
			obj = new InitStartLoadingImg();
			obj.setImageUrl(dto.getImageUrl());
			obj.setLinkUrl(dto.getLinkUrl());
			dataList.add(obj);
		}
				
		//返回数据
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用获取首页广告图接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取全媒体列表</p>
	 * @author Tangtao on 2016年11月2日
	 * @param request
	 * @param appId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getAllMedia"}, method = {RequestMethod.POST})
	public Response<CommonListResult<AllMediaData>> getAllMedia(HttpServletRequest request, @RequestParam Long appId) {
		logger.debug("===================== 调用获取全媒体接口 =====================>"
				+ String.format("\n<请求参数：appId=%d", appId));
		Response<CommonListResult<AllMediaData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取全媒体接口异常：" + response);
			return response;
		}
		
		//查询数据
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_status", AllMedia.STATUS3);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo", true);
		sortMap.put("id", false);
		Response<List<AllMedia>> dataResponse = allMediaService.queryForList(map, sortMap);
		List<AllMedia> dtos = dataResponse.getData();
		//组装数据
		CommonListResult<AllMediaData> data = new CommonListResult<AllMediaData>();
		List<AllMediaData> dataList = Lists.newArrayList();
		AllMediaData obj;
		for (AllMedia dto : dtos) {
			obj = new AllMediaData();
			obj.setAppId(dto.getAppId());
			obj.setColumnsId(dto.getColumnsId());
			obj.setId(dto.getId());
			obj.setLinkUrl(dto.getLinkUrl());
			obj.setMaxImageUrl(dto.getMaxImageUrl());
			obj.setMinImageUrl(dto.getMinImageUrl());
			obj.setName(dto.getName());
			obj.setSortNo(dto.getSortNo());
			obj.setTopColumnsIndex(dto.getTopColumnsIndex());
			obj.setType(dto.getType());
			dataList.add(obj);
		}
				
		//返回数据
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用获取全媒体接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取公租房配租结果</p>
	 * @author Tangtao on 2016年11月7日
	 * @param request
	 * @param resp
	 * @param userName
	 * @param cardNumL4
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getHousingPublicResult"}, method = {RequestMethod.POST})
	public Response<CommonListResult<HousingPublicData>> getHousingPublicResult(HttpServletRequest request, HttpServletResponse resp , 
			@RequestParam String userName, 
			@RequestParam String cardNumL4) {
		resp.setHeader("Access-Control-Allow-Origin", "*");	//解决js ajax跨域问题
		logger.debug("===================== 调用获取公租房配租结果接口 =====================>"
				+ String.format("\n<请求参数：userName=%s, cardNumL4=%s", userName, cardNumL4));
		Response<CommonListResult<HousingPublicData>> response = Response.newInstance();
		//检查参数的必要性
		if (StringUtils.isBlank(userName) || cardNumL4.length() != 4) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取公租房配租结果接口异常：" + response);
			return response;
		}
		
		//查询数据
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_userName", userName);
		map.put("LLIKE_cardNum", cardNumL4);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("id", false);
		Response<List<HousingPublic>> dataResponse = housingPublicService.queryForList(map, sortMap);
		List<HousingPublic> dtos = dataResponse.getData();
		//组装数据
		CommonListResult<HousingPublicData> data = new CommonListResult<HousingPublicData>();
		List<HousingPublicData> dataList = Lists.newArrayList();
		HousingPublicData obj;
		for (HousingPublic dto : dtos) {
			obj = new HousingPublicData();
			obj.setAddress(dto.getAddress());
			obj.setApplyType(dto.getApplyType());
			obj.setCardNum(dto.getCardNum());
			obj.setGender(dto.getSex() != null && HousingPublic.allSexs.containsKey(dto.getSex()) ? HousingPublic.allSexs.get(dto.getSex()) : "未知");
			obj.setHouseType(dto.getHouseType());
			obj.setUserName(dto.getUserName());
			obj.setWorkUnit(dto.getWorkUnit());
			dataList.add(obj);
		}
		
		//返回数据
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用获取公租房配租结果接口结果：" + response);
		return response;
	}
	
	/**
	 * Title: 获取子栏目
	 * @author Tangtao on 2016年5月1日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param parentCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getColumns"}, method = {RequestMethod.POST})
	public Response<CommonListResult<GetColumnsData>> getColumns(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			@RequestParam String parentCode) {
		logger.debug("===================== 调用获取子栏目接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, parentCode=%s", appId, sessionId, token, parentCode));
		Response<CommonListResult<GetColumnsData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(parentCode)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取子栏目接口异常：" + response);
			return response;
		}
		response = appColumnsService.getChildren(appId, parentCode);	
		logger.debug("调用获取子栏目接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 区县推荐列表</p>
	 * @author Tangtao on 2016年10月26日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastSortNo
	 * @param lastAppId
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getRecommedApps"}, method = {RequestMethod.POST})
	public Response<CommonListResult<RecommendAppData>> getRecommedApps(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			Integer lastSortNo, 
			Long lastAppId,
			Long lastId) {
		logger.debug("===================== 调用获取区县推荐APP列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, lastSortNo=%d, lastAppId=%d, lastId=%d", appId, sessionId, token, lastSortNo, lastAppId, lastId));
		Response<CommonListResult<RecommendAppData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取区县推荐APP列表接口异常：" + response);
			return response;
		}
		
		//查询数据
		ScrollPage<RecommendAppDto> scrollPage = new ScrollPage<RecommendAppDto>();
		scrollPage.addScrollPageOrder(new ScrollPageOrder("sort_no", ScrollPage.ASC, lastSortNo));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("recommend_app_id", ScrollPage.ASC, lastAppId));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage.setPageSize(10);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_status", RecommendApp.STATUS3);
		Response<ScrollPage<RecommendAppDto>> pageResponse = recommendAppService.queryForScrollPage(scrollPage, conditions);
		List<RecommendAppDto> dtos = pageResponse.getData().getPageResults();
		//组装数据
		CommonListResult<RecommendAppData> data = new CommonListResult<RecommendAppData>();
		List<RecommendAppData> dataList = Lists.newArrayList();
		RecommendAppData obj;
		for (RecommendAppDto dto : dtos) {
			obj = new RecommendAppData();
			obj.setAppIcon(dto.getAppIcon());
			obj.setAppId(dto.getRecommendAppId());
			obj.setAppName(dto.getRecommendAppName());
			obj.setColumnsId(dto.getColumnsId());
			obj.setDownloadUrl(dto.getDownloadUrl());
			obj.setId(dto.getId());
			obj.setIsCarousel(RecommendApp.IS_CAROUSEL_1.equals(dto.getIsCarousel()) ? true : false);				
			obj.setSortNo(dto.getSortNo());
			dataList.add(obj);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用获取区县推荐APP列表接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 获取热线列表</p>
	 * @author Tangtao on 2016年7月8日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param hotlineTypeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"hotline"}, method = {RequestMethod.POST})
	public Response<CommonListResult<HotlineData>> hotline(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			Long hotlineTypeId) {
		logger.debug("===================== 调用热线列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, hotlineTypeId=%d", appId, sessionId, token, hotlineTypeId));
		Response<CommonListResult<HotlineData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用热线列表接口异常：" + response);
			return response;
		}
		
		//查询数据
		Map<String, Object> map = Maps.newHashMap(); 
		map.put("EQ_appId", appId);
		map.put("EQ_hotlineTypeId", hotlineTypeId);
		map.put("EQ_status", ConfigHotline.STATUS3);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo", true);
		Response<List<ConfigHotline>> chResponse = configHotlineService.queryForList(map, sortMap);
		List<ConfigHotline> hotlines = Lists.newArrayList();
		if (chResponse.getCode() == ErrorCodes.SUCCESS) {
			hotlines = chResponse.getData();
		}
		
		//返回数据
		CommonListResult<HotlineData> result = new CommonListResult<HotlineData>();
		List<HotlineData> dataList = Lists.newArrayList();
		HotlineData data;
		for (ConfigHotline obj : hotlines) {
			data = new HotlineData();
			data.setName(obj.getName());
			data.setPhone(obj.getPhone());
			dataList.add(data);
		}
		result.setDataList(dataList);
		response.setData(result);
		logger.debug("调用热线列表接口结果：" + response);
		return response;
	}

	/**
	 * Title: 客户端初始化
	 * @author Tangtao on 2016年4月30日
	 * @param request
	 * @param appId 
	 * @param sessionId 
	 * @param token
	 * @param appVersion app 版本号
	 * @param loadingImgVersion 首页加载图版本号
	 * @param columnsVersion 栏目版本号
	 * @param type 客户端类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"initStart"}, method = {RequestMethod.POST})
	public Response<InitStartResult> initStart(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			@RequestParam Integer appVersion, 
			@RequestParam Integer loadingImgVersion, 
			@RequestParam Integer columnsVersion, 
			@RequestParam Integer type) {
		String typeStr = type.intValue() == 1 ? "Android" : type.intValue() == 2 ? "iOS" : "未知设备类型";
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, appVersion=%s, loadingImgVersion=%s, columnsVersion=%s, type=%d[%s]", 
				appId, sessionId, token, appVersion, loadingImgVersion, columnsVersion, type, typeStr);
		logger.debug("===================== 调用客户端初始化接口 =====================>" + parameters);
		Response<InitStartResult> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || appVersion == null || loadingImgVersion == null || columnsVersion == null || type == null || type < 1 || type > 2) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用客户端初始化接口异常：" + response + parameters);
			return response;
		}
		response = appDetailVersionService.getInitStartInfo(appId, sessionId, token, appVersion, loadingImgVersion, columnsVersion, type);
		logger.debug("调用客户端初始化接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取天气预报</p>
	 * @author Tangtao on 2016年5月26日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"weather"}, method = {RequestMethod.POST})
	public Response<AppWeatherResult> weather(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token) {
		logger.debug("===================== 调用天气预报接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s", appId, sessionId, token));
		Response<AppWeatherResult> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用天气预报接口异常：" + response);
			return response;
		}
		Response<AppWeather> resp = appWeatherService.getAppWeatherByAppId(appId);
		if (resp.getCode() >= 0 && resp.getData() != null) {
			AppWeather weather = resp.getData();
			AppWeatherResult data = new AppWeatherResult();
			data.setCityName(weather.getCityName());
			data.setConditionIconUrl(weather.getConditionIconUrl());
			data.setTmpMax(weather.getTmpMax());
			data.setTmpMin(weather.getTmpMin());
			data.setTmpNow(weather.getTmpNow());
			response.setData(data);
		} else {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("调用天气预报接口失败");
		}
		logger.debug("调用天气预报接口结果：" + response);
		return response;
	}
	
//	private String appendSuffixBySize(int w, int h, String filePath) {
//		// 后缀
//		String suffix = FileUtils.getExtensions(filePath);
//		String subFileName = filePath.substring(0, filePath.lastIndexOf("." + suffix));
//		StringBuilder newFileName = new StringBuilder(subFileName);
//		newFileName.append("_").append(w).append("x").append(h).append(".").append(suffix);
//		return filePath.replace(filePath, newFileName);
//	}

}