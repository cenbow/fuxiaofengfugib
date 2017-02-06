package com.cqliving.cloud.online.shopping.domain;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 运费模板明细表 Entity
 * Date: 2016-11-17 15:41:25
 * @author Code Generator
 */
@Entity
@Table(name = "shopping_fare_template_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShoppingFareTemplateDetail extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 是 */
	public static final Byte ISDEFAULT1 = 1;
	/** 否 */
	public static final Byte ISDEFAULT0 = 0;
		
	/** 是否默认 */
	public static final Map<Byte, String> allIsDefaults = Maps.newTreeMap();
	static {
		allIsDefaults.put(ISDEFAULT1, "是");
		allIsDefaults.put(ISDEFAULT0, "否");
	}
	/** 正常 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "正常");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 运费模板ID */
	private Long fareTemplateId;
	/** 一级区域名称（展示用，逗号分隔） */
	private String regionNames;
	/** 是否默认 */
	private Byte isDefault;
	/** 基础运费件数 */
	private Integer baseFareCount;
	/** 基础运费 */
	private Integer baseFare;
	/** 续件运费件数 */
	private Integer addFareCount;
	/** 续件运费 */
	private Integer addFare;
	/** 状态 */
	private Byte status;
	/** 排序号（默认Integer.MAX_VALUE） */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 运费模板区域  */
	private List<ShoppingFareTemplateRegionDetail> regions;
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
	public Long getFareTemplateId(){
		return this.fareTemplateId;
	}
	
	public void setFareTemplateId(Long fareTemplateId){
		this.fareTemplateId = fareTemplateId;
	}
	public String getRegionNames(){
		return this.regionNames;
	}
	
	public void setRegionNames(String regionNames){
		this.regionNames = regionNames;
	}
	public Byte getIsDefault(){
		return this.isDefault;
	}
	
	public void setIsDefault(Byte isDefault){
		this.isDefault = isDefault;
	}
	public Integer getBaseFareCount(){
		return this.baseFareCount;
	}
	
	public void setBaseFareCount(Integer baseFareCount){
		this.baseFareCount = baseFareCount;
	}
	public Integer getBaseFare(){
		return this.baseFare;
	}
	
	public void setBaseFare(Integer baseFare){
		this.baseFare = baseFare;
	}
	public Integer getAddFareCount(){
		return this.addFareCount;
	}
	
	public void setAddFareCount(Integer addFareCount){
		this.addFareCount = addFareCount;
	}
	public Integer getAddFare(){
		return this.addFare;
	}
	
	public void setAddFare(Integer addFare){
		this.addFare = addFare;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
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
	
	@OneToMany(targetEntity=ShoppingFareTemplateRegionDetail.class,fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinColumn(name="fareTemplateDetailId",insertable=false,updatable=false)
	public List<ShoppingFareTemplateRegionDetail> getRegions() {
		return regions;
	}

	public void setRegions(List<ShoppingFareTemplateRegionDetail> regions) {
		this.regions = regions;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
