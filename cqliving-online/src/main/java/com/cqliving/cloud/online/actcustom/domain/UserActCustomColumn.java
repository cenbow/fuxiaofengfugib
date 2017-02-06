package com.cqliving.cloud.online.actcustom.domain;


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
 * 用户报名活动自定义收集列 Entity
 * Date: 2016-12-21 09:31:25
 * @author Code Generator
 */
@Entity
@Table(name = "user_act_custom_column")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActCustomColumn extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 用户二维码扫描活动表，表user_act_custom_signup的code字段 */
	private Long userActCustomSignupId;
	/** 收集列配置表ID，表act_custom_column的主键 */
	private Long actCustomColumnId;
	/** 收集列的值 */
	private String value;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getUserActCustomSignupId(){
		return this.userActCustomSignupId;
	}
	
	public void setUserActCustomSignupId(Long userActCustomSignupId){
		this.userActCustomSignupId = userActCustomSignupId;
	}
	public Long getActCustomColumnId(){
		return this.actCustomColumnId;
	}
	
	public void setActCustomColumnId(Long actCustomColumnId){
		this.actCustomColumnId = actCustomColumnId;
	}
	public String getValue(){
		return this.value;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
