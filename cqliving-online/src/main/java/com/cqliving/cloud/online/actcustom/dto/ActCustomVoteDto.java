package com.cqliving.cloud.online.actcustom.dto;

import java.util.Date;

public class ActCustomVoteDto {
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 生成二维码的URL地址，在生成之前需要在该url后面拼上用户token和活动code */
	private String url;
	/** 活动名称 */
	private String name;
	/** 活动CODE，生成一个UUID */
	private String code;
	/** 回复源类型 */
	private Byte sourceType;
	/** source_type=1时，对应info_classify表的主键。source_type=6时，对应act_info_list表的主键 */
	private Long sourceId;
	/** 活动开始时间 */
	private Date startTime;
	/** 活动结束时间 */
	private Date endTime;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 活动类型 */
	private Byte type;
	/** 活动banner图片地址*/
	private String bannerImageUrl;
	/** 活动 投票限制类型*/
	private Byte voteLimitType;
	/** 活动投票限制值 */
	private Long voteLimitValue;
	/**参加人数*/
	private Long userNum;
	/**总投票数*/
	private Long voteTatalNum;
	public Long getVoteTatalNum() {
		return voteTatalNum;
	}
	public void setVoteTatalNum(Long voteTatalNum) {
		this.voteTatalNum = voteTatalNum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Byte getSourceType() {
		return sourceType;
	}
	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdatorId() {
		return updatorId;
	}
	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public String getBannerImageUrl() {
		return bannerImageUrl;
	}
	public void setBannerImageUrl(String bannerImageUrl) {
		this.bannerImageUrl = bannerImageUrl;
	}
	public Byte getVoteLimitType() {
		return voteLimitType;
	}
	public void setVoteLimitType(Byte voteLimitType) {
		this.voteLimitType = voteLimitType;
	}
	public Long getVoteLimitValue() {
		return voteLimitValue;
	}
	public void setVoteLimitValue(Long voteLimitValue) {
		this.voteLimitValue = voteLimitValue;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	
}
