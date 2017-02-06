/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.tool.utils;

import java.text.NumberFormat;

/**
 * Title:
 * <p>Description: 货币相关工具</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月23日
 */
public class CurrencyUtil {
	
	/**
	 * <p>Description: 格式化金额，最多保留两位小数</p>
	 * @author Tangtao on 2016年11月23日
	 * @param price 金额（分）
	 * @return
	 */
	public static String format(Integer price) {
		return format(price, 2);
	}
	
	/**
	 * <p>Description: 格式化金额</p>
	 * @author Tangtao on 2016年11月23日
	 * @param price 金额（分）
	 * @param maximumFractionDigits	最多保留几位小数
	 * @return
	 */
	public static String format(Integer price, int maximumFractionDigits) {
		return format(price, maximumFractionDigits, 0);
	}
	
	/**
	 * <p>Description: 格式化金额</p>
	 * @author Tangtao on 2016年11月23日
	 * @param price 金额（分）
	 * @param maximumFractionDigits 最多保留几位小数
	 * @param minimumFractionDigits 最少保留几位小数，不能大于<tt>maximumFractionDigits</tt>
	 * @return
	 */
	public static String format(Integer price, int maximumFractionDigits, int minimumFractionDigits) {
		return format(price, maximumFractionDigits, minimumFractionDigits, false);
	}
	
	/**
	 * <p>Description: 格式化金额</p>
	 * @author Tangtao on 2016年11月23日
	 * @param price 金额（分）
	 * @param maximumFractionDigits 最多保留几位小数
	 * @param minimumFractionDigits 最少保留几位小数，不能大于<tt>maximumFractionDigits</tt>
	 * @param showSymbol 是否显示货币符号（￥）
	 * @return
	 */
	public static String format(Integer price, int maximumFractionDigits, int minimumFractionDigits, boolean showSymbol) {
		if (price == null) {
			price = 0;
		}
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(maximumFractionDigits);	
		format.setMinimumFractionDigits(minimumFractionDigits);	
		return (showSymbol ? "￥" : "") + format.format(price.doubleValue() / 100);
	}

}