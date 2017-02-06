package com.cqliving.cloud.online.interfacc.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月21日
 */
public class FavoritesShoppingData {

	/** 收藏ID */
	private Long favoriteId;
	/** 商品ID */
	private Long sourceId;
	/** 标签（逗号分隔） */
	private String labels;
	/** 列表图片 */
	private String listImageUrl;
	/** 名称 */
	private String name;
	/** 原价 */
	private String originalPrice;
	/** 价格 */
	private String price;
	/** 收藏量 */
	private Integer collectCount;
	/** 是否允许评论{0:允许,1:不允许} */
	private Byte commentType;
	/** 跳转的Url */
	private String url;
	/** 分享的Url */
	private String shareUrl;
	/** 详情展示类型 */
	private Byte detailViewType;
	/** 业务类型 */
	private Byte sourceType;
	/** 摘要 */
	private String synopsis;
	
	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getListImageUrl() {
		return listImageUrl;
	}

	public void setListImageUrl(String listImageUrl) {
		this.listImageUrl = listImageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public Byte getCommentType() {
		return commentType;
	}

	public void setCommentType(Byte commentType) {
		this.commentType = commentType;
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

	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}

	public Long getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Long favoriteId) {
		this.favoriteId = favoriteId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}