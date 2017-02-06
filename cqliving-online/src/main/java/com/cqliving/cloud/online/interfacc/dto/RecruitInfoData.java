package com.cqliving.cloud.online.interfacc.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月12日
 */
public class RecruitInfoData {
	
	/** ID */
	private Long id;
	/** 企业名称 */
	private String name;
	/** 招聘职位 */
	private String position;
	/** 职位月薪，见ol_option表TYPE_CODE = SALARY */
	private String salary;
	/** 招聘人数 */
	private String numberPeople;
	/** 工作性质，见ol_option表TYPE_CODE = WORKMODE */
	private String workmode;
	/** 发布时间 */
	private String publicTime;
	/** 发布日期（yyyy-MM-dd） */
	private String publicTimeStr;
	/** 学历，见ol_option表TYPE_CODE = EDUCATION */
	private String education;
	/** 标签 */
	private String entLabel;
	/** 排序号 */
	private Integer sortNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getNumberPeople() {
		return numberPeople;
	}

	public void setNumberPeople(String numberPeople) {
		this.numberPeople = numberPeople;
	}

	public String getWorkmode() {
		return workmode;
	}

	public void setWorkmode(String workmode) {
		this.workmode = workmode;
	}

	public String getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEntLabel() {
		return entLabel;
	}

	public void setEntLabel(String entLabel) {
		this.entLabel = entLabel;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getPublicTimeStr() {
		return publicTimeStr;
	}

	public void setPublicTimeStr(String publicTimeStr) {
		this.publicTimeStr = publicTimeStr;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}