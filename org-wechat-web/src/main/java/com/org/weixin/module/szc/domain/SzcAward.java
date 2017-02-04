package com.org.weixin.module.szc.domain;


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
 * szc_砂之船奖品表 Entity
 * Date: 2016-09-02 11:24:15
 * @author Code Generator
 */
@Entity
@Table(name = "szc_award")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SzcAward extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 奖品CODE */
	private String code;
	/** 奖品名称 */
	private String name;
	/** 奖品图片 */
	private String img;
	/** 奖品比例配置数量(虚拟数量) */
	private Integer virtualNum;
	/** 实际奖品发放数量 */
	private Integer actualNum;
	/** 开始时间 */
	private Date beginTime;
	/** 结束时间 */
	private Date endTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Integer getVirtualNum() {
		return virtualNum;
	}

	public void setVirtualNum(Integer virtualNum) {
		this.virtualNum = virtualNum;
	}

	public Integer getActualNum() {
		return actualNum;
	}

	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}

	public Date getBeginTime(){
		return this.beginTime;
	}
	
	public void setBeginTime(Date beginTime){
		this.beginTime = beginTime;
	}
	public Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
