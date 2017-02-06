package com.cqliving.cloud.online.order.manager.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.dao.UserAccountDao;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.order.dao.OrderAlipayNotifyLogDao;
import com.cqliving.cloud.online.order.dao.OrderIncomeRecordsDao;
import com.cqliving.cloud.online.order.dao.OrderInfoDao;
import com.cqliving.cloud.online.order.dao.OrderPayConfigDao;
import com.cqliving.cloud.online.order.dao.OrderPayDao;
import com.cqliving.cloud.online.order.dao.OrderPriceDetailDao;
import com.cqliving.cloud.online.order.dao.OrderRefundDao;
import com.cqliving.cloud.online.order.dao.OrderShopCartDao;
import com.cqliving.cloud.online.order.domain.OrderAlipayNotifyLog;
import com.cqliving.cloud.online.order.domain.OrderFlow;
import com.cqliving.cloud.online.order.domain.OrderGoods;
import com.cqliving.cloud.online.order.domain.OrderIncomeRecords;
import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.cloud.online.order.domain.OrderPay;
import com.cqliving.cloud.online.order.domain.OrderPayConfig;
import com.cqliving.cloud.online.order.domain.OrderPriceDetail;
import com.cqliving.cloud.online.order.domain.OrderRefund;
import com.cqliving.cloud.online.order.dto.OrderConfirmDarta;
import com.cqliving.cloud.online.order.dto.OrderGoodsData;
import com.cqliving.cloud.online.order.dto.OrderInfoData;
import com.cqliving.cloud.online.order.dto.OrderInfoDto;
import com.cqliving.cloud.online.order.dto.OrderRefundData;
import com.cqliving.cloud.online.order.dto.OrderRefundDto;
import com.cqliving.cloud.online.order.dto.OrderUserAddressData;
import com.cqliving.cloud.online.order.dto.PaySuccessResultData;
import com.cqliving.cloud.online.order.manager.OrderFlowManager;
import com.cqliving.cloud.online.order.manager.OrderGoodsManager;
import com.cqliving.cloud.online.order.manager.OrderInfoManager;
import com.cqliving.cloud.online.shopping.dao.ShoppingFareTemplateDetailDao;
import com.cqliving.cloud.online.shopping.dao.ShoppingGoodsDao;
import com.cqliving.cloud.online.shopping.dao.ShoppingUserAddressDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateDetail;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.NumberUtil;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.Base64Util;
import com.cqliving.tool.utils.CurrencyUtil;
import com.cqliving.tool.utils.ImageUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("orderInfoManager")
public class OrderInfoManagerImpl extends EntityServiceImpl<OrderInfo, OrderInfoDao> implements OrderInfoManager {
	
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private OrderGoodsManager orderGoodsManager;
	@Autowired
	private OrderFlowManager orderFlowManager;
	@Autowired
	private OrderRefundDao orderRefundDao;
	@Autowired
	private OrderPriceDetailDao orderPriceDetailDao;
	@Autowired
	private OrderAlipayNotifyLogDao orderAlipayNodifyLogDao;
	@Autowired
	private OrderPayConfigDao orderPayConfigDao;
	@Autowired
	private OrderShopCartDao orderShopCartDao;
	@Autowired
	private OrderIncomeRecordsDao orderIncomeRecordsDao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private ShoppingGoodsDao shoppingGoodsDao;
	@Autowired
	private ShoppingUserAddressDao shoppingUserAddressDao;
	@Autowired
	private ShoppingFareTemplateDetailDao shoppingFareTemplateDetailDao;
	
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(OrderInfo.STATUS99, idList);
	}
	
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status, Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}
	
	@Override
	@Transactional(value="transactionManager")
	public int updatePayForStatus(Byte payforStatus, Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updatePayForStatus(payforStatus, idList);
	}
	
	@Override
	public List<OrderInfo> getByIds(Long...ids){
		List<Long> idList = Arrays.asList(ids);
		Map<String, Object> map = Maps.newHashMap();
		map.put("IN_id", idList);
		map.put("EQ_status", OrderInfo.STATUS3);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		sortMap.put("id", true);
		return query(map, sortMap);
	}

	@Override
	public OrderInfo getByOrderNo(String orderNo){
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_orderNo", orderNo);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		List<OrderInfo> list = query(map, sortMap);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	@Override
	@Transactional(value="transactionManager")
	public void cancelByUser(Long appId, String sessionId, String token, Long...ids){
		UserSession userSession = userSessionManager.getByToken(token);
    	if(userSession == null){
    		throw new BusinessException(-2, "用户信息错误。");
    	}
    	List<OrderInfo> orderInfoList = getByIds(ids);
    	if(orderInfoList == null || orderInfoList.size() != ids.length){
    		throw new BusinessException(-3, "订单不存在。");
    	}
    	//处理订单流水记录
    	Date now = new Date();
    	for(OrderInfo orderInfo : orderInfoList){
    		if(!OrderInfo.PAYFORSTATUS1.equals(orderInfo.getPayforStatus())){
    			throw new BusinessException(-3, "订单不存在。");
    		}
    		saveOrderFlow(orderInfo, OrderFlow.OPERATETYPE3, OrderFlow.USERTYPE1, now, userSession.getUserId(), "用户取消订单");
    	}
    	//处理商品库存
    	List<OrderGoods> orderGoodsList = orderGoodsManager.getByOrders(ids);
    	if(orderGoodsList != null && orderGoodsList.size() > 0){
    		for(OrderGoods orderGoods : orderGoodsList){
    			if(OrderGoods.REFUNDSTATUS1.equals(orderGoods.getRefundStatus())){//对未退款的还原库存
    				shoppingGoodsDao.updateStockAndSalesVolume(orderGoods.getQuantity(), orderGoods.getQuantity()*-1, orderGoods.getGoodsId());
    			}
    		}
    	}
    	//修改订单状态
		List<Long> idList = Arrays.asList(ids);
		this.getEntityDao().updatePayForStatus(OrderInfo.PAYFORSTATUS5, idList);
	}
	
	@Override
	@Transactional(value="transactionManager")
	public void delByUser(Long appId, String sessionId, String token, Long...ids){
		UserSession userSession = userSessionManager.getByToken(token);
		if(userSession == null){
			throw new BusinessException(-2, "用户信息错误。");
		}
		List<OrderInfo> orderInfoList = getByIds(ids);
		if(orderInfoList == null || orderInfoList.size() != ids.length){
			throw new BusinessException(-3, "订单不存在。");
		}
		//处理订单流水记录
		Date now = new Date();
		OrderFlow orderFlow;
		List<OrderFlow> orderFlowList = Lists.newArrayList();
		for(OrderInfo orderInfo : orderInfoList){
			//删除已取消的或者已完成的
			if(!(OrderInfo.PAYFORSTATUS5.equals(orderInfo.getPayforStatus()) || OrderInfo.PAYFORSTATUS6.equals(orderInfo.getPayforStatus()))){
    			throw new BusinessException(-3, "订单不存在。");
    		}
			orderFlow = new OrderFlow();
			orderFlow.setAppId(orderInfo.getAppId());
			orderFlow.setOrderId(orderInfo.getId());
			orderFlow.setOperateType(OrderFlow.OPERATETYPE11);
			orderFlow.setDescription("用户删除订单");
			orderFlow.setUserType(OrderFlow.USERTYPE1);
			orderFlow.setUserId(userSession.getUserId());
			orderFlow.setCreateTime(now);
			orderFlowList.add(orderFlow);
		}
		orderFlowManager.saves(orderFlowList);
		//修改订单状态
		List<Long> idList = Arrays.asList(ids);
		this.getEntityDao().updateStatus(OrderInfo.STATUS99, idList);
	}

	@Override
	public CommonListResult<OrderInfoData> getScrollPage(Long appId, String sessionId, String token, Byte payforStatus, Long lastId) {
		UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_userId", userSession.getUserId());
		conditions.put("EQ_payforStatus", payforStatus);
		conditions.put("NOTEQ_status", OrderInfo.STATUS99);
		ScrollPage<OrderInfo> scrollPage = new ScrollPage<OrderInfo>();
		scrollPage.setPageSize(10);
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage = getEntityDao().queryScrollPage(scrollPage, conditions);
		List<OrderInfo> result = scrollPage.getPageResults();
		
		List<OrderGoods> goodsList = null;
		if(result != null && result.size() > 0){
			Long[] ids = new Long[result.size()];
			for(int i = 0; i < result.size(); i++){
				ids[i] = result.get(i).getId();
			}
			if(ids.length > 0)
				goodsList = orderGoodsManager.getByOrders(ids);
		}
		List<OrderInfoData> list = Lists.newArrayList();
		if(goodsList != null && goodsList.size() > 0){
			OrderInfoData orderInfoData;
			OrderGoodsData orderGoodsData;
			List<OrderGoodsData> goodsDataList;
			for(OrderInfo orderInfo : result){
				//判断订单是否过期
//				if(OrderInfo.PAYFORSTATUS1.equals(orderInfo.getPayforStatus()) && getTimeDiff(orderInfo.getCreateTime()) == null){
//					orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS5);
//				}
				orderInfoData = new OrderInfoData();
				orderInfoData = new OrderInfoData();
				orderInfoData.setOrderId(orderInfo.getId());
				orderInfoData.setPayForStatus(orderInfo.getPayforStatus());
				orderInfoData.setPayForStatusStr(OrderInfo.allPayforStatussFront.get(orderInfo.getPayforStatus()));
				orderInfoData.setCreateTimeStr(DateUtil.format(orderInfo.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
				orderInfoData.setOrderMoney(CurrencyUtil.format(orderInfo.getOrderAmount(), 2, 2, true));
				goodsDataList = Lists.newArrayList();
				for(OrderGoods orderGoods : goodsList){
					if(orderInfo.getId().equals(orderGoods.getOrderId())){
						orderGoodsData = new OrderGoodsData();
						orderGoodsData.setGoodsId(orderGoods.getGoodsId());
						orderGoodsData.setGoodsName(orderGoods.getGoodsName());
						orderGoodsData.setGoodsPrice(CurrencyUtil.format(orderGoods.getGoodsPrice(), 2, 2, true));
						orderGoodsData.setOriginalPrice(CurrencyUtil.format(orderGoods.getOriginalPrice(), 2, 2, true));
						orderGoodsData.setQuantity(orderGoods.getQuantity());
						orderGoodsData.setRefundStatus(orderGoods.getRefundStatus());
						orderGoodsData.setRefundStatusStr(OrderGoods.allRefundStatus.get(orderGoods.getRefundStatus()));
						orderGoodsData.setGoodsImageUrl(orderGoods.getGoodsImageUrl() == null ? "" : orderGoods.getGoodsImageUrl());
						orderGoodsData.setOrderId(orderInfo.getId());
						goodsDataList.add(orderGoodsData);
					}
				}
				orderInfoData.setGoodsList(goodsDataList);
				list.add(orderInfoData);
			}
		}
		CommonListResult<OrderInfoData> commonListResult = new CommonListResult<OrderInfoData>();
		commonListResult.setDataList(list);
		return commonListResult;
	}
	
	@Override
	public CommonListResult<OrderInfoData> getScrollPageRefund(Long appId, String sessionId, String token, Long lastId) {
		UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_userId", userSession.getUserId());
		ScrollPage<OrderRefundDto> scrollPage = new ScrollPage<OrderRefundDto>();
		scrollPage.setPageSize(10);
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage = orderRefundDao.queryScrollPage(scrollPage, conditions, appId, userSession.getUserId());
		List<OrderRefundDto> result = scrollPage.getPageResults();
		
		List<OrderInfoData> list = Lists.newArrayList();
		if(result != null && result.size() > 0){
			OrderInfoData orderInfoData;
			List<OrderGoodsData> goodsDataList;
			OrderGoodsData orderGoodsData;
			for(OrderRefundDto dto : result){
				orderInfoData = new OrderInfoData();
				orderInfoData.setRefundId(dto.getId());
				orderInfoData.setOrderId(dto.getOrderId());
				orderInfoData.setPayForStatus(OrderInfo.PAYFORSTATUS7);
				orderInfoData.setPayForStatusStr(OrderInfo.PAYFORSTATUS6.equals(dto.getOrderPayforStatus()) ? OrderInfo.allPayforStatussFront.get(dto.getOrderPayforStatus()) : "退款中");
				orderInfoData.setCreateTimeStr(DateUtil.format(dto.getOrderCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
				orderInfoData.setOrderMoney(CurrencyUtil.format(dto.getRefundAmount(), 2, 2, true));
				goodsDataList = Lists.newArrayList();
				orderGoodsData = new OrderGoodsData();
				orderGoodsData.setGoodsId(dto.getGoodsId());
				orderGoodsData.setGoodsName(dto.getGoodsName());
				orderGoodsData.setGoodsPrice(CurrencyUtil.format(dto.getGoodsPrice(), 2, 2, true));
				orderGoodsData.setOriginalPrice(CurrencyUtil.format(dto.getOriginalPrice(), 2, 2, true));
				orderGoodsData.setRefundStatus(dto.getRefundStatus());
				orderGoodsData.setRefundStatusStr(OrderGoods.allRefundStatus.get(dto.getRefundStatus()));
				orderGoodsData.setQuantity(dto.getQuantity());
				orderGoodsData.setGoodsImageUrl(dto.getGoodsImageUrl());
				orderGoodsData.setOrderId(dto.getOrderId());
				goodsDataList.add(orderGoodsData);
				orderInfoData.setGoodsList(goodsDataList);
				list.add(orderInfoData);
			}
		}
		CommonListResult<OrderInfoData> rs = new CommonListResult<OrderInfoData>();
		rs.setDataList(list);
		return rs;
	}
	
	@Override
	public OrderInfoData detail(Long appId, String sessionId, String token, Long orderId){
		UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        OrderInfo orderInfo = this.getEntityDao().get(orderId);
        if(orderInfo == null || !OrderInfo.STATUS3.equals(orderInfo.getStatus())){
        	throw new BusinessException(ErrorCodes.FAILURE, "订单不存在");
        }
        
        //判断订单是否已经过期,预防任务还没来得及跑订单就已经过期
        //待支付
        Long diff = getTimeDiff(orderInfo.getCreateTime());
        if(OrderInfo.PAYFORSTATUS1.equals(orderInfo.getPayforStatus()) && diff == null){
    		orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS5);
        }
        //订单信息
        OrderInfoData orderInfoData = new OrderInfoData();
        orderInfoData.setOrderId(orderInfo.getId());
        if(OrderInfo.PAYFORSTATUS1.equals(orderInfo.getPayforStatus())){//如果是待支付的时候，显示还差多少时间过期
        	orderInfoData.setDiffTime(diff);
        }
        orderInfoData.setOrderNo(orderInfo.getOrderNo());
        orderInfoData.setPayForStatus(orderInfo.getPayforStatus());
        orderInfoData.setPayForStatusStr(OrderInfo.allPayforStatussFront.get(orderInfo.getPayforStatus()));
        orderInfoData.setOrderMoney(CurrencyUtil.format(orderInfo.getOrderAmount(), 2, 2, true));
        orderInfoData.setDescn(orderInfo.getDescn() == null ? "" : orderInfo.getDescn());//给卖家留言
        orderInfoData.setShippingFare(CurrencyUtil.format(orderInfo.getShippingFare()));//运费
        orderInfoData.setExpressNo(orderInfo.getExpressNo() == null ? "" : orderInfo.getExpressNo());
        orderInfoData.setExpressCompany(orderInfo.getExpressCompany() == null ? "" : orderInfo.getExpressCompany() + "  正在为您服务");
        orderInfoData.setCreateTimeStr(DateUtil.format(orderInfo.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
        //收货地址信息
        OrderUserAddressData address = new OrderUserAddressData();
        address.setReceiverName(orderInfo.getReceiverName());
        address.setReceiverPhone(orderInfo.getReceiverPhone());
        address.setReceiverAddress(orderInfo.getReceiverAddress());
        orderInfoData.setRecAddress(address);
        //商品信息
        List<OrderGoods> list = orderGoodsManager.getByOrder(orderInfo.getId());
        List<OrderGoodsData> goodsList = Lists.newArrayList();
        OrderGoodsData goodsData;
        ShoppingGoods shoppingGoods;
        String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
        int goodsCount = 0;
        if(list != null && list.size() > 0){
        	for(OrderGoods og : list){
        		goodsData = new OrderGoodsData();
        		goodsData.setGoodsId(og.getGoodsId());
        		goodsData.setGoodsImageUrl(og.getGoodsImageUrl() == null ? "" : og.getGoodsImageUrl());
        		goodsData.setGoodsName(og.getGoodsName());
        		goodsData.setGoodsPrice(CurrencyUtil.format(og.getGoodsPrice(), 2, 2, true));
        		goodsData.setOriginalPrice(CurrencyUtil.format(og.getOriginalPrice(), 2, 2, true));
        		goodsData.setRefundStatus(og.getRefundStatus());
        		goodsData.setRefundStatusStr(OrderGoods.allRefundStatus.get(og.getRefundStatus()));
        		goodsData.setQuantity(og.getQuantity());
        		goodsData.setOrderId(orderInfo.getId());
        		
        		goodsData.setSourceType(BusinessType.SOURCE_TYPE_13);
//        		goodsData.setUrl(String.format(webPath + "shopping/goodsDetail.html?appId=%d&id=%d", appId, og.getGoodsId()));
        		goodsData.setShareUrl(String.format(webPath + "shopping/goodsShare.html?appId=%d&id=%d", appId, og.getGoodsId()));
        		goodsData.setTitle(og.getGoodsName());
        		shoppingGoods = shoppingGoodsDao.get(og.getGoodsId());
        		goodsData.setSynopsis(shoppingGoods != null ? shoppingGoods.getSynopsis() : og.getGoodsName());
        		
        		goodsList.add(goodsData);
        		goodsCount += og.getQuantity();
        	}
        }
        orderInfoData.setGoodsCount(goodsCount);
        orderInfoData.setGoodsList(goodsList);
        //时间信息
        StringBuffer timeStr = new StringBuffer();
        timeStr.append("订单编号：").append(orderInfo.getOrderNo());
        timeStr.append("\n\n").append("下单时间：").append(DateUtil.format(orderInfo.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
        if(OrderInfo.PAYFORSTATUS2.equals(orderInfo.getPayforStatus())){//待发货
        	OrderFlow orderFlow = orderFlowManager.getByOrderAndStatus(orderId, OrderFlow.OPERATETYPE4);
        	if(orderFlow != null){
        		timeStr.append("\n\n").append("支付时间：").append(DateUtil.format(orderFlow.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
        	}
        }
        if(OrderInfo.PAYFORSTATUS3.equals(orderInfo.getPayforStatus())){//已发货
        	OrderFlow orderFlow = orderFlowManager.getByOrderAndStatus(orderId, OrderFlow.OPERATETYPE5);
        	if(orderFlow != null){
        		timeStr.append("\n\n").append("发货时间：").append(DateUtil.format(orderFlow.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
        	}
        }
        orderInfoData.setTimeStr(timeStr.toString());
        return orderInfoData;
	}
	/**
	 * Title:根据传入的时间判断离订单过期还有多少毫秒，如果返回null则表示已经过期
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月24日
	 * @param date
	 * @return
	 */
	private Long getTimeDiff(Date date){
		Long a = new Date().getTime(),
			b = date.getTime() + OrderInfo.payExprieTime;
		if(a > b){//开始时间都大于结束时间了，说明都已经过期了
			return null;
		}
		return b - a;
	}

	@Override
	@Transactional(value="transactionManager")
	public OrderInfo create(Long appId, String sessionId, String token, Long recAddressId, String goodsIds, String goodsNums, String descn){
    	UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        //为了获得用户名
        UserAccount userAccount = userAccountDao.get(userSession.getUserId());
        //为了获得用户手机号
        UserInfo userInfo = userInfoDao.get(userSession.getUserId());
        if (userAccount == null || userInfo == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        Date now = new Date();
        
        //处理商品
        String[] ids = goodsIds.split(","),
        		nums = goodsNums.split(",");
        if(ids.length != nums.length){//如果商品id和商品数量不对，说明参数有误
        	throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
        }
        ShoppingGoods shoppingGoods;
        OrderGoods orderGoods;
        List<OrderGoods> orderGoodsList = Lists.newArrayList();
        List<Long> goodsIdList = Lists.newArrayList();//商品id集合，删除购物车用
        int fare = 0,//运费
    		quantity = 0,//商品数量
    		totalGoodsMoney = 0;//商品总金额
        for(int i = 0; i < ids.length; i++){
        	quantity = Integer.parseInt(nums[i]);
        	shoppingGoods = shoppingGoodsDao.get(Long.parseLong(ids[i]));
        	if(shoppingGoods == null || !ShoppingGoods.STATUS3.equals(shoppingGoods.getStatus())){
            	throw new BusinessException(ErrorCodes.FAILURE, "商品不存在");
            }
        	if(shoppingGoods.getStock() < quantity){
        		throw new BusinessException(-2, shoppingGoods.getName() + "库存不足");
        	}
        	//订单商品(缺少订单ID)
        	orderGoods = getOrderGoods(shoppingGoods, quantity, now);
        	orderGoodsList.add(orderGoods);
        	//运费
        	fare += getShippingFare(recAddressId, shoppingGoods, quantity);
        	//计算订单的总金额
        	totalGoodsMoney += orderGoods.getGoodsPrice() * orderGoods.getQuantity();//商品总金额
        	goodsIdList.add(shoppingGoods.getId());
        	//减去库存和增加销量
        	shoppingGoodsDao.updateStockAndSalesVolume(orderGoods.getQuantity()*-1, orderGoods.getQuantity(), shoppingGoods.getId());
        }
        //用户选择的收货地址
        ShoppingUserAddress shoppingUserAddress = shoppingUserAddressDao.get(recAddressId);
        
        //订单主信息（OrderInfo）
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAppId(appId);
        orderInfo.setUserId(userSession.getUserId());
        orderInfo.setOrderNo(NumberUtil.getOrderNo(userSession.getUserId()));
        orderInfo.setOrderAmount(totalGoodsMoney + fare);//商品的金额加上运费
        orderInfo.setShippingFare(fare);//运费
        orderInfo.setDescn(descn);
        orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS1);
        orderInfo.setStatus(OrderInfo.STATUS3);
        orderInfo.setUserName(userInfo.getName());
        orderInfo.setUserPhone(StringUtils.isBlank(userAccount.getTelephone()) ? shoppingUserAddress.getCellphone() : userAccount.getTelephone());
  		orderInfo.setReceiverName(shoppingUserAddress.getRecivier());
  		orderInfo.setReceiverPhone(shoppingUserAddress.getCellphone());
  		orderInfo.setReceiverAddress(shoppingUserAddress.getFullAddress());
  		orderInfo.setCreateTime(now);
  		orderInfo.setUpdaterId(userSession.getUserId());
  		orderInfo = save(orderInfo);
        
        //保存订单商品信息（OrderGoods）
        for(OrderGoods og : orderGoodsList){
        	og.setOrderId(orderInfo.getId());
        }
        orderGoodsManager.saves(orderGoodsList);
        
        //保存订单流水表信息（OrderFlow）
        saveOrderFlow(orderInfo, OrderFlow.OPERATETYPE1, OrderFlow.USERTYPE1, now, userSession.getUserId(), null);
		//订单金额明细
        List<OrderPriceDetail> orderPriceDetails = Lists.newArrayList();
        orderPriceDetails.add(getOrderPriceDetail(orderInfo.getId(), userSession.getUserId(), OrderPriceDetail.DISCOUNTTYPE1, totalGoodsMoney, null, now));
        orderPriceDetails.add(getOrderPriceDetail(orderInfo.getId(), userSession.getUserId(), OrderPriceDetail.DISCOUNTTYPE2, fare, null, now));
        orderPriceDetailDao.saves(orderPriceDetails);
        //删除购物车
        orderShopCartDao.deleteByGoodsIds(goodsIdList);
        return orderInfo;
	}
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月29日
	 * @param orderId
	 * @param userId
	 * @param discountType
	 * @param money
	 * @param descn
	 * @param createTime
	 * @return
	 */
	private OrderPriceDetail getOrderPriceDetail(Long orderId, Long userId, Byte discountType, Integer money, String descn, Date createTime){
		OrderPriceDetail detail = new OrderPriceDetail();
		detail.setOrderId(orderId);
		detail.setUserId(userId);
		detail.setDiscountType(discountType);
		detail.setDiscountAmount(money);
		detail.setDescn(descn);
		detail.setCreateTime(createTime);
		return detail;
	}
	
	/**
	 * Title:根据用户收货地址、商品选择的运费模板和商品数量计算出运费
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月28日
	 * @param shoppingUserAddressId 用户选择的收货地址id
	 * @param shippingFareTemplateId 商品的模板id
	 * @param amount 商品数量
	 * @return 返回的运费单位为分哦。注意cms运费模板设置的运费单位是元。
	 */
	public int getShippingFare(Long shoppingUserAddressId, ShoppingGoods shoppingGoods, Integer amount){
		int fare = 0;
		if(ShoppingGoods.ISFREESHIPPING1.equals(shoppingGoods.getIsFreeShipping())){//包邮
		    return fare;
		}
		if(ShoppingGoods.ISFREESHIPPING2.equals(shoppingGoods.getIsFreeShipping())){//满多少包邮
			//满多少包邮
			Integer freeShippingPrice = shoppingGoods.getFreeShippingPrice() == null ? 0 : shoppingGoods.getFreeShippingPrice();
			if(amount * shoppingGoods.getPrice() >= freeShippingPrice){
				return fare;//包邮
			}
		}
		//下面是不满足包邮的情况。
		Long shippingFareTemplateId = shoppingGoods.getShippingFareTemplateId();
		//获得运费模板，取得运费模板区域2级，如果没有就取基础模板
		ShoppingFareTemplateDetail detail = null;
		if(shoppingUserAddressId == null){//如果没有收货地址就只去读基本的运费模板
			detail = shoppingFareTemplateDetailDao.getBaseFareByShoppingFareTempleteId(shippingFareTemplateId);
		}else{//如果有收货地址
			detail = shoppingFareTemplateDetailDao.getFareByUserAddress(shoppingUserAddressId, shippingFareTemplateId);
			if(detail == null){//如果没有根据地区配置，就获取基本运费模板
				detail = shoppingFareTemplateDetailDao.getBaseFareByShoppingFareTempleteId(shippingFareTemplateId);
			}
		}
		if(detail != null){
			if(detail.getBaseFareCount() >= amount){//amount件以内
				fare = detail.getBaseFare();
			}else{//超出了基本运费数量，每增加多少件，增加多少元
				Double a = (Math.ceil((amount - detail.getBaseFareCount())/detail.getAddFareCount()));
				fare = detail.getBaseFare()  + a.intValue() * detail.getAddFare();
			}
		}
		/*由于cms运费模板设置的金额单位是元，而商品统一用的分，所以这里把返回的运费转成分为单位，方便统一运算*/
		fare = fare * 100;
		return fare;
	}
	
	/**
	 * Title:返回订单商品对象
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param shoppingGoods
	 * @param quantity
	 * @param now
	 * @return
	 */
	private OrderGoods getOrderGoods(ShoppingGoods shoppingGoods, Integer quantity, Date now){
		OrderGoods orderGoods = new OrderGoods();
		orderGoods.setGoodsId(shoppingGoods.getId());
		orderGoods.setIsEvaluate(OrderGoods.ISEVALUATE0);
		orderGoods.setRefundStatus(OrderGoods.REFUNDSTATUS1);
		orderGoods.setGoodsName(shoppingGoods.getName());
		orderGoods.setGoodsPrice(shoppingGoods.getPrice());
		orderGoods.setOriginalPrice(shoppingGoods.getOriginalPrice());
		orderGoods.setGoodsImageUrl(shoppingGoods.getMinImageUrl());
		orderGoods.setQuantity(quantity);
		orderGoods.setCreateTime(now);
		return orderGoods;
	}
	
	/**
	 * Title:保存订单流水记录表
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月28日
	 * @param orderInfo
	 * @param operateType
	 * @param userType
	 * @param now
	 * @param userId
	 * @param description
	 * @return
	 */
	private OrderFlow saveOrderFlow(OrderInfo orderInfo, Byte operateType, Byte userType, Date now, Long userId, String description){
		//保存订单流水表信息（OrderFlow）
		return orderFlowManager.save(getOrderFlow(orderInfo, operateType, userType, now, userId, description));
	}
	
	/**
	 * Title:组合订单流水记录对象
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月30日
	 * @param orderInfo
	 * @param operateType
	 * @param userType
	 * @param now
	 * @param userId
	 * @param description
	 * @return
	 */
	private OrderFlow getOrderFlow(OrderInfo orderInfo, Byte operateType, Byte userType, Date now, Long userId, String description){
		//保存订单流水表信息（OrderFlow）
        OrderFlow orderFlow = new OrderFlow();
        orderFlow.setAppId(orderInfo.getAppId());
		orderFlow.setOrderId(orderInfo.getId());
		orderFlow.setOperateType(operateType);
		orderFlow.setDescription(description);
		orderFlow.setUserType(userType);
		orderFlow.setUserId(userId);
		orderFlow.setCreateTime(now == null ? new Date() : now);
		return orderFlow;
	}

	@Override
	@Transactional(value="transactionManager")
	public OrderConfirmDarta confirmOrderByUser(Long appId, String sessionId, String token, Long recAddressId, String goodsIds, String goodsNums) {
    	UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        boolean isNotAddress = false;//如果recAddressId不为空说明是选择地址的时候调用的这个接口，不需要再返回商品信息了。
        ShoppingUserAddress shoppingUserAddress = null;
        if(recAddressId == null){// 如果没传收货地址就取用户默认的收货地址
        	isNotAddress = true;
        	//获取用户默认的收货地址
        	shoppingUserAddress = shoppingUserAddressDao.getUserDefault(appId, userSession.getUserId());
        	recAddressId = shoppingUserAddress != null ? shoppingUserAddress.getId() : null;
        }else{
        	shoppingUserAddress = shoppingUserAddressDao.get(recAddressId);
        }
        
		//处理商品
        String[] ids = goodsIds.split(","),
        		nums = goodsNums.split(",");
        if(ids.length != nums.length){//如果商品id和商品数量不对，说明参数有误
        	throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
        }
        Integer quantity = 0, fare = 0, totalMoney = 0;
        ShoppingGoods shoppingGoods;
        List<OrderGoodsData> goodsDataList = Lists.newArrayList();
        OrderGoodsData orderGoodsData;
        for(int i = 0; i < ids.length; i++){
        	quantity = Integer.parseInt(nums[i]);
        	shoppingGoods = shoppingGoodsDao.get(Long.parseLong(ids[i]));
        	if(shoppingGoods == null || !ShoppingGoods.STATUS3.equals(shoppingGoods.getStatus())){
            	throw new BusinessException(ErrorCodes.FAILURE, "商品不存在");
            }
        	if(shoppingGoods.getStock() < quantity){
        		throw new BusinessException(-2, shoppingGoods.getName() + "库存不足");
        	}
        	//运费，如果是多个商品就去运费最高的那个
//        	fareTmp = getShippingFare(recAddressId, shoppingGoods, quantity);
//        	if(fareTmp > fare){//取运费最高的一个
//        		fare = fareTmp;
//        	}
        	fare += getShippingFare(recAddressId, shoppingGoods, quantity);
        	//计算订单的总金额
        	totalMoney += shoppingGoods.getPrice() * quantity;
        	
        	if(isNotAddress){
        		//商品列表集合
        		orderGoodsData = new OrderGoodsData();
        		orderGoodsData.setGoodsId(shoppingGoods.getId());
        		orderGoodsData.setGoodsName(shoppingGoods.getName());
        		orderGoodsData.setGoodsPrice(CurrencyUtil.format(shoppingGoods.getPrice(), 2, 2, true));
        		orderGoodsData.setOriginalPrice(CurrencyUtil.format(shoppingGoods.getOriginalPrice(), 2, 2, true));
        		orderGoodsData.setQuantity(quantity);
        		orderGoodsData.setRefundStatus(OrderGoods.REFUNDSTATUS1);
        		orderGoodsData.setGoodsImageUrl(shoppingGoods.getMinImageUrl() == null ? "" : shoppingGoods.getMinImageUrl());
        		goodsDataList.add(orderGoodsData);
        	}
        }
        OrderConfirmDarta data = new OrderConfirmDarta();
        data.setGoodsList(goodsDataList);//商品列表
        data.setShippingFare(CurrencyUtil.format(fare, 2, 2, true));//运费
        data.setOrderMoney(CurrencyUtil.format(totalMoney, 2, 2, true));//订单金额（不含运费）
        data.setTotalMoney(CurrencyUtil.format(totalMoney + fare, 2, 2, true));//订单总金额（含运费）
        OrderUserAddressData orderUserAddress = new OrderUserAddressData();
        if(shoppingUserAddress != null){
        	orderUserAddress.setOrderUserAddressId(shoppingUserAddress.getId());
        	orderUserAddress.setReceiverName(shoppingUserAddress.getRecivier());
        	orderUserAddress.setReceiverPhone(shoppingUserAddress.getCellphone());
        	orderUserAddress.setReceiverAddress(shoppingUserAddress.getFullAddress());
        }
        data.setRecAddress(orderUserAddress);
		return data;
	}

	@Override
	public Map<String, String> getApplyRefund(Long appId, String sessionId, String token, Long orderId, Long goodsId) {
		UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        OrderInfo orderInfo = get(orderId);
        if(orderInfo == null || !orderInfo.getUserId().equals(userSession.getUserId())){
        	throw new BusinessException(-2, "订单不存在");
        }
        OrderGoods orderGoods = orderGoodsManager.getByOrderAndGoods(orderId, goodsId);
        if(orderGoods == null || !OrderGoods.REFUNDSTATUS1.equals(orderGoods.getRefundStatus())){
        	throw new BusinessException(-3, "订单不存在该商品");
        }
        String refundMoneyStr = CurrencyUtil.format((orderGoods.getGoodsPrice() * orderGoods.getQuantity()), 2, 2, true);
        Map<String, String> map = Maps.newHashMap();
        map.put("moneyStr", refundMoneyStr);
//        map.put("payAccount", orderInfo.getPayAccount());
        return map;
	}
	
	@Override
	@Transactional(value="transactionManager")
	public void applyRefundByUser(Long appId, String sessionId, String token, Long orderId, Long goodsId, String payAccount, String description, String images) {
    	UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        OrderInfo orderInfo = get(orderId);
        if(orderInfo == null || !orderInfo.getUserId().equals(userSession.getUserId())){
        	throw new BusinessException(-2, "订单不存在");
        }
        OrderGoods orderGoods = orderGoodsManager.getByOrderAndGoods(orderId, goodsId);
        if(orderGoods == null){
        	throw new BusinessException(-3, "订单不存在该商品");
        }
        if(!OrderGoods.REFUNDSTATUS1.equals(orderGoods.getRefundStatus())){
        	throw new BusinessException(-4, "该商品已经申请过退款");
        }
        Date now = new Date();
        orderGoods.setRefundStatus(OrderGoods.REFUNDSTATUS2);
        //修改商品状态
        orderGoodsManager.update(orderGoods);
		//订单流水记录
		saveOrderFlow(orderInfo, OrderFlow.OPERATETYPE6, OrderFlow.USERTYPE1, now, userSession.getUserId(), "用户申请退款");
		//订单退货表
		OrderRefund orderRefund = new OrderRefund();
		orderRefund.setOrderId(orderInfo.getId());
		orderRefund.setOrderNo(orderInfo.getOrderNo());
		orderRefund.setGoodsId(orderGoods.getGoodsId());
		orderRefund.setGoodsName(orderGoods.getGoodsName());
		orderRefund.setGoodsPrice(orderGoods.getGoodsPrice());
		orderRefund.setOriginalPrice(orderGoods.getOriginalPrice());
		orderRefund.setUserPayAccount(payAccount);//买家支付帐号
		orderRefund.setRefundAmount(orderGoods.getGoodsPrice() * orderGoods.getQuantity());//需求没说用户可以填写数量，所以这里是买多少退多少。
		orderRefund.setQuantity(orderGoods.getQuantity());
		orderRefund.setRefundReason(description);
		orderRefund.setGoodsImageUrl(orderGoods.getGoodsImageUrl());
		orderRefund.setCreateTime(now);
		//处理订单状态
		List<OrderGoods> list = orderGoodsManager.getByExceptRefundAndGoodsId(orderId, goodsId);
		if(list == null || list.size() == 0){//说明订单已经退货完了，改变订单的状态为退货中
			orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS7);
			update(orderInfo);
		}
		
		//图片处理
		//保存图片，返回图片路径
		if(StringUtils.isNotBlank(images)){
			String[] imgArray = images.split(",");
			StringBuilder imageStr = new StringBuilder();
			String filePath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH);
			String modulePath = handleModulePath("order", appId);
			if (!filePath.endsWith(File.separator)) {
				filePath += File.separator;
			}
			File destFile = new File(filePath);
			destFile.mkdirs();
			int w, h;	//切图基准：{多图: 226x226}
			for (String img : imgArray) {
				String fileName = StringUtil.getUUID() + ".jpg";
				File file = Base64Util.decodeBase64(img.replaceAll("\\s*|\t|\r|\n", ""), modulePath + fileName, filePath);
				//切图 begin
				w = h = 226;
				String cutFilePath = ImageUtil.appendSuffixBySize(w, h, file.getPath());
				File cutFile = new File(cutFilePath);
				ImageUtil.cutForceSize(w, h, file, cutFile);
				//切图 end
				String url = PropertiesConfig.getString(PropertyKey.FILE_URL_PATH);
				if (!url.endsWith(File.separator)) {
					url += File.separator;
				}
				String fIleUrl = cutFilePath.replace(filePath, url);
				if(imageStr.length() > 0){
					imageStr.append(",");
				}
				imageStr.append(fIleUrl.replace("\\", "/"));
			}
			orderRefund.setRefundImagesUrl(imageStr.toString());
		}
		orderRefundDao.save(orderRefund);
	}
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月29日
	 * @param modulePath
	 * @param appId
	 * @return
	 */
	private String handleModulePath(String modulePath, Long appId) {
		if (StringUtil.isEmpty(modulePath)) {
			modulePath = "common";
		}
		StringBuilder modulePathBuilder = new StringBuilder();
		modulePathBuilder.append(File.separator).append("app_").append(null == appId ? 0 : appId);
		modulePathBuilder.append(File.separator).append("server");
		return modulePathBuilder.toString();
    }

	@Override
	public OrderRefundData getRefundProgress(Long appId, String sessionId, String token, Long orderId, Long goodsId) {
		UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        OrderInfo orderInfo = get(orderId);
        if(orderInfo == null || !orderInfo.getUserId().equals(userSession.getUserId())){
        	throw new BusinessException(-2, "订单不存在");
        }
        Map<String, Object> searchMap = Maps.newHashMap();
        searchMap.put("EQ_orderId", orderId);
        searchMap.put("EQ_goodsId", goodsId);
        List<OrderRefund> orderRefundList = orderRefundDao.query(searchMap, null);
        if(orderRefundList == null || orderRefundList.size() != 1){
        	throw new BusinessException(-4, "退款信息不存在");
        }
        OrderRefund orderRefund = orderRefundList.get(0);
        OrderRefundData data = new OrderRefundData();
        data.setRefundMoneyStr(CurrencyUtil.format(orderRefund.getRefundAmount(), 2, 2, true));
        data.setGoodsId(orderRefund.getGoodsId());
        data.setGoodsName(orderRefund.getGoodsName());
        data.setGoodsPrice(CurrencyUtil.format(orderRefund.getGoodsPrice(), 2, 2, true));
        data.setOrderNo(orderRefund.getOrderNo());
        data.setQuantity(orderRefund.getQuantity());
        data.setGoodsImageUrl(orderRefund.getGoodsImageUrl());
        
        Map<String, String> map = Maps.newHashMap();
        List<Map<String, String>> list = Lists.newArrayList();
        map.put("timeStr", DateUtil.format(orderRefund.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        map.put("info", "已提交退款申请，等待卖家确认");
        list.add(map);
        if(orderRefund.getRefuseTime() != null && StringUtils.isNotBlank(orderRefund.getRefuseReason())){
        	map = Maps.newHashMap();
        	map.put("timeStr", DateUtil.format(orderRefund.getRefuseTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
            map.put("info", "卖家拒绝退款原因：" + orderRefund.getRefuseReason());
            list.add(map);
        }else if(orderRefund.getAgreeTime() != null){
        	map = Maps.newHashMap();
        	map.put("timeStr", DateUtil.format(orderRefund.getAgreeTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        	map.put("info", "卖家已确认退款，请注意查看您的账户");
        	list.add(map);
        }
        data.setStatusList(list);
        return data;
	}

	@Override
	@Transactional(value="transactionManager")
	public void confirmReceipt(Long appId, String sessionId, String token, Long orderId) {
		UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
		OrderInfo orderInfo = get(orderId);
		if(orderInfo == null || !orderInfo.getUserId().equals(userSession.getUserId())){
			throw new BusinessException(ErrorCodes.FAILURE, "订单不存在");
		}
		if(!OrderInfo.PAYFORSTATUS3.equals(orderInfo.getPayforStatus())){
			throw new BusinessException(-2, "订单信息有误");
		}
		orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS4);
		update(orderInfo);
		//记录订单流水
		saveOrderFlow(orderInfo, OrderFlow.OPERATETYPE9, OrderFlow.USERTYPE1, null, userSession.getUserId(), "用户确认收货");
	}

	@Override
	@Transactional(value="transactionManager")
	public void alipayCallback(OrderInfo orderInfo, Map<String, String> map) {
		Date now = new Date();
		OrderAlipayNotifyLog log = new OrderAlipayNotifyLog();
		log.setCreateTime(now);
		log.setNotifyTime(DateUtil.parseDate(map.get("notify_time")));
		log.setNotifyType(map.get("notify_type"));
		log.setNotifyId(map.get("notify_id"));
		log.setPayAppId(map.get("app_id"));
		log.setCharset(map.get("charset"));
		log.setVersion(map.get("version"));
		log.setSignType(map.get("sign_type"));
		log.setSign(map.get("sign"));
		log.setTradeNo(map.get("trade_no"));
		log.setOutTradeNo(map.get("out_trade_no"));
		log.setBuyerId(map.get("buyer_id"));
		log.setBuyerLogonId(map.get("buyer_logon_id"));
		log.setSellerId(map.get("seller_id"));
		log.setSellerEmail(map.get("seller_email"));
		log.setTradeStatus(map.get("trade_status"));
		log.setTotalAmount(Double.parseDouble(map.get("total_amount")));
		log.setReceiptAmount(Double.parseDouble(map.get("receipt_amount")));
		log.setInvoiceAmount(Double.parseDouble(map.get("invoice_amount")));
		log.setBuyerPayAmount(Double.parseDouble(map.get("buyer_pay_amount")));
		log.setSubject(map.get("subject"));
		log.setBody(map.get("body"));
		log.setGmtCreate(DateUtil.parseDate(map.get("gmt_create")));
		log.setGmtPayment(DateUtil.parseDate(map.get("gmt_payment")));
		log.setFundBillList(map.get("fund_bill_list"));
		orderAlipayNodifyLogDao.save(log);
		if(OrderAlipayNotifyLog.TRADE_STATUS_3.equals(map.get("trade_status"))){//支付宝返回支付成功状态后才保存订单流水记录等信息
			//保存订单流水表信息（OrderFlow）
			saveOrderFlow(orderInfo, OrderFlow.OPERATETYPE4, OrderFlow.USERTYPE1, now, orderInfo.getUserId(), "支付成功后，支付宝异步回调接口");
			//修改订单状态
			orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS2);
			orderInfo.setPayAccount(log.getBuyerId());
			orderInfo.setPayMode(OrderInfo.PAYMODE1);
			save(orderInfo);
			//订单合并支付表
			OrderPay orderPay = new OrderPay();
			orderPay.setOrderId(orderInfo.getId());
			orderPay.setOrderPayNo(log.getTradeNo());
			orderPay.setCreateTime(now);
			orderPayDao.save(orderPay);
			
			//收支记录
			OrderIncomeRecords orderIncomeRecords = new OrderIncomeRecords();
			orderIncomeRecords.setAppId(orderInfo.getAppId());
			orderIncomeRecords.setOrderId(orderInfo.getId());
			orderIncomeRecords.setOrderNo(orderInfo.getOrderNo());
			orderIncomeRecords.setMoney(orderInfo.getOrderAmount());
			orderIncomeRecords.setPayMode(OrderIncomeRecords.PAYMODE1);
			orderIncomeRecords.setCreateTime(now);
			Integer totalMoney = orderIncomeRecordsDao.getTotalByAppId(orderInfo.getAppId());
			totalMoney = (totalMoney == null ? 0 : totalMoney) + orderInfo.getOrderAmount();
			orderIncomeRecords.setTotalMoney(totalMoney);
			orderIncomeRecordsDao.save(orderIncomeRecords);
		}
	}

	@Override
	@Transactional(value="transactionManager")
	public void payExpiredTask() {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_payforStatus", OrderInfo.PAYFORSTATUS1);
		map.put("EQ_status", OrderInfo.STATUS3);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		List<OrderInfo> list = query(map, sortMap);
		if(list != null && list.size() > 0){
			List<Long> ids = Lists.newArrayList();
			List<OrderFlow> orderFlowList = Lists.newArrayList();
			Date now = new Date();
			for(OrderInfo orderInfo : list){
				if(getTimeDiff(orderInfo.getCreateTime()) == null){
					ids.add(orderInfo.getId());
					orderFlowList.add(getOrderFlow(orderInfo, OrderFlow.OPERATETYPE2, OrderFlow.USERTYPE2, now, 0L, "订单支付超时自动过期"));
				}
			}
			if(ids.size() > 0){
				Long[] idsLong = (Long[]) ids.toArray();
				List<OrderGoods> goodsList = orderGoodsManager.getByOrders(idsLong);
				for(OrderGoods orderGoods : goodsList){//还原库存
					shoppingGoodsDao.updateStockAndSalesVolume(orderGoods.getQuantity(), orderGoods.getQuantity()*-1, orderGoods.getGoodsId());
				}
				//修改订单状态
				this.getEntityDao().updatePayForStatus(OrderInfo.PAYFORSTATUS5, ids);
				//保存订单流水记录
				orderFlowManager.saves(orderFlowList);
			}
		}
	}

	@Override
	public String getAlipayConfig(Long appId, String sessionId, String token, Long orderId) {
		UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        OrderInfo orderInfo = get(orderId);
        if(orderInfo == null || !OrderInfo.PAYFORSTATUS1.equals(orderInfo.getPayforStatus())){
        	throw new BusinessException(-2, "订单不存在或已过期");
        }
        if(appId != orderInfo.getAppId()){
        	throw new BusinessException(-3, "app信息错误");
        }
        OrderPayConfig config = orderPayConfigDao.getByAppId(orderInfo.getAppId(), OrderPayConfig.PAYMODEL1);
        if(config == null){
        	throw new BusinessException(-4, "商户支付信息有误");
        }
        Date now = new Date();
        
        Map<String, String> keyValues = Maps.newHashMap();
        keyValues.put("app_id", config.getPayAppId());
        
        Map<String, String> map = Maps.newHashMap();
        map.put("subject", orderInfo.getOrderNo());
        map.put("out_trade_no", orderInfo.getOrderNo());
//        map.put("total_amount", CurrencyUtil.format(orderInfo.getOrderAmount()));//TODO:上线后用正式的订单金额
        map.put("total_amount", CurrencyUtil.format(1));//TODO：测试的时候用1分钱
        map.put("product_code", "QUICK_MSECURITY_PAY");
//        map.put("goods_type", "1");
		keyValues.put("biz_content", JSONObject.toJSONString(map));
		
		keyValues.put("charset", "utf-8");
        keyValues.put("method", "alipay.trade.app.pay");
        String serverPath = PropertiesConfig.getString(PropertyKey.SERVER_URL_PATH);
		keyValues.put("notify_url", serverPath + "order/alipayCallback.html");
        keyValues.put("format", "JSON");
		keyValues.put("sign_type", "RSA");
		keyValues.put("timestamp", DateUtil.format(now, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
		keyValues.put("version", "1.0");
		
		try {
			//获取参数字符串
			String params = getSignContent(keyValues);
			//签名
			String sign = AlipaySignature.rsaSign(keyValues, config.getPrivateKey(), "UTF-8");
			params = params + "&sign=" + URLEncoder.encode(sign, "UTF-8");
			return params;
		} catch (AlipayApiException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new BusinessException(-2222, "获取支付配置失败");
		}
	}
	
	/**
	 * Title:支付宝支付参数组合和encode
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月8日
	 * @param sortedParams
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String getSignContent(Map<String, String> sortedParams) throws UnsupportedEncodingException {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (com.alipay.api.internal.util.StringUtils.areNotEmpty(key, value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + URLEncoder.encode(value, "UTF-8"));
                index++;
            }
        }
        return content.toString();
    }
	
	@Override
	@Transactional(value="transactionManager")
	public void deliverGoods(Long orderId, String expressCompany, String expressNo, Long userId) {
		OrderInfo orderInfo = get(orderId);
		if(orderInfo == null || !OrderInfo.PAYFORSTATUS2.equals(orderInfo.getPayforStatus()) || !OrderInfo.STATUS3.equals(orderInfo.getStatus())){
			throw new BusinessException(ErrorCodes.FAILURE, "订单不存在，或状态已经改变，请刷新");
		}
		Date now = new Date();
		orderInfo.setExpressCompany(expressCompany);
		orderInfo.setExpressNo(expressNo);
		orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS3);
		orderInfo.setUpdaterId(userId);
		orderInfo.setOperateTime(now);
		update(orderInfo);
		saveOrderFlow(orderInfo, OrderFlow.OPERATETYPE5, OrderFlow.USERTYPE2, now, userId, "");
	}
	
	@Override
	public PageInfo<OrderInfoDto> queryForPage(PageInfo<OrderInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap){
		PageInfo<OrderInfoDto> page = this.getEntityDao().queryForPage(pageInfo, searchMap, sortMap);
		List<OrderInfoDto> list = page.getPageResults();
		if(list != null && list.size() > 0){
			for(OrderInfoDto dto : list){
			    dto.setOrderAmount(dto.getOrderAmount() - dto.getShippingFare());//列表的订单金额显示成不包含运费的金额
				//查询订单是否存在有退货的商品，用于列表退货功能，为了性能除开待支付、已完成和已取消的状态不查询，因为这两个状态不可能出现退货的
				if(!OrderInfo.PAYFORSTATUS1.equals(dto.getPayforStatus()) && !OrderInfo.PAYFORSTATUS5.equals(dto.getPayforStatus()) && !OrderInfo.PAYFORSTATUS6.equals(dto.getPayforStatus())){
					dto.setRefundStatus(orderGoodsManager.getRefundGoodsByOrder(dto.getId()) != null ? OrderGoods.REFUNDSTATUS2 : OrderGoods.REFUNDSTATUS1);
				}
			}
		}
		return page;
	}

    @Override
    @Transactional(value="transactionManager")
    public void refundByAdmin(Long[] ids, String type, String[] contents, Long userId) {
        if(ids != null){
            OrderRefund orderRefund;
            OrderGoods orderGoods;
            Date now = new Date();
            Long orderId = null;
            for(int i = 0; i < ids.length; i ++){
                orderRefund = orderRefundDao.get(ids[i]);
                if(orderRefund == null){
                    throw new BusinessException(ErrorCodes.FAILURE, "退款记录不存在");
                }
                orderGoods = orderGoodsManager.getByOrderAndGoods(orderRefund.getOrderId(), orderRefund.getGoodsId());
                if(orderGoods == null){
                    throw new BusinessException(ErrorCodes.FAILURE, "订单不存在该商品");
                }
                orderId = orderGoods.getOrderId();
                if("1".equals(type)){//同意退款
                    orderRefund.setAgreeReason(contents[i]);
                    orderRefund.setAgreeTime(now);
                }else if("2".equals(type)){//拒绝退款
                    if(StringUtils.isBlank(contents[i])){
                        throw new BusinessException(ErrorCodes.FAILURE, "请输入拒绝退款的原因");
                    }
                    orderRefund.setRefuseReason(contents[i]);
                    orderRefund.setRefuseTime(now);
                }
                orderRefundDao.update(orderRefund);
                orderGoods.setRefundStatus("1".equals(type) ? OrderGoods.REFUNDSTATUS3 : OrderGoods.REFUNDSTATUS4);
                orderGoodsManager.update(orderGoods);
            }
//          判断订单下面还有其他商品吗，如果没有就把这个订单状态改成完成
//            就是判断订单是否退完了。
            OrderInfo orderInfo = get(orderId);
            if(orderId != null){
                List<OrderGoods> orderGoodsList = orderGoodsManager.getByOrder(orderId);
                if(ids.length == orderGoodsList.size()){
                    orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS6);
                    orderInfo.setUpdaterId(userId);
                    orderInfo.setOperateTime(now);
                    update(orderInfo);
                }
            }
            //记录订单流水信息
            saveOrderFlow(orderInfo, "1".equals(type) ? OrderFlow.OPERATETYPE7 : OrderFlow.OPERATETYPE8, OrderFlow.USERTYPE2, now, userId, ids != null ? ids.toString() : "");
        }
    }

	/*@Override
	@Transactional(value="transactionManager")
	public void refundByAdmin(Long orderId, Long goodsId, String type, String content, Long userId) {
		OrderInfo orderInfo = get(orderId);
		if(orderInfo == null){
			throw new BusinessException(-1, "订单不存在");
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_orderId", orderId);
		map.put("EQ_goodsId", goodsId);
		List<OrderRefund> orderRefunds = orderRefundDao.query(map, null);
		if(orderRefunds == null || orderRefunds.size() == 0){
			throw new BusinessException(-2, "退款信息不存在");
		}
		Date now = new Date();
		for(OrderRefund orderRefund : orderRefunds){
			if("1".equals(type)){//同意退款
				orderRefund.setAgreeReason(content);
				orderRefund.setAgreeTime(now);
			}else if("2".equals(type)){//拒绝退款
				orderRefund.setRefuseReason(content);
				orderRefund.setRefuseTime(now);
			}
			orderRefundDao.update(orderRefund);
		}
		//判断订单下面还有其他商品吗，如果没有就把这个订单状态改成完成
		//就是判断订单是否退完了。
		List<OrderGoods> goodsList = orderGoodsManager.getByOrder(orderId);
		int refundSize = 0;
		if(goodsList != null){
			for(OrderGoods orderGoods : goodsList){//更新订单商品关联表商品的退货状态
				if(OrderGoods.REFUNDSTATUS2.equals(orderGoods.getRefundStatus()) && (goodsId == null || orderGoods.getGoodsId().equals(goodsId))){
					orderGoods.setRefundStatus("1".equals(type) ? OrderGoods.REFUNDSTATUS3 : OrderGoods.REFUNDSTATUS4);
					orderGoodsManager.update(orderGoods);
					refundSize ++;
				}
			}
		}
		if(goodsList != null && goodsList.size() == refundSize){
			orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS6);
			orderInfo.setUpdaterId(userId);
			orderInfo.setOperateTime(now);
			update(orderInfo);
		}
		saveOrderFlow(orderInfo, "1".equals(type) ? OrderFlow.OPERATETYPE7 : OrderFlow.OPERATETYPE8, OrderFlow.USERTYPE2, now, userId, goodsId != null ? goodsId.toString() : "");
	}*/

	@Override
	@Transactional(value="transactionManager")
	public void cancelByAdmin(Long userId, Long[] ids) {
		List<OrderInfo> orderInfoList = getByIds(ids);
		//处理订单流水记录
    	Date now = new Date();
    	List<OrderFlow> orderFlowList = Lists.newArrayList();
    	for(OrderInfo orderInfo : orderInfoList){
    		if(!OrderInfo.PAYFORSTATUS1.equals(orderInfo.getPayforStatus())){
    			continue;
    		}
    		orderFlowList.add(getOrderFlow(orderInfo, OrderFlow.OPERATETYPE3, OrderFlow.USERTYPE1, now, userId, "管理员取消订单"));
        	//修改订单状态
    		orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS5);
			orderInfo.setUpdaterId(userId);
			orderInfo.setOperateTime(now);
			update(orderInfo);
    	}
    	orderFlowManager.saves(orderFlowList);
    	//处理商品库存
    	List<OrderGoods> orderGoodsList = orderGoodsManager.getByOrders(ids);
    	if(orderGoodsList != null && orderGoodsList.size() > 0){
    		for(OrderGoods orderGoods : orderGoodsList){
    			if(OrderGoods.REFUNDSTATUS1.equals(orderGoods.getRefundStatus())){//对未退款的还原库存
    				shoppingGoodsDao.updateStockAndSalesVolume(orderGoods.getQuantity(), orderGoods.getQuantity()*-1, orderGoods.getGoodsId());
    			}
    		}
    	}
	}

	@Override
	public PaySuccessResultData getOrderPayResult(Long appId, String sessionId, String token, Long orderId) {
		UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
		OrderInfo orderInfo = get(orderId);
		if(!userSession.getUserId().equals(orderInfo.getUserId())){
			throw new BusinessException(-22, "订单和用户不匹配");
		}
		PaySuccessResultData data = new PaySuccessResultData();
		data.setOrderId(orderInfo.getId());
		data.setPayforStatus(OrderInfo.PAYFORSTATUS2.equals(orderInfo.getPayforStatus()) ? PaySuccessResultData.PAYFORSTATUS1 : PaySuccessResultData.PAYFORSTATUS2);
		data.setOrderNo(orderInfo.getOrderNo());
		data.setPayMoney(CurrencyUtil.format(orderInfo.getOrderAmount(), 2, 2, true));
		return data;
	}

    @Override
    public void saveByAdmim(OrderInfo orderInfo) {
        OrderInfo orderInfoSouce = get(orderInfo.getId());
        if(orderInfoSouce == null){
            throw new BusinessException(ErrorCodes.FAILURE, "订单不存在");
        }
        Date now = DateUtil.now();
        orderInfoSouce.setShippingFare(orderInfo.getShippingFare());
        //修改订单金额明细表的运费
        List<OrderPriceDetail> detailPrices = getOrderPriceDetailByOrder(orderInfoSouce.getId(), OrderPriceDetail.DISCOUNTTYPE2);
        if(detailPrices != null && detailPrices.size() > 0){
            for(OrderPriceDetail detailPrice : detailPrices){
                detailPrice.setDiscountAmount(orderInfo.getShippingFare());
                orderPriceDetailDao.update(detailPrice);
                break;
            }
        }
        orderInfoSouce.setOperateTime(now);
        orderInfoSouce.setUpdaterId(orderInfo.getUpdaterId());
        update(orderInfoSouce);
    }
    
    /**
     * Title:获取订单的金额情况
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月11日
     * @param orderId
     * @param discountType
     * @return
     */
    private List<OrderPriceDetail> getOrderPriceDetailByOrder(Long orderId, Byte discountType){
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_orderId", orderId);
        map.put("EQ_discountType", discountType);
        Map<String, Boolean> sortMap = Maps.newHashMap();
        return orderPriceDetailDao.query(map, sortMap);
    }
}
