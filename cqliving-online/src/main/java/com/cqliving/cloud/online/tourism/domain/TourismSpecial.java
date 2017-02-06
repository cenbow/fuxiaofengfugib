package com.cqliving.cloud.online.tourism.domain;


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
 * 旅游专题关系表 Entity
 * Date: 2016-08-23 13:55:36
 * @author Code Generator
 */
@Entity
@Table(name = "tourism_special")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TourismSpecial extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 保存 */
	public static final Byte STATUS1 = 1;
	/** 已上线 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "保存");
		allStatuss.put(STATUS3, "已上线");
		allStatuss.put(STATUS88, "下线");
	}
	
	/** ID */
	private Long id;
	/** 专题旅游ID（tourism_info表ID） */
	private Long tourismId;
	/** 非专题旅游表ID（tourism_info表ID） */
	private Long refTourismId;
	/** 状态 */
	private Byte status;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人 */
	private String creator;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getTourismId(){
		return this.tourismId;
	}
	
	public void setTourismId(Long tourismId){
		this.tourismId = tourismId;
	}
	public Long getRefTourismId(){
		return this.refTourismId;
	}
	
	public void setRefTourismId(Long refTourismId){
		this.refTourismId = refTourismId;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
