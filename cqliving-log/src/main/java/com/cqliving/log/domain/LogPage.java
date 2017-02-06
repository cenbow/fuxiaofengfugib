package com.cqliving.log.domain;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * 平台日志 Entity
 *
 * Date: 2014-03-26 12:16:57
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "LOG_PAGE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LogPage extends BaseLog {
	
	private static final long serialVersionUID = 1880096699212312503L;
}
