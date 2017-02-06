package com.cqliving.cloud.online.order.domain;


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
 * 订单与商品关联表 Entity
 * Date: 2016-11-21 21:35:07
 * @author Code Generator
 */
@Entity
@Table(name = "order_goods")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderGoods extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 否 */
	public static final Byte ISEVALUATE0 = 0;
	/** 是 */
	public static final Byte ISEVALUATE1 = 1;
		
	/** 是否已评价 */
	public static final Map<Byte, String> allIsEvaluates = Maps.newTreeMap();
	static {
		allIsEvaluates.put(ISEVALUATE0, "否");
		allIsEvaluates.put(ISEVALUATE1, "是");
	}
	/** 未退款 */
	public static final Byte REFUNDSTATUS1 = 1;
	/** 退款中*/
	public static final Byte REFUNDSTATUS2 = 2;
	/** 退款完成 */
	public static final Byte REFUNDSTATUS3 = 3;
	/** 退款失败，拒绝退款 */
	public static final Byte REFUNDSTATUS4 = 4;
		
	/** 退款状态 */
	public static final Map<Byte, String> allRefundStatus = Maps.newTreeMap();
	static {
		allRefundStatus.put(REFUNDSTATUS1, "未退款");
		allRefundStatus.put(REFUNDSTATUS2, "退款中");
		allRefundStatus.put(REFUNDSTATUS3, "退款成功");
		allRefundStatus.put(REFUNDSTATUS4, "退款失败");
	}
	
	/** 主键 */
	private Long id;
	/** 订单id */
	private Long orderId;
	/** 商品id */
	private Long goodsId;
	/** 是否已评价 */
	private Byte isEvaluate;
	/** 退款状态 */
	private Byte refundStatus;
	/** 商品名称 */
	private String goodsName;
	/** 商品单价，购买时的价格（分） */
	private Integer goodsPrice;
	/** 原价（分） */
	private Integer originalPrice;
	/** 商品图片 */
	private String goodsImageUrl;
	/** 商品数量 */
	private Integer quantity;
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
	public Long getOrderId(){
		return this.orderId;
	}
	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}
	public Long getGoodsId(){
		return this.goodsId;
	}
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}
	public Byte getIsEvaluate() {
		return isEvaluate;
	}
	public void setIsEvaluate(Byte isEvaluate) {
		this.isEvaluate = isEvaluate;
	}
	public Byte getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Byte refundStatus) {
		this.refundStatus = refundStatus;
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
	public Integer getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getGoodsImageUrl(){
		return this.goodsImageUrl;
	}
	public void setGoodsImageUrl(String goodsImageUrl){
		this.goodsImageUrl = goodsImageUrl;
	}
	public Integer getQuantity(){
		return this.quantity;
	}
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
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
