package com.org.weixin.module.zjchj.domain;


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
 * 奖品表 Entity
 * Date: 2016-09-26 15:17:36
 * @author Code Generator
 */
@Entity
@Table(name = "zjchj_award")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZjchjAward extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 初级吃货 */
	public static final Byte LEVEL1 = 1;
	/** 中级吃货 */
	public static final Byte LEVEL2 = 2;
	/** 高级吃货 */
	public static final Byte LEVEL3 = 3;
	/** 终极吃货 */
	public static final Byte LEVEL4 = 4;
		
	/** 奖品等级 */
	public static final Map<Byte, String> allLevels = Maps.newTreeMap();
	static {
		allLevels.put(LEVEL1, "初级");
		allLevels.put(LEVEL2, "中级");
		allLevels.put(LEVEL3, "高级");
		allLevels.put(LEVEL4, "终极");
	}
	/** 发布 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "发布");
		allStatuss.put(STATUS88, "下线");
	}
	/** 否 */
	public static final Byte ISTRUEAWARD0 = 0;
	/** 是 */
	public static final Byte ISTRUEAWARD1 = 1;
		
	/** 是否真实奖品 */
	public static final Map<Byte, String> allIsTrueAwards = Maps.newTreeMap();
	static {
		allIsTrueAwards.put(ISTRUEAWARD0, "否");
		allIsTrueAwards.put(ISTRUEAWARD1, "是");
	}
	
	/** ID */
	private Long id;
	/** 奖品名称 */
	private String name;
	/** 奖品实际数量 */
	private Integer actualNum;
	/** 奖品虚拟数量 */
	private Integer virtualNum;
	/** 已抽中奖品数量 */
	private Integer awardNum;
	/** 已核销数量 */
	private Integer rewardNum;
	/** 奖品等级 */
	private Byte level;
	/** 创建时间 */
	private Date createTime;
	/** 状态 */
	private Byte status;
	/** 是否真实奖品 */
	private Byte isTrueAward;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Integer getActualNum(){
		return this.actualNum;
	}
	
	public void setActualNum(Integer actualNum){
		this.actualNum = actualNum;
	}
	public Integer getVirtualNum(){
		return this.virtualNum;
	}
	
	public void setVirtualNum(Integer virtualNum){
		this.virtualNum = virtualNum;
	}
	public Integer getAwardNum(){
		return this.awardNum;
	}
	
	public void setAwardNum(Integer awardNum){
		this.awardNum = awardNum;
	}
	public Integer getRewardNum(){
		return this.rewardNum;
	}
	
	public void setRewardNum(Integer rewardNum){
		this.rewardNum = rewardNum;
	}
	public Byte getLevel(){
		return this.level;
	}
	
	public void setLevel(Byte level){
		this.level = level;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Byte getIsTrueAward(){
		return this.isTrueAward;
	}
	
	public void setIsTrueAward(Byte isTrueAward){
		this.isTrueAward = isTrueAward;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
