package com.org.weixin.system.domain;


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
import org.wechat.framework.domain.AbstractEntity;

import com.google.common.collect.Maps;

/**
 * sys_config Entity
 *
 * Date: 2015-07-23 20:46:52
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysConfig extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 禁用 */
	public static final byte STATUS0 = 0;
	/** 启用 */
	public static final byte STATUS1 =  1;
		
	/** 状态{0:禁用, 1:启用} */
		public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS0, "禁用");
		allStatuss.put(STATUS1, "启用");
	}
	
	/** 主键 */
	private Long id;
	/** 模块ID */
	private Long moduleId;
	/** 配置项名称 */
	private String configKey;
	/** 配置项的值 */
	private String configValue;
	/** 状态{0:禁用, 1:启用} */
	private byte status;
	/** 备注 */
	private String remarks;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getModuleId(){
		return this.moduleId;
	}
	
	public void setModuleId(Long moduleId){
		this.moduleId = moduleId;
	}
	public String getConfigKey(){
		return this.configKey;
	}
	
	public void setConfigKey(String configKey){
		this.configKey = configKey;
	}
	public String getConfigValue(){
		return this.configValue;
	}
	
	public void setConfigValue(String configValue){
		this.configValue = configValue;
	}
	public byte getStatus(){
		return this.status;
	}
	
	public void setStatus(byte status){
		this.status = status;
	}
	public String getRemarks(){
		return this.remarks;
	}
	
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
