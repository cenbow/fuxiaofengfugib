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
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.service.ActInfoListService;
import com.cqliving.cloud.online.interfacc.dto.ActInfoData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title: 段子相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月29日
 */
@Controller
public class ActController {
	
	private static final Logger logger = LoggerFactory.getLogger(ActController.class);
	
	@Autowired
	private ActInfoListService actInfoListService;
	
	/**
	 * <p>Description: 获取首页推荐活动</p>
	 * @author Tangtao on 2016年7月28日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"actIndex"}, method = {RequestMethod.POST})
	public Response<ActInfoData> actIndexPage(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token) {
		logger.debug("===================== 调用获取首页推荐活动接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, ", appId, sessionId, token));
		Response<ActInfoData> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取首页推荐活动接口异常：" + response);
			return response;
		}
		Response<CommonListResult<ActInfoData>> listResponse = actInfoListService.getScrollPage(appId, sessionId, token, null, null, null, null, null, ActInfo.ISRECOMMEND1);	
		if (listResponse.getCode() == 0 && listResponse.getData() != null) {
			List<ActInfoData> datas = listResponse.getData().getDataList();
			if (CollectionUtils.isNotEmpty(datas)) {
				response.setData(datas.get(0));
			}
		}
		logger.debug("调用获取首页推荐活动接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取活动列表</p>
	 * @author Tangtao on 2016年6月29日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param rangeType
	 * @param showType
	 * @param lastId
	 * @param lastSortNo
	 * @param lastStartTimestamp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"actList"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ActInfoData>> actList(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			Byte rangeType, 
			Byte showType, 
			Long lastId, 
			Integer lastSortNo, 
			String lastStartTimestamp) {
		String rangeTypeStr = "全部活动";
		if (rangeType != null) {
			if (rangeType.byteValue() == 1) {
				rangeTypeStr = "未过期活动";
			} else if (rangeType.byteValue() == 0) {
				rangeTypeStr = "已过期活动";
			}
		}
		String showTypeStr = showType == null ? "全部类型" : ActInfoList.allShowTypes.containsKey(showType) ? ActInfoList.allShowTypes.get(showType) : "未知";
		logger.debug("===================== 调用获取活动列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, rangeType=%d[%s], showType=%d[%s], lastId=%d, lastSortNo=%d, lastStartTimestamp=%s", 
						appId, sessionId, token, rangeType, rangeTypeStr, showType, showTypeStr, lastId, lastSortNo, lastStartTimestamp));
		Response<CommonListResult<ActInfoData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取活动列表接口异常：" + response);
			return response;
		}
		response = actInfoListService.getScrollPage(appId, sessionId, token, rangeType, showType, lastId, lastSortNo, lastStartTimestamp, null);	
		logger.debug("调用获取活动列表接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取活动类型</p>
	 * @author Tangtao on 2016年6月29日
	 * @param request
	 * @param appId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"actTypes"}, method = {RequestMethod.POST})
	public Response<CommonListResult<Map<Byte, String>>> actTypes(HttpServletRequest request, @RequestParam Long appId) {
		logger.debug("===================== 调用获取活动类型接口 =====================>"
				+ String.format("\n<请求参数：appId=%d", appId));
		Response<CommonListResult<Map<Byte, String>>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取活动类型接口异常：" + response);
			return response;
		}
		CommonListResult<Map<Byte, String>> data = new CommonListResult<Map<Byte, String>>();
		List<Map<Byte, String>> dataList = Lists.newArrayList();
		Map<Byte, String> map;
		for (Byte key  : ActInfoList.allTypes.keySet()) {
			map = Maps.newHashMap();
			map.put(key, ActInfoList.allTypes.get(key));
			dataList.add(map);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用获取活动类型接口结果：" + response);
		return response;
	}

}