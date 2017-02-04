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
 * sys_rule_keyword Entity
 *
 * Date: 2015-07-23 20:46:53
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_rule_keyword")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRuleKeyword extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键 */
	private Long id;
	/** 规则ID */
	private Long ruleId;
	/** uniacid */
	private Long uniacid;
	/** 模块ID */
	private Long moduleId;
	/** 匹配关键字 */
	private String content;
	/** type */
	private byte type;
	/** displayorder */
	private byte displayorder;
	/** status */
	private byte status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getRuleId(){
		return this.ruleId;
	}
	
	public void setRuleId(Long ruleId){
		this.ruleId = ruleId;
	}
	public Long getUniacid(){
		return this.uniacid;
	}
	
	public void setUniacid(Long uniacid){
		this.uniacid = uniacid;
	}
	public Long getModuleId(){
		return this.moduleId;
	}
	
	public void setModuleId(Long moduleId){
		this.moduleId = moduleId;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public byte getType(){
		return this.type;
	}
	
	public void setType(byte type){
		this.type = type;
	}
	public byte getDisplayorder(){
		return this.displayorder;
	}
	
	public void setDisplayorder(byte displayorder){
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
