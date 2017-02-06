/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dto;

import java.util.List;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月1日
 */
public class QiNiuTransCodeResult {

	public final static int CODE0=0;
	public final static int CODE1=1;
	
	private String id;// 	是 	持久化处理的进程ID，即前文中的<persistentId>。
	private Integer code; 	//是 	状态码0成功，1等待处理，2正在处理，3处理失败，4通知提交失败。
	private String  desc;	//是 	与状态码相对应的详细描述。
	private String  inputKey;	//是 	处理源文件的文件名。
	private String  inputBucket;	//是 	处理源文件所在的空间名。
	private List<QiNiuTransCodeItems>  items; 	//是 	云处理操作列表，包含每个云处理操作的状态信息。
	private String  pipeline; 	//是 	云处理操作的处理队列，默认使用队列为共享队列0.default。
	private String  reqid ;	//是 	云处理请求的请求id，主要用于七牛技术人员的问题排查。
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getInputKey() {
		return inputKey;
	}
	public void setInputKey(String inputKey) {
		this.inputKey = inputKey;
	}
	public String getInputBucket() {
		return inputBucket;
	}
	public void setInputBucket(String inputBucket) {
		this.inputBucket = inputBucket;
	}
	public List<QiNiuTransCodeItems> getItems() {
		return items;
	}
	public void setItems(List<QiNiuTransCodeItems> items) {
		this.items = items;
	}
	public String getPipeline() {
		return pipeline;
	}
	public void setPipeline(String pipeline) {
		this.pipeline = pipeline;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	@Override
	public String toString() {
		return "QiNiuTransCodeResult [id=" + id + ", code=" + code + ", desc=" + desc + ", inputKey=" + inputKey
				+ ", inputBucket=" + inputBucket + ", items=" + items + ", pipeline=" + pipeline + ", reqid=" + reqid
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
