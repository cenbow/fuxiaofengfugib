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
 * 活动表 Entity
 * Date: 2016-06-07 09:21:22
 * @author Code Generator
 */
@Entity
@Table(name = "act_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 保存 */
	public static final Byte STATUS1 = 1;
	/** 已发布 */
	public static final Byte STATUS3 = 3;
	/** 已下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
	/** 是否推荐到首页{0:否,1:是}，首页展示  */
	public static final Byte ISRECOMMEND0 = 0;
	/** 是否推荐到首页{0:否,1:是}，首页展示  */
	public static final Byte ISRECOMMEND1 = 1;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	public static final Map<Byte, String> allsRecommends = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "保存");
		allStatuss.put(STATUS3, "已发布");
		allStatuss.put(STATUS88, "已下线");
		allStatuss.put(STATUS99, "删除");
		
		allsRecommends.put(ISRECOMMEND0, "否");
		allsRecommends.put(ISRECOMMEND1, "是");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 标题 */
	private String title;
	/** 活动开始时间 */
	private Date startTime;
	/** 活动结束时间 */
	private Date endTime;
	/** 回复量 */
	private Integer replyCount;
	/** 点赞量 */
	private Integer priseCount;
	/** 摘要 */
	private String digest;
	/** 图片列表，只能一张 */
	private String listImageUrl;
	/** 活动列表图片，只多三张 */
	private String actImageUrl;
	/** 内容,包含HTML标签的富文本内容 */
	private String content;
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
	/** 排序号 */
	private Integer sortNo;
	/** 是否推荐到首页{0:否,1:是}，首页展示 */
	private Byte isRecommend;
	/** 推荐到首页图片 */
	private String recommendImageUrl;
	
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
	public Integer getReplyCount(){
		return this.replyCount;
	}
	
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public Integer getPriseCount(){
		return this.priseCount;
	}
	
	public void setPriseCount(Integer priseCount){
		this.priseCount = priseCount;
	}
	public String getDigest(){
		return this.digest;
	}
	
	public void setDigest(String digest){
		this.digest = digest;
	}
	public String getListImageUrl(){
		return this.listImageUrl;
	}
	
	public void setListImageUrl(String listImageUrl){
		this.listImageUrl = listImageUrl;
	}
	public String getActImageUrl(){
		return this.actImageUrl;
	}
	
	public void setActImageUrl(String actImageUrl){
		this.actImageUrl = actImageUrl;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
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
	
	public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Byte getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getRecommendImageUrl() {
        return recommendImageUrl;
    }
    
    public void setRecommendImageUrl(String recommendImageUrl) {
        this.recommendImageUrl = recommendImageUrl;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
