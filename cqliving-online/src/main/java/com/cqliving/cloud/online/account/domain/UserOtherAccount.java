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
 * 用户其他平台账号表 Entity
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
@Entity
@Table(name = "USER_OTHER_ACCOUNT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserOtherAccount extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 微信 */
	public static final Byte TYPE0 = 0;
	/** 微博 */
	public static final Byte TYPE1 = 1;
    /** QQ */
    public static final Byte TYPE2 = 2;
    /** 其他 */
    public static final Byte TYPE10 = 10;
		
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "微信");
		allTypes.put(TYPE1, "微博");
		allTypes.put(TYPE2, "QQ");
		allTypes.put(TYPE10, "其他");
	}
	
	/** id */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 类型 */
	private Byte type;
	/** 用户在其他平台的标识 */
	private String userToken;
	/** 注册时间 */
	private Date registTime;
	
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
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public String getUserToken(){
		return this.userToken;
	}
	
	public void setUserToken(String userToken){
		this.userToken = userToken;
	}
	public Date getRegistTime(){
		return this.registTime;
	}
	
	public void setRegistTime(Date registTime){
		this.registTime = registTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
