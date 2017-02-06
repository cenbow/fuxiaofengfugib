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
 * 用户验证码表 Entity
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
@Entity
@Table(name = "USER_SMS_LOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserSmsLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 未使用 */
	public static final Byte STATUS0 = 0;
	/** 已使用 */
	public static final Byte STATUS1 = 1;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS0, "未使用");
		allStatuss.put(STATUS1, "已使用");
	}
	/** 注册 */
	public static final Byte TYPE0 = 0;
	/** 登录 */
	public static final Byte TYPE1 = 1;
	/** 找回密码 */
	public static final Byte TYPE2 = 2;
	/** 修改密码 */
	public static final Byte TYPE3 = 3;
	/** 更换手机 */
	public static final Byte TYPE4 = 4;
	/** 行政审批-实名认证By huxiaoping 2016-12-29 */
	public static final Byte TYPE5 = 5;
		
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "注册");
		allTypes.put(TYPE1, "登录");
		allTypes.put(TYPE2, "找回密码");
		allTypes.put(TYPE3, "修改密码");
		allTypes.put(TYPE4, "修改手机号");
		allTypes.put(TYPE5, "行政审批-实名认证");
	}
	
	/** ID */
	private Long id;
	/** 验证码 */
	private String captcha;
	/** 手机号码 */
	private String telephone;
	/** 状态 */
	private Byte status;
	/** 类型 */
	private Byte type;
	/** 设备码 */
	private String phoneCode;
	/** 创建时间 */
	private Date createTime;
	/** 来源APP */
	private Long appId;
	/** 使用时间 */
	private Date useTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getCaptcha(){
		return this.captcha;
	}
	
	public void setCaptcha(String captcha){
		this.captcha = captcha;
	}
	public String getTelephone(){
		return this.telephone;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public String getPhoneCode(){
		return this.phoneCode;
	}
	
	public void setPhoneCode(String phoneCode){
		this.phoneCode = phoneCode;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	
	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
