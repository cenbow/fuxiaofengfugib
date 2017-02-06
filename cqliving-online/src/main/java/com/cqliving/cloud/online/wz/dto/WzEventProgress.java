package com.cqliving.cloud.online.wz.dto;

import java.util.Date;

/**
 * Title:问政事件进度信息
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月11日
 */
public class WzEventProgress {

    public static final String STATUS1 = "已受理";
    public static final String STATUS2 = "已转交相关部门";
    public static final String STATUS3 = "已回复";
    
    /** 问政ID **/
    private Long questionId;
    /** 事件名称 **/
    private String status;
    /** 事件时间 **/
    private Date dateTime;
    
    public Long getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
}
