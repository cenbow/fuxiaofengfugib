/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月1日
 */
public class QiNiuTransCodeItems {

	private String  cmd; 	//是 	所执行的云处理操作命令fopN。
	private Integer  code;
	private String  desc;
	private String  error; 	//	如果处理失败，该字段会给出失败的详细原因。
	private String  hash;	//是 	云处理结果保存在服务端的唯一hash标识。
	private String  key; 	//是 	云处理结果的外链资源名Key。
	private String  returnOld; 	//是 	默认为0。当用户执行saveas时，如果未加force且指定的bucket：key存在，则返回1 ，告诉用户返回的是旧数据。
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getReturnOld() {
		return returnOld;
	}
	public void setReturnOld(String returnOld) {
		this.returnOld = returnOld;
	}
	
}
