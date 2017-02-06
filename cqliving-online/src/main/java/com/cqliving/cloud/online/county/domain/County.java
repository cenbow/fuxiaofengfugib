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
 * 区县表 Entity
 * Date: 2017-01-05 10:11:02
 * @author Code Generator
 */
@Entity
@Table(name = "county")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class County extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 区 */
	public static final Byte TYPE0 = 0;
	/** 县 */
	public static final Byte TYPE1 = 1;
	/** 已上线 */
    public static final Byte STATUS3 = 3;
    /** 待上线 */
    public static final Byte STATUS1 = 1;
    
    /** 状态 */
    public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
    
	/** 区县类别 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
	    allStatuss.put(STATUS3, "已上线");
	    allStatuss.put(STATUS1,"待上线");
		allTypes.put(TYPE0, "区");
		allTypes.put(TYPE1, "县");
	}
	
	/** 主键 */
	private Long id;
	/** 区县名称 */
	private String name;
	/** 区县类别 */
	private Byte type;
	/** 创建时间 */
	private Date createTime;
	/** 状态 */
    private Byte status;
	
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
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
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
