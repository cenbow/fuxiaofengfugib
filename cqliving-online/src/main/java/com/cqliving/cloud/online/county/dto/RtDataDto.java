package com.cqliving.cloud.online.county.dto;

import java.util.List;

public class RtDataDto {
    private List<String> xian;
    private List<String> qu;
    
    public List<String> getXian() {
        return xian;
    }
    public void setXian(List<String> xian) {
        this.xian = xian;
    }
    public List<String> getQu() {
        return qu;
    }
    public void setQu(List<String> qu) {
        this.qu = qu;
    }
}