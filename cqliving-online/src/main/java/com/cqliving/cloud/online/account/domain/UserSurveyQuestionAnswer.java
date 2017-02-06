package com.cqliving.cloud.online.account.domain;


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
 * 用户调研问卷答案表 Entity
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
@Entity
@Table(name = "USER_SURVEY_QUESTION_ANSWER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserSurveyQuestionAnswer extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 参与历史ID */
	private Long historyId;
	/** 客户端_ID */
	private Long appId;
	/** 问题ID */
	private Long questionId;
	/** 答案ID */
	private Long answerId;
	/** 答案内容 */
	private String content;
	/** 答案附加内容 */
	private String answerAttach;
	/** 回答答案时间 */
	private Date createTime;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getHistoryId(){
		return this.historyId;
	}
	
	public void setHistoryId(Long historyId){
		this.historyId = historyId;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
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
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getAnswerAttach(){
		return this.answerAttach;
	}
	
	public void setAnswerAttach(String answerAttach){
		this.answerAttach = answerAttach;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
