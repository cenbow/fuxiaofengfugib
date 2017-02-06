package com.cqliving.cloud.online.topic.domain;


import java.util.Map;
import java.util.Date;

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
 * 话题表 Entity
 * Date: 2016-07-14 09:45:12
 * @author Code Generator
 */
@Entity
@Table(name = "topic_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TopicInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 审核不通过 */
	public static final Byte STATUS_1 = -1;
	/** 待审核(保存) */
	public static final Byte STATUS1 = 1;
	/** 已发布 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS_1, "审核不通过");
		allStatuss.put(STATUS1, "待审核(草稿)");
		allStatuss.put(STATUS3, "已发布");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	/** 否 */
	public static final Byte ISTOP0 = 0;
	/** 是 */
	public static final Byte ISTOP1 = 1;
		
	/** 是否置顶 */
	public static final Map<Byte, String> allIsTops = Maps.newTreeMap();
	static {
		allIsTops.put(ISTOP0, "否");
		allIsTops.put(ISTOP1, "是");
	}
	/** 否 */
	public static final Byte ISRECOMMEND0 = 0;
	/** 是 */
	public static final Byte ISRECOMMEND1 = 1;
		
	/** 是否推荐到首页 */
	public static final Map<Byte, String> allIsRecommends = Maps.newTreeMap();
	static {
		allIsRecommends.put(ISRECOMMEND0, "否");
		allIsRecommends.put(ISRECOMMEND1, "是");
	}
	/** APP用户 */
	public static final Byte SOURCETYPE1 = 1;
	/** 后台用户 */
	public static final Byte SOURCETYPE2 = 2;
		
	/** 话题来源 */
	public static final Map<Byte, String> allSourceTypes = Maps.newTreeMap();
	static {
		allSourceTypes.put(SOURCETYPE1, "APP用户");
		allSourceTypes.put(SOURCETYPE2, "后台用户");
	}
	/** 否 */
	public static final Byte ISAUDIT0 = 0;
	/** 是 */
	public static final Byte ISAUDIT1 = 1;
		
	/** 是否已审核 */
	public static final Map<Byte, String> allIsAudits = Maps.newTreeMap();
	static {
		allIsAudits.put(ISAUDIT0, "否");
		allIsAudits.put(ISAUDIT1, "是");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 话题名称 */
	private String name;
	/** 话题内容 */
	private String content;
	/** 话题列表图片，取用户上传的第一张图，没有取默认图 */
	private String listImageUrl;
	/** 状态 */
	private Byte status;
	/** 是否置顶 */
	private Byte isTop;
	/** 置顶图片 */
	private String topImageUrl;
	/** 是否推荐到首页 */
	private Byte isRecommend;
	/** 推荐到首页图片 */
	private String recommendImageUrl;
	/** 话题来源 */
	private Byte sourceType;
	/** 评论量 */
	private Integer replyCount;
	/** 审核时间 */
	private Date auditTime;
	/** 是否已审核 */
	private Byte isAudit;
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
	/** 话题类型ids，引用config_common_type表的id，如,1,2,3,4,5, */
	private String types;
	
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
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getListImageUrl(){
		return this.listImageUrl;
	}
	
	public void setListImageUrl(String listImageUrl){
		this.listImageUrl = listImageUrl;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Byte getIsTop(){
		return this.isTop;
	}
	
	public void setIsTop(Byte isTop){
		this.isTop = isTop;
	}
	public String getTopImageUrl(){
		return this.topImageUrl;
	}
	
	public void setTopImageUrl(String topImageUrl){
		this.topImageUrl = topImageUrl;
	}
	public Byte getIsRecommend(){
		return this.isRecommend;
	}
	
	public void setIsRecommend(Byte isRecommend){
		this.isRecommend = isRecommend;
	}
	public String getRecommendImageUrl(){
		return this.recommendImageUrl;
	}
	
	public void setRecommendImageUrl(String recommendImageUrl){
		this.recommendImageUrl = recommendImageUrl;
	}
	public Byte getSourceType(){
		return this.sourceType;
	}
	
	public void setSourceType(Byte sourceType){
		this.sourceType = sourceType;
	}
	public Integer getReplyCount(){
		return this.replyCount;
	}
	
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public Date getAuditTime(){
		return this.auditTime;
	}
	
	public void setAuditTime(Date auditTime){
		this.auditTime = auditTime;
	}
	public Byte getIsAudit(){
		return this.isAudit;
	}
	
	public void setIsAudit(Byte isAudit){
		this.isAudit = isAudit;
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
	
	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
