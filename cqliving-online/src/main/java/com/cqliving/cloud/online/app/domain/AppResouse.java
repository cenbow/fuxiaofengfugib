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
 * 资讯资源表【文字,图文,视频,音频】 Entity
 * Date: 2016-04-29 16:18:30
 * @author Code Generator
 */
@Entity
@Table(name = "APP_RESOUSE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppResouse extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 普通图文 */
	public static final Byte TYPE0 = 0;
	/** 视频 */
	public static final Byte TYPE1 = 1;
	/** 音频 */
	public static final Byte TYPE2 = 2;
	/** 多图 */
	public static final Byte TYPE6 = 6;

	public static final Byte STATUS3 = 3;
	public static final Byte STATUS99 = 99;
	/** 来源类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "普通图文");
		allTypes.put(TYPE1, "视频");
		allTypes.put(TYPE2, "音频");
		allTypes.put(TYPE6, "多图");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 资讯ID */
	private Long informationId;
	/** 内容ID */
	private Long informationContentId;
	/** 来源类型 */
	private Byte type;
	/** 名称 */
	private String name;
	/** 文件路径 */
	private String fileUrl;
	/** 说明 */
	private String description;
	/** 排序号 */
	private Integer sortNo;
	/** 文件资源ID */
	private Long infoFileId;
	/** 状态{3:正常,99:删除} */
	private Byte status;
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
	public Long getInformationId(){
		return this.informationId;
	}
	
	public void setInformationId(Long informationId){
		this.informationId = informationId;
	}
	public Long getInformationContentId(){
		return this.informationContentId;
	}
	
	public void setInformationContentId(Long informationContentId){
		this.informationContentId = informationContentId;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getFileUrl(){
		return this.fileUrl;
	}
	
	public void setFileUrl(String fileUrl){
		this.fileUrl = fileUrl;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	
	public Long getInfoFileId() {
		return infoFileId;
	}

	public void setInfoFileId(Long infoFileId) {
		this.infoFileId = infoFileId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
