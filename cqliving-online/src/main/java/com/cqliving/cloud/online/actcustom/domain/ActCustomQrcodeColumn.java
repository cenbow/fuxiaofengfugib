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
 * Date: 2016-12-21 09:29:50
 * @author Code Generator
 */
@Entity
@Table(name = "act_custom_qrcode_column")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActCustomQrcodeColumn extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 非必填 */
	public static final Byte ISREQUIRED0 = 0;
	/** 必填 */
	public static final Byte ISREQUIRED1 = 1;
		
	/** 是否必填 */
	public static final Map<Byte, String> allIsRequireds = Maps.newTreeMap();
	static {
		allIsRequireds.put(ISREQUIRED0, "非必填");
		allIsRequireds.put(ISREQUIRED1, "必填");
	}
	/** 不显示 */
	public static final Byte ISLISTVIEW0 = 0;
	/** 显示 */
	public static final Byte ISLISTVIEW1 = 1;
		
	/** 是否列表显示 */
	public static final Map<Byte, String> allIsListViews = Maps.newTreeMap();
	static {
		allIsListViews.put(ISLISTVIEW0, "不显示");
		allIsListViews.put(ISLISTVIEW1, "显示");
	}
	
	/** ID */
	private Long id;
	/** 用户二维码扫描活动表，表act_qrcode的code字段 */
	private String actQrcodeCode;
	/** 收集列配置表ID，表act_custom_column的主键 */
	private Long actCustomColumnId;
	/** 序号 */
	private Integer sortNo;
	/** 字段长度，为0则不检查，该字段的值不能大于user_act_custom_column表的value字段的长度 */
	private Integer length;
	/** 是否必填 */
	private Byte isRequired;
	/** 是否列表显示 */
	private Byte isListView;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getActQrcodeCode(){
		return this.actQrcodeCode;
	}
	
	public void setActQrcodeCode(String actQrcodeCode){
		this.actQrcodeCode = actQrcodeCode;
	}
	public Long getActCustomColumnId(){
		return this.actCustomColumnId;
	}
	
	public void setActCustomColumnId(Long actCustomColumnId){
		this.actCustomColumnId = actCustomColumnId;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public Integer getLength(){
		return this.length;
	}
	
	public void setLength(Integer length){
		this.length = length;
	}
	public Byte getIsRequired(){
		return this.isRequired;
	}
	
	public void setIsRequired(Byte isRequired){
		this.isRequired = isRequired;
	}
	public Byte getIsListView(){
		return this.isListView;
	}
	
	public void setIsListView(Byte isListView){
		this.isListView = isListView;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
