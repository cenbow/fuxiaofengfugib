package com.cqliving.cloud.online.order.dto;

import java.util.List;

/**
 * Title:订单信息
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月23日
 */
public class OrderInfoData {
	/** 订单ID */
	private Long orderId;
	/** 退货id，order_refund 表的id，用于订单中心的退货列表 */
	private Long refundId;
	/** 订单编号 */
	private String orderNo;
	/** 订单状态 */
	private Byte payForStatus;
	/** 订单状态字符串 */
	private String payForStatusStr;
	/** 订单下单时间字符串 */
	private String createTimeStr;
	/** 订单总金额（元） */
	private String orderMoney;
	/** 订单收货地址 */
	private OrderUserAddressData recAddress;
	/** 商品列表 */
	private List<OrderGoodsData> goodsList;
	/** 运费 */
	private String shippingFare;
	/** 订单备注， 给卖家留言 */
	private String descn;
	/** 时间集合字符串，直接取出来显示就OK了如：支付时间：2015-11-20 12:50 */
	private String timeStr;
	/** 快递单号 */
	private String expressNo;
	/** 快递公司 */
	private String expressCompany;
	/** 如果是待支付状态时，离过期还剩多少毫秒 */
	private Long diffTime;
	/** 商品总数 */
	private Integer goodsCount;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getRefundId() {
		return refundId;
	}
	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Byte getPayForStatus() {
		return payForStatus;
	}
	public void setPayForStatus(Byte payForStatus) {
		this.payForStatus = payForStatus;
	}
	public String getPayForStatusStr() {
		return payForStatusStr;
	}
	public void setPayForStatusStr(String payForStatusStr) {
		this.payForStatusStr = payForStatusStr;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public List<OrderGoodsData> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<OrderGoodsData> goodsList) {
		this.goodsList = goodsList;
	}
	public OrderUserAddressData getRecAddress() {
		return recAddress;
	}
	public void setRecAddress(OrderUserAddressData recAddress) {
		this.recAddress = recAddress;
	}
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getShippingFare() {
		return shippingFare;
	}
	public void setShippingFare(String shippingFare) {
		this.shippingFare = shippingFare;
	}
	public String getDescn() {
		return descn;
	}
	public void setDescn(String descn) {
		this.descn = descn;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getExpressCompany() {
		return expressCompany;
	}
	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}
	public Long getDiffTime() {
		return diffTime;
	}
	public void setDiffTime(Long diffTime) {
		this.diffTime = diffTime;
	}
	public Integer getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
}
