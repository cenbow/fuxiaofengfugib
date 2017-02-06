package com.cqliving.cloud.online.info.domain;


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
 * 相关资讯表 Entity
 * Date: 2016-04-29 16:22:23
 * @author Code Generator
 */
@Entity
@Table(name = "INFO_CORRELATION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoCorrelation extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 正常上线 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 资讯ID */
	private Long infoClassifyId;
	/** 关联资讯ID */
	private Long refInfoClassifyId;
	/** 分类ID,0表示无分类 */
	private Long themeId;
	/** 排序号，如无排序，默认0 */
	private Integer sortNo;
	/** 状态{3:发布,88:下线} */
	private Byte status;
	
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	
	public static final Map<Byte,String> statusMap = Maps.newHashMap();
	
	static{
		statusMap.put(STATUS3, "发布");
		statusMap.put(STATUS88, "下线");
	}
	
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
	
	public Long getInfoClassifyId() {
		return infoClassifyId;
	}

	public void setInfoClassifyId(Long infoClassifyId) {
		this.infoClassifyId = infoClassifyId;
	}

	public Long getRefInfoClassifyId() {
		return refInfoClassifyId;
	}

	public void setRefInfoClassifyId(Long refInfoClassifyId) {
		this.refInfoClassifyId = refInfoClassifyId;
	}

	public Long getThemeId(){
		return this.themeId;
	}
	
	public void setThemeId(Long themeId){
		this.themeId = themeId;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
