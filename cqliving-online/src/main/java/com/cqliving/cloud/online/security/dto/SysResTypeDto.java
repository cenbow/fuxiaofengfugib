package com.cqliving.cloud.online.security.dto;

import java.util.Date;
import java.util.List;

import com.cqliving.cloud.online.config.dto.PermissionDto;

public class SysResTypeDto {

    /** 主键ID */
    private Long id;
    /** 资源名称 */
    private String name;
    /** 排序值 */
    private Integer sortNum;
    /** 状态 */
    private Byte status;
    /** 创建时间 */
    private Date createDate;
    
    private List<PermissionDto> permissions;

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

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.permissions = permissions;
    }    
}