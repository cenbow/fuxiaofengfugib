package com.cqliving.cloud.online.info.domain;


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
 * 资讯标签表 Entity
 * Date: 2016-05-06 10:52:59
 * @author Code Generator
 */
@Entity
@Table(name = "info_lable")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoLable extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 名称 */
	private String name;
	/** 业务类型 */
	private Byte sourceType;
	
	/** 新闻 */
	public static final Byte SOURCE_TYPE_1 = 1;
	/** 问政 */
//	public static final Byte SOURCE_TYPE_2 = 2;
	/** 商情 */
	public static final Byte SOURCE_TYPE_3 = 3;
	/** 随手拍 */
//	public static final Byte SOURCE_TYPE_4 = 4;
	/** 段子 */
//	public static final Byte SOURCE_TYPE_5 = 5;
	/** 活动 */
//	public static final Byte SOURCE_TYPE_6 = 6;
	/** 话题 */
//	public static final Byte SOURCE_TYPE_7 = 7;
	/** 便民 */
//	public static final Byte SOURCE_TYPE_8 = 8;
	/** 热线 */
//	public static final Byte SOURCE_TYPE_9 = 9;
	
	public static final Map<Byte, String> allSourceTypes = Maps.newTreeMap();
	static {
		allSourceTypes.put(SOURCE_TYPE_1, "新闻");
//		allSourceTypes.put(SOURCE_TYPE_2, "问政");
		allSourceTypes.put(SOURCE_TYPE_3, "商情");
//		allSourceTypes.put(SOURCE_TYPE_4, "随手拍");
//		allSourceTypes.put(SOURCE_TYPE_5, "段子");
//		allSourceTypes.put(SOURCE_TYPE_6, "活动");
//		allSourceTypes.put(SOURCE_TYPE_7, "话题");
//		allSourceTypes.put(SOURCE_TYPE_8, "便民");
//		allSourceTypes.put(SOURCE_TYPE_9, "热线");
	}
	
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
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
