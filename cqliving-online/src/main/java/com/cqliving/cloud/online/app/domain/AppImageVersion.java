package com.cqliving.cloud.online.app.domain;


import java.util.Date;
import java.util.LinkedHashMap;
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
 * 客户端发布广告表 Entity
 * Date: 2016-05-04 16:01:26
 * @author Code Generator
 */
@Entity
@Table(name = "app_image_version")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppImageVersion extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** Android */
	public static final String TYPE1 = "1";
	/** IOS */
	public static final String TYPE2 = "2";
	public static final String TYPE0 = "1,2";
		
	public static final Byte USESTATUS0 = 0;
	public static final Byte USESTATUS1 = 1;
	/** 正常 */
    public static final Byte STATUS3 = 3;
    /** 删除 */
    public static final Byte STATUS99 = 99;
	/** 客户端类型 */
	public static final Map<String, String> allTypes = new LinkedHashMap<>();
	/** 使用状态  */
	public static final Map<Byte, String> allUseStatus = new LinkedHashMap<>();
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "全部");
		allTypes.put(TYPE1, "Android");
		allTypes.put(TYPE2, "IOS");
		allUseStatus.put(USESTATUS0, "未上线");
		allUseStatus.put(USESTATUS1, "已上线");
		allStatuss.put(STATUS3, "正常");
        allStatuss.put(STATUS99, "删除");
	}
	
	/** 主键 */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 标题 */
	private String title;
	/** 客户端类型 */
	private String type;
	/** 版本号 */
	private Integer versionNo;
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
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 使用状态 */
    private Byte useStatus;
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
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	public Integer getVersionNo(){
		return this.versionNo;
	}
	
	public void setVersionNo(Integer versionNo){
		this.versionNo = versionNo;
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
	
	public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Byte useStatus) {
        this.useStatus = useStatus;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}