package com.org.weixin.module.zjchj.domain;


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

/**
 * 订单表 Entity
 * Date: 2016-09-26 15:18:38
 * @author Code Generator
 */
@Entity
@Table(name = "zjchj_bill_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZjchjBillInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 用户id */
	private Long userId;
	/** 业务流水号 */
	private String serialId;
	/** 订单号 */
	private String billSerialNumber;
	/** 第三方open_id */
	private String openId;
	/** 门店名称 */
	private String shopName;
	/** 菜品号 */
	private String itemsErial;
	/** 菜品名称 */
	private String itemsName;
	/** 创建时间 */
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getSerialId(){
		return this.serialId;
	}
	
	public void setSerialId(String serialId){
		this.serialId = serialId;
	}
	public String getBillSerialNumber(){
		return this.billSerialNumber;
	}
	
	public void setBillSerialNumber(String billSerialNumber){
		this.billSerialNumber = billSerialNumber;
	}
	public String getOpenId(){
		return this.openId;
	}
	
	public void setOpenId(String openId){
		this.openId = openId;
	}
	public String getShopName(){
		return this.shopName;
	}
	
	public void setShopName(String shopName){
		this.shopName = shopName;
	}
	public String getItemsErial(){
		return this.itemsErial;
	}
	
	public void setItemsErial(String itemsErial){
		this.itemsErial = itemsErial;
	}
	public String getItemsName(){
		return this.itemsName;
	}
	
	public void setItemsName(String itemsName){
		this.itemsName = itemsName;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
