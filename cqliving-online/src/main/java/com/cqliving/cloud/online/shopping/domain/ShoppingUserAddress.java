package com.cqliving.cloud.online.shopping.domain;


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
 * 用户收货地址表 Entity
 * Date: 2016-11-17 15:41:44
 * @author Code Generator
 */
@Entity
@Table(name = "shopping_user_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShoppingUserAddress extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 否 */
	public static final Byte ISDEFAULT0 = 0;
	/** 是 */
	public static final Byte ISDEFAULT1 = 1;
		
	/** 是否默认地址 */
	public static final Map<Byte, String> allIsDefaults = Maps.newTreeMap();
	static {
		allIsDefaults.put(ISDEFAULT0, "否");
		allIsDefaults.put(ISDEFAULT1, "是");
	}
	/** 正常 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "正常");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 收货人 */
	private String recivier;
	/** 收货人手机号 */
	private String cellphone;
	/** 一级区域ID */
	private Long regionLevelOneId;
	/** 一级区域名称 */
	private String regionLevelOneName;
	/** 二级区域ID */
	private Long regionLevelTwoId;
	/** 二级区域名称 */
	private String regionLevelTwoName;
	/** 三级级区域ID */
	private Long regionLevelThreeId;
	/** 三级区域名称 */
	private String regionLevelThreeName;
	/** 四级级区域ID */
	private Long regionLevelFourId;
	/** 四级区域名称 */
	private String regionLevelFourName;
	/** 详细地址 */
	private String address;
	/** 完整地址（展示用） */
	private String fullAddress;
	/** 邮编 */
	private String zip;
	/** 是否默认地址 */
	private Byte isDefault;
	/** 用户ID */
	private Long userId;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	
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
	public String getRecivier(){
		return this.recivier;
	}
	
	public void setRecivier(String recivier){
		this.recivier = recivier;
	}
	public String getCellphone(){
		return this.cellphone;
	}
	
	public void setCellphone(String cellphone){
		this.cellphone = cellphone;
	}
	public Long getRegionLevelOneId(){
		return this.regionLevelOneId;
	}
	
	public void setRegionLevelOneId(Long regionLevelOneId){
		this.regionLevelOneId = regionLevelOneId;
	}
	public String getRegionLevelOneName(){
		return this.regionLevelOneName;
	}
	
	public void setRegionLevelOneName(String regionLevelOneName){
		this.regionLevelOneName = regionLevelOneName;
	}
	public Long getRegionLevelTwoId(){
		return this.regionLevelTwoId;
	}
	
	public void setRegionLevelTwoId(Long regionLevelTwoId){
		this.regionLevelTwoId = regionLevelTwoId;
	}
	public String getRegionLevelTwoName(){
		return this.regionLevelTwoName;
	}
	
	public void setRegionLevelTwoName(String regionLevelTwoName){
		this.regionLevelTwoName = regionLevelTwoName;
	}
	public Long getRegionLevelThreeId(){
		return this.regionLevelThreeId;
	}
	
	public void setRegionLevelThreeId(Long regionLevelThreeId){
		this.regionLevelThreeId = regionLevelThreeId;
	}
	public String getRegionLevelThreeName(){
		return this.regionLevelThreeName;
	}
	
	public void setRegionLevelThreeName(String regionLevelThreeName){
		this.regionLevelThreeName = regionLevelThreeName;
	}
	public Long getRegionLevelFourId(){
		return this.regionLevelFourId;
	}
	
	public void setRegionLevelFourId(Long regionLevelFourId){
		this.regionLevelFourId = regionLevelFourId;
	}
	public String getRegionLevelFourName(){
		return this.regionLevelFourName;
	}
	
	public void setRegionLevelFourName(String regionLevelFourName){
		this.regionLevelFourName = regionLevelFourName;
	}
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	public String getFullAddress(){
		return this.fullAddress;
	}
	
	public void setFullAddress(String fullAddress){
		this.fullAddress = fullAddress;
	}
	public String getZip(){
		return this.zip;
	}
	
	public void setZip(String zip){
		this.zip = zip;
	}
	public Byte getIsDefault(){
		return this.isDefault;
	}
	
	public void setIsDefault(Byte isDefault){
		this.isDefault = isDefault;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
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
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
