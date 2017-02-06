package com.cqliving.cloud.online.app.domain;


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
 * 天气预报表 Entity
 * Date: 2016-05-25 14:15:26
 * @author Code Generator
 */
@Entity
@Table(name = "app_weather")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppWeather extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 非默认 */
	public static final Byte ISDEFAULT0 = 0;
	/** 默认 */
	public static final byte ISDEFAULT1 = 1;
		
	/** 是否默认，当找不到APP_ID时，使用该值 */
	public static final Map<Byte, String> allIsDefaults = Maps.newTreeMap();
	static {
		allIsDefaults.put(ISDEFAULT0, "非默认");
		allIsDefaults.put(ISDEFAULT1, "默认");
	}
	
	/** 主键 */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 天气预报时间 */
	private Date weatherTime;
	/** 区域名称 */
	private String cityName;
	/** 区域拼音，根据该值访问接口 */
	private String cityPhoneticize;
	/** 当天最高温度 */
	private Integer tmpMax;
	/** 当天最低温度 */
	private Integer tmpMin;
	/** 当天实时温度 */
	private Integer tmpNow;
	/** 天气状况代码 */
	private String conditionCode;
	/** 天气状况名称，例如：晴阴 */
	private String conditionName;
	/** 天气状况图标地图 */
	private String conditionIconUrl;
	/** 是否默认，当找不到APP_ID时，使用该值 */
	private Byte isDefault;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 定制文件夹。这个地址是共用的：http://nimage.cqliving.com/images/weathericon/common/101.png，
	 * 当custom_dir不为空（例如等于cq）时，会将公共地址里面的common替换成cq，
	 * 则重庆的天气预报图片地址则为http://nimage.cqliving.com/images/weathericon/cq/101.png */
	private String customDir;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Date getWeatherTime(){
		return this.weatherTime;
	}
	
	public void setWeatherTime(Date weatherTime){
		this.weatherTime = weatherTime;
	}
	public String getCityName(){
		return this.cityName;
	}
	
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
	public String getCityPhoneticize(){
		return this.cityPhoneticize;
	}
	
	public void setCityPhoneticize(String cityPhoneticize){
		this.cityPhoneticize = cityPhoneticize;
	}
	public Integer getTmpMax(){
		return this.tmpMax;
	}
	
	public void setTmpMax(Integer tmpMax){
		this.tmpMax = tmpMax;
	}
	public Integer getTmpMin(){
		return this.tmpMin;
	}
	
	public void setTmpMin(Integer tmpMin){
		this.tmpMin = tmpMin;
	}
	public Integer getTmpNow(){
		return this.tmpNow;
	}
	
	public void setTmpNow(Integer tmpNow){
		this.tmpNow = tmpNow;
	}
	public String getConditionCode(){
		return this.conditionCode;
	}
	
	public void setConditionCode(String conditionCode){
		this.conditionCode = conditionCode;
	}
	public String getConditionName(){
		return this.conditionName;
	}
	
	public void setConditionName(String conditionName){
		this.conditionName = conditionName;
	}
	public String getConditionIconUrl(){
		return this.conditionIconUrl;
	}
	
	public void setConditionIconUrl(String conditionIconUrl){
		this.conditionIconUrl = conditionIconUrl;
	}
	public Byte getIsDefault(){
		return this.isDefault;
	}
	
	public void setIsDefault(Byte isDefault){
		this.isDefault = isDefault;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public String getCustomDir() {
		return customDir;
	}

	public void setCustomDir(String customDir) {
		this.customDir = customDir;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
