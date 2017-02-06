package com.cqliving.cloud.online.act.domain;


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
 * 活动答题表 Entity
 * Date: 2016-06-07 09:22:46
 * @author Code Generator
 */
@Entity
@Table(name = "act_test_collect")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActTestCollect extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 非必填 */
	public static final Byte ISREQUIRED0 = 0;
	/** 必填 */
	public static final Byte ISREQUIRED1 = 1;
		
	/** 是否必填 */
	public static final Map<Byte, String> allIsRequireds = Maps.newTreeMap();
	static {
		allIsRequireds.put(ISREQUIRED0, "非必填");
		allIsRequireds.put(ISREQUIRED1, "必填");
	}
	
	/** ID */
	private Long id;
	/** 活动答题表ID（act_test表主键） */
	private Long actTestId;
	/** 信息收集表ID（act_collect_info表主键） */
	private Long actCollectInfoId;
	/** 是否必填 */
	private Byte isRequired;
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
	public Long getActTestId(){
		return this.actTestId;
	}
	
	public void setActTestId(Long actTestId){
		this.actTestId = actTestId;
	}
	public Long getActCollectInfoId(){
		return this.actCollectInfoId;
	}
	
	public void setActCollectInfoId(Long actCollectInfoId){
		this.actCollectInfoId = actCollectInfoId;
	}
	public Byte getIsRequired(){
		return this.isRequired;
	}
	
	public void setIsRequired(Byte isRequired){
		this.isRequired = isRequired;
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
