/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
/**
 * 
 */
package com.cqliving.cloud.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.service.CommentAppConfigService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.OrderEvaluateData;
import com.cqliving.cloud.online.interfacc.dto.ShoppingCarouselData;
import com.cqliving.cloud.online.interfacc.dto.ShoppingCartData;
import com.cqliving.cloud.online.interfacc.dto.ShoppingData;
import com.cqliving.cloud.online.interfacc.dto.ShoppingDetailData;
import com.cqliving.cloud.online.interfacc.dto.ShoppingImagesData;
import com.cqliving.cloud.online.interfacc.dto.ShoppingIndexResult;
import com.cqliving.cloud.online.order.dto.OrderEvaluateDto;
import com.cqliving.cloud.online.order.service.OrderEvaluateService;
import com.cqliving.cloud.online.order.service.OrderShopCartService;
import com.cqliving.cloud.online.shopping.domain.ShoppingCategory;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.shopping.domain.ShoppingImages;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.shopping.service.ShoppingCategoryService;
import com.cqliving.cloud.online.shopping.service.ShoppingGoodsService;
import com.cqliving.cloud.online.shopping.service.ShoppingImagesService;
import com.cqliving.cloud.online.shopping.service.ShoppingRecommendService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.utils.CurrencyUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title: 商城相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月21日
 */
