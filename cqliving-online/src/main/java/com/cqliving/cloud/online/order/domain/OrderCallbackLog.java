package com.cqliving.cloud.online.order.domain;


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
 * 订单支付宝回调日志 Entity
 * Date: 2016-11-21 21:34:46
 * @author Code Generator
 */
@Entity
@Table(name = "order_callback_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderCallbackLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键 */
	private Long id;
	/** 订单号，表order_pay中的order_pay_no字段 */
	private String orderPayOn;
	/** 支付宝交易流水号 */
	private String aliTradeNo;
	/** 订单类型 */
	private String orderType;
	/** 购买人支付宝账号 */
	private String buyerAccount;
	/** 购买人支付宝id */
	private String buyerId;
	/** 卖家支付宝账号 */
	private String sellerAccount;
	/** 卖家支付宝id */
	private String sellerId;
	/** 支付调用服务名 */
	private String serviceName;
	/** 调用是否成功 */
	private String ifSuccess;
	/** 返回通知id */
	private String notifyId;
	/** 返回通知类型 */
	private String notifyType;
	/** 返回通知时间 */
	private String notifyTime;
	/** 支付类型 */
	private String paymentType;
	/** 订单名称 */
	private String orderName;
	/** 支付费用 */
	private String payFee;
	/** 支付宝交易状态 */
	private String tradeStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getOrderPayOn(){
		return this.orderPayOn;
	}
	
	public void setOrderPayOn(String orderPayOn){
		this.orderPayOn = orderPayOn;
	}
	public String getAliTradeNo(){
		return this.aliTradeNo;
	}
	
	public void setAliTradeNo(String aliTradeNo){
		this.aliTradeNo = aliTradeNo;
	}
	public String getOrderType(){
		return this.orderType;
	}
	
	public void setOrderType(String orderType){
		this.orderType = orderType;
	}
	public String getBuyerAccount(){
		return this.buyerAccount;
	}
	
	public void setBuyerAccount(String buyerAccount){
		this.buyerAccount = buyerAccount;
	}
	public String getBuyerId(){
		return this.buyerId;
	}
	
	public void setBuyerId(String buyerId){
		this.buyerId = buyerId;
	}
	public String getSellerAccount(){
		return this.sellerAccount;
	}
	
	public void setSellerAccount(String sellerAccount){
		this.sellerAccount = sellerAccount;
	}
	public String getSellerId(){
		return this.sellerId;
	}
	
	public void setSellerId(String sellerId){
		this.sellerId = sellerId;
	}
	public String getServiceName(){
		return this.serviceName;
	}
	
	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}
	public String getIfSuccess(){
		return this.ifSuccess;
	}
	
	public void setIfSuccess(String ifSuccess){
		this.ifSuccess = ifSuccess;
	}
	public String getNotifyId(){
		return this.notifyId;
	}
	
	public void setNotifyId(String notifyId){
		this.notifyId = notifyId;
	}
	public String getNotifyType(){
		return this.notifyType;
	}
	
	public void setNotifyType(String notifyType){
		this.notifyType = notifyType;
	}
	public String getNotifyTime(){
		return this.notifyTime;
	}
	
	public void setNotifyTime(String notifyTime){
		this.notifyTime = notifyTime;
	}
	public String getPaymentType(){
		return this.paymentType;
	}
	
	public void setPaymentType(String paymentType){
		this.paymentType = paymentType;
	}
	public String getOrderName(){
		return this.orderName;
	}
	
	public void setOrderName(String orderName){
		this.orderName = orderName;
	}
	public String getPayFee(){
		return this.payFee;
	}
	
	public void setPayFee(String payFee){
		this.payFee = payFee;
	}
	public String getTradeStatus(){
		return this.tradeStatus;
	}
	
	public void setTradeStatus(String tradeStatus){
		this.tradeStatus = tradeStatus;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
