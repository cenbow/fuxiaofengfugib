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
 * 用户信息表 Entity
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
@Entity
@Table(name = "USER_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 未知 */
	public static final Byte SEX0 = 0;
	/** 男 */
	public static final Byte SEX1 = 1;
	/** 女 */
	public static final Byte SEX2 = 2;
		
	/** 性别 */
	public static final Map<Byte, String> allSexs = Maps.newTreeMap();
	static {
		allSexs.put(SEX0, "未知");
		allSexs.put(SEX1, "男");
		allSexs.put(SEX2, "女");
	}
	
	/** ID,同URSERID */
	private Long id;
	/** 姓名 */
	private String name;
	/** 头像 */
	private String imgUrl;
	/** 性别 */
	private Byte sex;
	/** 个性签名 */
	private String speciality;
	/** 最后修改时间 */
	private Date updateTime;
	/** 匿名昵称 */
	private String anonymousName;
	/** 活动用户来源 */
	private String actSource;
	/** 交通信息卡卡号 */
	private String trafficCard;
	/** 身份证号码 */
	private String idCard;
	
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
	public String getImgUrl(){
		return this.imgUrl;
	}
	
	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}
	public Byte getSex(){
		return this.sex;
	}
	
	public void setSex(Byte sex){
		this.sex = sex;
	}
	public String getSpeciality(){
		return this.speciality;
	}
	
	public void setSpeciality(String speciality){
		this.speciality = speciality;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public String getAnonymousName() {
		return anonymousName;
	}

	public void setAnonymousName(String anonymousName) {
		this.anonymousName = anonymousName;
	}

	public String getActSource() {
		return actSource;
	}

	public void setActSource(String actSource) {
		this.actSource = actSource;
	}

	public String getTrafficCard() {
		return trafficCard;
	}

	public void setTrafficCard(String trafficCard) {
		this.trafficCard = trafficCard;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
