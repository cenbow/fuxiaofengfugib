package com.cqliving.cloud.online.interfacc.dto;


import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月12日
 */
public class RecruitInfoDetailData {
	
	/** ID */
	private Long id;
	/** 企业性质，见ol_option表TYPE_CODE = ENT_NATURE */
	private String nature;
	/** 企业规模，见ol_option表TYPE_CODE = ENT_SCALE */
	private String scale;
	/** 企业简介 */
	private String synopsis;
	/** 招聘职位 */
	private String position;
	/** 职位描述 */
	private String description;
	/** 职位月薪，见ol_option表TYPE_CODE = SALARY */
	private String salary;
	/** 招聘人数 */
	private String numberPeople;
	/** 工作性质，见ol_option表TYPE_CODE = WORKMODE */
	private String workmode;
	/** 联系电话 */
	private String telephone;
	/** 联系地址 */
	private String address;
	/** 发布日期（yyyy-MM-dd） */
	private String publicTime;
	/** 学历，见ol_option表TYPE_CODE = EDUCATION */
	private String education;
	/** 图片地址 */
	private List<String> imageUrls;
	
	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}