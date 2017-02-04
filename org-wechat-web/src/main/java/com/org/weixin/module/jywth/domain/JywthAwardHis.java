package com.org.weixin.module.jywth.domain;


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
import org.wechat.framework.domain.AbstractEntity;

import com.google.common.collect.Maps;

/**
 * 红包领取历史表 Entity
 *
 * Date: 2016-04-02 13:11:46
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "jywth_award_his")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JywthAwardHis extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 未领取 */
	public static final byte TAKESTATUS0 = 0;
	/** 已领取 */
	public static final byte TAKESTATUS1 = 1;
	/** 已核销 */
	public static final byte TAKESTATUS2 = 2;
	/** 领取状态{0:未领取,1:已领取} */
		public static final Map<Byte, String> allTakeStatuss = Maps.newTreeMap();
	static {
		allTakeStatuss.put(TAKESTATUS0, "未领取");
		allTakeStatuss.put(TAKESTATUS1, "已领取");
		allTakeStatuss.put(TAKESTATUS2, "已核销");
	}
	
	/** ID */
	private Long id;
	/** 领奖手机号 */
	private String phone;
	/** 奖品CODE */
	private String awardCode;
	/** 领取状态{0:未领取,1:已领取,2:已核销} */
	private byte takeStatus;
	/** 领取时间 */
	private Date takeTime;
	/** 领取数量 */
	private Integer takeNum;
	/** 领奖兑换码 */
	private String exchangeCode;
	//中奖时间
	private Date awardTime;
	//核销时间
	private Date verifyTime;
	//奖券名称
	private String awardName;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getPhone(){
		return this.phone;
	}
	
	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getAwardCode(){
		return this.awardCode;
	}
	
	public void setAwardCode(String awardCode){
		this.awardCode = awardCode;
	}
	public byte getTakeStatus(){
		return this.takeStatus;
	}
	
	public void setTakeStatus(byte takeStatus){
		this.takeStatus = takeStatus;
	}
	public Date getTakeTime(){
		return this.takeTime;
	}
	
	public Date getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(Date awardTime) {
		this.awardTime = awardTime;
	}

	public void setTakeTime(Date takeTime){
		this.takeTime = takeTime;
	}
	public Integer getTakeNum(){
		return this.takeNum;
	}
	
	public void setTakeNum(Integer takeNum){
		this.takeNum = takeNum;
	}
	public String getExchangeCode(){
		return this.exchangeCode;
	}
	
	public void setExchangeCode(String exchangeCode){
		this.exchangeCode = exchangeCode;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
