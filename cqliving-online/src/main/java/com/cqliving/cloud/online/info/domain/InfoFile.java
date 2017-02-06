package com.cqliving.cloud.online.info.domain;


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
 * 资讯对应的文件表 Entity
 * Date: 2016-05-28 12:25:21
 * @author Code Generator
 */
@Entity
@Table(name = "info_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoFile extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 视频 */
	public static final Byte TYPE1 = 1;
	/** 音频 */
	public static final Byte TYPE2 = 2;
	/** html */
	public static final Byte TYPE3 = 3;
	/** 图片 */
	public static final Byte TYPE4 = 4;
		
	/** 是否已加入专题 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE1, "视频");
		allTypes.put(TYPE2, "音频");
		allTypes.put(TYPE3, "html");
		allTypes.put(TYPE4, "图片");
	}
	/** 未下载 */
	public static final byte DOWNLOADSTATUS1 = 1;
	/** 已添加至下载任务队列 */
	public static final byte DOWNLOADSTATUS2 = 2;
	/** 已下载 */
	public static final Byte DOWNLOADSTATUS3 = 3;
		
	/** 下载状态 */
	public static final Map<Byte, String> allDownloadStatuss = Maps.newTreeMap();
	static {
		allDownloadStatuss.put(DOWNLOADSTATUS1, "未下载");
		allDownloadStatuss.put(DOWNLOADSTATUS2, "已添加至下载任务队列");
		allDownloadStatuss.put(DOWNLOADSTATUS3, "已下载");
	}
	/** 已保存 */
	public static final Byte STATUS1 = 1;
	/** 转码中 */
	public static final Byte STATUS2 = 2;
	/** 正常上线 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "已保存");
		allStatuss.put(STATUS2, "转码中");
		allStatuss.put(STATUS3, "正常上线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 引用ID（当A上传文件后，B又上传同一文件，则B不需要再上传，B引用A的ID） */
	private Long refId;
	/** 客户端_ID */
	private Long appId;
	/** 文件名称 */
	private String originalName;
	/** 七牛返回--音视频转码持久化的进度查询ID */
	private String qiniuPersistentId;
	/** 七牛返回--文件HASH值 */
	private String qiniuHash;
	/** 七牛返回--文件KEY，对应七牛对应的文件名称 */
	private String qiniuKey;
	/** 对应七牛下载地址 */
	private String cloudUrl;
	/** 是否已加入专题 */
	private Byte type;
	/** 本地系统相对文件路径 */
	private String filePath;
	/** 下载状态 */
	private Byte downloadStatus;
	/** 添加至下载队列时间 */
	private Date addQueueTime;
	/** 下载完成时间 */
	private Date downloadTime;
	/** 状态 */
	private Byte status;
	/** 转码成功时间 */
	private Date transcodingTime;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	/** 文件后缀 */
	private String extensions;
	/** 七牛资源文件夹 */
	private String qiniuDomain;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getRefId(){
		return this.refId;
	}
	
	public void setRefId(Long refId){
		this.refId = refId;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	
	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	public String getQiniuPersistentId() {
		return qiniuPersistentId;
	}

	public void setQiniuPersistentId(String qiniuPersistentId) {
		this.qiniuPersistentId = qiniuPersistentId;
	}

	public String getQiniuHash(){
		return this.qiniuHash;
	}
	
	public void setQiniuHash(String qiniuHash){
		this.qiniuHash = qiniuHash;
	}
	public String getQiniuKey(){
		return this.qiniuKey;
	}
	
	public void setQiniuKey(String qiniuKey){
		this.qiniuKey = qiniuKey;
	}
	public String getCloudUrl(){
		return this.cloudUrl;
	}
	
	public void setCloudUrl(String cloudUrl){
		this.cloudUrl = cloudUrl;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public String getFilePath(){
		return this.filePath;
	}
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	public Byte getDownloadStatus(){
		return this.downloadStatus;
	}
	
	public void setDownloadStatus(Byte downloadStatus){
		this.downloadStatus = downloadStatus;
	}
	public Date getAddQueueTime(){
		return this.addQueueTime;
	}
	
	public void setAddQueueTime(Date addQueueTime){
		this.addQueueTime = addQueueTime;
	}
	public Date getDownloadTime(){
		return this.downloadTime;
	}
	
	public void setDownloadTime(Date downloadTime){
		this.downloadTime = downloadTime;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Date getTranscodingTime(){
		return this.transcodingTime;
	}
	
	public void setTranscodingTime(Date transcodingTime){
		this.transcodingTime = transcodingTime;
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
	
	public String getExtensions() {
		return extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}
  
	public String getQiniuDomain() {
		return qiniuDomain;
	}

	public void setQiniuDomain(String qiniuDomain) {
		this.qiniuDomain = qiniuDomain;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
