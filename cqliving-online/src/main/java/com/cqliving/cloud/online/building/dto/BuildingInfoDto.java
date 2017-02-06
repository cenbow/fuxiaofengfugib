/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.building.dto;

import java.util.Date;
import java.util.List;

import com.cqliving.cloud.online.building.domain.BuildingImage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年10月12日
 */
public class BuildingInfoDto {

	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 楼盘名称 */
	private String name;
	/** 所属区域CODE */
	private String regionCode;
	/** 所属区域名称 */
	private String regionName;
	/** 所处位置纬度 */
	private String lat;
	/** 所处位置经度 */
	private String lng;
	/** 地标，可以填写多个，用逗号分隔，页面只显示第一个 */
	private String landmark;
	/** 户型面积范围-低，单位平方米 */
	private Double averageLow;
	/** 户型面积范围-高，单位平方米 */
	private Double averageHigh;
	/** 标签。最多添加3个标签,用逗号隔开,每个标签不超过5个字，如 学区房,特色别墅,景观居所； */
	private String buildingLabel;
	/** 楼盘均价（分），用于前台条件过滤 */
	private Integer averagePrice;
	/** 显示价格，用于页面展示 */
	private Integer viewPrice;
	/** 显示价格单位 */
	private Byte viewUnit;
	/** 列表图 */
	private String listImageUrl;
	/** 地址 */
	private String address;
	/** 建筑类型 */
	private String buildingType;
	/** 容积率 */
	private String ratio;
	/** 开盘时间。如 2016年6月18日三期5号楼已开盘 */
	private String openTime;
	/** 产权年限 */
	private String buildingYear;
	/** 开发商 */
	private String developer;
	/** 绿化率 */
	private String greeningRate;
	/** 交房时间 */
	private String launchTime;
	/** 物业费。如 5.00元/每平米·月 */
	private String propertyPrice;
	/** 装修状况 */
	private String renovationSituation;
	/** 预售许可证 */
	private String presalePermit;
	/** 咨询电话。只填写数字即可，而非必须填固定格式 */
	private String telephone;
	/** 可以选择多个，用逗号分开。户型 */
	private String houseType;
	/** 户型结构。如 三居 */
	private String structure;
	/** 套内面积。如 99999平方米 */
	private String innerArea;
	/** 排序号 */
	private Integer sortNo;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	
	/** ext */
	/** 置业详情图片 */
	private List<BuildingImage> buildingImg;
	/** 置业详情跳转地址 */
	private String detailUrl;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Double getAverageLow() {
		return averageLow;
	}

	public void setAverageLow(Double averageLow) {
		this.averageLow = averageLow;
	}

	public Double getAverageHigh() {
		return averageHigh;
	}

	public void setAverageHigh(Double averageHigh) {
		this.averageHigh = averageHigh;
	}

	public String getBuildingLabel() {
		return buildingLabel;
	}

	public void setBuildingLabel(String buildingLabel) {
		this.buildingLabel = buildingLabel;
	}

	public Integer getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Integer averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Integer getViewPrice() {
		return viewPrice;
	}

	public void setViewPrice(Integer viewPrice) {
		this.viewPrice = viewPrice;
	}

	public Byte getViewUnit() {
		return viewUnit;
	}

	public void setViewUnit(Byte viewUnit) {
		this.viewUnit = viewUnit;
	}

	public String getListImageUrl() {
		return listImageUrl;
	}

	public void setListImageUrl(String listImageUrl) {
		this.listImageUrl = listImageUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getBuildingYear() {
		return buildingYear;
	}

	public void setBuildingYear(String buildingYear) {
		this.buildingYear = buildingYear;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getGreeningRate() {
		return greeningRate;
	}

	public void setGreeningRate(String greeningRate) {
		this.greeningRate = greeningRate;
	}

	public String getLaunchTime() {
		return launchTime;
	}

	public void setLaunchTime(String launchTime) {
		this.launchTime = launchTime;
	}

	public String getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(String propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public String getRenovationSituation() {
		return renovationSituation;
	}

	public void setRenovationSituation(String renovationSituation) {
		this.renovationSituation = renovationSituation;
	}

	public String getPresalePermit() {
		return presalePermit;
	}

	public void setPresalePermit(String presalePermit) {
		this.presalePermit = presalePermit;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getInnerArea() {
		return innerArea;
	}

	public void setInnerArea(String innerArea) {
		this.innerArea = innerArea;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public List<BuildingImage> getBuildingImg() {
		return buildingImg;
	}

	public void setBuildingImg(List<BuildingImage> buildingImg) {
		this.buildingImg = buildingImg;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	
}
