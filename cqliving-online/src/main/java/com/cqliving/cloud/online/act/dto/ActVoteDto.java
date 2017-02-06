/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.act.dto;

import java.util.Date;
import java.util.List;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月24日
 */
public class ActVoteDto {
	
	/** -----------主表------------*/
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 活动ID（act_info表主键） */
	private Long actInfoId;
	/** 活动集合表ID（act_info_list表主键） */
	private Long actInfoListId;
	/** 活动类型 */
	private Byte type;
	/** 活动开始时间 */
	private Date startTime;
	/** 活动结束时间 */
	private Date endTime;
	/** 类型 */
	private Byte limitRateType;
	/** 数量 */
	private Integer limitRateTimes;
	/** 类型 */
	private Byte limitSingleType;
	/** 数量 */
	private Integer limitSingleTimes;
	/** 类型 */
	private Byte limitRuleType;
	/** 当limit_rule_type为1和0时，值默认为0但无效，当为限制值时有效 */
	private Integer limitRuleTimes;
	/** 是否登陆后才能操作 */
	private Byte loggedStatus;
	/** 是否分享分享后增加投票次数 */
	private Byte isShare;
	/** 分享后增加投票次数，当is_share=0时该值无效 */
	private Integer shareAddTimes;
	/** 类型 */
	private Byte limitShareType;
	/** 当limit_share_type为1和0时，值默认为0但无效，当为限制值时有效 */
	private Integer limitShareTimes;
	/** 模板CODE（act_template表里面的code） */
	private String actTemplateCode;
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
	
	private List<ActVoteClassifyDto> actVoteClassifyDtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getActInfoId() {
		return actInfoId;
	}

	public void setActInfoId(Long actInfoId) {
		this.actInfoId = actInfoId;
	}

	public Long getActInfoListId() {
		return actInfoListId;
	}

	public void setActInfoListId(Long actInfoListId) {
		this.actInfoListId = actInfoListId;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Byte getLimitRateType() {
		return limitRateType;
	}

	public void setLimitRateType(Byte limitRateType) {
		this.limitRateType = limitRateType;
	}

	public Integer getLimitRateTimes() {
		return limitRateTimes;
	}

	public void setLimitRateTimes(Integer limitRateTimes) {
		this.limitRateTimes = limitRateTimes;
	}

	public Byte getLimitSingleType() {
		return limitSingleType;
	}

	public void setLimitSingleType(Byte limitSingleType) {
		this.limitSingleType = limitSingleType;
	}

	public Integer getLimitSingleTimes() {
		return limitSingleTimes;
	}

	public void setLimitSingleTimes(Integer limitSingleTimes) {
		this.limitSingleTimes = limitSingleTimes;
	}

	public Byte getLimitRuleType() {
		return limitRuleType;
	}

	public void setLimitRuleType(Byte limitRuleType) {
		this.limitRuleType = limitRuleType;
	}

	public Integer getLimitRuleTimes() {
		return limitRuleTimes;
	}

	public void setLimitRuleTimes(Integer limitRuleTimes) {
		this.limitRuleTimes = limitRuleTimes;
	}

	public Byte getLoggedStatus() {
		return loggedStatus;
	}

	public void setLoggedStatus(Byte loggedStatus) {
		this.loggedStatus = loggedStatus;
	}

	public Byte getIsShare() {
		return isShare;
	}

	public void setIsShare(Byte isShare) {
		this.isShare = isShare;
	}

	public Integer getShareAddTimes() {
		return shareAddTimes;
	}

	public void setShareAddTimes(Integer shareAddTimes) {
		this.shareAddTimes = shareAddTimes;
	}

	public Byte getLimitShareType() {
		return limitShareType;
	}

	public void setLimitShareType(Byte limitShareType) {
		this.limitShareType = limitShareType;
	}

	public Integer getLimitShareTimes() {
		return limitShareTimes;
	}

	public void setLimitShareTimes(Integer limitShareTimes) {
		this.limitShareTimes = limitShareTimes;
	}

	public String getActTemplateCode() {
		return actTemplateCode;
	}

	public void setActTemplateCode(String actTemplateCode) {
		this.actTemplateCode = actTemplateCode;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public List<ActVoteClassifyDto> getActVoteClassifyDtos() {
		return actVoteClassifyDtos;
	}

	public void setActVoteClassifyDtos(List<ActVoteClassifyDto> actVoteClassifyDtos) {
		this.actVoteClassifyDtos = actVoteClassifyDtos;
	}
	
}

