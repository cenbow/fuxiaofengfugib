package com.cqliving.cloud.online.analysis.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.cqliving.tool.common.util.file.Export;

/**
 * 编辑分栏目统计表 Entity
 * Date: 2016-06-22 10:29:43
 * @author Code Generator
 */
public class AnalyInfoColumns {
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
	/** 发贴量 */
	@Export(name = "发贴量",order = 3)
	private Integer infoCount;
	/** 浏览总量 */
	@Export(name = "当前浏览量",order = 4)
	private Integer viewTotalCount;
	/** 评论总数量 */
	@Export(name = "当前回复量",order = 5)
	private Integer replyCount;
	/** 参与活动数量 */
	@Export(name = "当前参与量",order = 6)
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
