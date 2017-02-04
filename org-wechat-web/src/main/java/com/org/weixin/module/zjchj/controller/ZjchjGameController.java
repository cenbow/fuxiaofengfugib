/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.zjchj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.org.common.Constants;
import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.CommonController;
import com.org.weixin.client.bean.base.user.User;
import com.org.weixin.client.net.LocalHttpClient;
import com.org.weixin.module.zjchj.constant.ZjchjConstants;
import com.org.weixin.module.zjchj.domain.ZjchjAward;
import com.org.weixin.module.zjchj.domain.ZjchjGoodsInfo;
import com.org.weixin.module.zjchj.domain.ZjchjUserAward;
import com.org.weixin.module.zjchj.domain.ZjchjUserGoodsInfo;
import com.org.weixin.module.zjchj.domain.ZjchjUserVisitLog;
import com.org.weixin.module.zjchj.dto.ZjchjShopInfoDto;
import com.org.weixin.module.zjchj.manager.ZjchjAwardManager;
import com.org.weixin.module.zjchj.manager.ZjchjGoodsInfoManager;
import com.org.weixin.module.zjchj.manager.ZjchjUserAwardManager;
import com.org.weixin.module.zjchj.manager.ZjchjUserGoodsInfoManager;
import com.org.weixin.module.zjchj.manager.ZjchjUserVisitLogManager;
import com.org.weixin.module.zjchj.util.RequestVerify;
import com.org.weixin.system.dto.AccInfoDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年9月26日
 */
