package com.cqliving.cloud.online.interfacc.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月2日
 */
public class AppWeatherResult {

	/** 区域名称 */
	private String cityName;
	/** 当天最高温度 */
	private Integer tmpMax;
	/** 当天最低温度 */
	private Integer tmpMin;
	/** 当天实时温度 */
	private Integer tmpNow;
	/** 天气状况图标地图 */
	private String conditionIconUrl;
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getTmpMax() {
		return tmpMax;
	}

	public void setTmpMax(Integer tmpMax) {
		this.tmpMax = tmpMax;
	}

	public Integer getTmpMin() {
		return tmpMin;
	}

	public void setTmpMin(Integer tmpMin) {
		this.tmpMin = tmpMin;
	}

	public Integer getTmpNow() {
		return tmpNow;
	}

	public void setTmpNow(Integer tmpNow) {
		this.tmpNow = tmpNow;
	}

	public String getConditionIconUrl() {
		return conditionIconUrl;
	}

	public void setConditionIconUrl(String conditionIconUrl) {
		this.conditionIconUrl = conditionIconUrl;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
