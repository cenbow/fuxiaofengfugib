package com.org.weixin.module.jywth.domain;


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
import org.wechat.framework.domain.AbstractEntity;

import com.google.common.collect.Maps;

/**
 * 短信日志表 Entity
 *
 * Date: 2016-04-02 13:11:48
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "jywth_msg_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JywthMsgLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 获取验证码 */
	public static final byte MSGTYPE0 = 0;
	/** 验证验证码 */
	public static final byte MSGTYPE1 = 1;
	/** 兑奖码 */
	public static final byte MSGTYPE2 = 2;
	//发送成功
	public static final byte ISSUCCESS0=0;
	//发送失败
	public static final byte ISSUCCESS1=-1;
	
	/** 短信类型{0:验证码,1:兑奖码} */
	public static final Map<Byte, String> allMsgTypes = Maps.newTreeMap();
	public static final Map<Byte, String> allIsSuccess = Maps.newTreeMap();
		
	static {
		allMsgTypes.put(MSGTYPE0, "获取验证码");
		allMsgTypes.put(MSGTYPE1, "验证验证码");
		allMsgTypes.put(MSGTYPE2, "兑奖码");
		allIsSuccess.put(ISSUCCESS0, "发送成功");
		allIsSuccess.put(ISSUCCESS1, "发送失败");
	}
	
	/** ID */
	private Long id;
	/** 获取手机号 */
	private String phone;
	/** 验证码(8位随机数字) */
	private String msgCode;
	/** 远程调用参数 */
	private String remoteParams;
	/** 远程回调结果 */
	private String remoteResp;
	/** 创建时间 */
	private Date createTime;
	/** 失效时间 */
	private Date overdueTime;
	//短信失效时间
	private Integer expireTime;
	//0:发送成功,-1:失败
	private byte isSuccess;
	/** 短信类型{0:获取验证码,1:验证验证码,2:兑奖码} */
	private byte msgType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public Integer getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Integer expireTime) {
		this.expireTime = expireTime;
	}

	public void setId(Long id){
		this.id = id;
	}
	public String getPhone(){
		return this.phone;
	}
	
	public byte getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(byte isSuccess) {
		this.isSuccess = isSuccess;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getMsgCode(){
		return this.msgCode;
	}
	
	public void setMsgCode(String msgCode){
		this.msgCode = msgCode;
	}
	public String getRemoteParams(){
		return this.remoteParams;
	}
	
	public void setRemoteParams(String remoteParams){
		this.remoteParams = remoteParams;
	}
	public String getRemoteResp(){
		return this.remoteResp;
	}
	
	public void setRemoteResp(String remoteResp){
		this.remoteResp = remoteResp;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOverdueTime() {
		return overdueTime;
	}

	public void setOverdueTime(Date overdueTime) {
		this.overdueTime = overdueTime;
	}

	public byte getMsgType(){
		return this.msgType;
	}
	
	public void setMsgType(byte msgType){
		this.msgType = msgType;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
