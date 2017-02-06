/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.basic.dto;

import java.util.List;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月30日
 */
public class RegionDto {

	/** 主键 */
	private Long id;
	/** 区域编码 */
	private Long code;
	/** 父级区域编码 */
	private String pcode;
	/** 层级 */
	private Long hieraechy;
	/** 区域名称 */
	private String name;
	/** 省级区域名称 */
	private String level1name;
	/** 地市级区域名称 */
	private String level2name;
	/** 区县级区域名称 */
	private String level3name;
	/** 乡镇级区域名称 */
	private String level4name;
	/** 村，街道级区域名称 */
	private String level5name;
	/** 中央信息库对应区域编码 */
	private String seftid;
	/** 区域全称 */
	private String fullname;
	/** 拼音首字母缩写 */
	private String phoneticizeab;
	/** 全拼 */
	private String phoneticize;
	/** 中国大区划分code */
    private String optionCode;
    //子区域
    private List<RegionDto> subRegion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public Long getHieraechy() {
		return hieraechy;
	}
	public void setHieraechy(Long hieraechy) {
		this.hieraechy = hieraechy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel1name() {
		return level1name;
	}
	public void setLevel1name(String level1name) {
		this.level1name = level1name;
	}
	public String getLevel2name() {
		return level2name;
	}
	public void setLevel2name(String level2name) {
		this.level2name = level2name;
	}
	public String getLevel3name() {
		return level3name;
	}
	public void setLevel3name(String level3name) {
		this.level3name = level3name;
	}
	public String getLevel4name() {
		return level4name;
	}
	public void setLevel4name(String level4name) {
		this.level4name = level4name;
	}
	public String getLevel5name() {
		return level5name;
	}
	public void setLevel5name(String level5name) {
		this.level5name = level5name;
	}
	public String getSeftid() {
		return seftid;
	}
	public void setSeftid(String seftid) {
		this.seftid = seftid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhoneticizeab() {
		return phoneticizeab;
	}
	public void setPhoneticizeab(String phoneticizeab) {
		this.phoneticizeab = phoneticizeab;
	}
	public String getPhoneticize() {
		return phoneticize;
	}
	public void setPhoneticize(String phoneticize) {
		this.phoneticize = phoneticize;
	}
	public String getOptionCode() {
		return optionCode;
	}
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
	public List<RegionDto> getSubRegion() {
		return subRegion;
	}
	public void setSubRegion(List<RegionDto> subRegion) {
		this.subRegion = subRegion;
	}
    
}
