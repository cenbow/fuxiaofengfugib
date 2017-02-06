package com.cqliving.cloud.online.actcustom.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月21日
 */
public class ActCustomQrcodeColumnDto {
	/** ID */
	private Long id;
	
	/** 参数名称 */
	private String name;
	/** 参数类型 */
	private Byte type;
	/** 描述信息 */
	private String description;
	
	/** 用户二维码扫描活动表，表act_qrcode的code字段 */
	private String actQrcodeCode;
	/** 收集列配置表ID，表act_custom_column的主键 */
	private Long actCustomColumnId;
	/** 序号 */
	private Integer sortNo;
	/** 字段长度，为0则不检查，该字段的值不能大于user_act_custom_column表的value字段的长度 */
	private Integer length;
	/** 是否必填 */
	private Byte isRequired;
	/** 是否列表显示 */
	private Byte isListView;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActQrcodeCode() {
		return actQrcodeCode;
	}
	public void setActQrcodeCode(String actQrcodeCode) {
		this.actQrcodeCode = actQrcodeCode;
	}
	public Long getActCustomColumnId() {
		return actCustomColumnId;
	}
	public void setActCustomColumnId(Long actCustomColumnId) {
		this.actCustomColumnId = actCustomColumnId;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Byte getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Byte isRequired) {
		this.isRequired = isRequired;
	}
	public Byte getIsListView() {
		return isListView;
	}
	public void setIsListView(Byte isListView) {
		this.isListView = isListView;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
