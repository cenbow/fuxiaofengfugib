package com.cqliving.cloud.online.message.domain;


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
 * 消息未读数量表 Entity
 * Date: 2016-06-16 13:37:29
 * @author Code Generator
 */
@Entity
@Table(name = "message_unread_count")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageUnreadCount extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 反馈 */
	public static final Byte TYPE1 = 1;
	/** 评论 */
	public static final Byte TYPE2 = 2;
	/** 问政 */
	public static final Byte TYPE3 = 3;
	/** 点赞 */
	public static final Byte TYPE4 = 4;
		
	/** 消息类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE1, "反馈");
		allTypes.put(TYPE2, "评论");
		allTypes.put(TYPE3, "问政");
		allTypes.put(TYPE4, "点赞");
	}
	
	/** 主键 */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 用户ID */
	private Long userId;
	/** 消息类型 */
	private Byte type;
	/** 未读消息数 */
	private Integer number;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	
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
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
