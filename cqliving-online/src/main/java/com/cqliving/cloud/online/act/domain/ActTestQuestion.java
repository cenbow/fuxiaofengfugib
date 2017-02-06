package com.cqliving.cloud.online.act.domain;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 活动答题分类问题表 Entity
 * Date: 2016-06-07 09:22:55
 * @author Code Generator
 */
@Entity
@Table(name = "act_test_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActTestQuestion extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 单选2 */
	public static final Byte TYPE1 = 1;
	/** 多选 */
	public static final Byte TYPE2 = 2;
	/** 文本 */
	public static final Byte TYPE3 = 3;
		
	/** 问题类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE1, "单选");
		allTypes.put(TYPE2, "多选");
		allTypes.put(TYPE3, "文本");
	}
	
	/** ID */
	private Long id;
	/** 活动答题分类表ID（act_test_classify表主键） */
	private Long actTestClassifyId;
	/** 问题名称（问题描述） */
	private String question;
	/** 问题图片,多张用,分隔 */
	private String imageUrl;
	/** 问题类型 */
	private Byte type;
	/** 问题分值 */
	private Integer score;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	
	/** 问题的答案 **/
	private List<ActTestAnswer> answerList;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getActTestClassifyId(){
		return this.actTestClassifyId;
	}
	
	public void setActTestClassifyId(Long actTestClassifyId){
		this.actTestClassifyId = actTestClassifyId;
	}
	public String getQuestion(){
		return this.question;
	}
	
	public void setQuestion(String question){
		this.question = question;
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
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="actTestQuestionId")
	@OrderBy(value="sortNo asc")
	public List<ActTestAnswer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<ActTestAnswer> answerList) {
		this.answerList = answerList;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
