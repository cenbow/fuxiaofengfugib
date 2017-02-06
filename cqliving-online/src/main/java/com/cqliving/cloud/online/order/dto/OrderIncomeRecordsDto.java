package com.cqliving.cloud.online.order.dto;


import java.util.Date;

import com.cqliving.tool.common.util.file.Export;

/**
 * 订单收支记录表
 * Date: 2016-12-07 11:09:38
 * @author Code Generator
 */
public class OrderIncomeRecordsDto {

	/** 主键 */
	private Long id;
	/** 客户端id */
	private Long appId;
	/** 订单id，表order_info的主键 */
	private Long orderId;
	/** 订单号 */
	@Export(name = "订单号", order = 4)
	private String orderNo;
	/** 金额（单位：分），有正负 */
	private Integer money;
	@Export(name = "收入", order = 1)
	private String moneyExport;
	/** 收支总金额（单位：分），把当前app之前所有收支记录的money相加，再加上本次money的值 */
	private Integer totalMoney;
	@Export(name = "余额", order = 3)
	private String totalMoneyExport;
	/** 支付渠道 */
    @Export(name = "支付渠道",json = "{1:支付宝,2:微信}", order = 2)
    private Byte payMode;
    /** 创建时间 */
    @Export(name = "时间", pattern = "yyyy-MM-dd hh:mm:ss", order = 0)
    private Date createTime;
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAppId() {
        return appId;
    }
    public void setAppId(Long appId) {
        this.appId = appId;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public Integer getMoney() {
        return money;
    }
    public void setMoney(Integer money) {
        this.money = money;
    }
   
    public Integer getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    public String getMoneyExport() {
        return moneyExport;
    }
    public void setMoneyExport(String moneyExport) {
        this.moneyExport = moneyExport;
    }
    public String getTotalMoneyExport() {
        return totalMoneyExport;
    }
    public void setTotalMoneyExport(String totalMoneyExport) {
        this.totalMoneyExport = totalMoneyExport;
    }
    public Byte getPayMode() {
        return payMode;
    }
    public void setPayMode(Byte payMode) {
        this.payMode = payMode;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
