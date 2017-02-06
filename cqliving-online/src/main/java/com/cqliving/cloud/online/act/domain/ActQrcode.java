package com.cqliving.cloud.online.act.domain;


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
 * 二维码扫描活动 Entity
 * Date: 2016-12-16 15:15:51
 * @author Code Generator
 */
@Entity
@Table(name = "act_qrcode")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActQrcode extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 新闻 */
	public static final Byte SOURCETYPE1 = 1;
	/** 活动 */
	public static final Byte SOURCETYPE6 = 6;
		
	/** 回复源类型 */
	public static final Map<Byte, String> allSourceTypes = Maps.newTreeMap();
	static {
		allSourceTypes.put(SOURCETYPE1, "新闻");
		allSourceTypes.put(SOURCETYPE6, "活动");
	}
	/** 已上线 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "已上线");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** 二维码扫描 */
	public static final Byte TYPE1 = 1;
	/** 报名 */
	public static final Byte TYPE2 = 2;
	/** 活动类型 */
	public static final Map<Byte, String> allTypeTypes = Maps.newTreeMap();
	static {
		allTypeTypes.put(TYPE1, "二维码扫描");
		allTypeTypes.put(TYPE2, "报名");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 生成二维码的URL地址，在生成之前需要在该url后面拼上用户token和活动code */
	private String url;
	/** 活动名称 */
	private String name;
	/** 活动CODE，生成一个UUID */
	private String code;
	/** 回复源类型 */
	private Byte sourceType;
	/** source_type=1时，对应info_classify表的主键。source_type=6时，对应act_info_list表的主键 */
	private Long sourceId;
	/** 活动开始时间 */
	private Date startTime;
	/** 活动结束时间 */
	private Date endTime;
	/** 状态 */
	private Byte status;
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
	/** 活动类型 */
	private Byte type;
	/** 活动banner图片地址*/
	private String bannerImageUrl;
	/** 活动 投票限制类型*/
	private Byte voteLimitType;
	/** 活动投票限制值 */
	private Long voteLimitValue;
	
	public String getBannerImageUrl() {
		return bannerImageUrl;
	}

	public void setBannerImageUrl(String bannerImageUrl) {
		this.bannerImageUrl = bannerImageUrl;
	}

	public Byte getVoteLimitType() {
		return voteLimitType;
	}

	public void setVoteLimitType(Byte voteLimitType) {
		this.voteLimitType = voteLimitType;
	}

	public Long getVoteLimitValue() {
		return voteLimitValue;
	}

	public void setVoteLimitValue(Long voteLimitValue) {
		this.voteLimitValue = voteLimitValue;
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
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
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
	public Date getStartTime(){
		return this.startTime;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	public Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
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
	
	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
