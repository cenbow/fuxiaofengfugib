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
 * 前端资源权限，该表数据需要初始化好 Entity
 * Date: 2016-12-20 10:12:14
 * @author Code Generator
 */
@Entity
@Table(name = "permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Permission extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** id */
	private Long id;
	/** 资源名称 */
	private String name;
	/** 资源请求URL，根据该字段和用户请求的判断做比较来判断是否拥有权限值 */
	private String url;
	/** 表sys_res_typer的ID */
	private Long sysResTypeId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	public Long getSysResTypeId(){
		return this.sysResTypeId;
	}
	
	public void setSysResTypeId(Long sysResTypeId){
		this.sysResTypeId = sysResTypeId;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
