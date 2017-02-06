package com.cqliving.cloud.online.tourism.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 旅游表 Entity
 * Date: 2016-08-23 17:29:24
 * @author Code Generator
 */
public class TourismInfoDto {
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 名称 */
	private String name;
	/** 分类 */
	private Byte type;
	/** 是否外链 */
	private Byte isLink;
	/** 外链地址 */
	private String linkUrl;
	/** 分类 */
	private Byte isShow;
	/** 所处位置 */
	private String place;
	/** 所处位置纬度 */
	private String lat;
	/** 所处位置经度 */
	private String lng;
	/** 所属区域CODE */
	private String regionCode;
	/** 列表图地址 */
	private String imageUrl;
	/** 状态 */
	private Byte status;
	/** 排序号 */
	private Integer sortNo;
	/** 收藏量 */
	private Integer collectCount;
	/** 评论量 */
	private Integer replyCount;
	/** 浏览量 */
	private Integer viewCount;
	/** 点赞量 */
	private Integer praiseCount;
	/** 景区价格 */
	private String price;
	/** 开放时间，特意把time字段放前面 */
	private String timeOpen;
	/** 气候类型 */
	private String climateType;
	/** 联系电话,最多三个用逗号分隔 */
	private String telephone;
	/** 地点 */
	private String address;
	/** 专题描述 */
	private String description;
	/** 景点介绍内容 */
	private String content;
	/** 景点线路 */
	private String scenicRoute;
	/** 摘要，如果为空，则取content字段并截字 */
	private String synopsis;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	
	//------------------------ 冗余字段 --------------------------
	/** 区域名称 */
	private String regionName;
	/** tourism_special表ID */
	private Long tourismSpecialId;
	/** tourism_special表sort_no */
	private Integer tourismSpecialSortNo;
	/** tourism_special表专题ID */
	private Long tourismId;
	/** 距离 */
	private Long distance;
	
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
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public Byte getIsLink(){
		return this.isLink;
	}
	
	public void setIsLink(Byte isLink){
		this.isLink = isLink;
	}
	public String getLinkUrl(){
		return this.linkUrl;
	}
	
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}
	public Byte getIsShow(){
		return this.isShow;
	}
	
	public void setIsShow(Byte isShow){
		this.isShow = isShow;
	}
	public String getPlace(){
		return this.place;
	}
	
	public void setPlace(String place){
		this.place = place;
	}
	public String getLat(){
		return this.lat;
	}
	
	public void setLat(String lat){
		this.lat = lat;
	}
	public String getLng(){
		return this.lng;
	}
	
	public void setLng(String lng){
		this.lng = lng;
	}
	public String getRegionCode(){
		return this.regionCode;
	}
	
	public void setRegionCode(String regionCode){
		this.regionCode = regionCode;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public Integer getCollectCount(){
		return this.collectCount;
	}
	
	public void setCollectCount(Integer collectCount){
		this.collectCount = collectCount;
	}
	public Integer getReplyCount(){
		return this.replyCount;
	}
	
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public Integer getViewCount(){
		return this.viewCount;
	}
	
	public void setViewCount(Integer viewCount){
		this.viewCount = viewCount;
	}
	public Integer getPraiseCount(){
		return this.praiseCount;
	}
	
	public void setPraiseCount(Integer praiseCount){
		this.praiseCount = praiseCount;
	}
	public String getPrice(){
		return this.price;
	}
	
	public void setPrice(String price){
		this.price = price;
	}
	public String getTimeOpen(){
		return this.timeOpen;
	}
	
	public void setTimeOpen(String timeOpen){
		this.timeOpen = timeOpen;
	}
	public String getClimateType(){
		return this.climateType;
	}
	
	public void setClimateType(String climateType){
		this.climateType = climateType;
	}
	public String getTelephone(){
		return this.telephone;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getScenicRoute(){
		return this.scenicRoute;
	}
	
	public void setScenicRoute(String scenicRoute){
		this.scenicRoute = scenicRoute;
	}
	public String getSynopsis(){
		return this.synopsis;
	}
	
	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
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
	
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Long getTourismSpecialId() {
		return tourismSpecialId;
	}

	public void setTourismSpecialId(Long tourismSpecialId) {
		this.tourismSpecialId = tourismSpecialId;
	}

	public Integer getTourismSpecialSortNo() {
		return tourismSpecialSortNo;
	}

	public void setTourismSpecialSortNo(Integer tourismSpecialSortNo) {
		this.tourismSpecialSortNo = tourismSpecialSortNo;
	}

	public Long getTourismId() {
		return tourismId;
	}

	public void setTourismId(Long tourismId) {
		this.tourismId = tourismId;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
