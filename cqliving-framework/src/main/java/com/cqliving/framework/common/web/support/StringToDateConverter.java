package com.cqliving.framework.common.web.support;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import com.cqliving.framework.utils.Dates;
import com.cqliving.framework.utils.Regexs;

/**
 * Title:处理前台传递过来的日期类型字符串，全局日期类型字段转换器
 * Copyright (c) CQLIVING 2016
 * @author yuwu on 2016年7月14日
 */
public class StringToDateConverter implements Converter<String, Date> {
	//private static final Logger logger = LoggerFactory.getLogger(StringToDateConverter.class);

	//private String pattern;

	public StringToDateConverter(){}
	//public StringToDateConverter(String pattern) {
	//	this.pattern = pattern;
	//}

	public Date convert(String timeStr) {
		if (StringUtils.isBlank(timeStr)) {
			return null;
		}
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		//simpleDateFormat.setLenient(false);
		if(Regexs.matcher(Regexs.DATE_YYYY_MM_DD_REGEXP, timeStr)){
			return Dates.parse(timeStr, Dates.YYYY_MM_DD);
		}else if(Regexs.matcher(Regexs.DATE_YYYY_MM_DD_HH_MM_SS_REGEXP, timeStr)){
			return Dates.parse(timeStr, Dates.YYYY_MM_DD_HH_MM_SS);
		}else if(Regexs.matcher(Regexs.DATE_YYYY_MM_DD_HH_MM_REGEXP, timeStr)){
			return Dates.parse(timeStr, Dates.YYYY_MM_DD_HH_MM);
		}else{
			return Dates.parse(timeStr, Dates.YYYY_MM_DD);
		}
	}
}
