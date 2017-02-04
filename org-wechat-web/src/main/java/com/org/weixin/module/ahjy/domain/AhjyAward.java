package com.org.weixin.module.ahjy.domain;


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
 * 奖品表 Entity
 *
 * Date: 2016-03-29 11:44:40
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "ahjy_award")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AhjyAward extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 奖品CODE */
	private String code;
	/** 奖品名称 */
	private String name;
	/** 奖品图片 */
	private String img;
	/** 奖品数量 */
	private Integer num;
	
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
	public String getImg(){
		return this.img;
	}
	
	public void setImg(String img){
		this.img = img;
	}
	public Integer getNum(){
		return this.num;
	}
	
	public void setNum(Integer num){
		this.num = num;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
