package com.cqliving.log.domain;


import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Maps;
/**
 * 平台日志 Entity
 *
 * Date: 2014-03-26 12:16:57
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "LOG_OPERATE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LogOperate extends BaseLog{

	private static final long serialVersionUID = -8361652872625597783L;

	/** 显示 */
    public static final Byte TYPE0 = 0;
    /** 不显示 */
    public static final Byte TYPE1 = 1;
        
    /** 状态 */
    public static final Map<Byte, String> allTypes = Maps.newTreeMap();
    static {
        allTypes.put(TYPE0, "显示");
        allTypes.put(TYPE1, "不显示");
    }
}
