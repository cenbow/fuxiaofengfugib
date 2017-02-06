package com.cqliving.log.util;


import java.util.Date;

/**
 * 系统资源表 Entity
 * Date: 2016-04-15 11:00:39
 * @author Code Generator
 */
public class SysResourceDto{
    
    /** 链接 */
    public static final String URL = "URL";
    /** 按钮 */
    public static final String BUTTON = "BUTTON";

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
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getParentId(){
		return this.parentId;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getRestype(){
		return this.restype;
	}
	
	public void setRestype(String restype){
		this.restype = restype;
	}
	public String getResString(){
		return this.resString;
	}
	
	public void setResString(String resString){
		this.resString = resString;
	}
	public String getPermissionValue(){
		return this.permissionValue;
	}
	
	public void setPermissionValue(String permissionValue){
		this.permissionValue = permissionValue;
	}
	public String getDescn(){
		return this.descn;
	}
	
	public void setDescn(String descn){
		this.descn = descn;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Integer getSortNum(){
		return this.sortNum;
	}
	
	public void setSortNum(Integer sortNum){
		this.sortNum = sortNum;
	}
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public Long getSysResTypeId() {
		return sysResTypeId;
	}

	public void setSysResTypeId(Long sysResTypeId) {
		this.sysResTypeId = sysResTypeId;
	}
}
