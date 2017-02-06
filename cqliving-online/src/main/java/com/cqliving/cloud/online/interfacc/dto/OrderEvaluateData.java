package com.cqliving.cloud.online.interfacc.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title: 商品评价列表
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月1日
 */
public class OrderEvaluateData {

	/** id */
	private Long id;
	/** 内容 */
	private String content;
	/** 商品评价星星数（1-5） */
	private Integer goodsScore;
	/** 评价图片地址，最多5张，用逗号分开 */
	private String imageUrls;
	/** 评价列表图片地址，最多5张，用逗号分开 */
	private String listImageUrls;
	/** 创建时间 */
	private String createTime;
	/** 创建人姓名 */
	private String userName;
	/** 创建人头像 */
	private String avatar;
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGoodsScore() {
		return goodsScore;
	}

	public void setGoodsScore(Integer goodsScore) {
		this.goodsScore = goodsScore;
	}

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getListImageUrls() {
		return listImageUrls;
	}

	public void setListImageUrls(String listImageUrls) {
		this.listImageUrls = listImageUrls;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
