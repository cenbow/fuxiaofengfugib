package com.org.weixin.module.zjchj.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BillInfo {

	private String billSerialNumber;
	private String cashier;
	private int changeAmount;
	private String checkstand;
	private String consumeNum;
	private int couponAmount;
	private String deskNo;
	private int discountAmount;
	private List<GoodsDetail> goodsDetails;
	private String id;
	private int paidAmount;
	private int receivableAmount;
	private String saleTime;
	private String saler;
	private List<SettlementWay> settlementWay;
	private String shopEntityAddress;
	private String shopEntityName;
	private String shopName;
	private String telephone;
	private int totalFee;
	private int totalNum;

	public void setBillSerialNumber(String billSerialNumber) {
		this.billSerialNumber = billSerialNumber;
	}

	public String getBillSerialNumber() {
		return this.billSerialNumber;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getCashier() {
		return this.cashier;
	}

	public void setChangeAmount(int changeAmount) {
		this.changeAmount = changeAmount;
	}

	public int getChangeAmount() {
		return this.changeAmount;
	}

	public void setCheckstand(String checkstand) {
		this.checkstand = checkstand;
	}

	public String getCheckstand() {
		return this.checkstand;
	}

	public void setConsumeNum(String consumeNum) {
		this.consumeNum = consumeNum;
	}

	public String getConsumeNum() {
		return this.consumeNum;
	}

	public void setCouponAmount(int couponAmount) {
		this.couponAmount = couponAmount;
	}

	public int getCouponAmount() {
		return this.couponAmount;
	}

	public void setDeskNo(String deskNo) {
		this.deskNo = deskNo;
	}

	public String getDeskNo() {
		return this.deskNo;
	}

	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}

	public int getDiscountAmount() {
		return this.discountAmount;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	public int getPaidAmount() {
		return this.paidAmount;
	}

	public void setReceivableAmount(int receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	public int getReceivableAmount() {
		return this.receivableAmount;
	}

	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}

	public String getSaleTime() {
		return this.saleTime;
	}

	public void setSaler(String saler) {
		this.saler = saler;
	}

	public String getSaler() {
		return this.saler;
	}

	public void setShopEntityAddress(String shopEntityAddress) {
		this.shopEntityAddress = shopEntityAddress;
	}

	public String getShopEntityAddress() {
		return this.shopEntityAddress;
	}

	public void setShopEntityName(String shopEntityName) {
		this.shopEntityName = shopEntityName;
	}

	public String getShopEntityName() {
		return this.shopEntityName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public int getTotalFee() {
		return this.totalFee;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getTotalNum() {
		return this.totalNum;
	}
	
	public List<GoodsDetail> getGoodsDetails() {
		return goodsDetails;
	}

	public void setGoodsDetails(List<GoodsDetail> goodsDetails) {
		this.goodsDetails = goodsDetails;
	}

	public List<SettlementWay> getSettlementWay() {
		return settlementWay;
	}

	public void setSettlementWay(List<SettlementWay> settlementWay) {
		this.settlementWay = settlementWay;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}