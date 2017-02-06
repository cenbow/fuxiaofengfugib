package com.cqliving.cloud.online.order.dto;

import java.util.List;

/**
 * Title:确认订单接口需要的数据
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月28日
 */
public class OrderConfirmDarta {

	/** 订单总金额（元）（不含运费） */
	private String orderMoney;
	/** 商品列表 */
	private List<OrderGoodsData> goodsList;
	/** 运费 */
	private String shippingFare;
	/** 订单收货地址 */
	private OrderUserAddressData recAddress;
	/** 订单总金额（含运费） */
	private String totalMoney;
	
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public List<OrderGoodsData> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<OrderGoodsData> goodsList) {
		this.goodsList = goodsList;
	}
	public String getShippingFare() {
		return shippingFare;
	}
	public void setShippingFare(String shippingFare) {
		this.shippingFare = shippingFare;
	}
	public OrderUserAddressData getRecAddress() {
		return recAddress;
	}
	public void setRecAddress(OrderUserAddressData recAddress) {
		this.recAddress = recAddress;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
}
