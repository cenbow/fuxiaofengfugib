package com.org.weixin.module.dalingmusicale.domain;


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

import org.wechat.framework.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 用户分享历史表 Entity
 * Date: 2016-09-16 09:09:57
 * @author Code Generator
 */
@Entity
@Table(name = "user_share_his")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserShareHis extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 人气王投票分享 */
	public static final Byte SHARETYPE1 = 1;
	/** 抢票分享 */
	public static final Byte SHARETYPE2 = 2;
	/** 明星决赛分享 */
	public static final Byte SHARETYPE3 = 3;
	/** 分享类型 */
	public static final Map<Byte, String> allShareTypes = Maps.newTreeMap();
	static {
		allShareTypes.put(SHARETYPE1, "投票分享");
		allShareTypes.put(SHARETYPE2, "抢票分享");
		allShareTypes.put(SHARETYPE3, "决赛分享");
	}
	
	/** id */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 分享时间 */
	private Date shareTime;
	/** 分享类型 */
	private Byte shareType;
	
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
	public Date getShareTime(){
		return this.shareTime;
	}
	
	public void setShareTime(Date shareTime){
		this.shareTime = shareTime;
	}
	public Byte getShareType(){
		return this.shareType;
	}
	
	public void setShareType(Byte shareType){
		this.shareType = shareType;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
