package com.cqliving.cloud.online.order.dto;

/**
 * Title:支付成功后的结果
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月9日
 */
public class PaySuccessResultData {

	/** 支付成功 */
	public final static Byte PAYFORSTATUS1 = 1;
	/** 支付失败 */
	public final static Byte PAYFORSTATUS2 = 2;
	
	/** 订单ID */
	private Long orderId;
	/** 订单编号 */
	private String orderNo;
	/** 支付金额 */
	private String payMoney;
	/** 支付状态 */
	private Byte payforStatus;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public Byte getPayforStatus() {
		return payforStatus;
	}
	public void setPayforStatus(Byte payforStatus) {
		this.payforStatus = payforStatus;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
}
