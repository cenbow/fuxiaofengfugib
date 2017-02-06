package com.cqliving.cloud.online.account.dto;

import java.util.Date;

public class UserDto {
    
    /** id */
    private Long id;
    /** 用户名 */
    private String userName;
    /** 手机号 */
    private String telephone;
    /** 邮箱 */
    private String email;
    /** 密码 */
    private String password;
    /** 来源,字符串，类似 手机，WAP，网站 */
    private String source;
    /** 来源ID,用户来源的区县ID */
    private Long appId;
    /** 类型 */
    private Byte type;
    /** 状态 */
    private Byte status;
    /** 注册时间 */
    private Date registTime;
    /** 所处经度 */
    private String lng;
    /** 所处纬度 */
    private String lat;
    /** 所处位置 */
    private String place;
    /** 最后登录时间 */
    private Date lastLoginTime;
    /** 最后登录IP */
    private String loginIp;
    
    /** 姓名 */
    private String name;
    /** 头像 */
    private String imgUrl;
    /** 性别 */
    private Byte sex;
    /** 个性签名 */
    private String speciality;
    /** 最后修改时间 */
    private Date updateTime;
    
    
    /** 客户端名称 */
    private String appName;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public Long getAppId() {
        return appId;
    }
    public void setAppId(Long appId) {
        this.appId = appId;
    }
    public Byte getType() {
        return type;
    }
    public void setType(Byte type) {
        this.type = type;
    }
    public Byte getStatus() {
        return status;
    }
    public void setStatus(Byte status) {
        this.status = status;
    }
    public Date getRegistTime() {
        return registTime;
    }
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public Byte getSex() {
        return sex;
    }
    public void setSex(Byte sex) {
        this.sex = sex;
    }
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getLoginIp() {
        return loginIp;
    }
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getLng() {
        return lng;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
}