package com.cqliving.cloud.online.survey.domain;


import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 调研表 Entity
 * Date: 2016-05-06 17:56:04
 * @author Code Generator
 */
@Entity
@Table(name = "survey_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final Byte STATUS3 = 3;
	public static final Byte STATUS99 = 99;
	/** 问卷 */
	public static final Byte TYPE0 = 0;
	/** 投票 */
	public static final Byte TYPE1 = 1;
	/** 打擂 */
	public static final Byte TYPE2 = 2;
		
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "问卷");
		allTypes.put(TYPE1, "投票");
		allTypes.put(TYPE2, "打擂");
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
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 新闻详情_ID */
	private Long informationId;
	/** 新闻内容Id */
	private Long informationContentId;
	/** 标题 */
	private String title;
	/** 类型 */
	private Byte type;
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
	
	private Byte quesType;
	/** 状态3:正常 ,99:删除 */
	private Byte status;
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
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
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
	
	public Long getInformationId() {
		return informationId;
	}

	public void setInformationId(Long informationId) {
		this.informationId = informationId;
	}
    
	public Long getInformationContentId() {
		return informationContentId;
	}

	public void setInformationContentId(Long informationContentId) {
		this.informationContentId = informationContentId;
	}

	@Transient
	public Byte getQuesType() {
		return quesType;
	}

	public void setQuesType(Byte quesType) {
		this.quesType = quesType;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
