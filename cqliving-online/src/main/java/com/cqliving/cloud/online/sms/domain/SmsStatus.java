package com.cqliving.cloud.online.sms.domain;


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
 * 状态报告表 Entity
 * Date: 2016-04-29 16:40:01
 * @author Code Generator
 */
@Entity
@Table(name = "SMS_STATUS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsStatus extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 任务ID */
	private String taskid;
	/** 手机号 */
	private String telephone;
	/** 状态报告----10：发送成功，20：发送失败             */
	private String status;
	/** 接收时间 */
	private Date receivetime;
	/** 子号，即自定义扩展号 */
	private String extno;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getTaskid(){
		return this.taskid;
	}
	
	public void setTaskid(String taskid){
		this.taskid = taskid;
	}
	public String getTelephone(){
		return this.telephone;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public String getStatus(){
		return this.status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	public Date getReceivetime(){
		return this.receivetime;
	}
	
	public void setReceivetime(Date receivetime){
		this.receivetime = receivetime;
	}
	public String getExtno(){
		return this.extno;
	}
	
	public void setExtno(String extno){
		this.extno = extno;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
