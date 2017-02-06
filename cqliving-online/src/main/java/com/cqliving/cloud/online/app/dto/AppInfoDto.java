package com.cqliving.cloud.online.app.dto;


import java.util.Date;

import com.cqliving.framework.common.domain.AbstractEntity;

/**
 * 客户端 DTO
 * Date: 2016-04-15 09:43:46
 * @author huxiaoping
 */
public class AppInfoDto extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 客户端名称 */
	private String name;
	/** 客户端后台名称 */
	private String cmsName;
	/** 客户端LOGO */
	private String logo;
	/** 所在区域 */
	private String location;
	/** 自定义域名 */
	private String appDomain;
	/** 后台域名 */
	private String cmsDomain;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 更新人ID */
	private Long updatorId;
	/** updator */
	private String updator;
	/** 更新时间 */
	private Date updateTime;
	/** 联系电话 */
	private String contactPhone;
	/** 传真 */
	private String fax;
	/** 地址 */
	private String address;
	/** 说明 */
	private String description;
	/** 最后经纬度 */
	private String position;
	/** 短信扩展码号 */
	private String smsCode;
	/** 前缀 */
	private String namePrefix;
	/** 极光推送 AppKey */
	private String jpushAppKey;
	/** 极光推送 AppSecret */
	private String jpushAppSecret;
	
	/** 管理账户用户名 */
    private String username;
    /** 登录密码 */
    private String password;
    /** 管理账户状态 */
    private Byte status;
    /** 管理账户Id */
    private Long userId;
    /** 天气Id */
    private Long weatherId;
    /** 七牛云Id */
    private Long qiniuId;
	
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCmsName() {
        return cmsName;
    }
    public void setCmsName(String cmsName) {
        this.cmsName = cmsName;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getAppDomain() {
        return appDomain;
    }
    public void setAppDomain(String appDomain) {
        this.appDomain = appDomain;
    }
    public String getCmsDomain() {
        return cmsDomain;
    }
    public void setCmsDomain(String cmsDomain) {
        this.cmsDomain = cmsDomain;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public Long getUpdatorId() {
        return updatorId;
    }
    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }
    public String getUpdator() {
        return updator;
    }
    public void setUpdator(String updator) {
        this.updator = updator;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Byte getStatus() {
        return status;
    }
    public void setStatus(Byte status) {
        this.status = status;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getSmsCode() {
        return smsCode;
    }
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
	public String getNamePrefix() {
		return namePrefix;
	}
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
	public String getJpushAppKey() {
		return jpushAppKey;
	}
	public void setJpushAppKey(String jpushAppKey) {
		this.jpushAppKey = jpushAppKey;
	}
	public String getJpushAppSecret() {
		return jpushAppSecret;
	}
	public void setJpushAppSecret(String jpushAppSecret) {
		this.jpushAppSecret = jpushAppSecret;
	}
    public Long getWeatherId() {
        return weatherId;
    }
    public void setWeatherId(Long weatherId) {
        this.weatherId = weatherId;
    }
    public Long getQiniuId() {
        return qiniuId;
    }
    public void setQiniuId(Long qiniuId) {
        this.qiniuId = qiniuId;
    }
}