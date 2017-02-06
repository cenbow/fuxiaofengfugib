package com.cqliving.cloud.online.config.domain;


import java.util.Map;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * recommend_info Entity
 * Date: 2016-08-01 14:21:38
 * @author Code Generator
 */
@Entity
@Table(name = "recommend_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RecommendInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 新闻 */
	public static final Byte SOURCETYPE1 = 1;
	/** 商情 */
	public static final Byte SOURCETYPE3 = 3;
	/** 话题 */
	public static final Byte SOURCETYPE7 = 7;
		
	/** 业务类型 */
	public static final Map<Byte, String> allSourceTypes = Maps.newTreeMap();
	static {
		allSourceTypes.put(SOURCETYPE1, "新闻");
		allSourceTypes.put(SOURCETYPE3, "商情");
		allSourceTypes.put(SOURCETYPE7, "话题");
	}
	/** 保存 */
	public static final Byte STATUS1 = 1;
	/** 已发布 */
	public static final Byte STATUS3 = 3;
	/** 已下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "保存");
		allStatuss.put(STATUS3, "已发布");
		allStatuss.put(STATUS88, "已下线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 业务ID，source_type=7则对应topic_info表的ID */
	private Long sourceId;
	/** 业务类型 */
	private Byte sourceType;
	/** 名称 */
	private String name;
	/** 图片地址 */
	private String imageUrl;
	/** 链接地址 */
	private String linkUrl;
	/** 排序号 */
	private Integer sortNo;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public Long getSourceId(){
		return this.sourceId;
	}
	
	public void setSourceId(Long sourceId){
		this.sourceId = sourceId;
	}
	public Byte getSourceType(){
		return this.sourceType;
	}
	
	public void setSourceType(Byte sourceType){
		this.sourceType = sourceType;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public String getLinkUrl(){
		return this.linkUrl;
	}
	
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