@Controller
@RequestMapping({"zjchj/{accId}"})
public class ZjchjGameController extends CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(ZjchjGameController.class);
	
	private static final String VIEW_404 = "/module/zjchj/pages/404";
	private static final String VIEW_INDEX = "/module/zjchj/pages/index";
	private static final String VIEW_DETAIL = "/module/zjchj/pages/detail";
	private static final String VIEW_AWARD = "/module/zjchj/pages/award";
	private static final String VIEW_NEW = "/module/zjchj/pages/new";
	private static final String VIEW_SUBSCRIBE = "/module/zjchj/pages/subscribe";
	
	private static final Set<String> parmaSet = Sets.newHashSet();

	static {
		parmaSet.add("serialid");
		parmaSet.add("times");
		parmaSet.add("sign");
	}
	
	@Autowired
	private SpyMemcachedClient memcachedClient;
	@Autowired
	private ZjchjAwardManager zjchjAwardManager;
	@Autowired
	private ZjchjGoodsInfoManager zjchjGoodsInfoManager;
	@Autowired
	private ZjchjUserAwardManager zjchjUserAwardManager;
	@Autowired
	private ZjchjUserGoodsInfoManager zjchjUserGoodsInfoManager;
	@Autowired
	private ZjchjUserVisitLogManager zjchjUserVisitLogManager;
	
	/**
	 * <p>Description: 活动首页</p>
	 * @author Tangtao on 2016年9月26日
	 * @param request
	 * @param model
	 * @param serialid
	 * @param type 存在此参数，认为是分享过来的页面，只做展示，不处理业务
	 * @return
	 */
	@RequestMapping({"index"})
	public String index(HttpServletRequest request, Model model, @PathVariable Long accId, String serialid, String type) {
		logger.info("<===================== 访问【正佳吃货节】活动首页 =====================>" + 
				String.format("\n<请求参数：serialid=%s, type=%s, ip=%s>", serialid, type, request.getRemoteAddr()));
		if (StringUtils.isBlank(type)) {	//需要验证
			//获取需要加密验证的参数
			Map<String, String[]> map = request.getParameterMap();
			Map<String, String[]> validMap = Maps.newHashMap();
			for (String key : map.keySet()) {
				if (parmaSet.contains(key)) {
					validMap.put(key, map.get(key));
				}
			}
			//加密验证
			Response<Void> response = RequestVerify.verify(validMap);
			if (response.getCode() < Response.SUCCESS) {
				logger.info("加密验证失败");
				return VIEW_404;
			}
		}
		
		//设置分享，获取config
		setUserAndShare(request, accId);
		
		//获取各级福利点亮需要的菜品数
		String primaryFoodNumStr = memcachedClient.get(ZjchjConstants.PRIMARY_FOOD_NUM);
		String intermediateFoodNumStr = memcachedClient.get(ZjchjConstants.INTERMEDIATE_FOOD_NUM);
		String advancedFoodNumStr = memcachedClient.get(ZjchjConstants.ADVANCED_FOOD_NUM);
		String extremeFoodNumStr = memcachedClient.get(ZjchjConstants.EXTREME_FOOD_NUM);
		Integer primaryFoodNum = NumberUtils.toInt(primaryFoodNumStr, 3);
		Integer intermediateFoodNum = NumberUtils.toInt(intermediateFoodNumStr, 5);
		Integer advancedFoodNum = NumberUtils.toInt(advancedFoodNumStr, 7);
		Integer extremeFoodNum = NumberUtils.toInt(extremeFoodNumStr, 20);
		model.addAttribute("primaryFoodNum", primaryFoodNum);
		model.addAttribute("intermediateFoodNum", intermediateFoodNum);
		model.addAttribute("advancedFoodNum", advancedFoodNum);
		model.addAttribute("extremeFoodNum", extremeFoodNum);
		
		//根据业务流水id，绑定用户id
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		Long userId = sessionUser.getId();
//		Long userId = 9L;	//FIXME Tangtao
		boolean b1 = true;
		if ("invalid_serialid".equals(serialid) || "-1".equals(serialid)) {	//非法流水号或二维码已经被扫过
			logger.info("serialid=【" + serialid + "】，二维码已被扫过，不能重复参与");
			model.addAttribute("erweima_saoguola", "亲，二维码已经扫过了");	
		} else if (StringUtils.isBlank(type) && StringUtils.isNotBlank(serialid)) {	
			//查询用户已点亮的菜品
			Map<String, Object> map = Maps.newHashMap();
			map.put("EQ_userId", userId);
			Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
			sortMap.put("id", true);
			List<ZjchjUserGoodsInfo> userGoodsInfos = zjchjUserGoodsInfoManager.query(map, sortMap);
			Integer goodsSize = CollectionUtils.isEmpty(userGoodsInfos) ? 0 : userGoodsInfos.size();
			b1 = goodsSize < extremeFoodNum;	//未达到终极吃货条件
			//点亮菜品操作
			zjchjUserGoodsInfoManager.light(serialid, userId);
		} 
		
		//查询用户点亮的菜品
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_userId", userId);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("id", true);
		List<ZjchjUserGoodsInfo> userGoodsInfos = zjchjUserGoodsInfoManager.query(map, sortMap);
		model.addAttribute("goods", userGoodsInfos);
		Integer goodsSize = CollectionUtils.isEmpty(userGoodsInfos) ? 0 : userGoodsInfos.size();
		model.addAttribute("goodsSize", goodsSize);
		//未点亮菜品数
		Integer unlightSize = 40 - goodsSize;
		model.addAttribute("unlightGoodsSize", unlightSize);
		
		//记录访问日志
		ZjchjUserVisitLog visitLog = new ZjchjUserVisitLog();
		visitLog.setCreateTime(DateUtil.now());
		visitLog.setUserId(userId);
		zjchjUserVisitLogManager.save(visitLog);
		
		//关注逻辑判断
		Map<Long, AccInfoDto> accInfoMap = memcachedClient.get(Constants.Weixin.WEIXIN_ACC_MAP);
		AccInfoDto aid = accInfoMap.get(accId);
		String accessToken = aid.getAccessToken();
		String openId = sessionUser.getOpenid();
		logger.info("<===========================\n"
				+ "accessToken=" + accessToken + "*******openId=" + openId + "\n"
						+ "==========================>");
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri("https://api.weixin.qq.com/cgi-bin/user/info")
				.addParameter("access_token", accessToken)
				.addParameter("openid", openId)
				.addParameter("lang","zh_CN")
				.build();
		User user = LocalHttpClient.executeJsonResult(httpUriRequest, User.class);
		logger.info(user.toString());
		boolean isSubscribe = user != null && user.getSubscribe() != null && user.getSubscribe().intValue() == 1;
		if (!isSubscribe) {
			return VIEW_SUBSCRIBE;
		}
		
		boolean b2 = goodsSize >= extremeFoodNum;	//达到终极吃货条件
		//如果点亮菜品从20以下达到20以上，跳转到抽奖提示页面
		if (!b1 && b2) {
			return VIEW_NEW;
		}
		return VIEW_INDEX;
	}
	
	/**
	 * <p>Description: 活动规则/爆款推荐/我的奖品页面</p>
	 * @author Tangtao on 2016年9月27日
	 * @param request
	 * @param model
	 * @param accId
	 * @param tab
	 * @return
	 */
	@RequestMapping({"detail/{tab}"})
	public String detail(HttpServletRequest request, Model model, @PathVariable Long accId, @PathVariable String tab) {
		//设置分享，获取config
		setUserAndShare(request, accId);
		
		//目的tab页
		model.addAttribute("tab", tab);
		
		//查询爆款菜品数据
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("shopSortKey", true);
		sortMap.put("id", true);
		List<ZjchjGoodsInfo> goodsInfos = zjchjGoodsInfoManager.query(map, sortMap);
		List<ZjchjShopInfoDto> shopInfoDtos = transfer(goodsInfos);
		model.addAttribute("shopInfoDtos", shopInfoDtos);
		
		//查询我的奖品
		map.clear();
		sortMap.clear();
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		Long userId = sessionUser.getId();
//		Long userId = 9L;	//FIXME Tangtao
		map.put("EQ_userId", userId);
		map.put("EQ_isTrueAward", ZjchjUserAward.ISTRUEAWARD1);
		sortMap.put("id", true);
		List<ZjchjUserAward> userAwards = zjchjUserAwardManager.query(map, sortMap);
		model.addAttribute("userAwards", userAwards);
		model.addAttribute("levelMap", ZjchjAward.allLevels);
		
		//获取兑奖期限
		String rewardExpiryDateStr = memcachedClient.get(ZjchjConstants.REWARD_EXPIRY_DATE);
		Date rewardExpiryDate = DateUtil.parse(rewardExpiryDateStr, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
		model.addAttribute("expiryDate", rewardExpiryDate);
		model.addAttribute("isExpired", DateUtil.now().after(rewardExpiryDate));
		
		//关注逻辑判断
		Map<Long, AccInfoDto> accInfoMap = memcachedClient.get(Constants.Weixin.WEIXIN_ACC_MAP);
		AccInfoDto aid = accInfoMap.get(accId);
		String accessToken = aid.getAccessToken();
		String openId = sessionUser.getOpenid();
		logger.info("<===========================\n"
				+ "accessToken=" + accessToken + "*******openId=" + openId + "\n"
						+ "==========================>");
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri("https://api.weixin.qq.com/cgi-bin/user/info")
				.addParameter("access_token", accessToken)
				.addParameter("openid", openId)
				.addParameter("lang","zh_CN")
				.build();
		User user = LocalHttpClient.executeJsonResult(httpUriRequest, User.class);
		logger.info(user.toString());
		boolean isSubscribe = user != null && user.getSubscribe() != null && user.getSubscribe().intValue() == 1;
		if (!isSubscribe) {
			return VIEW_SUBSCRIBE;
		}
		return VIEW_DETAIL;
	}
	
	/**
	 * <p>Description: 点击福利按钮</p>
	 * @author Tangtao on 2016年9月27日
	 * @param request
	 * @param model
	 * @param accId
	 * @param tab
	 * @return
	 */
	@RequestMapping({"fuli/{level}"})
	public String fuli(HttpServletRequest request, Model model, @PathVariable Long accId, @PathVariable Byte level) {
		//设置分享，获取config
		setUserAndShare(request, accId);
		
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		Long userId = sessionUser.getId();
//		Long userId = 9L;	//FIXME Tangtao
		
		//关注逻辑判断
		Map<Long, AccInfoDto> accInfoMap = memcachedClient.get(Constants.Weixin.WEIXIN_ACC_MAP);
		AccInfoDto aid = accInfoMap.get(accId);
		String accessToken = aid.getAccessToken();
		String openId = sessionUser.getOpenid();
		logger.info("<===========================\n"
				+ "accessToken=" + accessToken + "*******openId=" + openId + "\n"
						+ "==========================>");
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri("https://api.weixin.qq.com/cgi-bin/user/info")
				.addParameter("access_token", accessToken)
				.addParameter("openid", openId)
				.addParameter("lang","zh_CN")
				.build();
		User user = LocalHttpClient.executeJsonResult(httpUriRequest, User.class);
		logger.info(user.toString());
		boolean isSubscribe = user != null && user.getSubscribe() != null && user.getSubscribe().intValue() == 1;
		if (!isSubscribe) {
			return VIEW_SUBSCRIBE;
		}
		
		//判断是否已抽过奖品
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_userId", userId);
		map.put("EQ_level", level);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		List<ZjchjUserAward> userAwards = zjchjUserAwardManager.query(map, sortMap);
		if (CollectionUtils.isNotEmpty(userAwards)) {	//已抽过奖，去我的奖品
			return "forward:/zjchj/" + accId + "/detail/my.html";
		}
		
		//判断点亮菜品是否已打到要求
		map = Maps.newHashMap();
		map.put("EQ_userId", userId);
		sortMap = Maps.newLinkedHashMap();
		List<ZjchjUserGoodsInfo> userGoodsInfos = zjchjUserGoodsInfoManager.query(map, sortMap);
		Integer lightedGoods = CollectionUtils.isNotEmpty(userGoodsInfos) ? userGoodsInfos.size() : 0;
		//获取各级福利点亮需要的菜品数
		String primaryFoodNumStr = memcachedClient.get(ZjchjConstants.PRIMARY_FOOD_NUM);
		String intermediateFoodNumStr = memcachedClient.get(ZjchjConstants.INTERMEDIATE_FOOD_NUM);
		String advancedFoodNumStr = memcachedClient.get(ZjchjConstants.ADVANCED_FOOD_NUM);
		String extremeFoodNumStr = memcachedClient.get(ZjchjConstants.EXTREME_FOOD_NUM);
		Integer primaryFoodNum = NumberUtils.toInt(primaryFoodNumStr, 3);
		Integer intermediateFoodNum = NumberUtils.toInt(intermediateFoodNumStr, 5);
		Integer advancedFoodNum = NumberUtils.toInt(advancedFoodNumStr, 7);
		Integer extremeFoodNum = NumberUtils.toInt(extremeFoodNumStr, 20);
		boolean canDraw = true;	//是否可以抽奖
		if (ZjchjAward.LEVEL1.equals(level)) {	//点击初级吃货福利
			canDraw = lightedGoods >= primaryFoodNum;
		} else if (ZjchjAward.LEVEL2.equals(level)) { //点击中级吃货福利
			canDraw = lightedGoods >= intermediateFoodNum;
		} else if (ZjchjAward.LEVEL3.equals(level)) { //点击高级吃货福利
			canDraw = lightedGoods >= advancedFoodNum;
		} else if (ZjchjAward.LEVEL4.equals(level)) { //点击终极吃货福利
			canDraw = lightedGoods >= extremeFoodNum;
		} else {
			canDraw = false;
		}
		if (!canDraw) {	//未达到抽奖条件，去规则页面
			return "forward:/zjchj/" + accId + "/detail/rule.html";
		} else if (ZjchjAward.LEVEL4.equals(level)) {	//点击终极吃货福利且达到资格
			//去终极抽奖机会展示页面
			return VIEW_NEW;
		}
		
		//可以抽奖啦
		ZjchjAward award = zjchjAwardManager.draw(userId, level);
		if (award != null && ZjchjAward.ISTRUEAWARD1.equals(award.getIsTrueAward())) {	//中奖
			model.addAttribute("award", award);
		} 
		return VIEW_AWARD;
	}
	
	//***************************************** 私有方法 *****************************************

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年9月27日
	 * @param goodsInfos
	 * @return
	 */
	private List<ZjchjShopInfoDto> transfer(List<ZjchjGoodsInfo> goodsInfos) {
		List<ZjchjShopInfoDto> list = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(goodsInfos)) {
			ZjchjShopInfoDto previous = null;
			ZjchjShopInfoDto dto;
			List<ZjchjGoodsInfo> goodsList;
			for (ZjchjGoodsInfo goods : goodsInfos) {
				if (previous == null || !previous.getShopName().equals(goods.getShopName())) {
					goodsList = new ArrayList<ZjchjGoodsInfo>();
					goodsList.add(goods);
					dto = new ZjchjShopInfoDto();
					dto.setGoodsInfos(goodsList);
					dto.setId(goods.getId());
					dto.setShopAddr(goods.getShopAddr());
					dto.setShopLogo(goods.getShopLogo());
					dto.setShopName(goods.getShopName());
					dto.setShopSortKey(goods.getShopSortKey());
					list.add(dto);
					previous = dto;
				} else {	//同一店铺菜品
					List<ZjchjGoodsInfo> gl = previous.getGoodsInfos();
					gl.add(goods);
				} 
			}
		}
		return list;
	}
	
}