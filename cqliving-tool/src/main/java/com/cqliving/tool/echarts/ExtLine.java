/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.tool.echarts;

import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.style.LineStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月9日
 */
public class ExtLine extends Line{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LineStyle lineStyle;

	public LineStyle getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}
	
}
