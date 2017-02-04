package com.org.weixin.module.zjchj.domain;


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
 * 爆款菜品表 Entity
 * Date: 2016-09-26 15:18:43
 * @author Code Generator
 */
@Entity
@Table(name = "zjchj_goods_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZjchjGoodsInfo extends AbstractEntity implements Comparable<ZjchjGoodsInfo> {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;
	/** 店铺名称 */
	private String shopName;
	/** 店铺LOGO */
	private String shopLogo;
	/** 店铺地址 */
	private String shopAddr;
	/** 店铺排序关键字 */
	private String shopSortKey;
	/** 爆款菜品名称 */
	private String name;
	/** 图片名称 */
	private String image;
	/** 创建时间 */
	private Date createTime;
	/** 备用菜品名称 */
	private String otherNames;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopAddr() {
		return shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}

	public String getShopSortKey() {
		return shopSortKey;
	}

	public void setShopSortKey(String shopSortKey) {
		this.shopSortKey = shopSortKey;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getImage(){
		return this.image;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public String getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZjchjGoodsInfo other = (ZjchjGoodsInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(ZjchjGoodsInfo o) {
		return this.getId().intValue() - o.getId().intValue();
	}

}
