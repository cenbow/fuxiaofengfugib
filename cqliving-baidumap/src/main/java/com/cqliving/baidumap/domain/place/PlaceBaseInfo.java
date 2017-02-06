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

import com.cqliving.baidumap.domain.Location;

/**
 * Title: poi 基础信息
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月15日
 */
public class PlaceBaseInfo {
	
	/** poi名称 */
	private String name;
	/** poi经纬度坐标 */
	private Location location;
	/** poi地址信息 */
	private String address;
	/** poi电话信息 */
	private String telephone;
	/** poi的唯一标示 */
	private String uid;
	/** 街景图id */
	private String street_id;
	/** 是否有详情页：1有，0没有 */
	private String detail;
	/** poi的扩展信息，仅当请求scope=2时，显示该字段，不同的poi类型，显示的detail_info字段不同。 */
	private PlaceDetailInfo detail_info;
	
	//位置建议接口返回的字段
	private String city;
	private String district;
	private String business;
	private String cityid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getStreet_id() {
		return street_id;
	}
	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}
	public PlaceDetailInfo getDetail_info() {
		return detail_info;
	}
	public void setDetail_info(PlaceDetailInfo detail_info) {
		this.detail_info = detail_info;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}