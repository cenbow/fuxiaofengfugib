package com.cqliving.cloud.online.wz.dto;

import java.util.List;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月12日
 */
public class WzAppAuthorityDto {

    private Long id;
    private String value;
    /** 问政信息收集表 **/
    private List<WzCollectInfoDto> collectInfoList;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public List<WzCollectInfoDto> getCollectInfoList() {
        return collectInfoList;
    }
    public void setCollectInfoList(List<WzCollectInfoDto> collectInfoList) {
        this.collectInfoList = collectInfoList;
    }
    
    
}
