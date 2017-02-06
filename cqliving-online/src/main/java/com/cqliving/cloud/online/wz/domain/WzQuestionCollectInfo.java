package com.cqliving.cloud.online.wz.domain;


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
 * 问题信息收集表 Entity
 * Date: 2016-05-10 09:47:41
 * @author Code Generator
 */
@Entity
@Table(name = "wz_question_collect_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WzQuestionCollectInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 问题ID */
	private Long questionId;
	/** 信息收集表ID,表wz_collect_info主键 */
	private Long collectInfoId;
	/** 信息收集表参数值 */
	private String value;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getQuestionId(){
		return this.questionId;
	}
	
	public void setQuestionId(Long questionId){
		this.questionId = questionId;
	}
	public Long getCollectInfoId(){
		return this.collectInfoId;
	}
	
	public void setCollectInfoId(Long collectInfoId){
		this.collectInfoId = collectInfoId;
	}
	public String getValue(){
		return this.value;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
