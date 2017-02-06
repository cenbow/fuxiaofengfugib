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
 * 活动答题分类表 Entity
 * Date: 2016-06-07 09:22:31
 * @author Code Generator
 */
@Entity
@Table(name = "act_test_classify")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActTestClassify extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 不设分值 **/
	public static Byte ISSETSCORE0 = 0;
	/** 设置分值 **/
	public static Byte ISSETSCORE1 = 1;
	/** 题目是否设置分值 */
	public static final Map<Byte, String> allIsSetScore = Maps.newTreeMap();
	static {
		allIsSetScore.put(ISSETSCORE0, "不设分值");
		allIsSetScore.put(ISSETSCORE1, "设置分值");
	}
	
	/** ID */
	private Long id;
	/** 活动集合表ID（act_info_list表主键） */
	private Long actInfoListId;
	/** 活动答题表ID（act_test表主键） */
	private Long actTestId;
	/** 分类标题，不超过10个字 */
	private String title;
	/** 分类主题，不超过50个字 */
	private String subject;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	/** 题目是否设置分值{0:不设分值,1:设置分值} */
	private Byte isSetScore;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getActInfoListId(){
		return this.actInfoListId;
	}
	
	public void setActInfoListId(Long actInfoListId){
		this.actInfoListId = actInfoListId;
	}
	public Long getActTestId(){
		return this.actTestId;
	}
	
	public void setActTestId(Long actTestId){
		this.actTestId = actTestId;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getSubject(){
		return this.subject;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
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
	
	public Byte getIsSetScore() {
		return isSetScore;
	}

	public void setIsSetScore(Byte isSetScore) {
		this.isSetScore = isSetScore;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
