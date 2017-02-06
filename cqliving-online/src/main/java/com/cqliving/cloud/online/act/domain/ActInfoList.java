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
 * 活动集合表，一个活动包含 Entity
 * Date: 2016-06-07 09:21:44
 * @author Code Generator
 */
@Entity
@Table(name = "act_info_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActInfoList extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 公告 */
	public static final Byte TYPE1 = 1;
	/** 外链 */
	public static final Byte TYPE2 = 2;
	/** 投票 */
	public static final Byte TYPE3 = 3;
	/** 答题 */
	public static final Byte TYPE4 = 4;
	/** 报名 */
	public static final Byte TYPE5 = 5;
	/** 问卷 */
	public static final Byte TYPE6 = 6;
	/** 征集 */
	public static final Byte TYPE7 = 7;
	/** 抽奖 */
	public static final Byte TYPE8 = 8;
		
	/** 未激活 */
	public static final Byte STATUS1 = 1;
	/** 已激活 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
	
	/** 活动类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	/** 活动类型 */
	public static final Map<String, String> allTypesStr = Maps.newTreeMap();
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	/** 活动在前端展示类型 */
	public static final Map<Byte, String> allShowTypes = Maps.newTreeMap();
	/** 活动在前端展示类型 */
	public static final Map<String, String> allShowTypesStr = Maps.newTreeMap();
	static {
		
		allTypes.put(TYPE1, "公告");
		allTypes.put(TYPE2, "外链");
		allTypes.put(TYPE3, "投票");
		allTypes.put(TYPE4, "答题");
//		allTypes.put(TYPE5, "报名");
//		allTypes.put(TYPE6, "问卷");
//		allTypes.put(TYPE7, "征集");
//		allTypes.put(TYPE8, "抽奖");
		
	    allTypesStr.put(TYPE1+"", "公告");
	    allTypesStr.put(TYPE2+"", "外链");
	    allTypesStr.put(TYPE3+"", "投票");
	    allTypesStr.put(TYPE4+"", "答题");
//	    allTypesStr.put(TYPE5+"", "报名");
//	    allTypesStr.put(TYPE6+"", "问卷");
//	    allTypesStr.put(TYPE7+"", "征集");
//	    allTypesStr.put(TYPE8+"", "抽奖");
	    
	    allStatuss.put(STATUS1, "未激活");
		allStatuss.put(STATUS3, "已激活");
		allStatuss.put(STATUS99, "删除");
		
		allShowTypes.put(TYPE1, "公告");
		allShowTypes.put(TYPE2, "外链");
		allShowTypes.put(TYPE3, "投票");
		allShowTypes.put(TYPE4, "答题");
		allShowTypes.put(TYPE5, "报名");
		allShowTypes.put(TYPE6, "问卷");
		allShowTypes.put(TYPE7, "征集");
		allShowTypes.put(TYPE8, "抽奖");
		
		allShowTypesStr.put(TYPE1+"", "公告");
		allShowTypesStr.put(TYPE2+"", "外链");
		allShowTypesStr.put(TYPE3+"", "投票");
		allShowTypesStr.put(TYPE4+"", "答题");
		allShowTypesStr.put(TYPE5+"", "报名");
		allShowTypesStr.put(TYPE6+"", "问卷");
		allShowTypesStr.put(TYPE7+"", "征集");
		allShowTypesStr.put(TYPE8+"", "抽奖");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 活动ID（act_info表主键） */
	private Long actInfoId;
	/** 活动类型 */
	private Byte type;
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
	/** 活动在前端展示类型{1:公告,2:外链,3:投票,4:答题,5:报名,6:问卷,7:征集,8:抽奖}*/
	private Byte showType;
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
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
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
	
	public Byte getShowType() {
		return showType;
	}

	public void setShowType(Byte showType) {
		this.showType = showType;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
