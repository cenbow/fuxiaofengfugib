package com.cqliving.cloud.online.app.dto;

import java.util.Date;

public class AppVersionDto {
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
    /** 状态  */
    private Byte status;
    
    /** app名称 */
    private String appName;
    /** 最大的id，根据appId分组，页面只有最大的id才有修改功能 */
    private String maxId;
    
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

    public String getUpdateContext() {
        return updateContext;
    }

    public void setUpdateContext(String updateContext) {
        this.updateContext = updateContext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getViewVersion() {
        return viewVersion;
    }

    public void setViewVersion(String viewVersion) {
        this.viewVersion = viewVersion;
    }

    public Integer getVesionNo() {
        return vesionNo;
    }

    public void setVesionNo(Integer vesionNo) {
        this.vesionNo = vesionNo;
    }

    public Integer getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(Integer minVersion) {
        this.minVersion = minVersion;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getMaxId() {
        return maxId;
    }

    public void setMaxId(String maxId) {
        this.maxId = maxId;
    }
}
