package com.cqliving.cloud.online.shopping.dto;

import java.util.Date;

public class ShoppingRecommendDto {
    /** ID */
    private Long id;
    /** 客户端_ID */
    private Long appId;
    /** 图片地址 */
    private String imageUrl;
    /** 商品ID */
    private Long shoppingGoodsId;
    /** 类型 */
    private Byte type;
    /** 排序号 */
    private Integer sortNo;
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
    
    //业务字段
    /** 商品分类Id */
    private Long shoppingCategoryId;
    /** 商品分类名 */
    private String shoppingCategoryName;
    /** 商品名 */
    private String shoppingGoodsName;
    /** 客户端名 */
    private String appName;
    
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
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Long getShoppingGoodsId() {
        return shoppingGoodsId;
    }
    public void setShoppingGoodsId(Long shoppingGoodsId) {
        this.shoppingGoodsId = shoppingGoodsId;
    }
    public Byte getType() {
        return type;
    }
    public void setType(Byte type) {
        this.type = type;
    }
    public Integer getSortNo() {
        return sortNo;
    }
    public void setSortNo(Integer sortNo) {
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
    public Long getShoppingCategoryId() {
        return shoppingCategoryId;
    }
    public void setShoppingCategoryId(Long shoppingCategoryId) {
        this.shoppingCategoryId = shoppingCategoryId;
    }
    public String getShoppingCategoryName() {
        return shoppingCategoryName;
    }
    public void setShoppingCategoryName(String shoppingCategoryName) {
        this.shoppingCategoryName = shoppingCategoryName;
    }
    public String getShoppingGoodsName() {
        return shoppingGoodsName;
    }
    public void setShoppingGoodsName(String shoppingGoodsName) {
        this.shoppingGoodsName = shoppingGoodsName;
    }
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
}
