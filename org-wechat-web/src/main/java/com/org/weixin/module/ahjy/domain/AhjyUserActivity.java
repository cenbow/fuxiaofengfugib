package com.org.weixin.module.ahjy.domain;


import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.wechat.framework.domain.AbstractEntity;

import com.google.common.collect.Maps;

/**
 * 艾赫金源活动用户表 Entity
 *
 * Date: 2016-03-26 09:10:39
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "ahjy_user_activity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AhjyUserActivity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 不是 */
	public static final byte ISHOST0 = 0;
	/** 是 */
	public static final byte ISHOST1 = 1;
		
	/** 是否是楼主{0:不是,1:是} */
		public static final Map<Byte, String> allIsHosts = Maps.newTreeMap();
	static {
		allIsHosts.put(ISHOST0, "不是");
		allIsHosts.put(ISHOST1, "是");
	}
	/** 未中奖 */
	public static final byte ISAWARD0 = 0;
	/** 已中奖 */
	public static final byte ISAWARD1 = 1;
		
	/** 是否中奖{0:未中奖;1:已中奖} */
		public static final Map<Byte, String> allIsAwards = Maps.newTreeMap();

	static {
		allIsAwards.put(ISAWARD0, "未中奖");
		allIsAwards.put(ISAWARD1, "已中奖");
	}
	
	/** ID */
	private Long id;
	/** 活动ID */
	private Long activityId;
	/** 用户ID */
	private Long userId;
	/** 用户手机号 */
	private String userPhone;
	/** 用户头像 */
	private String userImg;
	/** 加入时间 */
	private Date joinTime;
	/** 是否是楼主{0:不是,1:是} */
	private byte isHost;
	/** 活动得分 */
	private Integer score;
	/** 是否中奖{0:未中奖;1:已中奖} */
	private byte isAward;
	
	@Id
	@GeneratedValue
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getActivityId(){
		return this.activityId;
	}
	
	public void setActivityId(Long activityId){
		this.activityId = activityId;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getUserPhone(){
		return this.userPhone;
	}
	
	public void setUserPhone(String userPhone){
		this.userPhone = userPhone;
	}
	public String getUserImg(){
		return this.userImg;
	}
	
	public void setUserImg(String userImg){
		this.userImg = userImg;
	}
	public Date getJoinTime(){
		return this.joinTime;
	}
	
	public void setJoinTime(Date joinTime){
		this.joinTime = joinTime;
	}
	public byte getIsHost(){
		return this.isHost;
	}
	
	public void setIsHost(byte isHost){
		this.isHost = isHost;
	}
	public Integer getScore(){
		return this.score;
	}
	
	public void setScore(Integer score){
		this.score = score;
	}
	public byte getIsAward(){
		return this.isAward;
	}
	
	public void setIsAward(byte isAward){
		this.isAward = isAward;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
