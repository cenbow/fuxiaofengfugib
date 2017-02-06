package com.cqliving.cloud.online.order.domain;


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
 * 订单支付配置表 Entity
 * Date: 2016-11-21 21:35:27
 * @author Code Generator
 */
@Entity
@Table(name = "order_pay_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderPayConfig extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 支付宝 */
	public static final Byte PAYMODEL1 = 1;
		
	/** 支付渠道 */
	public static final Map<Byte, String> allPayModeLs = Maps.newTreeMap();
	static {
		allPayModeLs.put(PAYMODEL1, "支付宝");
	}
	
	/** 主键 */
	private Long id;
	/** 客户端id */
	private Long appId;
	/** 商户id */
	private String payAppId;
	/** key */
	private String privateKey;
	/** 支付宝公钥 */
	private String publicKey;
	/** 支付渠道 */
	private Byte payModel;
	
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
	public String getPayAppId() {
		return payAppId;
	}

	public void setPayAppId(String payAppId) {
		this.payAppId = payAppId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public Byte getPayModel() {
		return payModel;
	}

	public void setPayModel(Byte payModel) {
		this.payModel = payModel;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
