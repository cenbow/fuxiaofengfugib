package com.cqliving.cloud.online.analysis.domain;


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

import com.cqliving.framework.common.domain.AbstractEntity;

/**
 * 资讯基本浏览情况统计表 Entity
 * Date: 2016-06-25 15:51:14
 * @author Code Generator
 */
@Entity
@Table(name = "analysis_information")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnalysisInformation extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 日期 */
	private Date statisticsTime;
	/** sys_user表ID */
	private Long userId;
	/** app_info表ID */
	private Long appId;
	/** 新闻数量 */
	private Integer infoCount;
	/** 浏览总量 */
	private Integer viewTotalCount;
	/** 浏览人数 */
	private Integer viewUserCount;
	/** 评论总数量 */
	private Integer replyCount;
	/** 评论总人数 */
	private Integer replyUserCount;
	/** 参与活动数量 */
	private Integer activeCount;
	/** 参与活动人数 */
	private Integer activeUserCount;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Date getStatisticsTime(){
		return this.statisticsTime;
	}
	
	public void setStatisticsTime(Date statisticsTime){
		this.statisticsTime = statisticsTime;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Integer getInfoCount(){
		return this.infoCount;
	}
	
	public void setInfoCount(Integer infoCount){
		this.infoCount = infoCount;
	}
	public Integer getViewTotalCount(){
		return this.viewTotalCount;
	}
	
	public void setViewTotalCount(Integer viewTotalCount){
		this.viewTotalCount = viewTotalCount;
	}
	public Integer getViewUserCount(){
		return this.viewUserCount;
	}
	
	public void setViewUserCount(Integer viewUserCount){
		this.viewUserCount = viewUserCount;
	}
	public Integer getReplyCount(){
		return this.replyCount;
	}
	
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public Integer getReplyUserCount(){
		return this.replyUserCount;
	}
	
	public void setReplyUserCount(Integer replyUserCount){
		this.replyUserCount = replyUserCount;
	}
	public Integer getActiveCount(){
		return this.activeCount;
	}
	
	public void setActiveCount(Integer activeCount){
		this.activeCount = activeCount;
	}
	public Integer getActiveUserCount(){
		return this.activeUserCount;
	}
	
	public void setActiveUserCount(Integer activeUserCount){
		this.activeUserCount = activeUserCount;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
