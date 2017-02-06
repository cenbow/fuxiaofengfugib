package com.cqliving.cloud.online.config.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年7月13日
 */
public class ConfigConvenienceDto {
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 热线类型ID，config_life_type表主键 */
	private Long convenienceTypeId;
	/** 便民名称 */
	private String name;
	/** 便民链接 */
	private String linkUrl;
	/** 便民图标48*48px地址 */
	private String iconMinUrl;
	/** 便民图标72*72px地址 */
	private String iconMaxUrl;
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

	/** 客户端名称 */
	private String appName;
	/** 类型名称 */
	private String convenienceTypeName;
	/** 便民分类序号 */
	private Integer typeSortNo;
	/** 便民分类图标地址 */
	private String typeImgUrl;
	
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
	public Long getConvenienceTypeId() {
		return convenienceTypeId;
	}
	public void setConvenienceTypeId(Long convenienceTypeId) {
		this.convenienceTypeId = convenienceTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getIconMinUrl() {
		return iconMinUrl;
	}
	public void setIconMinUrl(String iconMinUrl) {
		this.iconMinUrl = iconMinUrl;
	}
	public String getIconMaxUrl() {
		return iconMaxUrl;
	}
	public void setIconMaxUrl(String iconMaxUrl) {
		this.iconMaxUrl = iconMaxUrl;
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
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getConvenienceTypeName() {
		return convenienceTypeName;
	}
	public void setConvenienceTypeName(String convenienceTypeName) {
		this.convenienceTypeName = convenienceTypeName;
	}
	public Integer getTypeSortNo() {
		return typeSortNo;
	}
	public void setTypeSortNo(Integer typeSortNo) {
		this.typeSortNo = typeSortNo;
	}
	public String getTypeImgUrl() {
		return typeImgUrl;
	}
	public void setTypeImgUrl(String typeImgUrl) {
		this.typeImgUrl = typeImgUrl;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
