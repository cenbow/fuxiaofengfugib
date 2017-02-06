package com.cqliving.cloud.online.actcustom.domain;


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
 * 用户自定义投票活动表 Entity
 * Date: 2017-01-03 10:31:16
 * @author Code Generator
 */
@Entity
@Table(name = "user_act_custom_vote")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActCustomVote extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 用户二维码扫描活动表，表act_qrcode的code字估 */
	private String actQrcodeCode;
	/** 自定义活动投票选项表ID（act_custom_vote_item表主键） */
	private Long actCustomVoteItemId;
	/** 用户ID */
	private Long userId;
	/** 创建时间 */
	private Date createTime;
	/** 电话code*/
	private String sessionId;
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getActQrcodeCode(){
		return this.actQrcodeCode;
	}
	
	public void setActQrcodeCode(String actQrcodeCode){
		this.actQrcodeCode = actQrcodeCode;
	}
	public Long getActCustomVoteItemId(){
		return this.actCustomVoteItemId;
	}
	
	public void setActCustomVoteItemId(Long actCustomVoteItemId){
		this.actCustomVoteItemId = actCustomVoteItemId;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
