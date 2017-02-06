package com.cqliving.cloud.online.app.domain;


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
 * 客户端发布市场资源表 Entity
 * Date: 2016-05-04 15:48:25
 * @author Code Generator
 */
@Entity
@Table(name = "app_marketplace_resource")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppMarketplaceResource extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** loading图 */
	public static final Integer IMAGETYPE0 = 0;
	/** 启动广告图 */
	public static final Integer IMAGETYPE1 = 1;
	/** 首页广告图 */
	public static final Integer IMAGETYPE2 = 2;
		
	/** 图片类型 */
	public static final Map<Integer, String> allImageTypes = Maps.newTreeMap();
	static {
		allImageTypes.put(IMAGETYPE0, "loading图");
		allImageTypes.put(IMAGETYPE1, "启动广告图");
		allImageTypes.put(IMAGETYPE2, "首页广告图");
	}
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
	
	/** 主键 */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 图片版本ID */
	private Long versionId;
	/** 图片类型 */
	private Integer imageType;
	/** 图片地址 */
	private String imageUrl;
	/** 广告链接地址 */
	private String linkUrl;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 更新时间 */
	private Date updateTime;
	/** 状态 */
	private Byte status;
	/** 广告显示时长(倒计时多少秒)，单位：秒 */
	private Integer showTime;
	
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
	public Long getVersionId(){
		return this.versionId;
	}
	
	public void setVersionId(Long versionId){
		this.versionId = versionId;
	}
	public Integer getImageType(){
		return this.imageType;
	}
	
	public void setImageType(Integer imageType){
		this.imageType = imageType;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public String getLinkUrl(){
		return this.linkUrl;
	}
	
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
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
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public Integer getShowTime() {
        return showTime;
    }

    public void setShowTime(Integer showTime) {
        this.showTime = showTime;
    }

    public void setStatus(Byte status){
		this.status = status;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}