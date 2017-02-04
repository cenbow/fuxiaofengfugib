package com.org.weixin.module.zjchj.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ZjchjAwardDto {

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
	
	//冗余字段
	/** 已抽奖数量 */
	private Integer drawNum;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getActualNum() {
		return actualNum;
	}

	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}

	public Integer getVirtualNum() {
		return virtualNum;
	}

	public void setVirtualNum(Integer virtualNum) {
		this.virtualNum = virtualNum;
	}

	public Integer getAwardNum() {
		return awardNum;
	}

	public void setAwardNum(Integer awardNum) {
		this.awardNum = awardNum;
	}

	public Integer getRewardNum() {
		return rewardNum;
	}

	public void setRewardNum(Integer rewardNum) {
		this.rewardNum = rewardNum;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getIsTrueAward() {
		return isTrueAward;
	}

	public void setIsTrueAward(Byte isTrueAward) {
		this.isTrueAward = isTrueAward;
	}

	public Integer getDrawNum() {
		return drawNum;
	}

	public void setDrawNum(Integer drawNum) {
		this.drawNum = drawNum;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
