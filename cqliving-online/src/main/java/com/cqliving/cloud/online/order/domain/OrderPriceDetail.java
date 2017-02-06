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
 * 订单金额明细表 Entity
 * Date: 2016-11-21 21:35:33
 * @author Code Generator
 */
@Entity
@Table(name = "order_price_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderPriceDetail extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 商品金额 */
	public static final Byte DISCOUNTTYPE1 = 1;
	/** 运费 */
	public static final Byte DISCOUNTTYPE2 = 2;
	/** 折扣 */
	public static final Byte DISCOUNTTYPE3 = 3;
	/** 积分抵扣 */
	public static final Byte DISCOUNTTYPE4 = 4;
	/** 优惠券 */
	public static final Byte DISCOUNTTYPE5 = 5;
	/** 会员折扣 */
	public static final Byte DISCOUNTTYPE6 = 6;
		
	/** 金额类型 */
	public static final Map<Byte, String> allDiscountTypes = Maps.newTreeMap();
	static {
		allDiscountTypes.put(DISCOUNTTYPE1, "商品金额");
		allDiscountTypes.put(DISCOUNTTYPE2, "运费");
		allDiscountTypes.put(DISCOUNTTYPE3, "折扣");
		allDiscountTypes.put(DISCOUNTTYPE4, "积分抵扣");
		allDiscountTypes.put(DISCOUNTTYPE5, "优惠券");
		allDiscountTypes.put(DISCOUNTTYPE6, "会员折扣");
	}
	
	/** 主键 */
	private Long id;
	/** 订单id */
	private Long orderId;
	/** 用户id */
	private Long userId;
	/** 金额类型 */
	private Byte discountType;
	/** 金额（单位：分），有正负 */
	private Integer discountAmount;
	/** 备注 */
	private String descn;
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
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public Byte getDiscountType(){
		return this.discountType;
	}
	
	public void setDiscountType(Byte discountType){
		this.discountType = discountType;
	}
	public Integer getDiscountAmount(){
		return this.discountAmount;
	}
	
	public void setDiscountAmount(Integer discountAmount){
		this.discountAmount = discountAmount;
	}
	public String getDescn(){
		return this.descn;
	}
	
	public void setDescn(String descn){
		this.descn = descn;
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
