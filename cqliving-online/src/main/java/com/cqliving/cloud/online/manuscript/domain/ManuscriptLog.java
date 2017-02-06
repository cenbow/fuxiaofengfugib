package com.cqliving.cloud.online.manuscript.domain;


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
 * 抓稿日志表 Entity
 * Date: 2016-11-08 16:06:30
 * @author Code Generator
 */
@Entity
@Table(name = "manuscript_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ManuscriptLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 抓稿栏目配置表ID，表manuscript_columns的主键 */
	private Long manuscriptColumnsId;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 用时毫秒数 */
	private Integer operateMillisecond;
	/** 入库数据多少条 */
	private Integer insertNum;
	/** XML中一共多少条数据 */
	private Integer countNum;
	/** 加载XML用时毫秒数 */
	private Long loadXmlMillisecond;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getManuscriptColumnsId(){
		return this.manuscriptColumnsId;
	}
	
	public void setManuscriptColumnsId(Long manuscriptColumnsId){
		this.manuscriptColumnsId = manuscriptColumnsId;
	}
	public Date getStartTime(){
		return this.startTime;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	public Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	public Integer getOperateMillisecond(){
		return this.operateMillisecond;
	}
	
	public void setOperateMillisecond(Integer operateMillisecond){
		this.operateMillisecond = operateMillisecond;
	}
	public Integer getInsertNum(){
		return this.insertNum;
	}
	
	public void setInsertNum(Integer insertNum){
		this.insertNum = insertNum;
	}
	
	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public Long getLoadXmlMillisecond() {
		return loadXmlMillisecond;
	}

	public void setLoadXmlMillisecond(Long loadXmlMillisecond) {
		this.loadXmlMillisecond = loadXmlMillisecond;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
