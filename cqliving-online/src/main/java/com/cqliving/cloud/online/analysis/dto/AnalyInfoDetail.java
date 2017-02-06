package com.cqliving.cloud.online.analysis.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.cqliving.tool.common.util.file.Export;

/**
 * 编辑分栏目统计表 Entity
 * Date: 2016-06-22 10:29:43
 * @author Code Generator
 */
public class AnalyInfoDetail {
	
	//新闻内容ID
	private Long informationId;
	/** APP_id */
	private Long appId;
	/** 编辑登陆名 */
	@Export(name = "编辑登陆名",order = 0)
	private String userName;
	/** 编辑姓名 */
	@Export(name = "编辑姓名",order = 1)
	private String nickname;
	/** 栏目名称 */
	@Export(name = "栏目名称",order = 2)
	private String columnsName;
	/** 新闻标题 */
	@Export(name = "新闻标题",order = 3)
	private String title;
	/** 上线时间 */
	@Export(name = "上线时间",order = 4)
	private Date onlineTime;
	/** 浏览总量 */
	@Export(name = "当前浏览量",order = 5)
	private Integer viewTotalCount;
	/** 评论总数量 */
	@Export(name = "当前回复量",order = 6)
	private Integer replyCount;
	/** 参与活动数量 */
	@Export(name = "当前活动参与量",order = 7)
	private Integer activeCount;
	
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getColumnsName() {
		return columnsName;
	}

	public void setColumnsName(String columnsName) {
		this.columnsName = columnsName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getViewTotalCount() {
		return viewTotalCount;
	}

	public void setViewTotalCount(Integer viewTotalCount) {
		this.viewTotalCount = viewTotalCount;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(Integer activeCount) {
		this.activeCount = activeCount;
	}
    
	public Long getInformationId() {
		return informationId;
	}

	public void setInformationId(Long informationId) {
		this.informationId = informationId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
