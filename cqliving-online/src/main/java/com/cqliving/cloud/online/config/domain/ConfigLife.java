package com.cqliving.cloud.online.config.domain;


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

/**
 * 中国建行银行悦生活服务接口 Entity
 * Date: 2016-06-16 17:48:17
 * @author Code Generator
 */
@Entity
@Table(name = "config_life")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConfigLife extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键ID */
	private Long id;
	/** 缴费二级类型描述 */
	private String itemDesc;
	/** 缴费二级类型名称 */
	private String billItem;
	/** 缴费一级类型名称 */
	private String billType;
	/** 缴费一级类型描述 */
	private String billTypeDesc;
	/** 自定义类型，例如1代表水费 */
	private Integer type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getItemDesc(){
		return this.itemDesc;
	}
	
	public void setItemDesc(String itemDesc){
		this.itemDesc = itemDesc;
	}
	public String getBillItem(){
		return this.billItem;
	}
	
	public void setBillItem(String billItem){
		this.billItem = billItem;
	}
	public String getBillType(){
		return this.billType;
	}
	
	public void setBillType(String billType){
		this.billType = billType;
	}
	public String getBillTypeDesc(){
		return this.billTypeDesc;
	}
	
	public void setBillTypeDesc(String billTypeDesc){
		this.billTypeDesc = billTypeDesc;
	}
	public Integer getType(){
		return this.type;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
