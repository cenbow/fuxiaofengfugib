package com.cqliving.cloud.online.account.dto;

/**
 * Title:用户收集信息
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月22日
 */
public class UserActCollectInfoDto {

	/** 收集字段的id **/
	private Long actCollectInfoId;
	/** 答题收集配置表id 为了区分唯一  **/
	private Long actTestCollectId;
	/** 是否必填 **/
	private Byte isRequired;
	/** 字段名称 **/
	private String name;
	/** 字段类型 1:填空,2:单选,3:多选,4:下拉列表 **/
	private Byte type;
	/** 长度限制，当参数类型为1时，该值为用户填写长度限制（最多输入500）。当类型为4时，该值为最多选择多少项 **/
	private Integer length;
	/** 选项id **/
	private Long optionId;
	/** 选项值 **/
	private String optionValue;
	/** 活动信息收集选项表ID，即问题答案（act_collect_info_option表主键）。当收集问题类型为（2:单选,3:多选,4:下拉列表）时，该值有效。 **/
	private Long selectValueId;
	/** 活动信息收集内容，当收集问题类型为（1:填空）时，该值有效。 **/
	private String inputValue;
	
	public Long getActCollectInfoId() {
		return actCollectInfoId;
	}
	public void setActCollectInfoId(Long actCollectInfoId) {
		this.actCollectInfoId = actCollectInfoId;
	}
	public Long getActTestCollectId() {
		return actTestCollectId;
	}
	public void setActTestCollectId(Long actTestCollectId) {
		this.actTestCollectId = actTestCollectId;
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
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public Long getSelectValueId() {
		return selectValueId;
	}
	public void setSelectValueId(Long selectValueId) {
		this.selectValueId = selectValueId;
	}
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	
}
