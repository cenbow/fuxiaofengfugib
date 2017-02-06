package com.cqliving.cloud.online.survey.domain;


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
 * 问题表答案表 Entity
 * Date: 2016-05-06 17:55:06
 * @author Code Generator
 */
@Entity
@Table(name = "survey_question_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyQuestionAnswer extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final Byte STATUS3 = 3;
	public static final Byte STATUS99 = 99;
	public static final Byte ISAFFIRMATIVE0 = 0;
	public static final Byte ISAFFIRMATIVE1 = 1;
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
	
	/** ID */
	private Long id;
	/** 问题ID */
	private Long questionId;
	/** 答案 */
	private String answer;
	/** 答案图片，多个用,分隔 */
	private String imageUrl;
	/** 答案类型 */
	private Byte type;
	/** 输入长度限制 */
	private Integer inputLimit;
	/** 排序号 */
	private Integer sortNo;
	/** 编号 */
	private String number;
	/** 初始量 */
	private Integer initValue;
	/** 实际量 */
	private Integer actValue;
	/** 创建时间 */
	private Date createTime;
	/** 是否正方,该字段只对打擂有用{0:否,1:是} */
	private Byte isAffirmative;
	/** 状态3:正常 ,99:删除 */
	private Byte status;
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
	public String getAnswer(){
		return this.answer;
	}
	
	public void setAnswer(String answer){
		this.answer = answer;
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
	public Integer getInputLimit(){
		return this.inputLimit;
	}
	
	public void setInputLimit(Integer inputLimit){
		this.inputLimit = inputLimit;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public String getNumber(){
		return this.number;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	public Integer getInitValue(){
		return this.initValue;
	}
	
	public void setInitValue(Integer initValue){
		this.initValue = initValue;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Byte getIsAffirmative() {
		return isAffirmative;
	}

	public void setIsAffirmative(Byte isAffirmative) {
		this.isAffirmative = isAffirmative;
	}

	public Integer getActValue() {
		return actValue;
	}

	public void setActValue(Integer actValue) {
		this.actValue = actValue;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
