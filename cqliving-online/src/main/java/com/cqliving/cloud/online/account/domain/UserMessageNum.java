package com.cqliving.cloud.online.account.domain;


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
 * 用户消息通知数量表 Entity
 * Date: 2016-05-12 11:23:50
 * @author Code Generator
 */
@Entity
@Table(name = "user_message_num")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserMessageNum extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 问政通知 */
	public static final Byte TYPE10 = 10;
	/** 问政评论通知 */
	public static final Byte TYPE11 = 11;
	/** 新闻评论通知 */
	public static final Byte TYPE20 = 20;
		
	/** 通知类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE10, "问政通知");
		allTypes.put(TYPE11, "问政评论通知");
		allTypes.put(TYPE20, "新闻评论通知");
	}
	
	/** ID */
	private Long id;
	/** 客户端ID */
	private Long appId;
	/** 用户ID */
	private Long userId;
	/** 通知数量 */
	private Integer quantity;
	/** 通知类型 */
	private Byte type;
	/** 更新时间 */
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
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public Integer getQuantity(){
		return this.quantity;
	}
	
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
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
