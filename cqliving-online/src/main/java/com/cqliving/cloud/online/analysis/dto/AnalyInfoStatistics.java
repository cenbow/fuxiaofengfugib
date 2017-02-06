package com.cqliving.cloud.online.analysis.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.cqliving.tool.common.util.file.Export;

/**
 * 编辑分栏目统计表 Entity
 * Date: 2016-06-22 10:29:43
 * @author Code Generator
 */
public class AnalyInfoStatistics {
	/** APP_id */
	private Long appId;
	/** 编辑登陆名 */
	@Export(name = "编辑登陆名",order = 0)
	private String userName;
	/** 编辑姓名 */
	@Export(name = "编辑姓名",order = 1)
	private String nickname;
	/** 新闻数量 */
	@Export(name = "新闻数量",order = 2)
	private Integer infoCount;
	/** 浏览总量 */
	@Export(name = "浏览总量",order = 3)
	private Integer viewTotalCount;
	/** 浏览人数 */
	@Export(name = "浏览人数",order = 4)
	private Integer viewUserCount;
	/** 评论总数量 */
	@Export(name = "评论总数量",order = 5)
	private Integer replyCount;
	/** 评论总人数 */
	@Export(name = "评论总人数",order = 6)
	private Integer replyUserCount;
	/** 参与活动数量 */
	@Export(name = "参与活动数量",order = 7)
	private Integer activeCount;
	/** 参与活动人数 */
	@Export(name = "参与活动人数",order = 8)
	private Integer activeUserCount;

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

	public Integer getInfoCount() {
		return infoCount;
	}

	public void setInfoCount(Integer infoCount) {
		this.infoCount = infoCount;
	}

	public Integer getViewTotalCount() {
		return viewTotalCount;
	}

	public void setViewTotalCount(Integer viewTotalCount) {
		this.viewTotalCount = viewTotalCount;
	}

	public Integer getViewUserCount() {
		return viewUserCount;
	}

	public void setViewUserCount(Integer viewUserCount) {
		this.viewUserCount = viewUserCount;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getReplyUserCount() {
		return replyUserCount;
	}

	public void setReplyUserCount(Integer replyUserCount) {
		this.replyUserCount = replyUserCount;
	}

	public Integer getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(Integer activeCount) {
		this.activeCount = activeCount;
	}

	public Integer getActiveUserCount() {
		return activeUserCount;
	}

	public void setActiveUserCount(Integer activeUserCount) {
		this.activeUserCount = activeUserCount;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
