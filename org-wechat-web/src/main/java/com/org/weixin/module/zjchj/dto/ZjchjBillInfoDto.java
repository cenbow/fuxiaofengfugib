package com.org.weixin.module.zjchj.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ZjchjBillInfoDto {

	/** ID */
	private Long id;
	/** 用户id */
	private Long userId;
	/** 业务流水号 */
	private String serialId;
	/** 订单号 */
	private String billSerialNumber;
	/** 第三方open_id */
	private String openId;
	/** 门店名称 */
	private String shopName;
	/** 菜品号 */
	private String itemsErial;
	/** 菜品名称 */
	private String itemsName;
	/** 创建时间 */
	private Date createTime;
	
	//冗余字段
	/** 消费次数 */
	private Integer consumeTime;
	/** 消费人数 */
	private Integer consumePeople;
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getSerialId(){
		return this.serialId;
	}
	
	public void setSerialId(String serialId){
		this.serialId = serialId;
	}
	public String getBillSerialNumber(){
		return this.billSerialNumber;
	}
	
	public void setBillSerialNumber(String billSerialNumber){
		this.billSerialNumber = billSerialNumber;
	}
	public String getOpenId(){
		return this.openId;
	}
	
	public void setOpenId(String openId){
		this.openId = openId;
	}
	public String getShopName(){
		return this.shopName;
	}
	
	public void setShopName(String shopName){
		this.shopName = shopName;
	}
	public String getItemsErial(){
		return this.itemsErial;
	}
	
	public void setItemsErial(String itemsErial){
		this.itemsErial = itemsErial;
	}
	public String getItemsName(){
		return this.itemsName;
	}
	
	public void setItemsName(String itemsName){
		this.itemsName = itemsName;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Integer getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Integer consumeTime) {
		this.consumeTime = consumeTime;
	}

	public Integer getConsumePeople() {
		return consumePeople;
	}

	public void setConsumePeople(Integer consumePeople) {
		this.consumePeople = consumePeople;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
