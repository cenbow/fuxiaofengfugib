package com.org.weixin.module.dalingmusicale.domain;


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
import org.wechat.framework.domain.AbstractEntity;

/**
 * 用户投票信息 Entity
 * Date: 2016-09-16 09:10:09
 * @author Code Generator
 */
@Entity
@Table(name = "user_vote")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserVote extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键 */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 投票时间 */
	private Date voteTime;
	/** 选手id */
	private String contestantCode;
	/** 投票阶段 */
	private Byte voteStep;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public Date getVoteTime(){
		return this.voteTime;
	}
	
	public void setVoteTime(Date voteTime){
		this.voteTime = voteTime;
	}
	public String getContestantCode(){
		return this.contestantCode;
	}
	
	public void setContestantCode(String contestantCode){
		this.contestantCode = contestantCode;
	}
	public Byte getVoteStep() {
		return voteStep;
	}

	public void setVoteStep(Byte voteStep) {
		this.voteStep = voteStep;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
