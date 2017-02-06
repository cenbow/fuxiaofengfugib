/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.app.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月3日
 */
public class AdditionalParameters {

	private Set<AppColumnsDto> children;
	
	private String itemSelected="true";
	
	private Long id;
	
	public Set<AppColumnsDto> getChildren() {
		return children;
	}

	public void setChildren(Set<AppColumnsDto> children) {
		this.children = children;
	}

	@JsonProperty(value="item-selected")
	public String getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(String itemSelected) {
		this.itemSelected = itemSelected;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
