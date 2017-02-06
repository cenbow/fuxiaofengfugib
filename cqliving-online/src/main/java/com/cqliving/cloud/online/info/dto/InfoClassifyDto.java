package com.cqliving.cloud.online.info.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.cqliving.cloud.common.InformationUtil;

public class InfoClassifyDto {

	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 资讯ID */
	private Long informationId;
	/** 栏目ID */
	private Long columnsId;
	/** 栏目新闻显示状态 */
	private Byte classfieViewStatus;
	/** 排序号 */
	private Integer sortNo;
	/** 列表显示类型 */
	private Byte listViewType;
	/** 资讯浏览量 */
	private Integer viewCount;
	/** 资讯回复量 */
	private Integer replyCount;
	/** 源APPID */
	private Long sourceAppId;
	/** 源新闻ID */
	private Long sourceInformationId;
	/** 资讯标题 */
	private String title;
	/** 资讯列表标题 */
	private String listTitle;
	/** 专题主题 */
	private String specialTheme;
	/** 状态 */
	private Byte status;
	/** 上线时间 */
	private Date onlineTime;
	/** 上线时间年月日 */
	private Date onlineTimeDate;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	/** 编辑人姓名 */
	private String updator;
	/** 是否已加入专题 */
	private Byte addSpecialStatus;
	/** 更新时间 */
	private Date updateTime;
	/** 华龙网新闻ID，如果是抓稿过来的字段，则该字段不为空。同manuscript_info_classify表的old_guid字段 */
	private String hlwOldGuid;
	
	//------------------------ 冗余字段 --------------------------
	
	/** 类型 */
	private Byte type;
	/** 新闻内容类型 */
	private Byte contextType;
	/** 栏目名称 */
	private String columnsName;
	/** 来源网站 */
	private String infoSource;
	/** 总评论量 */
	private Integer totalReplyCount;
	/** 总浏览量 */
	private Integer totalViewCount;
	/** 列表图片，逗号分隔 */
	private String imgUrls;
	/** 资讯标签,多个用,分隔，注意  前边也要带,号，例   ,1,2, */
	private String infoLabel;
	/** 推送状态 */
	private Byte sendStatus;
	/** 评论需审核 */
	private Byte commentValidateType;
	/** 内容需审核 */
	private Byte validateType;
	/** 允许评论 */
	private Byte commentType;
	/** 推荐处理状态 */
	private Byte recommendStatus;
	/** 源推荐 AppId */
	private Long sourceRecommendAppId;
	/** 推荐目标 AppId */
	private Long targetAppId;
	/** 推荐时间 */
	private Date commentTime;
	/** 相关咨询表 id */
	private Long correlationId;
	/** 专题分类 id */
	private Long themeId;
	/** 专题分类名称 */
	private String themeName;
	/** 推荐表 id */
	private Long commentId;
	/** 关键字 */
	private String keywords;
	/** 资讯内容的全文本，不带HTML标签的 */
	private String contentText;
	/** 资讯回复量 */
	private Integer infoReplyCount;
	/** 内容URL */
	private String contentUrl;
	/** 状态排序号 */
	private Integer statusNo;
	/** 收藏 id */
	private Long userFavoriteId;
	/** 收藏状态 */
	private Byte userFavoriteStatus;
	/** 用户 id */
	private Long userId;
	/** 收藏类型 */
	private Byte sourceType;
	/** 多图新闻图片数量 */
	private Integer multipleImgCount;
	/** 初始阅读量 */
	private Long initCount;
	/** 阅读量增加类型 */
	private Byte addType;
	/** 达到峰值时间，以秒为单位 */
	private Integer topTime;
	/** 详情浏览量 */
	private Integer detailViewCount;
	/** 摘要 */
	private String synopsis;
	/** 是否推荐 **/
	private Byte isRecommend;
	
	//相关新闻的主新闻ID
	private Long infoClassifyId;
	private Long refInfoClassifyId;
	//关联排序号
	private Integer correSortNo;
	//分享标题
	private String shareTitle;
	
	public Integer getCorreSortNo() {
		return correSortNo;
	}

	public void setCorreSortNo(Integer correSortNo) {
		this.correSortNo = correSortNo;
	}

	public Byte getValidateType() {
		return validateType;
	}

	public void setValidateType(Byte validateType) {
		this.validateType = validateType;
	}

	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Long getInformationId(){
		return this.informationId;
	}
	
	public void setInformationId(Long informationId){
		this.informationId = informationId;
	}
	public Long getColumnsId(){
		return this.columnsId;
	}
	
	public void setColumnsId(Long columnsId){
		this.columnsId = columnsId;
	}
	public Byte getClassfieViewStatus(){
		return this.classfieViewStatus;
	}
	
