/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.log.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.cqliving.framework.common.domain.AbstractEntity;


/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author tangqiang on 2015年1月9日
 */
@MappedSuperclass
public abstract class BaseLog extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4063510764829281L;

	/** ID */
	protected Long id;
	
	/** 模块 */
	protected String module;
	
	/** 模块名称 */
	protected String moduleName;
	
	/** 操作 */
	protected String action;
	
	/** 操作名称 */
	protected String actionName;
	
	/** 执行时间 */
	protected Long executeMilliseconds;
	
	/** 操作时间 */
	protected Date operateTime;
	
	/** 操作会员名称 */
	protected String operateUser;
	
	/** 用户ID */
	protected Long operateUserId;
	
	/** 请求参数 */
	protected String requestParameters;
	
	/** 操作结果 */
	protected Integer operateResult;
	
	/** 消息 */
	protected String operateMessage;
	
	/** 客户端信息 */
	protected String clientInformations;
	
	/** 备注 */
	protected String des;
	
	/** 平台标识，在线教育平台暂使用OL_100 */
	protected String tag;
	
	/** 会话ID */
	protected String sessionId;
	
	protected String refer;
	
	/** 资源分类ID */
	protected Long sysResTypeId;
	
	/** AppId */
    private Long appId;
    
    /**cms显示状态*/
    private Byte type;
	
	
	public String getModule(){
		return this.module;
	}
	
	public void setModule(String module){
		this.module = module;
	}
	public String getModuleName(){
		return this.moduleName;
	}
	
	public void setModuleName(String moduleName){
		this.moduleName = moduleName;
	}
	public String getAction(){
		return this.action;
	}
	
	public void setAction(String action){
		this.action = action;
	}
	public String getActionName(){
		return this.actionName;
	}
	
	public void setActionName(String actionName){
		this.actionName = actionName;
	}
	public Long getExecuteMilliseconds(){
		return this.executeMilliseconds;
	}
	
	public void setExecuteMilliseconds(Long executeMilliseconds){
		this.executeMilliseconds = executeMilliseconds;
	}
	public Date getOperateTime(){
		return this.operateTime;
	}
	
	public void setOperateTime(Date operateTime){
		this.operateTime = operateTime;
	}
	public String getOperateUser(){
		return this.operateUser;
	}
	
	public void setOperateUser(String operateUser){
		this.operateUser = operateUser;
	}
	public Long getOperateUserId(){
		return this.operateUserId;
	}
	
	public void setOperateUserId(Long operateUserId){
		this.operateUserId = operateUserId;
	}
	public String getRequestParameters(){
		return this.requestParameters;
	}
	
	public void setRequestParameters(String requestParameters){
		this.requestParameters = requestParameters;
	}
	public Integer getOperateResult(){
		return this.operateResult;
	}
	
	public void setOperateResult(Integer operateResult){
		this.operateResult = operateResult;
	}
	public String getOperateMessage(){
		return this.operateMessage;
	}
	
	public void setOperateMessage(String operateMessage){
		this.operateMessage = operateMessage;
	}
	public String getClientInformations(){
		return this.clientInformations;
	}
	
	public void setClientInformations(String clientInformations){
		this.clientInformations = clientInformations;
	}
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getTag(){
		return this.tag;
	}
	
	public void setTag(String tag){
		this.tag = tag;
	}
	public String getSessionId(){
		return this.sessionId;
	}
	
	public void setSessionId(String sessionId){
		this.sessionId = sessionId;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	@Id	
	@GeneratedValue
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

    public Long getSysResTypeId() {
        return sysResTypeId;
    }

    public void setSysResTypeId(Long sysResTypeId) {
        this.sysResTypeId = sysResTypeId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
