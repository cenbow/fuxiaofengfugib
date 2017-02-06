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

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.ClientControlType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.service.CommentAppConfigService;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.TourismInfoData;
import com.cqliving.cloud.online.interfacc.dto.TourismSpecialData;
import com.cqliving.cloud.online.tourism.domain.TourismImage;
import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.cloud.online.tourism.service.TourismImageService;
import com.cqliving.cloud.online.tourism.service.TourismInfoService;
import com.cqliving.cloud.online.tourism.service.TourismSpecialService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title: 旅游相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年8月25日
 */
@Controller
public class TourismController {
	
	private static final Logger logger = LoggerFactory.getLogger(TourismController.class);
	
	@Autowired
	private CommentAppConfigService commentAppConfigService;
	@Autowired
	private ConfigRegionService configRegionService;
	@Autowired
	private TourismImageService tourismImageService;
	@Autowired
	private TourismInfoService tourismInfoService;
	@Autowired
	private TourismSpecialService tourismSpecialService;
	
	/**
	 * <p>Description: 旅游图片列表</p>
	 * @author Tangtao on 2016年8月25日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param tourismId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"tourismImageList"}, method = {RequestMethod.POST})
	public Response<CommonListResult<String>> tourismImageList(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token,
			@RequestParam Long tourismId) {
		logger.debug("===================== 调用旅游图片列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, tourismId=%d", appId, sessionId, token, tourismId));
		Response<CommonListResult<String>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || tourismId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用旅游图片列表接口异常：" + response);
			return response;
		}

		//查询数据
		PageInfo<TourismImage> pageInfo = new PageInfo<TourismImage>();
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_tourismId", tourismId);
		map.put("EQ_status", TourismImage.STATUS3);
		Map<String, Boolean> orderMap = Maps.newLinkedHashMap();
		orderMap.put("sortNo", true);
		orderMap.put("id", false);
		Response<PageInfo<TourismImage>> pageResponse = tourismImageService.queryForPage(pageInfo, map, orderMap);
		List<TourismImage> images = pageResponse.getData().getPageResults();
		
		//组装数据
		CommonListResult<String> data = new CommonListResult<String>();
		List<String> dataList = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(images)) {
			for (TourismImage obj : images) {
				dataList.add(obj.getUrl());
			}
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用旅游图片列表接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 旅游列表</p>
	 * @author Tangtao on 2016年8月25日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param order 排序规则{i: 智能排序, d或空: 离我最近}
	 * @param token
	 * @param lat
	 * @param lng
	 * @param regionCode
	 * @param type
	 * @param tourismName
	 * @param lastBusinessValue 距离
	 * @param lastSortNo
	 * @param lastUpdateTime
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"tourismInfoList"}, method = {RequestMethod.POST})
	public Response<CommonListResult<TourismInfoData>> tourismInfoList(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			String order, 
			@RequestParam String lat, 
			@RequestParam String lng, 
			String regionCode, 
			Byte type, 
			String tourismName, 
			Long lastBusinessValue, 
			Integer lastSortNo, 
			String lastUpdateTime,
			Long lastId) {
		String typeStr = (type != null && TourismInfo.allTypes.containsKey(type)) ? TourismInfo.allTypes.get(type) : "全部";
		logger.debug("===================== 调用旅游列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, order=%s, lat=%s, lng=%s, regionCode=%s, type=%d[%s], tourismName=%s, lastBusinessValue=%d, lastSortNo=%d, lastUpdateTime=%s, lastId=%d", 
						appId, sessionId, token, order, lat, lng, regionCode, type, typeStr, tourismName, lastBusinessValue, lastSortNo, lastUpdateTime, lastId));
		Response<CommonListResult<TourismInfoData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(lat) || StringUtils.isBlank(lng) || !NumberUtils.isNumber(lat) || !NumberUtils.isNumber(lng)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用旅游列表接口异常：" + response);
			return response;
		}

		//查询数据
		ScrollPage<TourismInfoDto> scrollPage = new ScrollPage<TourismInfoDto>();
		if ("i".equals(order)) {		//智能排序
			scrollPage.addScrollPageOrder(new ScrollPageOrder("sort_no", ScrollPage.ASC, lastSortNo));
			scrollPage.addScrollPageOrder(new ScrollPageOrder("update_time", ScrollPage.DESC, lastUpdateTime));
			scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		} else {	//离我最近
			scrollPage.addScrollPageOrder(new ScrollPageOrder("distance", ScrollPage.ASC, lastBusinessValue));
			scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		}
		scrollPage.setPageSize(10);
		Response<ScrollPage<TourismInfoDto>> pageResponse = tourismInfoService.queryForScrollPage(scrollPage, Double.parseDouble(lat), Double.parseDouble(lng), appId, regionCode, type, tourismName);
		List<TourismInfoDto> dtos = pageResponse.getData().getPageResults();
		
		//组装数据
		CommonListResult<TourismInfoData> data = new CommonListResult<TourismInfoData>();
		List<TourismInfoData> dataList = Lists.newArrayList();
		transfer(appId, dtos, dataList, Double.parseDouble(lat), Double.parseDouble(lng));
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用旅游列表接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 旅游专题详情</p>
	 * @author Tangtao on 2016年8月25日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lat
	 * @param lng
	 * @param tourismId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"tourismInfoSubList"}, method = {RequestMethod.POST})
	public Response<TourismSpecialData> tourismInfoSubList(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			@RequestParam String lat, 
			@RequestParam String lng, 
			@RequestParam Long tourismId) {
		logger.debug("===================== 调用旅游专题详情接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, lat=%s, lng=%s, tourismId=%d", appId, sessionId, token, lat, lng, tourismId));
		Response<TourismSpecialData> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || tourismId == null || !NumberUtils.isNumber(lat) || !NumberUtils.isNumber(lng)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用旅游专题详情接口异常：" + response);
			return response;
		}
		
		//查询专题
		Response<TourismInfo> dataResponse = tourismInfoService.get(tourismId);
		if (dataResponse.getCode() < 0 || dataResponse.getData() == null || !TourismInfo.STATUS3.equals(dataResponse.getData().getStatus())) {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("专题不存在");
			logger.error("调用旅游专题详情接口异常：" + response);
			return response;
		}
		TourismInfo special = dataResponse.getData();
		if (!TourismInfo.TYPE2.equals(special.getType())) {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("该景点不是专题");
			logger.error("调用旅游专题详情接口异常：" + response);
			return response;
		}
		String description = special.getDescription();
	
		//查询子列表数据
		Response<List<TourismInfoDto>> pageResponse = tourismSpecialService.queryForSubList(appId, tourismId, Double.parseDouble(lat), Double.parseDouble(lng));
		List<TourismInfoDto> dtos = pageResponse.getData();
		
		//组装数据
		TourismSpecialData data = new TourismSpecialData();
		List<TourismInfoData> dataList = Lists.newArrayList();
		transfer(appId, dtos, dataList, Double.parseDouble(lat), Double.parseDouble(lng));
		data.setDataList(dataList);
		data.setDescription(StringUtils.defaultString(description));
		response.setData(data);
		logger.debug("调用旅游专题详情接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 旅游区域</p>
	 * @author Tangtao on 2016年8月25日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"tourismRegion"}, method = {RequestMethod.POST})
	public Response<CommonListResult<Map<String, String>>> tourismRegion(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token) {
		logger.debug("===================== 调用旅游区域列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s", appId, sessionId, token));
		Response<CommonListResult<Map<String, String>>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用旅游区域列表接口异常：" + response);
			return response;
		}
		
		//查询数据
		Response<List<ConfigRegion>> regionResponse = configRegionService.getByAppAndType(new Long[]{appId}, BusinessType.SOURCE_TYPE_10);
		List<ConfigRegion> list = regionResponse.getData();
		//组装数据
		CommonListResult<Map<String, String>> data = new CommonListResult<Map<String, String>>();
		List<Map<String, String>> dataList = Lists.newArrayList();
		Map<String, String> map;
		for (ConfigRegion obj : list) {
			map = Maps.newHashMap();
			map.put(obj.getCode(), obj.getName());
			dataList.add(map);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用旅游区域列表接口：" + response);
		return response;
	}

	private void transfer(Long appId, List<TourismInfoDto> dtos, List<TourismInfoData> dataList, double lat, double lng) {
		TourismInfoData obj;
		//获取是否允许评论配置
		Byte canComment = commentAppConfigService.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_10).getData();
		//格式化工具，保留两位小数
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);	//保留两位小数
		format.setGroupingUsed(false);	//不使用千分符
		String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
		if (CollectionUtils.isNotEmpty(dtos)) {
			for (TourismInfoDto dto : dtos) {
				obj = new TourismInfoData();
				obj.setCommentType(NumberUtils.toByte(CommentConfig.VALUE0.equals(canComment) ? "1" : "0"));
				obj.setDetailViewType(TourismInfo.ISLINK1.equals(dto.getIsLink()) ? ClientControlType.DETAIL_VIEW_TYPE_3 : TourismInfo.TYPE2.equals(dto.getType()) ? ClientControlType.DETAIL_VIEW_TYPE_11 : ClientControlType.DETAIL_VIEW_TYPE_10);	
				obj.setDistance(dto.getDistance());
				if (dto.getDistance() != null) {
					double distanceKm = dto.getDistance().doubleValue() / 1000;
					obj.setDistanceFormatted(format.format(distanceKm) + "km");
				} else {
					obj.setDistanceFormatted("");
				}
				obj.setId(dto.getId());
				obj.setImageUrl(dto.getImageUrl());
				obj.setLinkUrl(dto.getLinkUrl());
				obj.setLat(dto.getLat());
				obj.setLng(dto.getLng());
				obj.setName(dto.getName());
				obj.setPlace(dto.getPlace());
				obj.setPrice(dto.getPrice());
				if (TourismInfo.ISLINK1.equals(dto.getIsLink())) {
					obj.setShareUrl(dto.getLinkUrl());	
				} else {
					if (TourismInfo.TYPE2.equals(dto.getType())) {
						obj.setShareUrl(webPath + "/tourism/special_detail/" + dto.getId() + ".html?lat=" + lat + "&lng=" + lng);	
					} else {
						obj.setShareUrl(webPath + "/tourism/tourism_detail/" + dto.getId() + ".html?lat=" + lat + "&lng=" + lng);	
					}
				}
				obj.setSortNo(dto.getSortNo());
				obj.setSourceType(BusinessType.SOURCE_TYPE_10);
				obj.setSynopsis(dto.getSynopsis());
				obj.setType(dto.getType());
				String typeName = (dto.getType() != null && TourismInfo.allTypes.containsKey(dto.getType())) ? TourismInfo.allTypes.get(dto.getType()) : "未知";
				obj.setTypeName(typeName);
				if (TourismInfo.TYPE2.equals(dto.getType())) {
					obj.setUrl(webPath + "/tourism/special_detail/" + dto.getId() + ".html");	
				} else {
					obj.setUrl(webPath + "/tourism/tourism_des_way/" + dto.getId() + ".html");	
				}
				obj.setUpdateTime(DateUtil.toString(dto.getUpdateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
				dataList.add(obj);
			}
		}
	}
	
}