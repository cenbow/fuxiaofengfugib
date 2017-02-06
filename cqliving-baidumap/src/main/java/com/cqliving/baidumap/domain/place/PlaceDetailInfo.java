/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.domain.place;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title: poi 详细信息
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月15日
 */
public class PlaceDetailInfo {
	
	/** 距离中心点的距离，圆形区域检索时返回 */
	private int distance;
	/** 所属分类，如"hotel"、"cater" */
	private String type;
	/** 标签 */
	private String tag;
	/** poi的详情页 */
	private String detail_url;
	/** poi商户的价格 */
	private String price;
	/** 营业时间 */
	private String shop_hours;
	/** 总体评分 */
	private String overall_rating;
	/** 口味评分 */
	private String taste_rating;
	/** 服务评分 */
	private String service_rating;
	/** 环境评分 */
	private String environment_rating;
	/** 星级（设备）评分 */
	private String facility_rating;
	/** 卫生评分 */
	private String hygiene_rating;
	/** 技术评分 */
	private String technology_rating;
	/** 图片数 */
	private String image_num;
	/** 团购数 */
	private int groupon_num;
	/** 优惠数 */
	private int discount_num;
	/** 评论数 */
	private String comment_num;
	/** 收藏数 */
	private String favorite_num;
	/** 签到数 */
	private String checkin_num;
	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	public String getShop_hours() {
		return shop_hours;
	}
	public void setShop_hours(String shop_hours) {
		this.shop_hours = shop_hours;
	}
	public String getOverall_rating() {
		return overall_rating;
	}
	public void setOverall_rating(String overall_rating) {
		this.overall_rating = overall_rating;
	}
	public String getTaste_rating() {
		return taste_rating;
	}
	public void setTaste_rating(String taste_rating) {
		this.taste_rating = taste_rating;
	}
	public String getService_rating() {
		return service_rating;
	}
	public void setService_rating(String service_rating) {
		this.service_rating = service_rating;
	}
	public String getEnvironment_rating() {
		return environment_rating;
	}
	public void setEnvironment_rating(String environment_rating) {
		this.environment_rating = environment_rating;
	}
	public String getFacility_rating() {
		return facility_rating;
	}
	public void setFacility_rating(String facility_rating) {
		this.facility_rating = facility_rating;
	}
	public String getHygiene_rating() {
		return hygiene_rating;
	}
	public void setHygiene_rating(String hygiene_rating) {
		this.hygiene_rating = hygiene_rating;
	}
	public String getTechnology_rating() {
		return technology_rating;
	}
	public void setTechnology_rating(String technology_rating) {
		this.technology_rating = technology_rating;
	}
	public String getImage_num() {
		return image_num;
	}
	public void setImage_num(String image_num) {
		this.image_num = image_num;
	}
	public int getGroupon_num() {
		return groupon_num;
	}
	public void setGroupon_num(int groupon_num) {
		this.groupon_num = groupon_num;
	}
	public int getDiscount_num() {
		return discount_num;
	}
	public void setDiscount_num(int discount_num) {
		this.discount_num = discount_num;
	}
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	public String getFavorite_num() {
		return favorite_num;
	}
	public void setFavorite_num(String favorite_num) {
		this.favorite_num = favorite_num;
	}
	public String getCheckin_num() {
		return checkin_num;
	}
	public void setCheckin_num(String checkin_num) {
		this.checkin_num = checkin_num;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}