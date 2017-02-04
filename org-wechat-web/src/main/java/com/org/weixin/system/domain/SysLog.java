package com.org.weixin.system.domain;


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
import org.wechat.framework.domain.AbstractEntity;

/**
 * sys_log Entity
 *
 * Date: 2015-07-27 17:15:52
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** id */
	private Long id;
	/** 用户id */
	private Long userId;
	/** 模块名称 */
	private String module;
	/** 方法名称 */
	private String methods;
	/** 调用时间 */
	private Date invokeTime;
	/** 执行时长(毫秒) */
	private Long execEllapse;
	/** ip地址 */
	private String ip;
	/** 描述 */
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getModule(){
		return this.module;
	}
	
	public void setModule(String module){
		this.module = module;
	}
	public String getMethods(){
		return this.methods;
	}
	
	public void setMethods(String methods){
		this.methods = methods;
	}
	public Date getInvokeTime(){
		return this.invokeTime;
	}
	
	public void setInvokeTime(Date invokeTime){
		this.invokeTime = invokeTime;
	}
	public Long getExecEllapse(){
		return this.execEllapse;
	}
	
	public void setExecEllapse(Long execEllapse){
		this.execEllapse = execEllapse;
	}
	public String getIp(){
		return this.ip;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
