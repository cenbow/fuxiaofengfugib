package com.cqliving.cloud.online.config.domain;


import java.util.Date;
import java.util.Map;

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
 * 区域表 Entity
 * Date: 2016-05-24 16:01:50
 * @author Code Generator
 */
@Entity
@Table(name = "config_region")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConfigRegion extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 问政 */
	public static final Byte TYPE2 = 2;
	/** 商情 */
	public static final Byte TYPE3 = 3;
	/** 旅游 */
	public static final Byte TYPE10 = 10;
	/** 置业 */
	public static final Byte TYPE11 = 11;	
	/** 区域类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE2, "问政");
		allTypes.put(TYPE3, "商情");
		allTypes.put(TYPE10, "旅游");
		allTypes.put(TYPE11, "置业");
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
	
	/** 主键ID */
	private Long id;
	/** 客户端ID */
	private Long appId;
	/** 区域CODE */
	private String code;
	/** 区域CODE */
	private String pcode;
	/** 层级，1表示1级，2表示2级，以此类推 */
	private Integer level;
	/** 区域名称 */
	private String name;
	/** 区域名称全拼 */
	private String fullName;
	/** 区域名称拼音全拼 */
	private String phoneticizeAb;
	/** 区域名称拼音首字母 */
	private String phoneticize;
	/** 创建时间 */
	private Date createTime;
	/** 商情分类表ID，只对type=2时有效,对应shop_type表的ID */
	private Long shopTypeId;
	/** 区域类型 */
	private Byte type;
	/** 排序号 */
	private Integer sortNo;
	/** 状态 */
	private Byte status;
	
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
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	public String getPcode(){
		return this.pcode;
	}
	
	public void setPcode(String pcode){
		this.pcode = pcode;
	}
	public Integer getLevel(){
		return this.level;
	}
	
	public void setLevel(Integer level){
		this.level = level;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getFullName(){
		return this.fullName;
	}
	
	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	public String getPhoneticizeAb(){
		return this.phoneticizeAb;
	}
	
	public void setPhoneticizeAb(String phoneticizeAb){
		this.phoneticizeAb = phoneticizeAb;
	}
	public String getPhoneticize(){
		return this.phoneticize;
	}
	
	public void setPhoneticize(String phoneticize){
		this.phoneticize = phoneticize;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getShopTypeId(){
		return this.shopTypeId;
	}
	
	public void setShopTypeId(Long shopTypeId){
		this.shopTypeId = shopTypeId;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}