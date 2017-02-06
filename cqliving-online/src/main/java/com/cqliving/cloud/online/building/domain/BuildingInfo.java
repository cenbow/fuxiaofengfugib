package com.cqliving.cloud.online.building.domain;


import java.util.Date;
import java.util.List;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 楼房信息表 Entity
 * Date: 2016-10-10 17:07:08
 * @author Code Generator
 */
@Entity
@Table(name = "building_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BuildingInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
//	1:元/㎡,2:万元/套,3:元/月,4:元/季度,5:元/年
	
	/** 元/㎡ */
	public static final Byte VIEWUNIT1 = 1;
	/** 万元/套 */
	public static final Byte VIEWUNIT2 = 2;
	/** 元/月 */
	public static final Byte VIEWUNIT3 = 3;
	/** 元/季度 */
	public static final Byte VIEWUNIT4 = 4;
	/** 元/年 */
	public static final Byte VIEWUNIT5 = 5;
		
	/** 显示价格单位 */
	public static final Map<Byte, String> allViewUnits = Maps.newTreeMap();
	static {
		allViewUnits.put(VIEWUNIT1, "元/㎡");
		allViewUnits.put(VIEWUNIT2, "万元/套");
		allViewUnits.put(VIEWUNIT3, "元/月");
		allViewUnits.put(VIEWUNIT4, "元/季度");
		allViewUnits.put(VIEWUNIT5, "元/年");
	}
	/** 一居 */
	public static final Byte HOUSETYPE1 = 1;
	/** 二居 */
	public static final Byte HOUSETYPE2 = 2;
	/** 三居 */
	public static final Byte HOUSETYPE3 = 3;
	/** 四居 */
	public static final Byte HOUSETYPE4 = 4;
	/** 五居 */
	public static final Byte HOUSETYPE5 = 5;
	/** 五居以上 */
	public static final Byte HOUSETYPE6 = 6;
		
	/** 可以选择多个，用逗号分开。户型 */
	public static final Map<Byte, String> allHouseTypes = Maps.newLinkedHashMap();
	
	public static final List<Map<Byte,String>> allHouseTypeList = Lists.newArrayList();
	static {
		allHouseTypes.put(HOUSETYPE1, "一居");
		allHouseTypes.put(HOUSETYPE2, "二居");
		allHouseTypes.put(HOUSETYPE3, "三居");
		allHouseTypes.put(HOUSETYPE4, "四居");
		allHouseTypes.put(HOUSETYPE5, "五居");
		allHouseTypes.put(HOUSETYPE6, "五居以上");
		
		Map<Byte,String> houseType1 = Maps.newHashMap();
		houseType1.put(HOUSETYPE1, "一居");
		Map<Byte,String> houseType2 = Maps.newHashMap();
		houseType2.put(HOUSETYPE2, "二居");
		Map<Byte,String> houseType3 = Maps.newHashMap();
		houseType3.put(HOUSETYPE3, "三居");
		Map<Byte,String> houseType4 = Maps.newHashMap();
		houseType4.put(HOUSETYPE4, "四居");
		Map<Byte,String> houseType5 = Maps.newHashMap();
		houseType5.put(HOUSETYPE5, "五居");
		Map<Byte,String> houseType6 = Maps.newHashMap();
		houseType6.put(HOUSETYPE6, "五居以上");
		
		allHouseTypeList.add(houseType1);
		allHouseTypeList.add(houseType2);
		allHouseTypeList.add(houseType3);
		allHouseTypeList.add(houseType4);
		allHouseTypeList.add(houseType5);
		allHouseTypeList.add(houseType6);
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
	
	/** 价格区间 */
	//条件常量值：价格区间
	private static final String CONDITION_AMONGST = "AMONGST";
	//条件常量值：大于等于
	private static final String CONDITION_GTE = "GTE";
	//条件常量值：小于等于
	private static final String CONDITION_LTE = "LTE";
	public static final Map<String,String> PRICE_RANGE =Maps.newLinkedHashMap();
	public static final List<Map<String,String>> PRICE_LIST = Lists.newArrayList();
	//租房子的价格集合
	public static final List<Map<String,String>> type2_PRICE_LIST = Lists.newArrayList();
	//价格区间常量值必须是以  条件常量值+价格值的格式,否则无法正常解析价格条件
	//当价格为区间时，价格值必须是  小价格+"-"+大价格   格式，价格值为分;
	public static final String PRICE_RANGE1  = "LTE500000";
	public static final String PRICE_RANGE2  = "AMONGST500000-700000";
	public static final String PRICE_RANGE3  = "AMONGST700000-900000";
	public static final String PRICE_RANGE4  = "GTE900000";
	
	public static final String PRICE_RANGE5  = "LTE50000";
	public static final String PRICE_RANGE6  = "AMONGST50000-80000";
	public static final String PRICE_RANGE7  = "AMONGST80000-100000";
	public static final String PRICE_RANGE8  = "GTE100000";
	static {
		PRICE_RANGE.put(PRICE_RANGE1, "5000元/㎡以下");
		PRICE_RANGE.put(PRICE_RANGE2, "5000-7000元/㎡");
		PRICE_RANGE.put(PRICE_RANGE3, "7000-9000元/㎡");
		PRICE_RANGE.put(PRICE_RANGE4, "9000元/㎡以上");
		
		Map<String,String> price1 = Maps.newHashMap();
		price1.put(PRICE_RANGE1, "5000元/㎡以下");
		Map<String,String> price2 = Maps.newHashMap();
		price2.put(PRICE_RANGE2, "5000-7000元/㎡");
		Map<String,String> price3 = Maps.newHashMap();
		price3.put(PRICE_RANGE3, "7000-9000元/㎡");
		Map<String,String> price4 = Maps.newHashMap();
		price4.put(PRICE_RANGE4, "9000元/㎡以上");
		PRICE_LIST.add(price1);
		PRICE_LIST.add(price2);
		PRICE_LIST.add(price3);
		PRICE_LIST.add(price4);
		
		Map<String,String> price5 = Maps.newHashMap();
		price5.put(PRICE_RANGE5, "500元以下");
		Map<String,String> price6 = Maps.newHashMap();
		price6.put(PRICE_RANGE6, "500-800元");
		Map<String,String> price7 = Maps.newHashMap();
		price7.put(PRICE_RANGE7, "800-1000元");
		Map<String,String> price8 = Maps.newHashMap();
		price8.put(PRICE_RANGE8, "1000元以上");
		type2_PRICE_LIST.add(price5);
		type2_PRICE_LIST.add(price6);
		type2_PRICE_LIST.add(price7);
		type2_PRICE_LIST.add(price8);
	}
	
	/** 买房子 */
	public static final Byte TYPE1 = 1;
	/** 租房子 */
	public static final Byte TYPE2 = 2;
	/** 状态 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	public static final Map<Byte, List<Map<String,String>>> allTypesPriceRange = Maps.newHashMap();
	static {
		allTypes.put(TYPE1, "买房子");
		allTypes.put(TYPE2, "租房子");
		
		allTypesPriceRange.put(TYPE1, PRICE_LIST);
		allTypesPriceRange.put(TYPE2, type2_PRICE_LIST);
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 楼盘名称 */
	private String name;
	/** 所属区域CODE */
	private String regionCode;
	/** 所属区域名称 */
	private String regionName;
	/** 所处位置纬度 */
	private String lat;
	/** 所处位置经度 */
	private String lng;
	/** 地标，可以填写多个，用逗号分隔，页面只显示第一个 */
	private String landmark;
	/** 户型面积范围-低，单位平方米 */
	private Double averageLow;
	/** 户型面积范围-高，单位平方米 */
	private Double averageHigh;
	/** 标签。最多添加3个标签,用逗号隔开,每个标签不超过5个字，如 学区房,特色别墅,景观居所； */
	private String buildingLabel;
	/** 楼盘均价（分），用于前台条件过滤 */
	private Integer averagePrice;
	/** 显示价格，用于页面展示 */
	private Integer viewPrice;
	/** 显示价格单位 */
	private Byte viewUnit;
	/** 列表图 */
	private String listImageUrl;
	/** 地址 */
	private String address;
	/** 建筑类型 */
	private String buildingType;
	/** 容积率 */
	private String ratio;
	/** 开盘时间。如 2016年6月18日三期5号楼已开盘 */
	private String openTime;
	/** 产权年限 */
	private String buildingYear;
	/** 开发商 */
	private String developer;
	/** 绿化率 */
	private String greeningRate;
	/** 交房时间 */
	private String launchTime;
	/** 物业费。如 5.00元/每平米·月 */
	private String propertyPrice;
	/** 装修状况 */
	private String renovationSituation;
	/** 预售许可证 */
	private String presalePermit;
	/** 咨询电话。只填写数字即可，而非必须填固定格式 */
	private String telephone;
	/** 可以选择多个，用逗号分开。户型 */
	private String houseType;
	/** 户型结构。如 三居 */
	private String structure;
	/** 套内面积。如 99999平方米 */
	private String innerArea;
	/** 排序号 */
	private Integer sortNo;
	/** 状态 */
	private Byte status;
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
	/** 评论数 */
	private Integer replyCount;
	/** 点赞数 */
	private Integer praiseCount;
	/** 类型{1:买房子,2:租房子} */
	private Byte type;
	
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
	public String getLandmark(){
		return this.landmark;
	}
	
	public void setLandmark(String landmark){
		this.landmark = landmark;
	}
	public Double getAverageLow(){
		return this.averageLow;
	}
	
	public void setAverageLow(Double averageLow){
		this.averageLow = averageLow;
	}
	public Double getAverageHigh(){
		return this.averageHigh;
	}
	
	public void setAverageHigh(Double averageHigh){
		this.averageHigh = averageHigh;
	}
	public String getBuildingLabel(){
		return this.buildingLabel;
	}
	
	public void setBuildingLabel(String buildingLabel){
		this.buildingLabel = buildingLabel;
	}
	public Integer getAveragePrice(){
		return this.averagePrice;
	}
	
	public void setAveragePrice(Integer averagePrice){
		this.averagePrice = averagePrice;
	}
	public Integer getViewPrice(){
		return this.viewPrice;
	}
	
	public void setViewPrice(Integer viewPrice){
		this.viewPrice = viewPrice;
	}
	public Byte getViewUnit(){
		return this.viewUnit;
	}
	
	public void setViewUnit(Byte viewUnit){
		this.viewUnit = viewUnit;
	}
	public String getListImageUrl(){
		return this.listImageUrl;
	}
	
	public void setListImageUrl(String listImageUrl){
		this.listImageUrl = listImageUrl;
	}
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	public String getBuildingType(){
		return this.buildingType;
	}
	
	public void setBuildingType(String buildingType){
		this.buildingType = buildingType;
	}
	public String getRatio(){
		return this.ratio;
	}
	
	public void setRatio(String ratio){
		this.ratio = ratio;
	}
	public String getOpenTime(){
		return this.openTime;
	}
	
	public void setOpenTime(String openTime){
		this.openTime = openTime;
	}
	public String getBuildingYear(){
		return this.buildingYear;
	}
	
	public void setBuildingYear(String buildingYear){
		this.buildingYear = buildingYear;
	}
	public String getDeveloper(){
		return this.developer;
	}
	
	public void setDeveloper(String developer){
		this.developer = developer;
	}
	public String getGreeningRate(){
		return this.greeningRate;
	}
	
	public void setGreeningRate(String greeningRate){
		this.greeningRate = greeningRate;
	}
	public String getLaunchTime(){
		return this.launchTime;
	}
	
	public void setLaunchTime(String launchTime){
		this.launchTime = launchTime;
	}
	public String getPropertyPrice(){
		return this.propertyPrice;
	}
	
	public void setPropertyPrice(String propertyPrice){
		this.propertyPrice = propertyPrice;
	}
	public String getRenovationSituation(){
		return this.renovationSituation;
	}
	
	public void setRenovationSituation(String renovationSituation){
		this.renovationSituation = renovationSituation;
	}
	public String getPresalePermit(){
		return this.presalePermit;
	}
	
	public void setPresalePermit(String presalePermit){
		this.presalePermit = presalePermit;
	}
	public String getTelephone(){
		return this.telephone;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public String getHouseType(){
		return this.houseType;
	}
	
	public void setHouseType(String houseType){
		this.houseType = houseType;
	}
	public String getStructure(){
		return this.structure;
	}
	
	public void setStructure(String structure){
		this.structure = structure;
	}
	public String getInnerArea(){
		return this.innerArea;
	}
	
	public void setInnerArea(String innerArea){
		this.innerArea = innerArea;
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
	
	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	//解析价格条件
	public static Map<String,Object> resolvePriceRange(Map<String,Object> searchMap,String price){
		
		if(null == searchMap){
			searchMap = Maps.newHashMap();
		}
		if(price.contains(CONDITION_AMONGST)){
			price = price.replace(CONDITION_AMONGST,"");
			String[] range = price.split("-");
			searchMap.put("GTE_averagePrice", range[0]);
			searchMap.put("LTE_averagePrice", range[1]);
		}else if(price.contains(CONDITION_GTE)){
			price = price.replace(CONDITION_GTE,"");
			searchMap.put("GTE_averagePrice",price);
		}else{
			price = price.replace(CONDITION_LTE,"");
			searchMap.put("LTE_averagePrice",price);
		}
		return searchMap;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
