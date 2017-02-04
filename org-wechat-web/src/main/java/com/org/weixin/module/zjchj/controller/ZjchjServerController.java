/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.zjchj.controller;

import java.util.Date;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.dao.support.PageInfo;

import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.org.weixin.CommonController;
import com.org.weixin.module.jywth.common.MD5Util;
import com.org.weixin.module.zjchj.constant.ZjchjConstants;
import com.org.weixin.module.zjchj.domain.BillInfo;
import com.org.weixin.module.zjchj.domain.SerialidResult;
import com.org.weixin.module.zjchj.domain.ZjchjAward;
import com.org.weixin.module.zjchj.domain.ZjchjBackUser;
import com.org.weixin.module.zjchj.domain.ZjchjBillInfo;
import com.org.weixin.module.zjchj.domain.ZjchjDrawResult;
import com.org.weixin.module.zjchj.domain.ZjchjUserAward;
import com.org.weixin.module.zjchj.dto.ZjchjAwardDto;
import com.org.weixin.module.zjchj.dto.ZjchjBillInfoDto;
import com.org.weixin.module.zjchj.manager.ZjchjAwardManager;
import com.org.weixin.module.zjchj.manager.ZjchjBackUserManager;
import com.org.weixin.module.zjchj.manager.ZjchjBillInfoManager;
import com.org.weixin.module.zjchj.manager.ZjchjUserAwardManager;
import com.org.weixin.module.zjchj.manager.ZjchjUserGoodsInfoManager;
import com.org.weixin.module.zjchj.manager.ZjchjUserVisitLogManager;
import com.org.weixin.module.zjchj.util.RequestVerify;
import com.org.weixin.system.domain.SysWechatUser;
import com.org.weixin.system.service.SysWechatUserService;
import com.org.weixin.util.JsonUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年9月26日
 */
