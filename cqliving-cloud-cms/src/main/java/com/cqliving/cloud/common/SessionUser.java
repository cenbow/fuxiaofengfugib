package com.cqliving.cloud.common;

import java.io.Serializable;
import java.util.Set;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.cloud.online.security.domain.SysUser;

/**
 * User: wangyx
 * Date: 14-3-26
 * Time: 下午7:20
 */

public class SessionUser implements HttpSessionBindingListener,Serializable{

	private static final long serialVersionUID = 1L;

	public static final String SESSION_USER_OBJECT_KEY="session_user_obj";

    private SessionUser(){}

    public static SessionUser bulider(SysUser sysUser){
        return new SessionUser().update(sysUser);
    }

    public SessionUser update(SysUser sysUser){
        this.setUserId(sysUser.getId());
        this.setUsername(sysUser.getUsername());
        this.setNickname(sysUser.getNickname());
        this.setUsertype(sysUser.getUsertype());
        this.setRole(sysUser.getRole());
        this.setAppId(sysUser.getAppId());
        this.setImgUrl(sysUser.getImgUrl());
        return this;
    }

	public Byte getUsertype() {
		return usertype;
	}

	public void setUsertype(Byte usertype) {
		this.usertype = usertype;
	}

	/**
	 * @return the role
	 */
	public Set<SysRole> getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Set<SysRole> role) {
		this.role = role;
	}

	/** 用户ID*/
	private Long userId;

	/** 登录用户名 */
	private String username;
	
	/** 操作员姓名 */
    private String nickname;
	
	/** 用户类型 */
	private Byte usertype;

    /** 角色 */
    private Set<SysRole> role;
	
    private Long appId;

    private String imgUrl;
    
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        if(SESSION_USER_OBJECT_KEY.equals(event.getName())
                && event.getValue() instanceof SessionUser){
            SessionUser sessionUser = (SessionUser) event.getValue();
            SessionFace.userMap.put(sessionUser.getUsername(), sessionUser);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        if(SESSION_USER_OBJECT_KEY.equals(event.getName())
                && event.getValue() instanceof SessionUser){
            SessionUser sessionUser = (SessionUser) event.getValue();
            SessionFace.userMap.remove(sessionUser.getUsername());
        }
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
    
}
