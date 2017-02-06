package com.cqliving.cloud.online.order.domain;


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
 * 订单 Entity
 * Date: 2016-11-21 21:35:10
 * @author Code Generator
 */
@Entity
@Table(name = "order_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	//订单过期时间 6个小时
	public static final Integer payExprieTime = 6*60*60*1000;

	/** 待支付 */
	public static final Byte PAYFORSTATUS1 = 1;
	/** 待发货 */
	public static final Byte PAYFORSTATUS2 = 2;
	/** 待收货 */
	public static final Byte PAYFORSTATUS3 = 3;
	/** 待评价 */
	public static final Byte PAYFORSTATUS4 = 4;
	/** 已取消 */
	public static final Byte PAYFORSTATUS5 = 5;
	/** 已完成 */
	public static final Byte PAYFORSTATUS6 = 6;
	/** 退款中 界面显示用，数据库不存这个值 */
	public static final Byte PAYFORSTATUS7 = 7;
	/** 已退款 */
//	public static final Byte PAYFORSTATUS8 = 8;
		
	/** 订单支付状态 */
	public static final Map<Byte, String> allPayforStatuss = Maps.newTreeMap();
	static {
		allPayforStatuss.put(PAYFORSTATUS1, "待支付");
		allPayforStatuss.put(PAYFORSTATUS2, "待发货");
		allPayforStatuss.put(PAYFORSTATUS3, "待收货");
		allPayforStatuss.put(PAYFORSTATUS4, "待评价");
		allPayforStatuss.put(PAYFORSTATUS5, "已取消");
		allPayforStatuss.put(PAYFORSTATUS6, "已完成");
		allPayforStatuss.put(PAYFORSTATUS7, "退款中");//界面显示用，数据库不存这个值
//		allPayforStatuss.put(PAYFORSTATUS8, "已退款");
	}
	/** 订单支付状态 */
	public static final Map<Byte, String> allPayforStatussFront = Maps.newTreeMap();
	static {
		allPayforStatussFront.put(PAYFORSTATUS1, "待支付");
		allPayforStatussFront.put(PAYFORSTATUS2, "待发货");
		allPayforStatussFront.put(PAYFORSTATUS3, "已发货");
		allPayforStatussFront.put(PAYFORSTATUS4, "待评价");
		allPayforStatussFront.put(PAYFORSTATUS5, "已取消");
		allPayforStatussFront.put(PAYFORSTATUS6, "已完成");
		allPayforStatussFront.put(PAYFORSTATUS7, "退款中");//界面显示用，数据库不存这个值
	}
	/** 正常 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 =  99;
		
	/** 订单状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "正常");
		allStatuss.put(STATUS99, "删除");
	}
	/** 支付宝 */
	public static final Integer PAYMODE1 = 1;
		
	/** 支付渠道 */
	public static final Map<Integer, String> allPayModes = Maps.newTreeMap();
	static {
		allPayModes.put(PAYMODE1, "支付宝");
	}
	
	/** 主键 */
	private Long id;
	/** 客户端id */
	private Long appId;
	/** 用户id */
	private Long userId;
	/** 订单号 */
	private String orderNo;
	/** 订单金额（分） */
	private Integer orderAmount;
	/** 快递公司 */
	private String expressCompany;
	/** 快递单号 */
	private String expressNo;
	/** 运费（分），没有则为0 */
	private Integer shippingFare;
	/** 订单备注 */
	private String descn;
	/** 订单支付状态 */
	private Byte payforStatus;
	/** 订单状态 */
	private Byte status;
	/** 购买人姓名 */
	private String userName;
	/** 购买人手机号码 */
	private String userPhone;
	/** 支付渠道 */
	private Integer payMode;
	/** 订单创建时间 */
	private Date createTime;
	/** 操作员id */
	private Long updaterId;
	/** 修改时间 */
	private Date operateTime;
	/** 收货人地址 */
	private String receiverAddress;
	/** 收货人姓名 */
	private String receiverName;
	/** 收货人电话 */
	private String receiverPhone;
	/** 支付帐号 */
	private String payAccount;
	
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
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getOrderNo(){
		return this.orderNo;
	}
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	public Integer getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getExpressCompany(){
		return this.expressCompany;
	}
	public void setExpressCompany(String expressCompany){
		this.expressCompany = expressCompany;
	}
	public String getExpressNo(){
		return this.expressNo;
	}
	public void setExpressNo(String expressNo){
		this.expressNo = expressNo;
	}
	public Integer getShippingFare(){
		return this.shippingFare;
	}
	
	public void setShippingFare(Integer shippingFare){
		this.shippingFare = shippingFare;
	}
	public String getDescn(){
		return this.descn;
	}
	
	public void setDescn(String descn){
		this.descn = descn;
	}
	public Byte getPayforStatus(){
		return this.payforStatus;
	}
	
	public void setPayforStatus(Byte payforStatus){
		this.payforStatus = payforStatus;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserPhone(){
		return this.userPhone;
	}
	
	public void setUserPhone(String userPhone){
		this.userPhone = userPhone;
	}
	public Integer getPayMode(){
		return this.payMode;
	}
	
	public void setPayMode(Integer payMode){
		this.payMode = payMode;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getUpdaterId(){
		return this.updaterId;
	}
	
	public void setUpdaterId(Long updaterId){
		this.updaterId = updaterId;
	}
	public Date getOperateTime(){
		return this.operateTime;
	}
	
	public void setOperateTime(Date operateTime){
		this.operateTime = operateTime;
	}
	
	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
