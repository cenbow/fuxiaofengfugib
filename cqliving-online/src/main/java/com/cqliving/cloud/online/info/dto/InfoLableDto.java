package com.cqliving.cloud.online.info.dto;

/**
 * 资讯标签表Dto
 * <p>Title:InfoLableDto </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author huxiaoping 2016年5月6日下午4:52:02
 *
 */
public class InfoLableDto {

    /** ID */
    private Long id;
    /** 客户端_ID */
    private Long appId;
    /** 名称 */
    private String name;
    /** 业务类型 */
	private Byte sourceType;
    /** 名称 */
    private String appName;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAppId() {
        return appId;
    }
    public void setAppId(Long appId) {
        this.appId = appId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Byte getSourceType() {
		return sourceType;
	}
	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}
	public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
}