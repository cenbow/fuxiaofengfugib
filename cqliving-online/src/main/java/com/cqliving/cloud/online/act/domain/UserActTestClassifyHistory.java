package com.cqliving.cloud.online.act.domain;


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
 * 用户答题分类历史表。一个用户对应一个分类测试题可以有多条记录。 Entity
 * Date: 2016-06-22 18:02:08
 * @author Code Generator
 */
@Entity
@Table(name = "user_act_test_classify_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActTestClassifyHistory extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 已完成 */
	public static final Byte ISFINISH1 = 1;
	/** 未完成 */
	public static final Byte ISFINISH0 = 0;
		
	/** 是否已完成 */
	public static final Map<Byte, String> allIsFinishs = Maps.newTreeMap();
	static {
		allIsFinishs.put(ISFINISH1, "已完成");
		allIsFinishs.put(ISFINISH0, "未完成");
	}
	
	/** ID */
	private Long id;
	/** 答题表ID（act_test_id表主键） */
	private Long actTestId;
	/** 答题分类表ID（act_test_classify表主键） */
	private Long actTestClassifyId;
	/** 用户ID */
	private Long userId;
	/** 用户答题获得的分数 */
	private Integer score;
	/** 答题开始时间 */
	private Date startTime;
	/** 参与时间 */
	private Date createTime;
	/** 是否已完成 */
	private Byte isFinish;
	
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
	public Long getActTestClassifyId(){
		return this.actTestClassifyId;
	}
	
	public void setActTestClassifyId(Long actTestClassifyId){
		this.actTestClassifyId = actTestClassifyId;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Byte getIsFinish(){
		return this.isFinish;
	}
	
	public void setIsFinish(Byte isFinish){
		this.isFinish = isFinish;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
