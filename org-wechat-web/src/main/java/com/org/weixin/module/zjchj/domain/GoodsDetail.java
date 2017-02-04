package com.org.weixin.module.zjchj.domain;
//

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//GoodsDetail.java
//Model file Generated using: 
//Vin.Favara's JSONExportV https://github.com/vivi7/JSONExport 
//(forked from Ahmed-Ali's JSONExport)
//

public class GoodsDetail  {

	private String itemserial;
	private String name;
	private int price;
	private int totalnum;
	private int totalprice;

	public void setItemserial(String itemserial) {
		this.itemserial = itemserial;
	}

	public String getItemserial() {
		return this.itemserial;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}

	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}

	public int getTotalnum() {
		return this.totalnum;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public int getTotalprice() {
		return this.totalprice;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
