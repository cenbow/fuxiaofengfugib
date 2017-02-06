package com.cqliving.cloud.online.info.domain;


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
 * 资讯栏目推荐表 Entity
 * Date: 2016-04-29 16:22:23
 * @author Code Generator
 */
@Entity
@Table(name = "INFO_CLASSIFY_COMMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoClassifyComment extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 推荐状态 */
	public static final Byte STATUS0 = 0;
	/** 已忽略 */
	public static final Byte STATUS1 = 1;
	/** 已使用 */
	public static final Byte STATUS2 = 2;
		
	/** 处理状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS0, "未处理");
		allStatuss.put(STATUS1, "已忽略");
		allStatuss.put(STATUS2, "已使用");
	}
	
	/** ID */
	private Long id;
	/** 推荐历史ID */
	private Long hisId;
	/** 来源客户端_ID */
	private Long sourceAppId;
	/** 资讯ID */
	private Long sourceInfoClassifyId;
	/** 目标客户端ID */
	private Long targetAppId;
	/** 推荐时间 */
	private Date commentTime;
	/** 处理状态 */
	private Byte status;
	
	private Long newInfoClassifyId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getHisId(){
		return this.hisId;
	}
	
	public void setHisId(Long hisId){
		this.hisId = hisId;
	}
	public Long getSourceAppId(){
		return this.sourceAppId;
	}
	
	public void setSourceAppId(Long sourceAppId){
		this.sourceAppId = sourceAppId;
	}
	
	public Long getSourceInfoClassifyId() {
		return sourceInfoClassifyId;
	}

	public void setSourceInfoClassifyId(Long sourceInfoClassifyId) {
		this.sourceInfoClassifyId = sourceInfoClassifyId;
	}

	public Long getNewInfoClassifyId() {
		return newInfoClassifyId;
	}

	public void setNewInfoClassifyId(Long newInfoClassifyId) {
		this.newInfoClassifyId = newInfoClassifyId;
	}

	public Long getTargetAppId(){
		return this.targetAppId;
	}
	
	public void setTargetAppId(Long targetAppId){
		this.targetAppId = targetAppId;
	}
	public Date getCommentTime(){
		return this.commentTime;
	}
	
	public void setCommentTime(Date commentTime){
		this.commentTime = commentTime;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
