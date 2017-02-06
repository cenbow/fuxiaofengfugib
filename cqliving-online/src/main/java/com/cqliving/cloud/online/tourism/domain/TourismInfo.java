package com.cqliving.cloud.online.tourism.domain;


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
 * 旅游表 Entity
 * Date: 2016-08-23 17:29:24
 * @author Code Generator
 */
@Entity
@Table(name = "tourism_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TourismInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 景点 */
	public static final Byte TYPE1 = 1;
	/** 专题 */
	public static final Byte TYPE2 = 2;
		
	/** 分类 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE1, "景点");
		allTypes.put(TYPE2, "专题");
	}
	/** 否 */
	public static final Byte ISLINK0 = 0;
	/** 是 */
	public static final Byte ISLINK1 = 1;
		
	/** 是否外链 */
	public static final Map<Byte, String> allIsLinks = Maps.newTreeMap();
	static {
		allIsLinks.put(ISLINK0, "否");
		allIsLinks.put(ISLINK1, "是");
	}
	/** 否 */
	public static final Byte ISSHOW0 = 0;
	/** 是 */
	public static final Byte ISSHOW1 = 1;
		
	/** 分类 */
	public static final Map<Byte, String> allIsShows = Maps.newTreeMap();
	static {
		allIsShows.put(ISSHOW0, "否");
		allIsShows.put(ISSHOW1, "是");
	}
	/** 保存 */
	public static final Byte STATUS1 = 1;
	/** 已上线 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "保存");
		allStatuss.put(STATUS3, "已上线");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	
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
	private Double lat;
	/** 所处位置经度 */
	private Double lng;
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
	
	public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
