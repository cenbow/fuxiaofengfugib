package com.cqliving.cloud.online.interfacc.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年8月25日
 */
public class TourismInfoData {

	/** ID */
	private Long id;
	/** 名称 */
	private String name;
	/** 分类 */
	private Byte type;
	/** 分类名称 */
	private String typeName;
	/** 外链地址 */
	private String linkUrl;
	/** 列表图地址 */
	private String imageUrl;
	/** 距离（米） */
	private Long distance;
	/** 格式化的距离 */
	private String distanceFormatted;
	/** 所处位置 */
	private String place;
	/** 所处位置纬度 */
	private String lat;
	/** 所处位置经度 */
	private String lng;
	/** 景区价格 */
	private String price;
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
	/** 排序号 */
	private Integer sortNo;
	/** 更新时间 */
	private String updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public String getDistanceFormatted() {
		return distanceFormatted;
	}

	public void setDistanceFormatted(String distanceFormatted) {
		this.distanceFormatted = distanceFormatted;
	}

	public Byte getCommentType() {
		return commentType;
	}

	public void setCommentType(Byte commentType) {
		this.commentType = commentType;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
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

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}