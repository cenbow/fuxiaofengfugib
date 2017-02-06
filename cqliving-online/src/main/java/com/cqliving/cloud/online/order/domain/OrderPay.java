package com.cqliving.cloud.online.order.domain;


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
 * 订单合并支付表（用于支付宝回调唯一标识），用于订单合并支付（订单合并支付时支付流水号与订单关系为一对多，否则一对一） Entity
 * Date: 2016-11-21 21:35:20
 * @author Code Generator
 */
@Entity
@Table(name = "order_pay")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderPay extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键 */
	private Long id;
	/** 订单id */
	private Long orderId;
	/** 订单支付流水号（支付宝回调的唯一标识，合并支付时与订单id关系为一对多，一为支付流水号，多为订单号） */
	private String orderPayNo;
	/** 订单创建时间 */
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getOrderId(){
		return this.orderId;
	}
	
	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}
	public String getOrderPayNo(){
		return this.orderPayNo;
	}
	
	public void setOrderPayNo(String orderPayNo){
		this.orderPayNo = orderPayNo;
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
