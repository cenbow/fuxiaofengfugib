package com.cqliving.cloud.online.account.domain;


import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 用户行政审批认证表 Entity
 * Date: 2016-12-29 09:17:02
 * @author Code Generator
 */
@Entity
@Table(name = "user_gov_authentication")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGovAuthentication extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 男 */
	public static final Byte SEX1 = 1;
	/** 女 */
	public static final Byte SEX2 = 2;
		
	/** 性别 */
	public static final Map<Byte, String> allSexs = Maps.newTreeMap();
	static {
		allSexs.put(SEX1, "男");
		allSexs.put(SEX2, "女");
	}
	/** 未认证 */
	public static final Byte ISAUTHENTICATION0 = 0;
	/** 已认证 */
	public static final Byte ISAUTHENTICATION1 = 1;
		
	/** 是否认证 */
	public static final Map<Byte, String> allIsAuthentications = Maps.newTreeMap();
	static {
		allIsAuthentications.put(ISAUTHENTICATION0, "未认证");
		allIsAuthentications.put(ISAUTHENTICATION1, "已认证");
	}
	
	/** id */
	private Long id;
	/** 真实姓名 */
	private String name;
	/** 身份证号码 */
	private String idCard;
	/** 性别 */
	private Byte sex;
	/** 民族code */
	private String nation;
	/** 手机号码 */
	private String phone;
	/** 行政审批平台返回的id */
	private String thirdOpenId;
	/** 是否认证 */
	private Byte isAuthentication;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	
	@Id
	
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
	public String getIdCard(){
		return this.idCard;
	}
	
	public void setIdCard(String idCard){
		this.idCard = idCard;
	}
	public Byte getSex(){
		return this.sex;
	}
	
	public void setSex(Byte sex){
		this.sex = sex;
	}
	public String getNation(){
		return this.nation;
	}
	
	public void setNation(String nation){
		this.nation = nation;
	}
	public String getPhone(){
		return this.phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getThirdOpenId(){
		return this.thirdOpenId;
	}
	
	public void setThirdOpenId(String thirdOpenId){
		this.thirdOpenId = thirdOpenId;
	}
	public Byte getIsAuthentication(){
		return this.isAuthentication;
	}
	
	public void setIsAuthentication(Byte isAuthentication){
		this.isAuthentication = isAuthentication;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
