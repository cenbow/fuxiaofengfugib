package com.cqliving.cloud.online.wz.domain;


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
 * 问政权限设置值表 Entity
 * Date: 2016-05-10 09:46:20
 * @author Code Generator
 */
@Entity
@Table(name = "wz_app_authority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WzAppAuthority extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 选择型(是和否) */
	public static final int AUTHORITYID1 = 1;
	/** 文本型(输入框) */
	public static final int AUTHORITYID2 = 2;
	/** 参数收集型 */
	public static final int AUTHORITYID3 = 3;
		
	/** 参数类型 */
	public static final Map<Integer, String> allAuthorityIds = Maps.newTreeMap();
	static {
		allAuthorityIds.put(AUTHORITYID1, "选择型(是和否)");
		allAuthorityIds.put(AUTHORITYID2, "文本型(输入框)");
		allAuthorityIds.put(AUTHORITYID3, "参数收集型");
	}
	/** 否 */
	public static final String VALUE0 = "0";
	/** 是 */
	public static final String VALUE1 = "1";
		
	/** 权限配置值 */
	public static final Map<String, String> allValues = Maps.newTreeMap();
	static {
		allValues.put(VALUE0, "否");
		allValues.put(VALUE1, "是");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 参数类型 */
	private Long authorityId;
	/** 权限配置值 */
	private String value;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	
	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
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
