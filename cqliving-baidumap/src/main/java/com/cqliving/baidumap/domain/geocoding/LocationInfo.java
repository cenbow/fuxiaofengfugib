/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.domain.geocoding;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.cqliving.baidumap.domain.Location;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月18日
 */
public class LocationInfo {
	
	/** 坐标 */
	private Location location;
	/** 位置的附加信息，是否精确查找。1为精确查找，即准确打点；0为不精确，即模糊打点 */
	private Integer precise;
	/** 可信度，描述打点准确度 */
	private Integer confidence;
	/** 地址类型 */
	private String level;
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Integer getPrecise() {
		return precise;
	}
	public void setPrecise(Integer precise) {
		this.precise = precise;
	}
	public Integer getConfidence() {
		return confidence;
	}
	public void setConfidence(Integer confidence) {
		this.confidence = confidence;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}