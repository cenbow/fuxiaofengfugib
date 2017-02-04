package com.org.weixin.module.zjchj.dto;


import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.org.weixin.module.zjchj.domain.ZjchjGoodsInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年9月27日
 */
public class ZjchjShopInfoDto {

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
	
	//冗余字段
	/** 菜品列表 */
	private List<ZjchjGoodsInfo> goodsInfos;
	
	public Long getId(){
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

	public List<ZjchjGoodsInfo> getGoodsInfos() {
		return goodsInfos;
	}

	public void setGoodsInfos(List<ZjchjGoodsInfo> goodsInfos) {
		this.goodsInfos = goodsInfos;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
