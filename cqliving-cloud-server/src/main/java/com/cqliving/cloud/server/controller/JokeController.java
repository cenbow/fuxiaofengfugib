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

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.JokeInfoData;
import com.cqliving.cloud.online.joke.service.JokeInfoService;
import com.cqliving.tool.common.Response;

/**
 * Title: 段子相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月29日
 */
@Controller
public class JokeController {
	
	private static final Logger logger = LoggerFactory.getLogger(JokeController.class);
	
	@Autowired
	private JokeInfoService jokeInfoService;
	
	/**
	 * <p>Description: 获取段子列表/详情</p>
	 * @author Tangtao on 2016年6月29日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @param lastId
	 * @param lastOnlineTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"jokeInfo"}, method = {RequestMethod.POST})
	public Response<CommonListResult<JokeInfoData>> jokeInfo(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			Long id, 
			Long lastId, 
			String lastOnlineTime) {
		logger.debug("===================== 调用获取段子列表/详情接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, id=%d, lastId=%d, lastOnlineTime=%s", appId, sessionId, token, id, lastId, lastOnlineTime));
		Response<CommonListResult<JokeInfoData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取段子列表/详情接口异常：" + response);
			return response;
		}
		response = jokeInfoService.getJokeInfo(appId, sessionId, token, id, lastId, lastOnlineTime);	
		logger.debug("调用获取段子列表/详情接口结果：" + response);
		return response;
	}

}