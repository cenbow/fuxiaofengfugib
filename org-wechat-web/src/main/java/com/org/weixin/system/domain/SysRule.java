package com.org.weixin.system.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.wechat.framework.domain.AbstractEntity;

/**
 * sys_rule Entity
 *
 * Date: 2015-07-23 20:46:53
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_rule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRule extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** id */
	private Long id;
	/** uniacid */
	private Long uniacid;
	/** 规则名称 */
	private String name;
	/** 模块ID */
	private Long moduleId;
	/** 拦截顺序 */
	private Integer displayorder;
	/** 状态 */
	private byte status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getUniacid(){
		return this.uniacid;
	}
	
	public void setUniacid(Long uniacid){
		this.uniacid = uniacid;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Long getModuleId(){
		return this.moduleId;
	}
	
	public void setModuleId(Long moduleId){
		this.moduleId = moduleId;
	}
	public Integer getDisplayorder(){
		return this.displayorder;
	}
	
	public void setDisplayorder(Integer displayorder){
		this.displayorder = displayorder;
	}
	public byte getStatus(){
		return this.status;
	}
	
	public void setStatus(byte status){
		this.status = status;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
