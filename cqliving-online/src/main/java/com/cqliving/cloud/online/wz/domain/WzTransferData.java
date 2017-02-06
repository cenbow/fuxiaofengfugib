package com.cqliving.cloud.online.wz.domain;

import java.util.Date;

public class WzTransferData {

	/** ID */
	private Long id;
	/** 问题ID */
	private Long questionId;
	/** 问题操作后状态 */
	private Byte status;
	/** 处理结果,当状态为status=3时有效 */
	private Byte result;
	/** 创建时间 */
	private Date createTime;
	/** 创建人名称 */
	private String creator;
	/** 转交人ID，即本条记录创建人ID */
	private Long creatorId;
	/** 回复内容 */
	private String replayContent;
	/** 描述 */
	private String description;
	

	/** 待处理人 */
	private WzUser wzUser;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getQuestionId() {
		return questionId;
	}


	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}


	public Byte getStatus() {
		return status;
	}


	public void setStatus(Byte status) {
		this.status = status;
	}


	public Byte getResult() {
		return result;
	}


	public void setResult(Byte result) {
		this.result = result;
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


	public Long getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getReplayContent() {
		return replayContent;
	}


	public void setReplayContent(String replayContent) {
		this.replayContent = replayContent;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public WzUser getWzUser() {
		return wzUser;
	}


	public void setWzUser(WzUser wzUser) {
		this.wzUser = wzUser;
	}
	
}
