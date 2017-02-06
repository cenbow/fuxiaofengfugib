package com.cqliving.cloud.online.shopping.domain;


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
 * 运费模板明细地区表 Entity
 * Date: 2016-11-17 15:41:29
 * @author Code Generator
 */
@Entity
@Table(name = "shopping_fare_template_region_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShoppingFareTemplateRegionDetail extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 是 */
	public static final Byte ISSUBSELECTEDALL1 = 1;
	/** 否 */
	public static final Byte ISSUBSELECTEDALL0 = 0;
		
	/** 是否全选子区域 */
	public static final Map<Byte, String> allIsSubSelectedAlls = Maps.newTreeMap();
	static {
		allIsSubSelectedAlls.put(ISSUBSELECTEDALL1, "是");
		allIsSubSelectedAlls.put(ISSUBSELECTEDALL0, "否");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 运费模板ID */
	private Long fareTemplateId;
	/** 运费模板明细ID */
	private Long fareTemplateDetailId;
	/** 区域名称 */
	private String regionName;
	/** 区域ID */
	private Long regionId;
	/** 区域级别 */
	private Integer regionLevel;
	/** 是否全选子区域 */
	private Byte isSubSelectedAll;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	
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
	public Long getFareTemplateDetailId(){
		return this.fareTemplateDetailId;
	}
	
	public void setFareTemplateDetailId(Long fareTemplateDetailId){
		this.fareTemplateDetailId = fareTemplateDetailId;
	}
	public String getRegionName(){
		return this.regionName;
	}
	
	public void setRegionName(String regionName){
		this.regionName = regionName;
	}
	public Long getRegionId(){
		return this.regionId;
	}
	
	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}
	public Integer getRegionLevel(){
		return this.regionLevel;
	}
	
	public void setRegionLevel(Integer regionLevel){
		this.regionLevel = regionLevel;
	}
	public Byte getIsSubSelectedAll(){
		return this.isSubSelectedAll;
	}
	
	public void setIsSubSelectedAll(Byte isSubSelectedAll){
		this.isSubSelectedAll = isSubSelectedAll;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
