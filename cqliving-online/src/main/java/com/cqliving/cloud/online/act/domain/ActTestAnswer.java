package com.cqliving.cloud.online.act.domain;


import java.util.Date;
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
 * 活动答题分类答案表 Entity
 * Date: 2016-06-07 09:22:20
 * @author Code Generator
 */
@Entity
@Table(name = "act_test_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActTestAnswer extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 正常 */
	public static final Byte TYPE0 = 0;
	/** 有其他输入 */
	public static final Byte TYPE1 = 1;
		
	/** 答案类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "正常");
		allTypes.put(TYPE1, "有其他输入");
	}
	/** 否 */
	public static final Byte ISRIGHTANSWER0 = 0;
	/** 是 */
	public static final Byte ISRIGHTANSWER1 = 1;
		
	/** 是否正确答案 */
	public static final Map<Byte, String> allIsRightAnswers = Maps.newTreeMap();
	static {
		allIsRightAnswers.put(ISRIGHTANSWER0, "否");
		allIsRightAnswers.put(ISRIGHTANSWER1, "是");
	}
	
	/** ID */
	private Long id;
	/** 活动答题问题表ID（act_test_question表主键） */
	private Long actTestQuestionId;
	/** 答案 */
	private String answer;
	/** 答案描述 */
	private String answerDesc;
	/** 答案图片，多个用,分隔 */
	private String imageUrl;
	/** 答案类型 */
	private Byte type;
	/** 是否正确答案 */
	private Byte isRightAnswer;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getActTestQuestionId(){
		return this.actTestQuestionId;
	}
	
	public void setActTestQuestionId(Long actTestQuestionId){
		this.actTestQuestionId = actTestQuestionId;
	}
	public String getAnswer(){
		return this.answer;
	}
	
	public void setAnswer(String answer){
		this.answer = answer;
	}
	public String getAnswerDesc(){
		return this.answerDesc;
	}
	
	public void setAnswerDesc(String answerDesc){
		this.answerDesc = answerDesc;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public Byte getIsRightAnswer(){
		return this.isRightAnswer;
	}
	
	public void setIsRightAnswer(Byte isRightAnswer){
		this.isRightAnswer = isRightAnswer;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
