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
 * 用户活动信息收集表 Entity
 * Date: 2016-06-07 09:29:11
 * @author Code Generator
 */
@Entity
@Table(name = "user_act_collec_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActCollecInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 用户参与活动集合表ID（user_act_list表主键） */
	private Long userActListId;
	/** 活动集合表ID，（act_info_list表主键） */
	private Long actInfoListId;
	/** 活动信息收集表ID，（act_collect_info表主键） */
	private Long actCollectInfoId;
	/** 活动信息收集选项表ID，即问题答案（act_collect_info_option表主键）。当收集问题类型为（2:单选,3:多选,4:下拉列表）时，该值有效。 */
	private Long actCollectOptionId;
	/** 用户ID */
	private Long userId;
	/** 活动信息收集内容，当收集问题类型为（1:填空）时，该值有效。 */
	private String value;
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
	public Long getUserActListId(){
		return this.userActListId;
	}
	
	public void setUserActListId(Long userActListId){
		this.userActListId = userActListId;
	}
	public Long getActInfoListId(){
		return this.actInfoListId;
	}
	
	public void setActInfoListId(Long actInfoListId){
		this.actInfoListId = actInfoListId;
	}
	public Long getActCollectInfoId(){
		return this.actCollectInfoId;
	}
	
	public void setActCollectInfoId(Long actCollectInfoId){
		this.actCollectInfoId = actCollectInfoId;
	}
	public Long getActCollectOptionId(){
		return this.actCollectOptionId;
	}
	
	public void setActCollectOptionId(Long actCollectOptionId){
		this.actCollectOptionId = actCollectOptionId;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getValue(){
		return this.value;
	}
	
	public void setValue(String value){
		this.value = value;
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
