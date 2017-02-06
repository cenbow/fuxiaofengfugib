/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.act.dto;

import java.util.Date;
import java.util.List;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月24日
 */
public class ActVoteClassifyDto {

	/** ID */
	private Long id;
	/** 活动ID（act_info表主键） */
	private Long actInfoId;
	/** 活动集合表ID（act_info_list表主键） */
	private Long actInfoListId;
	/** 活动投票表ID（act_vote表主键） */
	private Long actVoteId;
	/** 分类标题，不超过8个字 */
	private String title;
	/** 分类主题，不超过50个字  */
	private String subject;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	/** 1:图片投票 */
	private Byte isImageVote;
	
	private List<ActVoteItemDto> actVoteItems;

	
	/** ---------以下为投票项字段 ------------- */
	/** ID */
	private Long voteItemId;
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
	private Integer voteItemSortNo;
	/** 创建时间 */
	private Date voteItemCreateTime;
	/** 七牛资源表 */
	private Long infoFileId;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActInfoId() {
		return actInfoId;
	}

	public void setActInfoId(Long actInfoId) {
		this.actInfoId = actInfoId;
	}

	public Long getActInfoListId() {
		return actInfoListId;
	}

	public void setActInfoListId(Long actInfoListId) {
		this.actInfoListId = actInfoListId;
	}

	public Long getActVoteId() {
		return actVoteId;
	}

	public void setActVoteId(Long actVoteId) {
		this.actVoteId = actVoteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public List<ActVoteItemDto> getActVoteItems() {
		return actVoteItems;
	}

	public void setActVoteItems(List<ActVoteItemDto> actVoteItems) {
		this.actVoteItems = actVoteItems;
	}

	public Long getVoteItemId() {
		return voteItemId;
	}

	public void setVoteItemId(Long voteItemId) {
		this.voteItemId = voteItemId;
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

	public Integer getVoteItemSortNo() {
		return voteItemSortNo;
	}

	public void setVoteItemSortNo(Integer voteItemSortNo) {
		this.voteItemSortNo = voteItemSortNo;
	}

	public Date getVoteItemCreateTime() {
		return voteItemCreateTime;
	}

	public void setVoteItemCreateTime(Date voteItemCreateTime) {
		this.voteItemCreateTime = voteItemCreateTime;
	}

	public Long getInfoFileId() {
		return infoFileId;
	}

	public void setInfoFileId(Long infoFileId) {
		this.infoFileId = infoFileId;
	}

	public Byte getIsImageVote() {
		return isImageVote;
	}

	public void setIsImageVote(Byte isImageVote) {
		this.isImageVote = isImageVote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActVoteClassifyDto other = (ActVoteClassifyDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
