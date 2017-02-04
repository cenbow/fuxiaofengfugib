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
 * 音乐会门票 Entity
 * Date: 2016-09-16 09:09:40
 * @author Code Generator
 */
@Entity
@Table(name = "musicale_ticket")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MusicaleTicket extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 不是门票 */
	public static final Byte TICKETTYPE1 = 1;
	/** 是门票 */
	public static final Byte TICKETTYPE2 = 2;
		
	/** 门票类型 */
	public static final Map<Byte, String> allTicketTypes = Maps.newTreeMap();
	static {
		allTicketTypes.put(TICKETTYPE1, "不是门票");
		allTicketTypes.put(TICKETTYPE2, "是门票");
	}
	
	/** 主键 */
	private Long id;
	/** 音乐会名称 */
	private String name;
	/** 音乐会图片 */
	private String imageUrl;
	/** 演出时间段 */
	private String duration;
	/** 音乐会可售门票实际数量 */
	private Integer actualNum;
	/** 音乐会可售门票虚拟数量 */
	private Integer virtualNum;
	/** 可获得数量 */
	private Integer getNum;
	/** 门票类型 */
	private Byte ticketType;
	/** 抢票开始时间 */
	private Date beginTime;
	/** 抢票结束时间 */
	private Date endTime;
	
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
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public String getDuration(){
		return this.duration;
	}
	
	public void setDuration(String duration){
		this.duration = duration;
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
	public Integer getGetNum(){
		return this.getNum;
	}
	
	public void setGetNum(Integer getNum){
		this.getNum = getNum;
	}
	public Byte getTicketType(){
		return this.ticketType;
	}
	
	public void setTicketType(Byte ticketType){
		this.ticketType = ticketType;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
