package com.cqliving.cloud.online.message.domain;


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
 * 消息通知接收表 Entity
 * Date: 2016-04-29 16:31:06
 * @author Code Generator
 */
@Entity
@Table(name = "MESSAGE_RECEIVE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageReceive extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 站内信 */
	public static final Byte SENDTYPE0 = 0;
	/** 公告 */
    public static final Byte SENDTYPE1 = 1;
	/** APP通知 */
	public static final Byte SENDTYPE2 = 2;
	/** 短信通知 */
	public static final Byte SENDTYPE3 = 3;
		
	/** 发送类型 */
	public static final Map<Byte, String> allSendTypes = Maps.newTreeMap();
	static {
		allSendTypes.put(SENDTYPE0, "站内信");
		allSendTypes.put(SENDTYPE1, "公告");
		allSendTypes.put(SENDTYPE2, "APP通知");
		allSendTypes.put(SENDTYPE3, "短信通知");
	}
	
	/** 未读 */
    public static final Byte STATUS0 = 0;
    /** 已生效 */
    public static final Byte STATUS1 = 1;
    /** 删除 */
    public static final Byte STATUS99 = 99;
        
    /** 状态 */
    public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
    static {
        allStatuss.put(STATUS0, "未读");
        allStatuss.put(STATUS1, "已读");
//        allStatuss.put(STATUS99, "删除");
    }
	
	/** id */
	private Long id;
	/** 标识接受的哪一个消息 */
	private Long messageId;
	/** APP_ID */
	private Long appId;
	/** 接收人ID */
	private Long receiverId;
	/** 发送类型 */
	private Byte sendType;
	/** 状态 */
	private Byte status;
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
	
	public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Long getReceiverId(){
		return this.receiverId;
	}
	
	public void setReceiverId(Long receiverId){
		this.receiverId = receiverId;
	}
	public Byte getSendType(){
		return this.sendType;
	}
	
	public void setSendType(Byte sendType){
		this.sendType = sendType;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
