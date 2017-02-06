package com.cqliving.basic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;


/**
 * 通用字典表; InnoDB free: 140288 kB Entity
 * 
 * Date: 2014-03-26 10:34:29
 * 
 * @author Acooly Code Generator
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "OL_OPTION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Option extends AbstractEntity {
    /** 举报类型 */
    public static final String TYPECODE1 = "USER_REPORT_TYPE";
    /** 适合年龄 */
    public static final String TYPECODE2 = "SUITABLE_AGE";
    /** 课程时长 */
    public static final String TYPECODE3 = "SUITABLE_AGE";
    /** 企业性质 */
    public static final String TYPECODE4 = "ENT_NATURE";
    /** 支付方式 */
    public static final String TYPECODE5 = "PAYMENT";
    /** 工作经验类型 */
    public static final String TYPECODE6 = "W0RK_EXPERIENCE";
    /** 学历形式 */
    public static final String TYPECODE7 = "EDUFORM";
    /** 语言读写能力 */
    public static final String TYPECODE8 = "LANGUAGE_READ_CAPACI";
    /** 语言 */
    public static final String TYPECODE9 = "LANGUAGE";
    /** 企业规模 */
    public static final String TYPECODE10 = "ENT_SCALE";
    /** 工作类型 */
    public static final String TYPECODE11 = "WORKMODE";
    /** 婚姻状况 */
    public static final String TYPECODE12 = "MARRIAGE";
    /** 薪资 */
    public static final String TYPECODE13 = "SALARY";
    /** 学历 */
    public static final String TYPECODE14 = "EDUCATION";
    /** 民族 */
    public static final String TYPECODE15 = "NATIVE";
    /** 政治面貌 */
    public static final String TYPECODE16 = "POLITICS";
    /** 性别 */
    public static final String TYPECODE17 = "GENDER";
    /** 问题类型 */
    public static final String TYPECODE18 = "QUESTIONTYPE";
    /** 咨询类型 */
    public static final String TYPECODE19 = "CONSULT_TYPE";
    /** 中国大区 */
    public static final String TYPECODE20 = "CHINAAREA";
	/** ID */
	private Long id;

	/** 编码 */
	private String code;

	/** 名称 */
	private String name;

	/** 类别编码 */
	private String typeCode;

	/** 类别名称 */
	private String typeName;

	/** 排序值 */
	private int sortKey;

	@Id
	@GeneratedValue
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getSortKey() {
		return this.sortKey;
	}

	public void setSortKey(int sortKey) {
		this.sortKey = sortKey;
	}

	@Override
	public String toString() {
		return "Option [id=" + id + ", code=" + code + ", name=" + name
				+ ", typeCode=" + typeCode + ", typeName=" + typeName
				+ ", sortKey=" + sortKey + "]";
	}

}
