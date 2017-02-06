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
 * 购物车 Entity
 * Date: 2016-11-21 21:35:52
 * @author Code Generator
 */
@Entity
@Table(name = "order_shop_cart")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderShopCart extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键 */
	private Long id;
	/** 客户端_id */
	private Long appId;
	/** 商品id，对应（shopping_goods表id） */
	private Long goodsId;
	/** 数量 */
	private Integer quantity;
	/** 用户id */
	private Long userId;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	
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
	public Long getGoodsId(){
		return this.goodsId;
	}
	
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}
	public Integer getQuantity(){
		return this.quantity;
	}
	
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
