package com.cqliving.cloud.online.info.domain;


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
 * 资讯详情表 Entity
 * Date: 2016-05-07 09:58:55
 * @author Code Generator
 */
@Entity
@Table(name = "information")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Information extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 普通新闻 */
	public static final Byte TYPE0 = 0;
	/** 主题新闻 */
	public static final Byte TYPE2 = 2;
		
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "普通新闻");
		allTypes.put(TYPE2, "专题新闻");
	}
	/** 全部可见 */
	public static final String PLVIEWTYPE0 = "0";
	/** 客户端可见 */
	public static final String PLVIEWTYPE1 = "1";
	/** IOS可见 */
	public static final String PLVIEWTYPE2 = "2";
	/** WEB可见 */
	public static final String PLVIEWTYPE3 = "3";
		
	/** 平台可见类型 */
	public static final Map<String, String> allPlViewTypes = Maps.newTreeMap();
	static {
		allPlViewTypes.put(PLVIEWTYPE0, "全部可见");
		allPlViewTypes.put(PLVIEWTYPE1, "客户端可见");
		allPlViewTypes.put(PLVIEWTYPE2, "IOS可见");
		allPlViewTypes.put(PLVIEWTYPE3, "WEB可见");
	}
	
	/** 无需审核 */
	public static final Byte VALIDATETYPE0 = 0;
	/** 需审核 */
	public static final Byte VALIDATETYPE1 = 1;
		
	/** 内容需审核 */
	public static final Map<Byte, String> allValidateTypes = Maps.newTreeMap();
	static {
		allValidateTypes.put(VALIDATETYPE0, "无需审核");
		allValidateTypes.put(VALIDATETYPE1, "需审核");
	}
	/** 一次添加 */
	public static final Byte ADDTYPE0 = 0;
	/** 逐步添加 */
	public static final Byte ADDTYPE1 = 1;
		
	/** 阅读量增加类型 */
	public static final Map<Byte, String> allAddTypes = Maps.newTreeMap();
	static {
		allAddTypes.put(ADDTYPE0, "一次添加");
		allAddTypes.put(ADDTYPE1, "逐步添加");
	}
	/** 纯文本 */
	public static final Byte CONTEXTTYPE0 = 0;
	/** 多图 */
	public static final Byte CONTEXTTYPE1 = 1;
	/** 原创 */
	public static final Byte CONTEXTTYPE2 = 2;
	/** 外链 */
	public static final Byte CONTEXTTYPE3 = 3;
	/** 音频 */
	public static final Byte CONTEXTTYPE4 = 4;
	/** 视频 */
	public static final Byte CONTEXTTYPE5 = 5;
		
	/** 新闻内容类型 */
	public static final Map<Byte, String> allContextTypes = Maps.newTreeMap();
	static {
		allContextTypes.put(CONTEXTTYPE0, "纯文本");
		allContextTypes.put(CONTEXTTYPE1, "多图");
		allContextTypes.put(CONTEXTTYPE2, "原创");
		allContextTypes.put(CONTEXTTYPE3, "外链");
		allContextTypes.put(CONTEXTTYPE4, "音频");
		allContextTypes.put(CONTEXTTYPE5, "视频");
	}
	/** 未上传 */
	public static final Byte VIDEOSTATUS1 = 1;
	/** 转码中 */
	public static final Byte VIDEOSTATUS2 = 2;
	/** 转码完成 */
	public static final Byte VIDEOSTATUS3 = 3;
		
	/** 视频和音频转码状态 */
	public static final Map<Byte, String> allVideoStatuss = Maps.newTreeMap();
	static {
		allVideoStatuss.put(VIDEOSTATUS1, "未上传");
		allVideoStatuss.put(VIDEOSTATUS2, "转码中");
		allVideoStatuss.put(VIDEOSTATUS3, "转码完成");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 资讯标签,多个用,分隔，注意  前边也要带,号，例   ,1,2 */
	private String infoLabel;
	/** 新闻摘要 */
	private String synopsis;
	/** 类型 */
	private Byte type;
	/** 关键字 */
	private String keywords;
	/** 来源网站，文字 */
	private String infoSource;
	/** 平台可见类型 */
	private String plViewType;
	/** 内容需审核 */
	private Byte validateType;
	/** 初始阅读量 */
	private Long initCount;
	/** 阅读量增加类型 */
	private Byte addType;
	/** 达到峰值时间，以秒为单位 */
	private Integer topTime;
	/** 资讯浏览量 */
	private Integer viewCount;
	/** 资讯回复量 */
	private Integer replyCount;
	/** 内容,包含HTML标签的富文本内容 */
	private String content;
	/** 资讯内容的全文本，不带HTML标签的 */
	private String contentText;
	/** 内容,新闻的实际内容URL,对应生成后内容 */
	private String contentUrl;
	/** 原始栏目ID，为方便后续统计，新闻先归属其中一个栏目。 */
	private Long classifyId;
	/** 新闻内容类型 */
	private Byte contextType;
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
	/** 审核人ID */
	private Long auditingId;
	/** 审核人姓名 */
	private String auditingtor;
	/** 审核时间 */
	private Date auditingTime;
	/** 视频和音频转码状态 */
	private Byte videoStatus;
	/** 点赞数 */
	private Integer praiseCount;
	/** 多图新闻图片数量 */
	private Integer multipleImgCount;
	
	private Long infoClassifyListId;
	
	@Transient
	public Long getInfoClassifyListId() {
		return infoClassifyListId;
	}
	public void setInfoClassifyListId(Long infoClassifyListId) {
		this.infoClassifyListId = infoClassifyListId;
	}

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
	public String getInfoLabel(){
		return this.infoLabel;
	}
	
	public void setInfoLabel(String infoLabel){
		this.infoLabel = infoLabel;
	}
	
	public String getSynopsis(){
		return this.synopsis;
	}
	
	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public String getKeywords(){
		return this.keywords;
	}
	
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	public String getInfoSource(){
		return this.infoSource;
	}
	
	public void setInfoSource(String infoSource){
		this.infoSource = infoSource;
	}
	public String getPlViewType(){
		return this.plViewType;
	}
	
	public void setPlViewType(String plViewType){
		this.plViewType = plViewType;
	}
	public Byte getValidateType(){
		return this.validateType;
	}
	
	public void setValidateType(Byte validateType){
		this.validateType = validateType;
	}
	public Long getInitCount(){
		return this.initCount;
	}
	
	public void setInitCount(Long initCount){
		this.initCount = initCount;
	}
	public Byte getAddType(){
		return this.addType;
	}
	
	public void setAddType(Byte addType){
		this.addType = addType;
	}
	public Integer getTopTime(){
		return this.topTime;
	}
	
	public void setTopTime(Integer topTime){
		this.topTime = topTime;
	}
	public Integer getViewCount(){
		return this.viewCount;
	}
	
	public void setViewCount(Integer viewCount){
		this.viewCount = viewCount;
	}
	public Integer getReplyCount(){
		return this.replyCount;
	}
	
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getContentText(){
		return this.contentText;
	}
	
	public void setContentText(String contentText){
		this.contentText = contentText;
	}
	public String getContentUrl(){
		return this.contentUrl;
	}
	
	public void setContentUrl(String contentUrl){
		this.contentUrl = contentUrl;
	}
	public Long getClassifyId(){
		return this.classifyId;
	}
	
	public void setClassifyId(Long classifyId){
		this.classifyId = classifyId;
	}
	public Byte getContextType(){
		return this.contextType;
	}
	
	public void setContextType(Byte contextType){
		this.contextType = contextType;
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
	public Byte getVideoStatus(){
		return this.videoStatus;
	}
	
	public void setVideoStatus(Byte videoStatus){
		this.videoStatus = videoStatus;
	}
	
	public Integer getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}
	public Integer getMultipleImgCount() {
		return multipleImgCount;
	}
	public void setMultipleImgCount(Integer multipleImgCount) {
		this.multipleImgCount = multipleImgCount;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
