package com.cqliving.cloud.online.joke.domain;


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
 * 段子表 Entity
 * Date: 2016-06-28 11:18:14
 * @author Code Generator
 */
@Entity
@Table(name = "joke_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JokeInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 审核不通过 */
	public static final Byte STATUS_1 = -1;
	/** 草稿 */
	public static final Byte STATUS0 = 0;
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
		allStatuss.put(STATUS_1, "审核不通过");
		allStatuss.put(STATUS0, "草稿");
		allStatuss.put(STATUS1, "保存");
		allStatuss.put(STATUS3, "已上线");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 状态 */
	private Byte status;
	/** 内容 */
	private String content;
	/** 点赞量 */
	private Integer praiseCount;
	/** 鄙视量 */
	private Integer despiseCount;
	/** 评论量 */
	private Integer replyCount;
	/** 上线时间 */
	private Date onlineTime;
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
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public Integer getPraiseCount(){
		return this.praiseCount;
	}
	
	public void setPraiseCount(Integer praiseCount){
		this.praiseCount = praiseCount;
	}
	public Integer getDespiseCount() {
		return despiseCount;
	}

	public void setDespiseCount(Integer despiseCount) {
		this.despiseCount = despiseCount;
	}

	public Integer getReplyCount(){
		return this.replyCount;
	}
	
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public Date getOnlineTime(){
		return this.onlineTime;
	}
	
	public void setOnlineTime(Date onlineTime){
		this.onlineTime = onlineTime;
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
