package com.cqliving.cloud.online.account.domain;


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
 * 用户参与调研历史表 Entity
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
@Entity
@Table(name = "USER_SURVEY_HISTORY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserSurveyHistory extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 会话code */
	private String sessionCode;
	/** 客户端_ID */
	private Long appId;
	/** 调研ID */
	private Long surveyId;
	/** 参与时间 */
	private Date createTime;
	/** 参与时间 */
	private Long userId;
	/** 联系方式 */
	private String phone;
	/** IP */
	private String ip;
	/** 来源 */
	private String source;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getSessionCode(){
		return this.sessionCode;
	}
	
	public void setSessionCode(String sessionCode){
		this.sessionCode = sessionCode;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Long getSurveyId(){
		return this.surveyId;
	}
	
	public void setSurveyId(Long surveyId){
		this.surveyId = surveyId;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
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
	public String getIp(){
		return this.ip;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
	public String getSource(){
		return this.source;
	}
	
	public void setSource(String source){
		this.source = source;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
