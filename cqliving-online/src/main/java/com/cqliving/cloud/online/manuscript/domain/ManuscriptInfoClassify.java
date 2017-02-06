package com.cqliving.cloud.online.manuscript.domain;


import java.util.Date;
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
 * 抓稿新闻关系表 Entity
 * Date: 2016-11-08 16:06:27
 * @author Code Generator
 */
@Entity
@Table(name = "manuscript_info_classify")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ManuscriptInfoClassify extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	
	/** 否 */
	public static final Byte ISINSERT0 = 0;
	/** 是 */
	public static final Byte ISINSERT1 = 1;
	/** 是否已入库 */
	public static final Map<Byte, String> allIsInsert = Maps.newTreeMap();
	static {
		allIsInsert.put(ISINSERT0, "否");
		allIsInsert.put(ISINSERT1, "是");
	}
	
	/** ID */
	private Long id;
	/** app_info表ID */
	private Long appId;
	/** 资讯表ID，表info_classify的主键 */
	private Long infoClassifyId;
	/** 华龙网资讯唯一标识 */
	private String oldGuid;
	/** 描述 */
	private String description;
	/** 创建时间 */
	private Date createTime;
	/** 华龙网编辑 */
	private String editor;
	/** 抓稿栏目配置表ID，表manuscript_columns的主键 */
	private Long manuscriptColumnsId;
	/** 是否已入库{0:否,1:是} */
	private Byte  isInsert;
	
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
	public Long getInfoClassifyId(){
		return this.infoClassifyId;
	}
	
	public void setInfoClassifyId(Long infoClassifyId){
		this.infoClassifyId = infoClassifyId;
	}
	public String getOldGuid(){
		return this.oldGuid;
	}
	
	public void setOldGuid(String oldGuid){
		this.oldGuid = oldGuid;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Long getManuscriptColumnsId() {
		return manuscriptColumnsId;
	}

	public void setManuscriptColumnsId(Long manuscriptColumnsId) {
		this.manuscriptColumnsId = manuscriptColumnsId;
	}

	public Byte getIsInsert() {
		return isInsert;
	}

	public void setIsInsert(Byte isInsert) {
		this.isInsert = isInsert;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
