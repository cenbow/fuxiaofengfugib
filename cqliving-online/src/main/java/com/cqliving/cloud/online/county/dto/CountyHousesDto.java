package com.cqliving.cloud.online.county.dto;

public class CountyHousesDto {
    private String rtCode;
    private String rtMsg;
    private HousesRtDataDto rtData;
    
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
    public HousesRtDataDto getRtData() {
        return rtData;
    }
    public void setRtData(HousesRtDataDto rtData) {
        this.rtData = rtData;
    }
}