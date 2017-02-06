package com.cqliving.cloud.online.order.service;

import java.util.Map;

import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.cloud.online.order.dto.OrderConfirmDarta;
import com.cqliving.cloud.online.order.dto.OrderInfoData;
import com.cqliving.cloud.online.order.dto.OrderInfoDto;
import com.cqliving.cloud.online.order.dto.OrderRefundData;
import com.cqliving.cloud.online.order.dto.PaySuccessResultData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 订单 Service
 * Date: 2016-11-21 21:35:10
 * @author Code Generator
 */
/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2017年1月18日
 */
public interface OrderInfoService {

	/** @author Code Generator *****start*****/
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> save(OrderInfo domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:查询订单
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月1日
	 * @param id
	 * @return
	 */
	public Response<OrderInfo> get(Long id);
	
	/**
	 * Title:根据订单号获取订单
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月2日
	 * @param orderNo
	 * @return
	 */
	public Response<OrderInfo> getByOrderNo(String orderNo);
	
	/**
	 * Title:订单cms分页查询
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月1日
	 * @param pageInfo
	 * @param map
	 * @param orderMap
	 * @return
	 */
	public Response<PageInfo<OrderInfoDto>> queryForPage(PageInfo<OrderInfoDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	
	/**
	 * Title:修改状态
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param status
	 * @param ids
	 * @return
	 */
	public Response<Void> updateStatus(Byte status,Long... ids);
	
	/**
	 * Title:修改支付状态
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param payforStatus
	 * @param ids
	 * @return
	 */
	public Response<Void> updatePayForStatus(Byte payforStatus,Long... ids);
	
	/**
	 * Title:用户取消订单
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param ids
	 * @return
	 */
	public Response<Void> cancelByUser(Long appId, String sessionId, String token, Long...ids);
	
	/**
	 * Title:用户删除订单
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param ids
	 */
	public Response<Void> delByUser(Long appId, String sessionId, String token, Long...ids);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月22日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param payforStatus
	 * @param lastId
	 * @return
	 */
	Response<CommonListResult<OrderInfoData>> getScrollPage(Long appId, String sessionId, String token, Byte payforStatus, Long lastId);
	
	/**
	 * Title:订单详情
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月23日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @return
	 */
	Response<OrderInfoData> detail(Long appId, String sessionId, String token, Long orderId);
	
	/**
	 * Title:创建订单
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月28日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param recAddressId
	 * @param goodsIds
	 * @param goodsNums
	 * @param descn
	 * @return
	 */
	Response<OrderInfo> create(Long appId, String sessionId, String token, Long recAddressId, String goodsIds, String goodsNums, String descn);
	
	/**
	 * Title:订单确认
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月28日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param recAddressId
	 * @param goodsIds
	 * @param goodsNums
	 * @return
	 */
	public Response<OrderConfirmDarta> confirmOrderByUser(Long appId, String sessionId, String token, Long recAddressId, String goodsIds, String goodsNums);
	
	/**
	 * Title:用户申请退款
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月29日
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
	public Response<Void> applyRefundByUser(Long appId, String sessionId, String token, Long orderId, Long goodsId, String payAccount, String description, String images);
	
	/**
	 * Title:退款进度查询
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月29日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @param goodsId
	 * @return
	 */
	public Response<OrderRefundData> getRefundProgress(Long appId, String sessionId, String token, Long orderId, Long goodsId);
	
	/**
	 * Title:申请退款查询需要退款的金额
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月29日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @param goodsId
	 * @return
	 */
	public Response<Map<String, String>> getApplyRefund(Long appId, String sessionId, String token, Long orderId, Long goodsId);
	
	/**
	 * Title:确认收货
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月30日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @return
	 */
	public Response<Void> confirmReceipt(Long appId, String sessionId, String token, Long orderId);
	
	/**
	 * Title:订单过期任务
	 * <p>Description:对下单后一直不支付的订单处理</p>
	 * @author DeweiLi on 2016年11月30日
	 */
	public Response<Void> payExpiredTask();
	
	/**
	 * Title:支付宝回调
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月1日
	 * @param orderInfo
	 * @param map
	 * @return
	 */
	Response<Void> alipayCallback(OrderInfo orderInfo, Map<String, String> map);
	
	/**
	 * Title:获取支付配置
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月2日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @return
	 */
	public Response<String> getAlipayConfig(Long appId, String sessionId, String token, Long orderId);
	
	/**
	 * Title:cms发货
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月6日
	 * @param orderId
	 * @param expressCompany
	 * @param expressNo
	 * @param userId
	 * @return
	 */
	public Response<Void> deliverGoods(Long orderId, String expressCompany, String expressNo, Long userId);
	
	/**
	 * Title:确认退款和拒绝退款
	 * <p>Description:</p>
	 * @author DeweiLi on 2017年1月18日
	 * @param ids
	 * @param type
	 * @param contents
	 * @param userId
	 * @return
	 */
	public Response<Void> refundByAdmin(Long[] ids, String type, String[] contents, Long userId);
	
	/**
	 * Title:取消订单
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月6日
	 * @param userId
	 * @param ids
	 * @return
	 */
	public Response<Void> cancelByAdmin(Long userId, Long...ids);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月9日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @return
	 */
	public Response<PaySuccessResultData> getOrderPayResult(Long appId, String sessionId, String token, Long orderId);
	
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月11日
     * @param orderInfo
     * @return
     */
    public Response<Void> saveByAdmim(OrderInfo orderInfo);
}
