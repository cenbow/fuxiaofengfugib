/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.cloud.online.info.domain.InfoLable;
import com.cqliving.cloud.online.info.service.InfoLableService;
import com.cqliving.cloud.online.shop.domain.ShopCategory;
import com.cqliving.cloud.online.shop.domain.ShopImage;
import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.shop.dto.ShopInfoDto;
import com.cqliving.cloud.online.shop.service.ShopCategoryService;
import com.cqliving.cloud.online.shop.service.ShopImageService;
import com.cqliving.cloud.online.shop.service.ShopInfoService;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title: 商情
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年5月27日
 */
@Controller
@RequestMapping({"shop"})
public class ShopController {
	
	/** 商铺数据每页记录数 */
	private static final int SHOP_DATA_PAGE_SIZE = 10;
	
	private static final String VIEW_SHOP_DETAIL = "/shop/shop_detail";
	private static final String VIEW_SHOP_LIST = "/shop/shop_list";
	private static final String VIEW_SHOP_LIST_DATA = "/shop/shop_list_data";
	private static final String VIEW_DETAIL_SHARE = "/shop/shop_detail_share";
	private static final String VIEW_COMMENT_DATA = "/shop/shop_comment_data";
	private static final String VIEW_MY_POSITION = "/shop/my_position";
	
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private ConfigRegionService configRegionService;
	@Autowired
	private InfoLableService infoLableService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private ShopImageService shopImageService;
	@Autowired
	private ShopInfoService shopInfoService;
	@Autowired
	private UserInfoReplyService userInfoReplyService;
	
	/**
	 * <p>Description: 商铺详情</p>
	 * @author Tangtao on 2016年5月30日
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping({"detail/{id}"})
	public String detail(HttpServletRequest request, Model model, @PathVariable Long id) {
		//查询商铺
		Response<ShopInfo> shopResponse = shopInfoService.get(id);
		model.addAttribute("obj", shopResponse.getData());
		model.addAttribute("appId", shopResponse.getData().getAppId());
		//查询商铺标签
		if (shopResponse.getData().getInfoLabelId() != null) {
			Response<InfoLable> labelResponse = infoLableService.get(shopResponse.getData().getInfoLabelId());
			model.addAttribute("label", labelResponse.getData());
		}
		//查询商铺图片
		Response<List<ShopImage>> imagesrResponse = shopImageService.getByShop(id);
		model.addAttribute("shopImageCount", imagesrResponse.getData().size());
		//商铺图片地址
		List<String> imgList = Lists.newArrayList();
		for (ShopImage img : imagesrResponse.getData()) {
			imgList.add(img.getUrl());
		}
		model.addAttribute("shopImageUrls", StringUtils.join(imgList, ","));
		//获取APP信息
		request.setAttribute("appInfo", appInfoService.get(shopResponse.getData().getAppId()).getData());
		return VIEW_SHOP_DETAIL;
	}
	
	/**
	 * <p>Description: 商情分享详情</p>
	 * @author Tangtao on 2016年7月21日
	 * @param request
	 * @param model
	 * @param id
	 * @param openUrl
	 * @return
	 */
	@RequestMapping({"detail/share/{id}"})
	public String detailShare(HttpServletRequest request, Model model, @PathVariable Long id) {
		//查询商铺图片
		Response<List<ShopImage>> imagesrResponse = shopImageService.getByShop(id);
		model.addAttribute("shopImageCount", imagesrResponse.getData().size());
		//查询商情
		Response<ShopInfo> shopInfoResponse = shopInfoService.get(id);
		if (shopInfoResponse.getCode() != 0 || shopInfoResponse.getData() == null || !ShopInfo.STATUS3.equals(shopInfoResponse.getData().getStatus())) {
			return CommonController.DELETE_JSP;
		}
		ShopInfo shopInfo = shopInfoResponse.getData();
		model.addAttribute("obj", shopInfo);
		model.addAttribute("appId", shopInfo.getAppId());
		
		//查询商铺标签
		if (shopInfo.getInfoLabelId() != null) {
			Response<InfoLable> labelResponse = infoLableService.get(shopInfo.getInfoLabelId());
			model.addAttribute("label", labelResponse.getData());
		}
		
		//查询APP信息
		Response<AppInfo> appInfoResponse = appInfoService.get(shopInfo.getAppId());
		if (appInfoResponse.getCode() != 0 || appInfoResponse.getData() == null) {
			return CommonController.DELETE_JSP;
		}
		AppInfo appinfo = appInfoResponse.getData();
		model.addAttribute("appInfo", appinfo);
		model.addAttribute("openUrl", appConfigService.findByAppId(appinfo.getId()).getData().getDownLoadUrl());
		return VIEW_DETAIL_SHARE;
	}

