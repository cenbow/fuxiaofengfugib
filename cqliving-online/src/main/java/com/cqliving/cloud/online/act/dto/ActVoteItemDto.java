/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.act.dto;

import java.util.Date;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年8月4日
 */
public class ActVoteItemDto {

	/** ID */
	private Long id;
	/** 活动投票分类表ID（act_vote_classify表主键） */
	private Long actVoteClassifyId;
	/** 选项编号 */
	private String number;
	/** 选项标题，后台限制最多80个字 */
	private String itemTitle;
	/** 选项描述 */
	private String itemDesc;
	/** 实际量 */
	private Integer actValue;
	/** 初始量 */
	private Integer initValue;
	/** 选项图片 */
	private String imageUrl;
	/** 视频URL */
	private String videoUrl;
	/** 内容,包含HTML标签的富文本内容 */
	private String content;
	/** 状态 */
	private Byte status;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	/** 七牛资源表 */
	private Long infoFileId;
	
	private String qiniuHash;
	private String qiniuKey;
	private String originalName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getActVoteClassifyId() {
		return actVoteClassifyId;
	}
	public void setActVoteClassifyId(Long actVoteClassifyId) {
		this.actVoteClassifyId = actVoteClassifyId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public Integer getActValue() {
		return actValue;
	}
	public void setActValue(Integer actValue) {
		this.actValue = actValue;
	}
	public Integer getInitValue() {
		return initValue;
	}
	public void setInitValue(Integer initValue) {
		this.initValue = initValue;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getInfoFileId() {
		return infoFileId;
	}
	public void setInfoFileId(Long infoFileId) {
		this.infoFileId = infoFileId;
	}
	public String getQiniuHash() {
		return qiniuHash;
	}
	public void setQiniuHash(String qiniuHash) {
		this.qiniuHash = qiniuHash;
	}
	public String getQiniuKey() {
		return qiniuKey;
	}
	public void setQiniuKey(String qiniuKey) {
		this.qiniuKey = qiniuKey;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
}