@Controller
@RequestMapping({"shopping"})
public class ShoppingController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShoppingController.class);
	
	/** 商品排序方式 */
	private static final Map<String, String> orderMap = Maps.newHashMap();
	static {
		orderMap.put("n", "新品");
		orderMap.put("d", "折扣");
		orderMap.put("s", "评价");
		orderMap.put("x", "销量");
		orderMap.put("jg", "价格从高到低");
		orderMap.put("jd", "价格从低到高");
	}
	
	/** 商品排序对应的排序列及方向 */
	private static final Map<String, String[]> orderColMap = Maps.newHashMap();
	static {
		orderColMap.put("n", new String[]{"online_time", ScrollPage.DESC});
		orderColMap.put("d", new String[]{"discount", ScrollPage.DESC});
		orderColMap.put("s", new String[]{"goods_score", ScrollPage.DESC});
		orderColMap.put("x", new String[]{"sales_volume", ScrollPage.DESC});
		orderColMap.put("jg", new String[]{"price", ScrollPage.DESC});
		orderColMap.put("jd", new String[]{"price", ScrollPage.ASC});
	}
	
	@Autowired
	private CommentAppConfigService commentAppConfigService;
	@Autowired
	private OrderEvaluateService orderEvaluateService;
	@Autowired
	private OrderShopCartService orderShopCartService;
	@Autowired
	private ShoppingCategoryService shoppingCategoryService;
	@Autowired
	private ShoppingGoodsService shoppingGoodsService;
	@Autowired
	private ShoppingImagesService shoppingImagesService;
	@Autowired
	private ShoppingRecommendService shoppingRecommendService;
	
	/**
	 * <p>Description: 首页轮播数据</p>
	 * @author Tangtao on 2016年11月21日
	 * @param request
	 * @param appId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"carousel"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ShoppingCarouselData>> carousel(HttpServletRequest request, @RequestParam Long appId) {
		String parameters = String.format("\n<请求参数：appId=%d", appId);
		logger.debug("===================== 调用商城轮播数据接口 =====================>" + parameters);
		Response<CommonListResult<ShoppingCarouselData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商城轮播数据接口异常：" + response + parameters);
			return response;
		}
		
		//查询数据
		Response<List<ShoppingGoodsDto>> dataResponse = shoppingRecommendService.getCarouselByAppId(appId);
		List<ShoppingGoodsDto> list = dataResponse.getData();
		//组装数据
		CommonListResult<ShoppingCarouselData> data = new CommonListResult<ShoppingCarouselData>();
		List<ShoppingCarouselData> dataList = Lists.newArrayList();
		ShoppingCarouselData d;
		//获取是否允许评论配置
		Byte canComment = commentAppConfigService.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_13).getData();
		for (ShoppingGoodsDto obj : list) {
			d = new ShoppingCarouselData();
			d.setCommentType(NumberUtils.toByte(CommentConfig.VALUE0.equals(canComment) ? "1" : "0"));
			d.setDetailViewType((byte) -1);	//FIXME Tangtao	确定客户端的展示类型
			d.setId(obj.getId());
			d.setImageUrl(obj.getListImageUrl());
			d.setName(obj.getName());
			d.setShareUrl("");	//FIXME Tangtao
			d.setSourceType(BusinessType.SOURCE_TYPE_13);
			d.setSynopsis(obj.getSynopsis());
			d.setUrl("");	//FIXME Tangtao
			dataList.add(d);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用商城轮播数据接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 我的购物车</p>
	 * @author Tangtao on 2016年11月28日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastCartId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"cart"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ShoppingCartData>> cart(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token) {
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s", appId, sessionId, token);
		logger.debug("===================== 调用我的购物车接口 =====================>" + parameters);
		Response<CommonListResult<ShoppingCartData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用我的购物车接口异常：" + response + parameters);
			return response;
		}
		
		//查询数据
		Response<List<ShoppingGoodsDto>> cartResponse = shoppingGoodsService.getMyCart(appId, sessionId, token);
		List<ShoppingGoodsDto> list = cartResponse.getData();
		
		//组装数据
		CommonListResult<ShoppingCartData> data = new CommonListResult<ShoppingCartData>();
		List<ShoppingCartData> dataList = Lists.newArrayList();
		ShoppingCartData obj;
		//获取是否允许评论配置
		Byte canComment = commentAppConfigService.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_13).getData();
		for (ShoppingGoodsDto dto : list) {
			obj = new ShoppingCartData();
			obj.setCartId(dto.getCartId());
			obj.setCommentType(NumberUtils.toByte(CommentConfig.VALUE0.equals(canComment) ? "1" : "0"));
			obj.setDetailViewType((byte) -1);	//FIXME Tangtao
			obj.setGoodsId(dto.getId());
			obj.setLabels(dto.getLabels());
			obj.setListImageUrl(dto.getListImageUrl());
			obj.setName(dto.getName());
			obj.setOriginalPrice(CurrencyUtil.format(dto.getOriginalPrice(), 2, 2, true));
			obj.setPrice(CurrencyUtil.format(dto.getPrice(), 2, 2, true));
			obj.setQuantity(dto.getQuantity());
			obj.setShareUrl("");	//FIXME Tangtao
			obj.setSourceType(BusinessType.SOURCE_TYPE_13);
			obj.setStatus((byte) (ShoppingGoods.STATUS3.equals(dto.getStatus()) ? 1 : 0));
			obj.setStock(dto.getStock());
			obj.setSynopsis(dto.getSynopsis());
			obj.setUrl("");	//FIXME Tangtao
			dataList.add(obj);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用我的购物车接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 加入购物车</p>
	 * @author Tangtao on 2016年11月29日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param goodsId
	 * @param quantity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"cartAdd"}, method = {RequestMethod.POST})
	public Response<Boolean> cartAdd(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			@RequestParam("gid") Long goodsId, 
			@RequestParam(required = false, value = "q", defaultValue = "1") Integer quantity) {
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, goodsId=%d, quantity=%d", appId, sessionId, token, goodsId, quantity);
		logger.debug("===================== 调用加入购物车接口 =====================>" + parameters);
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || quantity < 1) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用加入购物车接口异常：" + response + parameters);
			return response;
		}
		
		//保存数据
		boolean data = orderShopCartService.add(appId, sessionId, token, goodsId, quantity).getData();
		response.setData(data);
		logger.debug("调用加入购物车接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 修改购物车</p>
	 * @author Tangtao on 2016年11月29日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @param goodsId
	 * @param quantity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"cartModify"}, method = {RequestMethod.POST})
	public Response<Integer> cartModify(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			@RequestParam("cid") Long id, 
			@RequestParam("gid") Long goodsId, 
			@RequestParam("q") Integer quantity) {
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, id=%d, goodsId=%d, quantity=%d", appId, sessionId, token, id, goodsId, quantity);
		logger.debug("===================== 调用修改购物车接口 =====================>" + parameters);
		Response<Integer> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || id == null || goodsId == null || quantity == null || Math.abs(quantity) != 1) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用修改购物车接口异常：" + response + parameters);
			return response;
		}
		
		//保存数据
		response = orderShopCartService.modify(appId, sessionId, token, id, goodsId, quantity);
		logger.debug("调用修改购物车接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 移出购物车</p>
	 * @author Tangtao on 2016年11月29日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"cartRemove"}, method = {RequestMethod.POST})
	public Response<Boolean> cartRemove(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			@RequestParam("cartIds") String ids) {
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, cartIds=%s", appId, sessionId, token, ids);
		logger.debug("===================== 调用移出购物车接口 =====================>" + parameters);
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || StringUtils.isBlank(ids)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用移出购物车接口异常：" + response + parameters);
			return response;
		}
		
		List<Long> idList = Lists.newArrayList();
		for (String id : ids.split(",")) {
			idList.add(NumberUtils.toLong(id.trim()));
		}
		//保存数据
		boolean data = orderShopCartService.remove(appId, sessionId, token, idList).getData();
		response.setData(data);
		logger.debug("调用移出购物车接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 获取商城顶级分类</p>
	 * @author Tangtao on 2016年11月22日
	 * @param request
	 * @param appId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"category"}, method = {RequestMethod.POST})
	public Response<CommonListResult<Map<Long, String>>> category(HttpServletRequest request, @RequestParam Long appId) {
		String parameters = String.format("\n<请求参数：appId=%d", appId);
		logger.debug("===================== 调用商城顶级分类接口 =====================>" + parameters);
		Response<CommonListResult<Map<Long, String>>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商城顶级分类接口异常：" + response + parameters);
			return response;
		}
		
		//查询数据
		Map<String, Object> conditionMap = Maps.newHashMap();
		conditionMap.put("EQ_appId", appId);
		conditionMap.put("EQ_status", ShoppingCategory.STATUS3);
		conditionMap.put("EQ_level", 1);
		conditionMap.put("EQ_parentId", 0);
		Map<String, Boolean> orderMap = Maps.newLinkedHashMap();
		orderMap.put("sortNo", true);
		orderMap.put("id", false);
		Response<List<ShoppingCategory>> categoryrResponse = shoppingCategoryService.queryForList(conditionMap, orderMap);
		List<ShoppingCategory> list = categoryrResponse.getData();
		
		//组装数据
		CommonListResult<Map<Long, String>> data = new CommonListResult<Map<Long, String>>();
		List<Map<Long, String>> dataList = Lists.newArrayList();
		Map<Long, String> map;
		for (ShoppingCategory obj : list) {
			map = Maps.newHashMap();
			map.put(obj.getId(), obj.getName());
			dataList.add(map);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用商城顶级分类接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 商品评价列表</p>
	 * @author Tangtao on 2016年12月1日
	 * @param request
	 * @param res
	 * @param appId
	 * @param goodsId
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"evaluateList"}, method = {RequestMethod.POST})
	public Response<CommonListResult<OrderEvaluateData>> evaluateList(HttpServletRequest request, HttpServletResponse res,
			@RequestParam Long appId, 
			@RequestParam("gid") Long goodsId, 
			Long lastId) {
		res.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
		String parameters = String.format("\n<请求参数：appId=%d, goodsId=%d, lastId=%d", appId, goodsId, lastId);
		logger.debug("===================== 调用商城商品评价列表接口 =====================>" + parameters);
		Response<CommonListResult<OrderEvaluateData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || goodsId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商城商品评价列表接口异常：" + response + parameters);
			return response;
		}
		
		//查询数据
		Map<String, Object> conditionMap = Maps.newHashMap();
		conditionMap.put("EQ_appId", appId);
		conditionMap.put("EQ_goodsId", goodsId);
		ScrollPage<OrderEvaluateDto> scrollPage = new ScrollPage<OrderEvaluateDto>();
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage.setPageSize(10);
		Response<ScrollPage<OrderEvaluateDto>> categoryrResponse = orderEvaluateService.queryForScrollPage(scrollPage, conditionMap);
		List<OrderEvaluateDto> list = categoryrResponse.getData().getPageResults();
		
		//组装数据
		CommonListResult<OrderEvaluateData> data = new CommonListResult<OrderEvaluateData>();
		List<OrderEvaluateData> dataList = Lists.newArrayList();
		OrderEvaluateData obj;
		for (OrderEvaluateDto dto : list) {
			obj = new OrderEvaluateData();
			obj.setAvatar(dto.getAvatar());
			obj.setContent(dto.getContent());
			obj.setCreateTime(DateUtil.convertTimeToFormatHore1(dto.getCreateTime().getTime()));
			obj.setGoodsScore(caculateScore(dto.getGoodsScore()));
			obj.setId(dto.getId());
			obj.setImageUrls(dto.getImageUrls());
			obj.setListImageUrls(dto.getListImageUrls());
			obj.setUserName(dto.getUserName());
			dataList.add(obj);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用商城商品评价列表接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 发表评价</p>
	 * @author Tangtao on 2016年12月8日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @param goodsIds
	 * @param goodsScores
	 * @param contents
	 * @param imgs
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"evaluateSave"}, method = {RequestMethod.POST})
	public Response<Boolean> evaluateSave(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			@RequestParam String token, 
			@RequestParam("oid") Long orderId, 
			@RequestParam("gids") String goodsIds,
			@RequestParam("gss") String goodsScores,
			@RequestParam("cs") String contents,
			String imgs) {
		String parameters = String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, orderId=%d, goodsIds=%s, goodsScores=%s, contents=%s, imgs.length=%d", 
				appId, sessionId, token, orderId, goodsIds, goodsScores, contents, imgs.split(",").length);
		logger.debug("===================== 调用商城订单评价保存接口 =====================>" + parameters);
		Response<Boolean> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || orderId == null || 
				StringUtils.isBlank(goodsIds) || StringUtils.isBlank(goodsScores) || StringUtils.isBlank(contents)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商城订单评价保存接口异常：" + response + parameters);
			return response;
		}
		response = orderEvaluateService.add(appId, sessionId, token, orderId, goodsIds, goodsScores, contents, imgs);
		logger.debug("调用商城订单评价保存接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 商品详情</p>
	 * @author Tangtao on 2016年12月1日
	 * @param request
	 * @param res
	 * @param appId
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"goodsDetail"}, method = {RequestMethod.POST})
	public Response<ShoppingDetailData> goodsDetail(HttpServletRequest request, HttpServletResponse res,
			@RequestParam Long appId,
			@RequestParam Long id) {
		res.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
		String parameters = String.format("\n<请求参数：appId=%d, id=%d", appId, id);
		logger.debug("===================== 调用商城商品详情接口 =====================>" + parameters);
		Response<ShoppingDetailData> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || id == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商城商品详情接口异常：" + response + parameters);
			return response;
		}
		
		//查询商品数据
		ShoppingGoods obj = shoppingGoodsService.get(id).getData();
		if (obj == null || ShoppingGoods.STATUS1.equals(obj.getStatus()) || ShoppingGoods.STATUS99.equals(obj.getStatus())) {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("商品不存在");
			logger.error("调用商城商品详情接口异常：" + response + parameters);
			return response;
		}
		//查询商品图片
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_shoppingGoodsId", id);
		map.put("EQ_status", ShoppingImages.STATUS3);
		Map<String, Boolean> orderMap = Maps.newLinkedHashMap();
		orderMap.put("sortNo", true);
		orderMap.put("id", false);
		PageInfo<ShoppingImages> pageInfo = new PageInfo<ShoppingImages>();
		pageInfo.setCountOfCurrentPage(Integer.MAX_VALUE);
		Response<PageInfo<ShoppingImages>> imageResponse = shoppingImagesService.queryForPage(pageInfo, map, orderMap);
		List<ShoppingImages> list = imageResponse.getData().getPageResults();
		
		//组装数据
		ShoppingDetailData data = new ShoppingDetailData();
		data.setContent(obj.getContent());
		data.setId(obj.getId());
		data.setLabels(obj.getLabels());
		data.setName(obj.getName());
		data.setOriginalPrice(CurrencyUtil.format(obj.getOriginalPrice(), 2, 2, true));
		data.setPrice(CurrencyUtil.format(obj.getPrice(), 2, 2, true));
		data.setReplyCount(obj.getReplyCount());
		data.setSalesVolume(obj.getSalesVolume());
		data.setStatus((byte) (ShoppingGoods.STATUS3.equals(obj.getStatus()) ? 1 : 0));
		data.setSynopsis(obj.getSynopsis());
		List<ShoppingImagesData> imageUrlList = Lists.newArrayList();
		ShoppingImagesData image;
		for (ShoppingImages img : list) {
			image = new ShoppingImagesData();
			image.setDescription(img.getDescription());
			image.setUrl(img.getUrl());
			imageUrlList.add(image);
		}
		data.setImageUrlList(imageUrlList);
		response.setData(data);
		logger.debug("调用商城商品详情接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 商品列表</p>
	 * @author Tangtao on 2016年11月24日
	 * @param request
	 * @param appId
	 * @param categoryId
	 * @param order 排序方式{n: 新品, d: 折扣, s: 评价, x: 销量, jg: 价格从高到低, jd: 价格从低到高}
	 * @param goodsName
	 * @param lastBusinessValue
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"goodsList"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ShoppingData>> goodsList(HttpServletRequest request, 
			@RequestParam Long appId, 
			Long categoryId, 
			@RequestParam String order, 
			String goodsName, 
			String lastBusinessValue, 
			Long lastId) {
		String orderStr = (StringUtils.isNotBlank(order) && orderMap.containsKey(order)) ? orderMap.get(order) : "未知";
		String parameters = String.format("\n<请求参数：appId=%d, order=%s[%s], goodsName=%s, lastBusinessValue=%s, lastId=%d", 
				appId, order, orderStr, goodsName, lastBusinessValue, lastId);
		logger.debug("===================== 调用商城商品列表接口 =====================>" + parameters);
		Response<CommonListResult<ShoppingData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || "未知".equals(orderStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商城商品列表接口异常：" + response + parameters);
			return response;
		}
		
		//查询数据
		Map<String, Object> conditionMap = Maps.newHashMap();
		conditionMap.put("EQ_appId", appId);
		conditionMap.put("LIKE_name", goodsName);
		conditionMap.put("EQ_categoryLevelOneId", categoryId);
		ScrollPage<ShoppingGoodsDto> scrollPage = new ScrollPage<ShoppingGoodsDto>();
		String columnName = orderColMap.get(order)[0];
		scrollPage.addScrollPageOrder(new ScrollPageOrder(columnName, orderColMap.get(order)[1], lastBusinessValue));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage.setPageSize(10);
		Response<ScrollPage<ShoppingGoodsDto>> categoryrResponse = shoppingGoodsService.queryForScrollPage(scrollPage, conditionMap);
		List<ShoppingGoodsDto> list = categoryrResponse.getData().getPageResults();
		
		//组装数据
		CommonListResult<ShoppingData> data = new CommonListResult<ShoppingData>();
		List<ShoppingData> dataList = Lists.newArrayList();
		ShoppingData shoppingData;
		//获取是否允许评论配置
		Byte canComment = commentAppConfigService.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_13).getData();
		for (ShoppingGoodsDto dto : list) {
			shoppingData = new ShoppingData();
			shoppingData.setCollectCount(dto.getCollectCount());
			shoppingData.setCommentType(NumberUtils.toByte(CommentConfig.VALUE0.equals(canComment) ? "1" : "0"));
			shoppingData.setDetailViewType((byte) -1);	//FIXME Tangtao
			shoppingData.setDiscount(dto.getDiscount());
			shoppingData.setGoodsScore(dto.getGoodsScore());
			shoppingData.setId(dto.getId());
			shoppingData.setLabels(dto.getLabels());
			shoppingData.setListImageUrl(dto.getListImageUrl());
			shoppingData.setName(dto.getName());
			shoppingData.setOnlineTime(DateUtil.format(dto.getOnlineTime()));
			shoppingData.setOriginalPrice(CurrencyUtil.format(dto.getOriginalPrice(), 2, 2, true));
			shoppingData.setPrice(CurrencyUtil.format(dto.getPrice(), 2, 2, true));
			shoppingData.setPriceCents(dto.getPrice());
			shoppingData.setSalesVolume(dto.getSalesVolume());
			shoppingData.setShareUrl("");	//FIXME Tangtao
			shoppingData.setSourceType(BusinessType.SOURCE_TYPE_13);
			shoppingData.setStock(dto.getStock());
			shoppingData.setSynopsis(dto.getSynopsis());
			shoppingData.setUrl("");	//FIXME Tangtao
			dataList.add(shoppingData);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用商城商品列表接口结果：" + response);
		return response;
	}

	/**
	 * <p>Description: 商城首页推荐商品</p>
	 * @author Tangtao on 2016年11月23日
	 * @param request
	 * @param appId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"index"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ShoppingIndexResult>> index(HttpServletRequest request, @RequestParam Long appId) {
		String parameters = String.format("\n<请求参数：appId=%d", appId);
		logger.debug("===================== 调用商城首页推荐商品接口 =====================>" + parameters);
		Response<CommonListResult<ShoppingIndexResult>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用商城首页推荐商品接口异常：" + response + parameters);
			return response;
		}
		
		//查询数据
		Map<String, Object> conditionMap = Maps.newHashMap();
		conditionMap.put("EQ_appId", appId);
		Map<String, Boolean> orderMap = Maps.newLinkedHashMap();
		orderMap.put("categoryLevelOneId", false);
		orderMap.put("sortNo", true);
		orderMap.put("id", false);
		Response<List<ShoppingGoodsDto>> categoryrResponse = shoppingRecommendService.getIndex(conditionMap, orderMap);
		List<ShoppingGoodsDto> list = categoryrResponse.getData();
		
		//组装数据
		CommonListResult<ShoppingIndexResult> data = new CommonListResult<ShoppingIndexResult>();
		List<ShoppingIndexResult> dataList = Lists.newArrayList();
		ShoppingIndexResult result;
		ShoppingIndexResult previous = null;
		ShoppingData shoppingData;
		//获取是否允许评论配置
		Byte canComment = commentAppConfigService.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_13).getData();
		for (ShoppingGoodsDto dto : list) {
			shoppingData = new ShoppingData();
			shoppingData.setCollectCount(dto.getCollectCount());
			shoppingData.setCommentType(NumberUtils.toByte(CommentConfig.VALUE0.equals(canComment) ? "1" : "0"));
			shoppingData.setDetailViewType((byte) -1);	//FIXME Tangtao
			shoppingData.setId(dto.getId());
			shoppingData.setLabels(dto.getLabels());
			shoppingData.setListImageUrl(dto.getListImageUrl());
			shoppingData.setName(dto.getName());
			shoppingData.setOriginalPrice(CurrencyUtil.format(dto.getOriginalPrice(), 2, 2, true));
			shoppingData.setPrice(CurrencyUtil.format(dto.getPrice(), 2, 2, true));
			shoppingData.setShareUrl("");	//FIXME Tangtao
			shoppingData.setSourceType(BusinessType.SOURCE_TYPE_13);
			shoppingData.setStock(dto.getStock());
			shoppingData.setSynopsis(dto.getSynopsis());
			shoppingData.setUrl("");	//FIXME Tangtao
			if (previous != null && dto.getCategoryLevelOneId().equals(previous.getCategoryId())) {	
				//同类商品，添加进商品列表
				previous.getDataList().add(shoppingData);
			} else {	
				//非同类商品
				result = new ShoppingIndexResult();
				result.setCategoryId(dto.getCategoryLevelOneId());
				result.setCategoryName(dto.getCategoryLevelOneName());
				List<ShoppingData> datas = Lists.newArrayList();
				datas.add(shoppingData);
				result.setDataList(datas);
				previous = result;
				dataList.add(result);
			}
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用商城首页推荐商品接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 计算得分的星星数</p>
	 * @author Tangtao on 2016年12月1日
	 * @param score
	 * @return
	 */
	private int caculateScore(int score) {
		if (score <= 0) {
			return 0;
		} else if (score <= 20) {
			return 1;
		} else if (score <= 40) {
			return 2;
		} else if (score <= 60) {
			return 3;
		} else if (score <= 80) {
			return 4;
		}
		return 5;
	}
	
}