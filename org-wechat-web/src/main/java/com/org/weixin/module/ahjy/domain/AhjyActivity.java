package com.org.weixin.module.ahjy.domain;


import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.wechat.framework.domain.AbstractEntity;

import com.google.common.collect.Maps;

/**
 * 艾赫金源活动表 Entity
 *
 * Date: 2016-03-26 09:10:38
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "ahjy_activity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AhjyActivity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 未开始 */
	public static final byte STATUS0 = 0;
	/** 活动中 */
	public static final byte STATUS1 = 1;
	/** 已结束 */
	public static final byte STATUS2 = 2;
		
	/** {0:未开始,1:活动中,2:已结束} */
		public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS0, "未开始");
		allStatuss.put(STATUS1, "活动中");
		allStatuss.put(STATUS2, "已结束");
	}
	/** 未领取 */
	public static final byte ISGETAWARD0 = 0;
	/** 已领取 */
	public static final byte ISGETAWARD1 = 1;
		
	/** 是否已领奖{0:未领取,1:已领取} */
		public static final Map<Byte, String> allIsGetAwards = Maps.newTreeMap();
	static {
		allIsGetAwards.put(ISGETAWARD0, "未领取");
		allIsGetAwards.put(ISGETAWARD1, "已领取");
	}
	
	/** 活动ID */
	private Long id;
	/** {0:未开始,1:活动中,2:已结束} */
	private Byte status;
	/** 创建人 */
	private Long createUserId;
	/** 创建时间 */
	private Date createTime;
	/** 活动开始时间 */
	private Date beginTime;
	/** 活动结束时间 */
	private Date finishTime;
	/** 中奖人ID */
	private Long awardUserId;
	/** 实物CODE */
	private String awardPhysical;
	/** 实物图像URL */
    private String img;
	/** 积分CODE */
	private String awardIntegral;
	/** 优惠券CODE */
	private String awardCoupon;
	/** 是否已领奖{0:未领取,1:已领取} */
	private Byte isGetAward;
	/** 奖品领取时间 */
	private Date awardGainTime;
	/** 领取人电话 */
	private String receiverPhone;
	/** 奖品JSON串 */
	private String awardJson;
	
	@Override
    @Id
	@GeneratedValue
	public Long getId(){
		return this.id;
	}
	
	@Override
    public void setId(Long id){
		this.id = id;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Long getCreateUserId(){
		return this.createUserId;
	}
	
	public void setCreateUserId(Long createUserId){
		this.createUserId = createUserId;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getBeginTime(){
		return this.beginTime;
	}
	
	public void setBeginTime(Date beginTime){
		this.beginTime = beginTime;
	}
	public Date getFinishTime(){
		return this.finishTime;
	}
	
	public void setFinishTime(Date finishTime){
		this.finishTime = finishTime;
	}
	public Long getAwardUserId(){
		return this.awardUserId;
	}
	
	public void setAwardUserId(Long awardUserId){
		this.awardUserId = awardUserId;
	}
	public String getAwardPhysical(){
		return this.awardPhysical;
	}
	
	public void setAwardPhysical(String awardPhysical){
		this.awardPhysical = awardPhysical;
	}
	public String getAwardIntegral(){
		return this.awardIntegral;
	}
	
	public void setAwardIntegral(String awardIntegral){
		this.awardIntegral = awardIntegral;
	}
	public String getAwardCoupon(){
		return this.awardCoupon;
	}
	
	public void setAwardCoupon(String awardCoupon){
		this.awardCoupon = awardCoupon;
	}
	public Byte getIsGetAward(){
		return this.isGetAward;
	}
	
	public void setIsGetAward(Byte isGetAward){
		this.isGetAward = isGetAward;
	}
	public Date getAwardGainTime(){
		return this.awardGainTime;
	}
	
	public void setAwardGainTime(Date awardGainTime){
		this.awardGainTime = awardGainTime;
	}
	public String getReceiverPhone(){
		return this.receiverPhone;
	}
	
	public void setReceiverPhone(String receiverPhone){
		this.receiverPhone = receiverPhone;
	}
	public String getAwardJson(){
		return this.awardJson;
	}
	
	public void setAwardJson(String awardJson){
		this.awardJson = awardJson;
	}
	public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
