/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.AppPermission;
import com.cqliving.cloud.online.config.dto.AppPermissionDto;
import com.cqliving.cloud.online.config.service.AppPermissionService;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.JsonMapper;
import com.cqliving.tool.common.util.RequestVerify;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月15日
 */
@Component
public class RequestPermissionUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestPermissionUtil.class);
	private JsonMapper mapper = JsonMapper.nonEmptyMapper();
	
	@Autowired 
	private AbstractRedisClient abstractRedisClient;
	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private AppPermissionService appPermissionService;
	
	/**
	 * <p>Description: 权限处理</p>
	 * @author Tangtao on 2016年12月15日
	 * @param request
	 * @param appId
	 * @param uri
	 * @return
	 */
	public Response<Void> handlePermisson(HttpServletRequest request, Long appId, String uri) {
		//查询是否缓存此权限
		AppPermissionDto value = abstractRedisClient.getHSet(CacheConstant.REQUEST_PERMISSION_APPID_HASH_PREFIX + appId, uri, AppPermissionDto.class);
		if (value == null) {
			//不需要控制
			return Response.newInstance();
		} 
		
		Response<Void> response = Response.newInstance();
		//权限判断
		if (AppPermission.ISPERMISSION0.equals(value.getIsPermission())) {	//没有请求权限
			response.setCode(ErrorCodes.CommonErrorEnum.REQUEST_NO_PERMISSION.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.REQUEST_NO_PERMISSION.getDesc());
		} else if (AppPermission.ISSIGN1.equals(value.getIsSign())) {	//需要加密签名
			Response<AppInfo> appResponse = appInfoService.get(appId);
			//查询 app 私钥
			String privateKey = appResponse.getData().getRequestSignKey();
			//加密验证
			response = RequestVerify.verify(request.getParameterMap(), privateKey);
		} else if (AppPermission.ISLOGIN1.equals(value.getIsLogin())) {	//需要token
			Map<String, String[]> map = request.getParameterMap();
			String token = ArrayUtils.isNotEmpty(map.get("token")) ? map.get("token")[0] : null;
			if (StringUtils.isBlank(token)) {	//需要token参数
				response.setCode(ErrorCodes.CommonErrorEnum.TOKEN_IS_BLANK.getCode());
			    response.setMessage(ErrorCodes.CommonErrorEnum.TOKEN_IS_BLANK.getDesc());
			}
		} else if (AppPermission.ISSESSIONID1.equals(value.getIsSessionId())) {	//需要sessionId
			Map<String, String[]> map = request.getParameterMap();
			String sessionId = ArrayUtils.isNotEmpty(map.get("sessionId")) ? map.get("sessionId")[0] : null;
			if (StringUtils.isBlank(sessionId)) {	//需要token参数
				response.setCode(ErrorCodes.CommonErrorEnum.TOKEN_IS_BLANK.getCode());
			    response.setMessage(ErrorCodes.CommonErrorEnum.TOKEN_IS_BLANK.getDesc());
			}
		} else if (AppPermission.ISREQUESTTIMES1.equals(value.getIsRequestTimes())) {	//需要控制访问频率
			//TODO Tangtao 暂时使用 RequestLimitUtil 在接口方法中实现
		}
		return response;
	}
	
	/**
	 * <p>Description: 初始化方法，将权限数据写入缓存</p>
	 * @author Tangtao on 2016年12月15日
	 */
	public void init() {
		logger.debug("============ 初始化客户端资源权限 begin ============");
		//查询全部权限数据
		List<AppPermissionDto> dtos = appPermissionService.getDtoOfAll().getData();
		for (AppPermissionDto dto : dtos) {
			abstractRedisClient.setHSet(CacheConstant.REQUEST_PERMISSION_APPID_HASH_PREFIX + dto.getAppId(), dto.getUrl(), mapper.toJson(dto));
		}
		logger.debug("============ 初始化客户端资源权限 finished ============");
	}

}