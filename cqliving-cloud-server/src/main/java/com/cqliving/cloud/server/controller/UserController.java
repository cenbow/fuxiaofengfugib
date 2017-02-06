/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.online.account.domain.UserFavorite;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.domain.UserOtherAccount;
import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.cloud.online.account.domain.UserReport;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.domain.UserShareHistory;
import com.cqliving.cloud.online.account.domain.UserSmsLog;
import com.cqliving.cloud.online.account.domain.UserViewRecode;
import com.cqliving.cloud.online.account.service.UserAccountService;
import com.cqliving.cloud.online.account.service.UserFavoriteService;
import com.cqliving.cloud.online.account.service.UserFeedbackService;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.account.service.UserInfoService;
import com.cqliving.cloud.online.account.service.UserMessageNumService;
import com.cqliving.cloud.online.account.service.UserPraiseService;
import com.cqliving.cloud.online.account.service.UserReportService;
import com.cqliving.cloud.online.account.service.UserSessionService;
import com.cqliving.cloud.online.account.service.UserShareHistoryService;
import com.cqliving.cloud.online.account.service.UserSmsLogService;
import com.cqliving.cloud.online.account.service.UserViewRecodeService;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.service.InfoClassifyService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.FavoritesData;
import com.cqliving.cloud.online.interfacc.dto.FavoritesShoppingData;
import com.cqliving.cloud.online.interfacc.dto.FeedbackData;
import com.cqliving.cloud.online.interfacc.dto.LoginResult;
import com.cqliving.cloud.online.interfacc.dto.PraisesData;
import com.cqliving.cloud.online.interfacc.dto.ShootInfoData;
import com.cqliving.cloud.online.interfacc.dto.UploadResult;
import com.cqliving.cloud.online.interfacc.dto.VisitResult;
import com.cqliving.cloud.online.message.domain.MessageUnreadCount;
import com.cqliving.cloud.online.message.service.MessageUnreadCountService;
import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.cloud.online.shoot.service.ShootInfoService;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.RequestVerify;
import com.cqliving.tool.utils.RequestUtil;
import com.google.common.collect.Lists;

