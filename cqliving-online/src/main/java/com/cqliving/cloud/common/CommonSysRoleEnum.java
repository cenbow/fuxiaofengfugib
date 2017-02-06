/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月19日
 */
public enum CommonSysRoleEnum {

	DISTRICT_MANAGER("DISTRICT_MANAGER","区县管理员"),
	EDITOR ("GENERAL_EDITOR","编辑"),
	WENZHENG("WENZHENG","问政"),
	WENZHENG_SUB("WENZHENG_SUB","问政子账号");
	
	public String code;
	public String des;
	
	/**
	 * @param code
	 * @param des
	 */
	private CommonSysRoleEnum(String code, String des) {
		this.code = code;
		this.des = des;
	}
	
	public String getCode(){
	    return this.code;
	} 
	
}
