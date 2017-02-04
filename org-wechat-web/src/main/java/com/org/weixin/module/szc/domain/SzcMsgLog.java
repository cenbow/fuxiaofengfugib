package com.org.weixin.module.szc.domain;


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
 * Date: 2016-09-02 11:24:19
 * @author Code Generator
 */
@Entity
@Table(name = "szc_msg_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SzcMsgLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 发送成功 */
	public static final Byte ERRNO0 = 0;
	/** 发送失败 */
	public static final Byte ERRNO_1 = -1;
		
	/** errno */
	public static final Map<Byte, String> allErrnos = Maps.newTreeMap();
	static {
		allErrnos.put(ERRNO0, "发送成功");
		allErrnos.put(ERRNO_1, "发送失败");
	}
	
	/** ID */
	private Long id;
	/** 获取手机号 */
	private String phone;
	/** 兑奖码 */
	private String msgCode;
	/** 远程调用参数 */
	private String remoteParams;
	/** 远程回调结果 */
	private String remoteResp;
	/** 创建时间 */
	private Date createTime;
	/** errno */
	private Integer errno;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getPhone(){
		return this.phone;
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
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Integer getErrno(){
		return this.errno;
	}
	
	public void setErrno(Integer errno){
		this.errno = errno;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
