package com.cqliving.cloud.online.config.dto;

public class PermissionDto {
    /** id */
    private Long id;
    /** 所属App */
    private Long appId;
    /** 资源名称 */
    private String name;
    /** 资源请求URL，根据该字段和用户请求的判断做比较来判断是否拥有权限值 */
    private String url;
    /** 表sys_res_typer的ID */
    private Long sysResTypeId;
    /**-- 客户端资源权限表id,是否必须登陆,是对接口请求进行签名验证,*/
    private Long appPermissionId;
    // 以下是客户端对应资源的值
    /** 是否有该方法的请求权限 */
    private Byte isPermission;
    /** 是否必须登录 */
    private Byte isLogin;
    /** 是对接口请求进行签名验证 */
    private Byte isSign;
    /** 是否验证sessionId为空 */
    private Byte isSessionId;
    /** 是否控制接口请求次数 */
    private Byte isRequestTimes;
    /** 请求次数间隔时间，单位分钟。request_times_interval分钟内，最多只能请求request_times_limit次 */
    private Integer requestTimesInterval;
    /** 请求限制次数，request_times_interval分钟内，最多只能请求request_times_limit次 */
    private Integer requestTimesLimit;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAppId() {
        return appId;
    }
    public void setAppId(Long appId) {
        this.appId = appId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Long getSysResTypeId() {
        return sysResTypeId;
    }
    public void setSysResTypeId(Long sysResTypeId) {
        this.sysResTypeId = sysResTypeId;
    }
    public Long getAppPermissionId() {
        return appPermissionId;
    }
    public void setAppPermissionId(Long appPermissionId) {
        this.appPermissionId = appPermissionId;
    }
    public Byte getIsPermission() {
        return isPermission;
    }
    public void setIsPermission(Byte isPermission) {
        this.isPermission = isPermission;
    }
    public Byte getIsLogin() {
        return isLogin;
    }
    public void setIsLogin(Byte isLogin) {
        this.isLogin = isLogin;
    }
    public Byte getIsSign() {
        return isSign;
    }
    public void setIsSign(Byte isSign) {
        this.isSign = isSign;
    }
    public Byte getIsSessionId() {
        return isSessionId;
    }
    public void setIsSessionId(Byte isSessionId) {
        this.isSessionId = isSessionId;
    }
    public Byte getIsRequestTimes() {
        return isRequestTimes;
    }
    public void setIsRequestTimes(Byte isRequestTimes) {
        this.isRequestTimes = isRequestTimes;
    }
    public Integer getRequestTimesInterval() {
        return requestTimesInterval;
    }
    public void setRequestTimesInterval(Integer requestTimesInterval) {
        this.requestTimesInterval = requestTimesInterval;
    }
    public Integer getRequestTimesLimit() {
        return requestTimesLimit;
    }
    public void setRequestTimesLimit(Integer requestTimesLimit) {
        this.requestTimesLimit = requestTimesLimit;
    }    
}