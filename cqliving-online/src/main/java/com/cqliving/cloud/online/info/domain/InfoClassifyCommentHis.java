package com.cqliving.cloud.online.info.domain;


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
 * 资讯栏目历史推荐表 Entity
 * Date: 2016-04-29 16:22:23
 * @author Code Generator
 */
@Entity
@Table(name = "INFO_CLASSIFY_COMMENT_HIS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoClassifyCommentHis extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 资讯ID */
	private Long infoClassifyId;
	/** 目标客户端ID，多个用,分隔 */
	private String targetAppId;
	/** 推荐时间 */
	private Date commentTime;
	
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
	
	public Long getInfoClassifyId() {
		return infoClassifyId;
	}

	public void setInfoClassifyId(Long infoClassifyId) {
		this.infoClassifyId = infoClassifyId;
	}

	public String getTargetAppId(){
		return this.targetAppId;
	}
	
	public void setTargetAppId(String targetAppId){
		this.targetAppId = targetAppId;
	}
	public Date getCommentTime(){
		return this.commentTime;
	}
	
	public void setCommentTime(Date commentTime){
		this.commentTime = commentTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
