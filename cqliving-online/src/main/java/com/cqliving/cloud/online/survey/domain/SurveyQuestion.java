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
 * 调研问题表 Entity
 * Date: 2016-05-06 17:55:02
 * @author Code Generator
 */
@Entity
@Table(name = "survey_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyQuestion extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final Byte STATUS3 = 3;
	public static final Byte STATUS99 = 99;
	
	/** 单选 */
	public static final Byte TYPE0 = 0;
	/** 判断 */
	public static final Byte TYPE2 = 2;
	/** 多选 */
	public static final Byte TYPE3 = 3;
	/** 填空 */
	public static final Byte TYPE4 = 4;
	/** 文本 */
	public static final Byte TYPE5 = 5;
	/** 图片 */
	public static final Byte TYPE6 = 6;
	
	/** 问题类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "单选");
		allTypes.put(TYPE2, "判断");
		allTypes.put(TYPE3, "多选");
		allTypes.put(TYPE4, "填空");
		allTypes.put(TYPE5, "文本");
		allTypes.put(TYPE6,"图片");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 调研ID */
	private Long surveyId;
	/** 问题 */
	private String question;
	/** 问题图片,多张用,分隔 */
	private String images;
	/** 问题类型 */
	private Byte type;
	/** 数量控制,填空，文本 表示输入长度，多选,则控制选择数量, -1 表示不控制 */
	private Integer limitCount;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
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
	public String getQuestion(){
		return this.question;
	}
	
	public void setQuestion(String question){
		this.question = question;
	}
	public String getImages(){
		return this.images;
	}
	
	public void setImages(String images){
		this.images = images;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public Integer getLimitCount(){
		return this.limitCount;
	}
	
	public void setLimitCount(Integer limitCount){
		this.limitCount = limitCount;
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
