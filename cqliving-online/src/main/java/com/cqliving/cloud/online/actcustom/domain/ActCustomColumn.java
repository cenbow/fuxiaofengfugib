package com.cqliving.cloud.online.actcustom.domain;


import java.util.Map;

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
 * 报名活动自定义收集列 Entity
 * Date: 2016-12-21 09:29:37
 * @author Code Generator
 */
@Entity
@Table(name = "act_custom_column")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActCustomColumn extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 身份证 */
	public static final Byte TYPE1 = 1;
	/** 手机 */
	public static final Byte TYPE2 = 2;
	/** 用户名 */
	public static final Byte TYPE3 = 3;
	/** 其它 */
	public static final Byte TYPE9 = 9;
		
	/** 参数类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE1, "身份证");
		allTypes.put(TYPE2, "手机");
		allTypes.put(TYPE3, "用户名");
		allTypes.put(TYPE9, "其它");
	}
	
	/** ID */
	private Long id;
	/** 参数名称 */
	private String name;
	/** 参数类型 */
	private Byte type;
	/** 描述信息 */
	private String description;
	
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
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
