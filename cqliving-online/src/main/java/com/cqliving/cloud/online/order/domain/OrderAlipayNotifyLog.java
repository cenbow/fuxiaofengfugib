package com.cqliving.cloud.online.order.domain;


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

/**
 * Title:订单支付宝支付异步通知日志记录表 Entity
 * <p>Description:
 * 对于App支付产生的交易，支付宝会根据原始支付API中传入的异步通知地址notify_url，通过POST请求的形式将支付结果作为参数通知到商户系统。
 * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
 * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
 * 4、验证app_id是否为该商户本身。
 * 上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
 * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
 * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
 * 注：
 * 	状态TRADE_SUCCESS的通知触发条件是商户签约的产品支持退款功能的前提下，买家付款成功；
 * 	交易状态TRADE_FINISHED的通知触发条件是商户签约的产品不支持退款功能的前提下，买家付款成功；或者，商户签约的产品支持退款功能的前提下，交易已经成功并且已经超过可退款期限。
 * </p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月1日
 */
@Entity
@Table(name = "order_alipay_notify_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderAlipayNotifyLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 交易目前所处的状态，见交易状态说明 
	 */
	/** 交易创建，等待买家付款 */
	public final static String TRADE_STATUS_1 = "WAIT_BUYER_PAY";
	/** 未付款交易超时关闭，或支付完成后全额退款 */
	public final static String TRADE_STATUS_2 = "TRADE_CLOSED";
	/** 交易支付成功 */
	public final static String TRADE_STATUS_3 = "TRADE_SUCCESS";
	/** 交易结束，不可退款 */
	public final static String TRADE_STATUS_4 = "TRADE_FINISHED";
	
	/** id */
	private Long id;
	/** 通知的发送时间。格式为yyyy-MM-dd HH:mm:ss */
	private Date notifyTime;
	/** 通知的类型 */
	private String notifyType;
	/** 通知校验ID */
	private String notifyId;
	/** 支付宝分配给开发者的应用Id */
	private String payAppId;
	/** 编码格式，如utf-8、gbk、gb2312等 */
	private String charset;
	/** 	调用的接口版本，固定为：1.0 */
	private String version;
	/** 签名算法类型，目前支持RSA */
	private String signType;
	/** 请参考异步返回结果的验签 */
	private String sign;
	/** 支付宝交易凭证号 */
	private String tradeNo;
	/** 原支付请求的商户订单号 */
	private String outTradeNo;
	/** 买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字 */
	private String buyerId;
	/** 买家支付宝账号 */
	private String buyerLogonId;
	/** 卖家支付宝用户号 */
	private String sellerId;
	/** 卖家支付宝账号 */
	private String sellerEmail;
	/** 交易目前所处的状态，见交易状态说明 */
	private String tradeStatus;
	/** 本次交易支付的订单金额，单位为人民币（元） */
	private Double totalAmount;
	/** 商家在交易中实际收到的款项，单位为元 */
	private Double receiptAmount;
	/** 用户在交易中支付的可开发票的金额 */
	private Double invoiceAmount;
	/** 用户在交易中支付的金额 */
	private Double buyerPayAmount;
	/** 商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来 */
	private String subject;
	/** 该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来 */
	private String body;
	/** 该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss */
	private Date gmtCreate;
	/** 该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss */
	private Date gmtPayment;
	/** 支付成功的各个渠道金额信息，详见资金明细信息说明 */
	private String fundBillList;
	/** 保存到数据库的时间 */
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Date getNotifyTime(){
		return this.notifyTime;
	}
	
	public void setNotifyTime(Date notifyTime){
		this.notifyTime = notifyTime;
	}
	public String getNotifyType(){
		return this.notifyType;
	}
	
	public void setNotifyType(String notifyType){
		this.notifyType = notifyType;
	}
	public String getNotifyId(){
		return this.notifyId;
	}
	
	public void setNotifyId(String notifyId){
		this.notifyId = notifyId;
	}
	public String getPayAppId(){
		return this.payAppId;
	}
	
	public void setPayAppId(String payAppId){
		this.payAppId = payAppId;
	}
	public String getCharset(){
		return this.charset;
	}
	
	public void setCharset(String charset){
		this.charset = charset;
	}
	public String getVersion(){
		return this.version;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	public String getSignType(){
		return this.signType;
	}
	
	public void setSignType(String signType){
		this.signType = signType;
	}
	public String getSign(){
		return this.sign;
	}
	
	public void setSign(String sign){
		this.sign = sign;
	}
	public String getTradeNo(){
		return this.tradeNo;
	}
	
	public void setTradeNo(String tradeNo){
		this.tradeNo = tradeNo;
	}
	public String getOutTradeNo(){
		return this.outTradeNo;
	}
	
	public void setOutTradeNo(String outTradeNo){
		this.outTradeNo = outTradeNo;
	}
	public String getBuyerId(){
		return this.buyerId;
	}
	
	public void setBuyerId(String buyerId){
		this.buyerId = buyerId;
	}
	public String getBuyerLogonId(){
		return this.buyerLogonId;
	}
	
	public void setBuyerLogonId(String buyerLogonId){
		this.buyerLogonId = buyerLogonId;
	}
	public String getSellerId(){
		return this.sellerId;
	}
	
	public void setSellerId(String sellerId){
		this.sellerId = sellerId;
	}
	public String getSellerEmail(){
		return this.sellerEmail;
	}
	
	public void setSellerEmail(String sellerEmail){
		this.sellerEmail = sellerEmail;
	}
	public String getTradeStatus(){
		return this.tradeStatus;
	}
	
	public void setTradeStatus(String tradeStatus){
		this.tradeStatus = tradeStatus;
	}
	public Double getTotalAmount(){
		return this.totalAmount;
	}
	
	public void setTotalAmount(Double totalAmount){
		this.totalAmount = totalAmount;
	}
	public Double getReceiptAmount(){
		return this.receiptAmount;
	}
	
	public void setReceiptAmount(Double receiptAmount){
		this.receiptAmount = receiptAmount;
	}
	public Double getInvoiceAmount(){
		return this.invoiceAmount;
	}
	
	public void setInvoiceAmount(Double invoiceAmount){
		this.invoiceAmount = invoiceAmount;
	}
	public Double getBuyerPayAmount(){
		return this.buyerPayAmount;
	}
	
	public void setBuyerPayAmount(Double buyerPayAmount){
		this.buyerPayAmount = buyerPayAmount;
	}
	public String getSubject(){
		return this.subject;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
	}
	public String getBody(){
		return this.body;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	public Date getGmtCreate(){
		return this.gmtCreate;
	}
	
	public void setGmtCreate(Date gmtCreate){
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtPayment(){
		return this.gmtPayment;
	}
	
	public void setGmtPayment(Date gmtPayment){
		this.gmtPayment = gmtPayment;
	}
	public String getFundBillList(){
		return this.fundBillList;
	}
	
	public void setFundBillList(String fundBillList){
		this.fundBillList = fundBillList;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
