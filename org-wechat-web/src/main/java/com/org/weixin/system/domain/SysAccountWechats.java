package com.org.weixin.system.domain;


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
import org.wechat.framework.domain.AbstractEntity;

/**
 * sys_account_wechats Entity
 *
 * Date: 2015-07-23 20:46:52
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_account_wechats")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysAccountWechats extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键 */
	private Long id;
	/** 预留字段 */
	private Long uniacid;
	/** token */
	private String token;
	/** 加密字符串 */
	private String encodingAesKey;
	/** 用户级别 */
	private byte level;
	/** 姓名 */
	private String name;
	/** 银行账户 */
	private String account;
	/** 证件原件 */
	private String original;
	/** 国家 */
	private String country;
	/** 省份 */
	private String province;
	/**  城市 */
	private String city;
	/** 用户名 */
	private String username;
	/** 密码 */
	private String password;
	/** appid */
	private String appid;
	/** secret */
	private String secret;
	/** 风格ID */
	private Integer styleid;
	/** 关注链接 */
	private String subscribeUrl;
	/** 状态 */
	private int status;
	/** 最后更新时间 */
	private Date createTime;
	/** 最后更新时间 */
	private Date updateTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getUniacid(){
		return this.uniacid;
	}
	
	public void setUniacid(Long uniacid){
		this.uniacid = uniacid;
	}
	public String getToken(){
		return this.token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	public String getEncodingAesKey(){
		return this.encodingAesKey;
	}
	
	public void setEncodingAesKey(String encodingAesKey){
		this.encodingAesKey = encodingAesKey;
	}
	public byte getLevel(){
		return this.level;
	}
	
	public void setLevel(byte level){
		this.level = level;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getAccount(){
		return this.account;
	}
	
	public void setAccount(String account){
		this.account = account;
	}
	public String getOriginal(){
		return this.original;
	}
	
	public void setOriginal(String original){
		this.original = original;
	}
	public String getCountry(){
		return this.country;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	public String getProvince(){
		return this.province;
	}
	
	public void setProvince(String province){
		this.province = province;
	}
	public String getCity(){
		return this.city;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getAppid(){
		return this.appid;
	}
	
	public void setAppid(String appid){
		this.appid = appid;
	}
	public String getSecret(){
		return this.secret;
	}
	
	public void setSecret(String secret){
		this.secret = secret;
	}
	public Integer getStyleid(){
		return this.styleid;
	}
	
	public void setStyleid(Integer styleid){
		this.styleid = styleid;
	}
	public String getSubscribeUrl(){
		return this.subscribeUrl;
	}
	
	public void setSubscribeUrl(String subscribeUrl){
		this.subscribeUrl = subscribeUrl;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
