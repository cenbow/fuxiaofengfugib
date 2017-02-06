package com.cqliving.cloud.online.shoot.domain;


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

/**
 * 随手拍图片表 Entity
 * Date: 2016-06-07 16:39:28
 * @author Code Generator
 */
@Entity
@Table(name = "shoot_images")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShootImages extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 随手拍_ID */
	private Long shootId;
	/** 系统文件表ID（对应INFO_IFLE表主键） */
	private Long infoFileId;
	/** 图片URL */
	private String imageUrl;
	/** 创建时间 */
	private Date createTime;
	/** 图片描述 */
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getShootId(){
		return this.shootId;
	}
	
	public void setShootId(Long shootId){
		this.shootId = shootId;
	}
	public Long getInfoFileId(){
		return this.infoFileId;
	}
	
	public void setInfoFileId(Long infoFileId){
		this.infoFileId = infoFileId;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
