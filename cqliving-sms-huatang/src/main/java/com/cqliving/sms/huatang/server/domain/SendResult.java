/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.sms.huatang.server.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月26日
 */
public class SendResult {
	
	/** 账户短信剩余条数 */
	private Integer remainPoint;
	/** 本次任务的 id */
	private Long taskId;
	/** 提交成功的短信数 */
	private Integer successCounts;
	
	public Integer getRemainPoint() {
		return remainPoint;
	}
	public void setRemainPoint(Integer remainPoint) {
		this.remainPoint = remainPoint;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Integer getSuccessCounts() {
		return successCounts;
	}
	public void setSuccessCounts(Integer successCounts) {
		this.successCounts = successCounts;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}