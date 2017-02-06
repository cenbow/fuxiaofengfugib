package com.cqliving.cloud.online.order.domain;

/**
 * Title:订单创建成功后返回的数据，包括支付配置和订单信息
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月1日
 */
public class OrderCreateSuccessData {
	
	/** 支付宝分配给开发者的应用ID */
	private String alipayAppId;
	/** 商户请求参数的签名串 */
	private String sign;
	/** 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。 */
	private String body;
	/** 商品的标题/交易标题/订单标题/订单关键字等。 */
	private String subject;
	/** 商户网站唯一订单号 */
	private String outTradeNo;
	/** 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] */
	private String totalAmount;
	/** 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY */
	private String productCode;
}
