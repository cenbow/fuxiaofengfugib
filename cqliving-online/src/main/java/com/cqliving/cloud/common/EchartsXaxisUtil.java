/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月8日
 */
public class EchartsXaxisUtil {

	/**
	 * Title:
	 * <p>Description:Map<Integer,String> key为实际时间的小时数</p>
	 * @author fuxiaofeng on 2016年11月11日
	 * @return
	 */
	public static List<Map<Integer,String>> getHoursXaxis(){
		List<Map<Integer,String>> xaxisData = Lists.newArrayList();
		for(int i =0 ;i<24;i++){
			Map<Integer,String> map = Maps.newLinkedHashMap();
			String x = String.format("%02d",i);
			map.put(i,x);
			xaxisData.add(map);
		}
		return xaxisData;
	}
	
	/**
	 * Title:
	 * <p>Description:Map<Integer,String> key为实际日期的日期天</p>
	 * @author fuxiaofeng on 2016年11月11日
	 * @param date
	 * @param day
	 * @return
	 */
	public static List<Map<Integer,String>> getDayXaxis(Date date,int day){
		List<Map<Integer,String>> xaxisData = Lists.newArrayList();
		//根据时间推算day后面的日期
		for(int i=day;i>0;i--){
			Date newDate = DateUtil.addDays(date,-i);
			Map<Integer,String> map = Maps.newLinkedHashMap();
			map.put(DateUtil.getDay(newDate),DateUtil.formatDate(newDate, DateUtil.FORMAT_YYYY_MM_DD));
			xaxisData.add(map);
		}
		return xaxisData;
	}
}
