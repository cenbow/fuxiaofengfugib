package com.cqliving.cloud.online.info.domain;


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
 * 资讯调研关联表 Entity
 * Date: 2016-04-29 16:22:24
 * @author Code Generator
 */
@Entity
@Table(name = "INFO_SURVEY_RELATION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoSurveyRelation extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 资讯内容表ID */
	private Long informationContentId;
	/** 调研表ID */
	private Long surveyId;
	
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
	public Long getInformationContentId(){
		return this.informationContentId;
	}
	
	public void setInformationContentId(Long informationContentId){
		this.informationContentId = informationContentId;
	}
	public Long getSurveyId(){
		return this.surveyId;
	}
	
	public void setSurveyId(Long surveyId){
		this.surveyId = surveyId;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
