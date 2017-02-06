package com.cqliving.cloud.online.shopping.domain;


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
 * 商品图片表 Entity
 * Date: 2016-11-17 15:41:36
 * @author Code Generator
 */
@Entity
@Table(name = "shopping_images")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShoppingImages extends AbstractEntity {

	private static final long serialVersionUID = 1L;

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
	/** 商品_ID */
	private Long shoppingGoodsId;
	/** 系统文件表ID（对应INFO_FILE表主键） */
	private Long infoFileId;
	/** 图片URL */
	private String url;
	/** 排序号 */
	private Integer sortNo;
	/** 状态 */
	private Byte status;
	/** 图片描述 */
	private String description;
	/** 创建时间 */
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getShoppingGoodsId(){
		return this.shoppingGoodsId;
	}
	
	public void setShoppingGoodsId(Long shoppingGoodsId){
		this.shoppingGoodsId = shoppingGoodsId;
	}
	public Long getInfoFileId(){
		return this.infoFileId;
	}
	
	public void setInfoFileId(Long infoFileId){
		this.infoFileId = infoFileId;
	}
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
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
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
