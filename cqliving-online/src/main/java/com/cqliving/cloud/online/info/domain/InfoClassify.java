package com.cqliving.cloud.online.info.domain;


import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 资讯表 Entity
 * Date: 2016-05-07 09:54:57
 * @author Code Generator
 */
@Entity
@Table(name = "info_classify")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoClassify extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 显示 */
	public static final Byte CLASSFIEVIEWSTATUS0 = 0;
	/** 不显示 */
	public static final Byte CLASSFIEVIEWSTATUS1 = 1;
		
	/** 栏目新闻显示状态 */
	public static final Map<Byte, String> allClassfieViewStatuss = Maps.newTreeMap();
	static {
		allClassfieViewStatuss.put(CLASSFIEVIEWSTATUS0, "显示");
		allClassfieViewStatuss.put(CLASSFIEVIEWSTATUS1, "不显示");
	}
	/** 无图 */
	public static final Byte LISTVIEWTYPE0 = 0;
	/** 单图 */
	public static final Byte LISTVIEWTYPE1 = 1;
	/** 大图 */
	public static final Byte LISTVIEWTYPE2 = 2;
	/** 多图 */
	public static final Byte LISTVIEWTYPE3 = 3;
	/** 轮播 */
	public static final Byte LISTVIEWTYPE4 = 4;
	/** 窄图 */
	public static final Byte LISTVIEWTYPE5 = 5;
	/** 窄图带标题 */
	public static final Byte LISTVIEWTYPE6 = 6;
	/** 列表显示类型 */
	public static final Map<Byte, String> allListViewTypes = Maps.newTreeMap();
	static {
		allListViewTypes.put(LISTVIEWTYPE0, "无图");
		allListViewTypes.put(LISTVIEWTYPE1, "单图");
		allListViewTypes.put(LISTVIEWTYPE2, "大图");
		allListViewTypes.put(LISTVIEWTYPE3, "多图");
		allListViewTypes.put(LISTVIEWTYPE4, "轮播");
		allListViewTypes.put(LISTVIEWTYPE5, "窄图");
		allListViewTypes.put(LISTVIEWTYPE6, "窄图带标题");
	}
	/** 审核不通过 */
	public static final Byte STATUS_1 = -1;
	/** 草稿 */
	public static final Byte STATUS0 = 0;
	/** 保存（待审核） */
	public static final Byte STATUS1 = 1;
	/** 正常上线 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS_1, "审核不通过");
		allStatuss.put(STATUS0, "草稿");
		allStatuss.put(STATUS1, "待审核");
		allStatuss.put(STATUS3, "发布");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	/** 否 */
	public static final Byte ADDSPECIALSTATUS0 = 0;
	/** 是 */
	public static final Byte ADDSPECIALSTATUS1 = 1;
		
	/** 是否已加入专题 */
	public static final Map<Byte, String> allAddSpecialStatuss = Maps.newTreeMap();
	static {
		allAddSpecialStatuss.put(ADDSPECIALSTATUS0, "否");
		allAddSpecialStatuss.put(ADDSPECIALSTATUS1, "是");
	}
	
	/** 未推送 */
	public static final Byte SENDSTATUS0 = 0;
	/** 已推送 */
	public static final Byte SENDSTATUS1 = 1;
		
	/** 推送状态 */
	public static final Map<Byte, String> allSendStatuss = Maps.newTreeMap();
	static {
		allSendStatuss.put(SENDSTATUS0, "未推送");
		allSendStatuss.put(SENDSTATUS1, "已推送");
	}
	
	/** 允许 */
	public static final Byte COMMENTTYPE0 = 0;
	/** 不允许 */
	public static final Byte COMMENTTYPE1 = 1;
		
	/** 允许评论 */
	public static final Map<Byte, String> allCommentTypes = Maps.newTreeMap();
	static {
		allCommentTypes.put(COMMENTTYPE0, "允许");
		allCommentTypes.put(COMMENTTYPE1, "不允许");
	}
	/** 不需审核 */
	public static final Byte COMMENTVALIDATETYPE0 = 0;
	/** 需要审核 */
	public static final Byte COMMENTVALIDATETYPE1 = 1;
		
	/** 评论需审核 */
	public static final Map<Byte, String> allCommentValidateTypes = Maps.newTreeMap();
	static {
		allCommentValidateTypes.put(COMMENTVALIDATETYPE0, "不需审核");
		allCommentValidateTypes.put(COMMENTVALIDATETYPE1, "需要审核");
	}
	
	/** 否 */
	public static final Byte ISRECOMMEND0 = 0;
	/** 是 */
	public static final Byte ISRECOMMEND1 = 1;
		
	/** 是否推荐到首页 */
	public static final Map<Byte, String> allIsRecommends = Maps.newTreeMap();
	static {
		allIsRecommends.put(ISRECOMMEND0, "否");
		allIsRecommends.put(ISRECOMMEND1, "是");
	}
	
	/** 否 */
	public static final Byte ISVIEWWECHAT0 = 0;
	/** 是 */
	public static final Byte ISVIEWWECHAT1 = 1;
		
	/** 是否推荐到微信小程序 */
	public static final Map<Byte, String> allIsVIEWWECHAT = Maps.newTreeMap();
	static {
		allIsVIEWWECHAT.put(ISVIEWWECHAT0, "否");
		allIsVIEWWECHAT.put(ISVIEWWECHAT1, "是");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 实际对应的资讯ID */
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
	private Long sourceInfoClassifyId;
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
	/** 是否已加入专题 */
	private Byte addSpecialStatus;
	/** 推送状态 */
	private Byte sendStatus;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 允许评论 */
	private Byte commentType;
	/** 评论需审核 */
	private Byte commentValidateType;
	/** 是否推荐到首页 */
	private Byte isRecommend;
	/** 华龙网新闻ID，如果是抓稿过来的字段，则该字段不为空。同manuscript_info_classify表的old_guid字段 */
	private String hlwOldGuid;
	/** 是否推荐到微信小程序{0:否,1:是} */
	private Byte isViewWechat;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public Long getSourceAppId(){
		return this.sourceAppId;
	}
	
	public void setSourceAppId(Long sourceAppId){
		this.sourceAppId = sourceAppId;
	}
	
	public Long getSourceInfoClassifyId() {
		return sourceInfoClassifyId;
	}

	public void setSourceInfoClassifyId(Long sourceInfoClassifyId) {
		this.sourceInfoClassifyId = sourceInfoClassifyId;
	}

	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Date getOnlineTime(){
		return this.onlineTime;
	}
	
	public void setOnlineTime(Date onlineTime){
		this.onlineTime = onlineTime;
	}
	public Date getOnlineTimeDate(){
		return this.onlineTimeDate;
	}
	
	public void setOnlineTimeDate(Date onlineTimeDate){
		this.onlineTimeDate = onlineTimeDate;
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
	public Byte getAddSpecialStatus(){
		return this.addSpecialStatus;
	}
	
	public void setAddSpecialStatus(Byte addSpecialStatus){
		this.addSpecialStatus = addSpecialStatus;
	}
	
	public Byte getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Byte sendStatus) {
		this.sendStatus = sendStatus;
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
    
	public Byte getCommentType() {
		return commentType;
	}

	public void setCommentType(Byte commentType) {
		this.commentType = commentType;
	}

	public Byte getCommentValidateType() {
		return commentValidateType;
	}

	public void setCommentValidateType(Byte commentValidateType) {
		this.commentValidateType = commentValidateType;
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

	public Byte getIsViewWechat() {
		return isViewWechat;
	}

	public void setIsViewWechat(Byte isViewWechat) {
		this.isViewWechat = isViewWechat;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
