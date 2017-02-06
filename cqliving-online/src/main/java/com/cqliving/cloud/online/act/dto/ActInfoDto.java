package com.cqliving.cloud.online.act.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cqliving.cloud.online.act.domain.ActInfoList;

public class ActInfoDto {
    /** ID */
    private Long id;
    /** 客户端_ID */
    private Long appId;
    /** 标题 */
    private String title;
    /** 活动开始时间 */
    private Date startTime;
    /** 活动结束时间 */
    private Date endTime;
    /** 回复量 */
    private Integer replyCount;
    /** 点赞量 */
    private Integer priseCount;
    /** 摘要 */
    private String digest;
    /** 图片列表，只能一张 */
    private String listImageUrl;
    /** 活动列表图片，只多三张 */
    private String actImageUrl;
    /** 内容,包含HTML标签的富文本内容 */
    private String content;
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
    /** 激活状态 */
    private Byte activationState;
    /** 排序号 */
    private Integer sortNo;
    /** 排序号 */
    private Integer sortNo1;
    /** 是否推荐到首页{0:否,1:是}，首页展示 */
    private Byte isRecommend;
    /** 推荐到首页图片 */
    private String recommendImageUrl;
    
    
    /** 活动内容集合 */
    List<ActInfoList> actInfoList;
    
    private Long actInfoListId;
	/** 外链地址 */
	private String linkUrl;
    /** 参加人数 */
    private Integer joinNo;
    /** 参加人次 */
    private Integer participantsNo;
    /** 分享量 */
    private Integer shareNo;
    /** 活动类型 */
    private Byte actType;
    /** 活动类型 */
    private String actTypes;
    /** 活动显示类型 */
    private String showTypes;
    /** 活动状态 */
    private String actStatus;
    /** 活动开始时间 */
	private Date actStartTime;
	/** 活动结束时间 */
	private Date actEndTime;
	private Byte showType;
	/** 活动类型 */
	Set<String> typeSet;
	/** 活动显示类型 */
	Set<String> showTypeSet;
	
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
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public Integer getReplyCount() {
        return replyCount;
    }
    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }
    public Integer getPriseCount() {
        return priseCount;
    }
    public void setPriseCount(Integer priseCount) {
        this.priseCount = priseCount;
    }
    public String getDigest() {
        return digest;
    }
    public void setDigest(String digest) {
        this.digest = digest;
    }
    public String getListImageUrl() {
        return listImageUrl;
    }
    public void setListImageUrl(String listImageUrl) {
        this.listImageUrl = listImageUrl;
    }
    public String getActImageUrl() {
        return actImageUrl;
    }
    public void setActImageUrl(String actImageUrl) {
        this.actImageUrl = actImageUrl;
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
    public Integer getJoinNo() {
        return joinNo;
    }
    public void setJoinNo(Integer joinNo) {
        this.joinNo = joinNo;
    }
    public Integer getParticipantsNo() {
        return participantsNo;
    }
    public void setParticipantsNo(Integer participantsNo) {
        this.participantsNo = participantsNo;
    }
    public Integer getShareNo() {
        return shareNo;
    }
    public void setShareNo(Integer shareNo) {
        this.shareNo = shareNo;
    }
    public Byte getActType() {
        return actType;
    }
    public void setActType(Byte actType) {
        this.actType = actType;
    }
    public String getActStatus() {
        return actStatus;
    }
    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }
	public List<ActInfoList> getActInfoList() {
		return actInfoList;
	}
	public void setActInfoList(List<ActInfoList> actInfoList) {
		this.actInfoList = actInfoList;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Date getActStartTime() {
		return actStartTime;
	}
	public void setActStartTime(Date actStartTime) {
		this.actStartTime = actStartTime;
	}
	public Date getActEndTime() {
		return actEndTime;
	}
	public void setActEndTime(Date actEndTime) {
		this.actEndTime = actEndTime;
	}
	public Long getActInfoListId() {
		return actInfoListId;
	}
	public void setActInfoListId(Long actInfoListId) {
		this.actInfoListId = actInfoListId;
	}
    public String getActTypes() {
        return actTypes;
    }
    public void setActTypes(String actTypes) {
        this.actTypes = actTypes;
    }
    public Set<String> getTypeSet() {
        return typeSet;
    }
    public void setTypeSet(Set<String> typeSet) {
        this.typeSet = typeSet;
    }
    public Byte getActivationState() {
        return activationState;
    }
    public void setActivationState(Byte activationState) {
        this.activationState = activationState;
    }
    public Integer getSortNo() {
        return sortNo;
    }
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
    public Integer getSortNo1() {
        return sortNo1;
    }
    public void setSortNo1(Integer sortNo1) {
        this.sortNo1 = sortNo1;
    }

	public Byte getShowType() {
		return showType;
	}
	public void setShowType(Byte showType) {
		this.showType = showType;
	}
    

    public String getShowTypes() {
        return showTypes;
    }
    public void setShowTypes(String showTypes) {
        this.showTypes = showTypes;
    }
    public Set<String> getShowTypeSet() {
        return showTypeSet;
    }
    public void setShowTypeSet(Set<String> showTypeSet) {
        this.showTypeSet = showTypeSet;
    }
    public Byte getIsRecommend() {
        return isRecommend;
    }
    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }
    public String getRecommendImageUrl() {
        return recommendImageUrl;
    }
    public void setRecommendImageUrl(String recommendImageUrl) {
        this.recommendImageUrl = recommendImageUrl;
    }

}