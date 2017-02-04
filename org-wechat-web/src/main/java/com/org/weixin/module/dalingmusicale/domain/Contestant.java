package com.org.weixin.module.dalingmusicale.domain;


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
 * 选手 Entity
 * Date: 2016-09-16 09:09:34
 * @author Code Generator
 */
@Entity
@Table(name = "contestant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contestant extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 人气王 */
	public static final Byte VOTESTEP1 = 1;
	/** 现场决赛 */
	public static final Byte VOTESTEP2 = 2;
		
	/** 投票阶段 */
	public static final Map<Byte, String> allVoteSteps = Maps.newTreeMap();
	static {
		allVoteSteps.put(VOTESTEP1, "人气王");
		allVoteSteps.put(VOTESTEP2, "现场决赛");
	}
	
	/** 主键 */
	private Long id;
	/** 选手姓名 */
	private String name;
	/** 选手编号 */
	private String code;
	/** 选手照片地址 */
	private String imageUrl;
	/** 基本信息 */
	private String baseInfo;
	/** 视频地址 */
	private String videoUrl;
	/** 获得票数 */
	private Integer voteNum;
	/** 投票阶段 */
	private Byte voteStep;
	/** 投票开始时间 */
	private Date beginTime;
	/** 投票结束时间 */
	private Date endTime;
	//名字拼音
	private String nameSpell;
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
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public String getBaseInfo(){
		return this.baseInfo;
	}
	
	public void setBaseInfo(String baseInfo){
		this.baseInfo = baseInfo;
	}
	public String getVideoUrl(){
		return this.videoUrl;
	}
	
	public void setVideoUrl(String videoUrl){
		this.videoUrl = videoUrl;
	}
	public Integer getVoteNum(){
		return this.voteNum;
	}
	
	public void setVoteNum(Integer voteNum){
		this.voteNum = voteNum;
	}
	public Byte getVoteStep(){
		return this.voteStep;
	}
	
	public void setVoteStep(Byte voteStep){
		this.voteStep = voteStep;
	}
	public Date getBeginTime(){
		return this.beginTime;
	}
	
	public void setBeginTime(Date beginTime){
		this.beginTime = beginTime;
	}
	public Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public String getNameSpell() {
		return nameSpell;
	}

	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
