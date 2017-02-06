package com.cqliving.cloud.online.order.dto;

/**
 * Title:订单的收货地址信息
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月23日
 */
public class OrderUserAddressData {
	
	/** 用户收货地址ID */
	private Long orderUserAddressId;
	/** 收货人 */
	private String receiverName;
	/** 收货人手机号 */
	private String receiverPhone;
	/** 完整地址（展示用） */
	private String receiverAddress;
	public Long getOrderUserAddressId() {
		return orderUserAddressId;
	}
	public void setOrderUserAddressId(Long orderUserAddressId) {
		this.orderUserAddressId = orderUserAddressId;
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
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
}
