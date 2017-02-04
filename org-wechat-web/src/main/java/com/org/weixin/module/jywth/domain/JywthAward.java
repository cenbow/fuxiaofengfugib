package com.org.weixin.module.jywth.domain;


import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.wechat.framework.domain.AbstractEntity;

import com.google.common.collect.Maps;

/**
 * jywth_揭阳万泰汇奖品表 Entity
 *
 * Date: 2016-04-02 13:11:44
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "jywth_award")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JywthAward extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final Map<String,Integer>  codeNumMap_1462 = Maps.newHashMap();
	public static final Map<String,Integer>  codeNumMap_4462 = Maps.newHashMap();
	public static final Map<String,Integer>  codeNumMap_2462 = Maps.newHashMap();
	
	static{
		codeNumMap_1462.put("414005",1000);
		codeNumMap_1462.put("414008",300);
		codeNumMap_1462.put("414012",100);
		codeNumMap_1462.put("414018",50);
		codeNumMap_1462.put("414030",10);
		codeNumMap_1462.put("414188",2);
		codeNumMap_1462.put("4142222",0);
		codeNumMap_1462.put("4140000",0);
		
		codeNumMap_4462.put("414005",4000);
		codeNumMap_4462.put("414008",300);
		codeNumMap_4462.put("414012",100);
		codeNumMap_4462.put("414018",50);
		codeNumMap_4462.put("414030",10);
		codeNumMap_4462.put("414188",2);
		codeNumMap_4462.put("4142222",0);
		codeNumMap_4462.put("4140000",0);
		
		codeNumMap_2462.put("414005",2000);
		codeNumMap_2462.put("414008",300);
		codeNumMap_2462.put("414012",100);
		codeNumMap_2462.put("414018",50);
		codeNumMap_2462.put("414030",10);
		codeNumMap_2462.put("414188",2);
		codeNumMap_2462.put("4142222",0);
		codeNumMap_2462.put("4140000",0);
	}
	
	/** ID */
	private Long id;
	/** 红包CODE */
	private String code;
	/** 红包名称 */
	private String name;
	/** 红包数量 */
	private Integer num;
	/** 开始投放时间 */
	private Date sendBeginTime;
	/** 结束投放时间 */
	private Date sendEndTime;
	
	private Long awardHisId;
	
	@Transient
	public Long getAwardHisId() {
		return awardHisId;
	}

	public void setAwardHisId(Long awardHisId) {
		this.awardHisId = awardHisId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Integer getNum(){
		return this.num;
	}
	
	public void setNum(Integer num){
		this.num = num;
	}
	public Date getSendBeginTime(){
		return this.sendBeginTime;
	}
	
	public void setSendBeginTime(Date sendBeginTime){
		this.sendBeginTime = sendBeginTime;
	}
	public Date getSendEndTime(){
		return this.sendEndTime;
	}
	
	public void setSendEndTime(Date sendEndTime){
		this.sendEndTime = sendEndTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
