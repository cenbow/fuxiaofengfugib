package com.cqliving.cloud.online.order.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.cloud.online.order.dto.OrderConfirmDarta;
import com.cqliving.cloud.online.order.dto.OrderInfoData;
import com.cqliving.cloud.online.order.dto.OrderInfoDto;
import com.cqliving.cloud.online.order.dto.OrderRefundData;
import com.cqliving.cloud.online.order.dto.PaySuccessResultData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 订单 Manager
 * Date: 2016-11-21 21:35:10
 * @author Code Generator
 */
/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月25日
 */
public interface OrderInfoManager extends EntityService<OrderInfo> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	
	/**
	 * Title:修改订单支付状态
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param payforStatus
	 * @param ids
	 */
	public int updatePayForStatus(Byte payforStatus, Long...ids);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param ids
	 * @return
	 */
	List<OrderInfo> getByIds(Long...ids);
	
	/**
	 * Title:根据订单号获取订单
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月2日
	 * @param orderNo
	 * @return
	 */
	OrderInfo getByOrderNo(String orderNo);
	
	/**
	 * Title:用户取消订单
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param ids
	 */
	public void cancelByUser(Long appId, String sessionId, String token, Long...ids);
	
	/**
	 * Title:用户删除订单
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param ids
	 */
	public void delByUser(Long appId, String sessionId, String token, Long...ids);
	
	/**
	 * Title:订单中心
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月22日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param payforStatus
	 * @param lastId
	 * @return
	 */
	public CommonListResult<OrderInfoData> getScrollPage(Long appId, String sessionId, String token, Byte payforStatus, Long lastId);
	
	/**
	 * Title:订单中心-退款列表
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月5日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastId
	 * @return
	 */
	public CommonListResult<OrderInfoData> getScrollPageRefund(Long appId, String sessionId, String token, Long lastId);
	
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
	OrderInfoData detail(Long appId, String sessionId, String token, Long orderId);

	
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
	OrderInfo create(Long appId, String sessionId, String token, Long recAddressId, String goodsIds, String goodsNums, String descn);

	/**
	 * Title:确认订单
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
	public OrderConfirmDarta confirmOrderByUser(Long appId, String sessionId, String token, Long recAddressId, String goodsIds, String goodsNums);

	/**
	 * Title:申请退款金额和支付帐号回显
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月29日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @param goodsId
	 * @return
	 */
	public Map<String, String> getApplyRefund(Long appId, String sessionId, String token, Long orderId, Long goodsId);
	
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
	 */
	void applyRefundByUser(Long appId, String sessionId, String token, Long orderId, Long goodsId, String payAccount, String description, String images);
	
	/**
	 * Title:退款进度
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月29日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @param goodsId
	 * @return
	 */
	OrderRefundData getRefundProgress(Long appId, String sessionId, String token, Long orderId, Long goodsId);
	
	/**
	 * Title:确认收货
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月29日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 */
	void confirmReceipt(Long appId, String sessionId, String token, Long orderId);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月30日
	 * @param orderInfo
	 * @param map 
	 */
	void alipayCallback(OrderInfo orderInfo, Map<String, String> map);

	/**
	 * Title:定时处理订单过期
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月30日
	 */
	public void payExpiredTask();

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月2日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @return
	 */
	public String getAlipayConfig(Long appId, String sessionId, String token, Long orderId);
	
	/**
	 * Title:cms发货
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月6日
	 * @param orderId
	 * @param expressCompany
	 * @param expressNo
	 * @param userId
	 */
	public void deliverGoods(Long orderId, String expressCompany, String expressNo, Long userId);

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月6日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	public PageInfo<OrderInfoDto> queryForPage(PageInfo<OrderInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * Title:管理员确认退款和拒绝退款
	 * <p>Description:</p>
	 * @author DeweiLi on 2017年1月18日
	 * @param ids
	 * @param type
	 * @param contents
	 * @param userId
	 */
	public void refundByAdmin(Long[] ids, String type, String[] contents, Long userId);

	/**
	 * Title:取消订单
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月6日
	 * @param userId
	 * @param ids
	 */
	public void cancelByAdmin(Long userId, Long[] ids);
	
	/**
	 * Title:支付成功后客户端显示的结果
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月9日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 */
	public PaySuccessResultData getOrderPayResult(Long appId, String sessionId, String token, Long orderId);

    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月11日
     * @param orderInfo
     */
    public void saveByAdmim(OrderInfo orderInfo);
}
