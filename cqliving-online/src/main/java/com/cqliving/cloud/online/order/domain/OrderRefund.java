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
 * 订单商品退货表，针对订单里面的商品退货 Entity
 * Date: 2016-11-21 21:35:45
 * @author Code Generator
 */
@Entity
@Table(name = "order_refund")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderRefund extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键 */
	private Long id;
	/** 订单id */
	private Long orderId;
	/** 订单编号 */
	private String orderNo;
	/** 商品id */
	private Long goodsId;
	/** 商品名称 */
	private String goodsName;
	/** 商品单价，购买时的商品价格（分） */
	private Integer goodsPrice;
	/** 商品原价 */
	private Integer originalPrice;
	/** 买家支付帐号 */
	private String userPayAccount;
	/** 退款金额（分），可能有折扣，退款金额不能大于用户当前订单的支付金额 */
	private Integer refundAmount;
	/** 商品数量 */
	private Integer quantity;
	/** 退货回执单号 */
	private String receiptNo;
	/** 申请退款图片（逗号分隔），最多9张 */
	private String refundImagesUrl;
	/** 退款原因 */
	private String refundReason;
	/** 创建时间，提交退货时间 */
	private Date createTime;
	/** 拒绝退款时间 */
	private Date refuseTime;
	/** 拒绝退款原因 */
	private String refuseReason;
	/** 同意退款时间 */
	private Date agreeTime;
	/** 同意退款原因 */
	private String agreeReason;
	/** 商品图片 */
	private String goodsImageUrl;
	
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
	public String getOrderNo(){
		return this.orderNo;
	}
	
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	public Long getGoodsId(){
		return this.goodsId;
	}
	
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}
	public String getGoodsName(){
		return this.goodsName;
	}
	
	public void setGoodsName(String goodsName){
		this.goodsName = goodsName;
	}
	public Integer getGoodsPrice(){
		return this.goodsPrice;
	}
	
	public void setGoodsPrice(Integer goodsPrice){
		this.goodsPrice = goodsPrice;
	}
	public String getUserPayAccount(){
		return this.userPayAccount;
	}
	
	public void setUserPayAccount(String userPayAccount){
		this.userPayAccount = userPayAccount;
	}
	public Integer getRefundAmount(){
		return this.refundAmount;
	}
	
	public void setRefundAmount(Integer refundAmount){
		this.refundAmount = refundAmount;
	}
	public Integer getQuantity(){
		return this.quantity;
	}
	
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}
	public String getReceiptNo(){
		return this.receiptNo;
	}
	
	public void setReceiptNo(String receiptNo){
		this.receiptNo = receiptNo;
	}
	public String getRefundImagesUrl(){
		return this.refundImagesUrl;
	}
	
	public void setRefundImagesUrl(String refundImagesUrl){
		this.refundImagesUrl = refundImagesUrl;
	}
	public String getRefundReason(){
		return this.refundReason;
	}
	
	public void setRefundReason(String refundReason){
		this.refundReason = refundReason;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getRefuseTime(){
		return this.refuseTime;
	}
	
	public void setRefuseTime(Date refuseTime){
		this.refuseTime = refuseTime;
	}
	public String getRefuseReason(){
		return this.refuseReason;
	}
	
	public void setRefuseReason(String refuseReason){
		this.refuseReason = refuseReason;
	}
	public Date getAgreeTime(){
		return this.agreeTime;
	}
	
	public void setAgreeTime(Date agreeTime){
		this.agreeTime = agreeTime;
	}
	public String getAgreeReason(){
		return this.agreeReason;
	}
	
	public void setAgreeReason(String agreeReason){
		this.agreeReason = agreeReason;
	}
	
	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getGoodsImageUrl() {
		return goodsImageUrl;
	}

	public void setGoodsImageUrl(String goodsImageUrl) {
		this.goodsImageUrl = goodsImageUrl;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
