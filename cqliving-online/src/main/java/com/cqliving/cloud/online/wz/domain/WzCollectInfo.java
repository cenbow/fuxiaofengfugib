package com.cqliving.cloud.online.wz.domain;


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
 * 信息收集表 Entity
 * Date: 2016-05-10 09:47:12
 * @author Code Generator
 */
@Entity
@Table(name = "wz_collect_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WzCollectInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 不必填 */
	public static final Byte ISREQUIRED0 = 0;
	/** 必填 */
	public static final Byte ISREQUIRED1 = 1;
	
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** wz_app_authority表ID */
	private Long appAuthorityId;
	/** 参数名称 */
	private String name;
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
	/** 是否必填 */
	private Byte isRequired;
	
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
	public Long getAppAuthorityId(){
		return this.appAuthorityId;
	}
	
	public void setAppAuthorityId(Long appAuthorityId){
		this.appAuthorityId = appAuthorityId;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
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
	
	public Byte getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Byte isRequired) {
		this.isRequired = isRequired;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
