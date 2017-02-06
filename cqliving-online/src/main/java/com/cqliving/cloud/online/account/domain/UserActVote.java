package com.cqliving.cloud.online.account.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;

/**
 * 用户_活动投票表 Entity
 * Date: 2016-06-07 09:29:49
 * @author Code Generator
 */
@Entity
@Table(name = "user_act_vote")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActVote extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 用户参与活动集合表ID（user_act_list表主键） */
	private Long userActListId;
	/** 活动投票分类表ID（act_vote_classify表主键） */
	private Long actVoteClassifyId;
	/** 活动分类选项表ID（act_vote_item表主键） */
	private Long actVoteItemId;
	/** 用户ID */
	private Long userId;
	/** 创建时间 */
	private Date createTime;
	/** 投票活动ID */
	private Long voteId;
	
	/** 业务字段 */
	/** 客户端ID */
	private Long appId;
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
	public Long getActVoteClassifyId(){
		return this.actVoteClassifyId;
	}
	
	public void setActVoteClassifyId(Long actVoteClassifyId){
		this.actVoteClassifyId = actVoteClassifyId;
	}
	public Long getActVoteItemId(){
		return this.actVoteItemId;
	}
	
	public void setActVoteItemId(Long actVoteItemId){
		this.actVoteItemId = actVoteItemId;
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
	
	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	@Transient
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
