package com.cqliving.cloud.online.order.dto;

import java.util.Date;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月5日
 */
public class OrderRefundDto {
	/** 主键 */
	private Long id;
	/** 订单id */
	private Long orderId;
	/** 订单编号 */
	private String orderNo;
	/** 商品id */
	private Long goodsId;
	/** 商品名称 */
	private String goodsName;
	/** 商品单价，购买时的商品价格（分） */
	private Integer goodsPrice;
	/** 商品原价 */
	private Integer originalPrice;
	/** 买家支付帐号 */
	private String userPayAccount;
	/** 退款金额（分），可能有折扣，退款金额不能大于用户当前订单的支付金额 */
	private Integer refundAmount;
	/** 商品数量 */
	private Integer quantity;
	/** 退货回执单号 */
	private String receiptNo;
	/** 申请退款图片（逗号分隔），最多9张 */
	private String refundImagesUrl;
	/** 退款原因 */
	private String refundReason;
	/** 创建时间，提交退货时间 */
	private Date createTime;
	/** 拒绝退款时间 */
	private Date refuseTime;
	/** 拒绝退款原因 */
	private String refuseReason;
	/** 同意退款时间 */
	private Date agreeTime;
	/** 同意退款原因 */
	private String agreeReason;
	/** 商品图片 */
	private String goodsImageUrl;

	/** 订单创建时间 */
	private Date orderCreateTime;
	/** 订单状态 */
	private Byte orderPayforStatus;
	/** 商品退货状态 */
	private Byte refundStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Integer goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getUserPayAccount() {
		return userPayAccount;
	}

	public void setUserPayAccount(String userPayAccount) {
		this.userPayAccount = userPayAccount;
	}

	public Integer getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getRefundImagesUrl() {
		return refundImagesUrl;
	}

	public void setRefundImagesUrl(String refundImagesUrl) {
		this.refundImagesUrl = refundImagesUrl;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getRefuseTime() {
		return refuseTime;
	}

	public void setRefuseTime(Date refuseTime) {
		this.refuseTime = refuseTime;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public Date getAgreeTime() {
		return agreeTime;
	}

	public void setAgreeTime(Date agreeTime) {
		this.agreeTime = agreeTime;
	}

	public String getAgreeReason() {
		return agreeReason;
	}

	public void setAgreeReason(String agreeReason) {
		this.agreeReason = agreeReason;
	}

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public Byte getOrderPayforStatus() {
		return orderPayforStatus;
	}

	public void setOrderPayforStatus(Byte orderPayforStatus) {
		this.orderPayforStatus = orderPayforStatus;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getGoodsImageUrl() {
		return goodsImageUrl;
	}

	public void setGoodsImageUrl(String goodsImageUrl) {
		this.goodsImageUrl = goodsImageUrl;
	}

	public Byte getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Byte refundStatus) {
		this.refundStatus = refundStatus;
	}
	
}
