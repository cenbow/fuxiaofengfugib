package com.cqliving.cloud.online.act.dto;

import java.util.Date;
import java.util.List;

/**
 * Title:活动答题详情接口需要的数据
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月22日
 */
public class ActAnswerResult {
	
	/** 活动信息ID**/
	private Long actInfoId;
	/** 活动答题 ID **/
	private Long actTestId;
	/** 活动标题 **/
	private String title;
	/** 活动摘要 **/
	private String digest;
	/** 活动内容 **/
	private String content;
	/** 活动图片 **/
	private List<String> actImageUrls;
	/** 活动收集信息  **/
	private List<ActCollectInfoResult> actCollectInfoList;
	/** 用户答题信息  **/
	private UserActTestClassifyResult userClassify;
	/** 活动开始时间 **/
	private String startTimeStr;
	/** 活动结束时间 **/
	private String endTimeStr;
	/** 活动开始时间 **/
	private Date startTime;
	/** 活动结束时间 **/
	private Date endTime;
	/** 是否需要登录(0:否,1:是) **/
	private Byte isLogin;
	
	public Long getActInfoId() {
		return actInfoId;
	}
	public void setActInfoId(Long actInfoId) {
		this.actInfoId = actInfoId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getActImageUrls() {
		return actImageUrls;
	}
	public void setActImageUrls(List<String> actImageUrls) {
		this.actImageUrls = actImageUrls;
	}
	public List<ActCollectInfoResult> getActCollectInfoList() {
		return actCollectInfoList;
	}
	public void setActCollectInfoList(List<ActCollectInfoResult> actCollectInfoList) {
		this.actCollectInfoList = actCollectInfoList;
	}
	public UserActTestClassifyResult getUserClassify() {
		return userClassify;
	}
	public void setUserClassify(UserActTestClassifyResult userClassify) {
		this.userClassify = userClassify;
	}
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public String getEndTimeStr() {
		return endTimeStr;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getActTestId() {
		return actTestId;
	}
	public void setActTestId(Long actTestId) {
		this.actTestId = actTestId;
	}
	public Byte getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Byte isLogin) {
		this.isLogin = isLogin;
	}
}
