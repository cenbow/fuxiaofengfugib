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
 * 订单操作流水记录表 Entity
 * Date: 2016-11-21 21:35:04
 * @author Code Generator
 */
@Entity
@Table(name = "order_flow")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderFlow extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 生成订单 */
	public static final Byte OPERATETYPE1 = 1;
	/** 订单过期 */
	public static final Byte OPERATETYPE2 = 2;
	/** 取消订单 */
	public static final Byte OPERATETYPE3 = 3;
	/** 支付 */
	public static final Byte OPERATETYPE4 = 4;
	/** 发货 */
	public static final Byte OPERATETYPE5 = 5;
	/** 申请退款 */
	public static final Byte OPERATETYPE6 = 6;
	/** 确认退款 */
	public static final Byte OPERATETYPE7 = 7;
	/** 拒绝退款 */
	public static final Byte OPERATETYPE8 = 8;
	/** 确认收货 */
	public static final Byte OPERATETYPE9 = 9;
	/** 评价 */
	public static final Byte OPERATETYPE10 = 10;
	/** 删除 */
	public static final Byte OPERATETYPE11 = 11;
		
	/** 操作类型 */
	public static final Map<Byte, String> allOperateTypes = Maps.newTreeMap();
	static {
		allOperateTypes.put(OPERATETYPE1, "生成订单");
		allOperateTypes.put(OPERATETYPE2, "订单过期");
		allOperateTypes.put(OPERATETYPE3, "取消订单");
		allOperateTypes.put(OPERATETYPE4, "支付");
		allOperateTypes.put(OPERATETYPE5, "发货");
		allOperateTypes.put(OPERATETYPE6, "申请退款");
		allOperateTypes.put(OPERATETYPE7, "确认退款");
		allOperateTypes.put(OPERATETYPE8, "拒绝退款");
		allOperateTypes.put(OPERATETYPE9, "确认收货");
		allOperateTypes.put(OPERATETYPE10, "评价");
		allOperateTypes.put(OPERATETYPE11, "删除");
	}
	/** app用户 */
	public static final Byte USERTYPE1 = 1;
	/** 后台用户 */
	public static final Byte USERTYPE2 = 2;
		
	/** 用户类型 */
	public static final Map<Byte, String> allUserTypes = Maps.newTreeMap();
	static {
		allUserTypes.put(USERTYPE1, "app用户");
		allUserTypes.put(USERTYPE2, "后台用户");
	}
	
	/** 主键 */
	private Long id;
	/** 客户端id */
	private Long appId;
	/** 总订单id */
	private Long orderId;
	/** 操作类型 */
	private Byte operateType;
	/** 描述 */
	private String description;
	/** 用户类型 */
	private Byte userType;
	/** 用户id，如果user_type=1，则为user_account表的主键。如果user_type=2，则为sys_user表的主键 */
	private Long userId;
	/** 创建时间 */
	private Date createTime;
	/** ip地址 */
	private String ip;
	
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
	public Byte getOperateType(){
		return this.operateType;
	}
	
	public void setOperateType(Byte operateType){
		this.operateType = operateType;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public Byte getUserType(){
		return this.userType;
	}
	
	public void setUserType(Byte userType){
		this.userType = userType;
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
	public String getIp(){
		return this.ip;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
