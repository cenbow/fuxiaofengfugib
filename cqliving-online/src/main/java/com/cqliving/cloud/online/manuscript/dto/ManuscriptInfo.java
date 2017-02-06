package com.cqliving.cloud.online.manuscript.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月8日
 */
public class ManuscriptInfo {
	
	/** 标题 */
	private String title;
	private String categoryUrl;
	private String link;
	private String span;
	/** 华龙网的编辑，或者是作者 */
	private String editor;
	/** 数据源id，唯一 */
	private String guid;
	/** 列表图 */
	private String img;
	private String images;
	/** 描述 */
	private String description;
	/** 如果是多图类型的时候，解析content中的img标签，图片的描述去img标签的alt属性的内容，如果alt为空就取 description字段的内容 */
	private String content;
	private String category;
	private String author;
	/** 发布时间，只有年月日时分，没有秒哦 */
	private String pubDate;
	/** 音、视频流地址（处理办法：把标签内容字符串中的“ld”替换成“http”，再去掉“/vod”即可） */
	private String media;
	/** 来源 */
	private String source;
	/** item整个节点的xml */
	private String XMLCONTENT;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategoryUrl() {
		return categoryUrl;
	}
	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getSpan() {
		return span;
	}
	public void setSpan(String span) {
		this.span = span;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getXMLCONTENT() {
		return XMLCONTENT;
	}
	public void setXMLCONTENT(String xMLCONTENT) {
		XMLCONTENT = xMLCONTENT;
	}
	
}
