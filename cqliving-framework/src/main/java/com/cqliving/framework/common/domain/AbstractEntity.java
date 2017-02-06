package com.cqliving.framework.common.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 统一定义id的entity基类
 * 
 * 一般用于基于数据库的实体类
 * 
 * @author zhangpu
 * 
 */
public abstract class AbstractEntity implements Serializable {
	/** UID */
	private static final long serialVersionUID = -5797027386139497608L;
	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
