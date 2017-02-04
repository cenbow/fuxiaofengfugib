/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.jywth.dao;

import java.util.List;
import java.util.Map;

import com.org.weixin.module.jywth.domain.JywthAwardHis;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月5日
 */
public interface JywthAwardHisDaoCustom {

	public List<JywthAwardHis> queryAwardByConditions(Map<String, String> searchMap, Map<String, Boolean> sortMap);
}
