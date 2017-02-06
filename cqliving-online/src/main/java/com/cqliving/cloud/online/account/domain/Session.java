package com.cqliving.cloud.online.account.domain;


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
 * 会话表 Entity
 * Date: 2016-04-29 16:39:16
 * @author Code Generator
 */
@Entity
@Table(name = "SESSION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Session extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 来源APP */
	private Long sourceAppId;
	/** 会话code */
	private String sessionCode;
	/** 设备码 */
	private String phoneCode;
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
	public Long getSourceAppId(){
		return this.sourceAppId;
	}
	
	public void setSourceAppId(Long sourceAppId){
		this.sourceAppId = sourceAppId;
	}
	public String getSessionCode(){
		return this.sessionCode;
	}
	
	public void setSessionCode(String sessionCode){
		this.sessionCode = sessionCode;
	}
	public String getPhoneCode(){
		return this.phoneCode;
	}
	
	public void setPhoneCode(String phoneCode){
		this.phoneCode = phoneCode;
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
