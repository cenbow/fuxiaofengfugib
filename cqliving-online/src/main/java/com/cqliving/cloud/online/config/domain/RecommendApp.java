package com.cqliving.cloud.online.config.domain;


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
 * 区县推荐APP表，在重庆APP中使用 Entity
 * Date: 2016-10-26 17:18:11
 * @author Code Generator
 */
@Entity
@Table(name = "recommend_app")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RecommendApp extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 上线 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "上线");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** 不展示 */
	public static final Byte IS_CAROUSEL_0 = 0;
	/** 展示 */
	public static final Byte IS_CAROUSEL_1 = 1;
		
	/** 状态 */
	public static final Map<Byte, String> allIsCarousels = Maps.newTreeMap();
	static {
		allIsCarousels.put(IS_CAROUSEL_0, "不展示");
		allIsCarousels.put(IS_CAROUSEL_1, "展示");
	}
	
	/** ID */
	private Long id;
	/** 客户端ID,APP_INFO表ID，一个客户端上面可以有多个推荐客户端 */
	private Long appId;
	/** 推荐客户端ID,APP_INFO表ID */
	private Long recommendAppId;
	/** 推荐客户端的栏目表ID,app_columns表的ID */
	private Long columnsId;
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
	/** 是否展示轮播数据 */
	private Byte isCarousel;
	
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
	public Long getRecommendAppId(){
		return this.recommendAppId;
	}
	
	public void setRecommendAppId(Long recommendAppId){
		this.recommendAppId = recommendAppId;
	}
	public Long getColumnsId(){
		return this.columnsId;
	}
	
	public void setColumnsId(Long columnsId){
		this.columnsId = columnsId;
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
	
	public Byte getIsCarousel() {
		return isCarousel;
	}

	public void setIsCarousel(Byte isCarousel) {
		this.isCarousel = isCarousel;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
