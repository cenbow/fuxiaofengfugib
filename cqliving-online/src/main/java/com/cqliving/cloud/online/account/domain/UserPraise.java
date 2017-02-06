package com.cqliving.cloud.online.account.domain;


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

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * user_用户点赞表 Entity
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
@Entity
@Table(name = "USER_PRAISE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserPraise extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 新闻 */
	public static final Byte SOURCETYPE1 = 1;
	/** 问政 */
	public static final Byte SOURCETYPE2 = 2;
	/** 商情 */
	public static final Byte SOURCETYPE3 = 3;
	/** 随手拍 */
	public static final Byte SOURCETYPE4 = 4;
	/** 段子 */
	public static final Byte SOURCETYPE5 = 5;
	/** 活动 */
	public static final Byte SOURCETYPE6 = 6;
	/** 话题 */
	public static final Byte SOURCETYPE7 = 7;
	/** 旅游 */
	public static final Byte SOURCETYPE10 = 10;
	/** 手机置业 */
	public static final Byte SOURCETYPE11 = 11;
	
	
	/** 来源类型 */
	public static final Map<Byte, String> allSourceTypes = Maps.newTreeMap();
	static {
		allSourceTypes.put(SOURCETYPE1, "新闻");
		allSourceTypes.put(SOURCETYPE2, "问政");
		allSourceTypes.put(SOURCETYPE3, "商情");
		allSourceTypes.put(SOURCETYPE4, "随手拍");
		allSourceTypes.put(SOURCETYPE5, "段子");
		allSourceTypes.put(SOURCETYPE6, "活动");
		allSourceTypes.put(SOURCETYPE7, "话题");
		allSourceTypes.put(SOURCETYPE10, "旅游");
		allSourceTypes.put(SOURCETYPE11, "手机置业");
	}
	/** 点赞 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "正常");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** 点赞 */
	public static final Byte TYPE0 = 0;
	/** 点踩 */
	public static final Byte TYPE1 = 1;
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "点赞");
		allTypes.put(TYPE1, "点踩");
	}
	
	/** 业务类型 */
	public static final Byte OPERATETYPE0 = 0;
	/** 评论 */
	public static final Byte OPERATETYPE1 = 1;
		
	/** 类型 */
	public static final Map<Byte, String> allOperateTypes = Maps.newTreeMap();
	static {
		allOperateTypes.put(OPERATETYPE0, "业务类型");
		allOperateTypes.put(OPERATETYPE1, "评论");
	}
	
	/** ID */
	private Long id;
	/** 来源APP */
	private Long appId;
	/** 操作用户ID */
	private Long sourceUserId;
	/** 会话code */
	private String sessionCode;
	/** 操作用户头像 */
	private String sourceUserImage;
	/** 操作用户显示昵称 */
	private String nickName;
	/** 被点赞用户ID */
	private Long userId;
	/** 点赞来源名称 */
	private String sourceName;
	/** 根据不同的来源类型到不同的表，评论表 */
	private Long sourceId;
	/** 来源类型 */
	private Byte sourceType;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 图片 */
	private String imageUrl;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 点赞类型 */
	private Byte type;
	/** 类型 */
    private Byte operateType;
	
	/** 创建时间 */
	private String token;
	
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
	public Long getSourceUserId(){
		return this.sourceUserId;
	}
	
	public void setSourceUserId(Long sourceUserId){
		this.sourceUserId = sourceUserId;
	}
	public String getSessionCode(){
		return this.sessionCode;
	}
	
	public void setSessionCode(String sessionCode){
		this.sessionCode = sessionCode;
	}
	public String getSourceUserImage(){
		return this.sourceUserImage;
	}
	
	public void setSourceUserImage(String sourceUserImage){
		this.sourceUserImage = sourceUserImage;
	}
	public String getNickName(){
		return this.nickName;
	}
	
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getSourceName(){
		return this.sourceName;
	}
	
	public void setSourceName(String sourceName){
		this.sourceName = sourceName;
	}
	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Byte getSourceType(){
		return this.sourceType;
	}
	
	public void setSourceType(Byte sourceType){
		this.sourceType = sourceType;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getOperateType() {
		return operateType;
	}

	public void setOperateType(Byte operateType) {
		this.operateType = operateType;
	}

	@Transient
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
