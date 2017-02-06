package com.cqliving.cloud.online.wz.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.framework.common.domain.AbstractEntity;

/**
 * 子帐号列表 Entity
 * Date: 2016-05-17 15:18:12
 * @author Code Generator
 */
@Entity
@Table(name = "wz_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WzUser extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID,同sys_user一致 */
	private Long id;
	/** 客户端ID */
	private Long appId;
	/** 回复机构名称 */
	private String orgName;

    private SysUser sysUser;
	
	@Id
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public String getOrgName(){
		return this.orgName;
	}
	
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id",unique=true)
	public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
