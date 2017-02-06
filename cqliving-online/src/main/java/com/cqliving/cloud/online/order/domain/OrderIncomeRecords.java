package com.cqliving.cloud.online.order.domain;


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
 * 订单收支记录表 Entity
 * Date: 2016-12-07 11:09:38
 * @author Code Generator
 */
@Entity
@Table(name = "order_income_records")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderIncomeRecords extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 支付宝 */
	public static final Byte PAYMODE1 = 1;
		
	/** 支付渠道 */
	public static final Map<Byte, String> allPayModes = Maps.newTreeMap();
	static {
		allPayModes.put(PAYMODE1, "支付宝");
	}
	
	/** 主键 */
	private Long id;
	/** 客户端id */
	private Long appId;
	/** 订单id，表order_info的主键 */
	private Long orderId;
	/** 订单号 */
	private String orderNo;
	/** 金额（单位：分），有正负 */
	private Integer money;
	/** 收支总金额（单位：分），把当前app之前所有收支记录的money相加，再加上本次money的值 */
	private Integer totalMoney;
	/** 支付渠道 */
	private Byte payMode;
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
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Long getOrderId(){
		return this.orderId;
	}
	
	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}
	public String getOrderNo(){
		return this.orderNo;
	}
	
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	public Integer getMoney(){
		return this.money;
	}
	
	public void setMoney(Integer money){
		this.money = money;
	}
	public Integer getTotalMoney(){
		return this.totalMoney;
	}
	
	public void setTotalMoney(Integer totalMoney){
		this.totalMoney = totalMoney;
	}
	public Byte getPayMode(){
		return this.payMode;
	}
	
	public void setPayMode(Byte payMode){
		this.payMode = payMode;
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