@Controller
@RequestMapping({"zjchj_in"})
public class ZjchjServerController extends CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(ZjchjServerController.class);
	
	private static final String VIEW_STATISTICS = "/module/zjchj/pages/back/statistics";
	private static final String VIEW_LOGIN = "/module/zjchj/pages/back/login";
	private static final String VIEW_REWARD = "/module/zjchj/pages/back/reward";
	private static final String VIEW_ERROR = "/module/zjchj/pages/back/error";
	
	@Autowired
	private SysWechatUserService sysWechatUserService;
	@Autowired
	private ZjchjAwardManager zjchjAwardManager;
	@Autowired
	private ZjchjBackUserManager zjchjBackUserManager;
	@Autowired
	private ZjchjBillInfoManager zjchjBillInfoManager;
	@Autowired
	private ZjchjUserAwardManager zjchjUserAwardManager;
	@Autowired
	private ZjchjUserGoodsInfoManager zjchjUserGoodsInfoManager;
	@Autowired
	private ZjchjUserVisitLogManager zjchjUserVisitLogManager;
	
	/**
	 * <p>Description: 获取业务流水id</p>
	 * @author Tangtao on 2016年9月26日
	 * @param request
	 * @param openId
	 * @param billInfoStr
	 * @param sign
	 * @return
	 */
	@ResponseBody
	@RequestMapping({"order"})
	public Response<SerialidResult> getSerialid(HttpServletRequest request, String openId, @RequestParam(required = false, value = "billInfo") String billInfoStr, String sign) {
		logger.info("===================== 调用获取业务流水id接口 =====================>" + 
				String.format("\n<请求参数：openId=%s, billInfo=%s, sign=%s", openId, billInfoStr, sign));
		Response<SerialidResult> dataResponse = Response.newInstance();
		try {
			BillInfo billInfo = JsonUtil.parseObject(billInfoStr, BillInfo.class);
			//检查参数
			if (StringUtils.isBlank(openId) || billInfo == null || CollectionUtils.isEmpty(billInfo.getGoodsDetails())) {
				dataResponse.setCode(-1);
				dataResponse.setMessage("无效参数");
				logger.error("调用获取业务流水id接口异常：无效参数");
			}
			//判断是否已记录过该店铺该订单数据
			Map<String, Object> map = Maps.newHashMap();
			map.put("EQ_billSerialNumber", billInfo.getBillSerialNumber());
			map.put("EQ_shopName", billInfo.getShopEntityName());
			List<ZjchjBillInfo> zjchjBillInfos = zjchjBillInfoManager.query(map, null); 
			if (CollectionUtils.isNotEmpty(zjchjBillInfos)) {
				dataResponse.setCode(-1);
				dataResponse.setMessage("该小票已参加活动，不能重复扫码");
				logger.error("该小票已参加活动，不能重复扫码");
			}
			
			//保存业务数据
			String serialid = zjchjBillInfoManager.save(openId, billInfo);
			//返回
			if (!StringUtils.isBlank(serialid)) {
				SerialidResult data = new SerialidResult();
				data.setSerialid(serialid);
				dataResponse.setData(data);
				logger.info("调用获取业务流水id接口结果：" + dataResponse);
			} else {
				dataResponse.setCode(-1);
				dataResponse.setMessage("服务器繁忙");
				logger.error("调用获取业务流水id接口异常");
			}
		} catch (Exception e) {
			dataResponse.setCode(-1);
			dataResponse.setMessage("服务器繁忙");
			logger.error("调用获取业务流水id接口异常：" + e.getMessage());
		}
		return dataResponse;
	}
	
	/**
	 * <p>Description: 终极抽奖接口，返回3个中奖用户及奖品</p>
	 * @author Tangtao on 2016年10月24日
	 * @param request
	 * @param time
	 * @param sign
	 * @return
	 */
	@ResponseBody
	@RequestMapping({"zjcj"})
	public Response<List<ZjchjDrawResult>> zjcj(HttpServletRequest request, String time, String sign) {
		logger.info("===================== 调用终极抽奖接口 =====================>" + 
				String.format("\n<请求参数：time=%s, sign=%s", time, sign));
		Response<List<ZjchjDrawResult>> dataResponse = Response.newInstance();
		try {
			Map<String, String[]> map = request.getParameterMap();
			//加密验证
			Response<Void> response = RequestVerify.verify(map);
			if (response.getCode() < Response.SUCCESS) {
				dataResponse.setCode(response.getCode());
				dataResponse.setMessage(response.getMessage());
				logger.info("加密验证失败");
				return dataResponse;
			}
			
			List<ZjchjDrawResult> data = Lists.newArrayList();
			ZjchjDrawResult obj;
			//抽取满足条件的用户，3个
			List<Long> userIds = zjchjUserGoodsInfoManager.getExtremeWin();
			for (Long userId : userIds) {
				ZjchjAward award = zjchjAwardManager.draw(userId, ZjchjAward.LEVEL4);
				if (award != null && ZjchjAward.ISTRUEAWARD1.equals(award.getIsTrueAward())) {
					//查询用户信息
					SysWechatUser user = sysWechatUserService.get(userId);
					obj = new ZjchjDrawResult();
					obj.setAwardName(award.getName());
					obj.setHeadImg(user != null ? StringUtils.defaultString(user.getHeadimgurl(), "") : "");
					obj.setNickname(user != null ? StringUtils.defaultString(user.getNickname(), "") : "");
					data.add(obj);
				}
			}
			dataResponse.setData(data);
		} catch (Exception e) {
			dataResponse.setCode(-1);
			dataResponse.setMessage("服务器繁忙");
			logger.error("调用抽奖接口异常：" + e.getMessage());
		}
		return dataResponse;
	}
	
	/**
	 * <p>Description: 数据统计</p>
	 * @author Tangtao on 2016年9月26日
	 * @param request
	 * @param model
	 * @param bd
	 * @param ed
	 * @return
	 */
	@RequestMapping({"statistics"})
	public String statistics(HttpServletRequest request, Model model, String bd, String ed) {
		Date beginTime = null, endTime = null;
		if (StringUtils.isNotBlank(bd)) {
			model.addAttribute("bd", bd);
			beginTime = DateUtil.parse(bd + " 00:00:00", DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
		}
		if (StringUtils.isNotBlank(ed)) {
			model.addAttribute("ed", ed);
			endTime = DateUtil.parse(ed + " 23:59:59", DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
		}
		
		//页面整体点击量
		Long totalCount = zjchjUserVisitLogManager.getTotalCount();
		model.addAttribute("totalCount", totalCount);
		
		//参与用户量
		Long totalPeople = zjchjUserVisitLogManager.getTotalPeople();
		model.addAttribute("totalPeople", totalPeople);
		
		//点亮菜品用户量
		Long totalLightedPeople = zjchjUserGoodsInfoManager.getTotalPeople();
		model.addAttribute("totalLightedPeople", totalLightedPeople);
		
		//点亮菜品用户分布
		List<ZjchjAwardDto> distribution = zjchjUserGoodsInfoManager.getDistribution();
		model.addAttribute("distribution", distribution);
		
		//终极大奖统计
		String extremeFoodNumStr = memcachedClient.get(ZjchjConstants.EXTREME_FOOD_NUM);
		Integer extremeFoodNum = NumberUtils.toInt(extremeFoodNumStr, 20);
		Long ec = zjchjUserGoodsInfoManager.getCountByQuantity(extremeFoodNum);
		model.addAttribute("ec_0", ec);		//达到抽奖资格
		//中奖及核销情况
		PageInfo<ZjchjUserAward> pageInfo = new PageInfo<ZjchjUserAward>();
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_level", ZjchjAward.LEVEL4);
		map.put("EQ_isTrueAward", ZjchjUserAward.ISTRUEAWARD1);
		pageInfo = zjchjUserAwardManager.query(pageInfo, map);
		List<ZjchjUserAward> awardsEx = pageInfo.getPageResults();
		model.addAttribute("ec_1", CollectionUtils.isNotEmpty(awardsEx) ? awardsEx.size() : 0);	//已中奖
		Integer rewardCount = 0;
		for (ZjchjUserAward obj : awardsEx) {
			if (ZjchjUserAward.ISREWARD1.equals(obj.getIsReward())) {
				rewardCount++;
			}
		}
		model.addAttribute("ec_2", rewardCount);	//已核销
		
		//中奖统计
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("level", true);
		sortMap.put("name", true);
		List<ZjchjAwardDto> awards = zjchjAwardManager.queryData(beginTime, endTime, sortMap);
		model.addAttribute("awards", awards);
		model.addAttribute("levelMap", ZjchjAward.allLevels);
		
		//菜品排行
		List<ZjchjBillInfoDto> billInfoDtos = zjchjBillInfoManager.getStatistics(beginTime, endTime);
		model.addAttribute("billInfos", billInfoDtos);
		return VIEW_STATISTICS;
	}
	
	/**
	 * <p>Description: 登录页面</p>
	 * @author Tangtao on 2016年9月27日
	 * @param request
	 * @param fname
	 * @param password
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET}, value = {"login"})
	public String login(HttpServletRequest request, String fname, String password) {
		return VIEW_LOGIN;
	}
	
	/**
	 * <p>Description: 登录</p>
	 * @author Tangtao on 2016年9月27日
	 * @param request
	 * @param model
	 * @param fname
	 * @param password
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST}, value = {"login"})
	public String doLogin(HttpServletRequest request, Model model, String fname, String password) {
		ZjchjBackUser user = zjchjBackUserManager.login(fname, MD5Util.MD5(password).toLowerCase());
		if (user == null) {
			model.addAttribute("msg", "编号或密码错误");
			return VIEW_ERROR;
		}
		request.getSession().setAttribute("backUser", user);
		return VIEW_REWARD;
	}
	
	/**
	 * <p>Description: 核销查询</p>
	 * @author Tangtao on 2016年9月27日
	 * @param request
	 * @param model
	 * @param rewardPwd
	 * @return
	 */
	@RequestMapping(value = {"query"})
	public String query(HttpServletRequest request, Model model, String rewardPwd) {
		//根据核销码查询用户奖品
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_rewardPwd", rewardPwd);
		List<ZjchjUserAward> awards = zjchjUserAwardManager.query(map, null);
		if (CollectionUtils.isNotEmpty(awards)) {
			model.addAttribute("award", awards.get(0));	
			model.addAttribute("allLevels", ZjchjAward.allLevels);	
			model.addAttribute("allIsRewards", ZjchjUserAward.allIsRewards);	
			return VIEW_REWARD;
		}
		model.addAttribute("msg", "核销码不存在");	
		return VIEW_ERROR;
	}
	
	/**
	 * <p>Description: 核销</p>
	 * @author Tangtao on 2016年9月27日
	 * @param request
	 * @param model
	 * @param id
	 * @param rewardPwd
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST}, value = {"reward"})
	public String reward(HttpServletRequest request, Model model, Long id, String rewardPwd) {
		ZjchjBackUser user = (ZjchjBackUser) request.getSession().getAttribute("backUser");
		//核销
		zjchjUserAwardManager.reward(id, user.getId(), user.getUserName());
		return "forward:/zjchj_in/query.html";
	}
	
}