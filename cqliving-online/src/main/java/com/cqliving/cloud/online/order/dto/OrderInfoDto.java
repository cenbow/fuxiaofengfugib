package com.cqliving.cloud.online.order.dto;

import java.util.Date;

/**
 * Title:订单和商品的关联查询
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月22日
 */
public class OrderInfoDto {
	
	/** 主键 */
	private Long id;
	/** 客户端id */
	private Long appId;
	/** 用户id */
	private Long userId;
	/** 订单号 */
	private String orderNo;
	/** 订单金额（分） */
	private Integer orderAmount;
	/** 快递公司 */
	private String expressCompany;
	/** 快递单号 */
	private String expressNo;
	/** 运费（分），没有则为0 */
	private Integer shippingFare;
	/** 订单备注 */
	private String descn;
	/** 订单支付状态 */
	private Byte payforStatus;
	/** 订单状态 */
	private Byte status;
	/** 购买人姓名 */
	private String userName;
	/** 购买人手机号码 */
	private String userPhone;
	/** 支付渠道 */
	private Integer payMode;
	/** 订单创建时间 */
	private Date createTime;
	/** 操作员id */
	private Long updaterId;
	/** 修改时间 */
	private Date operateTime;
	/** 收货人地址 */
	private String receiverAddress;
	/** 收货人姓名 */
	private String receiverName;
	/** 收货人电话 */
	private String receiverPhone;
	/** 支付帐号 */
	private String payAccount;
	
	
	/** 主键 */
	private Long orderGoodsId;
	/** 商品id */
	private Long goodsId;
	/** 是否已评价 */
	private Byte isEvaluate;
	/** 退款状态 */
	private Byte refundStatus;
	/** 商品名称 */
	private String goodsName;
	/** 商品单价，购买时的价格（分） */
	private Integer goodsPrice;
	/** 原价（分） */
	private Integer originalPrice;
	/** 商品图片 */
	private String goodsImageUrl;
	/** 商品数量 */
	private Integer quantity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getExpressCompany() {
		return expressCompany;
	}
	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public Integer getShippingFare() {
		return shippingFare;
	}
	public void setShippingFare(Integer shippingFare) {
		this.shippingFare = shippingFare;
	}
	public String getDescn() {
		return descn;
	}
	public void setDescn(String descn) {
		this.descn = descn;
	}
	public Byte getPayforStatus() {
		return payforStatus;
	}
	public void setPayforStatus(Byte payforStatus) {
		this.payforStatus = payforStatus;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getPayMode() {
		return payMode;
	}
	public void setPayMode(Integer payMode) {
		this.payMode = payMode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdaterId() {
		return updaterId;
	}
	public void setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public Long getOrderGoodsId() {
		return orderGoodsId;
	}
	public void setOrderGoodsId(Long orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Byte getIsEvaluate() {
		return isEvaluate;
	}
	public void setIsEvaluate(Byte isEvaluate) {
		this.isEvaluate = isEvaluate;
	}
	public Byte getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(Byte refundStatus) {
		this.refundStatus = refundStatus;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	
}
