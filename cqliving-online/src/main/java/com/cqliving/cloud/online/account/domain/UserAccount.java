package com.cqliving.cloud.online.account.domain;


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
 * 用户账号表 Entity
 * Date: 2016-04-29 16:28:55
 * @author Code Generator
 */
@Entity
@Table(name = "USER_ACCOUNT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAccount extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 注册用户 */
	public static final Byte TYPE0 = 0;
	/** 马甲 */
	public static final Byte TYPE1 = 1;
	/** 游客 */
	public static final Byte TYPE2 = 2;
		
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "注册用户");
		allTypes.put(TYPE1, "马甲");
		allTypes.put(TYPE2, "游客");
	}
	/** 正常 */
	public static final Byte STATUS0 = 0;
	/** 禁用 */
	public static final Byte STATUS1 = 1;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS0, "正常");
		allStatuss.put(STATUS1, "禁用");
	}
	
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
	/** 所处位置 */
	private String place;
	/** 所处位置经度 */
	private String lng;
	/** 所处位置纬度 */
	private String lat;
	/** 最后登录时间 */
	private Date lastLoginTime;
	/** 最后登录IP */
	private String loginIp;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getTelephone(){
		return this.telephone;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getSource(){
		return this.source;
	}
	
	public void setSource(String source){
		this.source = source;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Date getRegistTime(){
		return this.registTime;
	}
	
	public void setRegistTime(Date registTime){
		this.registTime = registTime;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
