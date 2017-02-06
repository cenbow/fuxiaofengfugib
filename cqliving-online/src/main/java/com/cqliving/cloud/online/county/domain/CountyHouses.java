package com.cqliving.cloud.online.county.domain;


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
 * 区县楼盘表 Entity
 * Date: 2017-01-05 10:11:11
 * @author Code Generator
 */
@Entity
@Table(name = "county_houses")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CountyHouses extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 已上线 */
    public static final Byte STATUS3 = 3;
    /** 待上线 */
    public static final Byte STATUS1 = 1;
	
    /** 状态 */
    public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
    static {
        allStatuss.put(STATUS3, "已上线");
        allStatuss.put(STATUS1,"待上线");
    }
	/** 主键 */
	private Long id;
	/** 区县表id */
	private Long countyId;
	/** 楼盘名称 */
	private String name;
	/** 楼盘地址 */
	private String addr;
	/** 建设单位 */
	private String unit;
	/** 公积金贷款银行 */
	private String bank;
	/** 状态 */
    private Byte status;
	/** 创建时间 */
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getCountyId(){
		return this.countyId;
	}
	
	public void setCountyId(Long countyId){
		this.countyId = countyId;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getAddr(){
		return this.addr;
	}
	
	public void setAddr(String addr){
		this.addr = addr;
	}
	public String getUnit(){
		return this.unit;
	}
	
	public void setUnit(String unit){
		this.unit = unit;
	}
	public String getBank(){
		return this.bank;
	}
	
	public void setBank(String bank){
		this.bank = bank;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
