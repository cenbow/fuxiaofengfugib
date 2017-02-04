package com.org.weixin.system.domain;


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
import org.wechat.framework.domain.AbstractEntity;

import com.google.common.collect.Maps;

/**
 * sys_wechat_user Entity
 *
 * Date: 2015-07-23 20:46:53
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_wechat_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysWechatUser extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 未关注 */
	public static final byte SUBSCRIBE0 = 0;
	/** 已关注 */
	public static final byte SUBSCRIBE1 = 1;
		
	/** 是否关注{0:未关注,1:已关注} */
		public static final Map<Byte, String> allSubscribes = Maps.newTreeMap();
	static {
		allSubscribes.put(SUBSCRIBE0, "未关注");
		allSubscribes.put(SUBSCRIBE1, "已关注");
	}
	/** 男 */
	public static final byte SEX1 = 1;
	/** 女 */
	public static final byte SEX2 = 2;
		
	/** 性别{1:男,2:女} */
		public static final Map<Byte, String> allSexs = Maps.newTreeMap();
	static {
		allSexs.put(SEX1, "男");
		allSexs.put(SEX2, "女");
	}
	
	/** id */
	private Long id;
	/** 模块ID */
	private Long moduleId;
	/** openid */
	private String openid;
	/** 是否关注{0:未关注,1:已关注} */
	private byte subscribe;
	/** 用户昵称 */
	private String nickname;
	/** 性别{1:男,2:女} */
	private int sex;
	/** 城市 */
	private String city;
	/** 省份 */
	private String province;
	/** 国家 */
	private String country;
	/** 语言 */
	private String language;
	/** 用户头像 */
	private String headimgurl;
	/** 用户关注时间 */
	private Date subscribeTime;
	/** 用户组 */
	private Long groupid;
	/** 多个公众号之间用户帐号互通UnionID机制 */
	private String unionId;
	/** 公众用户ID */
	private Long accountId;
	/** 用户特权 json 数组 */
	private String privilege;
	/** 备注 */
	private String remark;
	/** 手机号码 */
	private String phone;
	/** 手机号码更新时间 */
	private Date phoneUpdateTime;
	/** 创建时间 */
	private Date createTime;
	/** 修改i时间 */
	private Date updateTime;
	/** 用户取消关注 */
	private byte unsubscribe;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getModuleId(){
		return this.moduleId;
	}
	
	public void setModuleId(Long moduleId){
		this.moduleId = moduleId;
	}
	public String getOpenid(){
		return this.openid;
	}
	
	public void setOpenid(String openid){
		this.openid = openid;
	}
	public byte getSubscribe(){
		return this.subscribe;
	}
	
	public void setSubscribe(byte subscribe){
		this.subscribe = subscribe;
	}
	public String getNickname(){
		return this.nickname;
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	public int getSex(){
		return this.sex;
	}
	
	public void setSex(int sex){
		this.sex = sex;
	}
	public String getCity(){
		return this.city;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	public String getProvince(){
		return this.province;
	}
	
	public void setProvince(String province){
		this.province = province;
	}
	public String getCountry(){
		return this.country;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	public String getLanguage(){
		return this.language;
	}
	
	public void setLanguage(String language){
		this.language = language;
	}
	public String getHeadimgurl(){
		return this.headimgurl;
	}
	
	public void setHeadimgurl(String headimgurl){
		this.headimgurl = headimgurl;
	}
	public Date getSubscribeTime(){
		return this.subscribeTime;
	}
	
	public void setSubscribeTime(Date subscribeTime){
		this.subscribeTime = subscribeTime;
	}
	public Long getGroupid(){
		return this.groupid;
	}
	
	public void setGroupid(Long groupid){
		this.groupid = groupid;
	}
	
	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Long getAccountId(){
		return this.accountId;
	}
	
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	public String getPrivilege(){
		return this.privilege;
	}
	
	public void setPrivilege(String privilege){
		this.privilege = privilege;
	}
	public String getRemark(){
		return this.remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getPhone(){
		return this.phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	public Date getPhoneUpdateTime(){
		return this.phoneUpdateTime;
	}
	
	public void setPhoneUpdateTime(Date phoneUpdateTime){
		this.phoneUpdateTime = phoneUpdateTime;
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
	public byte getUnsubscribe(){
		return this.unsubscribe;
	}
	
	public void setUnsubscribe(byte unsubscribe){
		this.unsubscribe = unsubscribe;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
