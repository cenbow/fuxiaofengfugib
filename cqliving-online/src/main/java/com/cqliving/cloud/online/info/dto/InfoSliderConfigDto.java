package com.cqliving.cloud.online.info.dto;

import java.util.Date;

public class InfoSliderConfigDto {
    
    /** ID */
    private Long id;
    /** 客户端_ID */
    private Long appId;
    /** 栏目表ID，app_columns表主键 */
    private Long columnsId;
    /** 轮播图显示数量 */
    private Integer value;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private Long creatorId;
    /** 创建人姓名 */
    private String creator;
    /** 更新时间 */
    private Date updateTime;
    /** 更新人ID */
    private Long updatorId;
    /** 更新人 */
    private String updator;
    
    /**------------------业务字段---------------*/
    //栏目名称
    private String columnsName;

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

    public Long getColumnsId() {
        return columnsId;
    }

    public void setColumnsId(Long columnsId) {
        this.columnsId = columnsId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getColumnsName() {
        return columnsName;
    }

    public void setColumnsName(String columnsName) {
        this.columnsName = columnsName;
    }
}
