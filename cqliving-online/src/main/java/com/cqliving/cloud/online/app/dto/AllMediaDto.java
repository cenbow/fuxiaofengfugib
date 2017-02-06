package com.cqliving.cloud.online.app.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AllMediaDto{

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
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 更新时间 */
	private Date updateTime;
	
	
	private String columnsName;
	private String appName;
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
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public String getColumnsName() {
		return columnsName;
	}

	public void setColumnsName(String columnsName) {
		this.columnsName = columnsName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
