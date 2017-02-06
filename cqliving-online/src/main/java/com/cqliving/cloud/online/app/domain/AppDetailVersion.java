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
 * 客户端内容版本表 Entity
 * Date: 2016-04-29 16:18:29
 * @author Code Generator
 */
@Entity
@Table(name = "APP_DETAIL_VERSION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppDetailVersion extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 软件版本 */
	public static final Byte TYPE0 = 0;
	/** 广告loading图版本 */
	public static final Byte TYPE1 = 1;
	/** 栏目版本 */
	public static final Byte TYPE2 = 2;
		
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "软件版本");
		allTypes.put(TYPE1, "广告loading图版本");
		allTypes.put(TYPE2, "栏目版本");
	}
	
	/** 主键 */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 类型 */
	private Byte type;
	/** 发布时间 */
	private Date publishTime;
	/** 版本号 */
	private Integer versionNo;
	
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
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public Date getPublishTime(){
		return this.publishTime;
	}
	
	public void setPublishTime(Date publishTime){
		this.publishTime = publishTime;
	}
	public Integer getVersionNo(){
		return this.versionNo;
	}
	
	public void setVersionNo(Integer versionNo){
		this.versionNo = versionNo;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
