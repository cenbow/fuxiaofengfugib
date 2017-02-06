package com.cqliving.cloud.online.interfacc.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月2日
 */
public class AllMediaData {

	/** 主键 */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 全媒体名称 */
	private String name;
	/** 2倍图标地址 */
	private String minImageUrl;
	/** 3倍图标地址 */
	private String maxImageUrl;
	/** 类型 */
	private Byte type;
	/** 当type=1时该值有效 */
	private String linkUrl;
	/** 栏目ID，app_columns表主键。当type=2或type=3时该值必填 */
	private Long columnsId;
	/** 当type=3时该值有效。栏目ID的对应的顶层栏目所在的索引位置，索引从0开始 */
	private Integer topColumnsIndex;
	/** 排序号 */
	private Integer sortNo;
	
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
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getMinImageUrl(){
		return this.minImageUrl;
	}
	
	public void setMinImageUrl(String minImageUrl){
		this.minImageUrl = minImageUrl;
	}
	public String getMaxImageUrl(){
		return this.maxImageUrl;
	}
	
	public void setMaxImageUrl(String maxImageUrl){
		this.maxImageUrl = maxImageUrl;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public String getLinkUrl(){
		return this.linkUrl;
	}
	
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}
	public Long getColumnsId(){
		return this.columnsId;
	}
	
	public void setColumnsId(Long columnsId){
		this.columnsId = columnsId;
	}
	public Integer getTopColumnsIndex(){
		return this.topColumnsIndex;
	}
	
	public void setTopColumnsIndex(Integer topColumnsIndex){
		this.topColumnsIndex = topColumnsIndex;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}