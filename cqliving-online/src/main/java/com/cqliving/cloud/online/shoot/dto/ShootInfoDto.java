package com.cqliving.cloud.online.shoot.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ShootInfoDto {

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
	/** 是否允许评论 */
	private Byte isComment;
	/** 评论是否需审核 */
	private Byte isCommentAudit;
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
	
	//------------------------ 冗余字段 --------------------------
	/** 用户头像 */
	private String userImg;
	/** 用户昵称 */
	private String nickname;
	/** 图片路径 */
	private String imgs;
	/** 图片描述 */
	private String descs;
	/** 登录名 */
	private String loginName;
	/** 是否已点赞 */
	private Byte isPraised;
	/** 点赞用户 id */
	private Long praiseUserId;
	/** 创建时间 */
	private String createTimeStr;
	
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
	public Byte getIsComment(){
		return this.isComment;
	}
	
	public void setIsComment(Byte isComment){
		this.isComment = isComment;
	}
	public Byte getIsCommentAudit(){
		return this.isCommentAudit;
	}
	
	public void setIsCommentAudit(Byte isCommentAudit){
		this.isCommentAudit = isCommentAudit;
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
	
	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickName) {
		this.nickname = nickName;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public Long getPraiseUserId() {
		return praiseUserId;
	}

	public void setPraiseUserId(Long praiseUserId) {
		this.praiseUserId = praiseUserId;
	}

	public Byte getIsPraised() {
		return isPraised;
	}

	public void setIsPraised(Byte isPraised) {
		this.isPraised = isPraised;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
