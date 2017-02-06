package com.cqliving.cloud.online.county.dto;

public class CountyDto {
    private String rtCode;
    private String rtMsg;
    private RtDataDto rtData;
    
    public String getRtCode() {
        return rtCode;
    }
    public void setRtCode(String rtCode) {
        this.rtCode = rtCode;
    }
    public String getRtMsg() {
        return rtMsg;
    }
    public void setRtMsg(String rtMsg) {
        this.rtMsg = rtMsg;
    }
    public RtDataDto getRtData() {
        return rtData;
    }
    public void setRtData(RtDataDto rtData) {
        this.rtData = rtData;
    }
}