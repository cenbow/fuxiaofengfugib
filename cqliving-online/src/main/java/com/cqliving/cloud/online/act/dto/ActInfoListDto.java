package com.cqliving.cloud.online.act.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ActInfoListDto {

	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 活动ID（act_info表主键） */
	private Long actInfoId;
	/** 活动类型 */
	private Byte type;
	/** 活动显示类型 */
	private Byte showType;
	/** 状态 */
	private Byte status;
	/** 外链地址 */
	private String linkUrl;
	/** 活动开始时间 */
	private Date startTime;
	/** 活动结束时间 */
	private Date endTime;
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
	
	//------------------------ 冗余字段 --------------------------
	/** 标题 */
	private String title;
	/** 图片列表，只能一张 */
	private String listImageUrl;
	/** 排序号 */
	private Integer sortNo;
	/** 摘要 */
    private String digest;
    /** 是否推荐到首页{0:否,1:是}，首页展示 */
	private Byte isRecommend;
	/** 推荐到首页图片 */
	private String recommendImageUrl;
	/** 主活动结束时间 */
	private Date actInfoEndTime;
	
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
	public Long getActInfoId(){
		return this.actInfoId;
	}
	
	public void setActInfoId(Long actInfoId){
		this.actInfoId = actInfoId;
	}
	public Byte getType(){
		return this.type;
	}
	public void setType(Byte type){
		this.type = type;
	}
	public Byte getShowType() {
		return showType;
	}
	public void setShowType(Byte showType) {
		this.showType = showType;
	}

	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public String getLinkUrl(){
		return this.linkUrl;
	}
	
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}
	public Date getStartTime(){
		return this.startTime;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	public Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getListImageUrl() {
		return listImageUrl;
	}

	public void setListImageUrl(String listImageUrl) {
		this.listImageUrl = listImageUrl;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
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

	public Date getActInfoEndTime() {
		return actInfoEndTime;
	}

	public void setActInfoEndTime(Date actInfoEndTime) {
		this.actInfoEndTime = actInfoEndTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
