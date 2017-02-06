package com.cqliving.cloud.online.shop.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class ShopInfoDto {

	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 类型ID,shop_type表的ID */
	private Long typeId;
	/** 分类ID */
	private Long categoryId;
	/** 店铺名称 */
	private String name;
	/** 所处位置纬度 */
	private String lat;
	/** 所处位置经度 */
	private String lng;
	/** 所属区域CODE */
	private String regionCode;
	/** 所属区域名称 */
	private String regionName;
	/** 地址 */
	private String address;
	/** 封面图 */
	private String coverImg;
	/** 电话 */
	private String telephone;
	/** 状态 */
	private Byte status;
	/** 描述 */
	private String description;
	/** 内容 */
	private String content;
	/** 收藏量 */
	private Integer collectCount;
	/** 评论量 */
	private Integer replyCount;
	/** 价格（分） */
	private Integer price;
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
	/** 是否置顶 */
	private Byte topType;
	/** 标签ID */
	private Long infoLabelId;
	
	//------------------------ 冗余字段 --------------------------
	/** 距离 */
	private Long distance;
	/** 便签名 */
	private String infoLabel;
	/**商情推荐图片*/
	private String imageUrl ;
	
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
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
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
	public String getRegionName(){
		return this.regionName;
	}
	
	public void setRegionName(String regionName){
		this.regionName = regionName;
	}
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	public String getCoverImg(){
		return this.coverImg;
	}
	
	public void setCoverImg(String coverImg){
		this.coverImg = coverImg;
	}
	public String getTelephone(){
		return this.telephone;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
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
	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public Integer getReplyCount(){
		return this.replyCount;
	}
	
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public Integer getPrice(){
		return this.price;
	}
	
	public void setPrice(Integer price){
		this.price = price;
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
	
	public Byte getTopType() {
		return topType;
	}

	public void setTopType(Byte topType) {
		this.topType = topType;
	}

	public Long getInfoLabelId() {
		return infoLabelId;
	}

	public void setInfoLabelId(Long infoLabelId) {
		this.infoLabelId = infoLabelId;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public String getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(String infoLabel) {
		this.infoLabel = infoLabel;
	}

	public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
