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
 * 活动答题表 Entity
 * Date: 2016-06-07 09:22:07
 * @author Code Generator
 */
@Entity
@Table(name = "act_test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActTest extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 答题 */
	public static final Byte ACTTYPE4 = 4;
	/** 问卷 */
	public static final Byte ACTTYPE5 = 5;
		
	/** 活动类型 */
	public static final Map<Byte, String> allActTypes = Maps.newTreeMap();
	static {
		allActTypes.put(ACTTYPE4, "答题");
		allActTypes.put(ACTTYPE5, "问卷");
	}
	/** 普通答题 */
	public static final Byte TYPE1 = 1;
	/** 分类答题 */
	public static final Byte TYPE2 = 2;
		
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE1, "普通答题");
		allTypes.put(TYPE2, "分类答题");
	}
	/** 不限制 */
	public static final Byte LIMITANSWERTYPE0 = 0;
	/** 限制时间 */
	public static final Byte LIMITANSWERTYPE1 = 1;
		
	/** 类型 */
	public static final Map<Byte, String> allLimitAnswerTypes = Maps.newTreeMap();
	static {
		allLimitAnswerTypes.put(LIMITANSWERTYPE0, "不限制");
		allLimitAnswerTypes.put(LIMITANSWERTYPE1, "限制时间");
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
	public static final Byte ISSETSCORE0 = 0;
	/** 是 */
	public static final Byte ISSETSCORE1 = 1;
		
	/** 是否设置题目分值 */
	public static final Map<Byte, String> allIsSetScores = Maps.newTreeMap();
	static {
		allIsSetScores.put(ISSETSCORE0, "否");
		allIsSetScores.put(ISSETSCORE1, "是");
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
	private Byte actType;
	/** 类型 */
	private Byte type;
	/** 活动开始时间 */
	private Date startTime;
	/** 活动结束时间 */
	private Date endTime;
	/** 类型 */
	private Byte limitAnswerType;
	/** 限制答题时间，当limit_answer_type=0，该值无效 */
	private Integer limitAnswerTimes;
	/** 是否登陆后才能操作 */
	private Byte loggedStatus;
	/** 是否设置题目分值 */
	private Byte isSetScore;
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
	public Byte getActType(){
		return this.actType;
	}
	
	public void setActType(Byte actType){
		this.actType = actType;
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
	public Byte getLimitAnswerType(){
		return this.limitAnswerType;
	}
	
	public void setLimitAnswerType(Byte limitAnswerType){
		this.limitAnswerType = limitAnswerType;
	}
	public Integer getLimitAnswerTimes(){
		return this.limitAnswerTimes;
	}
	
	public void setLimitAnswerTimes(Integer limitAnswerTimes){
		this.limitAnswerTimes = limitAnswerTimes;
	}
	public Byte getLoggedStatus(){
		return this.loggedStatus;
	}
	
	public void setLoggedStatus(Byte loggedStatus){
		this.loggedStatus = loggedStatus;
	}
	public Byte getIsSetScore(){
		return this.isSetScore;
	}
	
	public void setIsSetScore(Byte isSetScore){
		this.isSetScore = isSetScore;
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
