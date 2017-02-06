package com.cqliving.cloud.online.interfacc.dto;

/**
 * Title：微信小程序的用户信息
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2017年1月23日
 */
public class WxlUserInfo {
    
    /**  */
    private String openId;
    /**  */
    private String nickName;
    /**  */
    private String gender;
    /**  */
    private String city;
    /**  */
    private String province;
    /**  */
    private String country;
    /**  */
    private String avatarUrl;
    /**  */
    private String unionId;
    /**  */
    private String watermark;
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public String getUnionId() {
        return unionId;
    }
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
    public String getWatermark() {
        return watermark;
    }
    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }
    
}
