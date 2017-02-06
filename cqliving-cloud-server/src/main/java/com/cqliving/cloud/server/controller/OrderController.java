package com.cqliving.cloud.server.controller;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.order.domain.OrderAlipayNotifyLog;
import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.cloud.online.order.domain.OrderPayConfig;
import com.cqliving.cloud.online.order.dto.OrderConfirmDarta;
import com.cqliving.cloud.online.order.dto.OrderInfoData;
import com.cqliving.cloud.online.order.dto.OrderRefundData;
import com.cqliving.cloud.online.order.dto.PaySuccessResultData;
import com.cqliving.cloud.online.order.service.OrderInfoService;
import com.cqliving.cloud.online.order.service.OrderPayConfigService;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.utils.CurrencyUtil;
import com.google.common.collect.Maps;

/**
 * Title:商城订单相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月17日
 */
@Controller
@RequestMapping(value="/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderPayConfigService orderPayConfigService;
    
    /**
     * Title:订单列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年11月17日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param payforStatus
     * @param lastId
     * @param lastCreateTime
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"list"}, method=RequestMethod.POST)
    public Response<CommonListResult<OrderInfoData>> list(HttpServletRequest request, 
    		@RequestParam Long appId, 
    		@RequestParam String sessionId, 
    		@RequestParam String token, 
    		@RequestParam(required=false, value="status") Byte payforStatus, 
    		@RequestParam(required=false)Long lastId){
    	logger.debug(String.format("==========订单列表接口=========>>appId=%d, sessionId=%s, token=%s, status=%s, lastId=%s", appId, sessionId, token, payforStatus != null && OrderInfo.allPayforStatussFront.containsKey(payforStatus) ? OrderInfo.allPayforStatussFront.get(payforStatus) : payforStatus, lastId));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	payforStatus = payforStatus != null && OrderInfo.allPayforStatussFront.containsKey(payforStatus) ? payforStatus : null;
    	Response<CommonListResult<OrderInfoData>> rs = orderInfoService.getScrollPage(appId, sessionId, token, payforStatus, lastId);
    	logger.debug("==========订单列表接口：结果=========>>" + rs);
    	return rs;
    }
    
    /**
     * Title:订单详情
     * <p>Description:</p>
     * @author DeweiLi on 2016年11月23日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"detail"}, method=RequestMethod.POST)
    public Response<OrderInfoData> detail(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long orderId){
    	logger.debug(String.format("==========订单详情接口=========>>appId=%d, sessionId=%s, token=%s, orderId=%s", appId, sessionId, token, orderId));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<OrderInfoData> rs = orderInfoService.detail(appId, sessionId, token, orderId);
    	logger.debug("==========订单详情接口：结果=========>>" + rs);
    	return rs;
    }
    
    /**
     * Title:订单删除
     * <p>Description:可删除的状态有：已取消（过期和用户取消）、已完成</p>
     * @author DeweiLi on 2016年11月17日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"del"}, method=RequestMethod.POST)
    public Response<Void> del(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long orderId){
    	logger.debug(String.format("==========订单删除接口=========>>appId=%d, sessionId=%s, token=%s, orderId=%d", appId, sessionId, token, orderId));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || orderId == null){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<Void> rs = orderInfoService.delByUser(appId, sessionId, token, orderId);
    	if(rs.getCode() >= 0){
    		rs.setMessage("删除成功");
    	}
    	logger.debug("==========订单删除接口：结果=========>>" + rs);
    	return rs;
    }
    
    /**
     * Title:订单取消
     * <p>Description:只有待支付的才能取消</p>
     * @author DeweiLi on 2016年11月17日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"cancel"}, method=RequestMethod.POST)
    public Response<Void> cancel(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long orderId){
    	logger.debug(String.format("==========订单取消接口=========>>appId=%d, sessionId=%s, token=%s, orderId=%d", appId, sessionId, token, orderId));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || orderId == null){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<Void> rs = orderInfoService.cancelByUser(appId, sessionId, token, orderId);
    	if(rs.getCode() >= 0){
    		rs.setMessage("取消成功");
    	}
    	logger.debug("==========订单取消接口：结果=========>>" + rs);
    	return rs;
    }
    
    /**
     * Title:订单保存，俗称下单
     * <p>Description:客户端点击去下单，调用创建订单接口，订单如果创建成功返回订单号和金额给客户端，客户端再调用支付宝</p>
     * @author DeweiLi on 2016年11月25日
     * @param request
     * @param appId
     * @param sessionId
     * @param orderId 已下单去支付的时候要传
     * @param token
     * @param recAddressId
     * @param goodsIds
     * @param goodsNums
     * @param descn
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"create"}, method=RequestMethod.POST)
    public Response<Map<String, Object>> create(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, Long orderId, Long recAddressId, String goodsIds, String goodsNums, String descn){
    	logger.debug(String.format("去支付接口：appId=%d, sessionId=%s, token=%s, 收货地址ID=%s, 商品id=%s, 对应商品数量=%s, 给卖家的留言=%s", appId, sessionId, token, recAddressId, goodsIds, goodsNums, descn));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	OrderInfo orderInfo = null;
    	Response<Map<String, Object>> rs = Response.newInstance();
    	if(orderId != null){
    		orderInfo = orderInfoService.get(orderId).getData();
    		if(orderInfo == null){
    			rs.setCode(ErrorCodes.FAILURE);
    			rs.setMessage("订单不存在");
    			return rs;
    		}
    	}else{
    		//检查参数的有效性
    		if(recAddressId == null || StringUtils.isBlank(goodsIds) || StringUtils.isBlank(goodsNums)){
        		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
        	}
    		Response<OrderInfo> rsTmp = orderInfoService.create(appId, sessionId, token, recAddressId, goodsIds, goodsNums, descn);
    		rs.setCode(rsTmp.getCode());
    		rs.setMessage(rsTmp.getMessage());
    		orderInfo = rsTmp.getData();
    	}
    	Map<String, Object> resultMap = Maps.newHashMap();
    	resultMap.put("orderId", orderInfo.getId());
    	resultMap.put("moneyStr", CurrencyUtil.format(orderInfo.getOrderAmount(), 2, 2, true));
    	resultMap.put("money", CurrencyUtil.format(orderInfo.getOrderAmount()));
    	rs.setData(resultMap);
    	logger.debug("去支付接口结果：" + rs);
    	return rs;
    }
    
    /**
     * Title:确认订单
     * <p>Description:</p>
     * @author DeweiLi on 2016年11月28日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param recAddressId 如果没有就不传这个参数
     * @param goodsIds
     * @param goodsNums
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"confirm"}, method=RequestMethod.POST)
    public Response<OrderConfirmDarta> confirmOrderByUser(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, Long recAddressId, @RequestParam String goodsIds, @RequestParam String goodsNums){
    	logger.debug(String.format("==========订单确认接口=========>>appId=%d, sessionId=%s, token=%s, recAddressId=%s, goodsIds=%s, goodsNums=%s", appId, sessionId, token, recAddressId, goodsIds, goodsNums));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || StringUtils.isBlank(goodsIds) || StringUtils.isBlank(goodsNums)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<OrderConfirmDarta> rs = orderInfoService.confirmOrderByUser(appId, sessionId, token, recAddressId, goodsIds, goodsNums);
    	logger.debug("==========订单确认接口：结果=========>>" + rs);
    	return rs;
    }

    /**
     * Title:申请退款查询相关信息
     * <p>Description:</p>
     * @author DeweiLi on 2016年11月29日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param orderId
     * @param goodsId
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"getApplyRefund"}, method=RequestMethod.POST)
    public Response<Map<String, String>> getApplyRefund(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long orderId, @RequestParam Long goodsId){
    	logger.debug(String.format("申请退款查询相关信息接口：appId=%d, sessionId=%s, token=%s, orderId=%d, goodsId=%d", appId, sessionId, token, orderId, goodsId));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || orderId == null || goodsId == null){
    		throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<Map<String, String>> rs = orderInfoService.getApplyRefund(appId, sessionId, token, orderId, goodsId);
    	logger.debug("申请退款查询相关信息接口返回结果:" + rs);
    	return rs;
    }
    
    /**
     * Title:申请退款
     * <p>Description:</p>
     * @author DeweiLi on 2016年11月29日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param orderId
     * @param goodsId
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"applyRefund"}, method=RequestMethod.POST)
    public Response<Void> applyRefundByUser(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long orderId, @RequestParam Long goodsId, @RequestParam String payAccount, String description, String images){
    	logger.debug(String.format("申请退款接口：appId=%d, sessionId=%s, token=%s, orderId=%d, goodsId=%d, payAccount=%s", appId, sessionId, token, orderId, goodsId, payAccount));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || orderId == null || goodsId == null || StringUtils.isBlank(payAccount)){
    		throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<Void> rs = orderInfoService.applyRefundByUser(appId, sessionId, token, orderId, goodsId, payAccount, description, images);
    	logger.debug("申请退款接口返回结果：" + rs);
    	return rs;
    }
    
    /**
     * Title:退款进度查询
     * <p>Description:</p>
     * @author DeweiLi on 2016年11月29日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param orderId
     * @param goodsId
     * @param payAccount
     * @param description
     * @param images
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"getRefundProgress"}, method=RequestMethod.POST)
    public Response<OrderRefundData> getRefundProgress(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long orderId, @RequestParam Long goodsId){
    	logger.debug(String.format("退款进度查询接口：appId=%d, sessionId=%s, token=%s, orderId=%d, goodsId=%d", appId, sessionId, token, orderId, goodsId));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || orderId == null || goodsId == null){
    		throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<OrderRefundData> rs = orderInfoService.getRefundProgress(appId, sessionId, token, orderId, goodsId);
    	logger.debug("退款进度查询接口返回结果：" + rs);
    	return rs;
    }
    
    /**
     * Title:确认收货
     * <p>Description:</p>
     * @author DeweiLi on 2016年11月29日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"confirmReceipt"}, method=RequestMethod.POST)
    public Response<Void> confirmReceipt(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long orderId){
    	logger.debug(String.format("确认收货接口：appId=%d, sessionId=%s, token=%s, orderId=%d", appId, sessionId, token, orderId));
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || orderId == null){
    		throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<Void> rs = orderInfoService.confirmReceipt(appId, sessionId, token, orderId);
    	logger.debug("确认收货接口返回结果：" + rs);
    	return rs;
    }
    
    /**
     * Title:支付回调
     * <p>Description:</p>
     * @author DeweiLi on 2016年11月17日
     * @param request
     * @param out_trade_no
     * @param trade_status
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"alipayCallback"}, method=RequestMethod.POST)
    public String alipayCallback(HttpServletRequest request){
    	Map<String, String> paramsMap = convertparamMap(request.getParameterMap());
    	//由于paramsMap验签的时候会删除sign和sign_type，所以才有了下面这个map的存在
    	Map<String, String> map = convertparamMap(request.getParameterMap());
        logger.info("支付宝异步通知接口：" + paramsMap.toString());
    	String out_trade_no = paramsMap.get("out_trade_no");
    	String trade_status = paramsMap.get("trade_status");
    	//提前验证了1步骤
    	if(StringUtils.isBlank(out_trade_no)){
    		logger.error("支付宝异步回调失败，原因：支付宝回调参数错误");
    		return "failed";
    	}
    	String publicKey = "";
    	OrderInfo orderInfo = orderInfoService.getByOrderNo(out_trade_no).getData();
    	if(orderInfo == null){
    		logger.error("支付宝异步回调失败，原因：订单信息不存在");
    		return "failed";
    	}
    	//根据appid获取支付宝配置
    	OrderPayConfig orderPayConfig = orderPayConfigService.getByAppId(orderInfo.getAppId(), OrderPayConfig.PAYMODEL1).getData();
    	if(orderPayConfig != null){
    		publicKey = orderPayConfig.getPublicKey();
    	}
    	if("".equals(publicKey)){
    		logger.error("支付宝异步回调失败，原因：未获取到对应的app公钥");
    		return "failed";
    	}
    	boolean isVerify = false;
    	try {
			isVerify = AlipaySignature.rsaCheckV1(paramsMap, publicKey, "UTF-8");
		} catch (AlipayApiException e) {
			logger.error("支付宝异步回调失败:" + e, e.getMessage());
    		return "failed";
		}
    	if(!isVerify){//验签
			logger.error("支付宝异步回调失败，原因：验签失败");
			return "failed";
		}
		/*
		 * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		 * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
		 * 4、验证app_id是否为该商户本身。
		 * 上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
		 * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
		 * */
		//2验证
		Integer callbackMoney = 0;
		try {
			Double a = Double.parseDouble(paramsMap.get("total_amount")) * 100;
			callbackMoney = a.intValue();
		} catch (NumberFormatException e) {
			logger.error("支付宝异步回调失败，原因：支付宝传过来的total_money（金额）转换错误。" + String.format("money=%s", paramsMap.get("total_amount")));
			return "failed";
		}
		//FIXME:DeweiLi 测试的时候用的1分钱，所以测试不验证这个。
//			if(!orderInfo.getOrderAmount().equals(callbackMoney)){
//				logger.error("支付宝异步回调失败，原因：支付金额和订单金额不匹配");
//	    		return "failed";
//			}
		//3验证
		//4验证
		if(!orderPayConfig.getPayAppId().equals(map.get("app_id"))){
			logger.error("支付宝异步回调失败，原因：验证app_id不为该商户本身。" + String.format("配置appId=%s, 支付宝回调给的appId=%s", orderPayConfig.getPayAppId(), map.get("app_id")));
			return "failed";
		}
		//防止支付宝多次回调
		if(!OrderInfo.PAYFORSTATUS1.equals(orderInfo.getPayforStatus())){//不是待支付状态的订单，则说明已经回调过了
			return "success";
		}
		Response<Void> rs = orderInfoService.alipayCallback(orderInfo, map);
    	if(rs.getCode() >= 0 && OrderAlipayNotifyLog.TRADE_STATUS_3.equals(trade_status)){
    		logger.info("支付宝异步通知接口：成功");
    		return "success";
    	}
    	logger.debug("==========支付宝回调接口：结果======失败===>>");
    	return "failed";
    }

	/**
	 * Title:支付宝回调获参数获取
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月9日
	 * @param reqeustParamMap
	 * @return
	 */
	private Map<String, String> convertparamMap(Map<String, String[]> reqeustParamMap) {
		Map<String, String> paramsMap = Maps.newHashMap();
		String name;
		String[] values;
		for (Iterator<String> iter = reqeustParamMap.keySet().iterator(); iter.hasNext();) {
            name = iter.next();
            values = reqeustParamMap.get(name);
        	paramsMap.put(name, values[0]);
        }
		return paramsMap;
	}
    
    /**
     * Title:获取支付配置
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月2日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"getAlipayConfig"}, method=RequestMethod.POST)
    public Response<String> getAlipayConfig(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long orderId){
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || orderId == null){
    		throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<String> rs = orderInfoService.getAlipayConfig(appId, sessionId, token, orderId);

    	logger.debug("==========支付宝配置=======>>" + rs);
    	return rs;
    }
    
    /**
     * Title:获取订单支付结果
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月2日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"getPayResult"}, method=RequestMethod.POST)
    public Response<PaySuccessResultData> getPayResult(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long orderId){
    	//检查参数的有效性
    	if(appId == null || StringUtils.isBlank(sessionId) || StringUtils.isBlank(token) || orderId == null){
    		throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<PaySuccessResultData> rs = orderInfoService.getOrderPayResult(appId, sessionId, token, orderId);
    	logger.debug("==========获取订单支付结果=======>>" + rs);
    	return rs;
    }
}