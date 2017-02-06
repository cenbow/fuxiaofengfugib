package com.cqliving.cloud.online.wz.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月30日
 */
public class WzQuestionData {
    
    private Long id;
    private String status;
    private String title;
    private Integer viewCount;
    private Integer replyCount;
    private String createTime;
    private String content;
    
    private Byte sourceType;
    private Byte detailViewType;
    private String url;
    private String shareUrl;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
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
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
	public Byte getSourceType() {
		return sourceType;
	}
	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}
	public Byte getDetailViewType() {
		return detailViewType;
	}
	public void setDetailViewType(Byte detailViewType) {
		this.detailViewType = detailViewType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    
}
