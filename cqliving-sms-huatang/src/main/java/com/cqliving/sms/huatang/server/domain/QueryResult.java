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
 * @author Tangtao on 2016年10月9日
 */
public class QueryResult {
	
	/** 余额 */
	private Long overage;
	/** 总点数  当支付方式为预付费是返回总充值点数 */
	private Long sendTotal;
	
	public void setOverage(Long overage) {
		this.overage = overage;
	}

	public Long getOverage() {
		return overage;
	}
	
	public Long getSendTotal() {
		return sendTotal;
	}

	public void setSendTotal(Long sendTotal) {
		this.sendTotal = sendTotal;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}