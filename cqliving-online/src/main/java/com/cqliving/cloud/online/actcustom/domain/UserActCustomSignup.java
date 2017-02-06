package com.cqliving.cloud.online.actcustom.domain;


import java.util.Date;
import java.util.Map;

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
 * 用户参与报名表 Entity
 * Date: 2016-12-21 09:31:29
 * @author Code Generator
 */
@Entity
@Table(name = "user_act_custom_signup")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActCustomSignup extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 已报名 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "已报名");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 用户二维码扫描活动表，表act_qrcode的code字估 */
	private String actQrcodeCode;
	/** 用户ID，usesr_account表主键 */
	private Long userId;
	/** 用户当前使用token */
	private String token;
	/** 用户姓名 */
	private String userName;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getActQrcodeCode(){
		return this.actQrcodeCode;
	}
	
	public void setActQrcodeCode(String actQrcodeCode){
		this.actQrcodeCode = actQrcodeCode;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getToken(){
		return this.token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
