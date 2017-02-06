package com.cqliving.cloud.online.manuscript.domain;


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
 * 抓稿栏目配置表 Entity
 * Date: 2016-11-08 16:06:24
 * @author Code Generator
 */
@Entity
@Table(name = "manuscript_columns")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ManuscriptColumns extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 有效 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "有效");
		allStatuss.put(STATUS99, "删除");
	}
	/** 无图 */
	public static final Byte LISTVIEWTYPE0 = 0;
	/** 单图 */
	public static final Byte LISTVIEWTYPE1 = 1;
	/** 大图 */
	public static final Byte LISTVIEWTYPE2 = 2;
	
	/** 状态 */
	public static final Map<Byte, String> allListViewType = Maps.newTreeMap();
	static {
		allListViewType.put(LISTVIEWTYPE0, "无图");
		allListViewType.put(LISTVIEWTYPE1, "单图");
		allListViewType.put(LISTVIEWTYPE2, "大图");
	}
	
	/** ID */
	private Long id;
	/** app_info表ID */
	private Long appId;
	/** 栏目ID */
	private Long columnsId;
	/** 栏目名称，冗余字段 */
	private String columnsName;
	/** 华龙网责任部门 */
	private String department;
	/** RSS地址，一个栏目可以对应多个RSS地址 */
	private String rssUrl;
	/** 描述 */
	private String description;
	/** 状态 */
	private Byte status;
	/** 新闻内容类型{0:纯文本,1:多图,5:视频}，请参考information表的context_type字段，这里只会有三种 */
	private Byte contextType;
	/** 状态{-1:审核不通过,0:草稿,1:保存,3:正常上线,88:下线,99:删除}，请参考info_classify表的context_type字段 */
	private Byte infoStatus;
	/** 列表显示类型{0:无图,1:单图,2:大图}，当抓取数据过来有导读图，则取该字段判断为1或2，如果没有导读图，则该字段无效，默认取0 */
	private Byte listViewType;
	/** 栏目默认浏览量随机范围值，例如（3000-6000），如果没有配置则是0 */
	private String viewCountRange;
	/** 默认来源，如果这个字段为空就去抓取过来的source字段 */
	private String sourceConfig;
	/** 阅读量增加类型{0:一次添加,1:逐步添加}同information表的add_type字段一样 */
	private Byte addType;
	/** 达到峰值时间(秒)，add_type为1时，这个配置有效” */
	private String countAndTime;
	
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
	public Long getColumnsId(){
		return this.columnsId;
	}
	
	public void setColumnsId(Long columnsId){
		this.columnsId = columnsId;
	}
	public String getColumnsName(){
		return this.columnsName;
	}
	
	public void setColumnsName(String columnsName){
		this.columnsName = columnsName;
	}
	public String getDepartment(){
		return this.department;
	}
	
	public void setDepartment(String department){
		this.department = department;
	}
	public String getRssUrl(){
		return this.rssUrl;
	}
	
	public void setRssUrl(String rssUrl){
		this.rssUrl = rssUrl;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	
	public Byte getContextType() {
		return contextType;
	}

	public void setContextType(Byte contextType) {
		this.contextType = contextType;
	}
	
	public Byte getInfoStatus() {
		return infoStatus;
	}

	public void setInfoStatus(Byte infoStatus) {
		this.infoStatus = infoStatus;
	}
	
	public Byte getListViewType() {
		return listViewType;
	}

	public void setListViewType(Byte listViewType) {
		this.listViewType = listViewType;
	}

	public String getViewCountRange() {
		return viewCountRange;
	}

	public void setViewCountRange(String viewCountRange) {
		this.viewCountRange = viewCountRange;
	}

	public String getSourceConfig() {
		return sourceConfig;
	}

	public void setSourceConfig(String sourceConfig) {
		this.sourceConfig = sourceConfig;
	}

	public Byte getAddType() {
		return addType;
	}

	public void setAddType(Byte addType) {
		this.addType = addType;
	}

	public String getCountAndTime() {
		return countAndTime;
	}

	public void setCountAndTime(String countAndTime) {
		this.countAndTime = countAndTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
