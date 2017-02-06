package com.cqliving.cloud.online.app.domain;


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
 * 客户端版本表 Entity
 * Date: 2016-04-29 16:18:30
 * @author Code Generator
 */
@Entity
@Table(name = "APP_VERSION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppVersion extends AbstractEntity {

	private static final long serialVersionUID = 1L;

//	/** android可见 */
//	public static final String TYPE0 = "1,2";
	/** android可见 */
	public static final String TYPE1 = "1";
	/** IOS可见 */
	public static final String TYPE2 = "2";
		
	/** 客户端类型 */
	public static final Map<String, String> allTypes = Maps.newTreeMap();
	static {
//		allTypes.put(TYPE0, "android和IOS可见");
		allTypes.put(TYPE1, "android可见");
		allTypes.put(TYPE2, "IOS可见");
	}
	/** 正常 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "正常");
		allStatuss.put(STATUS99, "删除");
	}
        
    /** 客户端类型 */
    public static final Map<Byte, String> allSTATUS = Maps.newTreeMap();
    static {
        allSTATUS.put(STATUS3, "正常");
        allSTATUS.put(STATUS99, "删除");
    }
	
	/** 主键 */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 版本升级说明 */
	private String updateContext;
	/** 客户端类型 */
	private String type;
	/** 客户端名称 */
	private String name;
	/** 客户端URL */
	private String url;
	/** 显示版本号 */
	private String viewVersion;
	/** 版本号 */
	private Integer vesionNo;
	/** 最低支持版本号 */
	private Integer minVersion;
	/** 发布时间 */
	private Date publishTime;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 更新时间 */
	private Date updateTime;
	/** 状态 */
	private Byte status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public String getUpdateContext(){
		return this.updateContext;
	}
	
	public void setUpdateContext(String updateContext){
		this.updateContext = updateContext;
	}
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	public String getViewVersion(){
		return this.viewVersion;
	}
	
	public void setViewVersion(String viewVersion){
		this.viewVersion = viewVersion;
	}
	public Integer getVesionNo(){
		return this.vesionNo;
	}
	
	public void setVesionNo(Integer vesionNo){
		this.vesionNo = vesionNo;
	}
	public Integer getMinVersion(){
		return this.minVersion;
	}
	
	public void setMinVersion(Integer minVersion){
		this.minVersion = minVersion;
	}
	public Date getPublishTime(){
		return this.publishTime;
	}
	
	public void setPublishTime(Date publishTime){
		this.publishTime = publishTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Long getUpdatorId(){
		return this.updatorId;
	}
	
	public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
