package com.cqliving.cloud.online.config.domain;


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
 * 评论APP配置表 Entity
 * Date: 2016-06-12 10:01:10
 * @author Code Generator
 */
@Entity
@Table(name = "comment_app_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommentAppConfig extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 否 */
	public static final Byte VALUE0 = 0;
	/** 是 */
	public static final Byte VALUE1 = 1;
		
	/** 参数值 */
	public static final Map<Byte, String> allValues = Maps.newTreeMap();
	static {
		allValues.put(VALUE0, "否");
		allValues.put(VALUE1, "是");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** comment_config表ID */
	private Long commentConfigId;
	/** 参数值 */
	private Byte value;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 类型 */
	private Byte type;
	
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
	public Byte getValue(){
		return this.value;
	}
	
	public void setValue(Byte value){
		this.value = value;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Long getUpdatorId(){
		return this.updatorId;
	}
	
	public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	
	public Long getCommentConfigId() {
		return commentConfigId;
	}

	public void setCommentConfigId(Long commentConfigId) {
		this.commentConfigId = commentConfigId;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
