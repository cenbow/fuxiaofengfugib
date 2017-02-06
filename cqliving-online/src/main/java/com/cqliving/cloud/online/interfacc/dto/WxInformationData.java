package com.cqliving.cloud.online.interfacc.dto;

import java.util.List;

import com.cqliving.cloud.online.app.domain.AppResouse;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2017年1月13日
 */
public class WxInformationData {

    /** 新闻id */
    private Long infoClassifyId;
    /** 新闻标题 */
    private String title;
    /** 新闻内容 */
    private String content;
    /** 新闻来源 */
    private String source;
    /** 发布时间 */
    private String pubDate;
    /** 浏览量 */
    private String viewCount;
    /** 新闻类型 */
    private Byte contextType;
    /** 图片，视频，音频 */
    private List<AppResouse> appResource;
    /** 新闻摘要 */
    private String synopsis;
    
    public Long getInfoClassifyId() {
        return infoClassifyId;
    }
    public void setInfoClassifyId(Long infoClassifyId) {
        this.infoClassifyId = infoClassifyId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public String getViewCount() {
        return viewCount;
    }
    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }
    public Byte getContextType() {
        return contextType;
    }
    public void setContextType(Byte contextType) {
        this.contextType = contextType;
    }
    public List<AppResouse> getAppResource() {
        return appResource;
    }
    public void setAppResource(List<AppResouse> appResource) {
        this.appResource = appResource;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    
}
