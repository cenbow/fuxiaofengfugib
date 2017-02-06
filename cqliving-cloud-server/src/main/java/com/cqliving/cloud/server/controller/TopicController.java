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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
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
import com.cqliving.cloud.online.config.domain.ConfigCommonType;
import com.cqliving.cloud.online.config.service.CommentAppConfigService;
import com.cqliving.cloud.online.config.service.ConfigCommonTypeService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.TopicInfoData;
import com.cqliving.cloud.online.interfacc.dto.TopicInfoDetailData;
import com.cqliving.cloud.online.topic.domain.TopicImage;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.cloud.online.topic.service.TopicImageService;
import com.cqliving.cloud.online.topic.service.TopicInfoService;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title: 话题相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月26日
 */
@Controller
public class TopicController {
	
	private static final Logger logger = LoggerFactory.getLogger(TopicController.class);
	
	@Autowired
	private CommentAppConfigService commentAppConfigService;
	@Autowired
	private ConfigCommonTypeService configCommonTypeService;
	@Autowired
	private TopicImageService topicImageService;
	@Autowired
	private TopicInfoService topicInfoService;
	
	/**
	 * <p>Description: 获取话题详情</p>
	 * @author Tangtao on 2016年7月27日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value = {"topicInfoDetail"}, method = {RequestMethod.POST})
	public Response<TopicInfoDetailData> topicInfoDetail(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			@RequestParam Long id) {
		logger.debug("===================== 调用获取话题详情接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, id=%d", appId, sessionId, token, id));
		Response<TopicInfoDetailData> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || id == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取话题详情接口异常：" + response);
			return response;
		}
		
		//查询数据
		//话题
		Response<TopicInfo> topicInfoResponse = topicInfoService.get(id);
		if (topicInfoResponse == null || topicInfoResponse.getCode() < 0 || topicInfoResponse.getData() == null || 
				(!TopicInfo.STATUS1.equals(topicInfoResponse.getData().getStatus()) && !TopicInfo.STATUS3.equals(topicInfoResponse.getData().getStatus()))) {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("话题不存在或已删除");
			logger.error("调用获取话题详情接口异常：" + response);
			return response;
		}
		TopicInfo topicInfo = topicInfoResponse.getData();
		//话题图片
		Response<List<TopicImage>> topicImageResponse = topicImageService.getByTopicInfoId(id);
		if (topicImageResponse == null || topicImageResponse.getCode() < 0 || CollectionUtils.isEmpty(topicImageResponse.getData())) {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("话题图片不存在或已删除");
			logger.error("调用获取话题详情接口异常：" + response);
			return response;
		}
		List<TopicImage> topicImages = topicImageResponse.getData();
		String[] imageUrlsArray = new String[topicImages.size()];
		for (int i = 0; i < topicImages.size(); i++) {
			imageUrlsArray[i] = topicImages.get(i).getUrl();
		}
		//话题分类
		String types = StringUtils.strip(topicInfo.getTypes(), ",");
		List<ConfigCommonType> typeList = Lists.newArrayList();
		String[] typeArray = ArrayUtils.EMPTY_STRING_ARRAY;
		if (StringUtils.isNotBlank(types)) {
			List<Long> ids = Lists.newArrayList();
			for (String idStr : types.split(",")) {
				ids.add(Long.valueOf(idStr));
			}
			Response<List<ConfigCommonType>> typeResponse = configCommonTypeService.getByIds(ids);
			if (typeResponse == null || typeResponse.getCode() < 0 || CollectionUtils.isEmpty(typeResponse.getData())) {
				response.setCode(ErrorCodes.FAILURE);
				response.setMessage("话题分类不存在或已删除");
				logger.error("调用获取话题详情接口异常：" + response);
				return response;
			}
			typeList = typeResponse.getData();
			typeArray = new String[typeList.size()];
			for (int i = 0; i < typeList.size(); i++) {
				typeArray[i] = typeList.get(i).getName();
			}
		}
		
		//返回数据
		TopicInfoDetailData data = new TopicInfoDetailData();
		data.setContent(topicInfo.getContent());
		data.setImageUrls(StringUtils.join(imageUrlsArray, ','));
		data.setName("#" + topicInfo.getName() + "#");
		data.setTypes(StringUtils.join(typeArray, ','));
		response.setData(data);
		logger.debug("调用获取话题详情接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取首页推荐话题</p>
	 * @author Tangtao on 2016年7月28日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"topicInfoIndex"}, method = {RequestMethod.POST})
	public Response<TopicInfoData> topicInfoIndex(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token) {
		logger.debug("===================== 调用获取首页推荐话题接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s", appId, sessionId, token));
		Response<TopicInfoData> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取首页推荐话题接口异常：" + response);
			return response;
		}
		
		//查询数据
		ScrollPage<TopicInfo> scrollPage = new ScrollPage<TopicInfo>();
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, null));
		scrollPage.setPageSize(10);
		Response<ScrollPage<TopicInfo>> pageResponse = topicInfoService.queryForScrollPage(scrollPage, appId, null, null, TopicInfo.ISRECOMMEND1);
		
		//返回数据
		List<TopicInfoData> dataList = Lists.newArrayList();
		if (pageResponse.getCode() == 0) {
			List<TopicInfo> topicInfos = pageResponse.getData().getPageResults();
			if (CollectionUtils.isNotEmpty(topicInfos)) {
				transfer(appId, dataList, topicInfos);
				response.setData(dataList.get(0));
			} 
		}
		logger.debug("调用获取首页推荐话题接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取话题列表</p>
	 * @author Tangtao on 2016年7月26日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type
	 * @param order
	 * @param name
	 * @param lastBusinessValue
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"topicInfo"}, method = {RequestMethod.POST})
	public Response<CommonListResult<TopicInfoData>> topicInfoList(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			Long type,
			@RequestParam String order, 
			String name, 
			String lastBusinessValue, 
			Long lastId) {
		logger.debug("===================== 调用获取话题列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, type=%d, order=%s, name=%s, lastBusinessValue=%s, lastId=%d", 
						appId, sessionId, token, type, order, name, lastBusinessValue, lastId));
		Response<CommonListResult<TopicInfoData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(order)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取话题列表接口异常：" + response);
			return response;
		}
		
		//查询数据
		ScrollPage<TopicInfo> scrollPage = new ScrollPage<TopicInfo>();
		String columnName = "r".equals(order) ? "reply_count" : "create_time";
		scrollPage.addScrollPageOrder(new ScrollPageOrder(columnName, ScrollPage.DESC, lastBusinessValue));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage.setPageSize(10);
		Response<ScrollPage<TopicInfo>> pageResponse = topicInfoService.queryForScrollPage(scrollPage, appId, type, name, null);
		
		//返回数据
		CommonListResult<TopicInfoData> data = new CommonListResult<TopicInfoData>();
		List<TopicInfoData> dataList = Lists.newArrayList();
		if (pageResponse.getCode() == 0) {
			List<TopicInfo> topicInfos = pageResponse.getData().getPageResults();
			if (CollectionUtils.isNotEmpty(topicInfos)) {
				transfer(appId, dataList, topicInfos);
			} 
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用获取话题列表接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取我的话题</p>
	 * @author Tangtao on 2016年7月27日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"topicInfoMine"}, method = {RequestMethod.POST})
	public Response<CommonListResult<TopicInfoData>> topicInfoMine(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			Long lastId) {
		logger.debug("===================== 调用获取我的话题列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, lastId=%d", 
						appId, sessionId, token, lastId));
		Response<CommonListResult<TopicInfoData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取我的话题列表接口异常：" + response);
			return response;
		}
		
		//查询数据
		ScrollPage<TopicInfo> scrollPage = new ScrollPage<TopicInfo>();
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage.setPageSize(10);
		Response<ScrollPage<TopicInfo>> pageResponse = topicInfoService.queryMyScrollPage(scrollPage, appId, sessionId, token);
		
		//返回数据
		CommonListResult<TopicInfoData> data = new CommonListResult<TopicInfoData>();
		List<TopicInfoData> dataList = Lists.newArrayList();
		if (pageResponse.getCode() == 0) {
			List<TopicInfo> topicInfos = pageResponse.getData().getPageResults();
			if (CollectionUtils.isNotEmpty(topicInfos)) {
				transfer(appId, dataList, topicInfos);
			} 
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用获取我的话题列表接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 删除我的话题</p>
	 * @author Tangtao on 2016年7月28日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"topicInfoRemove"}, method = {RequestMethod.POST})
	public Response<Boolean> topicInfoRemove(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			@RequestParam Long id) {
		logger.debug("===================== 调用话题删除接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, id=%d", appId, sessionId, token, id));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || id == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用话题删除接口异常：" + response);
			return response;
		}
		response = topicInfoService.remove(appId, sessionId, token, id);
		logger.debug("调用话题删除接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 发布话题</p>
	 * @author Tangtao on 2016年7月27日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param name
	 * @param content
	 * @param imgs
	 * @param typeIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"topicInfoSave"}, method = {RequestMethod.POST})
	public Response<Boolean> topicInfoSave(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			@RequestParam String name,
			@RequestParam String content,
			@RequestParam String imgs,
			@RequestParam String typeIds) {
		logger.debug("===================== 调用话题发布接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, name=%s, content=%s, imgs.length=%d, typeIds=%s, typeIds.length=%d", 
						appId, sessionId, token, name, content, imgs.split(",").length, typeIds, typeIds.split(",").length));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || StringUtils.isBlank(name) || StringUtils.isBlank(content) || StringUtils.isBlank(imgs) || StringUtils.isBlank(typeIds) || !typeIds.trim().matches("^\\d+(,\\d+)*$")) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用话题发布接口异常：" + response);
			return response;
		}
		response = topicInfoService.add(appId, sessionId, token, name, content, imgs, typeIds);
		logger.debug("调用话题发布接口：" + response);
		return response;
	}

	/**
	 * <p>Description: 获取置顶话题列表</p>
	 * @author Tangtao on 2016年7月26日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"topicInfoTop"}, method = {RequestMethod.POST})
	public Response<CommonListResult<TopicInfoData>> topicInfoTop(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token) {
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s", appId, sessionId, token);
		logger.debug("===================== 调用获取置顶话题接口 =====================>" + parameters);
		Response<CommonListResult<TopicInfoData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取置顶话题接口异常：" + response + parameters);
			return response;
		}
		Response<List<TopicInfo>> dataResponse = topicInfoService.getTopList(appId);	
		
		//返回数据
		CommonListResult<TopicInfoData> data = new CommonListResult<TopicInfoData>();
		List<TopicInfoData> dataList = Lists.newArrayList();
		if (dataResponse.getCode() == 0) {
			List<TopicInfo> list = dataResponse.getData();
			if (CollectionUtils.isNotEmpty(list)) {
				transfer(appId, dataList, list);
			} 
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用获取置顶话题接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取话题分类</p>
	 * @author Tangtao on 2016年7月27日
	 * @param request
	 * @param appId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"topicInfoTypes"}, method = {RequestMethod.POST})
	public Response<CommonListResult<Map<Long, String>>> topicInfoTypes(HttpServletRequest request, @RequestParam Long appId) {
		logger.debug("===================== 调用获取话题分类接口 =====================>"
				+ String.format("\n<请求参数：appId=%d", appId));
		Response<CommonListResult<Map<Long, String>>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取话题分类接口异常：" + response);
			return response;
		}
		//查询数据
		Response<List<ConfigCommonType>> typeResponse = configCommonTypeService.getBySourceType(appId, BusinessType.SOURCE_TYPE_7);
		
		//返回数据
		CommonListResult<Map<Long, String>> data = new CommonListResult<Map<Long, String>>();
		List<Map<Long, String>> dataList = Lists.newArrayList();
		Map<Long, String> map;
		for (ConfigCommonType type  : typeResponse.getData()) {
			map = Maps.newHashMap();
			map.put(type.getId(), type.getName());
			dataList.add(map);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用获取话题分类接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 转换为接口返回对象</p>
	 * @author Tangtao on 2016年7月26日
	 * @param appId
	 * @param dataList
	 * @param topicInfos
	 */
	private void transfer(Long appId, List<TopicInfoData> dataList, List<TopicInfo> topicInfos) {
		TopicInfoData data;
		//获取是否允许评论配置
		Byte canComment = commentAppConfigService.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_7).getData();
		String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
		for (TopicInfo obj : topicInfos) {
			data = new TopicInfoData();
			data.setCommentType(NumberUtils.toByte(CommentConfig.VALUE0.equals(canComment) ? "1" : "0"));
			data.setCreateTime(DateUtil.format(obj.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
			data.setCreateTimeStr(DateUtil.format(obj.getCreateTime(), "yyyy.MM.dd"));
			data.setCreator(obj.getCreator());
			data.setDetailViewType(ClientControlType.DETAIL_VIEW_TYPE_7);
			data.setId(obj.getId());
			data.setListImageUrl(obj.getListImageUrl());
			data.setName("#" + obj.getName() + "#");
			data.setRecommendImageUrl(obj.getRecommendImageUrl());
			data.setReplyCount(obj.getReplyCount());
			data.setShareUrl(webPath + "/topic_share.html?id=" + obj.getId());	
			data.setSourceType(BusinessType.SOURCE_TYPE_7);
			data.setSynopsis("");	//FIXME Tangtao
			data.setTitle(obj.getName());
			data.setTopImageUrl(obj.getTopImageUrl());
			data.setUrl("");	//FIXME Tangtao
			dataList.add(data);
		}
	}
	
}