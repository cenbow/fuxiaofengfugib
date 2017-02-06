package com.cqliving.cloud.online.order.dto;


import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单商品评价dto
 * Date: 2016-11-23 17:32:16
 * @author Code Generator
 */
public class OrderEvaluateDto {
    
	/** id */
	private Long id;
	/** 客户端_id */
	private Long appId;
	/** 订单id */
	private Long orderId;
	/** 商品id */
	private Long goodsId;
	/** 用户id */
	private Long userId;
	/** 内容 */
	private String content;
	/** 商品评价，1颗星20分，最多5颗星100分 */
	private Integer goodsScore;
	/** 评价图片地址，最多5张，用逗号分开 */
	private String imageUrls;
	/** 评价列表图片地址，最多5张，用逗号分开 */
	private String listImageUrls;
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
	/** 更新人id */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 审核时间 */
	private Date aduitTime;
	/** 审核人ID */
	private Long aduitUserId;
	
    /** 商品名 */
    private String shoppingGoodsName;
    /** 用户头像 */
    private String avatar;
    /** 用户名 */
    private String userName;
    /**用于排序的状态*/
    private Byte statusNew;
    /**审核人*/
    private String aduitor;
    /** 审核描述 */
    private String auditingContent;
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Long getOrderId(){
		return this.orderId;
	}
	
	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}
	public Long getGoodsId(){
		return this.goodsId;
	}
	
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public Integer getGoodsScore(){
		return this.goodsScore;
	}
	
	public void setGoodsScore(Integer goodsScore){
		this.goodsScore = goodsScore;
	}
	public String getImageUrls(){
		return this.imageUrls;
	}
	
	public void setImageUrls(String imageUrls){
		this.imageUrls = imageUrls;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Long getUpdatorId(){
		return this.updatorId;
	}
	
	public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	public Date getAduitTime(){
		return this.aduitTime;
	}
	
	public void setAduitTime(Date aduitTime){
		this.aduitTime = aduitTime;
	}
	public Long getAduitUserId(){
		return this.aduitUserId;
	}
	
	public void setAduitUserId(Long aduitUserId){
		this.aduitUserId = aduitUserId;
	}

    public String getShoppingGoodsName() {
        return shoppingGoodsName;
    }

    public void setShoppingGoodsName(String shoppingGoodsName) {
        this.shoppingGoodsName = shoppingGoodsName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Byte getStatusNew() {
        return statusNew;
    }

    public void setStatusNew(Byte statusNew) {
        this.statusNew = statusNew;
    }

    public String getAduitor() {
        return aduitor;
    }

    public void setAduitor(String aduitor) {
        this.aduitor = aduitor;
    }

    public String getAuditingContent() {
        return auditingContent;
    }

    public void setAuditingContent(String auditingContent) {
        this.auditingContent = auditingContent;
    }

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getListImageUrls() {
		return listImageUrls;
	}

	public void setListImageUrls(String listImageUrls) {
		this.listImageUrls = listImageUrls;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}