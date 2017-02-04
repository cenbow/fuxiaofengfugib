package com.org.weixin.module.ahjy.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title: 调用用户的抽奖接口--返回数据
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author YUWU on 2016年3月27日
 */
public class AhjyLuckyDrawJsonDto {
    
    /** 实物CODE */
    private String awardPhysical;
    /** 积分CODE */
    private String awardIntegral;
    /** 优惠券CODE */
    private String awardCoupon;
    
    public String getAwardPhysical() {
        return awardPhysical;
    }
    public void setAwardPhysical(String awardPhysical) {
        this.awardPhysical = awardPhysical;
    }
    public String getAwardIntegral() {
        return awardIntegral;
    }
    public void setAwardIntegral(String awardIntegral) {
        this.awardIntegral = awardIntegral;
    }
    public String getAwardCoupon() {
        return awardCoupon;
    }
    public void setAwardCoupon(String awardCoupon) {
        this.awardCoupon = awardCoupon;
    }
    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
