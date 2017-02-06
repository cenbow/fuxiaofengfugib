package com.cqliving.cloud.online.actcustom.dto;

import java.util.Date;

public class ActCustomVoteItemDto {

	/** ID */
	private Long id;
	/** 用户二维码扫描活动表，表act_qrcode的code字估 */
	private String actQrcodeCode;
	/** 选项编号 */
	private String number;
	/** 选项标题，后台限制最多80个字 */
	private String itemTitle;
	/** 选项描述 */
	private String itemDesc;
	/** 实际量 */
	private Integer actValue;
	/** 选项图片 */
	private String imageUrl;
	/** 视频URL */
	private String videoUrl;
	/** 内容,包含HTML标签的富文本内容 */
	private String content;
	/** 状态 */
	private Byte status;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	/**名次*/
	private Long ranking;
	public Long getRanking() {
		return ranking;
	}
	public void setRanking(Long ranking) {
		this.ranking = ranking;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActQrcodeCode() {
		return actQrcodeCode;
	}
	public void setActQrcodeCode(String actQrcodeCode) {
		this.actQrcodeCode = actQrcodeCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public Integer getActValue() {
		return actValue;
	}
	public void setActValue(Integer actValue) {
		this.actValue = actValue;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
