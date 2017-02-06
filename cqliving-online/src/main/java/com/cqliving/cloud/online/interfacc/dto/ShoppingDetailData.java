package com.cqliving.cloud.online.interfacc.dto;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月01日
 */
public class ShoppingDetailData {

	/** 商品ID */
	private Long id;
	/** 标签（逗号分隔） */
	private String labels;
	/** 图片列表 */
	private List<ShoppingImagesData> imageUrlList;
	/** 名称 */
	private String name;
	/** 原价 */
	private String originalPrice;
	/** 价格 */
	private String price;
	/** 摘要 */
	private String synopsis;
	/** 销量 */
	private Integer salesVolume;
	/** 状态{0:下架,1:上架} */
	private Byte status;
	/** 评论量 */
	private Integer replyCount;
	/** 详情 */
	private String content;
	
	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public List<ShoppingImagesData> getImageUrlList() {
		return imageUrlList;
	}

	public void setImageUrlList(List<ShoppingImagesData> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}