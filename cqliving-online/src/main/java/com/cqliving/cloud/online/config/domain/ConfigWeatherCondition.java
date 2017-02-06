package com.cqliving.cloud.online.config.domain;


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

/**
 * 天气预报状态表 Entity
 * Date: 2016-05-25 20:24:32
 * @author Code Generator
 */
@Entity
@Table(name = "config_weather_condition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConfigWeatherCondition extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键 */
	private Long id;
	/** 天气状况代码 */
	private String code;
	/** 天气状况名称，例如：晴阴 */
	private String name;
	/** 天气状况英文名称 */
	private String english;
	/** icon_url */
	private String iconUrl;
	
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
	public String getEnglish(){
		return this.english;
	}
	
	public void setEnglish(String english){
		this.english = english;
	}
	public String getIconUrl(){
		return this.iconUrl;
	}
	
	public void setIconUrl(String iconUrl){
		this.iconUrl = iconUrl;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
