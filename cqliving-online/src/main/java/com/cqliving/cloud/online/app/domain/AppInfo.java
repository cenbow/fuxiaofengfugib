package com.cqliving.cloud.online.app.domain;


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

/**
 * 客户端 Entity
 * Date: 2016-04-29 16:18:30
 * @author Code Generator
 */
@Entity
@Table(name = "APP_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 无需签名验证 */
	public static final Byte IS_REQUEST_SIGN_0 = 0;
	/** 需要签名验证 */
	public static final Byte IS_REQUEST_SIGN_1 = 1;
	
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
	/** 短信签名 */
	private String smsSignature;
	/** 极光推送 AppKey */
	private String jpushAppKey;
	/** 极光推送 AppSecret */
	private String jpushAppSecret;
	/** 百度地图Key */
	private String baiduLbsKey;
	/** 请求签名字符串，每个APP都不同，目前只对发送验证码、登陆和生成TOKEN接口签名 */
	private String requestSignKey;
	/** 是否签名，目前只对登陆和生成TOKEN接口签名做判断，不对发送验证码做判断，{1:是,0:否} */
	private Byte isRequestSign;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getCmsName(){
		return this.cmsName;
	}
	
	public void setCmsName(String cmsName){
		this.cmsName = cmsName;
	}
	public String getLogo(){
		return this.logo;
	}
	
	public void setLogo(String logo){
		this.logo = logo;
	}
	public String getLocation(){
		return this.location;
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	public String getAppDomain(){
		return this.appDomain;
	}
	
	public void setAppDomain(String appDomain){
		this.appDomain = appDomain;
	}
	public String getCmsDomain(){
		return this.cmsDomain;
	}
	
	public void setCmsDomain(String cmsDomain){
		this.cmsDomain = cmsDomain;
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
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public String getContactPhone(){
		return this.contactPhone;
	}
	
	public void setContactPhone(String contactPhone){
		this.contactPhone = contactPhone;
	}
	public String getFax(){
		return this.fax;
	}
	
	public void setFax(String fax){
		this.fax = fax;
	}
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public String getPosition(){
		return this.position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	public String getSmsCode(){
		return this.smsCode;
	}
	
	public void setSmsCode(String smsCode){
		this.smsCode = smsCode;
	}
	public String getNamePrefix(){
		return this.namePrefix;
	}
	
	public void setNamePrefix(String namePrefix){
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

	public String getSmsSignature() {
		return smsSignature;
	}

	public void setSmsSignature(String smsSignature) {
		this.smsSignature = smsSignature;
	}

	public String getBaiduLbsKey() {
		return baiduLbsKey;
	}

	public void setBaiduLbsKey(String baiduLbsKey) {
		this.baiduLbsKey = baiduLbsKey;
	}

	public String getRequestSignKey() {
		return requestSignKey;
	}

	public void setRequestSignKey(String requestSignKey) {
		this.requestSignKey = requestSignKey;
	}

	public Byte getIsRequestSign() {
		return isRequestSign;
	}

	public void setIsRequestSign(Byte isRequestSign) {
		this.isRequestSign = isRequestSign;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
