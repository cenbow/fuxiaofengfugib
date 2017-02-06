package com.cqliving.cloud.online.shopping.domain;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 商品表 Entity
 * Date: 2016-11-17 15:41:33
 * @author Code Generator
 */
@Entity
@Table(name = "shopping_goods")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShoppingGoods extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 是 */
	public static final Byte ISFREESHIPPING1 = 1;
	/** 满金额包邮 */
	public static final Byte ISFREESHIPPING2 = 2;
	/** 否 */
	public static final Byte ISFREESHIPPING0 = 0;
		
	/** 是否包邮 */
	public static final Map<Byte, String> allIsFreeShippings = Maps.newTreeMap();
	static {
		allIsFreeShippings.put(ISFREESHIPPING1, "是");
		allIsFreeShippings.put(ISFREESHIPPING0, "否");
		allIsFreeShippings.put(ISFREESHIPPING2, "满金额包邮");
	}
	/** 是 */
	public static final Byte ISRECOMMEMDINDEX1 = 1;
	/** 否 */
	public static final Byte ISRECOMMEMDINDEX0 = 0;
		
	/** 是否推荐到首页 */
	public static final Map<Byte, String> allIsRecommemdIndexs = Maps.newTreeMap();
	static {
		allIsRecommemdIndexs.put(ISRECOMMEMDINDEX1, "是");
		allIsRecommemdIndexs.put(ISRECOMMEMDINDEX0, "否");
	}
	/** 是 */
	public static final Byte ISRECOMMENDCAROUSEL1 = 1;
	/** 否 */
	public static final Byte ISRECOMMENDCAROUSEL0 = 0;
		
	/** 是否推荐到轮播 */
	public static final Map<Byte, String> allIsRecommendCarousels = Maps.newTreeMap();
	static {
		allIsRecommendCarousels.put(ISRECOMMENDCAROUSEL1, "是");
		allIsRecommendCarousels.put(ISRECOMMENDCAROUSEL0, "否");
	}
	/** 保存 */
	public static final Byte STATUS1 = 1;
	/** 上线 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "保存");
		allStatuss.put(STATUS3, "上线");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 一级分类ID */
	private Long categoryLevelOneId;
	/** 二级分类ID */
	private Long categoryLevelTwoId;
	/** 详情 */
	private String content;
	/** 是否包邮 */
	private Byte isFreeShipping;
	/** 包邮金额（分） */
	private Integer freeShippingPrice;
	/** 是否推荐到首页 */
	private Byte isRecommemdIndex;
	/** 是否推荐到轮播 */
	private Byte isRecommendCarousel;
	/** 标签（逗号分隔） */
	private String labels;
	/** 列表图片 */
	private String listImageUrl;
	/** 名称 */
	private String name;
	/** 上架时间 */
	private Date onlineTime;
	/** 原价（分） */
	private Integer originalPrice;
	/** 价格（分） */
	private Integer price;
	/** 单位 */
	private String unit;
	/** 销量 */
	private Integer salesVolume;
	/** 摘要 */
	private String synopsis;
	/** 运费模板ID */
	private Long shippingFareTemplateId;
	/** 排序号 */
	private Integer sortNo;
	/** 状态 */
	private Byte status;
	/** 库存 */
	private Integer stock;
	/** 收藏量 */
	private Integer collectCount;
	/** 浏览量 */
	private Integer viewCount;
	/** 评论量 */
	private Integer replyCount;
	/** 评论量 */
	private Integer praiseCount;
	/** 商品评价 */
	private Integer goodsScore;
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
	/** 商品小图地址 */
	private String minImageUrl;
	//商品打折
	private List<ShoppingDiscount> shoppingDiscounts;
	//商铺图片
	private List<ShoppingImages>  shoppingImages;
	
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
	public Long getCategoryLevelOneId(){
		return this.categoryLevelOneId;
	}
	
	public void setCategoryLevelOneId(Long categoryLevelOneId){
		this.categoryLevelOneId = categoryLevelOneId;
	}
	public Long getCategoryLevelTwoId(){
		return this.categoryLevelTwoId;
	}
	
	public void setCategoryLevelTwoId(Long categoryLevelTwoId){
		this.categoryLevelTwoId = categoryLevelTwoId;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public Byte getIsFreeShipping(){
		return this.isFreeShipping;
	}
	
	public void setIsFreeShipping(Byte isFreeShipping){
		this.isFreeShipping = isFreeShipping;
	}
	public Integer getFreeShippingPrice(){
		return this.freeShippingPrice;
	}
	
	public void setFreeShippingPrice(Integer freeShippingPrice){
		this.freeShippingPrice = freeShippingPrice;
	}
	public Byte getIsRecommemdIndex(){
		return this.isRecommemdIndex;
	}
	
	public void setIsRecommemdIndex(Byte isRecommemdIndex){
		this.isRecommemdIndex = isRecommemdIndex;
	}
	public Byte getIsRecommendCarousel(){
		return this.isRecommendCarousel;
	}
	
	public void setIsRecommendCarousel(Byte isRecommendCarousel){
		this.isRecommendCarousel = isRecommendCarousel;
	}
	public String getLabels(){
		return this.labels;
	}
	
	public void setLabels(String labels){
		this.labels = labels;
	}
	public String getListImageUrl(){
		return this.listImageUrl;
	}
	
	public void setListImageUrl(String listImageUrl){
		this.listImageUrl = listImageUrl;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Date getOnlineTime(){
		return this.onlineTime;
	}
	
	public void setOnlineTime(Date onlineTime){
		this.onlineTime = onlineTime;
	}
	public Integer getOriginalPrice(){
		return this.originalPrice;
	}
	
	public void setOriginalPrice(Integer originalPrice){
		this.originalPrice = originalPrice;
	}
	public Integer getPrice(){
		return this.price;
	}
	
	public void setPrice(Integer price){
		this.price = price;
	}
	public String getUnit(){
		return this.unit;
	}
	
	public void setUnit(String unit){
		this.unit = unit;
	}
	public Integer getSalesVolume(){
		return this.salesVolume;
	}
	
	public void setSalesVolume(Integer salesVolume){
		this.salesVolume = salesVolume;
	}
	public String getSynopsis(){
		return this.synopsis;
	}
	
	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}
	public Long getShippingFareTemplateId(){
		return this.shippingFareTemplateId;
	}
	
	public void setShippingFareTemplateId(Long shippingFareTemplateId){
		this.shippingFareTemplateId = shippingFareTemplateId;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Integer getStock(){
		return this.stock;
	}
	
	public void setStock(Integer stock){
		this.stock = stock;
	}
	public Integer getCollectCount(){
		return this.collectCount;
	}
	
	public void setCollectCount(Integer collectCount){
		this.collectCount = collectCount;
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
	public Integer getPraiseCount(){
		return this.praiseCount;
	}
	
	public void setPraiseCount(Integer praiseCount){
		this.praiseCount = praiseCount;
	}
	public Integer getGoodsScore() {
		return goodsScore;
	}

	public void setGoodsScore(Integer goodsScore) {
		this.goodsScore = goodsScore;
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

	public String getMinImageUrl() {
		return minImageUrl;
	}

	public void setMinImageUrl(String minImageUrl) {
		this.minImageUrl = minImageUrl;
	}

	@OneToMany(targetEntity=ShoppingDiscount.class,cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="shoppingGoodsId",insertable=false,updatable=false)
	@Where(clause ="status=3")
	public List<ShoppingDiscount> getShoppingDiscounts() {
		return shoppingDiscounts;
	}

	public void setShoppingDiscounts(List<ShoppingDiscount> shoppingDiscounts) {
		this.shoppingDiscounts = shoppingDiscounts;
	}

	@OneToMany(targetEntity=ShoppingImages.class,cascade = CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="shoppingGoodsId",insertable=false,updatable=false)
	@Where(clause ="status=3")
	@OrderBy(value ="sortNo asc")
	public List<ShoppingImages> getShoppingImages() {
		return shoppingImages;
	}

	public void setShoppingImages(List<ShoppingImages> shoppingImages) {
		this.shoppingImages = shoppingImages;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
