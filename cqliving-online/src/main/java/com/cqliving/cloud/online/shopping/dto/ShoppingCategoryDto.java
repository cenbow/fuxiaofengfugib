package com.cqliving.cloud.online.shopping.dto;

import java.util.Date;
import java.util.Set;

public class ShoppingCategoryDto  implements Comparable<ShoppingCategoryDto>{    
    /** ID */
    private Long id;
    /** 客户端_ID */
    private Long appId;
    /** 分类级别 */
    private Integer level;
    /** 父级ID */
    private Long parentId;
    /** 名称 */
    private String name;
    /** 图片地址 */
    private String imgUrl;
    /** 排序号 */
    private String sortNo;
    /** 状态 */
    private Byte status;
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
    
    private Set<ShoppingCategoryDto> subs;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Set<ShoppingCategoryDto> getSubs() {
        return subs;
    }

    public void setSubs(Set<ShoppingCategoryDto> subs) {
        this.subs = subs;
    }

    @Override
    public int compareTo(ShoppingCategoryDto o) {
        if (this.sortNo.equals(o.getSortNo())) {
            return this.id.compareTo(o.id);
        }
        // return -1;
        return Integer.parseInt(null==this.sortNo?"0":this.sortNo) - Integer.parseInt(null==o.getSortNo()?"0":o.getSortNo());
    }    
}