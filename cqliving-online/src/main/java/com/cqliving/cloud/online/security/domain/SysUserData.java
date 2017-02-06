package com.cqliving.cloud.online.security.domain;


import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 系统用户数据权限表，目前数据权限的值为app_id对应的值 Entity
 * Date: 2016-05-03 15:25:12
 * @author Code Generator
 */
@Entity
@Table(name = "sys_user_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUserData extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 类型{1:APPID} */
	public static final byte TYPE1 = 1;
	/** 类型{2:栏目ID} */
	public static final byte TYPE2 = 2;
	/** 类型{3:商情分类表} */
	public static final byte TYPE3 = 3;
	
	public static final Map<Byte,String> TYPEMAP = Maps.newHashMap();
	
	static {
		TYPEMAP.put(TYPE1, "APPID");
		TYPEMAP.put(TYPE2, "栏目ID");
		TYPEMAP.put(TYPE3, "商情分类表");
	}
	
	/** 主键ID */
	private Long id;
	/** 用户（sys_user表）ID */
	private Long userId;
	/** 数据权限值，根据type字段分别对应{1:APP_INFO表ID,2:app_columns表ID,3:shop_type表ID} */
	private Long value;
	/** 类型{1:APPID,2:栏目ID,3:商情分类表} */
	private Byte type;
	
	@Id
	@GeneratedValue
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
	public Long getValue(){
		return this.value;
	}
	
	public void setValue(Long value){
		this.value = value;
	}
	
	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
