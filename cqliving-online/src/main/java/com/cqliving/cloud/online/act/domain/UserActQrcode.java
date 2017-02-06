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
 * 用户参与二维码扫描活动表 Entity
 * Date: 2016-12-16 15:16:12
 * @author Code Generator
 */
@Entity
@Table(name = "user_act_qrcode")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActQrcode extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 未使用 */
	public static final Byte ISUSE0 = 0;
	/** 已使用 */
	public static final Byte ISUSE1 = 1;
		
	/** 状态 */
	public static final Map<Byte, String> allIsUses = Maps.newTreeMap();
	static {
		allIsUses.put(ISUSE0, "未使用");
		allIsUses.put(ISUSE1, "已使用");
	}
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
		allStatuss.put(STATUS1, "未审核");
		allStatuss.put(STATUS3, "已上线");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 用户二维码扫描活动表，表act_qrcode的code字估 */
	private String actQrcodeCode;
	/** 用户ID，usesr_account表主键 */
	private Long userId;
	/** 用户手机号码，不参与业务，只是记录 */
	private String phone;
	/** 用户当前使用token，不参与业务，只是记录 */
	private String token;
	/** 二维码地址 */
	private String qrcodeImageUrl;
	/** 状态 */
	private Byte isUse;
	/** 使用时间 */
	private Date useTime;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人名称 */
	private String creator;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getActQrcodeCode(){
		return this.actQrcodeCode;
	}
	
	public void setActQrcodeCode(String actQrcodeCode){
		this.actQrcodeCode = actQrcodeCode;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getPhone(){
		return this.phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getToken(){
		return this.token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	public String getQrcodeImageUrl(){
		return this.qrcodeImageUrl;
	}
	
	public void setQrcodeImageUrl(String qrcodeImageUrl){
		this.qrcodeImageUrl = qrcodeImageUrl;
	}
	public Byte getIsUse(){
		return this.isUse;
	}
	
	public void setIsUse(Byte isUse){
		this.isUse = isUse;
	}
	public Date getUseTime(){
		return this.useTime;
	}
	
	public void setUseTime(Date useTime){
		this.useTime = useTime;
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
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
