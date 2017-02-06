package com.cqliving.cloud.online.security.domain;


import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 系统组织机构表 Entity
 * Date: 2016-04-20 11:27:25
 * @author Code Generator
 */
@Entity
@Table(name = "sys_organization")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysOrganization extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 禁用 */
	public static final Byte STATUS0 = 0;
	/** 启用 */
	public static final Byte STATUS1 = 1;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS0, "禁用");
		allStatuss.put(STATUS1, "启用");
	}
	
	/** 主键ID */
	private Long id;
	/** 父ID */
	private Long parentId;
	/** 组织机构名称 */
	private String name;
	/** 组织机构CODE，示例：001001001 */
	private String code;
	/** 状态 */
	private Byte status;
	/** 层级 */
	private Integer level;
	
	private List<SysOrganization> subSysOrg;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="parentId")
	@OrderBy(value="level asc")
	public List<SysOrganization> getSubSysOrg() {
		return subSysOrg;
	}

	public void setSubSysOrg(List<SysOrganization> subSysOrg) {
		this.subSysOrg = subSysOrg;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getParentId(){
		return this.parentId;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Integer getLevel(){
		return this.level;
	}
	
	public void setLevel(Integer level){
		this.level = level;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
