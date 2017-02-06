package com.cqliving.cloud.online.account.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserFavoriteDto {

	/** ID */
	private Long id;
	/** 来源APP */
	private Long appId;
	/** 用户ID */
	private Long userId;
	/** 收藏来源栏目 */
	private Long sourceColumns;
	/** 栏目名称 */
	private String columnsName;
	/** 标题 */
	private String title;
	/** 图片 */
	private String imageUrl;
	/** 来源类型 */
	private Byte sourceType;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 审核人ID */
	private Long auditingId;
	/** 审核人姓名 */
	private String auditingtor;
	/** 审核时间 */
	private Date auditingTime;
	/** 来源ID，根据来源类型不同，引用不同表的主键 */
	private Long sourceId;
	
	//------------------------ 冗余字段 --------------------------
	
	/** 资讯浏览量 */
	private Integer viewCount;
	/** 资讯回复量 */
	private Integer replyCount;
	/** 列表显示类型 */
	private Byte	 listViewType;
	/** 资讯详情 id */
	private Long informationId;
	
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
	public Long getSourceColumns(){
		return this.sourceColumns;
	}
	
	public void setSourceColumns(Long sourceColumns){
		this.sourceColumns = sourceColumns;
	}
	public String getColumnsName(){
		return this.columnsName;
	}
	
	public void setColumnsName(String columnsName){
		this.columnsName = columnsName;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public Byte getSourceType(){
		return this.sourceType;
	}
	
	public void setSourceType(Byte sourceType){
		this.sourceType = sourceType;
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
	public Long getAuditingId(){
		return this.auditingId;
	}
	
	public void setAuditingId(Long auditingId){
		this.auditingId = auditingId;
	}
	public String getAuditingtor(){
		return this.auditingtor;
	}
	
	public void setAuditingtor(String auditingtor){
		this.auditingtor = auditingtor;
	}
	public Date getAuditingTime(){
		return this.auditingTime;
	}
	
	public void setAuditingTime(Date auditingTime){
		this.auditingTime = auditingTime;
	}
	
	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Byte getListViewType() {
		return listViewType;
	}

	public void setListViewType(Byte listViewType) {
		this.listViewType = listViewType;
	}

	public Long getInformationId() {
		return informationId;
	}

	public void setInformationId(Long informationId) {
		this.informationId = informationId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
