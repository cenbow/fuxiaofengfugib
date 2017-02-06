package com.cqliving.manager.security.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/5/20.
 *
 * 登陆主体对象
 */
public class SubjectInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 加密后的密码 */
    private String password;

    /** 加密盐值*/
    private String pwdSalt;
    
    /** 状态 */
	private Integer status;
	
	/** 过期时间 */
	private Date expiredDate;
	
	/** 解锁时间 */
	private Date unlockDate;
	/** 当前用户的APPId */
	private Long appId;
	
	private String imgUrl;
	
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Date getUnlockDate() {
		return unlockDate;
	}

	public void setUnlockDate(Date unlockDate) {
		this.unlockDate = unlockDate;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwdSalt() {
        return pwdSalt;
    }

    public void setPwdSalt(String pwdSalt) {
        this.pwdSalt = pwdSalt;
    }

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
    
}
