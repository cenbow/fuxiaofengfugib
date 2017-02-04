package com.org.weixin.module.dalingmusicale.domain;


import java.util.Map;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.wechat.framework.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 用户浏览历史表 Entity
 * Date: 2016-09-16 09:10:05
 * @author Code Generator
 */
@Entity
@Table(name = "user_view_his")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserViewHis extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 投票 */
	public static final Byte VIEWTYPE1 = 1;
	/** 音乐会 */
	public static final Byte VIEWTYPE2 = 2;
	/** 决赛 */
	public static final Byte VIEWTYPE3 = 3;
	/** 浏览类型 */
	public static final Map<Byte, String> allViewTypes = Maps.newTreeMap();
	static {
		allViewTypes.put(VIEWTYPE1, "投票");
		allViewTypes.put(VIEWTYPE2, "音乐会");
		allViewTypes.put(VIEWTYPE3, "决赛");
	}
	
	/** 主键 */
	private Long id;
	/** 浏览用户ID */
	private Long userId;
	/** 浏览源地址 */
	private String sourceUrl;
	/** 浏览源名称 */
	private String sourceName;
	/** 浏览类型 */
	private Byte viewType;
	/** 浏览时间 */
	private Date viewTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getSourceUrl(){
		return this.sourceUrl;
	}
	
	public void setSourceUrl(String sourceUrl){
		this.sourceUrl = sourceUrl;
	}
	public String getSourceName(){
		return this.sourceName;
	}
	
	public void setSourceName(String sourceName){
		this.sourceName = sourceName;
	}
	public Byte getViewType(){
		return this.viewType;
	}
	
	public void setViewType(Byte viewType){
		this.viewType = viewType;
	}
	public Date getViewTime(){
		return this.viewTime;
	}
	
	public void setViewTime(Date viewTime){
		this.viewTime = viewTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