	/**
	 * <p>Description: 商铺列表页面</p>
	 * @author Tangtao on 2016年5月28日
	 * @param request
	 * @param model
	 * @param appId
	 * @param shopTypeId
	 * @return
	 */
	@RequestMapping({"list/{appId}/{shopTypeId}"})
	public String listPage(HttpServletRequest request, Model model, @PathVariable Long appId, @PathVariable Long shopTypeId) {
		model.addAttribute("appId", appId);
		model.addAttribute("shopTypeId", shopTypeId);
		//查询百度地图Key
		Response<AppInfo> appInforResponse = appInfoService.get(appId);
		model.addAttribute("baiduLbsKey", appInforResponse.getData().getBaiduLbsKey());
		//查询区域
		Response<List<ConfigRegion>> regionResponse = configRegionService.getShopByAppAndType(appId, shopTypeId);
		model.addAttribute("regions", regionResponse.getData());
		//查询商铺分类
		Response<List<ShopCategory>> categoryrResponse = shopCategoryService.getByAppAndType(appId, shopTypeId);
		model.addAttribute("categories", categoryrResponse.getData());
		return VIEW_SHOP_LIST;
	}
	
	/**
	 * <p>Description: 加载数据</p>
	 * @author Tangtao on 2016年5月28日
	 * @param request
	 * @param model
	 * @param lat
	 * @param lng
	 * @param appId
	 * @param shopTypeId
	 * @param orderByColumn
	 * @param regionCode
	 * @param shopCategoryId
	 * @param lastBusinessValue
	 * @param lastShopId
	 * @return
	 */
	@RequestMapping({"loadData"})
	public String loadData(HttpServletRequest request, Model model, 
			@RequestParam double lat, 
			@RequestParam double lng, 
			@RequestParam("a") Long appId, 
			@RequestParam("t") Long shopTypeId, 
			@RequestParam("o") String orderByColumn, 
			@RequestParam("r") String regionCode, 
			@RequestParam("c") Long shopCategoryId, 
			@RequestParam(required = false, value = "n") String shopName, 
			@RequestParam(required = false, value = "lb") Integer lastBusinessValue, 
			@RequestParam(required = false, value = "li") Long lastShopId) {
		ScrollPage<ShopInfoDto> scrollPage = new ScrollPage<ShopInfoDto>();
		String columnName = "c".equals(orderByColumn) ? "collect_count" : "d".equals(orderByColumn) ? "distance" : "reply_count";
		scrollPage.addScrollPageOrder(new ScrollPageOrder("top_type", ScrollPage.DESC, lastBusinessValue == null ? null : ShopInfo.TOPTYPE0));
		scrollPage.addScrollPageOrder(new ScrollPageOrder(columnName, "d".equals(orderByColumn) ? ScrollPage.ASC : ScrollPage.DESC, lastBusinessValue));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastShopId));
		scrollPage.setPageSize(SHOP_DATA_PAGE_SIZE);
		Response<ScrollPage<ShopInfoDto>> response = shopInfoService.queryForScrollPage(scrollPage, lat, lng, appId, shopTypeId, regionCode, shopCategoryId, shopName);
		model.addAttribute("shops", response.getData().getPageResults());
		return VIEW_SHOP_LIST_DATA;
	}
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月21日
	 * @param request
	 * @param model
	 * @param shopInfoId
	 * @param lastReplyId
	 * @return
	 */
	@RequestMapping({"loadCommentData"})
	public String loadCommentData(HttpServletRequest request, Model model, 
			@RequestParam("i") Long shopInfoId, 
			@RequestParam(required = false, value = "l") Long lastReplyId) {
		ScrollPage<UserInfoReplyDto> scrollPage = new ScrollPage<UserInfoReplyDto>();
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastReplyId));
		scrollPage.setPageSize(SHOP_DATA_PAGE_SIZE);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_status", UserInfoReply.STATUS3);
		conditions.put("EQ_sourceType", UserInfoReply.SOURCETYPE3);
		conditions.put("EQ_sourceId", shopInfoId);
		Response<ScrollPage<UserInfoReplyDto>> response = userInfoReplyService.queryScrollPage(scrollPage, conditions, UserInfoReply.SOURCETYPE3);
		if (response.getCode() == 0 && CollectionUtils.isNotEmpty(response.getData().getPageResults())) {
			List<UserInfoReplyDto> list = response.getData().getPageResults();
			for (UserInfoReplyDto dto : list) {
				boolean isAnonymous = UserInfoReply.TYPE1.equals(dto.getType());
				dto.setCreateTimeStr(DateUtil.convertTimeToFormatHore1(dto.getCreateTime().getTime()));
				dto.setImgUrl(isAnonymous ? "" : dto.getImgUrl());
			}
			model.addAttribute("dataList", response.getData().getPageResults());
		}
		return VIEW_COMMENT_DATA;
	}
	
	/**
	 * <p>Description: 我的位置</p>
	 * @author Tangtao on 2016年7月28日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping({"myPosition/{appId}"})
	public String myPosition(HttpServletRequest request, Model model, @PathVariable Long appId) {
		//查询APP信息
		Response<AppInfo> appInfoResponse = appInfoService.get(appId);
		if (appInfoResponse.getCode() != 0 || appInfoResponse.getData() == null) {
			return CommonController.DELETE_JSP;
		}
		AppInfo appinfo = appInfoResponse.getData();
		model.addAttribute("appInfo", appinfo);
		return VIEW_MY_POSITION;
	}
	
}