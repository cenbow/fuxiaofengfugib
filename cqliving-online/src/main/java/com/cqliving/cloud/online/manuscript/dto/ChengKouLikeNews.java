package com.cqliving.cloud.online.manuscript.dto;

/**
 * Title:新闻下的相关新闻
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月27日
 */
public class ChengKouLikeNews {

	/** 新闻id */
	private Long id;
	/** 新闻名称 */
	private String title;
	/** 新闻发布时间 */
	private String pubTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubTime() {
		return pubTime;
	}
	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
}