	public void setClassfieViewStatus(Byte classfieViewStatus){
		this.classfieViewStatus = classfieViewStatus;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public Byte getListViewType(){
		return this.listViewType;
	}
	
	public void setListViewType(Byte listViewType){
		this.listViewType = listViewType;
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

	public Long getSourceAppId() {
		return sourceAppId;
	}

	public void setSourceAppId(Long sourceAppId) {
		this.sourceAppId = sourceAppId;
	}

	public Long getSourceInformationId() {
		return sourceInformationId;
	}

	public void setSourceInformationId(Long sourceInformationId) {
		this.sourceInformationId = sourceInformationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getOnlineTimeDate() {
		return onlineTimeDate;
	}

	public void setOnlineTimeDate(Date onlineTimeDate) {
		this.onlineTimeDate = onlineTimeDate;
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

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getContextType() {
		return contextType;
	}

	public void setContextType(Byte contextType) {
		this.contextType = contextType;
	}

	public String getColumnsName() {
		return columnsName;
	}

	public void setColumnsName(String columnsName) {
		this.columnsName = columnsName;
	}

	public String getInfoSource() {
		return infoSource;
	}

	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}

	public Integer getTotalReplyCount() {
		return totalReplyCount;
	}

	public void setTotalReplyCount(Integer totalReplyCount) {
		this.totalReplyCount = totalReplyCount;
	}

	public Integer getTotalViewCount() {
		return totalViewCount;
	}

	public void setTotalViewCount(Integer totalViewCount) {
		this.totalViewCount = totalViewCount;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(String infoLabel) {
		this.infoLabel = infoLabel;
	}

	public Byte getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Byte sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Byte getCommentValidateType() {
		return commentValidateType;
	}

	public void setCommentValidateType(Byte commentValidateType) {
		this.commentValidateType = commentValidateType;
	}

	public Byte getCommentType() {
		return commentType;
	}

	public void setCommentType(Byte commentType) {
		this.commentType = commentType;
	}

	public Byte getRecommendStatus() {
		return recommendStatus;
	}

	public void setRecommendStatus(Byte recommendStatus) {
		this.recommendStatus = recommendStatus;
	}

	public Long getSourceRecommendAppId() {
		return sourceRecommendAppId;
	}

	public void setSourceRecommendAppId(Long sourceRecommendAppId) {
		this.sourceRecommendAppId = sourceRecommendAppId;
	}

	public Long getTargetAppId() {
		return targetAppId;
	}

	public void setTargetAppId(Long targetAppId) {
		this.targetAppId = targetAppId;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Byte getAddSpecialStatus() {
		return addSpecialStatus;
	}

	public void setAddSpecialStatus(Byte addSpecialStatus) {
		this.addSpecialStatus = addSpecialStatus;
	}

	public Long getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(Long correlationId) {
		this.correlationId = correlationId;
	}

	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
     
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
    
	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public Long getInfoClassifyId() {
		return infoClassifyId;
	}

	public void setInfoClassifyId(Long infoClassifyId) {
		this.infoClassifyId = infoClassifyId;
	}

	public Long getRefInfoClassifyId() {
		return refInfoClassifyId;
	}

	public void setRefInfoClassifyId(Long refInfoClassifyId) {
		this.refInfoClassifyId = refInfoClassifyId;
	}
   
	public Integer getInfoReplyCount() {
		return infoReplyCount;
	}

	public void setInfoReplyCount(Integer infoReplyCount) {
		this.infoReplyCount = infoReplyCount;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public Integer getStatusNo() {
		return statusNo;
	}

	public void setStatusNo(Integer statusNo) {
		this.statusNo = statusNo;
	}

	public Long getUserFavoriteId() {
		return userFavoriteId;
	}

	public void setUserFavoriteId(Long userFavoriteId) {
		this.userFavoriteId = userFavoriteId;
	}

	public Byte getUserFavoriteStatus() {
		return userFavoriteStatus;
	}

	public void setUserFavoriteStatus(Byte userFavoriteStatus) {
		this.userFavoriteStatus = userFavoriteStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getMultipleImgCount() {
		return multipleImgCount;
	}

	public void setMultipleImgCount(Integer multipleImgCount) {
		this.multipleImgCount = multipleImgCount;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getInitCount() {
		return initCount;
	}

	public void setInitCount(Long initCount) {
		this.initCount = initCount;
	}

	public Byte getAddType() {
		return addType;
	}

	public void setAddType(Byte addType) {
		this.addType = addType;
	}

	public Integer getTopTime() {
		return topTime;
	}

	public void setTopTime(Integer topTime) {
		this.topTime = topTime;
	}

	public Integer getDetailViewCount() {
		return detailViewCount;
	}

	public void setDetailViewCount(Integer detaillViewCount) {
		this.detailViewCount = detaillViewCount;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Byte getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Byte isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getHlwOldGuid() {
		return hlwOldGuid;
	}

	public void setHlwOldGuid(String hlwOldGuid) {
		this.hlwOldGuid = hlwOldGuid;
	}

	public String getShareTitle() {
		shareTitle = InformationUtil.getShareTitle(this.appId, this.title);
		return this.shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getListTitle() {
		return listTitle;
	}

	public void setListTitle(String listTitle) {
		this.listTitle = listTitle;
	}

	public String getSpecialTheme() {
		return specialTheme;
	}

	public void setSpecialTheme(String specialTheme) {
		this.specialTheme = specialTheme;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
