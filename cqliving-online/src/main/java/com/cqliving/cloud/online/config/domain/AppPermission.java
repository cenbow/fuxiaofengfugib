package com.cqliving.cloud.online.config.domain;


import java.util.Map;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 客户端资源权限表 Entity
 * Date: 2016-12-14 16:50:46
 * @author Code Generator
 */
@Entity
@Table(name = "app_permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppPermission extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 否 */
	public static final Byte ISPERMISSION0 = 0;
	/** 是 */
	public static final Byte ISPERMISSION1 = 1;
	
	/** 是否有该方法的请求权限 */
	public static final Map<Byte, String> allIsPermissions = Maps.newTreeMap();
	static {
		allIsPermissions.put(ISPERMISSION0, "否");
		allIsPermissions.put(ISPERMISSION1, "是");
	}
	
	/** 否 */
	public static final Byte ISLOGIN0 = 0;
	/** 是 */
	public static final Byte ISLOGIN1 = 1;
		
	/** 是否必须登录 */
	public static final Map<Byte, String> allIsLogins = Maps.newTreeMap();
	static {
		allIsLogins.put(ISLOGIN0, "否");
		allIsLogins.put(ISLOGIN1, "是");
	}
	/** 否 */
	public static final Byte ISSIGN0 = 0;
	/** 是 */
	public static final Byte ISSIGN1 = 1;
		
	/** 是对接口请求进行签名验证 */
	public static final Map<Byte, String> allIsSigns = Maps.newTreeMap();
	static {
		allIsSigns.put(ISSIGN0, "否");
		allIsSigns.put(ISSIGN1, "是");
	}
	/** 否 */
	public static final Byte ISSESSIONID0 = 0;
	/** 是 */
	public static final Byte ISSESSIONID1 = 1;
		
	/** 是否验证sessionId为空 */
	public static final Map<Byte, String> allIsSessionIds = Maps.newTreeMap();
	static {
		allIsSessionIds.put(ISSESSIONID0, "否");
		allIsSessionIds.put(ISSESSIONID1, "是");
	}
	/** 否 */
	public static final Byte ISREQUESTTIMES0 = 0;
	/** 是 */
	public static final Byte ISREQUESTTIMES1 = 1;
		
	/** 是否控制接口请求次数 */
	public static final Map<Byte, String> allIsRequestTimess = Maps.newTreeMap();
	static {
		allIsRequestTimess.put(ISREQUESTTIMES0, "否");
		allIsRequestTimess.put(ISREQUESTTIMES1, "是");
	}
	/** 正常 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "正常");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** id */
	private Long id;
	/** 客户端ID，表app_info的主键 */
	private Long appId;
	/** 前端资源值ID，表permission的主键 */
	private Long configPermissionId;
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
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Long getConfigPermissionId(){
		return this.configPermissionId;
	}
	
	public void setConfigPermissionId(Long configPermissionId){
		this.configPermissionId = configPermissionId;
	}
	public Byte getIsLogin(){
		return this.isLogin;
	}
	
	public void setIsLogin(Byte isLogin){
		this.isLogin = isLogin;
	}
	public Byte getIsSign(){
		return this.isSign;
	}
	
	public void setIsSign(Byte isSign){
		this.isSign = isSign;
	}
	public Byte getIsSessionId(){
		return this.isSessionId;
	}
	
	public void setIsSessionId(Byte isSessionId){
		this.isSessionId = isSessionId;
	}
	public Byte getIsRequestTimes(){
		return this.isRequestTimes;
	}
	
	public void setIsRequestTimes(Byte isRequestTimes){
		this.isRequestTimes = isRequestTimes;
	}
	public Integer getRequestTimesInterval(){
		return this.requestTimesInterval;
	}
	
	public void setRequestTimesInterval(Integer requestTimesInterval){
		this.requestTimesInterval = requestTimesInterval;
	}
	public Integer getRequestTimesLimit(){
		return this.requestTimesLimit;
	}
	
	public void setRequestTimesLimit(Integer requestTimesLimit){
		this.requestTimesLimit = requestTimesLimit;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Long getUpdatorId(){
		return this.updatorId;
	}
	
	public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	
	public Byte getIsPermission() {
		return isPermission;
	}

	public void setIsPermission(Byte isPermission) {
		this.isPermission = isPermission;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
