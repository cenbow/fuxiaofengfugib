package com.cqliving.cloud.online.account.domain;


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
 * 分享历史纪录表 Entity
 * Date: 2016-06-08 09:35:21
 * @author Code Generator
 */
@Entity
@Table(name = "user_share_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserShareHistory extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 微信朋友圈 */
	public static final Byte SHAREPLATEFORM0 = 0;
	/** 微信好友 */
	public static final Byte SHAREPLATEFORM1 = 1;
	/** QQ空间 */
	public static final Byte SHAREPLATEFORM2 = 2;
	/** QQ */
	public static final Byte SHAREPLATEFORM3 = 3;
	/** 微博 */
	public static final Byte SHAREPLATEFORM4 = 4;
		
	/** 分享平台 */
	public static final Map<Byte, String> allSharePlateforms = Maps.newTreeMap();
	static {
		allSharePlateforms.put(SHAREPLATEFORM0, "微信朋友圈");
		allSharePlateforms.put(SHAREPLATEFORM1, "微信好友");
		allSharePlateforms.put(SHAREPLATEFORM2, "QQ空间");
		allSharePlateforms.put(SHAREPLATEFORM3, "QQ");
		allSharePlateforms.put(SHAREPLATEFORM4, "微博");
	}
	/** 新闻 */
	public static final Byte SOURCETYPE1 = 1;
	/** 问政 */
	public static final Byte SOURCETYPE2 = 2;
	/** 随手拍 */
	public static final Byte SOURCETYPE3 = 3;
	/** 活动 */
	public static final Byte SOURCETYPE4 = 4;
	/** 商情 */
	public static final Byte SOURCETYPE5 = 5;
		
	/** 分享类型 */
	public static final Map<Byte, String> allSourceTypes = Maps.newTreeMap();
	static {
		allSourceTypes.put(SOURCETYPE1, "新闻");
		allSourceTypes.put(SOURCETYPE2, "问政");
		allSourceTypes.put(SOURCETYPE3, "随手拍");
		allSourceTypes.put(SOURCETYPE4, "活动");
		allSourceTypes.put(SOURCETYPE5, "商情");
	}
	
	/** ID */
	private Long id;
	/** 来源APP */
	private Long appId;
	/** 用户ID */
	private Long userId;
	/** 会话code */
	private String sessionCode;
	/** 创建时间 */
	private Date createTime;
	/** 分享平台 */
	private Byte sharePlateform;
	/** 分享类型 */
	private Byte sourceType;
	/** 分享来源ID */
	private Long sourceId;
	/** 所处位置 */
	private String place;
	/** 所处经度 */
	private String lng;
	/** 所处纬度 */
	private String lat;
	
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
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getSessionCode(){
		return this.sessionCode;
	}
	
	public void setSessionCode(String sessionCode){
		this.sessionCode = sessionCode;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Byte getSharePlateform(){
		return this.sharePlateform;
	}
	
	public void setSharePlateform(Byte sharePlateform){
		this.sharePlateform = sharePlateform;
	}
	public Byte getSourceType(){
		return this.sourceType;
	}
	
	public void setSourceType(Byte sourceType){
		this.sourceType = sourceType;
	}
	public Long getSourceId(){
		return this.sourceId;
	}
	
	public void setSourceId(Long sourceId){
		this.sourceId = sourceId;
	}
	public String getPlace(){
		return this.place;
	}
	
	public void setPlace(String place){
		this.place = place;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
