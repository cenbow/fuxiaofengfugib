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
 * 资讯栏目列表图片表 Entity
 * Date: 2016-04-29 16:22:23
 * @author Code Generator
 */
@Entity
@Table(name = "INFO_CLASSIFY_LIST")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoClassifyList extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 无图 */
	public static final Byte LISTVIEWTYPE0 = 0;
	/** 单图 */
	public static final Byte LISTVIEWTYPE1 = 1;
	/** 大图 */
	public static final Byte LISTVIEWTYPE2 = 2;
	/** 多图 */
	public static final Byte LISTVIEWTYPE3 = 3;
	/** 轮播 */
	public static final Byte LISTVIEWTYPE4 = 4;
	/** 窄图 */
	public static final Byte LISTVIEWTYPE5 = 5;
	/** 窄图带标题 */
	public static final Byte LISTVIEWTYPE6 = 6;
	/** 列表显示类型 */
	public static final Map<Byte, String> allListViewTypes = Maps.newTreeMap();
	static {
		allListViewTypes.put(LISTVIEWTYPE0, "无图");
		allListViewTypes.put(LISTVIEWTYPE1, "单图");
		allListViewTypes.put(LISTVIEWTYPE2, "大图");
		allListViewTypes.put(LISTVIEWTYPE3, "多图");
		allListViewTypes.put(LISTVIEWTYPE4, "轮播");
		allListViewTypes.put(LISTVIEWTYPE5, "窄图");
		allListViewTypes.put(LISTVIEWTYPE6, "窄图带标题");
	}
	
	/** ID */
	private Long id;
	/** 资讯栏目ID */
	private Long classifyId;
	/** 客户端_ID */
	private Long appId;
	/** 资讯ID */
	private Long informationId;
	/** 栏目ID */
	private Long columnsId;
	/** 列表显示类型 */
	private Byte listViewType;
	/** 图片 */
	private String imageUrl;
	/** 排序号 */
	private Integer sortNo;
	/** 图片标题 */
	private String imgTitle;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getClassifyId(){
		return this.classifyId;
	}
	
	public void setClassifyId(Long classifyId){
		this.classifyId = classifyId;
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
	public Long getColumnsId(){
		return this.columnsId;
	}
	
	public void setColumnsId(Long columnsId){
		this.columnsId = columnsId;
	}
	public Byte getListViewType(){
		return this.listViewType;
	}
	
	public void setListViewType(Byte listViewType){
		this.listViewType = listViewType;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	
	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
