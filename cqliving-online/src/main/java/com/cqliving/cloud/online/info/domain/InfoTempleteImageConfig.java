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
 * 模板图片配置表 Entity
 * Date: 2016-05-07 11:41:16
 * @author Code Generator
 */
@Entity
@Table(name = "info_templete_image_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoTempleteImageConfig extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 宽为维度，等比压缩 */
	public static final Byte LIMITTYPE0 = 0;
	/** 固定压缩 */
	public static final Byte LIMITTYPE1 = 1;
		
	/** 是否等比压缩，不限制是以 */
	public static final Map<Byte, String> allLimitTypes = Maps.newTreeMap();
	static {
		allLimitTypes.put(LIMITTYPE0, "宽为维度，等比压缩");
		allLimitTypes.put(LIMITTYPE1, "固定压缩");
	}
	/** 单图 */
	public static final Byte LISTTYPE0 = 0;
	/** 大图 */
	public static final Byte LISTTYPE1 = 1;
	/** 三图 */
	public static final Byte LISTTYPE2 = 2;
	/** 轮播图 */
	public static final Byte LISTTYPE3 = 3;
	/** 窄图 */
	public static final Byte LISTTYPE4 = 4;
	/** 窄图带标题 */
	public static final Byte LISTTYPE5 = 5;
	/** 列表图片类型 */
	public static final Map<Byte, String> allListTypes = Maps.newTreeMap();
	static {
		allListTypes.put(LISTTYPE0, "单图");
		allListTypes.put(LISTTYPE1, "大图");
		allListTypes.put(LISTTYPE2, "三图");
		allListTypes.put(LISTTYPE3, "轮播图");
		allListTypes.put(LISTTYPE4, "窄图");
		allListTypes.put(LISTTYPE5, "窄图带标题");
	}
	
	/** ID */
	private Long id;
	/** 模板名称 */
	private String name;
	/** 模板CODE */
	private String templetCode;
	/** 是否等比压缩，不限制是以 */
	private Byte limitType;
	/** 宽 */
	private Integer width;
	/** 高 */
	private Integer hight;
	/** 备注说明 */
	private String context;
	/** 列表图片类型 */
	private Byte listType;
	
	private Long appId;
	
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
	public String getTempletCode(){
		return this.templetCode;
	}
	
	public void setTempletCode(String templetCode){
		this.templetCode = templetCode;
	}
	public Byte getLimitType(){
		return this.limitType;
	}
	
	public void setLimitType(Byte limitType){
		this.limitType = limitType;
	}
	public Integer getWidth(){
		return this.width;
	}
	
	public void setWidth(Integer width){
		this.width = width;
	}
	public Integer getHight(){
		return this.hight;
	}
	
	public void setHight(Integer hight){
		this.hight = hight;
	}
	public String getContext(){
		return this.context;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	public Byte getListType(){
		return this.listType;
	}
	
	public void setListType(Byte listType){
		this.listType = listType;
	}
	
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
