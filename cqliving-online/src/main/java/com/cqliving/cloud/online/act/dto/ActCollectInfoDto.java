package com.cqliving.cloud.online.act.dto;

import java.util.Date;
import java.util.List;

import com.cqliving.cloud.online.act.domain.ActCollectOption;

public class ActCollectInfoDto {
    /** ID */
    private Long id;
    /** 客户端_ID */
    private Long appId;
    /** 参数名称 */
    private String name;
    /** 参数类型 */
    private Byte type;
    /** 长度限制，当参数类型为1时，该值为用户填写长度限制（最多输入500）。当类型为4时，该值为最多选择多少项 */
    private Integer length;
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
    
    /** 更新人 */
    private String appName;
    /** 选项列表 */
    private List<ActCollectOption> optionList;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
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

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<ActCollectOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<ActCollectOption> optionList) {
        this.optionList = optionList;
    }
}