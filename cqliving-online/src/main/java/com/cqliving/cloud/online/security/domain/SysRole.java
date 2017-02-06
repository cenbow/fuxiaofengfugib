package com.cqliving.cloud.online.security.domain;


import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 系统角色表 Entity
 * Date: 2016-04-15 11:00:43
 * @author Code Generator
 */
@Entity
@Table(name = "sys_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRole extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public final static Byte TYPE1 = 1;
	public final static Byte TYPE2 = 2;
	public final static Map<Byte,String> allTypes = Maps.newHashMap();
	
	static {
		allTypes.put(TYPE1, "是公共角色");
		allTypes.put(TYPE2, "不是公共角色");
	}
	/** 主键ID */
	private Long id;
	/** 角色名称 */
	private String roleName;
	/** 描述信息 */
	private String descn;
	/** APP应用ID */
	private Long appId;
	/** 组织机构ID */
	private Long orgId;
	/** 公共角色CODE */
	private String commonRoleCode;
	/** 角色类型{1:是公共角色,2:不是公共角色} */
	private Byte type;
	/*Set<SysUser> users;
	
	@ManyToMany(mappedBy = "role", targetEntity = SysUser.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@LazyCollection(value = LazyCollectionOption.EXTRA)
	public Set<SysUser> getUsers() {
		return users;
	}

	public void setUsers(Set<SysUser> users) {
		this.users = users;
	}
	*/
	
	Set<SysResource> rescs;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER, targetEntity = SysResource.class)
	@JoinTable(name = "sys_role_resc", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = @JoinColumn(name = "RESC_ID"))
	@OrderBy(value = "sort_num")
	@LazyCollection(value = LazyCollectionOption.FALSE)
	public Set<SysResource> getRescs() {
		return rescs;
	}

	public void setRescs(Set<SysResource> rescs) {
		this.rescs = rescs;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getRoleName(){
		return this.roleName;
	}
	
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	public String getDescn(){
		return this.descn;
	}
	
	public void setDescn(String descn){
		this.descn = descn;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
    
	public String getCommonRoleCode() {
		return commonRoleCode;
	}

	public void setCommonRoleCode(String commonRoleCode) {
		this.commonRoleCode = commonRoleCode;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commonRoleCode == null) ? 0 : commonRoleCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysRole other = (SysRole) obj;
		if (commonRoleCode == null) {
			if (other.commonRoleCode != null)
				return false;
		} else if (!commonRoleCode.equals(other.commonRoleCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
