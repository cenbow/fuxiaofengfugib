package com.cqliving.cloud.online.interfacc.dto;

import java.util.List;

public class WzQuestionDetailResult {
 
	/** 问题ID **/
	private Long questionId;
	/** 标题 **/
	private String title;
	/** 内容 **/
	private String content;
	/** 问政图片 , 多个用逗号“,”隔开 **/
    private String images;
    /** 事件类型 **/
    private Byte type;
    /** 事件类型 中文 **/
    private String typeStr;
    /** 区域CODE **/
    private String regionCode;
    /** 区域CODE **/
    private String regionName;
    /** 收集信息 **/
    private List<WzCollectInfoData> collectInfo;
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public List<WzCollectInfoData> getCollectInfo() {
		return collectInfo;
	}
	public void setCollectInfo(List<WzCollectInfoData> collectInfo) {
		this.collectInfo = collectInfo;
	}
    
}
