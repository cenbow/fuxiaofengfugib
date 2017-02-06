package com.cqliving.cloud.online.config.domain;


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
 * 公租房表 Entity
 * Date: 2016-11-07 16:34:55
 * @author Code Generator
 */
@Entity
@Table(name = "housing_public")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HousingPublic extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 女 */
	public static final Byte SEX0 = 0;
	/** 男 */
	public static final Byte SEX1 = 1;
		
	/** 性别 */
	public static final Map<Byte, String> allSexs = Maps.newTreeMap();
	static {
		allSexs.put(SEX0, "女");
		allSexs.put(SEX1, "男");
	}
	/** 已发布 */
	public static final Byte STATUS3 = 3;
	/** 已下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 问题操作后状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "已发布");
		allStatuss.put(STATUS88, "已下线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 用户姓名 */
	private String userName;
	/** 用户身份证号 */
	private String cardNum;
	/** 性别 */
	private Byte sex;
	/** 工作单位 */
	private String workUnit;
	/** 户型 */
	private String houseType;
	/** 申请方式 */
	private String applyType;
	/** 配租结果，就是房屋地址 */
	private String address;
	/** 问题操作后状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 发布时间 */
	private Date publicTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getCardNum(){
		return this.cardNum;
	}
	
	public void setCardNum(String cardNum){
		this.cardNum = cardNum;
	}
	public Byte getSex(){
		return this.sex;
	}
	
	public void setSex(Byte sex){
		this.sex = sex;
	}
	public String getWorkUnit(){
		return this.workUnit;
	}
	
	public void setWorkUnit(String workUnit){
		this.workUnit = workUnit;
	}
	public String getHouseType(){
		return this.houseType;
	}
	
	public void setHouseType(String houseType){
		this.houseType = houseType;
	}
	public String getApplyType(){
		return this.applyType;
	}
	
	public void setApplyType(String applyType){
		this.applyType = applyType;
	}
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
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
	public Date getPublicTime(){
		return this.publicTime;
	}
	
	public void setPublicTime(Date publicTime){
		this.publicTime = publicTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
