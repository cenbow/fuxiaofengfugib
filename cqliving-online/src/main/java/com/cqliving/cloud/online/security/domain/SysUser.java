package com.cqliving.cloud.online.security.domain;


import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 系统用户表 Entity
 * Date: 2016-04-15 11:00:52
 * @author Code Generator
 */
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUser extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 管理员 */
	public static final Byte USERTYPE1 = 1;
	/** 操作员 */
	public static final Byte USERTYPE2 = 2;
	/** 区县管理员 */
	public static final Byte USERTYPE3 = 3;	
	/** 用户类型 */
	public static final Map<Byte, String> allUsertypes = Maps.newTreeMap();
	static {
		allUsertypes.put(USERTYPE1, "管理员");
		allUsertypes.put(USERTYPE2, "操作员");
		allUsertypes.put(USERTYPE3, "区县管理员");
	}
	/** 禁用 */
	public static final Byte STATUS0 = 0;
	/** 启用 */
	public static final Byte STATUS1 = 1;
	/** 锁定 */
	public static final Byte STATUS2 = 2;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS0, "禁用");
		allStatuss.put(STATUS1, "启用");
		allStatuss.put(STATUS2, "锁定");
	}
	
	/** 主键ID */
	private Long id;
	/** 登录用户名 */
	private String username;
	/** 操作员姓名 */
	private String nickname;
	/** 登录密码 */
	private String password;
	/** 密码加密填充值 */
	private String salt;
	/** 用户类型 */
	private Byte usertype;
	/** 客户端ID，如果该值为空，则可以看全部数据 */
	private Long appId;
	/** 电子邮件 */
	private String email;
	/** 状态 */
	private Byte status;
	/** 描述 */
	private String descn;
	/** 创建时间 */
	private Date createDate;
	/** 最后登录时间 */
	private Date lastLoginDate;
	/** 最后登录IP */
	private String lastLoginIp;
	/** 过期时间 */
	private Date expiredDate;
	/** 解锁时间 */
	private Date unlockDate;
	/** 头像 */
	private String imgUrl;
	/** 手机 */
	private String mobile;
	/** QQ */
	private String qqCode;
	/** 职位 */
	private String position;
	
	private Set<SysRole> role; 
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = SysRole.class)
	@JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @OrderBy(value = "id")
	public Set<SysRole> getRole() {
		return role;
	}

	public void setRole(Set<SysRole> role) {
		this.role = role;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	public String getNickname(){
		return this.nickname;
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getSalt(){
		return this.salt;
	}
	
	public void setSalt(String salt){
		this.salt = salt;
	}
	public Byte getUsertype(){
		return this.usertype;
	}
	
	public void setUsertype(Byte usertype){
		this.usertype = usertype;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public String getDescn(){
		return this.descn;
	}
	
	public void setDescn(String descn){
		this.descn = descn;
	}
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	public Date getLastLoginDate(){
		return this.lastLoginDate;
	}
	
	public void setLastLoginDate(Date lastLoginDate){
		this.lastLoginDate = lastLoginDate;
	}
	public String getLastLoginIp(){
		return this.lastLoginIp;
	}
	
	public void setLastLoginIp(String lastLoginIp){
		this.lastLoginIp = lastLoginIp;
	}
	public Date getExpiredDate(){
		return this.expiredDate;
	}
	
	public void setExpiredDate(Date expiredDate){
		this.expiredDate = expiredDate;
	}
	public Date getUnlockDate(){
		return this.unlockDate;
	}
	
	public void setUnlockDate(Date unlockDate){
		this.unlockDate = unlockDate;
	}
	public String getImgUrl(){
		return this.imgUrl;
	}
	
	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}
	public String getMobile(){
		return this.mobile;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	public String getQqCode(){
		return this.qqCode;
	}
	
	public void setQqCode(String qqCode){
		this.qqCode = qqCode;
	}
	public String getPosition(){
		return this.position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
