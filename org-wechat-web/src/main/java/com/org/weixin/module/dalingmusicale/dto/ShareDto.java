/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.weixin.module.dalingmusicale.constant.MusicaleConstant;
import com.org.weixin.module.dalingmusicale.domain.UserShareHis;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月18日
 */
public class ShareDto {

	String shareUrl;
	String shareTitle;
	String shareImg;
	String shareContent;
	Byte shareType;
	
	private static Logger logger = LoggerFactory.getLogger(ShareDto.class);
	
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	public String getShareImg() {
		return shareImg;
	}
	public void setShareImg(String shareImg) {
		this.shareImg = shareImg;
	}
	public String getShareContent() {
		return shareContent;
	}
	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}
	public Byte getShareType() {
		return shareType;
	}
	public void setShareType(Byte shareType) {
		this.shareType = shareType;
	}
	public static ShareDto getMusicaleShare(){
		ShareDto shareDto = new ShareDto();
		shareDto.setShareContent(MusicaleConstant.MUSICALE_SHARE_CONTENT);
		shareDto.setShareImg(MusicaleConstant.MUSICALE_SHARE_IMG);
		shareDto.setShareTitle(MusicaleConstant.MUSICALE_SHARE_TITLE);
		shareDto.setShareUrl(MusicaleConstant.MUSICALE_SHARE_URL);
		shareDto.setShareType(UserShareHis.SHARETYPE2);
		
		logger.info("-----------------分享对象信息：{}",shareDto);
		return shareDto;
	}
	
	public static ShareDto getVoteShare(){
		ShareDto shareDto = new ShareDto();
		shareDto.setShareContent(MusicaleConstant.VOTE_SHARE_CONTENT);
		shareDto.setShareImg(MusicaleConstant.VOTE_SHARE_IMG);
		shareDto.setShareTitle(MusicaleConstant.VOTE_SHARE_TITLE);
		shareDto.setShareUrl(MusicaleConstant.VOTE_SHARE_URL);
		shareDto.setShareType(UserShareHis.SHARETYPE1);
		
		logger.info("-----------------分享对象信息：{}",shareDto);
		return shareDto;
	}
	
	public static ShareDto getChampionShare(){
		
		ShareDto shareDto = new ShareDto();
		shareDto.setShareContent(MusicaleConstant.COMPETITION_SHARE_CONTENT);
		shareDto.setShareImg(MusicaleConstant.COMPETITION_SHARE_IMG);
		shareDto.setShareTitle(MusicaleConstant.COMPETITION_SHARE_TITLE);
		shareDto.setShareUrl(MusicaleConstant.COMPETITION_SHARE_URL);
		shareDto.setShareType(UserShareHis.SHARETYPE3);
		
		logger.info("-----------------现场投票分享对象信息：{}",shareDto);
		return shareDto;
	}
	
	@Override
	public String toString() {
		return "ShareDto [shareUrl=" + shareUrl + ", shareTitle=" + shareTitle + ", shareImg=" + shareImg
				+ ", shareContent=" + shareContent + ", shareType=" + shareType + "]";
	}
}
