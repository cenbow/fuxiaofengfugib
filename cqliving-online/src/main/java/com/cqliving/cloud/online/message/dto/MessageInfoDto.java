package com.cqliving.cloud.online.message.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年5月9日
 */
public class MessageInfoDto {
	
	/** id */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 标题 */
	private String title;
	/** 发送时间 */
	private Date sendTime;
	/** 内容 */
	private String context;
	/** 接收人类型 */
	private String receiverType;
	/** 发送类型 */
	private Byte sendType;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 接收客户端名称，多个用,分隔 */
	private String receiverAppId;
	
	/** 读取状态 */
	private Byte readStatus;
	/** 接收人ID */
    private Long receiverId;
    /** 接收表ID */
    private Long rId;
	
	//------------------------ 冗余字段 --------------------------
	
	/** app 名称 */
	private String appName;
	
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
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public Date getSendTime(){
		return this.sendTime;
	}
	
	public void setSendTime(Date sendTime){
		this.sendTime = sendTime;
	}
	public String getContext(){
		return this.context;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	public String getReceiverType(){
		return this.receiverType;
	}
	
	public void setReceiverType(String receiverType){
		this.receiverType = receiverType;
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
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Long getUpdatorId(){
		return this.updatorId;
	}
	
	public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	public String getReceiverAppId(){
		return this.receiverAppId;
	}
	
	public void setReceiverAppId(String receiverAppId){
		this.receiverAppId = receiverAppId;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Byte getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Byte readStatus) {
        this.readStatus = readStatus;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