/**
 * Title: 用户相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月27日
 */
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private InfoClassifyService infoClassifyService;
	@Autowired
	private MessageUnreadCountService messageUnreadCountService;
	@Autowired
	private ShootInfoService shootInfoService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserFavoriteService userFavoriteService;
	@Autowired
	private UserFeedbackService userFeedbackService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserInfoReplyService userInfoReplyService;
	@Autowired
	private UserMessageNumService userMessageNumService;
	@Autowired
	private UserReportService userReportService;
	@Autowired
	private UserPraiseService userPraiseService;
	@Autowired
	private UserSessionService userSessionService;
	@Autowired
	private UserShareHistoryService userShareHistoryService;
	@Autowired
	private UserSmsLogService userSmsLogService;
	@Autowired
	private UserViewRecodeService userViewRecodeService;
	
	/**
	 * <p>Description: 更换手机号第一步，验证</p>
	 * @author Tangtao on 2016年5月3日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param loginName
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"changePhone1"}, method = {RequestMethod.POST})
	public Response<Void> changePhone1(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			@RequestParam String loginName, 
			@RequestParam String pwd) {
		logger.debug("===================== 调用更换手机第一步接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, loginName=%s, pwd=%s", appId, sessionId, token, loginName, pwd));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(token) || StringUtils.isBlank(sessionId) || StringUtils.isBlank(loginName) || StringUtils.isBlank(pwd)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用更换手机第一步接口异常：" + response);
			return response;
		}
		response = userAccountService.check(appId, sessionId, token, loginName, pwd);
		logger.debug("调用更换手机第一步接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 更换手机号第二步，修改</p>
	 * @author Tangtao on 2016年5月3日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param captcha
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"changePhone2"}, method = {RequestMethod.POST})
	public Response<Void> changePhone2(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String loginName, 
			@RequestParam String captcha) {
		logger.debug("===================== 调用更换手机第二步接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, loginName=%s, captcha=%s", appId, sessionId, loginName, captcha));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(captcha) || StringUtils.isBlank(sessionId) || StringUtils.isBlank(loginName)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用更换手机第二步接口异常：" + response);
			return response;
		}
		response = userAccountService.changePhone(appId, sessionId, loginName, captcha);
		logger.debug("调用更换手机第二步接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 收藏/取消收藏</p>
	 * @author Tangtao on 2016年5月31日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type 1: 收藏, 0: 取消收藏
	 * @param title
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"collect"}, method = {RequestMethod.POST})
	public Response<Boolean> collect(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Byte type, 
			@RequestParam String title, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType) {
		String sourceTypeStr = (sourceType != null && UserFavorite.allSourceTypes.containsKey(sourceType)) ? UserFavorite.allSourceTypes.get(sourceType) : "未知";
		logger.debug("===================== 调用收藏/取消收藏接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, type=%d, title=%s, sourceId=%s, sourceType=%d[%s]", 
						appId, sessionId, token, type, title, sourceId, sourceType, sourceTypeStr));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(title) || sourceId == null || "未知".equals(sourceTypeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用收藏/取消收藏接口异常：" + response);
			return response;
		}
		response = userFavoriteService.collect(appId, sessionId, token, type, title, sourceId, sourceType);
		logger.debug("调用收藏/取消收藏接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 举报</p>
	 * @author Tangtao on 2016年5月26日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param content
	 * @param sourceId
	 * @param type
	 * @param sourceType
	 * @param reportCodes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"commentReport"}, method = {RequestMethod.POST})
	public Response<Void> commentReport(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			String content,
			@RequestParam Long sourceId, 
			@RequestParam Byte type, 
			@RequestParam Byte sourceType, 
			@RequestParam String reportCodes) {
		String sourceTypeStr = (sourceType != null && UserReport.allSourceTypes.containsKey(sourceType)) ? UserReport.allSourceTypes.get(sourceType) : "未知";
		String typeStr = (type != null && UserReport.allOperateTypes.containsKey(type)) ? UserReport.allOperateTypes.get(type) : "未知";
		logger.debug("===================== 调用举报接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, content=%s, sourceId=%d, type=%d[%s], sourceType=%d[%s], reportCodes=%s", 
						appId, sessionId, token, content, sourceId, type, typeStr, sourceType, sourceTypeStr, reportCodes));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || sourceId == null || StringUtils.isBlank(reportCodes) || "未知".equals(sourceTypeStr) || "未知".equals(typeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用举报接口异常：" + response);
			return response;
		}
		response = userReportService.saveUserReport(appId, sessionId, token, content, sourceId, type, sourceType, reportCodes);
		logger.debug("调用举报接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 保存评论</p>
	 * @author Tangtao on 2016年5月26日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param sourceId
	 * @param sourceType
	 * @param place
	 * @param lng
	 * @param lat
	 * @param replyId
	 * @param passiveReplyName
	 * @param passiveReplyId
	 * @param content
	 * @param isAnonymous
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"commentSave"}, method = {RequestMethod.POST})
	public Response<Void> commentSave(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType, 
			String place, 
			String lng, 
			String lat, 
			Long replyId, 
			String passiveReplyName, 
			Long passiveReplyId, 
			@RequestParam String content, 
			@RequestParam Boolean isAnonymous) {
		String sourceTypeStr = (sourceType != null && UserInfoReply.allSourceTypes.containsKey(sourceType)) ? UserInfoReply.allSourceTypes.get(sourceType) : "未知";
		logger.debug("===================== 调用保存评论接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, sourceId=%d, sourceType=%d[%s], place=%s, lng=%s, lat=%s, replyId=%s, passiveReplyName=%s, passiveReplyId=%s, content=%s, isAnonymous=%s", 
						appId, sessionId, token, sourceId, sourceType, sourceTypeStr, place, lng, lat, replyId, passiveReplyName, passiveReplyId, content, isAnonymous));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || sourceId == null || StringUtils.isBlank(content) || "未知".equals(sourceTypeStr) || isAnonymous == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用保存评论接口异常：" + response);
			return response;
		}
		response = userInfoReplyService.add(appId, sessionId, token, sourceId, sourceType, place, lng, lat, replyId, passiveReplyName, passiveReplyId, content, isAnonymous);
		logger.debug("调用保存评论接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 点踩</p>
	 * @author Tangtao on 2016年7月5日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param userId 被鄙视用户 id
	 * @param title 资讯标题/评论内容
	 * @param type
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"doDespise"}, method = {RequestMethod.POST})
	public Response<Boolean> doDespise(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			Long userId, 
			String title, 
			@RequestParam Byte type, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType) {
		String sourceTypeStr = (sourceType != null && UserPraise.allSourceTypes.containsKey(sourceType)) ? UserPraise.allSourceTypes.get(sourceType) : "未知";
		String typeStr = (type != null && UserPraise.allOperateTypes.containsKey(type)) ? UserPraise.allOperateTypes.get(type) : "未知";
		logger.debug("===================== 调用点踩接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, userId=%d, title=%s, type=%d[%s], sourceId=%s, sourceType=%d[%s]", 
						appId, sessionId, token, userId, title, type, typeStr, sourceId, sourceType, sourceTypeStr));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || sourceId == null || sourceType == null || !UserPraise.allSourceTypes.containsKey(sourceType)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用点踩接口异常：" + response);
			return response;
		}
		response = userPraiseService.praise(appId, sessionId, token, userId, title, type, sourceId, sourceType, UserPraise.TYPE1);
		logger.debug("调用点踩接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 点赞</p>
	 * @author Tangtao on 2016年6月1日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param userId 被点赞用户 id
	 * @param title 资讯标题/评论内容
	 * @param type
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"doPraise"}, method = {RequestMethod.POST})
	public Response<Boolean> doPraise(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			Long userId, 
			String title, 
			@RequestParam Byte type, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType) {
		String sourceTypeStr = (sourceType != null && UserPraise.allSourceTypes.containsKey(sourceType)) ? UserPraise.allSourceTypes.get(sourceType) : "未知";
		String typeStr = (type != null && UserPraise.allOperateTypes.containsKey(type)) ? UserPraise.allOperateTypes.get(type) : "未知";
		logger.debug("===================== 调用点赞接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, userId=%d, title=%s, type=%d[%s], sourceId=%s, sourceType=%d[%s]", 
						appId, sessionId, token, userId, title, type, typeStr, sourceId, sourceType, sourceTypeStr));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || sourceId == null || "未知".equals(sourceTypeStr) || "未知".equals(typeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用点赞接口异常：" + response);
			return response;
		}
		response = userPraiseService.praise(appId, sessionId, token, userId, title, type, sourceId, sourceType, UserPraise.TYPE0);
		logger.debug("调用点赞接口结果：" + response);
		return response;
	}

	/**
	 * Title: 我的资讯/段子收藏
	 * @author Tangtao on 2016年5月2日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastFavoriteId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"favorites"}, method = {RequestMethod.POST})
	public Response<CommonListResult<FavoritesData>> favorites(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			Long lastFavoriteId) {
		logger.debug("===================== 调用我的资讯/段子收藏接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, lastFavoriteId=%s", appId, sessionId, token, lastFavoriteId));
		Response<CommonListResult<FavoritesData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(token) || StringUtils.isBlank(sessionId)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用我的资讯/段子收藏接口异常：" + response);
			return response;
		}
		response = userFavoriteService.getMyFavoritesPage(appId, sessionId, token, lastFavoriteId);
		logger.debug("调用我的资讯/段子收藏接口结果：" + response);
		return response;
	}
	
	/**
	 * Title: 我的商城商品收藏
	 * @author Tangtao on 2016年11月21日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastFavoriteId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"favoritesShopping"}, method = {RequestMethod.POST})
	public Response<CommonListResult<FavoritesShoppingData>> favoritesShopping(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			Long lastFavoriteId) {
		logger.debug("===================== 调用我的商品收藏接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, lastFavoriteId=%s", appId, sessionId, token, lastFavoriteId));
		Response<CommonListResult<FavoritesShoppingData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(token) || StringUtils.isBlank(sessionId)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用我的商品收藏接口异常：" + response);
			return response;
		}
		response = userFavoriteService.getMyFavoritesShoppingPage(appId, sessionId, token, lastFavoriteId);
		logger.debug("调用我的商品收藏接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 找回密码</p>
	 * @author Tangtao on 2016年5月18日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param pwd
	 * @param captcha
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"findPwd"}, method = {RequestMethod.POST})
	public Response<Void> findPwd(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String pwd, 
			@RequestParam String captcha, 
			@RequestParam String phone) {
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, pwd=%s, captcha=%s, phone=%s", appId, sessionId, pwd, captcha, phone);
		logger.debug("===================== 调用找回密码接口 =====================>" + parameters);
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(pwd) || StringUtils.isBlank(captcha) || StringUtils.isBlank(phone)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用找回密码接口异常：" + response + parameters);
			return response;
		}
		response = userAccountService.findPwd(appId, sessionId, pwd, captcha, phone);
		logger.debug("调用找回密码接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 获取验证码</p>
	 * @author Tangtao on 2016年4月27日
	 * @param request
	 * @param appId 客户端 code
	 * @param sessionId 会话 id
	 * @param phone 手机号码
	 * @param type 验证码类型
	 * @return
	 * @see UserSmsLog#TYPE0 注册
	 * @see UserSmsLog#TYPE1 登录
	 * @see UserSmsLog#TYPE2 找回密码
	 * @see UserSmsLog#TYPE3 修改密码
	 * @see UserSmsLog#TYPE4 更换手机
	 * @see UserSmsLog#TYPE5 渝商-我要咨询
	 */
	@ResponseBody
	@RequestMapping(value = {"getCaptcha"}, method = {RequestMethod.POST, RequestMethod.GET})
	public Response<Void> getCaptcha(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String phone, 
			@RequestParam Byte type) {
		String typeStr = (type != null && UserSmsLog.allTypes.containsKey(type)) ? UserSmsLog.allTypes.get(type) : "未知";
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, phone=%s, type=%d[%s]", appId, sessionId, phone, type, typeStr);
		logger.debug("===================== 调用获取验证码接口 =====================>" + parameters);
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(phone) || "未知".equals(typeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取验证码接口异常：" + response + parameters);
			return response;
		}
		//查询 app_info
		Response<AppInfo> appResponse = appInfoService.get(appId);
		if (appResponse.getCode() < 0 || appResponse.getData() == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc());
			logger.error("调用获取验证码接口异常：" + response + parameters);
			return response;
		}
		
		//查询 app 私钥
		String privateKey = appResponse.getData().getRequestSignKey();
		//加密验证
		response = RequestVerify.verify(request.getParameterMap(), privateKey);
		if (response.getCode() < Response.SUCCESS) {
			return response;
		}
		response = userSmsLogService.sendCaptcha(appId, sessionId, phone, type);
		logger.debug("调用获取验证码接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取评论数</p>
	 * @author Tangtao on 2016年7月14日
	 * @param request
	 * @param appId
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getReplyCount"}, method = {RequestMethod.POST})
	public Response<Integer> getReplyCount(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType) {
		String sourceTypeStr = (sourceType != null && UserInfoReply.allSourceTypes.containsKey(sourceType)) ? UserInfoReply.allSourceTypes.get(sourceType) : "未知";
		String parameters = String.format("\n<请求参数：appId=%d, sourceId=%s, sourceType=%d[%s]", appId, sourceId, sourceType, sourceTypeStr);
		logger.debug("===================== 调用获取评论数接口 =====================>" + parameters);
		Response<Integer> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || sourceId == null || "未知".equals(sourceTypeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取评论数接口异常：" + response + parameters);
			return response;
		}
		response = userInfoReplyService.getReplyCount(appId, sourceId, sourceType);	
		logger.debug("调用获取评论数接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 增加浏览量</p>
	 * @author Tangtao on 2016年6月20日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"increaseViewCount"}, method = {RequestMethod.POST})
	public Response<Boolean> increaseViewCount(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType) {
		String sourceTypeStr = (sourceType != null && BusinessType.allSourceTypes.containsKey(sourceType)) ? BusinessType.allSourceTypes.get(sourceType) : "未知";
		logger.debug("===================== 调用增加浏览量接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, sourceId=%d, sourceType=%s[%s]", appId, sessionId, token, sourceId, sourceType, sourceTypeStr));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || sourceId == null || "未知".equals(sourceTypeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用增加浏览量接口异常：" + response);
			return response;
		}
		if (BusinessType.SOURCE_TYPE_1.equals(sourceType)) {	//新闻
			response = infoClassifyService.increaseViewCount(appId, sourceId);
			//保存用户浏览记录
			Response<InfoClassify> infoClassifyResponse = infoClassifyService.get(sourceId);
			if (infoClassifyResponse.getCode() < 0 || infoClassifyResponse.getData() == null) {
				response.setCode(ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getCode());
				response.setMessage(ErrorCodes.InfomationErrorEnum.INFO_CLASSIFY_NOT_EXIST.getDesc());
				logger.error("调用增加浏览量接口异常：" + response);
				return response;
			}
			InfoClassify infoClassify = infoClassifyResponse.getData();
			UserViewRecode userViewRecode = new UserViewRecode();
			userViewRecode.setAppId(appId);
			userViewRecode.setCreateTime(Dates.now());
			userViewRecode.setIpAddr(RequestUtil.getRequestIpAddr(request));
			userViewRecode.setSessionCode(sessionId);
			userViewRecode.setSourceId(sourceId);
			userViewRecode.setInformationId(infoClassify.getInformationId());
			userViewRecode.setSourceType(UserViewRecode.SOURCETYPE0);
			UserSession userSession = userSessionService.get(sessionId, token).getData();
			userViewRecode.setUserId(null != userSession ? userSession.getUserId() : null);
			userViewRecodeService.save(userViewRecode);
		}
		logger.debug("调用增加浏览量接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 是否已收藏</p>
	 * @author Tangtao on 2016年5月31日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"isCollected"}, method = {RequestMethod.POST})
	public Response<Boolean> isCollected(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType) {
		String sourceTypeStr = (sourceType != null && UserFavorite.allSourceTypes.containsKey(sourceType)) ? UserFavorite.allSourceTypes.get(sourceType) : "未知";
		logger.debug("===================== 调用是否已收藏接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, sourceId=%d, sourceType=%d[%s]", appId, sessionId, token, sourceId, sourceType, sourceTypeStr));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || sourceId == null || "未知".equals(sourceTypeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用是否已收藏接口异常：" + response);
			return response;
		}
		response = userFavoriteService.isCollected(appId, sessionId, token, sourceId, sourceType);
		logger.debug("调用是否已收藏接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 是否已点赞</p>
	 * @author Tangtao on 2016年5月31日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"isPraised"}, method = {RequestMethod.POST})
	public Response<Boolean> isPraised(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Byte type, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType) {
		String sourceTypeStr = (sourceType != null && UserFavorite.allSourceTypes.containsKey(sourceType)) ? UserFavorite.allSourceTypes.get(sourceType) : "未知";
		logger.debug("===================== 调用是否已点赞接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, sourceId=%d, sourceType=%d[%s]", appId, sessionId, token, sourceId, sourceType, sourceTypeStr));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || sourceId == null || "未知".equals(sourceTypeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用是否已点赞接口异常：" + response);
			return response;
		}
		response = userPraiseService.isPraised(appId, sessionId, token, sourceId, sourceType);
		logger.debug("调用是否已点赞接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 反馈列表</p>
	 * @author Tangtao on 2016年5月19日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"listFeedBack"}, method = {RequestMethod.POST})
	public Response<CommonListResult<FeedbackData>> listFeedBack(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			Long lastId) {
		logger.debug("===================== 调用我的反馈结果接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, lastId=%s", appId, sessionId, token, lastId));
		Response<CommonListResult<FeedbackData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用我的反馈结果接口异常：" + response);
			return response;
		}
		response = userFeedbackService.queryForScrollPage(appId, sessionId, token, lastId);
		logger.debug("调用我的反馈结果接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 登录</p>
	 * @author Tangtao on 2016年4月27日
	 * @param request 
	 * @param appId 客户端 id
	 * @param sessionId 会话 id
	 * @param loginName 用户名
	 * @param pwd 已加密的密码
	 * @param place 所处位置
	 * @param lat 纬度
	 * @param lng 经度
	 * @param openId 开放平台openid
	 * @param type 第三方登陆类型{0:微信,1:微博,2:QQ}
	 * @param nickName 第三方平台昵称
	 * @param imgUrl 第三方平台头像地址
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"login"}, method = {RequestMethod.POST})
	public Response<LoginResult> login(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String loginName, 
			String pwd, 
			String place, 
			String lat, 
			String lng, 
			String openId, 
			Byte type, 
			String nickName, 
			String imgUrl) {
		String typeStr = type == null ? "" : UserOtherAccount.allTypes.containsKey(type) ? UserOtherAccount.allTypes.get(type) : "未知";
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, loginName=%s, pwd=%s, place=%s, lat=%s, lng=%s, openId=%s, type=%d[%s], nickName=%s, imgUrl=%s", 
				appId, sessionId, loginName, pwd, place, lat, lng, openId, type, typeStr, nickName, imgUrl);
		logger.debug("===================== 调用登录接口 =====================>" + parameters);
		Response<LoginResult> response = Response.newInstance();
		//检查参数的必要性（登录时，位置信息不再是必要参数 By Tangtao 2017-01-25）
		if (appId == null || (type != null && !UserOtherAccount.allTypes.containsKey(type))) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用登录接口异常：" + response + parameters);
			return response;
		}
		
		//查询 app_info
		Response<AppInfo> appResponse = appInfoService.get(appId);
		if (appResponse.getCode() < 0 || appResponse.getData() == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc());
			logger.error("调用登录接口异常：" + response + parameters);
			return response;
		}
		//判断是否需要加密验证
