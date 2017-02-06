package com.cqliving.cloud.online.order.dto;

import java.util.List;
import java.util.Map;

/**
 * Title:退款进度信息
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月29日
 */
public class OrderRefundData {
	
	/** 退款金额 */
	private String refundMoneyStr;
	/** 订单号 */
	private String orderNo;
	/** 商品id */
	private Long goodsId;
	/** 商品名称 */
	private String goodsName;
	/** 商品价格 */
	private String goodsPrice;
	/** 商品列表图片 */
	private String goodsImageUrl;
	/** 数量 */
	private Integer quantity;
	/** 状态信息{info:信息内容;timeStr:处理时间;}，客户端只需要循环数组按序展示即可 */
	private List<Map<String, String>> statusList;
	
	public String getRefundMoneyStr() {
		return refundMoneyStr;
	}
	public void setRefundMoneyStr(String refundMoneyStr) {
		this.refundMoneyStr = refundMoneyStr;
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
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public List<Map<String, String>> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<Map<String, String>> statusList) {
		this.statusList = statusList;
	}
	public String getGoodsImageUrl() {
		return goodsImageUrl;
	}
	public void setGoodsImageUrl(String goodsImageUrl) {
		this.goodsImageUrl = goodsImageUrl;
	}
}
