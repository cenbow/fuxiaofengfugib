package com.cqliving.cloud.online.account.domain;


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
 * 短信模版表 Entity
 * Date: 2016-05-18 20:40:17
 * @author Code Generator
 */
@Entity
@Table(name = "sms_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsTemplate extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 注册 */
	public static final Byte TYPE0 = 0;
	/** 登录 */
	public static final Byte TYPE1 = 1;
	/** 找回密码 */
	public static final Byte TYPE2 = 2;
	/** 修改密码 */
	public static final Byte TYPE3 = 3;
	/** 更换手机 */
	public static final Byte TYPE4 = 4;
		
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "注册");
		allTypes.put(TYPE1, "登录");
		allTypes.put(TYPE2, "找回密码");
		allTypes.put(TYPE3, "修改密码");
		allTypes.put(TYPE4, "更换手机");
	}
	
	/** ID */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 内容 */
	private String content;
	/** 类型 */
	private Byte type;
	
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
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
