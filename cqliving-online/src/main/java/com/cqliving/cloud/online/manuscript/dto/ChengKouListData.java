package com.cqliving.cloud.online.manuscript.dto;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Title:城口xml列表对象
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月27日
 */
public class ChengKouListData {
	
	/** 图片新闻或者文字新闻 */
	public final static String TYPE_NORMAL = "normal";
	/** 组图类型（三张以上图片） */
	public final static String TYPE_PICS = "pics";
	/** 新闻类型 */
	public final static Map<String, String> allTypes = Maps.newTreeMap();
	static{
		allTypes.put(TYPE_NORMAL, "normal");
		allTypes.put(TYPE_PICS, "pics");
	}

	/** 新闻ID */
	private Long id;
	/** 新闻发布时间 */
	private String date;
	/** 新闻标题 */
	private String title;
	/** 内容简介 */
	private String intro;
	/** 新闻内容地址（HTML5版本） */
	private String link;
	/** 新闻封面图片 */
	private String img;
	/** 新闻类型（normal：图片新闻或者文字新闻,pics：组图类型（三张以上图片）） */
	private String type;
	/** 新闻图片（新闻全部图片逗号隔开） */
	private String pics;
	/** 该新闻的评论条数 */
	private Long comment;
	/** 新闻的具体内容 */
	private ChengKouNewsData newsData;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public Long getComment() {
		return comment;
	}
	public void setComment(Long comment) {
		this.comment = comment;
	}
	public ChengKouNewsData getNewsData() {
		return newsData;
	}
	public void setNewsData(ChengKouNewsData newsData) {
		this.newsData = newsData;
	}
}
