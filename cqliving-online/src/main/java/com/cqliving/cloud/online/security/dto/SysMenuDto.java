/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.security.dto;

import java.util.Date;
import java.util.Set;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年4月20日
 */
public class SysMenuDto implements Comparable<SysMenuDto>{

	/** 主键ID */
	private Long id;
	/** 父菜单 */
	private Long parentId;
	/** 菜单名称 */
	private String title;
	/** 菜单名称拼音首字母 */
	private String titleFirstSpell;
	/** 菜单图标 */
	private String icon;
	/** 显示方式 */
	private Byte showMode;
	/** 描述 */
	private String descn;
	/** 排序 */
	private Integer sortNum;
	/** 状态 */
	private Byte status;
	/** 资源ID */
	private Long resourceId;
	/** 创建时间 */
	private Date createDate;
	
	private Set<SysMenuDto> subSysMenu;
	
	public Set<SysMenuDto> getSubSysMenu() {
		return subSysMenu;
	}
	public void setSubSysMenu(Set<SysMenuDto> subSysMenu) {
		this.subSysMenu = subSysMenu;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleFirstSpell() {
		return titleFirstSpell;
	}
	public void setTitleFirstSpell(String titleFirstSpell) {
		this.titleFirstSpell = titleFirstSpell;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Byte getShowMode() {
		return showMode;
	}
	public void setShowMode(Byte showMode) {
		this.showMode = showMode;
	}
	public String getDescn() {
		return descn;
	}
	public void setDescn(String descn) {
		this.descn = descn;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public int compareTo(SysMenuDto sysMenuDto) {
		//升序排序
		int result = this.sortNum.intValue() > sysMenuDto.getSortNum().intValue() ? 1 : (this.sortNum.intValue() == sysMenuDto.getSortNum().intValue() ? 0 : -1);
        if (result == 0) {
            result = this.sortNum.intValue() > sysMenuDto.getId().intValue() ? 1 : (this.sortNum.intValue() == sysMenuDto.getId().intValue() ? 0 : -1);
        }
        return result;
	}
}
