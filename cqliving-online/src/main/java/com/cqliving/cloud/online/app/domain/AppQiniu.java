package com.cqliving.cloud.online.app.domain;


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
 * APP七牛云服务配置 Entity
 * Date: 2016-05-24 17:03:56
 * @author Code Generator
 */
@Entity
@Table(name = "app_qiniu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppQiniu extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 默认 */
	public static final Byte ISDEFAULT1 = 1;
	/** 不是默认*/
	public static final Byte ISDEFAULT0 = 0;
	/** 有效 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 是否默认 */
	public static final Map<Byte, String> allIsDefault = Maps.newTreeMap();
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "有效");
		allStatuss.put(STATUS99, "删除");
		allIsDefault.put(ISDEFAULT1, "默认");
		allIsDefault.put(ISDEFAULT0, "不是默认");
	}
	
	/** 主键 */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 七牛云服务资源名称 */
	private String bucketName;
	/** 七牛提供的测试域名 */
	private String domainTest;
	/** 绑定的自定义域名 */
	private String domainCustom;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 更新时间 */
	private Date updateTime;
	/** 是否默认 */
	private Byte isDefault;
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
	public String getBucketName(){
		return this.bucketName;
	}
	
	public void setBucketName(String bucketName){
		this.bucketName = bucketName;
	}
	public String getDomainTest(){
		return this.domainTest;
	}
	
	public void setDomainTest(String domainTest){
		this.domainTest = domainTest;
	}
	public String getDomainCustom(){
		return this.domainCustom;
	}
	
	public void setDomainCustom(String domainCustom){
		this.domainCustom = domainCustom;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
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
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Byte getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
