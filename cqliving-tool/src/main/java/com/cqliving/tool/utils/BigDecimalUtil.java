/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.tool.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2016年2月22日
 */
public class BigDecimalUtil {

	public static Double divide(Double a,Double b,int scale){
		
		if(null == a || null == b || 0 == b)return 0.0;
		
		BigDecimal aa = new BigDecimal(a);
		BigDecimal bb = new BigDecimal(b);
		
		return aa.divide(bb,scale, RoundingMode.HALF_EVEN).doubleValue();
	}
}
