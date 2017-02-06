package com.cqliving.cloud.online.info.dto;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.survey.dto.SurveyInfoDto;

public class InformationDto {

	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 资讯标签,多个用,分隔，注意  前边也要带,号，例   ,1,2 */
	private String infoLabel;
	/** 新闻摘要 */
	private String synopsis;
	/** 类型 */
	private Byte type;
	/** 关键字 */
	private String keywords;
	/** 来源网站，文字 */
	private String infoSource;
	/** 平台可见类型 */
	private String plViewType;
	/** 允许评论 */
	private Byte commentType;
	/** 评论需审核 */
	private Byte commentValidateType;
	/** 内容需审核 */
	private Byte validateType;
	/** 初始阅读量 */
	private Long initCount;
	/** 阅读量增加类型 */
	private Byte addType;
	/** 达到峰值时间，以秒为单位 */
	private Integer topTime;
	/** 资讯浏览量 */
	private Integer viewCount;
	/** 资讯回复量 */
	private Integer replyCount;
	/** 推送状态 */
	private Byte sendStatus;
	/** 内容,包含HTML标签的富文本内容 */
	private String content;
	/** 资讯内容的全文本，不带HTML标签的 */
	private String contentText;
	/** 内容,新闻的实际内容URL,对应生成后内容 */
	private String contentUrl;
	/** 原始栏目ID，为方便后续统计，新闻先归属其中一个栏目。 */
	private Long classifyId;
	/** 新闻内容类型 */
	private Byte contextType;
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
	/** 审核人ID */
	private Long auditingId;
	/** 审核人姓名 */
	private String auditingtor;
	/** 审核时间 */
	private Date auditingTime;
	/** 视频和音频转码状态 */
	private Byte videoStatus;
	/** 点赞数 */
	private Integer praiseCount;
	
	//------------------------ infoclassify字段 --------------------------
	//资讯Id
	private Long infoClassifyId;
	//资讯标题
	private String title;
	//状态
	private Byte status;
	private Date onlineTime;
	//------------------------ 冗余字段 --------------------------
	
	/** 栏目名称（逗号分隔，用于列表展示） */
	private String colsName;
	/** 栏目id（逗号分隔，用于列表查询） */
	private String colsId;
	/** 栏目排序号（选择单个栏目时，使用此字段） */
	private Integer sortNo;
	//调研
	List<SurveyInfoDto> surveyInfos;
	//投票
	List<SurveyInfoDto> votes;
	//打擂
	List<SurveyInfoDto> arenas;
	//外链，文本，及原创文本，音视频资源
	List<AppResouse> appResource;
	//新闻内容列表
	List<InformationContent> infocontents;
	//列表显示图片
	private String listViewImg;
	//栏目回复数
	private Integer classifyReplyCount;
	private String hlwOldGuid;
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
	
	public String getInfoLabel(){
		return this.infoLabel;
	}
	
	public void setInfoLabel(String infoLabel){
		this.infoLabel = infoLabel;
	}
	
	public String getSynopsis(){
		return this.synopsis;
	}
	
	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	
	public String getKeywords(){
		return this.keywords;
	}
	
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	public String getInfoSource(){
		return this.infoSource;
	}
	
	public void setInfoSource(String infoSource){
		this.infoSource = infoSource;
	}
	public String getPlViewType(){
		return this.plViewType;
	}
	
	public void setPlViewType(String plViewType){
		this.plViewType = plViewType;
	}
	
	public Byte getCommentType(){
		return this.commentType;
	}
	
	public void setCommentType(Byte commentType){
		this.commentType = commentType;
	}
	public Byte getCommentValidateType(){
		return this.commentValidateType;
	}
	
	public void setCommentValidateType(Byte commentValidateType){
		this.commentValidateType = commentValidateType;
	}
	public Byte getValidateType(){
		return this.validateType;
	}
	
	public void setValidateType(Byte validateType){
		this.validateType = validateType;
	}
	public Long getInitCount(){
		return this.initCount;
	}
	
	public void setInitCount(Long initCount){
		this.initCount = initCount;
	}
	public Byte getAddType(){
		return this.addType;
	}
	
	public void setAddType(Byte addType){
		this.addType = addType;
	}
	public Integer getTopTime(){
		return this.topTime;
	}
	
	public void setTopTime(Integer topTime){
		this.topTime = topTime;
	}
	public Integer getViewCount(){
		return this.viewCount;
	}
	
	public void setViewCount(Integer viewCount){
		this.viewCount = viewCount;
	}
	public Integer getReplyCount(){
		return this.replyCount;
	}
	
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public Byte getSendStatus(){
		return this.sendStatus;
	}
	
	public void setSendStatus(Byte sendStatus){
		this.sendStatus = sendStatus;
	}
	public String getContent(){
		return this.content;
	}
	
	public Long getClassifyId(){
		return this.classifyId;
	}
	
	public void setClassifyId(Long classifyId){
		this.classifyId = classifyId;
	}
	public Byte getContextType(){
		return this.contextType;
	}
	
	public void setContextType(Byte contextType){
		this.contextType = contextType;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Long getUpdatorId(){
		return this.updatorId;
	}
	
	public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	public Long getAuditingId(){
		return this.auditingId;
	}
	
	public void setAuditingId(Long auditingId){
		this.auditingId = auditingId;
	}
	public String getAuditingtor(){
		return this.auditingtor;
	}
	
	public void setAuditingtor(String auditingtor){
		this.auditingtor = auditingtor;
	}
	public Date getAuditingTime(){
		return this.auditingTime;
	}
	
	public void setAuditingTime(Date auditingTime){
		this.auditingTime = auditingTime;
	}
	
	public String getColsName() {
		return colsName;
	}

	public void setColsName(String colsName) {
		this.colsName = colsName;
	}

	public String getColsId() {
		return colsId;
	}

	public void setColsId(String colsId) {
		this.colsId = colsId;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Long getInfoClassifyId() {
		return infoClassifyId;
	}

	public void setInfoClassifyId(Long infoClassifyId) {
		this.infoClassifyId = infoClassifyId;
	}

	public List<SurveyInfoDto> getSurveyInfos() {
		return surveyInfos;
	}

	public void setSurveyInfos(List<SurveyInfoDto> surveyInfos) {
		this.surveyInfos = surveyInfos;
	}

	public List<SurveyInfoDto> getVotes() {
		return votes;
	}

	public void setVotes(List<SurveyInfoDto> votes) {
		this.votes = votes;
	}

	public List<SurveyInfoDto> getArenas() {
		return arenas;
	}

	public void setArenas(List<SurveyInfoDto> arenas) {
		this.arenas = arenas;
	}

	public List<AppResouse> getAppResource() {
		return appResource;
	}

	public void setAppResource(List<AppResouse> appResource) {
		this.appResource = appResource;
	}
    
	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public Byte getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(Byte videoStatus) {
		this.videoStatus = videoStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public String getListViewImg() {
		return listViewImg;
	}

	public void setListViewImg(String listViewImg) {
		this.listViewImg = listViewImg;
	}
   
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public List<InformationContent> getInfocontents() {
		return infocontents;
	}

	public void setInfocontents(List<InformationContent> infocontents) {
		this.infocontents = infocontents;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getClassifyReplyCount() {
		return classifyReplyCount;
	}

	public void setClassifyReplyCount(Integer classifyReplyCount) {
		this.classifyReplyCount = classifyReplyCount;
	}

	public String getHlwOldGuid() {
		return hlwOldGuid;
	}

	public void setHlwOldGuid(String hlwOldGuid) {
		this.hlwOldGuid = hlwOldGuid;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
