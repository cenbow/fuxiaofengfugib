package com.cqliving.cloud.online.act.dto;

import java.util.List;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月22日
 */
public class ActCollectInfoResult {
	
	/** 收集字段的id **/
	private Long actCollectInfoId;
	/** 是否必填 **/
	private Byte isRequired;
	/** 字段名称 **/
	private String name;
	/** 字段类型 1:填空,2:单选,3:多选,4:下拉列表 **/
	private Byte type;
	/** 长度限制，当参数类型为1时，该值为用户填写长度限制（最多输入500）。当类型为4时，该值为最多选择多少项 **/
	private Integer length;
	/** 活动信息收集内容，当收集问题类型为（1:填空）时，该值有效。 **/
	private String inputValue;
	/** 收集字段的所有选项 **/
	private List<ActCollectOptionResult> actCollectOptionList;
	
	public Long getActCollectInfoId() {
		return actCollectInfoId;
	}
	public void setActCollectInfoId(Long actCollectInfoId) {
		this.actCollectInfoId = actCollectInfoId;
	}
	public Byte getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Byte isRequired) {
		this.isRequired = isRequired;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	public List<ActCollectOptionResult> getActCollectOptionList() {
		return actCollectOptionList;
	}
	public void setActCollectOptionList(List<ActCollectOptionResult> actCollectOptionList) {
		this.actCollectOptionList = actCollectOptionList;
	}
	
	
}
