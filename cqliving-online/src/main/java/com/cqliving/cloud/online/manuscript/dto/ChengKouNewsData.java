package com.cqliving.cloud.online.manuscript.dto;

import java.util.List;

public class ChengKouNewsData {
	
	/** 请求详细内容的url */
	public final static String URL = "http://www.cqck.gov.cn/app/cgi/web_xhl_newscontent_%s.shtml";

	/** 新闻id */
	private Long id;
	/** 新闻栏目id */
	private String sort;
	/** 新闻名称 */
	private String title;
	/** 新闻发布时间 */
	private String pubTime;
	/** 新闻来源 */
	private String source;
	/** 新闻内容（HTML格式 图片最大宽度640像素） */
	private String contents;
	/** 新闻内容跳转URL */
	private String gotourl;
	/** 新闻类型（normal：图片新闻或者文字新闻,pics：组图类型（三张以上图片）） */
	private String type;
	/** 新闻内容中的图片URL（“,”隔开） */
	private String pics;
	/** 相关新闻 数组(最新的6条相关新闻) */
	private List<ChengKouLikeNews> likeNews;
	/** 当前新闻json内容 */
	private String jsonContent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getGotourl() {
		return gotourl;
	}
	public void setGotourl(String gotourl) {
		this.gotourl = gotourl;
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
	public List<ChengKouLikeNews> getLikeNews() {
		return likeNews;
	}
	public void setLikeNews(List<ChengKouLikeNews> likeNews) {
		this.likeNews = likeNews;
	}
	public String getJsonContent() {
		return jsonContent;
	}
	public void setJsonContent(String jsonContent) {
		this.jsonContent = jsonContent;
	}
}
