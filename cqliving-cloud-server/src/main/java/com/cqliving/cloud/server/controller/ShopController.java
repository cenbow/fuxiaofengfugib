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
import com.cqliving.cloud.common.ShopInfoUtil;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.ClientControlType;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.service.CommentAppConfigService;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.cloud.online.interfacc.dto.CommonKeyValueData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ShopInfoAddData;
import com.cqliving.cloud.online.interfacc.dto.ShopInfoData;
import com.cqliving.cloud.online.interfacc.dto.ShopKeyValueData;
import com.cqliving.cloud.online.shop.domain.ShopCategory;
import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.shop.domain.ShopType;
import com.cqliving.cloud.online.shop.dto.ShopInfoDto;
import com.cqliving.cloud.online.shop.service.ShopCategoryService;
import com.cqliving.cloud.online.shop.service.ShopInfoService;
import com.cqliving.cloud.online.shop.service.ShopTypeService;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title: 商情相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月15日
 */
@Controller
public class ShopController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	private CommentAppConfigService commentAppConfigService;
	@Autowired
	private ConfigRegionService configRegionService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private ShopInfoService shopInfoService;
	@Autowired
	private ShopTypeService shopTypeService;
	@Autowired
	private AppInfoService appInfoService;
	
	/**
	 * Title:调用商情上传-获取配置信息接口
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月21日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param shopTypeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shopAdd"}, method = RequestMethod.POST)
	public Response<Map<String, Object>> shopAdd(HttpServletRequest request, @RequestParam Long appId, String sessionId, String token, @RequestParam Long shopTypeId){
		logger.debug("===================== 调用商情上传-获取基本数据接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s", appId, sessionId, token));
		Response<Map<String, Object>> rs = Response.newInstance();
		//检查参数的必要性
		if (appId == null || shopTypeId == null) {
			rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商情上传-获取配置信息接口：" + rs);
			return rs;
		}
		ShopType st = shopTypeService.get(shopTypeId).getData();
		if(st == null){
			logger.error("调用商铺列表接口异常");
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("商情类型栏目配置有误");
			return rs;
		}
		Map<String, Object> map = Maps.newHashMap();
		AppInfo appInfo = appInfoService.get(appId).getData();
		if(appInfo == null){
			logger.error("调用商铺列表接口异常");
			rs.setCode(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode());
			rs.setMessage(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc());
			return rs;
		}
		map.put("regionName", appInfo.getLocation());
		//商情栏目集合
		List<ShopType> shopTypeList = shopTypeService.getByApp(appId).getData();
		//分类列表
		List<ShopCategory> shopCategoryList = shopCategoryService.getByAppAndType(appId, null).getData();
		//区域列表
		List<ConfigRegion> configRegionList = configRegionService.getShopByAppAndType(appId, null).getData();
		List<ShopInfoAddData> list = Lists.newArrayList();
		if(shopTypeList != null && shopTypeList.size() > 0){
			ShopInfoAddData shopInfoAddData = null;
			List<ShopKeyValueData> listMap = null;
			CommonKeyValueData keyValue = null;
			ShopKeyValueData shopKeyValueData = null;
			List<CommonKeyValueData> keyValueList = null;
			for(ShopType shopType : shopTypeList){
				shopInfoAddData = new ShopInfoAddData();
				shopInfoAddData.setId(shopType.getId());
				shopInfoAddData.setName(shopType.getName());
				//分类
				listMap = Lists.newArrayList();
				if(shopCategoryList != null && shopCategoryList.size() > 0){
					for(ShopCategory shopCategory : shopCategoryList){
						if(shopCategory.getTypeId().equals(shopType.getId())){
							shopKeyValueData = new ShopKeyValueData();
							shopKeyValueData.setCode(shopCategory.getId());
							shopKeyValueData.setValue(shopCategory.getName());
							listMap.add(shopKeyValueData);
						}
					}
				}
				shopInfoAddData.setTypeList(listMap);
				//区域
				keyValueList = Lists.newArrayList();
				if(configRegionList != null && configRegionList.size() > 0){
					for(ConfigRegion configRegion : configRegionList){
						if(configRegion.getShopTypeId().equals(shopType.getId())){
							keyValue = new CommonKeyValueData();
							keyValue.setCode(configRegion.getCode());
							keyValue.setValue(configRegion.getName());
							keyValueList.add(keyValue);
						}
					}
				}
				shopInfoAddData.setRegionList(keyValueList);
				list.add(shopInfoAddData);
			}
			
		}
		map.put("dataList", list);
		rs.setData(map);
		logger.debug("调用商情上传-获取基本数据接口：" + rs);
		return rs;
	}

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月19日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param appColumnsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shopCategory"}, method = {RequestMethod.POST})
	public Response<CommonListResult<Map<Long, String>>> shopCategory(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Long appColumnsId) {
		logger.debug("===================== 调用商情分类列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, appColumnsId=%d", 
						appId, sessionId, token, appColumnsId));
		Response<CommonListResult<Map<Long, String>>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || appColumnsId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商情分类列表接口异常：" + response);
			return response;
		}
		//根据栏目获取商情类型
		Response<ShopType> resp = shopTypeService.getByColumn(appColumnsId);
		if (resp.getCode() < 0 || resp.getData() == null) {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("商情类型栏目配置有误");
			logger.error("调用商铺列表接口异常：" + response);
			return response;
		}
		Long shopTypeId = resp.getData().getId();
		
		//查询数据
		Response<List<ShopCategory>> categoryrResponse = shopCategoryService.getByAppAndType(appId, shopTypeId);
		List<ShopCategory> list = categoryrResponse.getData();
		//组装数据
		CommonListResult<Map<Long, String>> data = new CommonListResult<Map<Long, String>>();
		List<Map<Long, String>> dataList = Lists.newArrayList();
		Map<Long, String> map;
		for (ShopCategory obj : list) {
			map = Maps.newHashMap();
			map.put(obj.getId(), obj.getName());
			dataList.add(map);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用商情分类列表接口结果：" + response);
		return response;
	}

	/**
	 * Title:调用商情上传-获取配置信息接口
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月21日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param appColumnsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shopConfig"}, method = RequestMethod.POST)
	public Response<Map<String, Object>> shopConfig(HttpServletRequest request, @RequestParam Long appId, String sessionId, String token, @RequestParam Long appColumnsId){
		logger.debug("===================== 调用商情上传-获取配置信息接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, appColumnsId=%d", appId, sessionId, token, appColumnsId));
		Response<Map<String, Object>> rs = Response.newInstance();
		//检查参数的必要性
		if (appId == null || appColumnsId == null) {
			rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商情上传-获取配置信息接口异常：" + rs);
			return rs;
		}
		Map<String, Object> map = Maps.newHashMap();
		ShopType shopType = shopTypeService.getByColumn(appColumnsId).getData();
		if(shopType == null){
			logger.error("调用商铺列表接口异常");
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("商情类型栏目配置有误");
			return rs;
		}
		map.put("defaultId", shopType.getId());
		map.put("defaultName", shopType.getName());
		
		Byte allowAdd = commentAppConfigService.getConfigValueByName(appId, CommentConfig.SHOP_ALLOW_USER_ADD, BusinessType.SOURCE_TYPE_3).getData();
		Byte isLogin = commentAppConfigService.getConfigValueByName(appId, CommentConfig.SHOP_USER_ADD_NEED_LOGIN, BusinessType.SOURCE_TYPE_3).getData();
		map.put("allowAdd", allowAdd);
		map.put("isLogin", isLogin);
		rs.setData(map);
		logger.debug("调用商情上传-获取配置信息接口：" + rs);
		return rs;
	}
	
	/**
	 * <p>Description: 获取首页商情</p>
	 * @author Tangtao on 2016年12月7日
	 * @param request
	 * @param appId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shopIdx"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ShopInfoData>> shopIdx(HttpServletRequest request, @RequestParam Long appId) {
		String parameters = String.format("\n<请求参数：appId=%d", appId);
		logger.debug("===================== 调用获取首页商情接口 =====================>" + parameters);
		Response<CommonListResult<ShopInfoData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取首页商情接口异常：" + response + parameters);
			return response;
		}
		Response<List<ShopInfoDto>> dataResponse = shopInfoService.getShopRecommendIndex(appId);
		List<ShopInfoDto> list = dataResponse.getData();
		
		//返回数据
		CommonListResult<ShopInfoData> data = new CommonListResult<ShopInfoData>();
		List<ShopInfoData> dataList = Lists.newArrayList();
		if (dataResponse.getCode() == 0) {
			ShopInfoData obj;
			//获取是否允许评论配置
			Byte canComment = commentAppConfigService.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_3).getData();
			for (ShopInfoDto dto : list) {
				obj = new ShopInfoData();
				obj.setAddress(dto.getAddress());
				obj.setCollectCount(dto.getCollectCount());
				obj.setCommentType(NumberUtils.toByte(CommentConfig.VALUE0.equals(canComment) ? "1" : "0"));
				obj.setCoverImg(dto.getImageUrl());
				obj.setDetailViewType(ClientControlType.DETAIL_VIEW_TYPE_2);
//				obj.setDistance(0L);
				obj.setId(dto.getId());
//				obj.setLabel("");
				obj.setName(dto.getName());
				obj.setPrice(dto.getPrice() == null ? 0 : dto.getPrice() / 100);
				obj.setReplyCount(dto.getReplyCount());
				obj.setShareUrl(ShopInfoUtil.getShareUrl(dto.getId()));	
				obj.setSourceType(BusinessType.SOURCE_TYPE_3);
				obj.setSynopsis(dto.getDescription());
				obj.setTitle(dto.getName());
				obj.setTopType(dto.getTopType());
				obj.setUrl(ShopInfoUtil.getRedirectUrl(dto.getId()));
				dataList.add(obj);
			}
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用获取首页商情接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 商铺列表</p>
	 * @author Tangtao on 2016年7月15日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lat
	 * @param lng
	 * @param appColumnsId
	 * @param order
	 * @param regionCode
	 * @param shopCategoryId
	 * @param shopName
	 * @param lastBusinessValue
	 * @param lastShopId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shopInfo"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ShopInfoData>> shopInfo(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			String lat, 
			String lng, 
			@RequestParam Long appColumnsId, 
			@RequestParam String order, 
			String regionCode, 
			Long shopCategoryId, 
			String shopName, 
			Integer lastBusinessValue, 
			Long lastShopId) {
		logger.debug("===================== 调用商铺列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, lat=%s, lng=%s, appColumnsId=%d, order=%s, regionCode=%s, shopCategoryId=%d, shopName=%s, lastBusinessValue=%d, lastShopId=%d", 
						appId, sessionId, token, lat, lng, appColumnsId, order, regionCode, shopCategoryId, shopName, lastBusinessValue, lastShopId));
		Response<CommonListResult<ShopInfoData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || appColumnsId == null || StringUtils.isBlank(order)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商铺列表接口异常：" + response);
			return response;
		}
		//根据栏目获取商情类型
		Response<ShopType> resp = shopTypeService.getByColumn(appColumnsId);
		if (resp.getCode() < 0 || resp.getData() == null) {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("商情类型栏目配置有误");
			logger.error("调用商铺列表接口异常：" + response);
			return response;
		}
		Long shopTypeId = resp.getData().getId();
		
		//查询数据，如没有经纬度又选了离我最近排序，则按热门火爆排序（By Tangtao 2017-01-24）
		if ((StringUtils.isBlank(lat) || StringUtils.isBlank(lng)) && "d".equals(order)) {
			order = "r";
		}
		
		ScrollPage<ShopInfoDto> scrollPage = new ScrollPage<ShopInfoDto>();
		String columnName = "c".equals(order) ? "collect_count" : "d".equals(order) ? "distance" : "reply_count";
		scrollPage.addScrollPageOrder(new ScrollPageOrder("top_type", ScrollPage.DESC, lastBusinessValue == null ? null : ShopInfo.TOPTYPE0));
		scrollPage.addScrollPageOrder(new ScrollPageOrder(columnName, "d".equals(order) ? ScrollPage.ASC : ScrollPage.DESC, lastBusinessValue));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastShopId));
		scrollPage.setPageSize(10);
		Double _lat = StringUtils.isNotBlank(lat) ? Double.parseDouble(lat) : null; 
		Double _lng = StringUtils.isNotBlank(lng) ? Double.parseDouble(lng) : null; 
		Response<ScrollPage<ShopInfoDto>> pageResponse = shopInfoService.queryForScrollPage(scrollPage, _lat, _lng, appId, shopTypeId, regionCode, shopCategoryId, shopName);
		List<ShopInfoDto> dtos = pageResponse.getData().getPageResults();
		//组装数据
		CommonListResult<ShopInfoData> data = new CommonListResult<ShopInfoData>();
		List<ShopInfoData> dataList = Lists.newArrayList();
		ShopInfoData obj;
		//获取是否允许评论配置
		Byte canComment = commentAppConfigService.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_3).getData();
		for (ShopInfoDto dto : dtos) {
			obj = new ShopInfoData();
			obj.setAddress(dto.getAddress());
			obj.setCollectCount(dto.getCollectCount());
			obj.setCommentType(NumberUtils.toByte(CommentConfig.VALUE0.equals(canComment) ? "1" : "0"));
			obj.setCoverImg(dto.getCoverImg());
			obj.setDetailViewType(ClientControlType.DETAIL_VIEW_TYPE_2);
			obj.setDistance(dto.getDistance());
			obj.setId(dto.getId());
			obj.setLabel(dto.getInfoLabel());
			obj.setName(dto.getName());
			obj.setPrice(dto.getPrice() == null ? 0 : dto.getPrice() / 100);
			obj.setReplyCount(dto.getReplyCount());
			obj.setShareUrl(ShopInfoUtil.getShareUrl(dto.getId()));	
			obj.setSourceType(BusinessType.SOURCE_TYPE_3);
			obj.setSynopsis(dto.getDescription());
			obj.setTitle(dto.getName());
			obj.setTopType(dto.getTopType());
			obj.setUrl(ShopInfoUtil.getRedirectUrl(dto.getId()));
			dataList.add(obj);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用商铺列表接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 商情区域列表</p>
	 * @author Tangtao on 2016年7月15日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param appColumnsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shopRegion"}, method = {RequestMethod.POST})
	public Response<CommonListResult<Map<String, String>>> shopRegion(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			@RequestParam Long appColumnsId) {
		logger.debug("===================== 调用商情区域列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, appColumnsId=%d", 
						appId, sessionId, token, appColumnsId));
		Response<CommonListResult<Map<String, String>>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || appColumnsId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商情区域列表接口异常：" + response);
			return response;
		}
		//根据栏目获取商情类型
		Response<ShopType> resp = shopTypeService.getByColumn(appColumnsId);
		if (resp.getCode() < 0 || resp.getData() == null) {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("商情类型栏目配置有误");
			logger.error("调用商铺列表接口异常：" + response);
			return response;
		}
		Long shopTypeId = resp.getData().getId();
		
		//查询数据
		Response<List<ConfigRegion>> regionResponse = configRegionService.getShopByAppAndType(appId, shopTypeId);
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
		logger.debug("调用商情区域列表接口：" + response);
		return response;
	}
	
	/**
	 * Title:商情上传保存接口
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月24日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param shopInfo
	 * @param images
	 * @param descriptions
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"shopSave"}, method = RequestMethod.POST)
	public Response<Byte> shopSave(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, String token,
			ShopInfo shopInfo,
			@RequestParam String images,
			String descriptions){
		logger.debug("===================== 调用商情上传-保存接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, typeId=%d, categoryId=%d, name=%s, lat=%s, lng=%s, regionCode=%s, regionName=%s, address=%s, telephone=%s, price=%d, description=%s",
						appId, sessionId, token, shopInfo.getTypeId(), shopInfo.getCategoryId(), shopInfo.getName(), shopInfo.getLat(), shopInfo.getLng(), shopInfo.getRegionCode(), shopInfo.getRegionName(), shopInfo.getAddress(), shopInfo.getTelephone(), shopInfo.getPrice(), shopInfo.getDescription()));
		Response<Byte> rs = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || shopInfo == null || StringUtils.isBlank(shopInfo.getName()) || StringUtils.isBlank(shopInfo.getAddress()) || StringUtils.isBlank(shopInfo.getTelephone()) || shopInfo.getPrice() == null || StringUtils.isBlank(images) || StringUtils.isBlank(shopInfo.getRegionCode()) || StringUtils.isBlank(shopInfo.getRegionName())) {
			rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商铺上传保存接口参数错误：" + rs);
			return rs;
		}
		//记得验证图片，至少要有一张
		rs = shopInfoService.save(shopInfo, images, descriptions, sessionId, token);
		if(rs.getCode() == 0){
        	Byte tmp = 1;
        	rs.setMessage(tmp.equals(rs.getData()) ? "发布成功，审核中" : "发布成功");
        }
		logger.debug("调用商情上传-保存接口：" + rs);
		return rs;
	}
}