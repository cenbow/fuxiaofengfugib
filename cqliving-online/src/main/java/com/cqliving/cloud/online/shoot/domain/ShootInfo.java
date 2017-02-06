package com.cqliving.cloud.online.shoot.domain;


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
 * 随手拍表 Entity
 * Date: 2016-06-07 16:45:09
 * @author Code Generator
 */
@Entity
@Table(name = "shoot_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShootInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 待审核 */
	public static final Byte STATUS2 = 2;
	/** 上线 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS2, "待审核");
		allStatuss.put(STATUS3, "上线");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	/** 无 */
	public static final Byte SHOOTTYPE0 = 0;
	/** 景色 */
	public static final Byte SHOOTTYPE1 = 1;
	/** 人物 */
	public static final Byte SHOOTTYPE2 = 2;
	/** 动态 */
	public static final Byte SHOOTTYPE3 = 3;
	/** 摄影 */
	public static final Byte SHOOTTYPE4 = 4;
		
	/** 摄影类型 */
	public static final Map<Byte, String> allShootTypes = Maps.newTreeMap();
	static {
		allShootTypes.put(SHOOTTYPE0, "无");
		allShootTypes.put(SHOOTTYPE1, "景色");
		allShootTypes.put(SHOOTTYPE2, "人物");
		allShootTypes.put(SHOOTTYPE3, "动态");
		allShootTypes.put(SHOOTTYPE4, "摄影");
	}
	/** 不允许 */
	public static final Byte ISCOMMENT0 = 0;
	/** 允许 */
	public static final Byte ISCOMMENT1 = 1;
		
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 随手拍内容 */
	private String content;
	/** 用户ID */
	private Long userId;
	/** 回复量 */
	private Integer replyCount;
	/** 点赞量 */
	private Integer priseCount;
	/** 状态 */
	private Byte status;
	/** 摄影类型 */
	private Byte shootType;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 所处经度 */
	private String lng;
	/** 所处纬度 */
	private String lat;
	/** 所处位置 */
	private String place;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public Integer getReplyCount(){
		return this.replyCount;
	}
	
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public Integer getPriseCount(){
		return this.priseCount;
	}
	
	public void setPriseCount(Integer priseCount){
		this.priseCount = priseCount;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Byte getShootType(){
		return this.shootType;
	}
	
	public void setShootType(Byte shootType){
		this.shootType = shootType;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Long getUpdatorId(){
		return this.updatorId;
	}
	
	public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	public String getLng(){
		return this.lng;
	}
	
	public void setLng(String lng){
		this.lng = lng;
	}
	public String getLat(){
		return this.lat;
	}
	
	public void setLat(String lat){
		this.lat = lat;
	}
	public String getPlace(){
		return this.place;
	}
	
	public void setPlace(String place){
		this.place = place;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
