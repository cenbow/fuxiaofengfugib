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
 * 用户答题表 Entity
 * Date: 2016-06-07 09:29:42
 * @author Code Generator
 */
@Entity
@Table(name = "user_act_test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActTest extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 用户分类答题历史表（user_act_test_classify_historyt表主键） */
	private Long testClassifyHistoryId;
	/** 活动答题问题表ID（act_test_question表主键） */
	private Long actTestQuestionId;
	/** 活动答题答案ID（act_test_answer表主键） */
	private Long actTestAnswerId;
	/** 用户ID */
	private Long userId;
	/** 参与时间 */
	private Date createTime;
	/** 答案内容 */
	private String answerContent;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getTestClassifyHistoryId() {
		return testClassifyHistoryId;
	}

	public void setTestClassifyHistoryId(Long testClassifyHistoryId) {
		this.testClassifyHistoryId = testClassifyHistoryId;
	}

	public Long getActTestQuestionId(){
		return this.actTestQuestionId;
	}
	
	public void setActTestQuestionId(Long actTestQuestionId){
		this.actTestQuestionId = actTestQuestionId;
	}
	public Long getActTestAnswerId(){
		return this.actTestAnswerId;
	}
	
	public void setActTestAnswerId(Long actTestAnswerId){
		this.actTestAnswerId = actTestAnswerId;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public String getAnswerContent(){
		return this.answerContent;
	}
	
	public void setAnswerContent(String answerContent){
		this.answerContent = answerContent;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
