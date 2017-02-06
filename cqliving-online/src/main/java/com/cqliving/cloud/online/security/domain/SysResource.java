package com.cqliving.cloud.online.security.domain;


import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 系统资源表 Entity
 * Date: 2016-04-15 11:00:39
 * @author Code Generator
 */
@Entity
@Table(name = "sys_resource")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysResource extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 链接 */
	public static final Integer RESTYPE0 = 0;
	/** 按钮 */
	public static final Integer RESTYPE1 = 1;
		
	/** 资源类型 */
	public static final Map<Integer, String> allRestypes = Maps.newTreeMap();
	static {
		allRestypes.put(RESTYPE0, "链接");
		allRestypes.put(RESTYPE1, "按钮");
	}
	/** 禁用 */
	public static final Byte STATUS0 = 0;
	/** 启用 */
	public static final Byte STATUS1 = 1;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS0, "禁用");
		allStatuss.put(STATUS1, "启用");
	}
	
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
	/*private Set<SysResource> subResource;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    @OrderBy(clause="sortNum ASC")
	@Where(clause="status=1")
    public Set<SysResource> getSubResource() {
        return subResource;
    }

    public void setSubResource(Set<SysResource> subResource) {
        this.subResource = subResource;
    }*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
