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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
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
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.app.service.AppResouseService;
import com.cqliving.cloud.online.info.service.InfoClassifyService;
import com.cqliving.cloud.online.interfacc.dto.BusinessDetailResult;
import com.cqliving.cloud.online.interfacc.dto.CommentsData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ImageTextResult;
import com.cqliving.cloud.online.interfacc.dto.MyCommentsData;
import com.cqliving.cloud.online.interfacc.dto.NewsData;
import com.cqliving.cloud.online.interfacc.dto.NewsResult;
import com.cqliving.cloud.online.interfacc.dto.SpecialInfoDetailResult;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;

/**
 * Title: 咨询相关接口
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年5月2日
 */
@Controller
public class InformationController {
	
	private static final Logger logger = LoggerFactory.getLogger(InformationController.class);
	
	@Autowired
	private AppResouseService appResouseService;
	@Autowired
	private InfoClassifyService infoClassifyService;
	@Autowired
	private UserInfoReplyService userInfoReplyService;
	
	/**
	 * <p>Description: 获取业务详情信息</p>
	 * @author Tangtao on 2016年6月4日
	 * @param request
	 * @param appId
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping({"getBusinessDetail"})
	public Response<BusinessDetailResult> getBusinessDetail(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType) {
		String sourceTypeStr = (sourceType != null && BusinessType.allSourceTypes.containsKey(sourceType)) ? BusinessType.allSourceTypes.get(sourceType) : "未知";
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, sourceId=%d, sourceType=%d[%s]", appId, sessionId, token, sourceId, sourceType, sourceTypeStr);
		logger.debug("===================== 调用获取业务详情信息接口 =====================>" + parameters);
		Response<BusinessDetailResult> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || sourceId == null || "未知".equals(sourceTypeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取业务详情信息异常：" + response + parameters);
			return response;
		}
		response = infoClassifyService.getBusinessDetail(appId, sessionId, token, sourceId, sourceType);
		logger.debug("调用获取业务详情信息结果：" + response);
		return response;
	}

	/**
	 * Title: 获取资讯评论列表
	 * @author Tangtao on 2016年5月1日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param sourceId
	 * @param sourceType
	 * @param lastReplyId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"comments"}, method = {RequestMethod.POST})
	public Response<CommonListResult<CommentsData>> getComments(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType, 
			String lastReplyId) {
		String sourceTypeStr = (sourceType != null && UserInfoReply.allSourceTypes.containsKey(sourceType)) ? UserInfoReply.allSourceTypes.get(sourceType) : "未知";
		logger.debug("===================== 调用获取评论列表接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, sourceId=%s, sourceType=%d[%s], lastReplyId=%s", 
						appId, sessionId, token, sourceId, sourceType, sourceTypeStr, lastReplyId));
		Response<CommonListResult<CommentsData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || sourceId == null || "未知".equals(sourceTypeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取评论列表接口异常：" + response);
			return response;
		}
		Long lastId = StringUtils.isBlank(lastReplyId) ? null : Long.valueOf(lastReplyId);
		response = userInfoReplyService.getPageBySourceId(appId, sessionId, token, sourceId, sourceType, lastId);
		logger.debug("调用获取评论列表接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 获取相关资讯，最多5条</p>
	 * @author Tangtao on 2016年6月1日
	 * @param request
	 * @param appId
	 * @param infoClassifyId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getCorrelation"}, method = {RequestMethod.POST})
	public Response<CommonListResult<NewsData>> getCorrelation(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam("id") Long infoClassifyId) {
		logger.debug("===================== 调用获取相关资讯接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, infoClassifyId=%s", appId, infoClassifyId));
		Response<CommonListResult<NewsData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || infoClassifyId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取相关资讯接口异常：" + response);
			return response;
		}
		response = infoClassifyService.getCorrelation(appId, infoClassifyId);
		logger.debug("调用获取相关资讯接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取图片新闻资源列表</p>
	 * @author Tangtao on 2016年6月2日
	 * @param request
	 * @param appId
	 * @param informationId
	 * @return
	 */
	@ResponseBody
	@RequestMapping({"info/getImageTexts"})
	public Response<ImageTextResult> getImageTexts(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam Long informationId) {
		logger.debug("===================== 调用获取图片新闻资源列表接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, informationId=%s", appId, informationId));
		Response<ImageTextResult> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || informationId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取图片新闻资源列表接口异常：" + response);
			return response;
		}
		Response<List<AppResouse>> resp = appResouseService.findByInformationId(informationId);
		
		//返回数据
		ImageTextResult data = new ImageTextResult();
		List<String> descriptions = Lists.newArrayList();
		List<String> images = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(resp.getData())) {
			for (AppResouse ar : resp.getData()) {
				if (AppResouse.TYPE6.equals(ar.getType())) {
					descriptions.add(ar.getDescription());
					images.add(ar.getFileUrl());
				}
			}
			data.setDescriptions(descriptions);
			data.setImages(images);
		}
		response.setData(data);
		logger.debug("调用获取图片新闻资源列表接口结果：" + response);
		return response;
	}
	
	/**
	 * Title: 获取我的评论列表
	 * @author Tangtao on 2016年5月2日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type {1:我的评价,2:收到的评价}
	 * @param lastReplyId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/comments"}, method = {RequestMethod.POST, RequestMethod.GET})
	public Response<CommonListResult<MyCommentsData>> getMyComments(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Integer type, 
			Long lastReplyId) {
		String typeStr = (type != null && type.intValue() == 1) ? "我的评价" :  (type != null && type.intValue() == 2) ? "收到的评价" : "未知";
		logger.debug("===================== 调用获取我的评论接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, type=%d[%s], lastReplyId=%s",  appId, sessionId, token, type, typeStr, lastReplyId));
		Response<CommonListResult<MyCommentsData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || "未知".equals(typeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取我的评论异常：" + response);
			return response;
		}
		response = userInfoReplyService.getPageByUser(appId, sessionId, token, type, lastReplyId, null);
		logger.debug("调用获取我的评论结果：" + response);
		return response;
	}

	/**
	 * Title: 获取资讯列表
	 * @author Tangtao on 2016年5月1日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param isCarousel
	 * @param columnId
	 * @param lastId
	 * @param lastSortNo
	 * @param lastOnlineTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"news"}, method = {RequestMethod.POST})
	public Response<NewsResult> getNews(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			@RequestParam Boolean isCarousel, 
			@RequestParam Long columnId, 
			Long lastId, 
			Integer lastSortNo, 
			String lastOnlineTime) {
		logger.debug("===================== 调用获取资讯列表接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, isCarousel=%s, columnId=%s, lastId=%s, lastSortNo=%s, lastOnlineTime=%s", 
						appId, sessionId, token, isCarousel, columnId, lastId, lastSortNo, lastOnlineTime));
		Response<NewsResult> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || columnId == null || isCarousel == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取资讯列表接口异常：" + response);
			return response;
		}
		response = infoClassifyService.getNewsByPage(appId, columnId, isCarousel, lastId, lastSortNo, lastOnlineTime);
		logger.debug("调用获取资讯列表接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取推荐新闻列表</p>
	 * @author Tangtao on 2016年11月23日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getRecommended"}, method = {RequestMethod.POST})
	public Response<CommonListResult<NewsData>> getRecommended(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token) {
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s", appId, sessionId, token);
		logger.debug("===================== 调用获取推荐资讯列表接口 =====================>" + parameters);
		Response<CommonListResult<NewsData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取推荐资讯列表接口异常：" + response + parameters);
			return response;
		}
		response = infoClassifyService.getRecommended(appId);
		logger.debug("调用获取推荐资讯列表接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取专题资讯详情</p>
	 * @author Tangtao on 2016年6月6日
	 * @param request
	 * @param appId
	 * @param infoClassifyId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"info/getSpecialDetail"}, method = {RequestMethod.POST})
	public Response<SpecialInfoDetailResult> getSpecialDetail(HttpServletRequest request, 
			@RequestParam Long appId,@RequestParam Long infoClassifyId) {
		logger.debug("===================== 调用获取专题资讯详情接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, infoClassifyId=%d", appId, infoClassifyId));
		Response<SpecialInfoDetailResult> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || infoClassifyId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取专题资讯详情接口异常：" + response);
			return response;
		}
		response = infoClassifyService.getSpecialDetail(appId, infoClassifyId);
		logger.debug("调用获取专题资讯详情接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取专题子新闻</p>
	 * @author Tangtao on 2016年6月6日
	 * @param request
	 * @param appId
	 * @param infoClassifyId
	 * @param themeId
	 * @param lastId
	 * @param lastSortNo
	 * @param lastOnlineTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"info/getSpecialSubList"}, method = {RequestMethod.POST})
	public Response<CommonListResult<NewsData>> getSpecialSubList(HttpServletRequest request, 
			@RequestParam Long appId,@RequestParam Long infoClassifyId, 
			@RequestParam Long themeId,Long lastId,Integer lastSortNo,String lastOnlineTime) {
		logger.debug("===================== 调用获取专题子新闻接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, infoClassifyId=%d, themeId=%d, lastId=%d, lastSortNo=%d, lastOnlineTime=%s", appId, infoClassifyId, themeId, lastId, lastSortNo, lastOnlineTime));
		Response<CommonListResult<NewsData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || infoClassifyId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取专题子新闻接口异常：" + response);
			return response;
		}
		response = infoClassifyService.getSpecialSubList(appId, infoClassifyId, themeId, lastId, lastSortNo, lastOnlineTime);
		logger.debug("调用获取专题子新闻接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 资讯搜索</p>
	 * @author Tangtao on 2016年6月1日
	 * @param request
	 * @param appId
	 * @param title
	 * @param lastId
	 * @param lastOnlineTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"info/search"}, method = {RequestMethod.POST})
	public Response<CommonListResult<NewsData>> searchNews(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String title, 
			Long lastId, 
			String lastOnlineTime) {
		logger.debug("===================== 调用资讯搜索接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, title=%s, lastId=%d, lastOnlineTime=%s", appId, title, lastId, lastOnlineTime));
		Response<CommonListResult<NewsData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(title)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用资讯搜索接口异常：" + response);
			return response;
		}
		response = infoClassifyService.searchNewsByPage(appId, title, lastId, lastOnlineTime);
		logger.debug("调用资讯搜索接口结果：" + response);
		return response;
	}

}