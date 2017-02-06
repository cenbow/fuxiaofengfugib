package com.cqliving.log.util;

public class LoginUser {
    /** 用户ID*/
    private Long userId;

    /** 登录用户名 */
    private String username;
    
    /** 操作员姓名 */
    private String nickname;
    
    /** AppId */
    private Long appId;

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

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}