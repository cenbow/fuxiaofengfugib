package com.cqliving.codegenerator.db.metadata;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ColumnMetadata {

	/** 字符类型 */
	public static final int DATATYPE_STRING = 0;
	/** 数字类型 */
	public static final int DATATYPE_LONG = 1;

	public static final int DATATYPE_INT = 4;
	/** 日期时间类型 */
	public static final int DATATYPE_DATE = 2;
	/** 对象类型 */
	public static final int DATATYPE_LOB = 3;
	/** -128到127数字类型 */
	public static final int DATATYPE_TINYINT = 5;

	/** 列名称 */
	private String name;
	/** 列类型 */
	private int dataType;
	/** 列精度 */
	private int length;

	/** 是否自动增长 */
	private boolean extra = false;

	/** 是否可以为空 */
	private boolean nullable;

	/** 默认值 */
	private Object defaultValue;

	/** 列备注 */
	private String common;

	/** 常用数字分类字段可选值 */
	private Map<String, String> options;

	public String getPropertyName() {

		String segment[] = name.toLowerCase().split("_");
		String propertyName = segment[0];
		if (segment.length > 1) {
			for (int i = 1; i < segment.length; i++) {
				propertyName += StringUtils.capitalize(segment[i]);
			}
		}
		return propertyName;
	}

	public String getJavaDataType() {
		switch (dataType) {
		case DATATYPE_STRING:
			return "String";
		case DATATYPE_INT:
			return "Integer";
		case DATATYPE_LONG:
			return "Long";
		case DATATYPE_DATE:
			return "Date";
		case DATATYPE_LOB:
			return "String";
		case DATATYPE_TINYINT:
			return "Byte";
		default:
			return "String";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isExtra() {
		return extra;
	}

	public void setExtra(boolean extra) {
		this.extra = extra;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	public void appendOption(String key, String value) {
		synchronized (this.options) {
			if (this.options == null) {
				this.options = new HashMap<String, String>();
			}
		}
		this.options.put(key, value);
	}

}
