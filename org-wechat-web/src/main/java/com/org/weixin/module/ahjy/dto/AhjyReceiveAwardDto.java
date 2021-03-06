package com.org.weixin.module.ahjy.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:金源活动表
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author yuwu on 2016年3月27日
 */
public class AhjyReceiveAwardDto {

    public static final int RESULT_FLAG1 = 1;
    public static final int RESULT_FLAG2 = 2;
    public static final int RESULT_FLAG3 = 3;
	/** 返回标识{1：手机号码不正确，2：机会用尽，3：没有参加活动 } */
	private Integer resultFlag;
	/** 实物CODE */
    private String awardPhysical;
    /** 实物图像URL */
    private String img;
    /** 积分CODE */
    private String awardIntegral;
    /** 优惠券CODE */
    private String awardCoupon;
    /** 活动表ID */
    private Long activityId;
    
    
	public Integer getResultFlag() {
        return resultFlag;
    }
    public void setResultFlag(Integer resultFlag) {
        this.resultFlag = resultFlag;
    }
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
    public Long getActivityId() {
        return activityId;
    }
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}