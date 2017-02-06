/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.interfacc.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title: 招聘条件
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月12日
 */
public class RecruitConditionResult {
	
	/** 月薪范围 */
	private List<CommonKeyValueData> salaries;
	/** 学历 */
	private List<CommonKeyValueData> educations;
	/** 工作性质 */
	private List<CommonKeyValueData> workmodes;

	public List<CommonKeyValueData> getSalaries() {
		return salaries;
	}

	public void setSalaries(List<CommonKeyValueData> salaries) {
		this.salaries = salaries;
	}

	public List<CommonKeyValueData> getEducations() {
		return educations;
	}

	public void setEducations(List<CommonKeyValueData> educations) {
		this.educations = educations;
	}

	public List<CommonKeyValueData> getWorkmodes() {
		return workmodes;
	}

	public void setWorkmodes(List<CommonKeyValueData> workmodes) {
		this.workmodes = workmodes;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}