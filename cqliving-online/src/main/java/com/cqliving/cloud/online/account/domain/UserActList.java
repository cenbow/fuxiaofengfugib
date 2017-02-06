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
 * 用户参与活动表 Entity
 * Date: 2016-06-07 14:05:59
 * @author Code Generator
 */
@Entity
@Table(name = "user_act_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActList extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 活动表ID */
	private Long actInfoId;
	/** 活动集合 表ID */
	private Long actInfoListId;
	/** 参与时间 */
	private Date createTime;
	/** IP */
	private String ip;
	/** 参加次数 */
	private Integer joinCount;
	/** 最与参与时间 */
	private Date updateTime;
	
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
	public Long getActInfoId(){
		return this.actInfoId;
	}
	
	public void setActInfoId(Long actInfoId){
		this.actInfoId = actInfoId;
	}
	public Long getActInfoListId(){
		return this.actInfoListId;
	}
	
	public void setActInfoListId(Long actInfoListId){
		this.actInfoListId = actInfoListId;
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
	public Integer getJoinCount(){
		return this.joinCount;
	}
	
	public void setJoinCount(Integer joinCount){
		this.joinCount = joinCount;
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
