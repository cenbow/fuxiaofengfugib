/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.basic.dao;

import java.util.List;
import java.util.Map;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author tangqiang on 2015年5月28日
 */
public interface PropertiesDaoCustom {

	public List<Map<String,Object>> loadPropertiesWith(String config);
}
