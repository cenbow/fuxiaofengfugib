package com.cqliving.cloud.online.survey.domain;


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
 * 调研问题表答案附加属性 Entity
 * Date: 2016-04-29 16:32:37
 * @author Code Generator
 */
@Entity
@Table(name = "SURVEY_QUESTION_ANSWER_ATTACH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyQuestionAnswerAttach extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 图片 */
	public static final Byte TYPE0 = 0;
	/** 定制 */
	public static final Byte TYPE1 = 1;
		
	/** 附加属性类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "图片");
		allTypes.put(TYPE1, "定制");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 调研ID */
	private Long surveyId;
	/** 问题ID */
	private Long questionId;
	/** 答案id */
	private Long answerId;
	/** 附加属性类型 */
	private Byte type;
	/** 排序号 */
	private Integer sortNo;
	/** 定制名称,如添加姓名,电话,联系方式 */
	private String customerName;
	/** 定制内容 */
	private String context;
	
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
	public Long getSurveyId(){
		return this.surveyId;
	}
	
	public void setSurveyId(Long surveyId){
		this.surveyId = surveyId;
	}
	public Long getQuestionId(){
		return this.questionId;
	}
	
	public void setQuestionId(Long questionId){
		this.questionId = questionId;
	}
	public Long getAnswerId(){
		return this.answerId;
	}
	
	public void setAnswerId(Long answerId){
		this.answerId = answerId;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public String getCustomerName(){
		return this.customerName;
	}
	
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
	public String getContext(){
		return this.context;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
