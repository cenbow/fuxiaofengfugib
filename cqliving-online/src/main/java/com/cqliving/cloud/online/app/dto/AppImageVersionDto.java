package com.cqliving.cloud.online.app.dto;

import java.util.Date;

public class AppImageVersionDto {
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
    
    /** App名称 */
    private String appName;

    /** loading图*/
    private String loadingUrl;
    private String linkUrl;
    
    /** 广告显示时长(倒计时多少秒)，单位：秒 */
    private Integer showTime;
    
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
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLoadingUrl() {
        return loadingUrl;
    }

    public void setLoadingUrl(String loadingUrl) {
        this.loadingUrl = loadingUrl;
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

    public Integer getShowTime() {
        return showTime;
    }

    public void setShowTime(Integer showTime) {
        this.showTime = showTime;
    }
}