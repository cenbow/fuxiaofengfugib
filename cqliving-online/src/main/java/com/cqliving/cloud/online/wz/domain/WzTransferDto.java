package com.cqliving.cloud.online.wz.domain;

import java.util.Date;

/**
 * Title:问政子列表
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月20日
 */
public class WzTransferDto {
    
    //wz_transfer表的id
    private Long id;
    //问政ID
    private Long questionId;
    //事件类型
    private Byte type;
    //转交状态，对应wz_transfer表的status
    private Byte status;
    //wz_transfer表的result
    private Byte result;
    //区域
    private String regionName;
    //问政标题
    private String title;
    //谁转交的
    private String creator;
    //转交时间
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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

    public Byte getResult() {
        return result;
    }

    public void setResult(Byte result) {
        this.result = result;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    
    
}
