package com.cqliving.cloud.online.wz.dto;

import java.util.Date;
import java.util.List;

import com.cqliving.cloud.online.wz.domain.WzQuestionCollectInfo;
import com.cqliving.cloud.online.wz.domain.WzQuestionImage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月10日
 */
public class WzQuestionDto {
    
    private Long id;
    private Long appId;
    private Byte type;
    private Byte status;
    private String title;
    private String regionCode;
    private String regionName;
    private Integer viewCount;
    private Integer replyCount;
    private Date createTime;
    /** 内容 */
    private String content;
    /** 回复内容 */
    private String replyContent;
    /** 回复时间 */
    private Date replyTime;
    /** 创建人名称 */
    private String creator;
    /** 审核时间（即受理时间） */
    private Date auditingTime;
    /** 受理部门，用于在前台展示 */
    private String auditingDepartment;
    /** 问政图片 **/
    private List<WzQuestionImage> images;
    /** 事件进度 **/
    private List<WzEventProgress> eventProgress;
    /** 收集信息 **/
    private List<WzQuestionCollectInfo> collectInfoList;
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
    
    public Byte getType() {
        return type;
    }
    public void setType(Byte type) {
        this.type = type;
    }
    public Byte getStatus() {
        return status;
    }
    public void setStatus(Byte status) {
        this.status = status;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getViewCount() {
        return viewCount;
    }
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
    public Integer getReplyCount() {
        return replyCount;
    }
    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getReplyContent() {
        return replyContent;
    }
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    public Date getReplyTime() {
        return replyTime;
    }
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public Date getAuditingTime() {
        return auditingTime;
    }
    public void setAuditingTime(Date auditingTime) {
        this.auditingTime = auditingTime;
    }
    public String getAuditingDepartment() {
        return auditingDepartment;
    }
    public void setAuditingDepartment(String auditingDepartment) {
        this.auditingDepartment = auditingDepartment;
    }
    public List<WzQuestionImage> getImages() {
        return images;
    }
    public void setImages(List<WzQuestionImage> images) {
        this.images = images;
    }
    public List<WzEventProgress> getEventProgress() {
        return eventProgress;
    }
    public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public void setEventProgress(List<WzEventProgress> eventProgress) {
        this.eventProgress = eventProgress;
    }
	public List<WzQuestionCollectInfo> getCollectInfoList() {
		return collectInfoList;
	}
	public void setCollectInfoList(List<WzQuestionCollectInfo> collectInfoList) {
		this.collectInfoList = collectInfoList;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
}
