package com.cqliving.cloud.online.config.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月26日
 */
public class RecommendAppDto {

	/** ID */
	private Long id;
	/** 客户端ID,APP_INFO表ID，一个客户端上面可以有多个推荐客户端 */
	private Long appId;
	/** 推荐客户端ID,APP_INFO表ID */
	private Long recommendAppId;
	/** 推荐客户端的栏目表ID,app_columns表的ID */
	private Long columnsId;
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
	/** 是否展示轮播数据 */
	private Byte isCarousel;
	
	//------------------------ 冗余字段 --------------------------
	
	/** 推荐客户名称 */
	private String recommendAppName;
	/** 推荐客户端栏目名称 */
	private String columnsName; 
	/** app图标 */
	private String appIcon;
	/** 下载地址 */
	private String downloadUrl;
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Long getRecommendAppId(){
		return this.recommendAppId;
	}
	
	public void setRecommendAppId(Long recommendAppId){
		this.recommendAppId = recommendAppId;
	}
	public Long getColumnsId(){
		return this.columnsId;
	}
	
	public void setColumnsId(Long columnsId){
		this.columnsId = columnsId;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Long getUpdatorId(){
		return this.updatorId;
	}
	
	public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	
	public Byte getIsCarousel() {
		return isCarousel;
	}

	public void setIsCarousel(Byte isCarousel) {
		this.isCarousel = isCarousel;
	}

	public String getRecommendAppName() {
		return recommendAppName;
	}

	public void setRecommendAppName(String recommendAppName) {
		this.recommendAppName = recommendAppName;
	}

	public String getColumnsName() {
		return columnsName;
	}

	public void setColumnsName(String columnsName) {
		this.columnsName = columnsName;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
