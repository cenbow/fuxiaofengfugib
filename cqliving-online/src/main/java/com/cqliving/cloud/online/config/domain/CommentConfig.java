package com.cqliving.cloud.online.config.domain;


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
 * 评论配置表 Entity
 * Date: 2016-06-12 10:01:52
 * @author Code Generator
 */
@Entity
@Table(name = "comment_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommentConfig extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	/** 随手拍是否需要审核 */
	public static final String SHOOT_INFO_NEED_AUDIT = "随手拍是否审核";
	/** 话题是否需要审核 */
	public static final String TOPIC_INFO_NEED_AUDIT = "话题是否审核";
	/** 是否允许评论 */
	public static final String CAN_COMMENT = "是否允许评论";
	/** 评论是否需要审核 */
	public static final String COMMENT_NEED_AUDIT = "评论是否审核";
	/** 是否允许用户上传商户信息 */
	public static final String SHOP_ALLOW_USER_ADD = "是否允许用户上传商户信息";
	/** 用户上传商户信息是否审核 */
	public static final String SHOP_USER_ADD_NEET_AUDIT = "用户上传商户信息是否审核";
	/** 用户是否登陆上传商户信息 */
	public static final String SHOP_USER_ADD_NEED_LOGIN = "用户是否登录上传商户信息";

	/** 否 */
	public static final Byte VALUE0 = 0;
	/** 是 */
	public static final Byte VALUE1 = 1;
		
	/** 参数值 */
	public static final Map<Byte, String> allValues = Maps.newTreeMap();
	static {
		allValues.put(VALUE0, "否");
		allValues.put(VALUE1, "是");
	}
	
	/** ID */
	private Long id;
	/** 参数名称 */
	private String name;
	/** 参数值 */
	private Byte value;
	/** 创建时间 */
	private Date createTime;
	/** 类型 */
	private Byte type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Byte getValue(){
		return this.value;
	}
	
	public void setValue(Byte value){
		this.value = value;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
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
