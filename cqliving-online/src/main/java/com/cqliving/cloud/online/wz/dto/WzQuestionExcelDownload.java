package com.cqliving.cloud.online.wz.dto;

import java.util.Date;

import com.cqliving.tool.common.util.file.Export;

/**
 * Title:问政Excel导出
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年10月18日
 */
public class WzQuestionExcelDownload {
	/** ID */
	@Export(name="ID", order=0)
	private Long id;
	/** 事件类型 */
	@Export(name="事件类型", order=2, json="{1:建言献策,2:投诉举报,3:咨询求助,4:其他}")
	private Byte type;
	/** 状态 */
	@Export(name="状态", order=3, json="{-1:审核不通过,2:未审核,3:审核通过待处理,4:转交中,5:已处理待发布,6:已驳回,7:已发布,88:已下线}")
	private Byte status;
	/** 所属区域名称 */
	@Export(name="区域名称", order=4)
	private String regionName;
	/** 标题 */
	@Export(name="标题", order=6)
	private String title;
	/** 内容 */
	@Export(name="内容", order=7)
	private String content;
	/** 资讯浏览量 */
	@Export(name="浏览量", order=8)
	private Integer viewCount;
	/** 资讯回复量 */
	@Export(name="回复量", order=9)
	private Integer replyCount;
	/** 创建时间 */
	@Export(name="创建时间", order=10)
	private Date createTime;
	/** 创建人名称 */
	@Export(name="创建人", order=11)
	private String creator;
	/** 用户手机号 **/
	@Export(name="创建人手机号", order=12)
	private String creatorPhone;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
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
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public Integer getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatorPhone() {
		return creatorPhone;
	}
	public void setCreatorPhone(String creatorPhone) {
		this.creatorPhone = creatorPhone;
	}
	
}
