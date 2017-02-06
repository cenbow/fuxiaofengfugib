package com.cqliving.cloud.online.app.domain;


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
 * 客户端模板表 Entity
 * Date: 2016-05-03 20:01:37
 * @author Code Generator
 */
@Entity
@Table(name = "app_templet")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTemplet extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	/** 新闻 */
    public static final Byte TYPE1 = 1;
	/** 外链类 */
	public static final Byte TYPE2 = 2;
	/** 自定义 */
	public static final Byte TYPE3 = 3;

    /** 状态 */
    public static final Map<Byte, String> allTypes = Maps.newTreeMap();
    
    static {
        allTypes.put(TYPE1, "新闻");
        allTypes.put(TYPE2, "外链类");
        allTypes.put(TYPE3, "自定义");
    }
    
    /** 公有 */
    public static final Byte COMMONTYPE1 = 1;
    /** APP私有 */
    public static final Byte COMMONTYPE2 = 2;
    
    /** 公有状态 */
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
	/** 模板名称 */
	private String name;
	/** 模板说明 */
	private String templetDesc;
	/** 模板类型 */
	private Byte type;
	/** 模板是否共有 */
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
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTempletDesc() {
        return templetDesc;
    }

    public void setTempletDesc(String templetDesc) {
        this.templetDesc = templetDesc;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getCommonType() {
        return commonType;
    }

    public void setCommonType(Byte commonType) {
        this.commonType = commonType;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}