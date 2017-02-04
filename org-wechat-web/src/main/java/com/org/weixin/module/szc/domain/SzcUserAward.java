package com.org.weixin.module.szc.domain;


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
 * 奖品领取历史表 Entity
 * Date: 2016-09-02 11:24:23
 * @author Code Generator
 */
@Entity
@Table(name = "szc_user_award")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SzcUserAward extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 未中奖 */
	public static final Byte TAKESTATUS0 = 0;
	/** 未核销 */
	public static final Byte TAKESTATUS1 = 1;
	/** 已核销 */
	public static final Byte TAKESTATUS2 = 2;
		
	/** 领取状态 */
	public static final Map<Byte, String> allTakeStatuss = Maps.newTreeMap();
	static {
		allTakeStatuss.put(TAKESTATUS0, "未中奖");
		allTakeStatuss.put(TAKESTATUS1, "未核销");
		allTakeStatuss.put(TAKESTATUS2, "已核销");
	}
	
	/** ID */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 领奖手机号 */
	private String phone;
	/** 奖品CODE */
	private String awardCode;
	/** 领取状态 */
	private Byte takeStatus;
	/** 兑换码 */
	private String convertCode;
	/** 中奖时间 */
	private Date awardTime;
	/** 核销时间 */
	private Date verifyTime;
	/** 奖券名称 */
	private String awardName;
	/** 奖券图片 */
    private String awardImg;	
    /** 中奖区域  */
    private Integer district;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public String getPhone(){
		return this.phone;
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
	public Byte getTakeStatus(){
		return this.takeStatus;
	}
	
	public void setTakeStatus(Byte takeStatus){
		this.takeStatus = takeStatus;
	}
	public String getConvertCode(){
		return this.convertCode;
	}
	
	public void setConvertCode(String convertCode){
		this.convertCode = convertCode;
	}
	public Date getAwardTime(){
		return this.awardTime;
	}
	
	public void setAwardTime(Date awardTime){
		this.awardTime = awardTime;
	}
	public Date getVerifyTime(){
		return this.verifyTime;
	}
	
	public void setVerifyTime(Date verifyTime){
		this.verifyTime = verifyTime;
	}
	public String getAwardName(){
		return this.awardName;
	}
	
	public void setAwardName(String awardName){
		this.awardName = awardName;
	}
	
	public String getAwardImg() {
		return awardImg;
	}

	public void setAwardImg(String awardImg) {
		this.awardImg = awardImg;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
