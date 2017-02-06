package com.cqliving.cloud.online.order.dto;

import com.cqliving.cloud.common.constant.BusinessType;

/**
 * Title:订单下的商品信息
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月23日
 */
public class OrderGoodsData {
	/** 商品ID */
	private Long goodsId;
	/** 商品名称 */
	private String goodsName;
	/** 商品单价，购买时的价格，数据库是按分存的，接口要转换成元为单位 */
	private String goodsPrice;
	/** 原价（元） */
	private String originalPrice;
	/** 商品列表图片 */
	private String goodsImageUrl;
	/** 商品数量 */
	private Integer quantity;
	/** 退款状态 */
	private Byte refundStatus;
	/** 退款状态 */
	private String refundStatusStr;
	/** 给客户端冗余的订单ID */
	private Long orderId;
	
	/** 跳转的Url */
	private String url;
	/** 分享的Url */
	private String shareUrl;
	/** 详情展示类型{1:多图新闻,2:普通新闻,3:专题新闻,4:随手拍,5:段子,7:话题} */
	private Byte detailViewType;
	/** 业务类型*/
	private Byte sourceType = BusinessType.SOURCE_TYPE_13;
	/** 用于分享的标题 */
	private String title;
	/** 摘要 */
	private String synopsis;
	
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
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
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
	public Byte getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(Byte refundStatus) {
		this.refundStatus = refundStatus;
	}
	public Byte getSourceType() {
		return sourceType;
	}
	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}
	public String getRefundStatusStr() {
		return refundStatusStr;
	}
	public void setRefundStatusStr(String refundStatusStr) {
		this.refundStatusStr = refundStatusStr;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	public Byte getDetailViewType() {
		return detailViewType;
	}
	public void setDetailViewType(Byte detailViewType) {
		this.detailViewType = detailViewType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
}
