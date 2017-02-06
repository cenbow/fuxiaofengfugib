package com.cqliving.tool.common.util.file;

import java.util.List;
import java.util.Map;


/**
 * 
 * Title:excel模板对象
 * <p>Description:excel模板对象</p>
 * Copyright (c) feinno 2013
 * @author zhuyf on 2015年1月16日
 */
public class ExcelTemplate {
	
	/** 字段名称*/
	private String fieldName;
	
	/** 字段描述*/
	private String fieldDesc;
	
	/** 字段对应的map类型
	 * 
	 * <bean class="com.feinno.qizhi.shop.common.excel.ExcelTemplate">
			<property name="fieldName" value="type"></property>
			<property name="fieldDesc" value="题目类型"></property>
			<property name="typeMap">
				<map>
					<entry key="单选" value="1" />
					<entry key="多选" value="2" />
					<entry key="问答" value="3" />
					<entry key="实操" value="4" />
				</map>
			</property>					
		</bean>
	*/
	private Map<String,Object> typeMap;

	/** 是否为空 */
	private boolean isBlank;
	
	/** 是否为空过滤器，如果isBlank为true，如果isBlankFilterMap不为空，
	 *  则isBlankFilterMap中的Key对应另一个ExcelTemplate对象中fieldName中的值， 例如：上面配置中的value="type"中的type
	 *  则isBlankFilterMap中的value对应另一个ExcelTemplate对象中typeMap中的值，例如：key="单选",key="多选"
	 */
	private Map<String,List<String>> isBlankFilterMap;
	
	/**
	 * 以下4个值都包含当前值
	 */
	
    /** 最大值*/
    private Double max;
    
    /** 最小值*/
    private Double min;
    
    /** 最大长度*/
    private Integer maxlength;
    
    /** 最小长度*/
    private Integer minlength;
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public Map<String, Object> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<String, Object> typeMap) {
		this.typeMap = typeMap;
	}

	public boolean getIsBlank() {
		return isBlank;
	}

	public void setIsBlank(boolean isBlank) {
		this.isBlank = isBlank;
	}

	public Map<String, List<String>> getIsBlankFilterMap() {
		return isBlankFilterMap;
	}

	public void setIsBlankFilterMap(Map<String, List<String>> isBlankFilterMap) {
		this.isBlankFilterMap = isBlankFilterMap;
	}

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Integer getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(Integer maxlength) {
        this.maxlength = maxlength;
    }

    public Integer getMinlength() {
        return minlength;
    }

    public void setMinlength(Integer minlength) {
        this.minlength = minlength;
    }
}