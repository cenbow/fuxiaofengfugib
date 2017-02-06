/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.domain.place;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title: 位置及位置建议接口返回对象
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月15日
 */
public class PlaceResult {
	
	/** 本次API访问状态，如果成功返回0，如果失败返回其他数字 */
	private int status;
	/** 对API访问状态值的英文说明，如果成功返回"ok"，并返回结果字段，如果失败返回错误说明 */
	private String message;
	/** 检索总数，用户请求中设置了page_num字段才会出现total字段。当检索总数值大于760时，多次刷新同一请求得到total的值可能稍有不同，属于正常情况 */
	private int total;
	//百度返回的数据有的为：result，有的为：results，因此存在以下两个字段
	/** 结果数据 */
	private List<PlaceBaseInfo> result;
	/** 结果数据 */
	private List<PlaceBaseInfo> results;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<PlaceBaseInfo> getResult() {
		return result;
	}
	public void setResult(List<PlaceBaseInfo> result) {
		this.result = result;
	}
	
	public List<PlaceBaseInfo> getResults() {
		return results;
	}
	public void setResults(List<PlaceBaseInfo> results) {
		this.results = results;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}