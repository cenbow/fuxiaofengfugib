package com.cqliving.cloud.online.recruitinfo.domain;


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
 * 人才招聘表 Entity
 * Date: 2016-10-11 14:08:19
 * @author Code Generator
 */
@Entity
@Table(name = "recruit_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RecruitInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 未审核 */
	public static final Byte STATUS1 = 1;
	/** 已上线 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "待上线");
		allStatuss.put(STATUS3, "已上线");
		allStatuss.put(STATUS88, "已下线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 企业名称 */
	private String name;
	/** 企业性质，见ol_option表TYPE_CODE = ENT_NATURE */
	private String nature;
	/** 企业规模，见ol_option表TYPE_CODE = ENT_SCALE */
	private String scale;
	/** 企业简介 */
	private String synopsis;
	/** 招聘职位 */
	private String position;
	/** 职位描述 */
	private String description;
	/** 职位月薪，见ol_option表TYPE_CODE = SALARY */
	private String salary;
	/** 招聘人数 */
	private String numberPeople;
	/** 工作性质，见ol_option表TYPE_CODE = WORKMODE */
	private String workmode;
	/** 联系电话 */
	private String telephone;
	/** 联系地址 */
	private String address;
	/** 发布日期 */
	private Date publicTime;
	/** 学历，见ol_option表TYPE_CODE = EDUCATION */
	private String education;
	/** 标签 */
	private String entLabel;
	/** 排序号 */
	private Integer sortNo;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	
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
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getNature(){
		return this.nature;
	}
	
	public void setNature(String nature){
		this.nature = nature;
	}
	public String getScale(){
		return this.scale;
	}
	
	public void setScale(String scale){
		this.scale = scale;
	}
	public String getSynopsis(){
		return this.synopsis;
	}
	
	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}
	public String getPosition(){
		return this.position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public String getSalary(){
		return this.salary;
	}
	
	public void setSalary(String salary){
		this.salary = salary;
	}
	public String getNumberPeople(){
		return this.numberPeople;
	}
	
	public void setNumberPeople(String numberPeople){
		this.numberPeople = numberPeople;
	}
	public String getWorkmode(){
		return this.workmode;
	}
	
	public void setWorkmode(String workmode){
		this.workmode = workmode;
	}
	public String getTelephone(){
		return this.telephone;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	public Date getPublicTime(){
		return this.publicTime;
	}
	
	public void setPublicTime(Date publicTime){
		this.publicTime = publicTime;
	}
	public String getEducation(){
		return this.education;
	}
	
	public void setEducation(String education){
		this.education = education;
	}
	public String getEntLabel(){
		return this.entLabel;
	}
	
	public void setEntLabel(String entLabel){
		this.entLabel = entLabel;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
