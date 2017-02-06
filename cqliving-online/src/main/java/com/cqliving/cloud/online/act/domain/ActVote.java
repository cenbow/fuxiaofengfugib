package com.cqliving.cloud.online.act.domain;


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
 * 活动投票表 Entity
 * Date: 2016-06-07 09:23:04
 * @author Code Generator
 */
@Entity
@Table(name = "act_vote")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActVote extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 普通投票 */
	public static final Byte TYPE1 = 1;
	/** 分类投票 */
	public static final Byte TYPE2 = 2;
		
	/** 活动类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE1, "普通投票");
		allTypes.put(TYPE2, "分类投票");
	}
	/** 无限制 */
	public static final Byte LIMITRATETYPE0 = 0;
	/** 总数限制 */
	public static final Byte LIMITRATETYPE1 = 1;
	/** 每日限制 */
	public static final Byte LIMITRATETYPE2 = 2;
		
	/** 类型 */
	public static final Map<Byte, String> allLimitRateTypes = Maps.newTreeMap();
	static {
		allLimitRateTypes.put(LIMITRATETYPE0, "无限制");
		allLimitRateTypes.put(LIMITRATETYPE1, "总数限制");
		allLimitRateTypes.put(LIMITRATETYPE2, "每日限制");
	}
	/** 无限制 */
	public static final Byte LIMITSINGLETYPE0 = 0;
	/** 总数限制 */
	public static final Byte LIMITSINGLETYPE1 = 1;
	/** 每日限制 */
	public static final Byte LIMITSINGLETYPE2 = 2;
		
	/** 类型 */
	public static final Map<Byte, String> allLimitSingleTypes = Maps.newTreeMap();
	static {
		allLimitSingleTypes.put(LIMITSINGLETYPE0, "无限制");
		allLimitSingleTypes.put(LIMITSINGLETYPE1, "总数限制");
		allLimitSingleTypes.put(LIMITSINGLETYPE2, "每日限制");
	}
	/** 单选 */
	public static final Byte LIMITRULETYPE0 = 0;
	/** 多选 */
	public static final Byte LIMITRULETYPE1 = 1;
	/** 限制选多少项 */
	public static final Byte LIMITRULETYPE2 = 2;
		
	/** 类型 */
	public static final Map<Byte, String> allLimitRuleTypes = Maps.newTreeMap();
	static {
		allLimitRuleTypes.put(LIMITRULETYPE0, "单选");
		allLimitRuleTypes.put(LIMITRULETYPE1, "多选");
		allLimitRuleTypes.put(LIMITRULETYPE2, "限制选多少项");
	}
	/** 否 */
	public static final Byte LOGGEDSTATUS0 = 0;
	/** 是 */
	public static final Byte LOGGEDSTATUS1 = 1;
		
	/** 是否登陆后才能操作 */
	public static final Map<Byte, String> allLoggedStatuss = Maps.newTreeMap();
	static {
		allLoggedStatuss.put(LOGGEDSTATUS0, "否");
		allLoggedStatuss.put(LOGGEDSTATUS1, "是");
	}
	/** 否 */
	public static final Byte ISSHARE0 = 0;
	/** 是 */
	public static final Byte ISSHARE1 = 1;
		
	/** 是否分享分享后增加投票次数 */
	public static final Map<Byte, String> allIsShares = Maps.newTreeMap();
	static {
		allIsShares.put(ISSHARE0, "否");
		allIsShares.put(ISSHARE1, "是");
	}
	/** 无限制 */
	public static final Byte LIMITSHARETYPE0 = 0;
	/** 总数限制 */
	public static final Byte LIMITSHARETYPE1 = 1;
	/** 每日限制 */
	public static final Byte LIMITSHARETYPE2 = 2;
		
	/** 类型 */
	public static final Map<Byte, String> allLimitShareTypes = Maps.newTreeMap();
	static {
		allLimitShareTypes.put(LIMITSHARETYPE0, "无限制");
		allLimitShareTypes.put(LIMITSHARETYPE1, "总数限制");
		allLimitShareTypes.put(LIMITSHARETYPE2, "每日限制");
	}
	/** 正常 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "正常");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 活动ID（act_info表主键） */
	private Long actInfoId;
	/** 活动集合表ID（act_info_list表主键） */
	private Long actInfoListId;
	/** 活动类型 */
	private Byte type;
	/** 活动开始时间 */
	private Date startTime;
	/** 活动结束时间 */
	private Date endTime;
	/** 类型 */
	private Byte limitRateType;
	/** 数量 */
	private Integer limitRateTimes;
	/** 类型 */
	private Byte limitSingleType;
	/** 数量 */
	private Integer limitSingleTimes;
	/** 类型 */
	private Byte limitRuleType;
	/** 当limit_rule_type为1和0时，值默认为0但无效，当为限制值时有效 */
	private Integer limitRuleTimes;
	/** 是否登陆后才能操作 */
	private Byte loggedStatus;
	/** 是否分享分享后增加投票次数 */
	private Byte isShare;
	/** 分享后增加投票次数，当is_share=0时该值无效 */
	private Integer shareAddTimes;
	/** 类型 */
	private Byte limitShareType;
	/** 当limit_share_type为1和0时，值默认为0但无效，当为限制值时有效 */
	private Integer limitShareTimes;
	/** 模板CODE（act_template表里面的code） */
	private String actTemplateCode;
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
	public Long getActInfoId(){
		return this.actInfoId;
	}
	
	public void setActInfoId(Long actInfoId){
		this.actInfoId = actInfoId;
	}
	public Long getActInfoListId(){
		return this.actInfoListId;
	}
	
	public void setActInfoListId(Long actInfoListId){
		this.actInfoListId = actInfoListId;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
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
	public Byte getLimitRateType(){
		return this.limitRateType;
	}
	
	public void setLimitRateType(Byte limitRateType){
		this.limitRateType = limitRateType;
	}
	public Integer getLimitRateTimes(){
		return this.limitRateTimes;
	}
	
	public void setLimitRateTimes(Integer limitRateTimes){
		this.limitRateTimes = limitRateTimes;
	}
	public Byte getLimitSingleType(){
		return this.limitSingleType;
	}
	
	public void setLimitSingleType(Byte limitSingleType){
		this.limitSingleType = limitSingleType;
	}
	public Integer getLimitSingleTimes(){
		return this.limitSingleTimes;
	}
	
	public void setLimitSingleTimes(Integer limitSingleTimes){
		this.limitSingleTimes = limitSingleTimes;
	}
	public Byte getLimitRuleType(){
		return this.limitRuleType;
	}
	
	public void setLimitRuleType(Byte limitRuleType){
		this.limitRuleType = limitRuleType;
	}
	public Integer getLimitRuleTimes(){
		return this.limitRuleTimes;
	}
	
	public void setLimitRuleTimes(Integer limitRuleTimes){
		this.limitRuleTimes = limitRuleTimes;
	}
	public Byte getLoggedStatus(){
		return this.loggedStatus;
	}
	
	public void setLoggedStatus(Byte loggedStatus){
		this.loggedStatus = loggedStatus;
	}
	public Byte getIsShare(){
		return this.isShare;
	}
	
	public void setIsShare(Byte isShare){
		this.isShare = isShare;
	}
	public Integer getShareAddTimes(){
		return this.shareAddTimes;
	}
	
	public void setShareAddTimes(Integer shareAddTimes){
		this.shareAddTimes = shareAddTimes;
	}
	public Byte getLimitShareType(){
		return this.limitShareType;
	}
	
	public void setLimitShareType(Byte limitShareType){
		this.limitShareType = limitShareType;
	}
	public Integer getLimitShareTimes(){
		return this.limitShareTimes;
	}
	
	public void setLimitShareTimes(Integer limitShareTimes){
		this.limitShareTimes = limitShareTimes;
	}
	public String getActTemplateCode(){
		return this.actTemplateCode;
	}
	
	public void setActTemplateCode(String actTemplateCode){
		this.actTemplateCode = actTemplateCode;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
