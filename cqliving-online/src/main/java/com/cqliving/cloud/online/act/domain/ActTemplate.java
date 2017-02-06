package com.cqliving.cloud.online.act.domain;


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
 * 活动模板表 Entity
 * Date: 2016-06-07 09:21:56
 * @author Code Generator
 */
@Entity
@Table(name = "act_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActTemplate extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 投票 */
	public static final Byte TYPE3 = 3;
	/** 答题 */
	public static final Byte TYPE4 = 4;
	/** 报名 */
	public static final Byte TYPE5 = 5;
	/** 问卷 */
	public static final Byte TYPE6 = 6;
	/** 征集 */
	public static final Byte TYPE7 = 7;
	/** 抽奖 */
	public static final Byte TYPE8 = 8;
		
	/** 模板类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE3, "投票");
		allTypes.put(TYPE4, "答题");
		allTypes.put(TYPE5, "报名");
		allTypes.put(TYPE6, "问卷");
		allTypes.put(TYPE7, "征集");
		allTypes.put(TYPE8, "抽奖");
	}
	/** 公有 */
	public static final Byte COMMONTYPE1 = 1;
	/** APP私有 */
	public static final Byte COMMONTYPE2 = 2;
		
	/** 模版公有状态 */
	public static final Map<Byte, String> allCommonTypes = Maps.newTreeMap();
	static {
		allCommonTypes.put(COMMONTYPE1, "公有");
		allCommonTypes.put(COMMONTYPE2, "APP私有");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 模板CODE */
	private String templetCode;
	/** 模板图片 */
	private String imageUrl;
	/** 模板类型 */
	private Byte type;
	/** 模版描述 */
	private String templetDesc;
	/** 模版名称 */
	private String name;
	/** 模版公有状态 */
	private Byte commonType;
	
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
	public String getTempletCode(){
		return this.templetCode;
	}
	
	public void setTempletCode(String templetCode){
		this.templetCode = templetCode;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public String getTempletDesc(){
		return this.templetDesc;
	}
	
	public void setTempletDesc(String templetDesc){
		this.templetDesc = templetDesc;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Byte getCommonType(){
		return this.commonType;
	}
	
	public void setCommonType(Byte commonType){
		this.commonType = commonType;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
