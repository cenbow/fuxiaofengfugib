package com.cqliving.cloud.online.config.dto;


import java.util.Date;

/**
 * config_热线表Dto
 * Date: 2016-07-07 11:54:13
 * @author huxiaoping
 */
public class ConfigHotlineDto {
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 热线类型ID，config_hotline_type表主键 */
	private Long hotlineTypeId;
	/** 热线名称 */
	private String name;
	/** 电话，最多输入三个电话 */
	private String phone;
	/** 排序号 */
	private Integer sortNo;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	
	/** app名称 */
    private String appName;
    /** 热线分类名称 */
    private String typeName;
	
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
	public Long getHotlineTypeId(){
		return this.hotlineTypeId;
	}
	
	public void setHotlineTypeId(Long hotlineTypeId){
		this.hotlineTypeId = hotlineTypeId;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getPhone(){
		return this.phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
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
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
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

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}