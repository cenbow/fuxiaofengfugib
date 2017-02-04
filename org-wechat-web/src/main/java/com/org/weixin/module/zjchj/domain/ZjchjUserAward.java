package com.org.weixin.module.zjchj.domain;


import java.util.Map;
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
import com.google.common.collect.Maps;

/**
 * 用户中奖表 Entity
 * Date: 2016-09-26 15:18:47
 * @author Code Generator
 */
@Entity
@Table(name = "zjchj_user_award")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZjchjUserAward extends AbstractEntity {

	private static final long serialVersionUID = 8894753248578928248L;
	
	/** 否 */
	public static final Byte ISREWARD0 = 0;
	/** 是 */
	public static final Byte ISREWARD1 = 1;
		
	/** 是否核销 */
	public static final Map<Byte, String> allIsRewards = Maps.newTreeMap();
	static {
		allIsRewards.put(ISREWARD0, "未核销");
		allIsRewards.put(ISREWARD1, "已核销");
	}
	
	/** 否 */
	public static final Byte ISTRUEAWARD0 = 0;
	/** 是 */
	public static final Byte ISTRUEAWARD1 = 1;
		
	/** 是否真实奖品 */
	public static final Map<Byte, String> allIsTrueAwards = Maps.newTreeMap();
	static {
		allIsTrueAwards.put(ISTRUEAWARD0, "否");
		allIsTrueAwards.put(ISTRUEAWARD1, "是");
	}
	
	/** ID */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 奖品名称 */
	private String awardName;
	/** 奖品ID */
	private Long awardId;
	/** 创建时间 */
	private Date createTime;
	/** 核销时间 */
	private Date rewardTime;
	/** 是否核销 */
	private Byte isReward;
	/** 奖品等级 */
	private Byte level;
	/** 核销码 */
	private String rewardPwd;
	/** 核销人ID */
	private Long backUserId;
	/** 核销人名称 */
	private String backUserName;
	/** 是否真实奖品 */
	private Byte isTrueAward;
	
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
	public String getAwardName(){
		return this.awardName;
	}
	
	public void setAwardName(String awardName){
		this.awardName = awardName;
	}
	public Long getAwardId(){
		return this.awardId;
	}
	
	public void setAwardId(Long awardId){
		this.awardId = awardId;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getRewardTime(){
		return this.rewardTime;
	}
	
	public void setRewardTime(Date rewardTime){
		this.rewardTime = rewardTime;
	}
	public Byte getIsReward(){
		return this.isReward;
	}
	
	public void setIsReward(Byte isReward){
		this.isReward = isReward;
	}
	
	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public String getRewardPwd() {
		return rewardPwd;
	}

	public void setRewardPwd(String rewardPwd) {
		this.rewardPwd = rewardPwd;
	}

	public Long getBackUserId() {
		return backUserId;
	}

	public void setBackUserId(Long backUserId) {
		this.backUserId = backUserId;
	}

	public String getBackUserName() {
		return backUserName;
	}

	public void setBackUserName(String backUserName) {
		this.backUserName = backUserName;
	}

	public Byte getIsTrueAward() {
		return isTrueAward;
	}

	public void setIsTrueAward(Byte isTrueAward) {
		this.isTrueAward = isTrueAward;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
