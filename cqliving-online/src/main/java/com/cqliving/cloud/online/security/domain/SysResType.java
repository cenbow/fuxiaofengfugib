package com.cqliving.cloud.online.security.domain;


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
 * 系统资源分类表 Entity
 * Date: 2016-06-29 17:07:53
 * @author Code Generator
 */
@Entity
@Table(name = "sys_res_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysResType extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 有效 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
	// 其他资源分类
    public static final Long OTHERSYSRESTYPEID = 1l;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "有效");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** 主键ID */
	private Long id;
	/** 资源名称 */
	private String name;
	/** 排序值 */
	private Integer sortNum;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createDate;
	
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
	public Integer getSortNum(){
		return this.sortNum;
	}
	
	public void setSortNum(Integer sortNum){
		this.sortNum = sortNum;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
