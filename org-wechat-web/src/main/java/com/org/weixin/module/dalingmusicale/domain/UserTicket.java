package com.org.weixin.module.dalingmusicale.domain;


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

import org.wechat.framework.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 用户抢票表 Entity
 * Date: 2016-09-16 09:10:01
 * @author Code Generator
 */
@Entity
@Table(name = "user_ticket")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserTicket extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 未中奖 */
	public static final Byte TAKESTATUS0 = 0;
	/** 已中奖未核销 */
	public static final Byte TAKESTATUS1 = 1;
	/** 已核销 */
	public static final Byte TAKESTATUS2 = 2;
		
	/** 状态 */
	public static final Map<Byte, String> allTakeStatuss = Maps.newTreeMap();
	static {
		allTakeStatuss.put(TAKESTATUS0, "未中奖");
		allTakeStatuss.put(TAKESTATUS1, "已中奖未核销");
		allTakeStatuss.put(TAKESTATUS2, "已核销");
	}
	
	/** 主键 */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 用户手机号 */
	private String phone;
	/** 门票ID */
	private Long musicaleTicketId;
	/** 抢票时间 */
	private Date grabTime;
	/** 核销码 */
	private String verifyCode;
	/** 核销时间 */
	private Date verifyTime;
	/** 状态 */
	private Byte takeStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getPhone(){
		return this.phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	public Long getMusicaleTicketId(){
		return this.musicaleTicketId;
	}
	
	public void setMusicaleTicketId(Long musicaleTicketId){
		this.musicaleTicketId = musicaleTicketId;
	}
	public Date getGrabTime(){
		return this.grabTime;
	}
	
	public void setGrabTime(Date grabTime){
		this.grabTime = grabTime;
	}
	public String getVerifyCode(){
		return this.verifyCode;
	}
	
	public void setVerifyCode(String verifyCode){
		this.verifyCode = verifyCode;
	}
	public Date getVerifyTime(){
		return this.verifyTime;
	}
	
	public void setVerifyTime(Date verifyTime){
		this.verifyTime = verifyTime;
	}
	public Byte getTakeStatus(){
		return this.takeStatus;
	}
	
	public void setTakeStatus(Byte takeStatus){
		this.takeStatus = takeStatus;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
