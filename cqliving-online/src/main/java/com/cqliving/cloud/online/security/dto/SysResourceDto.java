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
public class SysResourceDto implements Comparable<SysResourceDto>{

	/** 主键ID */
	private Long id;
	/** 父ID */
	private Long parentId;
	/** 资源名称 */
	private String title;
	/** 资源类型 */
	private String restype;
	/** 资源值 */
	private String resString;
	/** 权限值 */
	private String permissionValue;
	/** 描述 */
	private String descn;
	/** 状态 */
	private Byte status;
	/** 排序值 */
	private Integer sortNum;
	/** 创建时间 */
	private Date createDate;
	/** 资源分类ID */
	private Long sysResTypeId;
	
	Set<SysResourceDto> subResource;
	
	public Set<SysResourceDto> getSubResource() {
		return subResource;
	}
	public void setSubResource(Set<SysResourceDto> subResource) {
		this.subResource = subResource;
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
	public String getRestype() {
		return restype;
	}
	public void setRestype(String restype) {
		this.restype = restype;
	}
	public String getResString() {
		return resString;
	}
	public void setResString(String resString) {
		this.resString = resString;
	}
	public String getPermissionValue() {
		return permissionValue;
	}
	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}
	public String getDescn() {
		return descn;
	}
	public void setDescn(String descn) {
		this.descn = descn;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getSysResTypeId() {
		return sysResTypeId;
	}
	public void setSysResTypeId(Long sysResTypeId) {
		this.sysResTypeId = sysResTypeId;
	}
	
	/**
	 * Title:添加比较器
	 * <p>Description:</p>
	 * @author yuwu on 2016年6月30日
	 * @param sysMenuDto
	 * @return
	 */
	@Override
	public int compareTo(SysResourceDto sysResourceDto) {
		//升序排序
		int result = this.sortNum.intValue() > sysResourceDto.getSortNum().intValue() ? 1 : (this.sortNum.intValue() == sysResourceDto.getSortNum().intValue() ? 0 : -1);
        if (result == 0) {
            result = this.sortNum.intValue() > sysResourceDto.getId().intValue() ? 1 : (this.sortNum.intValue() == sysResourceDto.getId().intValue() ? 0 : -1);
        }
        return result;
	}
}
