package com.cqliving.cloud.online.info.dto;

public class InfoTempleteImageConfigDto {
    /** ID */
    private Long id;
    /** 模板名称 */
    private String name;
    /** 模板CODE */
    private String templetCode;
    /** 是否等比压缩，不限制是以 */
    private Byte limitType;
    /** 宽 */
    private Integer width;
    /** 高 */
    private Integer hight;
    /** 备注说明 */
    private String context;
    /** 列表图片类型 */
    private Byte listType;
    
    private Long appId;
    
    private String appName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTempletCode() {
        return templetCode;
    }

    public void setTempletCode(String templetCode) {
        this.templetCode = templetCode;
    }

    public Byte getLimitType() {
        return limitType;
    }

    public void setLimitType(Byte limitType) {
        this.limitType = limitType;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHight() {
        return hight;
    }

    public void setHight(Integer hight) {
        this.hight = hight;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Byte getListType() {
        return listType;
    }

    public void setListType(Byte listType) {
        this.listType = listType;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}