//		if (AppInfo.IS_REQUEST_SIGN_1.equals(appResponse.getData().getIsRequestSign())) {	//需要加密验证
//			Response<Void> signResponse = RequestVerify.verify(request.getParameterMap(), appResponse.getData().getRequestSignKey());
//			if (signResponse.getCode() < Response.SUCCESS) {
//				response.setCode(signResponse.getCode());
//				response.setMessage(signResponse.getMessage());
//				return response;
//			}
//		}
		
		if (StringUtils.isBlank(sessionId)) {
			sessionId = loginName;
		}
		String ip = request.getRemoteAddr();
		response = userAccountService.login(appId, sessionId, loginName, pwd, place, lat, lng, openId, type, nickName, imgUrl, ip);
		logger.debug("调用登录接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 退出登录</p>
	 * @author Tangtao on 2016年5月5日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"logout"}, method = {RequestMethod.POST})
	public Response<Void> logout(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token) {
		logger.debug("===================== 调用退出接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s", appId, sessionId, token));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用退出接口异常：" + response);
			return response;
		}
		response = userAccountService.logout(appId, sessionId, token);
		logger.debug("调用退出接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 修改昵称</p>
	 * @author Tangtao on 2016年5月16日
	 * @param request
	 * @param appId
	 * @param token
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/modifyNickname"}, method = {RequestMethod.POST})
	public Response<Void> modifyNickname(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String token, 
			@RequestParam String nickname) {
		logger.debug("===================== 调用修改昵称接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s, nickname=%s", appId, token, nickname));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(token) || StringUtils.isBlank(nickname)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用修改昵称接口异常：" + response);
			return response;
		}
		response = userInfoService.modifyNickname(appId, token, nickname);
		logger.debug("调用修改昵称接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 修改性别</p>
	 * @author Tangtao on 2016年5月16日
	 * @param request
	 * @param appId
	 * @param token
	 * @param gender
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/modifyGender"}, method = {RequestMethod.POST})
	public Response<Void> modifyGender(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String token, 
			@RequestParam Byte gender) {
		logger.debug("===================== 调用修改性别接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s, gender=%s", appId, token, gender));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(token) || gender == null || !UserInfo.allSexs.containsKey(gender)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用修改性别接口异常：" + response);
			return response;
		}
		response = userInfoService.modifyGender(appId, token, gender);
		logger.debug("调用修改性别接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 修改邮箱</p>
	 * @author Tangtao on 2016年5月16日
	 * @param request
	 * @param appId
	 * @param token
	 * @param email
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/modifyEmail"}, method = {RequestMethod.POST})
	public Response<Void> modifyEmail(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String token, 
			@RequestParam String email) {
		logger.debug("===================== 调用修改邮箱接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s, email=%s", appId, token, email));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(token) || StringUtils.isBlank(email)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用修改邮箱接口异常：" + response);
			return response;
		}
		response = userInfoService.modifyEmail(appId, token, email);
		logger.debug("调用修改邮箱接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 修改头像</p>
	 * @author Tangtao on 2016年5月16日
	 * @param request
	 * @param appId
	 * @param token
	 * @param head
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/modifyHead"}, method = {RequestMethod.POST})
	public Response<Void> modifyHead(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String token, 
			@RequestParam String head) {
		logger.debug("===================== 调用修改脑壳接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s, head=%s", appId, token, head));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(token) || StringUtils.isBlank(head)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用修改脑壳接口异常：" + response);
			return response;
		}
		response = userInfoService.modifyHead(appId, token, head);
		logger.debug("调用修改脑壳接口结果：" + response);
		return response;
	}

	/**
	 * Title: 我收到的赞
	 * @author Tangtao on 2016年5月2日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"praises"}, method = {RequestMethod.POST})
	public Response<CommonListResult<PraisesData>> myPraises(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			Long lastId) {
		logger.debug("===================== 调用我收到的赞接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, lastId=%d", appId, sessionId, token, lastId));
		Response<CommonListResult<PraisesData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(token) || StringUtils.isBlank(sessionId)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用我收到的赞接口异常：" + response);
			return response;
		}
		response = userPraiseService.getMyPraisePage(appId, token, lastId);
		logger.debug("调用我收到的赞接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 注册</p>
	 * @author Tangtao on 2016年4月27日
	 * @param request
	 * @param appCode 客户端 code
	 * @param sessionId 会话 id
	 * @param loginName 登录名
	 * @param pwd 已加密的密码
	 * @param captcha 验证码
	 * @param actSource 来源
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"register"}, method = {RequestMethod.POST})
	public Response<Void> register(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String loginName, 
			@RequestParam String pwd, 
			@RequestParam String captcha, 
			String actSource) {
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, loginName=%s, pwd=%s, captcha=%s, actSource=%s", appId, sessionId, loginName, pwd, captcha, actSource);
		logger.debug("===================== 调用注册接口 =====================>" + parameters);
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(loginName) || StringUtils.isBlank(pwd) || StringUtils.isBlank(captcha)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用注册接口异常：" + response + parameters);
			return response;
		}
		response = userInfoService.register(appId, sessionId, loginName, pwd, captcha, actSource);
		logger.debug("调用注册接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 删除我的评论</p>
	 * @author Tangtao on 2016年6月2日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param userInfoReplyId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/removeComment"}, method = {RequestMethod.POST})
	public Response<Void> removeComment(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam("replyId") Long userInfoReplyId) {
		logger.debug("===================== 调用删除我的评论接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, id=%d", appId, sessionId, token, userInfoReplyId));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || userInfoReplyId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用删除我的评论异常：" + response);
			return response;
		}
		response = userInfoReplyService.remove(appId, sessionId, token, userInfoReplyId);
		logger.debug("调用删除我的评论结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 删除收藏</p>
	 * @author Tangtao on 2016年5月16日
	 * @param request
	 * @param appId
	 * @param token
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/removeFavorites"}, method = {RequestMethod.POST})
	public Response<Void> removeFavorites(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam String ids) {
		logger.debug("===================== 调用删除收藏接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, ids=%s", appId, sessionId, token, ids));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(ids)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用删除收藏接口异常：" + response);
			return response;
		}
		List<Long> idList = Lists.newArrayList();
		for (String id : ids.split(",")) {
			idList.add(NumberUtils.toLong(id.trim()));
		}
		response = userInfoService.removeFavorites(appId, sessionId, token, idList);
		logger.debug("调用删除收藏接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 保存留言</p>
	 * @author Tangtao on 2016年5月19日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param title 标题
	 * @param content 留言内容
	 * @param images 逗号分隔的图片地址
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"saveFeedBack"}, method = {RequestMethod.POST})
	public Response<Void> saveFeedBack(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			String title, 
			@RequestParam String content, 
			String images) {
		logger.debug("===================== 调用保存留言接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, title=%s, content=%s, images=%s", appId, sessionId, token, title, content, images));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(content)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用保存留言接口异常：" + response);
			return response;
		}
		//检查标题和内容长度
		if (title.length() > 100 || content.length() > 500) {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("标题或内容过长");
			logger.error("调用保存留言接口异常：" + response);
			return response;
		}
		response = userFeedbackService.add(appId, sessionId, token, title, content, images);
		logger.debug("调用保存留言接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 分享成功日志记录</p>
	 * @author Tangtao on 2016年6月8日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param platform
	 * @param sourceId
	 * @param sourceType
	 * @param place
	 * @param lat
	 * @param lng
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shareLog"}, method = {RequestMethod.POST})
	public Response<Boolean> shareLog(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Byte platform, 
			@RequestParam Long sourceId, 
			@RequestParam Byte sourceType, 
			String place, 
			String lat, 
			String lng) {
		logger.debug("===================== 调用分享成功日志记录接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, platform=%d[%s], sourceId=%d, sourceType=%d[%s], place=%s, lat=%s, lng=%s", 
						appId, sessionId, token, platform, UserShareHistory.allSharePlateforms.get(platform), sourceId, sourceType, UserShareHistory.allSourceTypes.get(sourceType), place, lat, lng));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || platform == null || !UserShareHistory.allSharePlateforms.containsKey(platform) || sourceId == null || !UserShareHistory.allSourceTypes.containsKey(sourceType)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用分享成功日志记录接口异常：" + response);
			return response;
		}
		response = userShareHistoryService.add(appId, sessionId, token, platform, sourceId, sourceType, place, lat, lng);
		logger.debug("调用分享成功日志记录接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 随手拍首页/我的随手拍/随手拍详情</p>
	 * @author Tangtao on 2016年6月8日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type 类型{1: 随手拍首页, 2: 我的随手拍, 3: 详情}
	 * @param shootInfoId
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shootInfo"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ShootInfoData>> shootInfo(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Byte type, 
			Long shootInfoId, 
			Long lastId) {
		String[] typeArray = {"首页", "我的", "详情"};
		String typeStr = type > 0 && type <= typeArray.length ? typeArray[type - 1] : "未知";
		logger.debug("===================== 调用随手拍首页/我的随手拍/随手拍详情接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, type=%d[%s], shootInfoId=%d, lastId=%d", appId, sessionId, token, type, typeStr, shootInfoId, lastId));
		Response<CommonListResult<ShootInfoData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || type == null || type < 1 || type > typeArray.length) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用随手拍首页/我的随手拍/随手拍详情接口异常：" + response);
			return response;
		}
		response = shootInfoService.queryForScrollPage(appId, sessionId, token, type, shootInfoId, lastId);
		logger.debug("调用随手拍首页/我的随手拍/随手拍详情接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 删除随手拍</p>
	 * @author Tangtao on 2016年6月8日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shootInfoRemove"}, method = {RequestMethod.POST})
	public Response<Boolean> shootInfoRemove(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			@RequestParam Long id) {
		logger.debug("===================== 调用随手拍删除接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, id=%d", appId, sessionId, token, id));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || id == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用随手拍删除接口异常：" + response);
			return response;
		}
		response = shootInfoService.remove(appId, sessionId, token, id);
		logger.debug("调用随手拍删除接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 发布随手拍</p>
	 * @author Tangtao on 2016年6月8日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param content
	 * @param shootType
	 * @param imgs
	 * @param imgDescs
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shootInfoSave"}, method = {RequestMethod.POST})
	public Response<Boolean> shootInfoSave(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			String place,
			String lat,
			String lng,
			String content,
			@RequestParam Byte shootType,
			@RequestParam String imgs,
			@RequestParam String imgDescs) {
		String shootTypeStr = ShootInfo.allShootTypes.containsKey(shootType) ? ShootInfo.allShootTypes.get(shootType) : "未知";
		logger.debug("===================== 调用随手拍保存接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, place=%s, lat=%s, lng=%s, content=%s, shootType=%d[%s], imgs.length=%d, imgDescs=%s, imgDescs.length=%d", 
						appId, sessionId, token, place, lat, lng, content, shootType, shootTypeStr, imgs.split(",").length, imgDescs, imgDescs.split(",").length));
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || StringUtils.isBlank(imgs)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用随手拍保存接口异常：" + response);
			return response;
		}
		response = shootInfoService.add(appId, sessionId, token, place, lat, lng, content, shootType, imgs, imgDescs);
		logger.debug("调用随手拍保存接口结果：" + response);
		return response;
	}

	/**
	 * Title: 修改密码
	 * @author Tangtao on 2016年5月1日
	 * @param request
	 * @param appId
	 * @param token
	 * @param pwd
	 * @param newPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"updatePwd"}, method = {RequestMethod.POST})
	public Response<Void> updatePwd(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String token, 
			@RequestParam String pwd, 
			@RequestParam String newPwd) {
		logger.debug("===================== 调用修改密码接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s, pwd=%s, newPwd=%s", appId, token, pwd, newPwd));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(token) || StringUtils.isBlank(newPwd) || StringUtils.isBlank(pwd)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用修改密码接口异常：" + response);
			return response;
		}
		response = userInfoService.updatePwd(appId, token, pwd, newPwd);
		logger.debug("调用修改密码接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取未读消息数</p>
	 * @author Tangtao on 2016年6月16日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/unreadCount"}, method = {RequestMethod.POST})
	public Response<Integer> unreadCount(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Byte type) {
		String typeStr = MessageUnreadCount.allTypes.containsKey(type) ? MessageUnreadCount.allTypes.get(type) : "未知";
		logger.debug("===================== 调用获取未读消息数接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, type=%d[%s]", appId, sessionId, token, type, typeStr));
		Response<Integer> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || !MessageUnreadCount.allTypes.containsKey(type)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取未读消息数接口：" + response);
			return response;
		}
		response = messageUnreadCountService.getCount(appId, sessionId, token, type);
		logger.debug("调用获取未读消息数接口：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 清空未读消息数</p>
	 * @author Tangtao on 2016年6月16日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/unreadCountClear"}, method = {RequestMethod.POST})
	public Response<Void> unreadCountClear(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Byte type) {
		String typeStr = MessageUnreadCount.allTypes.containsKey(type) ? MessageUnreadCount.allTypes.get(type) : "未知";
		logger.debug("===================== 调用清空未读消息数接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, type=%d[%s]", appId, sessionId, token, type, typeStr));
		Response<Void> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || !MessageUnreadCount.allTypes.containsKey(type)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用清空未读消息数接口：" + response);
			return response;
		}
		response = messageUnreadCountService.clear(appId, sessionId, token, type);
		logger.debug("调用清空未读消息数接口：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 上传图片</p>
	 * @author Tangtao on 2016年5月16日
	 * @param request
	 * @param appId
	 * @param image
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/uploadImg"}, method = {RequestMethod.POST})
	public Response<UploadResult> uploadImg(HttpServletRequest request, @RequestParam(required = false) Long appId, @RequestParam("pic") String image) {
		logger.debug("===================== 调用上传图片接口 =====================>"
				+ "\n<请求参数：appId=%d, pic.length = " + image.length());
		Response<UploadResult> response = Response.newInstance();
		//检查参数的必要性
		appId = appId == null ? 28L : appId;	//FIXME 暂时这样写，避免APP强制升级
//		if (appId == null) {
//			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
//			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
//			logger.error("调用上传图片接口异常：" + response);
//			return response;
//		}
		response = userInfoService.uploadImg(image, appId);
		logger.debug("调用上传图片接口结果：" + response);
		return response;
	}

	/**
	 * Title:用户消息通知数量
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月12日
	 * @param request
	 * @param appId
	 * @param token
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/messageNum"}, method = {RequestMethod.POST})
	public Response<Integer> userMessageNum(HttpServletRequest request,
	        @RequestParam Long appId,
	        @RequestParam String token,
	        @RequestParam Byte type){
	    
	    Response<Integer> rs = Response.newInstance();
	    if(appId == null || StringUtils.isBlank(token) || type == null){
	        rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
	        rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
	        return rs;
	    }
	    rs = userMessageNumService.getNum(appId, token, type);
	    return rs;
	}

	/**
	 * Title:设置用户消息数量
	 * <p>Description:清空用户消息数量，设置用户消息数量</p>
	 * @author DeweiLi on 2016年5月12日
	 * @param request
	 * @param appId
	 * @param token
	 * @param type
	 * @param num 为null或0的时候表示删除这条数据，也是清零的一种方式，其他情况下在原来的数量上做增加减少
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"my/modifyMessageNum"}, method = {RequestMethod.POST})
	public Response<Void> userMessageNumZero(HttpServletRequest request,
	        @RequestParam Long appId,
	        @RequestParam String token,
	        @RequestParam Byte type,
	        @RequestParam(required = false, defaultValue = "0") Integer num){
	    
	    Response<Void> rs = Response.newInstance();
	    if(appId == null || StringUtils.isBlank(token) || type == null){
	        rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
	        rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
	        return rs;
	    }
	    rs = userMessageNumService.modifyNum(appId, token, type, num);
	    return rs;
	}

	/**
	 * <p>Description: 用户访问</p>
	 * @author Tangtao on 2016年5月5日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param phoneCode
	 * @param place 所处位置
	 * @param lat 纬度
	 * @param lng 经度
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"userVisit"}, method = {RequestMethod.POST})
	public Response<VisitResult> userVisit(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			@RequestParam String phoneCode, 
			String place, 
			String lat, 
			String lng) {
		logger.debug("===================== 调用用户访问接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, phoneCode=%s, place=%s, lat=%s, lng=%s", appId, sessionId, token, phoneCode, place, lat, lng));
		Response<VisitResult> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用用户访问接口异常：" + response);
			return response;
		}
		
		//查询 app_info
		Response<AppInfo> appResponse = appInfoService.get(appId);
		if (appResponse.getCode() < 0 || appResponse.getData() == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc());
			logger.error("调用用户访问接口异常：" + response);
			return response;
		}
		//判断是否需要加密验证
		if (AppInfo.IS_REQUEST_SIGN_1.equals(appResponse.getData().getIsRequestSign())) {	//需要加密验证
			Response<Void> signResponse = RequestVerify.verify(request.getParameterMap(), appResponse.getData().getRequestSignKey());
			if (signResponse.getCode() < Response.SUCCESS) {
				response.setCode(signResponse.getCode());
				response.setMessage(signResponse.getMessage());
				return response;
			}
		}
		
		String userAgent = request.getHeader("user-agent");
		response = userInfoService.visit(appId, sessionId, token, phoneCode, place, lat, lng, userAgent);
		logger.debug("调用用户访问接口结果：" + response);
		return response;
	}
	
}